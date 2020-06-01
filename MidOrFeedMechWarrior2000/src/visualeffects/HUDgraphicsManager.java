package visualeffects;

import abilities.ActiveAbility;
import abilities.Aura;
import abilities.PassiveAbility;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import units.Mech;
import units.MechHero;
import units.Unit;

public class HUDgraphicsManager extends JComponent {

  private ArrayList<Integer> findCrosshairCoordinates(Unit unit) {
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

  public void drawRectangle(Mech mechHero, List<Unit> listOfEnemyUnits, Graphics graphics) {
    if (mechHero.getUnitTargeted() != null) {
      graphics.setColor(Color.GREEN);
      ArrayList<Integer> crosshairCoordinates = findCrosshairCoordinates(mechHero.getUnitTargeted());
      graphics.drawRect(crosshairCoordinates.get(0),
              crosshairCoordinates.get(1),
              crosshairCoordinates.get(2),
              crosshairCoordinates.get(2));
    }
  }

  public void drawInfoBars(List<Unit> listOfAllUnits, Graphics graphics) {
    for (Unit unit : listOfAllUnits) {
      if (!unit.getUnitType().contains("Hero")) {
        if (unit.getUnitType().substring(unit.getUnitType().length() - 5, unit.getUnitType().length()).equals("Enemy")) {
          graphics.setColor(Color.RED);
        } else {
          graphics.setColor(Color.GREEN);
        }
        graphics.drawLine(unit.getPosX(), unit.getPosY() - 10, unit.getPosX() +
                calculateLengthOfBar(unit.getHealthPoints(), 72, unit.getRespawnHealthPoints()), unit.getPosY() - 10);
        if (unit.getHealthPoints() != unit.getRespawnHealthPoints()) {
          graphics.setColor(Color.BLACK);
          graphics.drawLine(unit.getPosX() +
                          calculateLengthOfBar(unit.getHealthPoints(), 72, unit.getRespawnHealthPoints()), unit.getPosY() - 10,
                  unit.getPosX() + 72, unit.getPosY() - 10);
        }
      }
      if (unit.getUnitType().contains("Hero")) {
        Stroke thick = new BasicStroke(3);
        Graphics2D graphics2d = (Graphics2D) graphics.create();
        graphics2d.setStroke(thick);
        graphics2d.setColor(Color.GREEN);
        graphics2d.drawLine(unit.getPosX(), unit.getPosY() - 10, unit.getPosX() +
                calculateLengthOfBar(unit.getHealthPoints(), 72, unit.getRespawnHealthPoints()), unit.getPosY() - 10);
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawLine(unit.getPosX(), unit.getPosY() - 8, unit.getPosX() +
                calculateLengthOfBar(unit.getEnergy(), 72, unit.getRespawnEnergy()), unit.getPosY() - 8);

        graphics2d.setColor(Color.BLACK);
        if (unit.getRespawnHealthPoints() != unit.getHealthPoints()) {
          graphics2d.drawLine(unit.getPosX() +
                          calculateLengthOfBar(unit.getHealthPoints(), 72, unit.getRespawnHealthPoints()), unit.getPosY() - 10,
                  unit.getPosX() + 72, unit.getPosY() - 10);
        }
        if (unit.getRespawnEnergy() != unit.getEnergy()) {
          graphics2d.drawLine(unit.getPosX() +
                          calculateLengthOfBar(unit.getEnergy(), 72, unit.getRespawnEnergy()), unit.getPosY() - 8,
                  unit.getPosX() + 72, unit.getPosY() - 8);
        }
      }
    }
  }

  private int calculateLengthOfBar(Integer currentValue, Integer barLength, Integer respawnValue) {
    if (currentValue.equals(respawnValue)) {
      return barLength;
    } else {
      Integer oneTickHealth = respawnValue / barLength;
      return currentValue / oneTickHealth;
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
        if (unit.getUnitType().contains("Hero")) {
          unitName = "Hero";
          graphics.drawString(unitName, 30, 675);
        } else if (unit.getUnitType().equals("mechEnemy")) {
          unitName = "Enemy Hero";
          graphics.drawString(unitName, 16, 675);
        } else if (unit.getUnitType().contains("creep")) {
          unitName = "Creep";
          graphics.drawString(unitName, 30, 675);

        }
        graphics.drawString("Level: " + String.valueOf(unit.getLevel()), 30, 745);
        graphics.drawString("Damage: " + unit.getAttackDamage(), 225, 705);
        graphics.drawString("Health: " + String.valueOf(unit.getHealthPoints()) +
                "/" + String.valueOf(unit.getRespawnHealthPoints()), 225, 720);
        graphics.drawString("Energy: " + String.valueOf(unit.getEnergy()) + "/" + unit.getRespawnEnergy(), 225, 735);
        graphics.drawString("Armor: " + String.valueOf(unit.getArmorRating()), 225, 750);

      }
    }
  }

