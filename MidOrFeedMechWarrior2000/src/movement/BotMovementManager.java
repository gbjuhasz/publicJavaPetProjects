package movement;

import java.awt.image.BufferedImage;
import units.Unit;

public abstract class BotMovementManager extends MovementManager {

  StuckUnitAssister stuckUnitAssister = new StuckUnitAssister();

  public void moveUnit(Unit unitMakingMove,
                       Unit unitDestination,
                       int roundCounter) {

    IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();
    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    unitMakingMove.calculateTargetDirection(unitDestination);
    String targetDirection = unitMakingMove.getTargetDirection();

    changeCoordinatesTowardsTargetDirection(unitMakingMove, currentX, currentY, targetDirection);
    if(illegalMoveChecker.isMoveLegal(unitDestination.getPosX(), unitDestination.getPosY())) {
      stuckUnitAssister.helpIfUnitIsStuck(unitMakingMove);
    }
    BufferedImage newImage = pickImage(findImageFileLocation(unitMakingMove.getFacingDirection(),
            isRoundNumberEven(roundCounter)));
    unitMakingMove.setImage(newImage);

  }

  public void changeCoordinatesTowardsTargetDirection(Unit unitMakingMove,
                                                      int currentX,
                                                      int currentY,
                                                      String targetDirection) {

    if (targetDirection.contains("N")) {
      unitMakingMove.setPosY(currentY - 18);
      if (targetDirection.length() == 1) {
        unitMakingMove.setFacingDirection("UP");
      }
    } else if (targetDirection.contains("S")) {
      unitMakingMove.setPosY(currentY + 18);
      if (targetDirection.length() == 1) {
        unitMakingMove.setFacingDirection("DOWN");
      }
    }

    if (targetDirection.contains("W")) {
      unitMakingMove.setPosX(currentX - 18);
      unitMakingMove.setFacingDirection("LEFT");
    } else if (targetDirection.contains("E")) {
      unitMakingMove.setPosX(currentX + 18);
      unitMakingMove.setFacingDirection("RIGHT");
    }
  }
}
