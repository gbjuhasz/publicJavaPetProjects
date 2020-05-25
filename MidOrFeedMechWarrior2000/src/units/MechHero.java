package units;

import abilities.AoeNuke;
import abilities.Stun;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;

public class MechHero extends Mech {

  MouseEvent mouseEventMarkingLocation;
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

  public MechHero(String fileLocation, int x, int y) {

    super.setPosX(x);
    super.setPosY(y);
    super.setRespawnX(x);
    super.setRespawnY(y);
    super.calculateImageCenterCoordinates();
    super.setUnitType("mechHero");
    Random random = new Random();
    super.setFeetForward(super.getFeetImageNames()[random.nextInt(2)]);
    super.getListOfAbilities().add(new Stun());
    super.getListOfAbilities().add(new AoeNuke());

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileLocation));
      super.setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public MouseEvent getMouseEventMarkingLocation() {
    return mouseEventMarkingLocation;
  }

  @Override
  public HashMap<String, Integer> getyCoordinatesForRightClickEffectInEveryDirection() {
    return yCoordinatesForRightClickEffectInEveryDirection;
  }

  @Override
  public HashMap<String, Integer> getxCoordinatesForRightClickEffectInEveryDirection() {
    return xCoordinatesForRightClickEffectInEveryDirection;
  }

  public void setMouseEventMarkingLocation(MouseEvent mouseEventMarkingLocation) {
    this.mouseEventMarkingLocation = mouseEventMarkingLocation;
  }

  public void calculateMouseEventDirection() {
    if (mouseEventMarkingLocation != null) {
      int posX = super.getPosX();
      int posY = super.getPosY();
      int targetX = mouseEventMarkingLocation.getX() - 36;
      int targetY = mouseEventMarkingLocation.getY() - 72;

      if (posX == targetX && posY < targetY) {
        setTargetDirection("S");
      } else if (posX == targetX && posY > targetY) {
        setTargetDirection("N");
      } else if (posX > targetX && posY == targetY) {
        setTargetDirection("W");
      } else if (posX < targetX && posY == targetY) {
        setTargetDirection("E");
      } else if (posX > targetX && posY < targetY) {
        setTargetDirection("SW");
      } else if (posX < targetX && posY < targetY) {
        setTargetDirection("SE");
      } else if (posX < targetX) {
        setTargetDirection("NE");
      } else if (posX > targetX) {
        setTargetDirection("NW");
      }
    }
  }
}
