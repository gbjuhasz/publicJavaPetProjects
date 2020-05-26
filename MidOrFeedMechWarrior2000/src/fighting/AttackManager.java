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
      unitAttacking.setRoundAttackedLastTime(roundCounter);
      unitAttacking.setRoundAttackNextTime(unitAttacking.getRoundAttackedLastTime() + unitAttacking.getRoundsPerAttack());
      if (attackHit(unitAttacking)) {
        int unitTargetedHP = unitTargeted.getHealthPoints();
        int unitTargetedArmor = unitTargeted.getArmorRating();
        int unitAttackingDamage = unitAttacking.getAttackDamage();
        unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage + unitTargetedArmor);
        unitAttacking.setLastAttackResult("Did"+(unitAttackingDamage + unitTargetedArmor)+"damage!");
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

  public Boolean attackHit(Unit unitAttacking) {
    int randomNumber = random.nextInt(10);
    return randomNumber > unitAttacking.getMissChance();
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
