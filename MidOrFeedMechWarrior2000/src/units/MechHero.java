package units;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MechHero extends Mech {

  public MechHero(String fileName, int x, int y){

    super.setPosX(x);
    super.setPosY(y);

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileName));
      super.setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
