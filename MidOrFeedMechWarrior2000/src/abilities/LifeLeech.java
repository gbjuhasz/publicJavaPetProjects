package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class LifeLeech extends PassiveAbility {

  private String name = "Health Siphon";
  private String category = "defensive passive";

  public LifeLeech(){
    super.setPosX(505);
    super.setPosY(700);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/LifeLeech.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    mech.setLifeLeechPercentage(getLevel());
  }
}
