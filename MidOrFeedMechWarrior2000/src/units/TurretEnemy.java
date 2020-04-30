package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TurretEnemy extends Turret {

  public TurretEnemy(){
    super.setPosX(580);
    super.setPosY(280);
    try {
      BufferedImage turretAlliedImage = ImageIO.read(new File("images/turretenemy/TurretEnemy.png"));
      super.setImage(turretAlliedImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
