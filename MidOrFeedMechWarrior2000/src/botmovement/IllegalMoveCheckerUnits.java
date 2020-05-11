package botmovement;

import java.util.List;
import units.Unit;

public class IllegalMoveCheckerUnits {

  public boolean isMoveLegal(int posX, int posY, List<Unit> listOfAllUnits){
    for(Unit unit : listOfAllUnits){
      if (posX >= unit.getPosX() &&
      posX <= unit.getPosX() + 9 &&
      posY >= unit.getPosY() &&
      posY <= unit.getPosY() + 9){
        return false;
      }
    }
    return true;
  };
}
