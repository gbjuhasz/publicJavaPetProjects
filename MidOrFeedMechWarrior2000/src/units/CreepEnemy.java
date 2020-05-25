package units;

import java.util.HashMap;
import waypoints.Waypoint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class CreepEnemy extends Creep {

  private HashMap<String, Integer> xCoordinatesForRightClickEffectInEveryDirection = new HashMap() {{
    put("Up", 28);
    put("Down", 28);
    put("Right", 33);
    put("Left", 28);
  }};
  private HashMap<String, Integer> yCoordinatesForRightClickEffectInEveryDirection = new HashMap() {
    {
      put("Up", 2);
      put("Down", 20);
      put("Right", 20);
      put("Left", 20);
    }
  };

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

  @Override
  public HashMap<String, Integer> getxCoordinatesForRightClickEffectInEveryDirection() {
    return xCoordinatesForRightClickEffectInEveryDirection;
  }

  @Override
  public HashMap<String, Integer> getyCoordinatesForRightClickEffectInEveryDirection() {
    return yCoordinatesForRightClickEffectInEveryDirection;
  }

  @Override
  public void calculateImageCenterCoordinates() {
    setImageMiddleX(getPosX() + 23);
    setImageMiddleY(getPosY() + 23);
  }
}
