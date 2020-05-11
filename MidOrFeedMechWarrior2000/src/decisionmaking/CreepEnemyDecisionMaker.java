package decisionmaking;

import botmovement.CreepEnemyMovementManager;
import units.Unit;

public class CreepEnemyDecisionMaker extends DecisionMaker {

  CreepEnemyMovementManager creepEnemyMovementManager = new CreepEnemyMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    creepEnemyMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            roundCounter);
  }
}
