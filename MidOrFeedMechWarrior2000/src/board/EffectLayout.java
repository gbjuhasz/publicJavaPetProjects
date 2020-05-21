package board;

import java.awt.*;
import java.util.List;
import units.Unit;

public class EffectLayout {

  public EffectLayout() {
  }
/*
  public void showSuccessfulRightClickExplosions(List<Unit> listOfAllUnits, Integer roundCounter, Graphics graphics) {
    for (Unit unit : listOfAllUnits
    ) {
      if (unit.isAlive() &&
              unit.isRightClickAttackedThisRound() &&
              unit.getRightClickEffect().getDisappearsInRound() != null &&
              unit.getRightClickEffect().getDisappearsInRound() < roundCounter) {
        unit.getRightClickEffect().draw(graphics);
      }
    }
  }*/
}
