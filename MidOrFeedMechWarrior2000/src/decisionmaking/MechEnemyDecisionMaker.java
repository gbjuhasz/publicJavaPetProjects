package decisionmaking;

import abilities.ActiveAbility;
import board.BoardComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import movement.MechEnemyMovementManager;
import units.Creep;
import units.Mech;
import units.Turret;
import units.Unit;

public class MechEnemyDecisionMaker extends DecisionMaker {

  MechEnemyMovementManager mechEnemyMovementManager = new MechEnemyMovementManager();
  Random random = new Random();

  public void reactToPlayerMovementMechEnemy(Mech mechEnemy,
                                             Mech mechHero,
                                             List<Creep> listOfCreepsEnemyWithDeadCreepsIncluded,
                                             List<Creep> listOfCreepsAlliedWithDeadCreepsIncluded,
                                             Turret turretEnemy,
                                             Turret turretAllied,
                                             List<Unit> listOfAllUnits,
                                             List<Mech> listOfMechs,
                                             int roundCounter) {

    ArrayList<Creep> listOfCreepsAllied = cleanListOfCreeps(listOfCreepsAlliedWithDeadCreepsIncluded);
    ArrayList<Creep> listOfCreepsEnemy = cleanListOfCreeps(listOfCreepsEnemyWithDeadCreepsIncluded);

    isItTimeToSwitchFeet(mechEnemy, roundCounter);
    checkIfStillHasTarget(mechEnemy);
    placeDamageAndArmorAuraEffectsOnUnit(mechEnemy, mechEnemy);
    calculatePowerScoresOfMechs(mechEnemy, mechHero, listOfCreepsEnemy, listOfCreepsAllied, turretEnemy, turretAllied, roundCounter);
    setIdealBehaviorBasedOnPowerScores(mechEnemy, mechHero, listOfCreepsEnemy, listOfCreepsAllied, turretEnemy, turretAllied, roundCounter);
    setTargetUnitBasedOnIdealBehavior(mechEnemy,mechHero,listOfCreepsAllied,turretEnemy,turretAllied,roundCounter);
    if(mechEnemy.getUnitTargeted().getUnitType().equals("turretEnemy")){
      moveTowardsTargetUnit(mechEnemy,mechEnemy.getUnitTargeted(),listOfAllUnits, roundCounter);
    } else if (mechEnemy.calculateDistanceBetweenUnits(mechEnemy.getUnitTargeted()) <= mechEnemy.getAttackRange()){
      attackManager.attackTargetUnit(mechEnemy,mechEnemy.getUnitTargeted(), listOfMechs,roundCounter);
    } else {
      moveTowardsTargetUnit(mechEnemy,mechEnemy.getUnitTargeted(),listOfAllUnits, roundCounter);
    }
    removeAuraEffects(mechEnemy, mechEnemy);
  }

  @Override
  public void moveTowardsTargetUnit(Unit unitMakingMove,
                                    BoardComponent boardComponent,
                                    List<Unit> listOfAllUnits,
                                    int roundCounter) {
    unitMakingMove.calculateTargetDirection(boardComponent);
    mechEnemyMovementManager.moveUnit(unitMakingMove,
            boardComponent,
            listOfAllUnits,
            roundCounter);
  }

  public void calculatePowerScoresOfMechs(Mech mechEnemy,
                                          Mech mechHero,
                                          ArrayList<Creep> listOfCreepsEnemy,
                                          ArrayList<Creep> listOfCreepsAllied,
                                          Turret turretEnemy,
                                          Turret turretAllied,
                                          int roundCounter) {
    calculatePowerScore(mechEnemy, mechHero, listOfCreepsEnemy, turretEnemy, roundCounter);
    calculatePowerScore(mechHero, mechEnemy, listOfCreepsAllied, turretAllied, roundCounter);
  }

  public int calculatePotentialDamageWithActiveAbilities(Mech mech, Mech mechHostile, int roundCounter) {
    int potentialMaxDamage = 0;
    ActiveAbility aoe = mech.getListOfActiveAbilities().get(1);
    ActiveAbility nuke = mech.getListOfActiveAbilities().get(3);


    if (nuke.getLevel() > 0 &&
            nuke.getCanBeusedAgainInRound() <= roundCounter &&
            aoe.getLevel() > 0 &&
            aoe.getCanBeusedAgainInRound() <= roundCounter &&
            mech.getEnergy() >= aoe.getEnergyCost() + nuke.getEnergyCost()) {
      potentialMaxDamage = potentialMaxDamage + aoe.getDamage() + nuke.getDamage();
    } else if (nuke.getLevel() == 0 &&
            aoe.getLevel() > 0 &&
            aoe.getCanBeusedAgainInRound() <= roundCounter &&
            mech.getEnergy() >= aoe.getEnergyCost()) {
      potentialMaxDamage = potentialMaxDamage + aoe.getDamage();
    } else if (nuke.getLevel() > 0 &&
            aoe.getLevel() == 0 &&
            nuke.getCanBeusedAgainInRound() <= roundCounter &&
            mech.getEnergy() >= nuke.getEnergyCost()) {
      potentialMaxDamage = potentialMaxDamage + nuke.getDamage();
    } else if (nuke.getLevel() > 0 &&
            nuke.getCanBeusedAgainInRound() <= roundCounter &&
            aoe.getLevel() > 0 &&
            aoe.getCanBeusedAgainInRound() <= roundCounter &&
            mech.getEnergy() >= nuke.getEnergyCost() &&
            mech.getEnergy() < aoe.getEnergyCost() + nuke.getEnergyCost()) {
      potentialMaxDamage = potentialMaxDamage + nuke.getDamage();
    } else if (nuke.getLevel() > 0 &&
            nuke.getCanBeusedAgainInRound() <= roundCounter &&
            aoe.getLevel() > 0 &&
            aoe.getCanBeusedAgainInRound() <= roundCounter &&
            mech.getEnergy() >= aoe.getEnergyCost() &&
            mech.getEnergy() < aoe.getEnergyCost() + nuke.getEnergyCost() &&
            mech.getEnergy() < nuke.getEnergyCost()) {
      potentialMaxDamage = potentialMaxDamage + aoe.getDamage();
    }
    return potentialMaxDamage;
  }

