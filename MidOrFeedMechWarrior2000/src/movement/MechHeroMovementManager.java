package movement;

import units.MechHero;

public class MechHeroMovementManager {

  public void moveMechHeroWithKeys(MechHero mechHero, int changeInX, int changeInY){
    int newX = mechHero.getPosX() + changeInX;
    int newY = mechHero.getPosY() + changeInY;
    mechHero.setPosX(newX);
    mechHero.setPosY(newY);
  }
}
