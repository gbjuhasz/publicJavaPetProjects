package movement;

import java.awt.image.BufferedImage;
import units.MechHero;

public class MechHeroMovementManager extends MovementManager {

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

  public void moveMechHeroWithMouse(MechHero mechHero, int mouseX, int mouseY){
    int mechHeroX = mechHero.getPosX();
    int mechHeroY = mechHero.getPosY();
    if(mouseX > mechHeroX){
      mechHero.setPosX(mechHeroX + 18);
    } else if ( mouseX < mechHeroX){
      mechHero.setPosX(mechHeroX - 18);
    }
    if(mouseY > mechHeroY){
      mechHero.setPosY(mechHeroY + 18);
    } else if ( mouseY < mechHeroY){
      mechHero.setPosY(mechHeroY - 18);
    }
  }
}
