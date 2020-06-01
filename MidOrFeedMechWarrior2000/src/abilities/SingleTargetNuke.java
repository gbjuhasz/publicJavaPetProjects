package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;
import units.Unit;

public class SingleTargetNuke extends ActiveAbility {

  private String name = "Ultimate: Drone Strike";
  private String category = "offensive targeted nuke";
  private int energyCost = 150;
  private int coolDown = 4000;
  private int range = 216;
  private int damage = 400;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;
  private String key = "R";


  public SingleTargetNuke() {
    super.setPosX(535);
    super.setPosY(675);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/SingleTargetNuke.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public String getCategory() {
    return category;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  @Override
  public void setLastUsedInRound(int lastUsedInRound) {
    this.lastUsedInRound = lastUsedInRound;
  }

  @Override
  public void setCanBeUsedAgainInRound(int canBeusedAgainInRound) {
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

  @Override
  public void useAbility(Mech mech, Unit unitTargeted, int roundCounter) {
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setCanBeUsedAgainInRound(roundCounter + getCoolDown());
    unitTargeted.setHealthPoints(unitTargeted.getHealthPoints() - damage);
    manageDeathByAbility(mech,unitTargeted,roundCounter);
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    setDamage(getDamage() + super.getLevel() * 200);
  }
}
