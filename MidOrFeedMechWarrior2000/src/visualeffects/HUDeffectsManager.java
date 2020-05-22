package visualeffects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import units.Mech;
import units.Unit;

public class HUDeffectsManager extends JComponent {

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
    if (mechHero.getUnitTargeted() != null) {
  //    for (Unit unit : listOfEnemyUnits) {
  //      if (mechHero.calculateDistanceBetweenUnits(unit) < mechHero.getAttackRange()) {
  /*        if (unit.getHealthPoints() <= mechHero.getAttackDamage() &&
                  mechHero.getAttackRange() >= mechHero.calculateDistanceBetweenUnits(unit)) {
            graphics.setColor(Color.RED);
          } else if (unit.getHealthPoints() > mechHero.getAttackDamage() &&
                  mechHero.getAttackRange() >= mechHero.calculateDistanceBetweenUnits(unit)) {
            graphics.setColor(Color.GREEN);
          } else {
            graphics.setColor(Color.WHITE);
          }*/
      graphics.setColor(Color.GREEN);
      ArrayList<Integer> crosshairCoordinates = findCrosshairCoordinates(mechHero.getUnitTargeted());
          graphics.drawRect(crosshairCoordinates.get(0),
                  crosshairCoordinates.get(1),
                  crosshairCoordinates.get(2),
                  crosshairCoordinates.get(2));
       //   graphics.setColor(Color.GREEN);
        }
 //     }
 //   }
  }

  public void drawHealthBars(List<Unit> listOfAllUnits, Graphics graphics) {
    for (Unit unit : listOfAllUnits) {
      if (unit.getUnitType().equals("turret")) {
        graphics.setColor(Color.GREEN);
        graphics.drawLine(unit.getPosX(), unit.getPosY() - 10, unit.getPosX() +
                calculateLengthOfGreenBar(unit, 72), unit.getPosY() - 10);
        graphics.setColor(Color.RED);
        graphics.drawLine(unit.getPosX() +
                        calculateLengthOfGreenBar(unit, 72), unit.getPosY() - 10,
                unit.getPosX() + 144, unit.getPosY() - 10);
      } else {
        if (unit.getUnitType().substring(unit.getUnitType().length() - 5, unit.getUnitType().length()).equals("Enemy")) {
          graphics.setColor(Color.RED);
        } else {
          graphics.setColor(Color.GREEN);
        }
        graphics.drawLine(unit.getPosX(), unit.getPosY() - 10, unit.getPosX() +
                calculateLengthOfGreenBar(unit, 72), unit.getPosY() - 10);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(unit.getPosX() +
                        calculateLengthOfGreenBar(unit, 72), unit.getPosY() - 10,
                unit.getPosX() + 72, unit.getPosY() - 10);
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

  public void drawUnitImageForHighlightedUnit(List<Unit> listOfAllUnits, HUD hud, Graphics graphics) {
    hud.getHudUnitImage().setImage(null);
    for (Unit unit : listOfAllUnits
    ) {
      if (unit.isHighlighted()) {
        hud.getHudUnitImage().setImage(unit.getImage());
      }
    }
    hud.getHudUnitImage().draw(graphics);
  }

  public void drawInfoOfHighlightedUnit(List<Unit> listOfAllUnits, Graphics graphics) {
    for (Unit unit : listOfAllUnits
    ) {
      if (unit.isHighlighted()) {
        graphics.setColor(Color.green);
        String unitName = new String();
        if(unit.getUnitType().contains("Hero")){
          unitName = "Hero";
          graphics.drawString(unitName, 30, 675);
        } else if(unit.getUnitType().equals("mechEnemy")){
          unitName = "Enemy Hero";
          graphics.drawString(unitName, 16, 675);
        } else if (unit.getUnitType().contains("creep")){
          unitName = "Creep";
          graphics.drawString(unitName, 30, 675);

        }
        graphics.drawString("Level: " + String.valueOf(unit.getLevel()), 30, 745);
        graphics.drawString("Experience: "+ unit.getExperiencePoints()+"/"+unit.getXpNeededForLevelUp().get(unit.getLevel()+1),225,705);
        graphics.drawString("Health: " + String.valueOf(unit.getHealthPoints()) +
                "/" + String.valueOf(unit.getRespawnHealthPoints()), 225, 720);
        graphics.drawString("Energy: ", 225, 735);
        graphics.drawString("Armor: " + String.valueOf(unit.getArmorRating()), 225, 750);

      }
    }
  }
}
