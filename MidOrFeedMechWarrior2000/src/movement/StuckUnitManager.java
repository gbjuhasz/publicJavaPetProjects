package movement;

import units.Unit;

public class StuckUnitManager {


  public void helpIfUnitIsStuck(Unit unit) {
    int x = unit.getPosX();
    int y = unit.getPosY();
    helpUnitAroundTopHalfOfRiver(x, y, unit);
    helpUnitAroundBuildingOnLeftSide(x, y, unit);
    helpUnitAroundBottomHalfOfTheRiver(x, y, unit);
  }


  private void helpUnitAroundTopHalfOfRiver(int x, int y, Unit unit) {
    if (x == 197 &&
            y >= 0 &&
            y < 180) {
      unit.setPosY(unit.getPosY() + 1);
    }

    if (x >= 197 && x <= 360 &&
            y == 180) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosX(unit.getPosX() + 1);
      }
    }

    if (x > 360 && x < 486 &&
            y <= 198) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosX(unit.getPosX() + 1);
        unit.setPosY(unit.getPosY() + 1);
      }
    }

    if (x == 486 && y <= 198) {
      unit.setPosY(unit.getPosY() + 1);
    }
  }

  private void helpUnitAroundBuildingOnLeftSide(int x, int y, Unit unit) {

    if (x < 145 && y == 270) {
      unit.setPosX(unit.getPosX() + 1);
    } else if (x < 145 && y == 450) {
      unit.setPosX(unit.getPosX() +1 );
    } else if (x == 144 && y > 270 && y < 450) {
      if(unit.getTargetDirection().contains("S")) {
        unit.setPosX(144);
        unit.setPosY(unit.getPosY() + 1);
      } else if (unit.getTargetDirection().contains("N")){
        unit.setPosX(144);
        unit.setPosY(unit.getPosY() - 1);
      }
    }
  }

  private void helpUnitAroundBottomHalfOfTheRiver(int x, int y, Unit unit) {

    if (x == 197 &&
            y >= 340 && y <= 486) {
      unit.setPosY(unit.getPosY() - 1);
    }
    if (x >= 180 && x <= 196 && y >= 485) {
      unit.setPosY(unit.getPosY() - 1);

    }
    if (x == 179 && y >= 486) {
      unit.setPosY(unit.getPosY() - 1);
    }

    if (x >= 198 && x <= 342 && y >= 340) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setPosY(unit.getPosY() - 1);

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosY(339);
      }
    }

    if (x > 342 && x <= 450 && y >= 360) {

      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setPosY(unit.getPosY() - 1);

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosY(unit.getPosY() - 1);
      }
    }
    if (x > 450 && x <= 486 && y >= 378) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setPosY(unit.getPosY() - 1);

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosX(unit.getPosX() + 1);
        unit.setPosY(unit.getPosY() - 1);
      }
    }
    if (x == 487 && y >= 378) {
      unit.setPosY(unit.getPosY() - 1);
    }
  }
}