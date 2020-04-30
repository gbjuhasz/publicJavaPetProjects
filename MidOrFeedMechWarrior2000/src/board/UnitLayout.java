package board;

import units.MechHero;
import units.TurretAllied;
import units.TurretEnemy;

public class UnitLayout {

  private MechHero mechHero;
  private TurretAllied turretAllied;
  private TurretEnemy turretEnemy;

  public void placeUnitsOnMap() {
    mechHero = new MechHero("images/mechhero/MechHeroDownEven.png", 0, 0);
    turretAllied = new TurretAllied();
    turretEnemy = new TurretEnemy();
  }

  public MechHero getMechHero() {
    return mechHero;
  }

  public TurretAllied getTurretAllied() {
    return turretAllied;
  }

  public TurretEnemy getTurretEnemy() {
    return turretEnemy;
  }
}
