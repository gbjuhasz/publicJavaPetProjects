package visualeffects;

import board.Tile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HUD extends Tile {

  HUDUnitImage hudUnitImage = new HUDUnitImage();

  public HUD(String fileLocation, Integer posX, Integer posY){
    super.setPosX(posX);
    super.setPosY(posY);

    try {
      BufferedImage tileImage = ImageIO.read(new File(fileLocation));
      setImage(tileImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public HUDUnitImage getHudUnitImage() {
    return hudUnitImage;
  }
}
