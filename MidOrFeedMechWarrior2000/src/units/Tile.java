package units;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile extends Unit {

  BufferedImage image;

  public Tile(String filename, Integer x, Integer y) {

    super.setPosX(x);
    super.setPosY(y);

    try {
      image = ImageIO.read(new File(filename));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, super.getPosX(), super.getPosY(), null);
    }
  }
}
