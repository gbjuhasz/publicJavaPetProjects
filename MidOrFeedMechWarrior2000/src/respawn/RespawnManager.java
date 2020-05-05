package respawn;

import java.util.ArrayList;
import units.Creep;
import units.Mech;

public class RespawnManager {

  public void respawnUnits(ArrayList<Mech> listOfMechs,
                           ArrayList<Creep> listOfCreepAllied,
                           ArrayList<Creep> listOfCreepEnemy,
                           int roundCounter) {

    respawnMechsIfDead(listOfMechs, roundCounter);
    respawnCreepsIfDead(listOfCreepAllied, roundCounter);
    respawnCreepsIfDead(listOfCreepEnemy,roundCounter);
  }

  public void respawnMechsIfDead(ArrayList<Mech> listOfMechs, int roundCounter) {
    for (Mech mech : listOfMechs) {
      if (!mech.isAlive() && roundCounter - mech.getRoundDied() == mech.getRoundsToRespawn()) {
        mech.setAlive(true);
        mech.setHealthPoints(mech.getRespawnHealthPoints());
        mech.setPosX(mech.getRespawnX());
        mech.setPosY(mech.getRespawnY());
      }
    }
  }

  public void respawnCreepsIfDead(ArrayList<Creep> listOfCreeps, int roundCounter){
    for(Creep creep: listOfCreeps){
      if(!creep.isAlive() &&
      roundCounter == creep.getRoundToRespawn()){
        creep.setAlive(true);
        creep.setRoundToRespawn(roundCounter + creep.getWaveSpawnTimer());
        creep.setHealthPoints(creep.getRespawnHealthPoints());
        creep.setPosX(creep.getRespawnX());
        creep.setPosY(creep.getRespawnY());
      }
    }
  }
}
