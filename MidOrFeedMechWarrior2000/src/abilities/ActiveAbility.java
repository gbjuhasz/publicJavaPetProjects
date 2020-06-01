package abilities;

import board.BoardComponent;
import experiencesystem.LevelUpManager;
import java.util.ArrayList;
import java.util.List;
import units.Mech;
import units.Unit;

public abstract class ActiveAbility extends MechAbility {

  private int energyCost;
  private int coolDown;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;
  private int range;
  private boolean activated = false;
  private String key;
  private int damage;
  private int area;
  private LevelUpManager levelUpManager = new LevelUpManager();


  public int getCoolDown() {
    return coolDown;
  }

  public int getEnergyCost() {
    return energyCost;
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

  public String getKey() {
    return key;
  }

  public int getCanBeusedAgainInRound() {
    return canBeusedAgainInRound;
  }

  public int getArea() {
    return area;
  }

  public int getDamage() {
    return damage;
  }

  public int getLastUsedInRound() {
    return lastUsedInRound;
  }

  public void setCanBeUsedAgainInRound(int canBeusedAgainInRound) {
    this.canBeusedAgainInRound = canBeusedAgainInRound;
  }

  public void setLastUsedInRound(int lastUsedInRound) {
    this.lastUsedInRound = lastUsedInRound;
  }

  public void levelUpAbility(Mech mech) {
    setLevel(getLevel() + 1);
  }

  public void useAbility(Mech mech, Unit unitTargeted, int roundCounter) {
  }

  public void useAOEAbility(Mech mech, Unit unitTargeted, ArrayList<Unit> listOfUnits, int roundCounter) {
  }

  public void manageDeathByAbility(Mech mech, Unit unitTargeted, int roundCounter) {
    if (unitTargeted.getHealthPoints() <= 0) {
      unitTargeted.setHighlighted(false);
      unitTargeted.setAlive(false);
      unitTargeted.setRoundDied(roundCounter);
      unitTargeted.setPosX(-100);
      unitTargeted.setPosY(-100);
      List<Mech> listOfMech = new ArrayList<>();
      listOfMech.add(mech);
      levelUpManager.grantXpToMechs(unitTargeted, listOfMech);
    }
  }
}
