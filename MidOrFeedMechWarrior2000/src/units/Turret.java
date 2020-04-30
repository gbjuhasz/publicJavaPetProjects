package units;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Turret extends Unit {
  //fields for fighting
  private Random random = new Random();
  private int healthPoints = 1000;
  private int armorRating = 10;
  private int attackRange = 60;
  private int attackDamage = 5 * (1 + random.nextInt(10));
}
