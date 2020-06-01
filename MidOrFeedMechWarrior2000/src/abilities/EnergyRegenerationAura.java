package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class EnergyRegenerationAura extends Aura {

  private String name = "Energy Siphon";
  private String category = "defensive aura";
  private int bonus = 5;

  public EnergyRegenerationAura(){
    super.setPosX(505);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/EnergyRegenerationAura.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int getBonus() {
    return bonus;
  }

  public void setBonus(int bonus) {
    this.bonus = bonus;
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    setBonus(getBonus() * super.getLevel());
  }
}
