package decisionmaking;

import fighting.AttackManager;
import java.util.ArrayList;
import java.util.List;
import units.*;

public abstract class DecisionMaker {

  AttackManager attackManager = new AttackManager();

  public void reactToPlayerMovement(Unit unitMakingDecision,
                                    Mech mech,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    List<Unit> listOfAllUnits,
                                    List<Mech> listOfMechs,
                                    int roundCounter) {

    isItTimeToSwitchFeet(unitMakingDecision, roundCounter);

    if (findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      attackTarget(unitMakingDecision, findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret),listOfMechs, roundCounter);
    } else if (findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      moveTowardsTargetUnit(unitMakingDecision, findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret), listOfAllUnits, roundCounter);
    } else {
      moveTowardsTargetUnit(unitMakingDecision, turret, listOfAllUnits, roundCounter);
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

  public void moveTowardsTargetUnit(Unit unitMakingMove, Unit unitTarget, List<Unit> listOfAllUnit, int roundCounter) {
  }

  public void attackTarget(Unit unitAttacking, Unit unitTarget, List<Mech> listOfMechs, int roundCounter) {
    attackManager.attackTargetUnit(unitAttacking, unitTarget, listOfMechs, roundCounter);
  }

  public String isItTimeToSwitchFeet(Unit unitMoving, int roundCounter) {


    if (unitMoving.getSwitchFeetInRound() == roundCounter) {
      if (unitMoving.getFeetForward().equals("ODD")) {
        unitMoving.setFeetForward("EVEN");
        unitMoving.setSwitchFeetInRound(roundCounter + unitMoving.getSwitchFeetEveryXRound());
      } else {
        unitMoving.setFeetForward("ODD");
        unitMoving.setSwitchFeetInRound(roundCounter + unitMoving.getSwitchFeetEveryXRound());
      }
    }
    return unitMoving.getFeetForward();
  }

  public Waypoint followiWaypoints(Creep creep) {
    return creep.getListOfWaypointsToFollow().get(creep.getHeadingTowardsWaypoint());
  }
}