  public void drawXPBar(MechHero mechHero, Graphics graphics) {
    if (mechHero.isHighlighted()) {
      Stroke thick = new BasicStroke(12);
      Graphics2D graphics2d = (Graphics2D) graphics.create();
      graphics2d.setStroke(thick);
      graphics2d.setColor(Color.GREEN);
      if (mechHero.getExperiencePoints() == 0) {
      } else if (mechHero.getLevel() == 1) {
        graphics2d.drawLine(139, 679, 139 + calculateLengthOfBar(mechHero.getExperiencePoints(), 200, mechHero.getXpNeededForLevelUp().get(mechHero.getLevel() + 1)), 679);
      } else {
        graphics2d.drawLine(139, 679, 139 + calculateLengthOfBar(mechHero.getExperiencePoints() - mechHero.getXpNeededForLevelUp().get(mechHero.getLevel()), 200, mechHero.getXpNeededForLevelUp().get(mechHero.getLevel() + 1)), 679);
      }
      graphics2d.setColor(Color.BLACK);
      Stroke thin = new BasicStroke(2);
      graphics2d.setStroke(thin);
      int xForSmallBars = 135;
      for (int i = 1; i < 13; i++) {
        graphics2d.drawLine(xForSmallBars + i * 20, 673, xForSmallBars + i * 20, 684);
      }
    }
  }

  public void drawAbilityIcons(Mech mechHero, Graphics graphics) {

    graphics.setColor(Color.white);

    for (ActiveAbility activeAbility : mechHero.getListOfActiveAbilities()
    ) {
      activeAbility.draw(graphics);
      if (activeAbility.isActivated()) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(activeAbility.getPosX(), activeAbility.getPosY(), 20, 20);
      }
      graphics.setColor(Color.WHITE);
      graphics.drawString("X", activeAbility.getPosX()+5, activeAbility.getPosY()+15);
      graphics.drawString(String.valueOf(activeAbility.getLevel()), activeAbility.getPosX() - 3, activeAbility.getPosY() + 7);
    }
    for (PassiveAbility passiveAbility : mechHero.getListOfPassiveAbilities()
    ) {
      passiveAbility.draw(graphics);
      if (passiveAbility.isTurnedOn()) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(passiveAbility.getPosX(), passiveAbility.getPosY(), 20, 20);
      }
      graphics.setColor(Color.WHITE);
      graphics.drawString("X", passiveAbility.getPosX()+5, passiveAbility.getPosY()+15);

      graphics.drawString(String.valueOf(passiveAbility.getLevel()), getX(), getY());
      graphics.drawString(String.valueOf(passiveAbility.getLevel()), passiveAbility.getPosX() - 3, passiveAbility.getPosY() + 7);

    }
    for (Aura aura : mechHero.getListOfAuras()
    ) {
      aura.draw(graphics);
      if (aura.isTurnedOn()) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(aura.getPosX(), aura.getPosY(), 20, 20);
      }
      graphics.setColor(Color.WHITE);
      graphics.drawString("X", aura.getPosX()+5, aura.getPosY()+15);

      graphics.drawString(String.valueOf(aura.getLevel()), getX(), getY());
      graphics.drawString(String.valueOf(aura.getLevel()), aura.getPosX() - 3, aura.getPosY() + 7);
    }
    graphics.drawLine(440,670, 560,670);
    graphics.drawLine(440,750, 560,750);
    graphics.drawLine(440,695, 560,695);
    graphics.drawLine(440,720, 560,720);
    graphics.drawLine(440,750, 560,750);
    graphics.drawLine(440,670, 440,750);
    graphics.drawLine(560,670, 560,750);
    graphics.drawLine(470,670, 470,750);
    graphics.drawLine(500,670, 500,750);
    graphics.drawLine(530,670, 530,750);
  }
}
