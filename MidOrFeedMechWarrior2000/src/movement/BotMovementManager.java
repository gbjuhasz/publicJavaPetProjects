package movement;

import java.awt.image.BufferedImage;
import java.util.List;
import units.Unit;

public abstract class BotMovementManager extends MovementManager {

  StuckUnitManager stuckUnitManager = new StuckUnitManager();
  IllegalMoveCheckerUnits illegalMoveCheckerUnits = new IllegalMoveCheckerUnits();
  IllegalMoveCheckerMapObjects illegalMoveCheckerMapObjects = new IllegalMoveCheckerMapObjects();


  public void moveUnit(Unit unitMakingMove,
                       Unit unitDestination,
                       List<Unit> listOfAllUnits,
                       int roundCounter) {

    unitMakingMove.calculateTargetDirection(unitDestination);
    String targetDirection = unitMakingMove.getTargetDirection();

    changeCoordinatesTowardsTargetDirection(unitMakingMove, listOfAllUnits, targetDirection, roundCounter);

    if(!illegalMoveCheckerMapObjects.isMoveLegal(unitMakingMove.getPosX(), unitMakingMove.getPosY())) {
      unitMakingMove.setPosX(unitMakingMove.getPreviousX());
      unitMakingMove.setPosY(unitMakingMove.getPreviousY());
      stuckUnitManager.helpIfUnitIsStuck(unitMakingMove);
    }
    unitMakingMove.calculateImageCenterCoordinates();
    BufferedImage newImage = pickImage(findImageFileLocation(unitMakingMove.getFacingDirection(),
            unitMakingMove.getFeetForward()));
    unitMakingMove.setImage(newImage);
  }

  public void changeCoordinatesTowardsTargetDirection(Unit unitMakingMove,
                                                      List<Unit> listOfAllUnits,
                                                      String targetDirection,
                                                      int roundCounter) {

    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    int futureX = unitMakingMove.getPosX();
    int futureY = unitMakingMove.getPosY();

    if (targetDirection.contains("N")) {
      futureY = futureY - 1;
      if (targetDirection.length() == 1) {
        unitMakingMove.setFacingDirection("UP");
      }
    } else if (targetDirection.contains("S")) {
      futureY = futureY + 1;
      if (targetDirection.length() == 1) {
        unitMakingMove.setFacingDirection("DOWN");
      }
    }

    if (targetDirection.contains("W")) {
      futureX = futureX - 1;
      unitMakingMove.setFacingDirection("LEFT");
    } else if (targetDirection.contains("E")) {
      futureX = futureX + 1;
      unitMakingMove.setFacingDirection("RIGHT");
    }
    for (int i = 0; i < listOfAllUnits.size() ; i++) {
      if(listOfAllUnits.get(i).getPosX() == currentX &&
      listOfAllUnits.get(i).getPosY() == currentY){
        listOfAllUnits.remove(i);
      }
    }
    if(!illegalMoveCheckerUnits.isMoveLegal(futureX,futureY,listOfAllUnits)){
      unitMakingMove.setPreviousX(currentX);
      unitMakingMove.setPreviousY(currentY);
    } else {
      unitMakingMove.setPreviousX(currentX);
      unitMakingMove.setPreviousY(currentY);
      unitMakingMove.setPosX(futureX);
      unitMakingMove.setPosY(futureY);
    }
  }
}
