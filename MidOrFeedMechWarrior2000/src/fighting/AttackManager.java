package fighting;

import java.util.Random;
import units.Unit;

public class AttackManager {

  Random random = new Random();

  public void attackTargetUnit(Unit unitAttacking, Unit unitTargeted){
    if(attackMissed(unitAttacking)){
      int unitTargetedHP = unitTargeted.getHealthPoints();
      int unitTargetedArmor = unitTargeted.getArmorRating();
      int unitAttackingDamage = unitAttacking.getAttackDamage();
      unitTargeted.setHealthPoints(unitTargetedHP-unitAttackingDamage+unitTargetedArmor);
      if(unitTargeted.getHealthPoints() <= 0){
        unitTargeted.setAlive(false);
        unitTargeted.setPosX(-100);
        unitTargeted.setPosY(-100);
      }
    }
  }

  public Boolean attackMissed(Unit unitAttacking){
    int randomNumber = random.nextInt(10);
    return randomNumber > unitAttacking.getMissChance();
  }
}
