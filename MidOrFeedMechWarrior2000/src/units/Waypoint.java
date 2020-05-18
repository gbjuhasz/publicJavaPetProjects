package units;

public class Waypoint extends Unit {

  public Waypoint(){}

  public Waypoint(int posX, int posY){
    super.setPosX(posX);
    super.setPosY(posY);
    super.setUnitType("waypoint");
  }
}
