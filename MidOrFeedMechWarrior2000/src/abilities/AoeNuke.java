package abilities;

import java.util.List;
import units.Mech;
import units.Unit;

public class AoeNuke extends Ability {
  private String name = "Cluster bomb";
  private int energyCost = 100;
  private int coolDown = 800;
  private int stunnedForRounds = 300;
  private int range = 216;
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
  public void useAOEAbility(Mech mech, Unit unitTargeted, List<Unit> listOfUnits, int roundCounter){
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setCanBeusedAgainInRound(roundCounter+getCoolDown());

  }
}
