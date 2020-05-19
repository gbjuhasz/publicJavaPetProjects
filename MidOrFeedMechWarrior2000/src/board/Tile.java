package board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Unit;

public class Tile extends BoardComponent {

  private BufferedImage image;


  public Tile(String fileName, Integer x, Integer y) {

    super.setPosX(x);
    super.setPosY(y);

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileName));
      setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public BufferedImage getImage() {
    return image;
  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, super.getPosX(), super.getPosY(), null);
    }
  }
}
