package movement;

public class IllegalMoveCheckerMapObjects {

  public Boolean isMoveLegal(int x, int y) {

    return checkMapBorders(x, y) &&
            checkRiverUpperHalfLeftSide(x, y) &&
            checkRiverUpperHalfBottomAndRightSide(x, y) &&
            checkRiverLowerHalf(x, y) &&
            checkBuildingOnTheLeft(x, y) &&
            checkRiverLowerHalfRightSideAndMid(x, y) &&
            checkBuildingBottomRight(x, y) &&
            checkBuildingTopRight(x, y);
  }

  //private innentol least visibility!!!!
  public Boolean checkMapBorders(int x, int y) {

    if (y > 648 || y < 0) {
      return false;
    }
    return x <= 648 && x >= 0;
  }

  public Boolean checkRiverUpperHalfLeftSide(int x, int y) {
    if (x == 198 &&
            y >= 0 &&
            y <= 180) {
      return false;
    }
    return true;
  }

  public Boolean checkRiverUpperHalfBottomAndRightSide(int x, int y) {
    if (x > 198 && x <= 360 &&
            y < 180) {
      return false;
    }
    if (x > 360 && x < 486 &&
            y < 198) {
      return false;
    }
    return true;
  }


  public Boolean checkRiverLowerHalf(int x, int y) {
    if (x >= 180 && x <= 486 && y > 486) {
      return false;
    }
    if (x >= 198 && x < 342 && y > 340 && y <= 486) {
      return false;
    }
    if (x >= 342 && x < 450 && y > 360) {
      return false;
    }
    if (x >= 450 && x <= 486 && y >= 378) {
      return false;
    }
    return true;
  }

  public Boolean checkRiverLowerHalfRightSideAndMid(int x, int y) {
 /*   if (x > 342 && x < 450 && y > 360) {
      return false;
    }
    if (x >= 450 && x < 486 && y > 378) {
      return false;
    }
    if (x == 486 && y > 378) {
      return false;
    }*/
    return true;
  }

  public Boolean checkBuildingOnTheLeft(int x, int y) {
    if (x < 144 && y > 290 && y < 450) {
      return false;
    }
    return true;
  }

  public Boolean checkBuildingBottomRight(int x, int y) {
    if (x >= 600 && y > 414 && y < 636) {
      return false;
    }
    return true;
  }

  public Boolean checkBuildingTopRight(int x, int y) {
    if (x > 604 && y < 355) {
      return false;
    }
    if (x > 582 && y < 64) {
      return false;
    }
    return true;
  }
}