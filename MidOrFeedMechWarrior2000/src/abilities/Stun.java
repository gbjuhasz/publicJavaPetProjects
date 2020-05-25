package abilities;

import units.Mech;
import units.Unit;

public class Stun extends Ability {
  private String name = "EMP shock";
  private int energyCost = 100;
  private int coolDown = 1000;
  private int stunnedForRounds = 300;
  private int range = 216;
  private String category = "Single target";
  private int lastUsedInRound;
  private int canBeusedAgainInRound;

  public Stun(){}

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getLastUsedInRound() {
    return lastUsedInRound;
  }

  @Override
  public int getCanBeusedAgainInRound() {
    return canBeusedAgainInRound;
  }

  @Override
  public int getEnergyCost() {
    return energyCost;
  }

  @Override
  public int getCoolDown() {
    return coolDown;
  }

  public int getStunnedForRounds() {
    return stunnedForRounds;
  }

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setLastUsedInRound(int lastUsedInRound) {
    this.lastUsedInRound = lastUsedInRound;
  }

  @Override
  public void setCanBeusedAgainInRound(int canBeusedAgainInRound) {
    this.canBeusedAgainInRound = canBeusedAgainInRound;
  }

  @Override
  public void setEnergyCost(int energyCost) {
    this.energyCost = energyCost;
  }

  @Override
  public void setCoolDown(int coolDown) {
    this.coolDown = coolDown;
  }

  public void setStunnedForRounds(int stunnedForRounds) {
    this.stunnedForRounds = stunnedForRounds;
  }

  @Override
  public void useAbility(Mech mech, Unit unitTargeted, int roundCounter){
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setCanBeusedAgainInRound(roundCounter+getCoolDown());
    unitTargeted.setStunned(true);
    unitTargeted.setStunOverInRound(roundCounter + getStunnedForRounds());
  }
}
