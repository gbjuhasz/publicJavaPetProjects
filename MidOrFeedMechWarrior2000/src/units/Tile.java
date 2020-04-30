package units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile extends Unit {

  public Tile(String fileName, Integer x, Integer y) {

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
