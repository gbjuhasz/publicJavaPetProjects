package movement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Unit;

public abstract class MovementManager {

  public void setFacingDirection(Unit unit, int changeInX, int changeInY) {

    if (changeInX == 0 && changeInY == -18) {
      unit.setFacingDirection("Up");
    } else if (changeInX == 0 && changeInY == 18) {
      unit.setFacingDirection("Down");
    } else if (changeInX == -18 && changeInY == 0) {
      unit.setFacingDirection("Left");
    } else {
      unit.setFacingDirection("Right");
    }
  }

  public String isItTimeToSwitchFeet(Unit unitMoving, int roundCounter) {


    if(unitMoving.getSwitchFeetInRound() == roundCounter){
      if(unitMoving.getFeetForward().equals("ODD")){
        unitMoving.setFeetForward("EVEN");
        unitMoving.setSwitchFeetInRound(roundCounter + unitMoving.getSwitchFeetEveryXRound());
      } else {
        unitMoving.setFeetForward("ODD");
        unitMoving.setSwitchFeetInRound(roundCounter + unitMoving.getSwitchFeetEveryXRound());
      }
    }
    return unitMoving.getFeetForward();
  }

  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {
    return "fileLocation";
  }

  public BufferedImage pickImage(String fileLocation) {

    BufferedImage image = null;

    try {
      image = ImageIO.read(new File(fileLocation));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
