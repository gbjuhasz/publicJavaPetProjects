package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ExtraArmorAura extends Aura {

  private String name = "Protective field";
  private String category = "defensive aura";
  private int bonus = 5 * getLevel();


  public ExtraArmorAura() {
    super.setPosX(475);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/ExtraArmorAura.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
