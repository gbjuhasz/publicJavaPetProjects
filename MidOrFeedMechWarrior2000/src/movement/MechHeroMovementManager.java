package movement;

import java.awt.image.BufferedImage;
import java.util.List;
import units.MechHero;
import units.Unit;

public class MechHeroMovementManager extends BotMovementManager {

  StuckUnitManager stuckUnitManager = new StuckUnitManager();

  public void moveUnitTowardsMouseEvent(MechHero mechHero,
                                        List<Unit> listOfAllUnits,
                                        int roundCounter) {

    if (mechHero.getUnitTargeted() != null || mechHero.getMouseEventMarkingLocation() != null) {

      mechHero.calculateMouseEventDirection();
      String targetDirection = mechHero.getTargetDirection();

      changeCoordinatesTowardsTargetDirection(mechHero, listOfAllUnits, targetDirection);
      if(!illegalMoveMapObjectsManager.isMoveLegal(mechHero.getPosX(),mechHero.getPosY())){
        mechHero.setPosX(mechHero.getPreviousX());
        mechHero.setPosY(mechHero.getPreviousY());
        stuckUnitManager.helpIfUnitIsStuck(mechHero);
      }
      checkAndSetFacingDirection(mechHero);
      mechHero.calculateImageCenterCoordinates();
      BufferedImage newImage = pickImage(findImageFileLocation(mechHero.getFacingDirection(),
              isItTimeToSwitchFeet(mechHero,roundCounter)));
      mechHero.setImage(newImage);
    }
  }

  @Override
  public String findImageFileLocation(String facingDirection, String roundEvenOrOdd) {

    String fileLocation = "images/mechhero/MechHero" + facingDirection + roundEvenOrOdd + ".png";
    return fileLocation;
  }
}
