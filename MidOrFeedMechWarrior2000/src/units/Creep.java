package units;

import java.util.ArrayList;
import java.util.List;

public abstract class Creep extends Unit {
  //fields for fighting
  private int attackRange = 72;
  private int attackDamage = 200;
  private int healthPoints = 550;
  private int respawnHealthPoints = 550;
  private int armorRating = 0;
  private int detectionRange = 144;
  private int missChance = 2;
  private int roundToRespawn;
  private int waveSpawnTimer;
  private List<Waypoint> listOfWaypointsToFollow = new ArrayList<>();
  private int headingTowardsWaypoint = 0;
  //fields for decision making

  @Override
  public int getDetectionRange() {
    return detectionRange;
  }

  @Override
  public int getAttackRange() {
    return attackRange;
  }

  @Override
  public int getAttackDamage() {
    return attackDamage;
  }

  @Override
  public int getHealthPoints() {
    return healthPoints;
  }

  @Override
  public int getArmorRating() {
    return armorRating;
  }

  @Override
  public int getMissChance() {
    return missChance;
  }

  public int getRoundToRespawn() {
    return roundToRespawn;
  }

  public int getWaveSpawnTimer() {
    return waveSpawnTimer;
  }

  public List<Waypoint> getListOfWaypointsToFollow() {
    return listOfWaypointsToFollow;
  }

  public int getHeadingTowardsWaypoint() {
    return headingTowardsWaypoint;
  }

  @Override
  public int getRespawnHealthPoints() {
    return respawnHealthPoints;
  }

  public void setWaveSpawnTimer(int waveSpawnTimer) {
    this.waveSpawnTimer = waveSpawnTimer;
  }

  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  public void setRoundToRespawn(int roundToRespawn) {
    this.roundToRespawn = roundToRespawn;
  }

  public void setListOfWaypointsToFollow(List<Waypoint> listOfWaypointsToFollow) {
    this.listOfWaypointsToFollow = listOfWaypointsToFollow;
  }

  public void setHeadingTowardsWaypoint(int headingTowardsWaypoint) {
    this.headingTowardsWaypoint = headingTowardsWaypoint;
  }
}
