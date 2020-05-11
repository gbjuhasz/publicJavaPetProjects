package botmovement;

public class CreepAlliedMovementManager extends BotMovementManager {

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/creepallied/CreepAllied" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }

}
