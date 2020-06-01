package mechherocontrol;

import abilities.ActiveAbility;
import abilities.MechAbility;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import units.MechHero;
import units.Unit;

public class MechHeroMouseClickReactionManager {

  public void reactToLeftClick(MechHero mechHero, List<Unit> listOfAllUnits, MouseEvent e,
                               int roundCounter
  ) {
    for (Unit unit : listOfAllUnits
    ) {
      unit.setHighlighted(false);
    }
    if (mechHero.getAvailableLevelUpPoints() > 0 &&
            e.getX() >= 440 &&
            e.getY() - 24 >= 670 &&
            e.getX() <= 570 &&
            e.getY() - 24 <= 750) {
      levelUpAbilities(mechHero, e);
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
    int b = Math.abs(e.getY() - 24 - unit.getImageMiddleY());
    return Math.sqrt(a * a + b * b);
  }

  private ActiveAbility findActivatedAbility(MechHero mechHero) {
    for (ActiveAbility activeAbility : mechHero.getListOfActiveAbilities()
    ) {
      if (activeAbility.isActivated()) {
        return activeAbility;
      }
    }
    return null;
  }

  private MechAbility findClosestAbilityIconToClick(List<MechAbility> mechAbilityList, MouseEvent e) {
    if (e.getY() - 24 >= 670 &&
            e.getY() - 24 < 695) {
      if (e.getX() >= 440 &&
              e.getX() < 470) {
        return mechAbilityList.get(0);
      } else if (e.getX() >= 470 &&
              e.getX() < 500) {
        return mechAbilityList.get(1);
      } else if (e.getX() >= 500 &&
              e.getX() < 530) {
        return mechAbilityList.get(2);
      } else if (e.getX() >= 530) {
        return mechAbilityList.get(3);
      }
    }
    if (e.getY() - 24 >= 695 &&
            e.getY() - 24 < 720) {
      if (e.getX() >= 440 &&
              e.getX() < 470) {
        return mechAbilityList.get(4);
      } else if (e.getX() >= 470 &&
              e.getX() < 500) {
        return mechAbilityList.get(5);
      } else if (e.getX() >= 500 &&
              e.getX() < 530) {
        return mechAbilityList.get(6);
      } else if (e.getX() >= 530) {
        return mechAbilityList.get(7);
      }
    }
    if (e.getY() - 24 >= 720 &&
            e.getY() - 24 < 750) {
      if (e.getX() >= 440 &&
              e.getX() < 470) {
        return mechAbilityList.get(8);
      } else if (e.getX() >= 470 &&
              e.getX() < 500) {
        return mechAbilityList.get(9);
      } else if (e.getX() >= 500 &&
              e.getX() < 530) {
        return mechAbilityList.get(10);
      } else if (e.getX() >= 530) {
        return mechAbilityList.get(11);
      }
    }
    return null;
  }

  private void levelUpAbilities(MechHero mechHero, MouseEvent e) {
    findClosestAbilityIconToClick(mechHero.getAllAbilities(), e).levelUpAbility(mechHero);
    mechHero.setAvailableLevelUpPoints(mechHero.getAvailableLevelUpPoints() - 1);
  }
}

