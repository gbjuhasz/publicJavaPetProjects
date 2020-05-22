package mechherocontrol;

import board.BoardComponent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import units.*;

public class MechHeroMouseClickReactionManager {

  public void reactToLeftClick(List<Unit> listOfAllUnits, MouseEvent e
  ) {
    if(identifyClickedUnit(listOfAllUnits, e)!= null){
      identifyClickedUnit(listOfAllUnits, e).setHighlighted(true);
    } else {
      for (Unit unit: listOfAllUnits
           ) {
        if(unit.getUnitType().equals("mechHero")){
          unit.setHighlighted(true);
        }
      }
    };
    /*  if (unit.getUnitType().equals("turret")) {
        if (e.getX() >= unit.getPosX() &&
                e.getX() <= unit.getPosX() + 144 &&
                e.getY() >= unit.getPosY() &&
                e.getY() <= unit.getPosY() + 144) {
          unit.setHighlighted(true);
        }
      } else {
        if (e.getX() >= unit.getImageMiddleX() -36 &&
                e.getX() <= unit.getImageMiddleX()+ 36 &&
                e.getY() >= unit.getImageMiddleY() -36 &&
                e.getY() <= unit.getImageMiddleY() + 36) {
          unit.setHighlighted(true);
        }
      }
    }*/
    }

  public void reactToRightClick(MechHero mechHero,
                                MechEnemy mechEnemy,
                                ArrayList<Creep> listOfCreepEnemy,
                                Turret turretEnemy,
                                List<Mech> listOfMechs,
                                MouseEvent mouseEvent
  ) {

    List<Unit> listOfAllClickableUnits = new ArrayList<>();
    listOfAllClickableUnits.add(mechEnemy);
    listOfAllClickableUnits.addAll(listOfCreepEnemy);
    listOfAllClickableUnits.add(turretEnemy);
    if (identifyClickedUnit(listOfAllClickableUnits, mouseEvent) == null) {
      mechHero.setUnitTargeted(null);
      mechHero.setMouseEventMarkingLocation(mouseEvent);
    } else {
      mechHero.setMouseEventMarkingLocation(null);
      mechHero.setUnitTargeted(identifyClickedUnit(listOfAllClickableUnits, mouseEvent));
    }
  }

  public Unit identifyClickedUnit(List<Unit> listOfUnits, MouseEvent e) {
  /*  for (Unit unit : listOfClickableUnits) {
      if (mouseEvent.getX() >= unit.getPosX() &&
              mouseEvent.getX() <= unit.getPosX() + 72 &&
              mouseEvent.getY() >= unit.getPosY() &&
              mouseEvent.getY() <= unit.getPosY() + 72) {
        return unit;
      }
    }
    return null;*/
    double closestUnitDistance = calculateDistanceBetweenClickAndUnits(e, listOfUnits.get(0));
    Unit closestUnit = listOfUnits.get(0);
    for (Unit unit : listOfUnits) {
      unit.setHighlighted(false);
      double distance = calculateDistanceBetweenClickAndUnits(e, unit);
      if (distance < closestUnitDistance) {
        closestUnitDistance = distance;
        closestUnit = unit;
      }
    }

    if (closestUnitDistance <= 72) {
      return closestUnit;
    } else {
      return null;
    }
  }

  public double calculateDistanceBetweenClickAndUnits(MouseEvent e, Unit unit) {
    int a = Math.abs(e.getX() - unit.getImageMiddleX());
    int b = Math.abs(e.getY() - unit.getImageMiddleY());
    double distance = Math.sqrt(a * a + b * b);
    return distance;
  }
}
