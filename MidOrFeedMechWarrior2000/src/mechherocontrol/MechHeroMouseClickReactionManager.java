package mechherocontrol;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import units.*;

public class MechHeroMouseClickReactionManager {

  public void reactToLeftClick(List<Unit> listOfAllUnits, MouseEvent e
  ) {
    for (Unit unit : listOfAllUnits) {
      unit.setHighlighted(false);
      if (unit.getUnitType().equals("turret")) {
        if (e.getX() >= unit.getPosX() &&
                e.getX() <= unit.getPosX() + 144 &&
                e.getY() >= unit.getPosY() &&
                e.getY() <= unit.getPosY() + 144) {
          unit.setHighlighted(true);
        }
      } else {
        if (e.getX() >= unit.getPosX() &&
                e.getX() <= unit.getPosX() + 72 &&
                e.getY() >= unit.getPosY() &&
                e.getY() <= unit.getPosY() + 72) {
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

  public Unit identifyClickedUnit(List<Unit> listOfClickableUnits, MouseEvent mouseEvent) {
    for (Unit unit : listOfClickableUnits) {
      if (mouseEvent.getX() >= unit.getPosX() &&
              mouseEvent.getX() <= unit.getPosX() + 72 &&
              mouseEvent.getY() >= unit.getPosY() &&
              mouseEvent.getY() <= unit.getPosY() + 72) {
        return unit;
      }
    }
    return null;
  }
}
