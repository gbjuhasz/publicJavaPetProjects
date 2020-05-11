package mechherocontrol;

import botmovement.IllegalMoveChecker;
import fighting.AttackManager;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import units.*;

public class MechHeroMouseCommandManager {

  AttackManager attackManager = new AttackManager();
  IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();


  public void reactToMouseAction(MechHero mechHero,
                                 MechEnemy mechEnemy,
                                 ArrayList<Creep> listOfCreepAllied,
                                 ArrayList<Creep> listOfCreepEnemy,
                                 Turret turretAllied,
                                 Turret turretEnemy,
                                 int roundCounter,
                                 MouseEvent mouseEvent
  ) {

    List<Unit> listOfAllClickableUnits = new ArrayList<>();
    listOfAllClickableUnits.add(mechEnemy);
    for (Creep creep :
            listOfCreepAllied) {
      listOfAllClickableUnits.add(creep);
    }
    for (Creep creep : listOfCreepEnemy
    ) {
      listOfAllClickableUnits.add(creep);
    }
    listOfAllClickableUnits.add(turretEnemy);
    if (identifyClickedUnit(listOfAllClickableUnits, mouseEvent) == null) {
      moveMechHeroTowardsMouseEventLocation(mechHero, mouseEvent);
    } else {
      if (mechHero.calculateDistanceBetweenUnits(
              identifyClickedUnit(listOfAllClickableUnits, mouseEvent)) > mechHero.getAttackRange()) {
        moveMechHeroTowardsMouseEventLocation(mechHero, mouseEvent);
      } else {
        attackUnitMarkedByMouseEvent(mechHero, identifyClickedUnit(listOfAllClickableUnits, mouseEvent), roundCounter);
      }
    }
  }

  public void moveMechHeroTowardsMouseEventLocation(MechHero mechHero, MouseEvent mouseEvent) {
    int mechHeroX = mechHero.getPosX();
    int mechHeroY = mechHero.getPosY();
    int mouseEventX = mouseEvent.getX();
    int mouseEventY = mouseEvent.getY();

    if (mouseEventX > mechHeroX &&
            mouseEventX < mechHeroX + 72) {
      if (mouseEventY > mechHeroY + 72) {
        mechHero.setPosY(mechHeroY + 18);

      } else if (mouseEventY < mechHeroY) {
        mechHero.setPosY(mechHeroY - 18);

      }
    } else if (mouseEventY > mechHeroY &&
            mouseEventY < mechHeroY + 72) {
      if (mouseEventX > mechHeroX) {
        mechHero.setPosX(mechHeroX + 18);

      } else if (mouseEventX < mechHeroX) {
        mechHero.setPosX(mechHeroX - 18);

      }
    } else if (mouseEventX < mechHeroX &&
            mouseEventY < mechHeroY) {
      mechHero.setPosX(mechHeroX - 18);
      mechHero.setPosY(mechHeroY - 18);

    } else if (mouseEventX > mechHeroX + 72 &&
            mouseEventY < mechHeroY) {
      mechHero.setPosX(mechHeroX + 18);
      mechHero.setPosY(mechHeroY - 18);

    } else if (mouseEventX > mechHeroX + 72 &&
            mouseEventY > mechHeroY + 72) {
      mechHero.setPosX(mechHeroX + 18);
      mechHero.setPosY(mechHeroY + 18);

    } else if (mouseEventX < mechHeroX &&
            mouseEventY > mechHeroY + 72) {
      mechHero.setPosX(mechHeroX - 18);
      mechHero.setPosY(mechHeroY + 18);
    }
    if (!illegalMoveChecker.isMoveLegal(mechHero.getPosX(),mechHero.getPosY())){
      mechHero.setPosX(mechHeroX);
      mechHero.setPosY(mechHeroY);
    }
  }

  public void attackUnitMarkedByMouseEvent(MechHero mechHero, Unit unitTargeted, int roundCounter) {
    attackManager.attackTargetUnit(mechHero, unitTargeted, roundCounter);
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