  public int calculatePotentialMaxDamageWithAutoAttacks(Mech mech, Mech mechHostile, int roundCounter) {

    double potentialRightClickDamage = mech.getAttackDamage() + (mech.getCritChance() / 10.0) * mech.getAttackDamage();

    if (mechHostile.getEvasionChance() > 0) {
      potentialRightClickDamage = potentialRightClickDamage * (mechHostile.getEvasionChance() / 10.0);
    }
    potentialRightClickDamage = potentialRightClickDamage - mechHostile.getArmorRating();
    return (int) Math.round(potentialRightClickDamage);
  }

  public int calculatePotentialDamageWithCreepsBeforeAggroReset(Mech mechHostile, List<Creep> listOfCreepsAllied, int roundCounter) {
    double potentialDamageFromCreeps = 0;

    for (Creep creep : listOfCreepsAllied
    ) {
      if (mechHostile.calculateDistanceBetweenUnits(creep) <= 50) {
        potentialDamageFromCreeps = potentialDamageFromCreeps + creep.getAttackDamage() * 2 - mechHostile.getArmorRating() * 2;
      } else if (mechHostile.calculateDistanceBetweenUnits(creep) <= 249) {
        potentialDamageFromCreeps = potentialDamageFromCreeps + creep.getAttackDamage() * 2 - mechHostile.getArmorRating() * 2;
      }
    }
    if (mechHostile.getEvasionChance() > 0) {
      potentialDamageFromCreeps = potentialDamageFromCreeps * (mechHostile.getEvasionChance() / 10.0);
    }
    return (int) Math.round(potentialDamageFromCreeps);
  }

  public int calculateMaxDamageWithTurretBeforeAggroReset(Mech mechHostile, Turret turretAllied) {
    double potentialTurretDamage = 0;
    if (mechHostile.calculateDistanceBetweenUnits(turretAllied) <= turretAllied.getAttackRange()) {
      potentialTurretDamage = potentialTurretDamage + turretAllied.getAttackDamage() - mechHostile.getArmorRating();
    }
    if (mechHostile.getEvasionChance() > 0) {
      potentialTurretDamage = potentialTurretDamage * (mechHostile.getEvasionChance() * 10.0);
    }
    return (int) Math.round(potentialTurretDamage);
  }

  public int calculateMaxEffectiveHPBeforeAggroReset(Mech mech, Mech mechHostile) {
    int effectiveHP = mech.getHealthPoints();
    int lifeLeechInOneAutoAttack = (int) Math.round((mech.getAttackDamage() - mechHostile.getArmorRating()) * (mech.getLifeLeechPercentage() / 10.0));
    if (mech.calculateDistanceBetweenUnits(mechHostile) <= 50) {
      effectiveHP = effectiveHP + lifeLeechInOneAutoAttack * 2;
    } else if (mech.calculateDistanceBetweenUnits(mechHostile) <= 249) {
      effectiveHP = effectiveHP + lifeLeechInOneAutoAttack;
    }
    return effectiveHP;
  }

  public void calculatePowerScore(Mech mech, Mech mechHostile, List<Creep> listOfCreepsAllied, Turret turretAllied,
                                  int roundCounter) {
    double enemyEffectiveHP = calculateMaxEffectiveHPBeforeAggroReset(mechHostile, mech);
    int activeDamage = calculatePotentialDamageWithActiveAbilities(mech, mechHostile, roundCounter);
    int autoAttackDamage= calculatePotentialMaxDamageWithAutoAttacks(mech, mechHostile, roundCounter);
    int creepDamage = calculatePotentialDamageWithCreepsBeforeAggroReset(mechHostile, listOfCreepsAllied, roundCounter);
    int turretDamage = calculateMaxDamageWithTurretBeforeAggroReset(mechHostile, turretAllied);
    int potentialDamage = activeDamage +
            autoAttackDamage +
            creepDamage + turretDamage;
    double powerScore = enemyEffectiveHP / potentialDamage;
    mech.setPowerScore((int) Math.round(powerScore));
  }

