package decisionmaking;

import movement.MechEnemyMovementManager;
import java.util.List;
import units.Unit;

public class MechEnemyDecisionMaker extends DecisionMaker {

  MechEnemyMovementManager mechEnemyMovementManager = new MechEnemyMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    List<Unit> listOfAllUnits,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    mechEnemyMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            listOfAllUnits,
            roundCounter);
  }
}
