package decisionmaking;

import fighting.AttackManager;
import java.util.ArrayList;
import java.util.List;
import movement.MechHeroMovementManager;
import units.*;

public class MechHeroDecisionMaker extends DecisionMaker {

  AttackManager attackManager = new AttackManager();
  MechHeroMovementManager mechHeroMovementManager = new MechHeroMovementManager();

  public void reactToMouseCommand(MechHero mechHero,
                                  Mech mech,
                                  ArrayList<Creep> listOfCreeps,
                                  Turret turret,
                                  List<Unit> listOfAllUnits,
                                  int roundCounter) {

    if(mechHero.getUnitTargeted() != null){
      if(mechHero.calculateDistanceBetweenUnits(mechHero.getUnitTargeted()) <= mechHero.getAttackRange()){
        attackManager.attackTargetUnit(mechHero, mech, roundCounter);
      } else {
        mechHeroMovementManager.moveUnit(mechHero, mechHero.getUnitTargeted(),listOfAllUnits, roundCounter);
      }
    }
    if(mechHero.getUnitTargeted() == null){
      mechHeroMovementManager.moveUnitTowardsMouseEvent(mechHero, listOfAllUnits,roundCounter);
    }
  }
}
