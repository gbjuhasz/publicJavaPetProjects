package movement;

import java.util.List;
import units.Unit;

public class IllegalMoveCheckerUnits {

  public boolean isMoveLegal(int posX, int posY, List<Unit> listOfAllUnits){
    for(Unit unit : listOfAllUnits){
      if (posX >= unit.getPosX() &&
      posX <= unit.getPosX() + 36 &&
      posY >= unit.getPosY() &&
      posY <= unit.getPosY() + 36){
        return false;
      }
    }
    return true;
  };
}
