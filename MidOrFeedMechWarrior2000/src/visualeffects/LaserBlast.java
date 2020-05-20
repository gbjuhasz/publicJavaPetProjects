package visualeffects;

import board.Tile;
import units.Unit;

public class LaserBlast extends Tile {

  private boolean hasArrived;
  private Integer targetX;
  private Integer targetY;

  public LaserBlast() {
    hasArrived = true;
  }

  public void launchLaserBlastTowardsTarget(Unit unitAttacking, Unit unitTargeted) {

    super.setPosX(unitAttacking.getImageMiddleX());
    super.setPosY(unitAttacking.getImageMiddleY());
    targetX = unitTargeted.getImageMiddleX();
    targetY = unitTargeted.getImageMiddleY();
    setHasArrived(false);
  }

  public boolean isHasArrived() {
    return hasArrived;
  }

  public void setHasArrived(boolean hasArrived) {
    this.hasArrived = hasArrived;
  }
}

