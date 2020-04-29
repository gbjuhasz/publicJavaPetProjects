package movement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.MechHero;

public class MechHeroMovementManager {

  public void moveMechHeroWithKeys(MechHero mechHero, int changeInX, int changeInY, int roundCounter){

    int newX = mechHero.getPosX() + changeInX;
    int newY = mechHero.getPosY() + changeInY;
    mechHero.setPosX(newX);
    mechHero.setPosY(newY);
    setFacingDirection(mechHero, changeInX,changeInY);
    BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(), isRoundNumberEven(roundCounter)));
    mechHero.setImage(newImage);
  }

  public void setFacingDirection(MechHero mechHero, int changeInX, int changeInY){
    if(changeInX == 0 && changeInY == -18){
      mechHero.setFacingDirection("Up");
    } else if (changeInX == 0 && changeInY == 18){
      mechHero.setFacingDirection("Down");
    } else if (changeInX == -18 && changeInY == 0) {
      mechHero.setFacingDirection("Left");
    } else {
      mechHero.setFacingDirection("Right");
    }
  }

  public String isRoundNumberEven(int roundCounter){
    if(roundCounter % 2 == 0){
      return "Even";
    } else {
      return "Odd";
    }
  }

  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd){
    String fileLocation = "/Users/BenceJuhasz/Desktop/publicJavaPetProjects/publicJavaPetProjects/MidOrFeedMechWarrior2000" +
            "/images/mechHero/MechHero" + facingDirection + roundEvenOrOdd +".png";
    return fileLocation;
  }

  public BufferedImage pickImage(String fileLocation){
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(fileLocation));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
