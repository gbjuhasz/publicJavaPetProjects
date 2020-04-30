package board;

import units.MechHero;

public class UnitLayout {

  private MechHero mechHero;

  public void placeUnitsOnMap() {
    mechHero = new MechHero("/Users/BenceJuhasz/Desktop/publicJavaPetProjects/publicJavaPetProjects/MidOrFeedMechWarrior2000/images/mechHero/MechHeroDownEven.png", 0, 0);
  }

  public MechHero getMechHero() {
    return mechHero;
  }

}
