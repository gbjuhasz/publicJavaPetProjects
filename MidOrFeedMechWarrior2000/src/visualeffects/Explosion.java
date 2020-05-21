package visualeffects;

import board.Tile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Explosion extends Tile {

  private Integer disappearsInRound;

  public Explosion() {
    super.setPosX(-100);
    super.setPosY(-100);

    try {
      BufferedImage explosionImage = ImageIO.read(new File("images/explosions/rightClickExplosionEVEN.png"));
      setImage(explosionImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Integer getDisappearsInRound() {
    return disappearsInRound;
  }

  public void setDisappearsInRound(Integer disappearsInRound) {
    this.disappearsInRound = disappearsInRound;
  }

  @Override
  public void setImage(BufferedImage image) {
    super.setImage(image);
  }

  public void pickImage(String fileLocation) {

    BufferedImage image = null;

    try {
      image = ImageIO.read(new File(fileLocation));
    } catch (IOException e) {
      e.printStackTrace();
    }
    super.setImage(image);
  }
}

