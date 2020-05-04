package decisionmaking;

import movement.CreepAlliedMovementManager;
import movement.CreepEnemyMovementManager;
import units.Unit;

public class CreepEnemyDecisionMaker extends DecisionMaker {

  CreepEnemyMovementManager creepEnemyMovementManager = new CreepEnemyMovementManager();
  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    int roundCounter){
    unitMakingMove.calculateTargetDirection(unitTarget);
    creepEnemyMovementManager.moveUnitTowardsDestination(unitMakingMove,
            unitTarget,
            roundCounter);
  }
}
