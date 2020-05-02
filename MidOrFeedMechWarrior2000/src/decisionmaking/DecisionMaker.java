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

  }

  public Unit findTargetInAttackRange(Unit unitMakingDecision,
                                      Mech mech,
                                      ArrayList<Creep> listOfCreeps,
                                      Turret turret){

    int attackRange = unitMakingDecision.getAttackRange();

    if(mech.isThreatToHeroUnit() && attackRange > unitMakingDecision.calculateDistanceBetweenUnits())

      return null;
  }
}
