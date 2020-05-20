package visualeffects;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import units.Unit;

public class RightClickVisualEffectManager extends JComponent {

  public RightClickVisualEffectManager() {
  }

  public void drawUpRightClicks(List<Unit> listOfAllUnits, Integer roundCounter, Graphics graphics) {
    for (Unit unit :
            listOfAllUnits) {
      if (unit.isRightClickAttackedThisRound() &&
              unit.getUnitTargeted() != null &&
              unit.calculateDistanceBetweenUnits(unit.getUnitTargeted()) < unit.getAttackRange()) {
        String unitFacingDirection = unit.getFacingDirection();

        Integer laserX1 = unit.getxCoordinatesForRightClickEffectInEveryDirection().get(unitFacingDirection);
        Integer laserY1 = unit.getyCoordinatesForRightClickEffectInEveryDirection().get(unitFacingDirection);
        Integer laserX2 = unit.getUnitTargeted().getImageMiddleX();
        Integer laserY2 = unit.getUnitTargeted().getImageMiddleY();

        if (unit.getUnitType().contains("Hero") &&
                laserX1 != null &&
                laserY1 != null &&
                laserX2 != null &&
                laserY2 != null) {
          graphics.setColor(Color.RED);
          graphics.drawLine(unit.getPosX() + laserX1, unit.getPosY() + laserY1, laserX2, laserY2);
        }
        if (roundCounter > unit.getRoundAttackedLastTime() + unit.getRightClickVisibleForRounds()) {
          unit.setRightClickAttackedThisRound(false);
        }
      }
    }
  }
}
