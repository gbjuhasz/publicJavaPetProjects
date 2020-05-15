package movement;

public class IllegalMoveCheckerMapObjects {

  public Boolean isMoveLegal(int x, int y) {

    return checkMapBorders(x, y); /*&&
            checkRiver(x, y) &&
            checkRiverUpperHalfRightSide(x,y) &&
            checkRiverLowerHalf(x, y) &&
            checkBuildingOnTheLeft(x, y) &&
            checkBuildingBottomRight(x, y) &&
            checkBuildingTopRight(x, y);*/
  }

  public Boolean checkMapBorders(int x, int y) {

    if (y > 648 || y < 0) {
      return false;
    } else return x <= 648 && x >= 0;
  }
/*
  public Boolean checkRiver(int x, int y) {

  }

  public Boolean checkRiverUpperHalfRightSide(int x, int y){

  }

  public Boolean checkRiverLowerHalf(int x, int y) {

  }

  public Boolean checkBuildingOnTheLeft(int x, int y) {

  }

  public Boolean checkBuildingBottomRight(int x, int y) {

  }

  public Boolean checkBuildingTopRight(int x, int y) {
*/
}
