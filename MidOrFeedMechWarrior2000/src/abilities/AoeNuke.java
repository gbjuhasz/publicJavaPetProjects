package abilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import units.Mech;
import units.Unit;

public class AoeNuke extends ActiveAbility {
  private String name = "Cluster bomb";
  private String category = "offensive active targeted aoe";
  private int energyCost = 100;
  private int coolDown = 800;
  private int area = 108;
  private int damage = 80;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;
  private String key = "W";

  public AoeNuke(){
    super.setPosX(475);
    super.setPosY(675);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/AoeNuke.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getArea() {
    return area;
  }

  @Override
  public String getCategory() {
    return category;
  }


  public void setDamage(int damage) {
    this.damage = damage;
  }

  @Override
  public void setCoolDown(int coolDown) {
    this.coolDown = coolDown;
  }

  @Override
  public void setEnergyCost(int energyCost) {
    this.energyCost = energyCost;
  }

  @Override
  public void setCanBeUsedAgainInRound(int canBeusedAgainInRound) {
    this.canBeusedAgainInRound = canBeusedAgainInRound;
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    setDamage(getDamage() + super.getLevel() * 30);
    setCoolDown(getCoolDown() - super.getLevel() * 100);
    setEnergyCost(getEnergyCost() - super.getLevel() * 10);
  }

  @Override
  public void useAOEAbility(Mech mech, Unit unitTargeted, ArrayList<Unit> listOfEnemyUnits, int roundCounter) {
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setLastUsedInRound(roundCounter);
    setCanBeUsedAgainInRound(roundCounter + getCoolDown());
    unitTargeted.setHealthPoints(unitTargeted.getHealthPoints() - getDamage());
    List<Unit> listOfUnitsInAoeRange = unitTargeted.findUnitsWithinRangeOfAreaEffectAroundUnit(unitTargeted, listOfEnemyUnits, area);
    for (Unit unitInAoe : listOfUnitsInAoeRange) {
      unitInAoe.setHealthPoints((unitInAoe.getHealthPoints() - getDamage()));
      manageDeathByAbility(mech, unitInAoe, roundCounter);
    }
    manageDeathByAbility(mech, unitTargeted, roundCounter);
  }
}
