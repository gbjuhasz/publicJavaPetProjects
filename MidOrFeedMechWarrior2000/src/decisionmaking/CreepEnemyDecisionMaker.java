package decisionmaking;

import botmovement.CreepEnemyMovementManager;
import java.util.List;
import units.Unit;

public class CreepEnemyDecisionMaker extends DecisionMaker {

  CreepEnemyMovementManager creepEnemyMovementManager = new CreepEnemyMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    List<Unit> listOfAllUnits,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    creepEnemyMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            listOfAllUnits,
            roundCounter);
  }
}
