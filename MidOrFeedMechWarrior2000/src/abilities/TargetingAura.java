package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class TargetingAura extends Aura {
  private boolean activated;
  private String name = "Ultimate: Overmind";
  private String category = "offensive aura";

  public TargetingAura(){
    super.setPosX(535);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/TargetingAura.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  @Override
  public void levelUpAbility(Mech mech) {
    if(mech.getLevel() == 6) {
      super.setLevel(super.getLevel() + 1);
    }
  }
}
