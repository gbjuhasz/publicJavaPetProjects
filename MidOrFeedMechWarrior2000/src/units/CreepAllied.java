package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CreepAllied extends Creep {
  //fields for positioning on board, image picking and drawing effects
  private String facingDirection = "Up";
  private String unitTypeForImage = "CreepAllied";

  public CreepAllied(String fileLocation, int x, int y){

    super.setPosX(x);
    super.setPosY(y);
    super.calculateImageCenterCoordinates();

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileLocation));
      super.setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
