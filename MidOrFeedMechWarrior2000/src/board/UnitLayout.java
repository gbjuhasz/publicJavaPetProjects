package board;

import units.MechHero;

public class UnitLayout {

  private MechHero mechHero;

  public void placeUnitsOnMap(){
    mechHero = new MechHero("/Users/BenceJuhasz/Desktop/publicJavaPetProjects/publicJavaPetProjects/MidOrFeedMechWarrior2000/images/mechHero/MechHeroDownEven.png", 360, 360);
  }

  public MechHero getMechHero() {
    return mechHero;
  }
}
