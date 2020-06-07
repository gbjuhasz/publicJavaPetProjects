package decisionmaking;

import abilities.Aura;
import board.BoardComponent;
import fighting.AttackManager;
import java.util.ArrayList;
import java.util.List;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public abstract class DecisionMaker {

  AttackManager attackManager = new AttackManager();

  public void reactToPlayerMovement(Unit unitMakingDecision,
                                    Mech mechAllied,
                                    Mech mechHostile,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    List<Unit> listOfAllUnits,
                                    List<Mech> listOfMechs,
                                    int roundCounter) {

    isItTimeToSwitchFeet(unitMakingDecision, roundCounter);
    checkIfStillHasTarget(unitMakingDecision);

    if (findTargetInRange(unitMakingDecision, mechHostile, listOfCreeps, turret, unitMakingDecision.getAttackRange()) != null) {
      attackTarget(unitMakingDecision, findTargetInRange(unitMakingDecision, mechHostile, listOfCreeps, turret, unitMakingDecision.getAttackRange()), listOfMechs, roundCounter);
    } else if (findTargetInRange(unitMakingDecision, mechHostile, listOfCreeps, turret, unitMakingDecision.getDetectionRange()) != null) {
      moveTowardsTargetUnit(unitMakingDecision, findTargetInRange(unitMakingDecision, mechHostile, listOfCreeps, turret, unitMakingDecision.getDetectionRange()), listOfAllUnits, roundCounter);
    }
  }

  public Unit findTargetInRange(Unit unitMakingDecision,
                                      Mech mech,
                                      ArrayList<Creep> listOfCreeps,
                                      Turret turret, int range) {

    List<Unit> unitsInRange = new ArrayList<>();

    if (mech.isAlive() &&
            !mech.isInvisible() &&
            unitMakingDecision.calculateDistanceBetweenUnits(mech) <= range) {
      unitsInRange.add(mech);
    }
    for (Creep creep : listOfCreeps) {
      if (unitMakingDecision.calculateDistanceBetweenUnits(creep) <= range &&
              creep.isAlive()) {
        unitsInRange.add(creep);
      }
    }

    if (unitMakingDecision.calculateDistanceBetweenUnits(turret) <= range) {
      unitsInRange.add(turret);
    }

    if(unitsInRange.size() == 0) {
      return null;
    } else {
      Unit unitToReturn = unitsInRange.get(0);
      double unitToReturnDistance = unitMakingDecision.calculateDistanceBetweenUnits(unitsInRange.get(0));
      int unitToReturnAggroPriority = unitsInRange.get(0).getAggroPriority();
      for (Unit unit: unitsInRange
           ) {
        if(unit.getAggroPriority() > unitToReturnAggroPriority){
          unitToReturn = unit;
          unitToReturnDistance = unit.calculateDistanceBetweenUnits(unitMakingDecision);
          unitToReturnAggroPriority = unit.getAggroPriority();
        } else if (unit.getAggroPriority() == unitToReturnAggroPriority){
          if(unit.calculateDistanceBetweenUnits(unitMakingDecision) < unitToReturnDistance){
            unitToReturn = unit;
            unitToReturnDistance = unit.calculateDistanceBetweenUnits(unitMakingDecision);
            unitToReturnAggroPriority = unit.getAggroPriority();
          }
        }
      }
      return unitToReturn;
    }
  }

  public void moveTowardsTargetUnit(Unit unitMakingMove, BoardComponent boardComponent, List<Unit> listOfAllUnit, int roundCounter) {
  }

  public void attackTarget(Unit unitAttacking, Unit unitTarget, List<Mech> listOfMechs, int roundCounter) {
    attackManager.attackTargetUnit(unitAttacking, unitTarget, listOfMechs, roundCounter);
  }

  public void isItTimeToSwitchFeet(Unit unitMoving, int roundCounter) {

    if (unitMoving.getSwitchFeetInRound() == roundCounter) {
      if (unitMoving.getFeetForward().equals("ODD")) {
        unitMoving.setFeetForward("EVEN");
        unitMoving.setSwitchFeetInRound(roundCounter + unitMoving.getSwitchFeetEveryXRound());
      } else {
        unitMoving.setFeetForward("ODD");
        unitMoving.setSwitchFeetInRound(roundCounter + unitMoving.getSwitchFeetEveryXRound());
      }
    }
  }

  public BoardComponent followWaypoints(Creep creep) {
    return creep.getListOfWaypointsToFollow().get(creep.getHeadingTowardsWaypoint());
  }

  public void checkIfStillHasTarget(Unit unitMakingDecision) {
    if (unitMakingDecision.getUnitTargeted() != null &&
            !unitMakingDecision.getUnitTargeted().isAlive()) {
      unitMakingDecision.setUnitTargeted(null);
    }
  }

  public void placeDamageAndArmorAuraEffectsOnUnit(Unit unit, Mech mech) {
    Aura extraDamageAura = mech.getListOfAuras().get(0);
    Aura extraArmorAura = mech.getListOfAuras().get(1);
    double distanceBetweenUnitAndMech = unit.calculateDistanceBetweenUnits(mech);
    if (extraDamageAura.getLevel() > 0 &&
            distanceBetweenUnitAndMech <= extraDamageAura.getRange() &&
    !unit.isUnderDamageAuraEffect()) {
      unit.setAttackDamage(unit.getAttackDamage() + extraDamageAura.getBonus());
      unit.setUnderDamageAuraEffect(true);
    }
    if (extraArmorAura.getLevel() > 0 &&
            unit.calculateDistanceBetweenUnits(mech) <= extraArmorAura.getRange() &&
    !unit.isUnderArmorAuraEffect()) {
      unit.setArmorRating(unit.getArmorRating() + extraArmorAura.getBonus());
      unit.setUnderArmorAuraEffect(true);
    }
  }

  public void removeAuraEffects(Unit unit,Mech mech){
    Aura extraDamageAura = mech.getListOfAuras().get(0);
    Aura extraArmorAura = mech.getListOfAuras().get(1);
    double distanceBetweenUnitAndMech = unit.calculateDistanceBetweenUnits(mech);
    if(unit.isUnderDamageAuraEffect() &&
            distanceBetweenUnitAndMech > extraDamageAura.getRange()){
      unit.setAttackDamage(unit.getAttackDamage()-extraDamageAura.getBonus());
      unit.setUnderDamageAuraEffect(false);
    }
    if(unit.isUnderArmorAuraEffect() &&
    distanceBetweenUnitAndMech > extraArmorAura.getRange()){
      unit.setArmorRating(unit.getArmorRating()-extraArmorAura.getBonus());
      unit.setUnderArmorAuraEffect(false);
    }
  }

  public void placeEnergyRestorationAuraEffectOnMechs(Mech mech, List<Creep> listOfCreeps){
    int energyRestored = 0;
    Aura energyRestorationAura = mech.getListOfAuras().get(2);
    if(energyRestorationAura.getLevel() > 0){
      energyRestored = energyRestored + energyRestorationAura.getBonus();
    }
    for (Creep creep: listOfCreeps) {
      if(creep.calculateDistanceBetweenUnits(mech) < energyRestorationAura.getRange()){
        energyRestored = energyRestored + energyRestorationAura.getBonus();
      }
    }
    if(mech.getEnergy() + energyRestored > mech.getRespawnEnergy()){
      mech.setEnergy(mech.getRespawnEnergy());
    } else {
      mech.setEnergy(mech.getEnergy() + energyRestored);
    }
  }
}
