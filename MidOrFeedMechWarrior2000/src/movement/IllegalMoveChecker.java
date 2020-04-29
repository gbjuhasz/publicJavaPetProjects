package movement;

import units.Unit;

public class IllegalMoveChecker {

  public Boolean isMoveLegal(int x, int y){
    if( checkMapBorders(x,y) == false ||
    checkRiverUpperHalf(x,y) == false){
      return false;
    }
    return true;
  }

  public Boolean checkMapBorders(int x, int y){

    if(y > 648 || y < 0){
      return false;
    } else if ( x > 648 || x < 0){
      return false;
    } else {
      return true;
    }
  }

  public Boolean checkRiverUpperHalf(int x, int y){
    if ( x > 216 && x <= 360) {
      if(y < 180){
        return false;
      }
    } else if ( x > 360 && x < 486){
      if(y < 198){
        return false;
      }
    }
    return true;
  }
}
