package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CreepEnemy extends Creep {

  //fields for respawning
  private int respawnX;
  private int respawnY;

  public CreepEnemy(String fileLocation, int x, int y){

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
