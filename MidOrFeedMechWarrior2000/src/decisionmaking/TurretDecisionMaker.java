package decisionmaking;

import java.util.ArrayList;
import java.util.List;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public class TurretDecisionMaker extends DecisionMaker {


  @Override
  public void reactToPlayerMovement(Unit unitMakingDecision,
                                    Mech mech,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    List<Unit> listOfAllUnits,
                                    List<Mech> listOfMechs,
                                    int roundCounter) {

    if (findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      attackTarget(unitMakingDecision, findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret), listOfMechs, roundCounter);
    }
  }

  @Override
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
    }
    return null;
  }
}
