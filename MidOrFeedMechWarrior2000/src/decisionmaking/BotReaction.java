package decisionmaking;

import java.util.ArrayList;
import units.Creep;
import units.Mech;
import units.Turret;

public class BotReaction {

  CreepAlliedDecisionMaker creepAlliedDecisionMaker = new CreepAlliedDecisionMaker();
  CreepEnemyDecisionMaker creepEnemyDecisionMaker = new CreepEnemyDecisionMaker();
  MechEnemyDecisionMaker mechEnemyDecisionMaker = new MechEnemyDecisionMaker();

  public void makeBotsReactToPlayerAction(Mech mechHero,
                                          Mech mechEnemy,
                                          ArrayList<Creep> listOfCreepAllied,
                                          ArrayList<Creep> listOfCreepEnemy,
                                          Turret turretAllied,
                                          Turret turretEnemy,
                                          int roundCounter) {
    for (Creep creepAllied : listOfCreepAllied) {
      if (creepAllied.isAlive()) {
        creepAlliedDecisionMaker.reactToPlayerMovement(creepAllied,
                mechEnemy,
                listOfCreepEnemy,
                turretEnemy,
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
                roundCounter);
      }
    }
    if (mechEnemy.isAlive()) {
      mechEnemyDecisionMaker.reactToPlayerMovement(mechEnemy,
              mechHero,
              listOfCreepAllied,
              turretAllied,
              roundCounter);
    }
  }
}
