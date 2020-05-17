package movement;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import units.Unit;

public abstract class BotMovementManager extends MovementManager {

  StuckUnitManager stuckUnitManager = new StuckUnitManager();
  IllegalMoveCheckerCreeps illegalMoveCheckerCreeps = new IllegalMoveCheckerCreeps();
  IllegalMoveCheckerMapObjects illegalMoveCheckerMapObjects = new IllegalMoveCheckerMapObjects();


  public void moveUnit(Unit unitMakingMove,
                       Unit unitDestination,
                       List<Unit> listOfAllUnits,
                       int roundCounter) {

    unitMakingMove.calculateTargetDirection(unitDestination);
    String targetDirection = unitMakingMove.getTargetDirection();

    changeCoordinatesTowardsTargetDirection(unitMakingMove, listOfAllUnits, targetDirection);

    if (!illegalMoveCheckerMapObjects.isMoveLegal(unitMakingMove.getPosX(), unitMakingMove.getPosY())) {
      unitMakingMove.setPosX(unitMakingMove.getPreviousX());
      unitMakingMove.setPosY(unitMakingMove.getPreviousY());
      stuckUnitManager.helpIfUnitIsStuck(unitMakingMove);
    }

    if(unitDestination.getUnitType().contains("creep")) {
      checkCreepsForCollision(unitMakingMove,listOfAllUnits,targetDirection);
    }

    checkAndSetFacingDirection(unitMakingMove);
    unitMakingMove.calculateImageCenterCoordinates();
    BufferedImage newImage = pickImage(findImageFileLocation(unitMakingMove.getFacingDirection(),
            unitMakingMove.getFeetForward()));
    unitMakingMove.setImage(newImage);
  }

  public void changeCoordinatesTowardsTargetDirection(Unit unitMakingMove,
                                                      List<Unit> listOfAllUnits,
                                                      String targetDirection) {

    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    int futureX = unitMakingMove.getPosX();
    int futureY = unitMakingMove.getPosY();

    if (targetDirection.contains("N")) {
      futureY = futureY - 1;
    } else if (targetDirection.contains("S")) {
      futureY = futureY + 1;
    }

    if (targetDirection.contains("W")) {
      futureX = futureX - 1;
    } else if (targetDirection.contains("E")) {
      futureX = futureX + 1;
    }

    List<Unit> listOfAllUnitsWithoutUnitMakingMove = new ArrayList<>();

    for (Unit unit : listOfAllUnits
    ) {
      if (unitMakingMove.getPosX() != unit.getPosX() &&
              unitMakingMove.getPosY() != unit.getPosY()) {
        listOfAllUnitsWithoutUnitMakingMove.add(unit);
      }
    }
    if (unitMakingMove.getUnitType().contains("creep")) {
      if (illegalMoveCheckerCreeps.isMoveLegalForCreeps(futureX, futureY, listOfAllUnitsWithoutUnitMakingMove)) {
        unitMakingMove.setPreviousX(currentX);
        unitMakingMove.setPreviousY(currentY);
        unitMakingMove.setPosX(futureX);
        unitMakingMove.setPosY(futureY);
      }
    } else {
      unitMakingMove.setPreviousX(currentX);
      unitMakingMove.setPreviousY(currentY);
      unitMakingMove.setPosX(futureX);
      unitMakingMove.setPosY(futureY);
    }
  }

  public void checkAndSetFacingDirection(Unit unitMakingMove) {
    int posXChange = unitMakingMove.getPosX() - unitMakingMove.getPreviousX();
    int posYChange = unitMakingMove.getPosY() - unitMakingMove.getPreviousY();
    if (posXChange > 0) {
      unitMakingMove.setFacingDirection("RIGHT");
    } else if (posXChange < 0) {
      unitMakingMove.setFacingDirection("LEFT");
    } else if (posYChange < 0) {
      unitMakingMove.setFacingDirection("UP");
    } else if (posYChange > 0) {
      unitMakingMove.setFacingDirection("DOWN");
    }
  }

  private void checkCreepsForCollision(Unit unitMakingMove,
                                      List<Unit> listOfAllUnits,
                                      String targetDirection) {

    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();

    List<Unit> listOfAllUnitsWithoutUnitMakingMove = new ArrayList<>();

    for (Unit unit : listOfAllUnits
    ) {
      if (unitMakingMove.getPosX() != unit.getPosX() &&
              unitMakingMove.getPosY() != unit.getPosY()) {
        listOfAllUnitsWithoutUnitMakingMove.add(unit);
      }
    }
    if (illegalMoveCheckerCreeps.isMoveLegalForCreeps(currentX, currentY, listOfAllUnitsWithoutUnitMakingMove)) {
      unitMakingMove.setPosX(unitMakingMove.getPreviousX());
      unitMakingMove.setPosY(unitMakingMove.getPreviousY());
    }
  }
}
