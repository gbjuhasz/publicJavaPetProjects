package fighting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import units.Mech;
import units.MechHero;
import units.Unit;

public class MechHeroAttackManager extends AttackManager {

  public void attackTargetUnitWithMechHero(MechHero mechHero, Unit unitTargeted, int roundCounter) {
    setFacingDirection(mechHero, unitTargeted);
    if (mechHero.getRoundAttackNextTime() <= roundCounter) {
      if(mechHero.isInvisible()){
        mechHero.setInvisible(false);
      }
      mechHero.setRoundAttackedLastTime(roundCounter);
      changeAggroPriorityAccordingToTarget(mechHero,unitTargeted,roundCounter);
      mechHero.setRoundAttackNextTime(mechHero.getRoundAttackedLastTime() + mechHero.getRoundsPerAttack());
      if (mechHero.calculateDistanceBetweenUnits(unitTargeted) < mechHero.getAttackRange()) {
        if (attackHit(mechHero, unitTargeted)) {
          int unitTargetedHP = unitTargeted.getHealthPoints();
          int unitTargetedArmor = unitTargeted.getArmorRating();
          int unitAttackingDamage = mechHero.getAttackDamage();
          if(isCriticalHit(mechHero)) {
            unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage * 2 + unitTargetedArmor);
            if(mechHero.getLifeLeechPercentage() != 0){
              stealHealth(mechHero, unitAttackingDamage*2-unitTargetedArmor);
            }
            mechHero.setLastAttackResult((unitAttackingDamage * 2 + unitTargetedArmor)+"CRITICAL damage!");
          } else {
            unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage + unitTargetedArmor);
            if(mechHero.getLifeLeechPercentage() != 0){
              stealHealth(mechHero, unitAttackingDamage - unitTargetedArmor);
            }
            mechHero.setLastAttackResult("Did"+(unitAttackingDamage + unitTargetedArmor)+"damage!");
          }
          mechHero.setRightClickAttackedThisRound(true);
          findImageFileLocation(mechHero.getFacingDirection(), isRoundNumberEven(roundCounter));
          BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
                  isRoundNumberEven(roundCounter)));
          mechHero.setImage(newImage);

          if (unitTargeted.getHealthPoints() <= 0) {
            unitTargeted.setHighlighted(false);
            mechHero.setUnitTargeted(null);
            mechHero.setMouseEventMarkingLocation(null);
            List<Mech> mechHeroInListForm = new ArrayList<>();
            mechHeroInListForm.add(mechHero);
            levelUpManager.grantXpToMechs(unitTargeted,mechHeroInListForm);
            unitTargeted.setAlive(false);
            unitTargeted.setRoundDied(roundCounter);
            unitTargeted.setPosX(-100);
            unitTargeted.setPosY(-100);
          }
        } else {
          mechHero.setLastAttackResult("Miss!");
          mechHero.setRightClickAttackedThisRound(true);
        }
      }
    }
  }

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechhero/MechHero" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }

  public String isRoundNumberEven(int roundCounter) {

    if (roundCounter % 2 == 0) {
      return "Even";
    } else {
      return "Odd";
    }
  }



  public BufferedImage pickImage(String fileLocation) {

    BufferedImage image = null;

    try {
      image = ImageIO.read(new File(fileLocation));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
