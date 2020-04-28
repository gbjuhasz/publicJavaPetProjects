package board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {

  BufferedImage image;
  int x;
  int y;

  public Tile(){
  }

  public Tile(String fileLocationName, Integer x, Integer y) {

    this.x = x;
    this.y = y;

    try {
      image = ImageIO.read(new File(fileLocationName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, x, y, null);
    }
  }
}
