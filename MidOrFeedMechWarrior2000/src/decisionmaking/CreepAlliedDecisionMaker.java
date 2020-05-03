package decisionmaking;

import movement.CreepAlliedMovementManager;
import units.Unit;

public class CreepAlliedDecisionMaker extends DecisionMaker {

  CreepAlliedMovementManager creepAlliedMovementManager = new CreepAlliedMovementManager();

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove, Unit unitTarget){
    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    unitMakingMove.calculateTargetDirection(unitTarget);
    String targetDirection = unitMakingMove.getTargetDirection();

    creepAlliedMovementManager.makeMoveBasedOnTargetDirection(unitMakingMove,
            currentX,
            currentY,
            targetDirection);
  }
}
