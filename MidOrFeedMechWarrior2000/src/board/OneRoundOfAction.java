package board;

import decisionmaking.*;
import java.util.ArrayList;
import java.util.List;
import units.*;
import visualeffects.LaserBlast;

public class OneRoundOfAction {

  CreepAlliedDecisionMaker creepAlliedDecisionMaker = new CreepAlliedDecisionMaker();
  CreepEnemyDecisionMaker creepEnemyDecisionMaker = new CreepEnemyDecisionMaker();
  MechEnemyDecisionMaker mechEnemyDecisionMaker = new MechEnemyDecisionMaker();
  TurretDecisionMaker turretDecisionMaker = new TurretDecisionMaker();
  MechHeroDecisionMaker mechHeroDecisionMaker = new MechHeroDecisionMaker();

  public void performOneRoundOfAction(MechHero mechHero,
                                      MechEnemy mechEnemy,
                                      ArrayList<Creep> listOfCreepAllied,
                                      ArrayList<Creep> listOfCreepEnemy,
                                      Turret turretAllied,
                                      Turret turretEnemy,
                                      List<Unit> listOfAllUnits,
                                      List<Mech> listOfMechs,
                                      int roundCounter) {

    if(mechHero.isAlive()){
      mechHeroDecisionMaker.reactToMouseCommand(mechHero,
              mechEnemy,
              listOfCreepEnemy,
              turretEnemy,
              listOfAllUnits,
              roundCounter);
    }

    for (Creep creepAllied : listOfCreepAllied) {
      if (creepAllied.isAlive()) {
        creepAlliedDecisionMaker.reactToPlayerMovement(creepAllied,
                mechEnemy,
                listOfCreepEnemy,
                turretEnemy,
                listOfAllUnits,
                listOfMechs,
                roundCounter);
      }
    }
    for (Creep creepEnemy : listOfCreepEnemy) {
      if (creepEnemy.isAlive()) {
        creepEnemyDecisionMaker.reactToPlayerMovement(creepEnemy,
                mechHero,
                listOfCreepAllied,
                turretAllied,
                listOfAllUnits,
                listOfMechs,
                roundCounter);
      }
    }
    if (mechEnemy.isAlive()) {
      mechEnemyDecisionMaker.reactToPlayerMovement(mechEnemy,
              mechHero,
              listOfCreepAllied,
              turretAllied,
              listOfAllUnits,
              listOfMechs,
              roundCounter);
    }
    turretDecisionMaker.reactToPlayerMovement(turretAllied,
            mechEnemy,
            listOfCreepEnemy,
            turretEnemy,
            listOfAllUnits,
            listOfMechs,
            roundCounter);

    turretDecisionMaker.reactToPlayerMovement(turretEnemy,
            mechHero,
            listOfCreepAllied,
            turretAllied,
            listOfAllUnits,
            listOfMechs,
            roundCounter);
  }
}
