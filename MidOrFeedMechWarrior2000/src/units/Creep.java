package units;

import java.util.Random;

public abstract class Creep extends Unit {
  //fields for positioning on board, image picking and drawing effects
  private boolean isAlive;
  //fields for fighting
  private Random random = new Random();
  private int attackRange = 72;
  private int attackDamage = 5 * (1 + random.nextInt(5));
  private int healthPoints = 100;
  private int armorRating = 0;
  //fields for decision making
}
