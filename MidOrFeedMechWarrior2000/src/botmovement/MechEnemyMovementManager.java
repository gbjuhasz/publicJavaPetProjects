package botmovement;

public class MechEnemyMovementManager extends BotMovementManager {

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechenemy/MechEnemy" + facingDirection + ".png";
    return fileLocation;
  }
}
