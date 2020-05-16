package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class MechEnemy extends Mech {

  private int detectionRange = 0;

  public MechEnemy(String fileLocation, int x, int y) {

    super.setPosX(x);
    super.setPosY(y);
    super.setRespawnX(x);
    super.setRespawnY(y);
    super.calculateImageCenterCoordinates();
    super.setUnitType("mechEnemy");
    Random random = new Random();
    super.setFeetForward(super.getFeetImageNames()[random.nextInt(2)]);

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
