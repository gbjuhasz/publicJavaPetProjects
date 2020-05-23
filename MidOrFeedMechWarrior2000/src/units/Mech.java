package units;

import abilities.Ability;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Mech extends Unit {
  //fields for fighting
  private int attackRange = 144;
  private int attackDamage = 50 + (super.getLevel() * 5);
  private int healthPoints = 750 + (super.getLevel() * 50);
  private int respawnHealthPoints = 750 + (super.getLevel() * 50);
  private int energy = 300 + (super.getLevel() * 50);
  private int respawnEnergy = 300 + (super.getLevel() * 50);
  private int armorRating = 0 + super.getLevel();
  private int missChance = 1;
  private int roundsToRespawn = super.getLevel() * 5 ;
  //fields for levelling
  private HashMap<Integer, Integer> xpBounty = new HashMap() {{
    put(1, 70);
    put(2, 118);
    put(3, 180);
    put(4, 255);
    put(5, 333);
    put(6, 427);
  }};
  //fields for decision making

  @Override
  public int getArmorRating() {
    return armorRating;
  }

  @Override
  public int getHealthPoints() {
    return healthPoints;
  }

  @Override
  public int getAttackDamage() {
    return attackDamage;
  }

  @Override
  public int getAttackRange() {
    return attackRange;
  }


  @Override
  public int getMissChance() {
    return  missChance;
  }

  @Override
  public int getRespawnHealthPoints() {
    return respawnHealthPoints;
  }

  public int getRoundsToRespawn() {
    return roundsToRespawn;
  }

  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  @Override
  public HashMap<Integer, Integer> getXpBounty() {
    return xpBounty;
  }

  @Override
  public int getRespawnEnergy() {
    return respawnEnergy;
  }

  @Override
  public int getEnergy() {
    return energy;
  }

  @Override
  public void setEnergy(int energy) {
    this.energy = energy;
  }

  @Override
  public void setRespawnEnergy(int respawnEnergy) {
    this.respawnEnergy = respawnEnergy;
  }
}
