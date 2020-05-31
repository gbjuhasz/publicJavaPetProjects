package mechherocontrol;

import abilities.Ability;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import units.MechHero;
import units.PositionedImage;
import units.Unit;

public class MechHeroMouseClickReactionManager {

  public void reactToLeftClick(MechHero mechHero, List<Unit> listOfAllUnits, MouseEvent e,
                               int roundCounter
  ) {
    for (Unit unit : listOfAllUnits
    ) {
      unit.setHighlighted(false);
    }
    levelUpAbilities(mechHero,e);
    for (int i = 0; i < mechHero.getListOfAbilities().size(); i++) {
      if (mechHero.getAvailableLevelUpPoints() > 0) {
        if (calculateDistanceBetweenAbilityIconAndClick(mechHero.getListOfAbilities().get(i), e) < 30) {
          mechHero.getListOfAbilities().get(i).setLevel(mechHero.getListOfAbilities().get(i).getLevel() + 1);
          mechHero.setAvailableLevelUpPoints(mechHero.getAvailableLevelUpPoints() - 1);
        }
      }
    }
    if (findActivatedAbility(mechHero) != null &&
            identifyClickedUnit(listOfAllUnits, e) != null) {
      ArrayList<Unit> listOfPossibleAbilityTargets = new ArrayList<>();
      for (Unit listOfAllUnit : listOfAllUnits) {
        if (listOfAllUnit.getUnitType().contains("Enemy") &&
                listOfAllUnit.isAlive() &&
                !listOfAllUnit.getUnitType().contains("turret")) {
          listOfPossibleAbilityTargets.add(listOfAllUnit);
        }
      }
      identifyClickedUnit(listOfAllUnits, e).setHighlighted(true);
      if (findActivatedAbility(mechHero).getCategory().contains("aoe")) {
        findActivatedAbility(mechHero).useAOEAbility(mechHero, identifyClickedUnit(listOfAllUnits, e), listOfPossibleAbilityTargets, roundCounter);
        findActivatedAbility(mechHero).setActivated(false);
      } else {
        findActivatedAbility(mechHero).useAbility(mechHero, identifyClickedUnit(listOfPossibleAbilityTargets, e), roundCounter);
        findActivatedAbility(mechHero).setActivated(false);
      }
    } else if (findActivatedAbility(mechHero) == null &&
            identifyClickedUnit(listOfAllUnits, e) != null
    ) {
      identifyClickedUnit(listOfAllUnits, e).setHighlighted(true);
    } else {
      mechHero.setHighlighted(true);
    }
  }

  public void reactToRightClick(MechHero mechHero,
                                List<Unit> listOfAllUnits,
                                MouseEvent mouseEvent
  ) {
    if (findActivatedAbility(mechHero) != null) {
      findActivatedAbility(mechHero).setActivated(false);
    }
    mechHero.setUnitTargeted(null);
    mechHero.setMouseEventMarkingLocation(null);
    List<Unit> listOfAllRightClickableUnits = new ArrayList<>();
    for (Unit unit : listOfAllUnits
    ) {
      if (unit.getUnitType().contains("Enemy") &&
              unit.isAlive()) {
        listOfAllRightClickableUnits.add(unit);
      }
    }
    if (identifyClickedUnit(listOfAllRightClickableUnits, mouseEvent) == null) {
      mechHero.setMouseEventMarkingLocation(mouseEvent);
      mechHero.setHighlighted(true);
    } else {
      mechHero.setUnitTargeted(identifyClickedUnit(listOfAllRightClickableUnits, mouseEvent));
      for (Unit unit : listOfAllUnits
      ) {
        if (unit.isHighlighted()) {
          unit.setHighlighted(false);
        }
      }
      identifyClickedUnit(listOfAllRightClickableUnits, mouseEvent).setHighlighted(true);
    }
  }

  private Unit identifyClickedUnit(List<Unit> listOfUnits, MouseEvent e) {
    if (listOfUnits.size() >= 1) {
      double closestUnitDistance = calculateDistanceBetweenClickAndUnits(e, listOfUnits.get(0));
      Unit closestUnit = listOfUnits.get(0);
      for (Unit unit : listOfUnits) {
        unit.setHighlighted(false);
        if (unit.isAlive() == true) {
          double distance = calculateDistanceBetweenClickAndUnits(e, unit);
          if (distance < closestUnitDistance) {
            closestUnitDistance = distance;
            closestUnit = unit;
          }
        }
      }

      if (closestUnitDistance <= 72 &&
              closestUnit.isAlive()) {
        return closestUnit;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  private double calculateDistanceBetweenClickAndUnits(MouseEvent e, Unit unit) {
    int a = Math.abs(e.getX() - unit.getImageMiddleX());
    int b = Math.abs(e.getY() - unit.getImageMiddleY());
    return Math.sqrt(a * a + b * b);
  }

  private Ability findActivatedAbility(MechHero mechHero) {
    for (Ability ability : mechHero.getListOfAbilities()
    ) {
      if (ability.isActivated()) {
        return ability;
      }
    }
    return null;
  }

  public double calculateDistanceBetweenAbilityIconAndClick(PositionedImage abilityOrAura, MouseEvent e) {
    int a = Math.abs(abilityOrAura.getPosX() + 10 - e.getX());
    int b = Math.abs(abilityOrAura.getPosY() + 10 - e.getY());
    double distance = Math.sqrt(a * a + b * b);
    return distance;
  }

  public void levelUpAbilities(MechHero mechHero, MouseEvent e) {
    if (mechHero.getAvailableLevelUpPoints() > 0) {
      for (int i = 0; i < 4; i++) {
        if (calculateDistanceBetweenAbilityIconAndClick(mechHero.getListOfAbilities().get(i), e) < 30) {
          mechHero.getListOfAbilities().get(i).setLevel(mechHero.getListOfAbilities().get(i).getLevel() + 1);
          mechHero.setAvailableLevelUpPoints(mechHero.getAvailableLevelUpPoints() - 1);
        }
        if (calculateDistanceBetweenAbilityIconAndClick(mechHero.getListOfPassiveAbilities().get(i), e) < 30) {
          mechHero.getListOfPassiveAbilities().get(i).setLevel(mechHero.getListOfPassiveAbilities().get(i).getLevel() + 1);
          mechHero.setAvailableLevelUpPoints(mechHero.getAvailableLevelUpPoints() - 1);
        }
        if (calculateDistanceBetweenAbilityIconAndClick(mechHero.getListOfAuras().get(i), e) < 30) {
          mechHero.getListOfAuras().get(i).setLevel(mechHero.getListOfAuras().get(i).getLevel() + 1);
          mechHero.setAvailableLevelUpPoints(mechHero.getAvailableLevelUpPoints() - 1);
        }
      }
    }
  }
}

