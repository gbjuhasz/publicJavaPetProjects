package units;

import java.util.Random;

public abstract class Creep extends Unit {
  //fields for fighting
  private int attackRange = 72;
  private int attackDamage = 25;
  private int healthPoints = 550;
  private int respawnHealthPoints = 550;
  private int armorRating = 0;
  private int detectionRange = 216;
  private int missChance = 2;
  private int roundsToRespawn = 50;
  //fields for decision making

  @Override
  public int getDetectionRange() {
    return detectionRange;
  }

  @Override
  public int getAttackRange() {
    return attackRange;
  }

  @Override
  public int getAttackDamage() {
    return attackDamage;
  }

  @Override
  public int getHealthPoints() {
    return healthPoints;
  }

  @Override
  public int getArmorRating() {
    return armorRating;
  }

  @Override
  public int getMissChance() {
    return missChance;
  }

  @Override
  public int getRoundsToRespawn() {
    return roundsToRespawn;
  }

  @Override
  public int getRespawnHealthPoints() {
    return respawnHealthPoints;
  }

  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }
}
