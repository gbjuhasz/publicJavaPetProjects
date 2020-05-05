package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MechHero extends Mech {

  private int respawnX = 72;
  private int respawnY = 0;

  public MechHero(String fileLocation, int x, int y) {

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

  @Override
  public int getRespawnX() {
    return respawnX;
  }

  @Override
  public int getRespawnY() {
    return respawnY;
  }
}
