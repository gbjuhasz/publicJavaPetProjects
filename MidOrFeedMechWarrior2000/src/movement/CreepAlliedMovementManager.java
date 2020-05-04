package movement;

import java.awt.image.BufferedImage;
import units.Unit;

public class CreepAlliedMovementManager extends BotMovementManager {

  @Override
  public void moveUnitTowardsDestination(Unit unitMakingMove,
                                          Unit unitDestination,
                                          int roundCounter){

    IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();
    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    unitMakingMove.calculateTargetDirection(unitDestination);
    String targetDirection = unitMakingMove.getTargetDirection();

    changeCoordinatesTowardsTargetDirection(unitMakingMove, currentX, currentY, targetDirection);
    BufferedImage newImage = pickImage(findImageFileLocation(unitMakingMove.getFacingDirection(),
            isRoundNumberEven(roundCounter)));
    unitMakingMove.setImage(newImage);

  }

  @Override
  public String findImageFileLocation(String  facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/creepallied/CreepAllied" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }

}
