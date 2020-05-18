package board;

import java.util.ArrayList;
import java.util.List;
import units.Waypoint;

public class WaypointLayout {

  Waypoint waypoint0mid = new Waypoint(90, 230);
  Waypoint waypoint0bottom = new Waypoint(0, 265);
  Waypoint waypoint1top = new Waypoint(234,180);
  Waypoint waypoint1mid = new Waypoint(234, 220);
  Waypoint waypoint1bottom = new Waypoint(220, 340);
  Waypoint waypoint2top = new Waypoint(600, 360);
  Waypoint waypoint2mid = new Waypoint(330, 380);
  Waypoint waypoint2bottom = new Waypoint(500, 395);
  Waypoint waypointTurretAllied = new Waypoint(80,150);
  Waypoint waypointTurretEnemy = new Waypoint(580,280);

  public WaypointLayout (){
  }

  public List<Waypoint> getWaypointListTopAllied(){
    List<Waypoint> waypointsTop = new ArrayList<>();
    waypointsTop.add(waypoint1top);
    waypointsTop.add(waypoint2top);
    waypointsTop.add(waypointTurretEnemy);
    return waypointsTop;
  }

  public List<Waypoint> getWaypointListTopEnemy(){
    List<Waypoint> waypointsTop = new ArrayList<>();
    waypointsTop.add(waypoint2top);
    waypointsTop.add(waypoint1top);
    waypointsTop.add(waypointTurretAllied);
    return waypointsTop;
  }

  public List<Waypoint> getWaypointListMidAllied(){
    List<Waypoint> waypointsTop = new ArrayList<>();
    waypointsTop.add(waypoint0mid);
    waypointsTop.add(waypoint2mid);
    waypointsTop.add(waypoint1mid);
    waypointsTop.add(waypointTurretEnemy);
    return waypointsTop;
  }

  public List<Waypoint> getWaypointListMidEnemy(){
    List<Waypoint> waypointsTop = new ArrayList<>();
    waypointsTop.add(waypoint2mid);
    waypointsTop.add(waypoint1mid);
    waypointsTop.add(waypointTurretAllied);
    return waypointsTop;
  }

  public List<Waypoint> getWaypointListBottomAllied(){
    List<Waypoint> waypointsTop = new ArrayList<>();
    waypointsTop.add(waypoint0bottom);
    waypointsTop.add(waypoint1bottom);
    waypointsTop.add(waypoint2bottom);
    waypointsTop.add(waypointTurretEnemy);
    return waypointsTop;
  }

  public List<Waypoint> getWaypointListBottomEnemy(){
    List<Waypoint> waypointsTop = new ArrayList<>();
    waypointsTop.add(waypoint2bottom);
    waypointsTop.add(waypoint1bottom);
    waypointsTop.add(waypointTurretAllied);
    return waypointsTop;
  }

}
