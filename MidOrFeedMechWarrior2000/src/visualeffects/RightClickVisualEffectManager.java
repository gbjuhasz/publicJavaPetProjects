package visualeffects;

import com.sun.tools.classfile.RuntimeInvisibleTypeAnnotations_attribute;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import units.Unit;

public class RightClickVisualEffectManager extends JComponent {

  public RightClickVisualEffectManager() {
  }

  public void drawUpRightClicks(List<Unit> listOfAllUnits, Integer roundCounter, Graphics graphics) {

    Graphics2D graphics2d = (Graphics2D) graphics.create();
    String roundEvenOrOdd;
    if (roundCounter % 2 == 0) {
      roundEvenOrOdd = "EVEN";
    } else {
      roundEvenOrOdd = "ODD";
    }

    //set the stroke of the copy, not the original
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    graphics2d.setStroke(dashed);

    for (Unit unit :
            listOfAllUnits) {
      if (unit.isRightClickAttackedThisRound() &&
              unit.getUnitTargeted() != null &&
              unit.getUnitTargeted().isAlive() &&
              unit.calculateDistanceBetweenUnits(unit.getUnitTargeted()) < unit.getAttackRange()) {
        String unitFacingDirection = unit.getFacingDirection();

        Integer laserX1 = unit.getxCoordinatesForRightClickEffectInEveryDirection().get(unitFacingDirection);
        Integer laserY1 = unit.getyCoordinatesForRightClickEffectInEveryDirection().get(unitFacingDirection);
        Integer laserX2 = unit.getUnitTargeted().getImageMiddleX();
        Integer laserY2 = unit.getUnitTargeted().getImageMiddleY();

        if (laserX1 != null &&
                laserY1 != null &&
                laserX2 != null &&
                laserY2 != null &&
                !unit.getLastAttackResult().equals("Miss!")) {
          unit.getRightClickEffect().setPosX(laserX2);
          unit.getRightClickEffect().setPosY(laserY2);
          unit.getRightClickEffect().pickImage("images/explosions/rightClickExplosion" + roundEvenOrOdd + ".png");
          unit.getRightClickEffect().setDisappearsInRound(unit.getRoundAttackedLastTime() + unit.getRightClickVisibleForRounds());
          unit.getRightClickEffect().draw(graphics);
          if (unit.getUnitType().contains("Hero")) {
            graphics2d.setColor(Color.RED);
            graphics2d.drawLine(unit.getPosX() + laserX1, unit.getPosY() + laserY1, laserX2, laserY2);
          } else if (unit.getUnitType().equals("creepAllied")) {
            graphics2d.setColor(Color.CYAN);
            graphics2d.drawLine(unit.getPosX() + laserX1, unit.getPosY() + laserY1, laserX2, laserY2);
          } else if (unit.getUnitType().equals("creepEnemy")) {
            graphics2d.setColor(Color.WHITE);
            graphics2d.drawLine(unit.getPosX() + laserX1, unit.getPosY() + laserY1, laserX2, laserY2);
          } else if (unit.getUnitType().equals("mechEnemy")) {
            graphics2d.setColor(Color.BLUE);
            graphics2d.drawLine(unit.getPosX() + laserX1, unit.getPosY() + laserY1, laserX2, laserY2);
          }
        } else if (unit.getLastAttackResult().equals("Miss!")) {
          graphics2d.setColor(Color.WHITE);
          graphics2d.drawString(unit.getLastAttackResult(), laserX2, laserY2);
          unit.getRightClickEffect().setPosX(-100);
          unit.getRightClickEffect().setPosY(-100);
        }
        if (roundCounter > unit.getRoundAttackedLastTime() + unit.getRightClickVisibleForRounds()) {
          unit.setRightClickAttackedThisRound(false);
        }
      }
    }
  }
}
