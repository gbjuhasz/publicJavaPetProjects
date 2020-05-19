package decisionmaking;

import board.BoardComponent;
import movement.MechEnemyMovementManager;
import java.util.List;
import units.Unit;

public class MechEnemyDecisionMaker extends DecisionMaker {

  MechEnemyMovementManager mechEnemyMovementManager = new MechEnemyMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    BoardComponent boardComponent,
                                    List<Unit> listOfAllUnits,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(boardComponent);
    mechEnemyMovementManager.moveUnit(unitMakingMove,
            boardComponent,
            listOfAllUnits,
            roundCounter);
  }
}
