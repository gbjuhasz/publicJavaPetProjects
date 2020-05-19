package board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import units.*;

public class UnitLayout {

  private MechHero mechHero;
  private MechEnemy mechEnemy;
  private TurretAllied turretAllied;
  private TurretEnemy turretEnemy;
  private CreepAllied creepAllied1;
  private CreepAllied creepAllied2;
  private CreepAllied creepAllied3;
  private CreepAllied creepAllied4;
  private CreepAllied creepAllied5;
  private CreepAllied creepAllied6;
  private CreepEnemy creepEnemy1;
  private CreepEnemy creepEnemy2;
  private CreepEnemy creepEnemy3;
  private CreepEnemy creepEnemy4;
  private CreepEnemy creepEnemy5;
  private CreepEnemy creepEnemy6;
  WaypointLayout waypointLayout = new WaypointLayout();

  public void placeUnitsOnMap() {
    mechHero = new MechHero("images/mechhero/MechHeroDownEven.png", 72, 0);
    mechEnemy = new MechEnemy("images/mechenemy/MechEnemyUp.png", 558, 648);
    turretAllied = new TurretAllied();
    turretEnemy = new TurretEnemy();
    creepAllied1 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 0, 90, true, 500, waypointLayout.getWaypointListBottomAllied());
    creepAllied2 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 90, 45, true, 500, waypointLayout.getWaypointListMidAllied());
    creepAllied3 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 180, 0, true, 500, waypointLayout.getWaypointListTopAllied());
    creepAllied4 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 0, 90, false, 500, waypointLayout.getWaypointListBottomAllied());
    creepAllied5 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 90, 45, false, 500, waypointLayout.getWaypointListMidAllied());
    creepAllied6 = new CreepAllied("images/creepallied/CreepAlliedDownEven.png", 180, 0, false, 500, waypointLayout.getWaypointListTopAllied());
    creepEnemy1 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 500, 575, true, 500, waypointLayout.getWaypointListBottomEnemy());
    creepEnemy2 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 550, 550, true, 500, waypointLayout.getWaypointListMidEnemy());
    creepEnemy3 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 600, 500, true, 500, waypointLayout.getWaypointListTopEnemy());
    creepEnemy4 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 500, 575, false, 500, waypointLayout.getWaypointListBottomEnemy());
    creepEnemy5 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 550, 550, false, 500, waypointLayout.getWaypointListMidEnemy());
    creepEnemy6 = new CreepEnemy("images/creepenemy/CreepEnemyUpEven.png", 600, 500, false, 500, waypointLayout.getWaypointListTopEnemy());
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

  public ArrayList<Creep> getListOfCreepAllied() {
    ArrayList<Creep> listOfCreepAllied = new ArrayList<>();
    listOfCreepAllied.add(creepAllied1);
    listOfCreepAllied.add(creepAllied2);
    listOfCreepAllied.add(creepAllied3);
    listOfCreepAllied.add(creepAllied4);
    listOfCreepAllied.add(creepAllied5);
    listOfCreepAllied.add(creepAllied6);
    return listOfCreepAllied;
  }

  public ArrayList<Creep> getListOfCreepEnemy() {
    ArrayList<Creep> listOfCreepEnemy = new ArrayList<>();
    listOfCreepEnemy.add(creepEnemy1);
    listOfCreepEnemy.add(creepEnemy2);
    listOfCreepEnemy.add(creepEnemy3);
    listOfCreepEnemy.add(creepEnemy4);
    listOfCreepEnemy.add(creepEnemy5);
    listOfCreepEnemy.add(creepEnemy6);
    return listOfCreepEnemy;
  }

  public ArrayList<Mech> getListOfMechs() {
    ArrayList<Mech> listOfMechs = new ArrayList<>();
    listOfMechs.add(mechHero);
    listOfMechs.add(mechEnemy);
    return listOfMechs;
  }

  public List<Unit> getListOfEnemyUnits() {
    List<Unit> listOfEnemy = new ArrayList<>();
    listOfEnemy.add(mechEnemy);
    listOfEnemy.addAll(getListOfCreepEnemy());
    listOfEnemy.add(turretEnemy);
    return listOfEnemy;
  }

  public List<Unit> getAllUnits() {
    List<Unit> listOfAllUnits = new ArrayList<>();
    listOfAllUnits.addAll(getListOfCreepEnemy());
    listOfAllUnits.addAll(getListOfCreepAllied());
    listOfAllUnits.add(mechEnemy);
    listOfAllUnits.add(mechHero);
    return listOfAllUnits;
  }

  public List<Unit> getAllUnitsOrderedByYCoordinate() {
    List<Unit> listOfAllUnits = getAllUnits();
    List<Unit> listOfAllUnitsOrderedByYCoordinate = new ArrayList<>();
    List<Integer> listOfYCoordinates = new ArrayList<>();
    for (Unit unit : listOfAllUnits) {
      listOfYCoordinates.add(unit.getPosY());
    }
    Collections.sort(listOfYCoordinates);
    for (int i = 0; i < listOfYCoordinates.size(); i++) {
      for (int j = 0; j < listOfAllUnits.size(); j++) {
        if (listOfAllUnits.get(j).getPosY() == listOfYCoordinates.get(i)) {
          listOfAllUnitsOrderedByYCoordinate.add(listOfAllUnits.get(j));
          listOfAllUnits.remove(j);
        }
      }
    }
    return listOfAllUnitsOrderedByYCoordinate;
  }
}
