package mechherocontrol;

import java.awt.image.BufferedImage;
import botmovement.IllegalMoveChecker;
import botmovement.MovementManager;
import units.MechHero;

public class MechHeroKeyMovementManager extends MovementManager {

  IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();

  public void moveMechHeroWithKeys(MechHero mechHero, int changeInX, int changeInY, int roundCounter) {

    int newX = mechHero.getPosX() + changeInX;
    int newY = mechHero.getPosY() + changeInY;

    if (!illegalMoveChecker.isMoveLegal(newX, newY)) {
      setFacingDirection(mechHero, changeInX, changeInY);
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isRoundNumberEven(roundCounter)));
      mechHero.setImage(newImage);
    } else {

      mechHero.setPosX(newX);
      mechHero.setPosY(newY);
      mechHero.calculateImageCenterCoordinates();

      setFacingDirection(mechHero, changeInX, changeInY);
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isRoundNumberEven(roundCounter)));
      mechHero.setImage(newImage);
    }
  }

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechhero/MechHero" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }
}
