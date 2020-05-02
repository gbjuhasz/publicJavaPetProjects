package movement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.MechHero;

public class MechHeroMovementManager extends MovementManager {

  IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();

  public void moveMechHeroWithKeys(MechHero mechHero, int changeInX, int changeInY, int roundCounter) {

    int newX = mechHero.getPosX() + changeInX;
    int newY = mechHero.getPosY() + changeInY;

    if (illegalMoveChecker.isMoveLegal(newX, newY) == false) {
      setFacingDirection(mechHero, changeInX, changeInY);
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isRoundNumberEven(roundCounter)));
      mechHero.setImage(newImage);
      return;
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
