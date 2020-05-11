package decisionmaking;

import botmovement.MechEnemyMovementManager;
import units.Unit;

public class MechEnemyDecisionMaker extends DecisionMaker {

  MechEnemyMovementManager mechEnemyMovementManager = new MechEnemyMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    mechEnemyMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            roundCounter);
  }
}
