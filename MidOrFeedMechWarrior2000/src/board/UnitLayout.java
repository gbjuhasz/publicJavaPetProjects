package board;

import java.util.ArrayList;
import units.*;

public class UnitLayout {

  private MechHero mechHero;
  private MechEnemy mechEnemy;
  private TurretAllied turretAllied;
  private TurretEnemy turretEnemy;
  private CreepAllied creepAllied1;
  private CreepAllied creepAllied2;
  private CreepAllied creepAllied3;
  private CreepEnemy creepEnemy1;
  private CreepEnemy creepEnemy2;
  private CreepEnemy creepEnemy3;

  public void placeUnitsOnMap() {
    mechHero = new MechHero("images/mechhero/MechHeroDownEven.png", 72, 0);
    mechEnemy = new MechEnemy("images/mechenemy/MechEnemyUp.png", 558, 648);
    turretAllied = new TurretAllied();
    turretEnemy = new TurretEnemy();
    creepAllied1 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 0, 72);
    creepAllied2 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 72, 72);
    creepAllied3 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 144, 72);
    creepEnemy1 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 486, 576);
    creepEnemy2 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 558, 576);
    creepEnemy3 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 630, 576);
  }

  public MechHero getMechHero() {
    return mechHero;
  }

  public MechEnemy getMechEnemy() {
    return mechEnemy;
  }

  public TurretAllied getTurretAllied() {
    return turretAllied;
  }

  public TurretEnemy getTurretEnemy() {
    return turretEnemy;
  }

  public ArrayList<CreepAllied> getListOfCreepAllied(){
    ArrayList<CreepAllied> listOfCreepAllied = new ArrayList<>();
    listOfCreepAllied.add(creepAllied1);
    listOfCreepAllied.add(creepAllied2);
    listOfCreepAllied.add(creepAllied3);
    return listOfCreepAllied;
  }

  public ArrayList<CreepEnemy> getListOfCreepEnemy(){
    ArrayList<CreepEnemy> listOfCreepEnemy = new ArrayList<>();
    listOfCreepEnemy.add(creepEnemy1);
    listOfCreepEnemy.add(creepEnemy2);
    listOfCreepEnemy.add(creepEnemy3);
    return listOfCreepEnemy;
  }
}
