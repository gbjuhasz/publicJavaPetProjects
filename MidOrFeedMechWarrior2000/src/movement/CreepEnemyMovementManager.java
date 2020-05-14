package movement;

public class CreepEnemyMovementManager extends BotMovementManager {

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/creepenemy/CreepEnemy" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }
}
