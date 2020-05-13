package visualeffects;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import units.Mech;
import units.Unit;

public class HUD extends JComponent {

  public ArrayList<Integer> findCrosshairCoordinates(Unit unit) {
    ArrayList<Integer> crosshairCoordinates = new ArrayList<>();
    crosshairCoordinates.add(unit.getPosX());
    crosshairCoordinates.add(unit.getPosY());
    crosshairCoordinates.add(72);
    return crosshairCoordinates;
  }

  public void drawHUD(Mech mechHero, List<Unit> listOfEnemyUnits, Graphics graphics){
    for (Unit unit : listOfEnemyUnits) {
      if (mechHero.calculateDistanceBetweenUnits(unit) < mechHero.getAttackRange()) {
        if (unit.getHealthPoints() <= mechHero.getAttackDamage() &&
                mechHero.getAttackRange() >= mechHero.calculateDistanceBetweenUnits(unit)) {
          graphics.setColor(Color.RED);
        } else if (unit.getHealthPoints() > mechHero.getAttackDamage() &&
                mechHero.getAttackRange() >= mechHero.calculateDistanceBetweenUnits(unit)) {
          graphics.setColor(Color.GREEN);
        } else {
          graphics.setColor(Color.WHITE);
        }
        ArrayList<Integer> crosshairCoordinates = findCrosshairCoordinates(unit);
        graphics.drawRect(crosshairCoordinates.get(0),
                crosshairCoordinates.get(1),
                crosshairCoordinates.get(2),
                crosshairCoordinates.get(2));
        graphics.drawString(unit.getCanBeAttackedWithThisButton(),
                crosshairCoordinates.get(0),
                crosshairCoordinates.get(1) - 10);
        if (unit.getHealthPoints() <= mechHero.getAttackDamage()) {
          graphics.setColor(Color.RED);
          graphics.drawString(String.valueOf(unit.getHealthPoints()), crosshairCoordinates.get(0) + 72, crosshairCoordinates.get(1) - 10);
        } else {
          graphics.drawString(String.valueOf(unit.getHealthPoints()), crosshairCoordinates.get(0) + 72, crosshairCoordinates.get(1) - 10);
        }
        graphics.setColor(Color.GREEN);
      }
    }

  }
}
