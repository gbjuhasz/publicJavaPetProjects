package units;

import java.util.Random;

public abstract class Turret extends Unit {
  //fields for fighting

  private int healthPoints = 1800;
  private int armorRating = 12;
  private int attackRange = 90;
  private int attackDamage = 110;

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
  public int getDetectionRange() {
    return super.getDetectionRange();
  }
}
