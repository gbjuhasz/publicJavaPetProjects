package fighting;

import units.Unit;

public class MechHeroAttackManager extends AttackManager {

  @Override
  public void attackTargetUnit(Unit unitAttacking, Unit unitTargeted, int roundCounter) {
    if (unitAttacking.calculateDistanceBetweenUnits(unitTargeted) < unitAttacking.getAttackRange()) {
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
        }
      }
    }
  }
}