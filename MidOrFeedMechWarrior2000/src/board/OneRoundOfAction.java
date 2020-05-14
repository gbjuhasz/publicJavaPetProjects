package board;

import decisionmaking.*;
import java.util.ArrayList;
import java.util.List;
import units.*;

public class OneRoundOfAction {

  CreepAlliedDecisionMaker creepAlliedDecisionMaker = new CreepAlliedDecisionMaker();
  CreepEnemyDecisionMaker creepEnemyDecisionMaker = new CreepEnemyDecisionMaker();
  MechEnemyDecisionMaker mechEnemyDecisionMaker = new MechEnemyDecisionMaker();
  TurretDecisionMaker turretDecisionMaker = new TurretDecisionMaker();
  MechHeroDecisionMaker mechHeroDecisionMaker = new MechHeroDecisionMaker();

  public void performOneRoundOfAction(MechHero mechHero,
                                      Mech mechEnemy,
                                      ArrayList<Creep> listOfCreepAllied,
                                      ArrayList<Creep> listOfCreepEnemy,
                                      Turret turretAllied,
                                      Turret turretEnemy,
                                      List<Unit> listOfAllUnits,
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
                roundCounter);
      }
    }
    if (mechEnemy.isAlive()) {
      mechEnemyDecisionMaker.reactToPlayerMovement(mechEnemy,
              mechHero,
              listOfCreepAllied,
              turretAllied,
              listOfAllUnits,
              roundCounter);
    }
    turretDecisionMaker.reactToPlayerMovement(turretAllied,
            mechEnemy,
            listOfCreepEnemy,
            turretEnemy,
            listOfAllUnits,
            roundCounter);

    turretDecisionMaker.reactToPlayerMovement(turretEnemy,
            mechHero,
            listOfCreepAllied,
            turretAllied,
            listOfAllUnits,
            roundCounter);
  }
}
