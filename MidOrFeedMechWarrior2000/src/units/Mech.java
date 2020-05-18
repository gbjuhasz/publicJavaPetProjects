package units;

import java.util.Random;

public abstract class Mech extends Unit {
  //fields for fighting
  private int attackRange = 144;
  private int attackDamage = 25;
  private int healthPoints = 700;
  private int respawnHealthPoints = 700000000; //for testing
  private int armorRating = 0;
  private int missChance = 1;
  private int mechLevel = 1;
  private int roundsToRespawn = mechLevel * 5 ;
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

  public int getMechLevel() {
    return mechLevel;
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

  public void setMechLevel(int mechLevel) {
    this.mechLevel = mechLevel;
  }
}
