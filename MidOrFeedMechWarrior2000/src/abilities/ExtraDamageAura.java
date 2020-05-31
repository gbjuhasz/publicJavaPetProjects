package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ExtraDamageAura extends Aura {

  private String name = "Weapon booster field";
  private String category = "offensive aura";
  private int bonus = 5 * getLevel();

  public ExtraDamageAura() {
    super.setPosX(445);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/ExtraDamageAura.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
