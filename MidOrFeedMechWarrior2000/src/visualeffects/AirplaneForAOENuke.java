package visualeffects;

import board.Tile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AirplaneForAOENuke extends Tile {

  public AirplaneForAOENuke(){
    super.setPosX(-100);
    super.setPosY(-100);

    try {
      BufferedImage explosionImage = ImageIO.read(new File("images/explosions/rightClickExplosionEVEN.png"));
      setImage(explosionImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
