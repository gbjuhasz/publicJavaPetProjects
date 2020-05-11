package mechherocontrol;

import fighting.AttackManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
        setFacingDirection(unitAttacking,unitTargeted);
        findImageFileLocation(unitAttacking.getFacingDirection(), isRoundNumberEven(roundCounter));
        BufferedImage newImage = pickImage(findImageFileLocation(unitAttacking.getFacingDirection(),
                isRoundNumberEven(roundCounter)));
        unitAttacking.setImage(newImage);

        if (unitTargeted.getHealthPoints() <= 0) {
          unitTargeted.setAlive(false);
          unitTargeted.setRoundDied(roundCounter);
          unitTargeted.setPosX(-100);
          unitTargeted.setPosY(-100);
        }
      }
    }
  }

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechhero/MechHeroAttacking" + facingDirection + roundEvenOrOdd + ".png";
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