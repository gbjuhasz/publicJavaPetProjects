package movement;

import units.Unit;

public class StuckUnitManager {

  IllegalMoveCheckerMapObjects illegalMoveCheckerMapObjects = new IllegalMoveCheckerMapObjects();

  public void helpIfUnitIsStuck(Unit unit) {
    int x = unit.getPosX();
    int y = unit.getPosY();
    helpUnitAroundTopHalf(x, y, unit);
    helpUnitAroundBuildingOntheLeftSide(x, y, unit);
    helpUnitAroundBottomHalfOfTheRiver(x, y, unit);
  }


  //Help unit around river top half
  private void helpUnitAroundTopHalf(int x, int y, Unit unit) {
    if (x == 197 &&
            y >= 0 &&
            y < 180) {
      unit.setPosY(unit.getPosY() + 1);
      unit.setFacingDirection("DOWN");
    }

    if (x >= 197 && x <= 360 &&
            y == 180) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setFacingDirection("LEFT");

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosX(unit.getPosX() + 1);
        unit.setFacingDirection("RIGHT");
      }
    }

    if (x > 360 && x < 486 &&
            y <= 198) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setFacingDirection("LEFT");

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosX(unit.getPosX() + 1);
        unit.setPosY(unit.getPosY() + 1);
        unit.setFacingDirection("RIGHT");
      }
    }

    if (x == 486 && y <= 198) {
      unit.setPosY(unit.getPosY() + 1);
      unit.setFacingDirection("DOWN");
    }
  }
  //Help unit around building on the left side

  private void helpUnitAroundBuildingOntheLeftSide(int x, int y, Unit unit) {
    if (x < 145 && y >= 290) {
      unit.setPosX(unit.getPosX() + 1);
      if (y == 290) {
        unit.setFacingDirection("RIGHT");
      } else if (x == 144) {
        if(unit.getTargetDirection().contains("S")) {
          unit.setFacingDirection("DOWN");
        } else {
          unit.setFacingDirection("UP");
        }
      } else if (y == 450) {
        unit.setFacingDirection("RIGHT");
      }
    }
  }

  //Help unit around bottom half of the river
  private void helpUnitAroundBottomHalfOfTheRiver(int x, int y, Unit unit) {

    if (x == 197 &&
            y >= 340 && y <= 486) {
      unit.setPosY(unit.getPosY() - 1);
      unit.setFacingDirection("UP");
    }
    if (x >= 180 && x <= 196 && y >= 485) {
      unit.setPosY(unit.getPosY() - 1);
      unit.setFacingDirection("UP");

    }
    if (x == 179 && y >= 486) {
      unit.setPosY(unit.getPosY() - 1);
      unit.setFacingDirection("UP");
    }

    if (x >= 198 && x <= 342 && y >= 340) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setPosY(unit.getPosY() - 1);
        unit.setFacingDirection("LEFT");

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosY(unit.getPosY() - 1);
        unit.setFacingDirection("RIGHT");
      }
    }

    if (x > 342 && x <= 450 && y >= 360) {

      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setPosY(unit.getPosY() - 1);
        unit.setFacingDirection("LEFT");

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosY(unit.getPosY() - 1);
        unit.setFacingDirection("RIGHT");
      }
    }
    if (x > 450 && x <= 486 && y >= 378) {
      if (unit.getTargetDirection().contains("W")) {
        unit.setPosX(unit.getPosX() - 1);
        unit.setPosY(unit.getPosY() - 1);
        unit.setFacingDirection("LEFT");

      } else if (unit.getTargetDirection().contains("E")) {
        unit.setPosX(unit.getPosX() + 1);
        unit.setPosY(unit.getPosY() + 1);
        unit.setFacingDirection("RIGHT");
      }
    }
    if (x == 487 && y >= 378) {
      unit.setPosY(unit.getPosY() - 1);
      unit.setFacingDirection("UP");

    }
  }
}

/*
    public Boolean checkRiverLowerHalfLeftSideAndMid(int x, int y) {
      if (x >= 198 && x < 342 &&
              y > 342 && y < 486) {
        return false;
      }
      if (x == 180 && y > 486) {
        return false;
      }
      return true;
    }

    public Boolean checkRiverLowerHalfRightSideAndMid(int x, int y) {
      if (x > 342 && x < 450 && y > 360) {
        return false;
      }
      if (x >= 450 && x < 486 && y > 378) {
        return false;
      }
      if (x == 486 && y > 378) {
        return false;
      }
      return true;
    }

    public Boolean checkBuildingOnTheLeft(int x, int y) {
      if (x < 144 && y > 270 && y < 450) {
        return false;
      }
      return true;
    }

    public Boolean checkBuildingBottomRight(int x, int y) {
      if(x >= 600 && y > 414 && y < 636) {
        return false;
      }
      return true;
    }

    public Boolean checkBuildingTopRight(int x, int y) {
      if(x > 604 && y < 355){
        return false;
      }
      if(x > 582 && y < 64) {
        return false;
      }
      return true;
    }
  }
   */