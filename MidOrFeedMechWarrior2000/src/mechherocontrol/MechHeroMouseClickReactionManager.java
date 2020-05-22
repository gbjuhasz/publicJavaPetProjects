package mechherocontrol;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import units.*;

public class MechHeroMouseClickReactionManager {

  public void reactToLeftClick(List<Unit> listOfAllUnits, MouseEvent e
  ) {
    if (identifyClickedUnit(listOfAllUnits, e) != null) {
      identifyClickedUnit(listOfAllUnits, e).setHighlighted(true);
    } else {
      for (Unit unit : listOfAllUnits
      ) {
        if (unit.getUnitType().equals("mechHero")) {
          unit.setHighlighted(true);
        }
      }
    }
  }

  public void reactToRightClick(MechHero mechHero,
                                MechEnemy mechEnemy,
                                ArrayList<Creep> listOfCreepEnemy,
                                Turret turretEnemy,
                                List<Mech> listOfMechs,
                                MouseEvent mouseEvent
  ) {
    mechHero.setUnitTargeted(null);
    mechHero.setMouseEventMarkingLocation(null);
    List<Unit> listOfAllClickableUnits = new ArrayList<>();
    listOfAllClickableUnits.add(mechEnemy);
    listOfAllClickableUnits.addAll(listOfCreepEnemy);
    listOfAllClickableUnits.add(turretEnemy);
    if (identifyClickedUnit(listOfAllClickableUnits, mouseEvent) == null) {
      mechHero.setMouseEventMarkingLocation(mouseEvent);
    } else {
      mechHero.setUnitTargeted(identifyClickedUnit(listOfAllClickableUnits, mouseEvent));
    }
  }

  public Unit identifyClickedUnit(List<Unit> listOfUnits, MouseEvent e) {
    double closestUnitDistance = calculateDistanceBetweenClickAndUnits(e, listOfUnits.get(0));
    Unit closestUnit = listOfUnits.get(0);
    for (Unit unit : listOfUnits) {
      unit.setHighlighted(false);
      if(unit.isAlive() == true) {
        double distance = calculateDistanceBetweenClickAndUnits(e, unit);
        if (distance < closestUnitDistance) {
          closestUnitDistance = distance;
          closestUnit = unit;
        }
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
