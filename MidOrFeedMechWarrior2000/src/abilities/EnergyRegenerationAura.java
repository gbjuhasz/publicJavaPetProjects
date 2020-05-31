package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EnergyRegenerationAura extends Aura {

  private String name = "Energy Siphon";
  private String category = "defensive aura";

  public EnergyRegenerationAura(){
    super.setPosX(505);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/EnergyRegenerationAura.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
