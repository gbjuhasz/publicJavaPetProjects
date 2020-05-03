package movement;

import units.Turret;
import units.Unit;

public abstract class BotMovementManager extends MovementManager {

  public void moveCreepTowardsDestination(Unit unitMakingMove, Unit unitDestination){

    IllegalMoveChecker illegalMoveChecker = new IllegalMoveChecker();
    int currentX = unitMakingMove.getPosX();
    int currentY = unitMakingMove.getPosY();
    unitMakingMove.calculateTargetDirection(unitDestination);
    String targetDirection = unitMakingMove.getTargetDirection();

    makeMoveBasedOnTargetDirection(unitMakingMove, currentX, currentY, targetDirection);

  }

  public void makeMoveBasedOnTargetDirection(Unit unitMakingMove,
                                             int currentX,
                                             int currentY,
                                             String targetDirection) {

    if(targetDirection.contains("N")){
      unitMakingMove.setPosY(currentY - 16);
    } else if( targetDirection.contains("S")){
      unitMakingMove.setPosY(currentY + 16);
    }

    if(targetDirection.contains("W")){
      unitMakingMove.setPosX(currentX - 16);
    } else if( targetDirection.contains("E")){
      unitMakingMove.setPosX(currentX + 16);
    }
  }

  @Override
  public void setFacingDirection(Unit unit, int changeInX, int changeInY) {

    if (changeInX == 0 && changeInY == -18) {
      unit.setFacingDirection("Up");
    } else if (changeInX == 0 && changeInY == 18) {
      unit.setFacingDirection("Down");
    } else if (changeInX == -18 && changeInY == 0) {
      unit.setFacingDirection("Left");
    } else {
      unit.setFacingDirection("Right");
    }
  }

}
