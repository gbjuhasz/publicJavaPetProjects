package respawn;

import java.util.ArrayList;
import units.Creep;
import units.Mech;

public class RespawnManager {

  public void respawnUnits(ArrayList<Mech> listOfMechs,
                           ArrayList<Creep> listOfCreepAllied,
                           ArrayList<Creep> lisOfCreepEnemy,
                           int roundCounter) {

    respawnMechsIfDead(listOfMechs, roundCounter);
  }

  public void respawnMechsIfDead(ArrayList<Mech> listOfMechs, int roundCounter) {
    for (Mech mech : listOfMechs) {
      if (!mech.isAlive() && roundCounter - mech.getRoundDied() == mech.getRoundsToRespawn()) {
        mech.setAlive(true);
        mech.setPosX(mech.getRespawnX());
        mech.setPosY(mech.getRespawnY());
      }
    }
  }
}
