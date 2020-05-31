package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class Overcharge extends PassiveAbility {

  private String name = "Ultimate: Overcharge";
  private String category = "offensive passive";

  public Overcharge(){
    super.setPosX(535);
    super.setPosY(700);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/Overcharge.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
  }
}
