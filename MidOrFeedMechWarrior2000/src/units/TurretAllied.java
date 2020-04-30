package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TurretAllied extends Turret {

  public TurretAllied(){
    super.setPosX(80);
    super.setPosY(150);
    try {
      BufferedImage turretAlliedImage = ImageIO.read(new File("images/turretallied/TurretAllied.png"));
      super.setImage(turretAlliedImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
