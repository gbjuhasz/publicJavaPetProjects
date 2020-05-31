package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class Evasion extends PassiveAbility {

  private String name = "Cloaking device";
  private String category = "defensive passive";

  public Evasion(){
    super.setPosX(475);
    super.setPosY(700);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/Evasion.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    mech.setEvasionChance(getLevel());
  }
}
