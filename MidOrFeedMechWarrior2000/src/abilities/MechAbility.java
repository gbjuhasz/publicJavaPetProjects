package abilities;

import board.BoardComponent;
import units.Mech;

public abstract class MechAbility extends BoardComponent {
  private int level;
  private String name;
  private String category;

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public void levelUpAbility(Mech mech){}
}
