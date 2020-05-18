package decisionmaking;

import java.util.ArrayList;
import java.util.List;
import movement.CreepAlliedMovementManager;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public class CreepAlliedDecisionMaker extends DecisionMaker {

  CreepAlliedMovementManager creepAlliedMovementManager = new CreepAlliedMovementManager();

  public void reactToPlayerMovement(Creep unitMakingDecision,
                                    Mech mech,
                                    ArrayList<Creep> listOfCreeps,
                                    Turret turret,
                                    List<Unit> listOfAllUnits,
                                    List<Mech> listOfMechs,
                                    int roundCounter) {

    isItTimeToSwitchFeet(unitMakingDecision,roundCounter);

    if (findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      attackTarget(unitMakingDecision, findTargetInAttackRange(unitMakingDecision, mech, listOfCreeps, turret), listOfMechs,  roundCounter);
    } else if (findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret) != null) {
      moveTowardsTargetUnit(unitMakingDecision, findTargetInDetectionRange(unitMakingDecision, mech, listOfCreeps, turret), listOfAllUnits, roundCounter);
    } else {
        moveTowardsTargetUnit(unitMakingDecision, followiWaypoints(unitMakingDecision),listOfAllUnits, roundCounter);
      if (unitMakingDecision.calculateDistanceBetweenUnits(followiWaypoints(unitMakingDecision)) < 18
              && unitMakingDecision.getHeadingTowardsWaypoint() < unitMakingDecision.getListOfWaypointsToFollow().size()) {
        unitMakingDecision.setHeadingTowardsWaypoint(unitMakingDecision.getHeadingTowardsWaypoint() + 1);
      }
    }
  }

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    Unit unitTarget,
                                    List<Unit> listOfAllUnits,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(unitTarget);
    creepAlliedMovementManager.moveUnit(unitMakingMove,
            unitTarget,
            listOfAllUnits,
            roundCounter);
  }
}
