package units;

import java.util.Random;

public abstract class Mech extends Unit {
  //fields for positioning on board, image picking and drawing effects
  private boolean isAlive;
  //fields for fighting
  private Random random = new Random();
  private int attackRange = 72;
  private int attackDamage = 10 * (1 + random.nextInt(5));
  private int healthPoints = 500;
  private int armorRating = 0;
  //fields for decision making
}
