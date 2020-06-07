package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class EnergyRegenerationAura extends Aura {

  private String name = "Energy Siphon";
  private String category = "defensive aura";
  private int bonus = 5;
  private int range = 144;


  public EnergyRegenerationAura() {
    super.setPosX(505);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/EnergyRegenerationAura.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getBonus() {
    return bonus;
  }

  @Override
  public int getRange() {
    return range;
  }

  public void setRange(int range) {
    this.range = range;
  }

  public void setBonus(int bonus) {
    this.bonus = bonus;
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel() + 1);
    setBonus(getBonus() * super.getLevel());
    setRange(getRange() + 72);
  }
}
