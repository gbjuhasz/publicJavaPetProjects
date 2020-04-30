package movement;

public class IllegalMoveChecker {

  public Boolean isMoveLegal(int x, int y) {

    if (!checkMapBorders(x, y) ||
            !checkRiverUpperHalf(x, y) ||
            !checkRiverLowerHalf(x, y) ||
            !checkBuildingOnTheLeft(x, y)) {
      return false;
    }
    return true;
  }

  public Boolean checkMapBorders(int x, int y) {

    if (y > 648 || y < 0) {
      return false;
    } else if (x > 648 || x < 0) {
      return false;
    } else {
      return true;
    }
  }

  public Boolean checkRiverUpperHalf(int x, int y) {

    if (x > 216 && x <= 360) {
      if (y < 180) {
        return false;
      }
    } else if (x > 360 && x < 486) {
      if (y < 198) {
        return false;
      }
    }
    return true;
  }

  public Boolean checkRiverLowerHalf(int x, int y) {

    if (x >= 450 && x <= 468) {
      if (y > 378) {
        return false;
      }
    } else if (x >= 342 && x <= 432) {
      if (y > 360) {
        return false;
      }
    } else if (x > 198 && x < 360) {
      if (y > 342 && y < 486) {
        return false;
      }
    } else if (x == 198) {
      if (y > 468) {
        return false;
      }
    } else if (x == 180) {
      if (y > 480) {
        return false;
      }
    }
    return true;
  }

  public Boolean checkBuildingOnTheLeft(int x, int y) {

    if (x > 54 && x < 144) {
      if (y > 216 && y < 450) {
        return false;
      }
    } else if (x <= 54) {
      if (y > 198 && y < 468) {
        return false;
      }
    }
    return true;
  }
}
