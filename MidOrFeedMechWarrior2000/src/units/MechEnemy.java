package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MechEnemy extends Mech {

  private int detectionRange = 720;

  public MechEnemy(String fileLocation, int x, int y) {

    super.setPosX(x);
    super.setPosY(y);
    super.setRespawnX(x);
    super.setRespawnY(y);
    super.calculateImageCenterCoordinates();
    super.setUnitType("mech");

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileLocation));
      super.setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getDetectionRange() {
    return detectionRange;
  }
}
