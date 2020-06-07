package decisionmaking;

import java.util.ArrayList;
import java.util.List;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public class TurretDecisionMaker extends DecisionMaker {


  public void reactToPlayerMovement(Unit unitMakingDecision,
                                    Mech mech,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    List<Unit> listOfAllUnits,
                                    List<Mech> listOfMechs,
                                    int roundCounter) {

    if (findTargetInRange(unitMakingDecision, mech, listOfCreeps, turret, unitMakingDecision.getAttackRange()) != null) {
      attackTarget(unitMakingDecision, findTargetInRange(unitMakingDecision, mech, listOfCreeps, turret, unitMakingDecision.getAttackRange()), listOfMechs, roundCounter);
    }
  }
}
