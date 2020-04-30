package board;

import units.MechHero;

public class UnitLayout {

  private MechHero mechHero;

  public void placeUnitsOnMap() {
    mechHero = new MechHero("images/mechhero/MechHeroDownEven.png", 0, 0);
  }

  public MechHero getMechHero() {
    return mechHero;
  }

}
