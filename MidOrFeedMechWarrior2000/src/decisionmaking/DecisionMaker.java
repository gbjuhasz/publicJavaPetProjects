package decisionmaking;

import java.util.ArrayList;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public abstract class DecisionMaker {

  public void reactToPlayerMovement(Unit unitMakingDecision,
                                    Mech mech,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    int roundCounter) {

    if(findTargetInAttackRange(unitMakingDecision,mech,listOfCreeps,turret) != null){
      attackTarget(unitMakingDecision,findTargetInAttackRange(unitMakingDecision,mech,listOfCreeps,turret));
    } else if (findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      moveTowardsTargetUnit(unitMakingDecision,findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret));
    } else {
      moveTowardsTargetUnit(unitMakingDecision,turret);
    }
  }

  public Unit findTargetInAttackRange(Unit unitMakingDecision,
                                      Mech mech,
                                      ArrayList<Creep> listOfCreeps,
                                      Turret turret){

    int attackRange = unitMakingDecision.getAttackRange();

    if(mech.isThreatToHeroUnit()
            && unitMakingDecision.calculateDistanceBetweenUnits(mech) <= attackRange){
      return mech;
    }
    for(Creep creep : listOfCreeps){
      if(unitMakingDecision.calculateDistanceBetweenUnits(creep) <= attackRange) {
        return creep;
      }
    }
    if(!mech.isThreatToHeroUnit()
            && unitMakingDecision.calculateDistanceBetweenUnits(mech) <= attackRange){
      return mech;
    } else if (unitMakingDecision.calculateDistanceBetweenUnits(turret)<= attackRange){
      return turret;
    }
    return null;
  }

  public Unit findTargetInDetectionRange(Unit unitMakingDecision,
                                      Mech mech,
                                      ArrayList<Creep> listOfCreeps,
                                      Turret turret){

    int detectionkRange = unitMakingDecision.getDetectionRange();

    if(mech.isThreatToHeroUnit()
            && unitMakingDecision.calculateDistanceBetweenUnits(mech) <= detectionkRange){
      return mech;
    }
    for(Creep creep : listOfCreeps){
      if(unitMakingDecision.calculateDistanceBetweenUnits(creep) <= detectionkRange) {
        return creep;
      }
    }
    if(!mech.isThreatToHeroUnit()
            && unitMakingDecision.calculateDistanceBetweenUnits(mech) <= detectionkRange){
      return mech;
    } else if (unitMakingDecision.calculateDistanceBetweenUnits(turret)<= detectionkRange){
      return turret;
    }
    return null;
  }

  public void moveTowardsTargetUnit(Unit unitMakingMove, Unit unitTarget){}

  public void attackTarget(Unit unitAttacking, Unit unitTarget){}

}
