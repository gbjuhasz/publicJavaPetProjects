package abilities;

import units.Mech;
import units.PositionedImage;

public abstract class Aura extends PositionedImage {

  private int level;
  private String name;
  private String category;
  private int range;
  private int bonus;
  private boolean isTurnedOn;


  public String getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isTurnedOn() {
    return isTurnedOn;
  }

  public void setCategory(String category) {
  }

  public void setTurnedOn(boolean turnedOn) {
    isTurnedOn = turnedOn;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void levelUpAbility(Mech mech) {
    setLevel(getLevel()+1);
  }

}
