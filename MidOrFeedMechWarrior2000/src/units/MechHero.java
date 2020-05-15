package units;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MechHero extends Mech {

  MouseEvent mouseEventMarkingLocation;
  Unit unitTargeted;

  public MechHero(String fileLocation, int x, int y) {

    super.setPosX(x);
    super.setPosY(y);
    super.setRespawnX(x);
    super.setRespawnY(y);
    super.calculateImageCenterCoordinates();
    super.setUnitType("mechHero");

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

  public Unit getUnitTargeted() {
    return unitTargeted;
  }

  public void setMouseEventMarkingLocation(MouseEvent mouseEventMarkingLocation) {
    this.mouseEventMarkingLocation = mouseEventMarkingLocation;
  }

  public void setUnitTargeted(Unit unitTargeted) {
    this.unitTargeted = unitTargeted;
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
