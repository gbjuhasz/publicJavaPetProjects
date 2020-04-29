package board;

import units.HeroMech;

public class UnitLayout {

  private HeroMech heroMech;

  public void placeUnitsOnMap(){
    heroMech = new HeroMech("/Users/BenceJuhasz/Desktop/publicJavaPetProjects/publicJavaPetProjects/MidOrFeedMechWarrior2000/images/mechHero/MechHeroDownEven.png", 360, 360);
  }

  public HeroMech getHeroMech() {
    return heroMech;
  }
}
