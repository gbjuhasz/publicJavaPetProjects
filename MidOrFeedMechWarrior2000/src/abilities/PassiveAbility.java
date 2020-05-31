package abilities;

import units.Mech;
import units.PositionedImage;

public abstract class PassiveAbility extends PositionedImage {

  private int level;
  private String name;
  private String category;
  private boolean isTurnedOn = false;

  public int getLevel() {
    return level;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public boolean isTurnedOn() {
    return isTurnedOn;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTurnedOn(boolean turnedOn) {
    isTurnedOn = turnedOn;
  }

  public void levelUpAbility(Mech mech){}
}
