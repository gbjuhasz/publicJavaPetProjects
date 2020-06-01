package abilities;

import units.Mech;

public abstract class PassiveAbility extends MechAbility {

  private boolean isTurnedOn = false;

  public boolean isTurnedOn() {
    return isTurnedOn;
  }

  public void setTurnedOn(boolean turnedOn) {
    isTurnedOn = turnedOn;
  }

  public void levelUpAbility(Mech mech) {
  }
}
