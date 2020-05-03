package movement;

import units.Unit;

public class CreepMovementManager extends MovementManager {

  public void moveCreepTowardsDestination(Unit unit){

    IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();

  }

  @Override
  public String findImageFileLocation(String  facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/creepallied/CreepAllied" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }
}
