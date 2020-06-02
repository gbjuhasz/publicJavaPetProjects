package fighting;

import experiencesystem.LevelUpManager;
import java.util.List;
import java.util.Random;
import units.Mech;
import units.Unit;

public class AttackManager {

  Random random = new Random();
  LevelUpManager levelUpManager = new LevelUpManager();

  public void attackTargetUnit(Unit unitAttacking, Unit unitTargeted, List<Mech> listOfMechs, int roundCounter) {
    setFacingDirection(unitAttacking, unitTargeted);
    if (unitAttacking.getRoundAttackNextTime() <= roundCounter) {
      if(unitAttacking.isInvisible()){
        unitAttacking.setInvisible(false);
      }
      unitAttacking.setRoundAttackedLastTime(roundCounter);
      unitAttacking.setRoundAttackNextTime(unitAttacking.getRoundAttackedLastTime() + unitAttacking.getRoundsPerAttack());
      if (attackHit(unitAttacking, unitTargeted)) {
        int unitTargetedHP = unitTargeted.getHealthPoints();
        int unitTargetedArmor = unitTargeted.getArmorRating();
        int unitAttackingDamage = unitAttacking.getAttackDamage();
        if(isCriticalHit(unitAttacking)) {
          unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage * 2 + unitTargetedArmor);
          unitAttacking.setLastAttackResult((unitAttackingDamage * 2 + unitTargetedArmor)+"CRITICAL damage!");
        } else {
          unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage + unitTargetedArmor);
          unitAttacking.setLastAttackResult("Did"+(unitAttackingDamage + unitTargetedArmor)+"damage!");
        }
        unitAttacking.setUnitTargeted(unitTargeted);
        unitAttacking.setRightClickAttackedThisRound(true);
        if (unitTargeted.getHealthPoints() <= 0) {
          unitTargeted.setAlive(false);
          unitTargeted.setHighlighted(false);
          levelUpManager.grantXpToMechs(unitTargeted, listOfMechs);
          unitTargeted.setRoundDied(roundCounter);
          unitTargeted.setPosX(-100);
          unitTargeted.setPosY(-100);
        }
      } else {
        unitAttacking.setLastAttackResult("Miss!");
        unitAttacking.setRightClickAttackedThisRound(true);
      }
    }
  }

  public Boolean attackHit(Unit unitAttacking, Unit unitAttacked) {
    int randomNumber = random.nextInt(10);
    return randomNumber > unitAttacking.getMissChance() + unitAttacked.getEvasionChance();
  }

  public Boolean isCriticalHit(Unit unitAttacking){
    int randomNumber = random.nextInt(10);
    return randomNumber > unitAttacking.getCritChance();
  }

  public void stealHealth(Mech mech, int damage){
    if(mech.getHealthPoints() < mech.getRespawnHealthPoints()) {
      int lifeLeech = mech.getLifeLeechPercentage();
      double lifeLeechPercentage = lifeLeech / 10.0;
      long healthStolen = Math.round(damage * lifeLeechPercentage);
      if(mech.getHealthPoints() + (int) healthStolen <= mech.getRespawnHealthPoints()) {
        mech.setHealthPoints(mech.getHealthPoints() + (int) healthStolen);
      } else {
        mech.setHealthPoints(mech.getRespawnHealthPoints());
      }
    }
  }

  public void setImageForAttacks(Unit unitAttacking, Integer roundCounter) {

  }

  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/";
    return fileLocation;
  }

  public void setFacingDirection(Unit unitAttacking, Unit unitTargeted) {
    Integer unitAttackingX = unitAttacking.getPosX();
    Integer unitAttackingY = unitAttacking.getPosY();
    Integer unitTargetedX = unitTargeted.getPosX();
    Integer unitTargetedY = unitTargeted.getPosY();
    if (unitAttackingX > unitTargetedX) {
      unitAttacking.setFacingDirection("Left");
    } else if (unitAttackingX < unitTargetedX) {
      unitAttacking.setFacingDirection("Right");
    } else {
      if (unitAttackingY < unitTargetedY) {
        unitAttacking.setFacingDirection("Down");
      } else {
        unitAttacking.setFacingDirection("Up");
      }
    }
  }
}
