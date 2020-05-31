package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;
import units.Unit;

public class Invisibility extends Ability {

  private String name = "Cloaking device";
  private String category = "defensive active";
  private int energyCost = 50;
  private int coolDown = 1000;
  private int duration = 200;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;
  private String key = "E";

  public Invisibility(){
    super.setPosX(505);
    super.setPosY(675);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/Invisibility.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCoolDown() {
    return coolDown;
  }

  @Override
  public int getEnergyCost() {
    return energyCost;
  }

  @Override
  public int getLastUsedInRound() {
    return super.getLastUsedInRound();
  }

  @Override
  public void useAbility(Mech mech, Unit unitTargeted, int roundCounter){
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setCanBeUsedAgainInRound(roundCounter+getCoolDown());
    mech.setInvisible(true);
    mech.setInvisibleUntilRound(duration);
  }
}
