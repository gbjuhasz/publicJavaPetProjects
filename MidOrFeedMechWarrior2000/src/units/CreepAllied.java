package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class CreepAllied extends Creep {

  public CreepAllied(String fileLocation, int x, int y,
                     boolean isAlive,
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
    super.setRespawnX(x);
    super.setRespawnY(y);
    super.calculateImageCenterCoordinates();
    super.setAlive(isAlive);
    super.setWaveSpawnTimer(waveTimer);
    super.setUnitType("creepAllied");
    Random random = new Random();
    super.setFeetForward(super.getFeetImageNames()[random.nextInt(2)]);
    try {
      BufferedImage tileImage = ImageIO.read(new File(fileLocation));
      super.setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
