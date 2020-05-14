package decisionmaking;

import movement.CreepAlliedMovementManager;
import java.util.List;
import units.Unit;

public class CreepAlliedDecisionMaker extends DecisionMaker {

  CreepAlliedMovementManager creepAlliedMovementManager = new CreepAlliedMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    List<Unit> listOfAllUnits,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    creepAlliedMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            listOfAllUnits,
            roundCounter);
  }
}
