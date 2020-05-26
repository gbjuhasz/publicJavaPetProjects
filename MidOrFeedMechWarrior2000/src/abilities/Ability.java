package abilities;

import java.util.ArrayList;
import java.util.List;
import units.Mech;
import units.Unit;

public abstract class Ability {

  private String name;
  private String category;
  private int energyCost;
  private int coolDown;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;
  private int range;
  private boolean activated = false;

  public int getCoolDown() {
    return coolDown;
  }

  public int getEnergyCost() {
    return energyCost;
  }

  public String getName() {
    return name;
  }

  public int getRange() {
    return range;
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public void setRange(int range) {
    this.range = range;
  }

  public void setCoolDown(int coolDown) {
    this.coolDown = coolDown;
  }

  public void setEnergyCost(int energyCost) {
    this.energyCost = energyCost;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCanBeusedAgainInRound() {
    return canBeusedAgainInRound;
  }

  public int getLastUsedInRound() {
    return lastUsedInRound;
  }

  public void setCanBeUsedAgainInRound(int canBeusedAgainInRound) {
    this.canBeusedAgainInRound = canBeusedAgainInRound;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setLastUsedInRound(int lastUsedInRound) {
    this.lastUsedInRound = lastUsedInRound;
  }

  public void useAbility(Mech mech, Unit unitTargeted, int roundCounter){}

  public void useAOEAbility(Mech mech, Unit unitTargeted, ArrayList<Unit> listOfUnits, int roundCounter){}
}
