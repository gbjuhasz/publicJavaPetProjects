package fighting;

import fighting.AttackManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.MechHero;
import units.Unit;

public class MechHeroAttackManager extends AttackManager {

  public void attackTargetUnitWithMechHero(MechHero mechHero, Unit unitTargeted, int roundCounter) {
    if (mechHero.calculateDistanceBetweenUnits(unitTargeted) < mechHero.getAttackRange()) {
      if (attackMissed(mechHero)) {
        int unitTargetedHP = unitTargeted.getHealthPoints();
        int unitTargetedArmor = unitTargeted.getArmorRating();
        int unitAttackingDamage = mechHero.getAttackDamage();
        unitTargeted.setHealthPoints(unitTargetedHP - unitAttackingDamage + unitTargetedArmor);
        setFacingDirection(mechHero,unitTargeted);
        findImageFileLocation(mechHero.getFacingDirection(), isRoundNumberEven(roundCounter));
          BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
                isRoundNumberEven(roundCounter)));
        mechHero.setImage(newImage);

        if (unitTargeted.getHealthPoints() <= 0) {
          unitTargeted.setAlive(false);
          unitTargeted.setRoundDied(roundCounter);
          unitTargeted.setPosX(-100);
          unitTargeted.setPosY(-100);
          mechHero.setUnitTargeted(null);
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