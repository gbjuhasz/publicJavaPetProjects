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
                                    Turret turret) {

    if(findTargetInAttackRange(unitMakingDecision,mech,listOfCreeps,turret) == null){
      moveTowardsDestination(unitMakingDecision, turret);
    } else {
      attackTarget(unitMakingDecision, findTargetInAttackRange(unitMakingDecision,mech,listOfCreeps,turret) );
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

  public void moveTowardsDestination(Unit unitMakingMove, Unit turret){}

  public void attackTarget(Unit unitAttacking, Unit unitTargeted){}

}