  public void setIdealBehaviorBasedOnPowerScores(Mech mechEnemy,
                                                 Mech mechHero,
                                                 ArrayList<Creep> listOfCreepsEnemy,
                                                 ArrayList<Creep> listOfCreepsAllied,
                                                 Turret turretEnemy,
                                                 Turret turretAllied,
                                                 int roundCounter) {

    if (mechEnemy.isAlive()) {

      int mechEnemyPowerScore = mechEnemy.getPowerScore();
      int mechHeroPowerScore = mechHero.getPowerScore();
      int powerScoreDifference = mechHeroPowerScore - mechEnemyPowerScore;

      if (!mechHero.isAlive()) {
        if (listOfCreepsAllied.size() > 0) {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Shove wave");
        } else {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Hit tower");
        }
      } else {
        if (powerScoreDifference >= -1 &&
                powerScoreDifference <= 1 &&
                listOfCreepsAllied.size() > 0) {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Trade hits or last hit");
        } else if (powerScoreDifference >= -1 &&
                powerScoreDifference <= 1 &&
                listOfCreepsAllied.size() == 0) {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Trade hits");
        } else if (powerScoreDifference < -1 &&
                listOfCreepsEnemy.size() > 0 &&
                mechEnemy.calculateDistanceBetweenUnits(mechHero) <= mechHero.getAttackRange()) {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Retreat");
        } else if (powerScoreDifference > 1) {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Try to set up kill");
        } else if (powerScoreDifference > 4) {
          mechEnemy.setIdealBehaviorBasedOnPowerScores("Go for kill");
        }
      }
    }
  }

  public void setTargetUnitBasedOnIdealBehavior(Mech mechEnemy, Mech mechHero, List<Creep> listOfCreepAllied, Turret turretEnemy, Turret turretAllied, int roundCounter) {
    String idealAction = mechEnemy.getIdealBehaviorBasedOnPowerScores();
    if (idealAction.equals("Shove wave")) {
      mechEnemy.setUnitTargeted(findClosestCreep(mechEnemy, listOfCreepAllied));
    } else if (idealAction.equals("Hit tower")) {
      mechEnemy.setUnitTargeted(turretAllied);
    } else if (idealAction.equals("Trade hits or last hit")) {
      if (mechEnemy.calculateDistanceBetweenUnits(mechHero) <= mechEnemy.getAttackRange()) {
        mechEnemy.setUnitTargeted(mechHero);
      } else if (findWeakestCreep(mechEnemy,listOfCreepAllied) != null &&
              mechEnemy.calculateDistanceBetweenUnits(findWeakestCreep(mechEnemy, listOfCreepAllied)) <= mechEnemy.getAttackRange()) {
        mechEnemy.setUnitTargeted(findWeakestCreep(mechEnemy, listOfCreepAllied));
      } else {
        int randomNumber = random.nextInt(100);
        if (randomNumber % 2 == 0 &&
                listOfCreepAllied.size()>1) {
          mechEnemy.setUnitTargeted(findWeakestCreep(mechEnemy, listOfCreepAllied));
        } else {
          mechEnemy.setUnitTargeted(mechHero);
        }
      }
    } else if (idealAction.equals("Retreat")) {
      mechEnemy.setUnitTargeted(turretEnemy);
    } else if (idealAction.equals("Trade hits") ||
            idealAction.equals("Try to set up kill") ||
            idealAction.equals("Go for kill")) {
      mechEnemy.setUnitTargeted(mechHero);
    }
  }

  public Creep findClosestCreep(Mech mech, List<Creep> listOfCreeps) {
    Creep targetCreep = listOfCreeps.get(0);
    double distanceOfTargetCreep = mech.calculateDistanceBetweenUnits(targetCreep);
    for (Creep creep : listOfCreeps
    ) {
      if (mech.calculateDistanceBetweenUnits(creep) < distanceOfTargetCreep) {
        targetCreep = creep;
        distanceOfTargetCreep = mech.calculateDistanceBetweenUnits(targetCreep);
      }
    }
    return targetCreep;
  }

  public Creep findWeakestCreep(Mech mech, List<Creep> listOfCreeps) {
    if(listOfCreeps.size() > 1) {
      Creep targetCreep = listOfCreeps.get(0);
      int targetCreepHealth = listOfCreeps.get(0).getHealthPoints();
      for (Creep creep : listOfCreeps
      ) {
        if (creep.getHealthPoints() < targetCreepHealth) {
          targetCreep = creep;
          targetCreepHealth = creep.getHealthPoints();
        }
      }
      return targetCreep;
    } else {
      return null;
    }

  }

  public ArrayList<Creep> cleanListOfCreeps(List<Creep> listOfCreeps){
    ArrayList<Creep> listOfCreepsClean = new ArrayList<>();
    for (Creep creep: listOfCreeps
         ) {
      if(creep.isAlive()){
        listOfCreepsClean.add(creep);
      }
    }
    return  listOfCreepsClean;
  }
}
