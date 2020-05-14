package visualeffects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import units.Mech;
import units.Unit;

public class HUD extends JComponent {

  public ArrayList<Integer> findCrosshairCoordinates(Unit unit) {
    ArrayList<Integer> crosshairCoordinates = new ArrayList<>();
    crosshairCoordinates.add(unit.getPosX());
    crosshairCoordinates.add(unit.getPosY());
    if (unit.getUnitType().equals("turret")) {
      crosshairCoordinates.add(144);
    } else {
      crosshairCoordinates.add(72);
    }
    return crosshairCoordinates;
  }

  public void drawHUD(Mech mechHero, List<Unit> listOfEnemyUnits, Graphics graphics) {
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
       /* if (unit.getHealthPoints() <= mechHero.getAttackDamage()) {
          graphics.setColor(Color.RED);
          graphics.drawString(String.valueOf(unit.getHealthPoints()), crosshairCoordinates.get(0) + 72, crosshairCoordinates.get(1) - 10);
        } else {
          graphics.drawString(String.valueOf(unit.getHealthPoints()), crosshairCoordinates.get(0) + 72, crosshairCoordinates.get(1) - 10);
        }*/
        graphics.setColor(Color.GREEN);
      }
    }
  }

  public void drawHealthBars(List<Unit> listOfAllUnits, Graphics graphics) {
    for (Unit unit: listOfAllUnits) {
      if(unit.getUnitType().equals("turret")){
        graphics.setColor(Color.GREEN);
        graphics.drawLine(unit.getPosX(), unit.getPosY() - 10, unit.getPosX() +
                calculateLengthOfGreenBar(unit, 72), unit.getPosY() - 10);
        graphics.setColor(Color.RED);
        graphics.drawLine(unit.getPosX() +
                        calculateLengthOfGreenBar(unit, 72),unit.getPosY() -10,
                unit.getPosX() + 144, unit.getPosY() -10);
      } else {
        graphics.setColor(Color.GREEN);
        graphics.drawLine(unit.getPosX(), unit.getPosY() - 10, unit.getPosX() +
                calculateLengthOfGreenBar(unit, 72), unit.getPosY() - 10);
        graphics.setColor(Color.RED);
        graphics.drawLine(unit.getPosX() +
                        calculateLengthOfGreenBar(unit, 72),unit.getPosY() -10,
                unit.getPosX() + 72, unit.getPosY() -10);
      }
    }
  }

  public int calculateLengthOfGreenBar(Unit unit, Integer healthBarLength) {
    if (unit.getHealthPoints() == unit.getRespawnHealthPoints()) {
      return healthBarLength;
    } else {
      Integer oneTickHealth = unit.getRespawnHealthPoints() / healthBarLength;
      return unit.getHealthPoints() / oneTickHealth;
    }
  }
}
