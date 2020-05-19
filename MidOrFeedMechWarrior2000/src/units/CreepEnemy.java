package units;

import waypoints.Waypoint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class CreepEnemy extends Creep {

  public CreepEnemy(String fileLocation,
                    int x,
                    int y,
                    boolean isAlive,
                    int waveTimer,
                    List<Waypoint> waypointList) {

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
    super.setAlive(isAlive);
    super.setWaveSpawnTimer(waveTimer);
    super.calculateImageCenterCoordinates();
    super.setUnitType("creepEnemy");
    super.setListOfWaypointsToFollow(waypointList);
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
