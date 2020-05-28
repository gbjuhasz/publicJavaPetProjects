package abilities;

import units.Mech;
import units.Unit;

public class Escape extends Ability {

  private String name = "Invisibility";
  private String category = "Escape";
  private int energyCost = 50;
  private int coolDown = 1000;
  private int duration = 200;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;

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
