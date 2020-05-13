package botmovement;

import java.awt.image.BufferedImage;
import java.util.List;
import units.Unit;

public abstract class BotMovementManager extends MovementManager {

  StuckUnitAssister stuckUnitAssister = new StuckUnitAssister();
  IllegalMoveCheckerUnits illegalMoveCheckerUnits = new IllegalMoveCheckerUnits();


  public void moveUnit(Unit unitMakingMove,
                       Unit unitDestination,
                       List<Unit> listOfAllUnits,
                       int roundCounter) {

    IllegalMoveCheckerMapObjects illegalMoveCheckerMapObjects = new IllegalMoveCheckerMapObjects();
    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    unitMakingMove.calculateTargetDirection(unitDestination);
    String targetDirection = unitMakingMove.getTargetDirection();
    changeCoordinatesTowardsTargetDirection(unitMakingMove, currentX, currentY, listOfAllUnits, targetDirection);
    if(!illegalMoveCheckerMapObjects.isMoveLegal(unitMakingMove.getPosX(), unitMakingMove.getPosY())) {
      stuckUnitAssister.helpIfUnitIsStuck(unitMakingMove);
    }
    unitMakingMove.calculateImageCenterCoordinates();
    BufferedImage newImage = pickImage(findImageFileLocation(unitMakingMove.getFacingDirection(),
            isRoundNumberEven(roundCounter)));
    unitMakingMove.setImage(newImage);

  }

  public void changeCoordinatesTowardsTargetDirection(Unit unitMakingMove,
                                                      int currentX,
                                                      int currentY,
                                                      List<Unit> listOfAllUnits,
                                                      String targetDirection) {

    int futureX = currentX;
    int futureY = currentY;

    if (targetDirection.contains("N")) {
      futureY = futureY - 18;
      if (targetDirection.length() == 1) {
        unitMakingMove.setFacingDirection("UP");
      }
    } else if (targetDirection.contains("S")) {
      futureY = futureY + 18;
      if (targetDirection.length() == 1) {
        unitMakingMove.setFacingDirection("DOWN");
      }
    }

    if (targetDirection.contains("W")) {
      futureX = futureX - 18;
      unitMakingMove.setFacingDirection("LEFT");
    } else if (targetDirection.contains("E")) {
      futureX = futureX + 18;
      unitMakingMove.setFacingDirection("RIGHT");
    }
    for (int i = 0; i < listOfAllUnits.size() ; i++) {
      if(listOfAllUnits.get(i).getPosX() == currentX &&
      listOfAllUnits.get(i).getPosY() == currentY){
        listOfAllUnits.remove(i);
      }
    }
    if(!illegalMoveCheckerUnits.isMoveLegal(futureX,futureY,listOfAllUnits)){
      unitMakingMove.setPosX(currentX);
      unitMakingMove.setPosY(currentY);
    } else {
      unitMakingMove.setPosX(futureX);
      unitMakingMove.setPosY(futureY);
    }
  }
}
