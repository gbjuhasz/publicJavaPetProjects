package botmovement;

public class IllegalMoveChecker {

  public Boolean isMoveLegal(int x, int y) {

    return checkMapBorders(x, y) &&
            checkRiverUpperHalf(x, y) &&
            checkRiverLowerHalf(x, y) &&
            checkBuildingOnTheLeft(x, y) &&
            checkBuildingBottomRight(x, y) &&
            checkBuildingTopRight(x, y);
  }

  public Boolean checkMapBorders(int x, int y) {

    if (y > 648 || y < 0) {
      return false;
    } else return x <= 648 && x >= 0;
  }

  public Boolean checkRiverUpperHalf(int x, int y) {

    if (x > 216 && x <= 360) {
      return y >= 180;
    } else if (x > 360 && x < 486) {
      return y >= 198;
    }
    return true;
  }

  public Boolean checkRiverLowerHalf(int x, int y) {

    if (x >= 450 && x <= 468) {
      return y <= 378;
    } else if (x >= 342 && x <= 432) {
      return y <= 360;
    } else if (x > 198 && x < 360) {
      return y <= 342 || y >= 486;
    } else if (x == 198) {
      return y <= 468;
    } else if (x == 180) {
      return y <= 480;
    }
    return true;
  }

  public Boolean checkBuildingOnTheLeft(int x, int y) {

    if (x > 54 && x < 144) {
      return y <= 270 || y >= 450;
    } else if (x <= 54) {
      return y <= 252 || y >= 468;
    }
    return true;
  }

  public Boolean checkBuildingBottomRight(int x, int y) {

    if (x > 594) {
      return y <= 414 || y >= 612;
    }
    return true;
  }

  public Boolean checkBuildingTopRight(int x, int y) {

    if (x > 612) {
      return y >= 270 || y <= 72;
    } else if (x == 594 || x == 612) {
      return y > 72;
    }
    return true;
  }
}
