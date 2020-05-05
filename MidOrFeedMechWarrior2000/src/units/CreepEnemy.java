package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CreepEnemy extends Creep {

  public CreepEnemy(String fileLocation, int x, int y, boolean isAlive,
                    int waveTimer) {

    if (isAlive) {
      super.setPosX(x);
      super.setPosY(y);
      super.setRoundToRespawn(2 * waveTimer);
    } else {
      super.setPosX(-100);
      super.setPosY(-100);
      super.setRoundToRespawn(waveTimer);
    }
    super.setAlive(isAlive);
    super.setWaveSpawnTimer(waveTimer);
    super.calculateImageCenterCoordinates();

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileLocation));
      super.setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
