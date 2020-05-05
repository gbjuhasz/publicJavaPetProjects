package decisionmaking;

import fighting.AttackManager;
import java.util.ArrayList;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public abstract class DecisionMaker {

  AttackManager attackManager = new AttackManager();

  public void reactToPlayerMovement(Unit unitMakingDecision,
                                    Mech mech,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    int roundCounter) {

    if (findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      attackTarget(unitMakingDecision, findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret), roundCounter);
    } else if (findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      moveTowardsTargetUnit(unitMakingDecision, findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret), roundCounter);
    } else {
      moveTowardsTargetUnit(unitMakingDecision, turret, roundCounter);
    }
  }

  public Unit findTargetInAttackRange(Unit unitMakingDecision,
                                      Mech mech,
                                      ArrayList<Creep> listOfCreeps,
                                      Turret turret) {

    int attackRange = unitMakingDecision.getAttackRange();

    if (mech.isThreatToHeroUnit() &&
            mech.isAlive() &&
            unitMakingDecision.calculateDistanceBetweenUnits(mech) <= attackRange) {
      return mech;
    }
    for (Creep creep : listOfCreeps) {
      if (unitMakingDecision.calculateDistanceBetweenUnits(creep) <= attackRange &&
      creep.isAlive()) {
        return creep;
      }
    }
    if (!mech.isThreatToHeroUnit() &&
            mech.isAlive() &&
            unitMakingDecision.calculateDistanceBetweenUnits(mech) <= attackRange) {
      return mech;
    } else if (unitMakingDecision.calculateDistanceBetweenUnits(turret) <= attackRange) {
      return turret;
    }
    return null;
  }

  public Unit findTargetInDetectionRange(Unit unitMakingDecision,
                                         Mech mech,
                                         ArrayList<Creep> listOfCreeps,
                                         Turret turret) {

    int detectionRange = unitMakingDecision.getDetectionRange();

    if (mech.isThreatToHeroUnit() &&
            mech.isAlive() &&
            unitMakingDecision.calculateDistanceBetweenUnits(mech) <= detectionRange) {
      return mech;
    }
    for (Creep creep : listOfCreeps) {
      if (unitMakingDecision.calculateDistanceBetweenUnits(creep) <= detectionRange &&
      creep.isAlive()) {
        return creep;
      }
    }
    if (!mech.isThreatToHeroUnit()
            && unitMakingDecision.calculateDistanceBetweenUnits(mech) <= detectionRange &&
    mech.isAlive()) {
      return mech;
    } else if (unitMakingDecision.calculateDistanceBetweenUnits(turret) <= detectionRange) {
      return turret;
    }
    return null;
  }

  public void moveTowardsTargetUnit(Unit unitMakingMove, Unit unitTarget, int roundCounter) {
  }

  public void attackTarget(Unit unitAttacking, Unit unitTarget, int roundCounter) {
    attackManager.attackTargetUnit(unitAttacking,unitTarget, roundCounter);
  }

}
