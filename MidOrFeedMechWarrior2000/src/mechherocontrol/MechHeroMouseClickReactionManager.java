package mechherocontrol;

import abilities.Ability;
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
    if (findActivatedAbility(mechHero) != null &&
    identifyClickedUnit(listOfAllUnits,e) != null) {
      ArrayList<Unit> listOfPossibleAbilityTargets = new ArrayList<>();
      for (Unit listOfAllUnit : listOfAllUnits) {
        if (listOfAllUnit.getUnitType().contains("Enemy") &&
                listOfAllUnit.isAlive() &&
                !listOfAllUnit.getUnitType().contains("turret")) {
          listOfPossibleAbilityTargets.add(listOfAllUnit);
        }
      }
      identifyClickedUnit(listOfAllUnits, e).setHighlighted(true);
      if(findActivatedAbility(mechHero).getCategory().contains("AOE")) {
        findActivatedAbility(mechHero).useAOEAbility(mechHero, identifyClickedUnit(listOfAllUnits, e), listOfPossibleAbilityTargets, roundCounter);
        findActivatedAbility(mechHero).setActivated(false);
      } else {
        findActivatedAbility(mechHero).useAbility(mechHero,identifyClickedUnit(listOfPossibleAbilityTargets,e), roundCounter);
        findActivatedAbility(mechHero).setActivated(false);
      }
    } else if(findActivatedAbility(mechHero) == null &&
            identifyClickedUnit(listOfAllUnits,e) != null
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
    if(findActivatedAbility(mechHero) != null) {
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
    if(listOfUnits.size() >= 1) {
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
}
