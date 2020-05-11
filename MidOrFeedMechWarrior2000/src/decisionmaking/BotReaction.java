package decisionmaking;

import java.util.ArrayList;
import java.util.List;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public class BotReaction {

  CreepAlliedDecisionMaker creepAlliedDecisionMaker = new CreepAlliedDecisionMaker();
  CreepEnemyDecisionMaker creepEnemyDecisionMaker = new CreepEnemyDecisionMaker();
  MechEnemyDecisionMaker mechEnemyDecisionMaker = new MechEnemyDecisionMaker();
  TurretDecisionMaker turretDecisionMaker = new TurretDecisionMaker();

  public void makeBotsReactToPlayerAction(Mech mechHero,
                                          Mech mechEnemy,
                                          ArrayList<Creep> listOfCreepAllied,
                                          ArrayList<Creep> listOfCreepEnemy,
                                          Turret turretAllied,
                                          Turret turretEnemy,
                                          List<Unit> listOfAllUnits,
                                          int roundCounter) {

    for (Creep creepAllied : listOfCreepAllied) {
      if (creepAllied.isAlive()) {
        creepAlliedDecisionMaker.reactToPlayerMovement(creepAllied,
                mechEnemy,
                listOfCreepEnemy,
                turretEnemy,
                listOfAllUnits,
                roundCounter
        );
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
