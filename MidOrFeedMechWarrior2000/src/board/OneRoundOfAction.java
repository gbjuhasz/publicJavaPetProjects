package board;

import decisionmaking.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import units.*;

public class OneRoundOfAction {

  CreepAlliedDecisionMaker creepAlliedDecisionMaker = new CreepAlliedDecisionMaker();
  CreepEnemyDecisionMaker creepEnemyDecisionMaker = new CreepEnemyDecisionMaker();
  MechEnemyDecisionMaker mechEnemyDecisionMaker = new MechEnemyDecisionMaker();
  TurretDecisionMaker turretDecisionMaker = new TurretDecisionMaker();
  MechHeroDecisionMaker mechHeroDecisionMaker = new MechHeroDecisionMaker();

  public void performOneRoundOfAction(MechHero mechHero,
                                      MechEnemy mechEnemy,
                                      ArrayList<Creep> listOfCreepAllied,
                                      ArrayList<Creep> listOfCreepEnemy,
                                      Turret turretAllied,
                                      Turret turretEnemy,
                                      List<Unit> listOfAllUnits,
                                      List<Mech> listOfMechs,
                                      int roundCounter) {

    checkStunnedAndInvisibleUnits(listOfAllUnits, roundCounter);
    checkAggroChange(listOfAllUnits, roundCounter);

    if (mechHero.isAlive() &&
            !mechHero.isStunned()) {
      mechHeroDecisionMaker.reactToMouseCommand(mechHero,
              mechEnemy,
              listOfCreepEnemy,
              turretEnemy,
              listOfAllUnits,
              roundCounter);
    }

    for (Creep creepAllied : listOfCreepAllied) {
      if (creepAllied.isAlive() &&
              !creepAllied.isStunned()) {
        creepAlliedDecisionMaker.reactToPlayerMovement(creepAllied,
                mechHero,
                mechEnemy,
                listOfCreepEnemy,
                turretEnemy,
                listOfAllUnits,
                listOfMechs,
                roundCounter);
      }
    }
    for (Creep creepEnemy : listOfCreepEnemy) {
      if (creepEnemy.isAlive() &&
              !creepEnemy.isStunned()) {
        creepEnemyDecisionMaker.reactToPlayerMovement(creepEnemy,
                mechEnemy,
                mechHero,
                listOfCreepAllied,
                turretAllied,
                listOfAllUnits,
                listOfMechs,
                roundCounter);
      }
    }
    if (mechEnemy.isAlive() &&
            !mechEnemy.isStunned()) {
      mechEnemyDecisionMaker.reactToPlayerMovementMechEnemy(mechEnemy,mechHero,listOfCreepEnemy,listOfCreepAllied,turretEnemy,turretAllied,listOfAllUnits,listOfMechs,roundCounter);
    }


    turretDecisionMaker.reactToPlayerMovement(turretAllied,
            mechEnemy,
            listOfCreepEnemy,
            turretEnemy,
            listOfAllUnits,
            listOfMechs,
            roundCounter);

    turretDecisionMaker.reactToPlayerMovement(turretEnemy,
            mechHero,
            listOfCreepAllied,
            turretAllied,
            listOfAllUnits,
            listOfMechs,
            roundCounter);
  }

  private void checkStunnedAndInvisibleUnits(List<Unit> lisOfAllUnits, int roundCounter) {
    for (Unit unit : lisOfAllUnits
    ) {
      if (unit.isStunned() &&
              unit.getStunOverInRound() == roundCounter) {
        unit.setStunned(false);
      }
      if (unit.isInvisible() &&
              unit.getInvisibleUntilRound() == roundCounter) {
        unit.setInvisible(false);
        BufferedImage imageInvisible = null;
        try {
          imageInvisible = ImageIO.read(new File("images/mechhero/MechHero" + unit.getFacingDirection() + unit.getFeetForward() + ".png"));
        } catch (IOException e) {
          e.printStackTrace();
        }
        unit.setImage(imageInvisible);
      }
    }
  }

  /*
  private void checkAuraEffect(Mech mech, List<Creep> creeps){
  /*  if(mech.getListOfAuras().get(0).getLevel() > 0{
      for (Creep creep: creeps
           ) {
        if (mech.calculateDistanceBetweenUnits(creep) <= mech.getListOfAuras().get(0).getRange()){
          creep.
        }
      }
    }
  }*/
  private void checkAggroChange(List<Unit> listOfAllUnits, int roundCounter) {
    for (Unit unit : listOfAllUnits
    ) {
      if (unit.getAggroPriority() != 1 &&
              !unit.getUnitType().contains("turret") &&
              unit.getRoundsToAggroReset() + unit.getAggroChangedInRound() == roundCounter) {
        unit.setAggroPriority(1);
      }
    }
  }
}
