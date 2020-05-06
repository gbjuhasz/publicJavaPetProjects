package visualeffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import units.Unit;

public class HUD {

  public ArrayList<Integer> findCrosshairCoordinates(Unit unit) {
    ArrayList<Integer> crosshairCoordinates = new ArrayList<>();
    crosshairCoordinates.add(unit.getPosX());
    crosshairCoordinates.add(unit.getPosY());
    crosshairCoordinates.add(72);
    return crosshairCoordinates;
  }
}
