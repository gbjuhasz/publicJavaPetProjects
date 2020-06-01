package abilities;

public abstract class Aura extends MechAbility {

  private int range;
  private int bonus;
  private boolean isTurnedOn;

  public boolean isTurnedOn() {
    return isTurnedOn;
  }

  public void setCategory(String category) {
  }

  public void setTurnedOn(boolean turnedOn) {
    isTurnedOn = turnedOn;
  }

}
