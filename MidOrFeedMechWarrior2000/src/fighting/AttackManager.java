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
    if (attackMissed(unitAttacking)) {
      int unitTargetedHP = unitTargeted.getHealthPoints();
      int unitTargetedArmor = unitTargeted.getArmorRating();
      int unitAttackingDamage = unitAttacking.getAttackDamage();
      unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage + unitTargetedArmor);
      if (unitTargeted.getHealthPoints() <= 0) {
        unitTargeted.setAlive(false);
        unitTargeted.setRoundDied(roundCounter);
        unitTargeted.setPosX(-100);
        unitTargeted.setPosY(-100);
        levelUpManager.grantXpToMechs(unitTargeted,listOfMechs);
      }
    }
  }

  public Boolean attackMissed(Unit unitAttacking) {
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
