package movement;

import java.awt.image.BufferedImage;
import java.util.List;
import units.MechHero;
import units.Unit;

public class MechHeroMovementManager extends BotMovementManager {

  public void moveUnitTowardsMouseEvent(MechHero mechHero,
                                        List<Unit> listOfAllUnits,
                                        int roundCounter) {

    if (mechHero.getUnitTargeted() != null || mechHero.getMouseEventMarkingLocation() != null) {
      int currentX = mechHero.getPosX();
      int currentY = mechHero.getPosY();

      mechHero.calculateMouseEventDirection();
      String targetDirection = mechHero.getTargetDirection();

      changeCoordinatesTowardsTargetDirection(mechHero, currentX, currentY, listOfAllUnits, targetDirection);
/*      if (!illegalMoveCheckerMapObjects.isMoveLegal(mechHero.getPosX(), mechHero.getPosY())) {
        stuckUnitAssister.helpIfUnitIsStuck(mechHero);
      }*/
      mechHero.calculateImageCenterCoordinates();
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isRoundNumberEven(roundCounter)));
      mechHero.setImage(newImage);
    }
  }

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechhero/MechHero" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }
}
