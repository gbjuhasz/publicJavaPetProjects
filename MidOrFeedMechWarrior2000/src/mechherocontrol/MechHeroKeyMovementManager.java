package mechherocontrol;

import java.awt.image.BufferedImage;
import movement.IllegalMoveCheckerMapObjects;
import movement.MovementManager;
import units.MechHero;

public class MechHeroKeyMovementManager extends MovementManager {

  IllegalMoveCheckerMapObjects illegalMoveCheckerMapObjects = new IllegalMoveCheckerMapObjects();

  public void moveMechHeroWithKeys(MechHero mechHero, int changeInX, int changeInY, int roundCounter) {

    int newX = mechHero.getPosX() + changeInX;
    int newY = mechHero.getPosY() + changeInY;

    if (!illegalMoveCheckerMapObjects.isMoveLegal(newX, newY)) {
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
