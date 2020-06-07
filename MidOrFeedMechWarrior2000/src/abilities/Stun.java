package abilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.Mech;
import units.Unit;

public class Stun extends ActiveAbility {
  private String name = "EMP shock";
  private String category = "offensive targeted stun";
  private int energyCost = 100;
  private int coolDown = 1000;
  private int stunnedForRounds = 0;
  private int range = 216;
  private int lastUsedInRound;
  private int canBeusedAgainInRound;
  private String key = "Q";

  public Stun(){
    super.setPosX(445);
    super.setPosY(675);
    try {
      super.setImage(ImageIO.read(new File("images/abilityicons/Stun.png")));
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
  public String getKey() {
    return key;
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

  public void setStunnedForRounds(int stunnedForRounds) {
    this.stunnedForRounds = stunnedForRounds;
  }

  @Override
  public void levelUpAbility(Mech mech) {
    super.setLevel(super.getLevel()+1);
    setCoolDown(getCoolDown() - super.getLevel() * 50);
    setEnergyCost(getEnergyCost() - super.getLevel() * 10);
    setStunnedForRounds(super.getLevel() * 100);
  }

  @Override
  public void useAbility(Mech mech, Unit unitTargeted, int roundCounter){
    mech.setEnergy(mech.getEnergy() - getEnergyCost());
    setCanBeUsedAgainInRound(roundCounter+getCoolDown());
    unitTargeted.setStunned(true);
    unitTargeted.setStunOverInRound(roundCounter + getStunnedForRounds());
  }
}
