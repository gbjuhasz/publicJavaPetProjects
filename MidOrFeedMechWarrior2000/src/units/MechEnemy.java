package units;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;

public class MechEnemy extends Mech {

  private int detectionRange = 0;

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
  public HashMap<String, Integer> getxCoordinatesForRightClickEffectInEveryDirection() {
    return xCoordinatesForRightClickEffectInEveryDirection;
  }

  @Override
  public HashMap<String, Integer> getyCoordinatesForRightClickEffectInEveryDirection() {
    return yCoordinatesForRightClickEffectInEveryDirection;
  }

  @Override
  public int getDetectionRange() {
    return detectionRange;
  }
}
