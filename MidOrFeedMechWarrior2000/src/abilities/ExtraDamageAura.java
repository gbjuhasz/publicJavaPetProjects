package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;

public class ExtraDamageAura extends Aura {

  private String name = "Weapon Booster Field";
  private String category = "offensive aura";
  private int range = 144;
  private int bonus = 5;

  public ExtraDamageAura() {
    super.setPosX(445);
    super.setPosY(725);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/ExtraDamageAura.png")));
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

  public void setBonus(int bonus) {
    this.bonus = bonus;
  }

  public void setRange(int range) {
    this.range = range;
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    setBonus(getBonus() * super.getLevel());
    setRange(getRange() + 72);
  }
}
