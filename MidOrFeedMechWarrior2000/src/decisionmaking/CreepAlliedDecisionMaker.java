package decisionmaking;

import movement.CreepAlliedMovementManager;
import units.Unit;

public class CreepAlliedDecisionMaker extends DecisionMaker {

  CreepAlliedMovementManager creepAlliedMovementManager = new CreepAlliedMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    creepAlliedMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            roundCounter);
  }
}
