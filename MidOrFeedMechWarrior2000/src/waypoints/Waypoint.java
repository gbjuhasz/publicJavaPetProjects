package waypoints;

import board.BoardComponent;

public class Waypoint extends BoardComponent {

  public Waypoint() {
  }

  public Waypoint(int posX, int posY) {
    super.setPosX(posX);
    super.setPosY(posY);
    super.setUnitType("waypoint");
  }
}
