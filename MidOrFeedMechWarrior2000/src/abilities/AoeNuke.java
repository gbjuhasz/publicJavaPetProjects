package abilities;

import java.util.ArrayList;
import java.util.List;
import units.Mech;
import units.Unit;

public class AoeNuke extends Ability {
  private String name = "Cluster bomb";
  private String category = "Targeted AOE";
  private int energyCost = 100;
  private int coolDown = 800;
  private int area = 108;
  private int damage = 1080;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;

  @Override
  public int getEnergyCost() {
    return energyCost;
  }

  @Override
  public int getCoolDown() {
    return coolDown;
  }

  @Override
  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }

  public int getArea() {
    return area;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public void setCanBeusedAgainInRound(int canBeusedAgainInRound) {
    this.canBeusedAgainInRound = canBeusedAgainInRound;
  }

  @Override
  public void useAOEAbility(Mech mech, Unit unitTargeted, ArrayList<Unit> listOfEnemyUnits, int roundCounter) {
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setLastUsedInRound(roundCounter);
    setCanBeusedAgainInRound(roundCounter + getCoolDown());
    unitTargeted.setHealthPoints(unitTargeted.getHealthPoints() - getDamage());
    List<Unit> listOfUnitsInAoeRange = unitTargeted.findUnitsWithinRangeOfAreaEffectAroundUnit(unitTargeted, listOfEnemyUnits, area);
    for (Unit unitInAoe : listOfUnitsInAoeRange) {
      unitInAoe.setHealthPoints((unitInAoe.getHealthPoints() - getDamage()));
    }
  }
}
