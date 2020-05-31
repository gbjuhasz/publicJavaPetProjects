package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class CritChance extends PassiveAbility {

  private String name = "Aiming system";
  private String category = "offensive passive";

  public CritChance(){
    super.setPosX(445);
    super.setPosY(700);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/CritChance.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    mech.setCritChance(getLevel());
  }
}
