package fighting;

import java.util.List;
import java.util.Random;
import units.Mech;
import units.Unit;

public class AttackManager {

  Random random = new Random();

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
        grantXpToMechs(unitTargeted,listOfMechs);
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

  public void grantXpToMechs(Unit unitTargeted, List<Mech> listOfMechs) {

    int xp = 50;

    if (unitTargeted.getUnitType().contains("creep")) {
      if (unitTargeted.getUnitType().contains("Enemy")) {
        for (int i = 0; i < listOfMechs.size(); i++) {
          if (listOfMechs.get(i).getUnitType().contains("Hero")) {
            listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + xp);
          }
        }
      } else {
        for (int i = 0; i < listOfMechs.size(); i++) {
          if (listOfMechs.get(i).getUnitType().contains("Enemy")) {
            listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + xp);
          }
        }
      }
    }

    if (unitTargeted.getUnitType().equals("mechEnemy")) {
      for (int i = 0; i < listOfMechs.size(); i++) {
        if (listOfMechs.get(i).getUnitType().contains("Hero")) {
          listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + 70 * unitTargeted.getLevel());
        }
      }
    }else if(unitTargeted.getUnitType().equals("mechHero")){
      for (int i = 0; i < listOfMechs.size(); i++) {
        if (listOfMechs.get(i).getUnitType().contains("mechEnemy")) {
          listOfMechs.get(i).setExperiencePoints(listOfMechs.get(i).getExperiencePoints() + 70 * unitTargeted.getLevel());
        }
      }
    }
  }
}
