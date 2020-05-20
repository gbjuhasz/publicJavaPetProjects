package decisionmaking;

import board.EffectLayout;
import fighting.MechHeroAttackManager;
import java.util.ArrayList;
import java.util.List;
import movement.MechHeroMovementManager;
import units.*;
import visualeffects.LaserBlast;

public class MechHeroDecisionMaker extends DecisionMaker {
  MechHeroAttackManager mechHeroAttackManager = new MechHeroAttackManager();
  MechHeroMovementManager mechHeroMovementManager = new MechHeroMovementManager();

  public void reactToMouseCommand(MechHero mechHero,
                                  Mech mech,
                                  ArrayList<Creep> listOfCreeps,
                                  Turret turret,
                                  List<Unit> listOfAllUnits,
                                  int roundCounter) {

    isItTimeToSwitchFeet(mechHero, roundCounter);
    checkIfStillHasTarget(mechHero);

    if (mechHero.getUnitTargeted() != null) {
      if (mechHero.calculateDistanceBetweenUnits(mechHero.getUnitTargeted()) <= mechHero.getAttackRange()) {
        mechHeroAttackManager.attackTargetUnitWithMechHero(mechHero, mechHero.getUnitTargeted(), roundCounter);
      } else {
        mechHeroMovementManager.moveUnit(mechHero, mechHero.getUnitTargeted(), listOfAllUnits, roundCounter);
      }
    }
    if (mechHero.getUnitTargeted() == null &&
            mechHero.getMouseEventMarkingLocation() != null) {
      mechHeroMovementManager.moveUnitTowardsMouseEvent(mechHero, listOfAllUnits, roundCounter);
      if (mechHero.getPosX() == mechHero.getMouseEventMarkingLocation().getX() - 36 &&
              mechHero.getPosY() == mechHero.getMouseEventMarkingLocation().getY() - 72) {
        mechHero.setMouseEventMarkingLocation(null);
      }
    }
  }
}
