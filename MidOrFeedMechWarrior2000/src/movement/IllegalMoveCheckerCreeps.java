package movement;

import java.util.List;
import units.Unit;

public class IllegalMoveCheckerCreeps {

  public boolean isMoveLegalForCreeps(int posX, int posY, List<Unit> listOfAllUnitsToCheck) {
    for (Unit unit : listOfAllUnitsToCheck) {
      for (int i = 0; i < listOfAllUnitsToCheck.size(); i++) {
        if (unit.isAlive() &&
                posX >= unit.getPosX() &&
                posX <= unit.getPosX() + 36 &&
                posY >= unit.getPosY() &&
                posY <= unit.getPosY() + 36) {
          return false;
        }
      }
    }
    return true;
  }
}
