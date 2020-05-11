package mechherocontrol;

import botmovement.IllegalMoveChecker;
import botmovement.MovementManager;
import fighting.AttackManager;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import units.*;

public class MechHeroMouseCommandManager extends MovementManager {

  MechHeroAttackManager mechHeroAttackManager = new MechHeroAttackManager();
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
    listOfAllClickableUnits.addAll(listOfCreepAllied);
    listOfAllClickableUnits.addAll(listOfCreepEnemy);
    listOfAllClickableUnits.add(turretEnemy);
    if (identifyClickedUnit(listOfAllClickableUnits, mouseEvent) == null) {
      moveMechHeroTowardsMouseEventLocation(mechHero, mouseEvent, roundCounter);
    } else {
      if (mechHero.calculateDistanceBetweenUnits(
              identifyClickedUnit(listOfAllClickableUnits, mouseEvent)) > mechHero.getAttackRange()) {
        moveMechHeroTowardsMouseEventLocation(mechHero, mouseEvent, roundCounter);
      } else {
        attackUnitMarkedByMouseEvent(mechHero, identifyClickedUnit(listOfAllClickableUnits, mouseEvent), roundCounter);
      }
    }
  }

  public void moveMechHeroTowardsMouseEventLocation(MechHero mechHero, MouseEvent mouseEvent, Integer roundCounter) {
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
      setFacingDirection(mechHero,  mechHero.getPosX() - mechHeroX ,
               mechHero.getPosY()- mechHeroY);
      mechHero.setPosX(mechHeroX);
      mechHero.setPosY(mechHeroY);
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isRoundNumberEven(roundCounter)));
      mechHero.setImage(newImage);
    } else {
      mechHero.calculateImageCenterCoordinates();

      setFacingDirection(mechHero,  mechHero.getPosX() - mechHeroX ,
              mechHero.getPosY()- mechHeroY);
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isRoundNumberEven(roundCounter)));
      mechHero.setImage(newImage);
    }
  }

  public void attackUnitMarkedByMouseEvent(MechHero mechHero, Unit unitTargeted, int roundCounter) {
    mechHeroAttackManager.attackTargetUnit(mechHero, unitTargeted, roundCounter);
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

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechhero/MechHero" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }

  @Override
  public void setFacingDirection(Unit unit, int changeInX, int changeInY) {

    if (changeInX > 0) {
      unit.setFacingDirection("Right");
    } else if (changeInX < 0) {
      unit.setFacingDirection("Left");
    } else if (changeInX == 0 && changeInY < 0) {
      unit.setFacingDirection("Up");
    } else {
      unit.setFacingDirection("Down");
    }
  }
}
