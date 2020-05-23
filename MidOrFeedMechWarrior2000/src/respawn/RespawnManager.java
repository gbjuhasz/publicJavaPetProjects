package respawn;

import java.util.ArrayList;
import units.Creep;
import units.Mech;
import units.MechHero;

public class RespawnManager {

  public void respawnUnits(MechHero mechHero,
                           Mech mechEnemy,
                           ArrayList<Creep> listOfCreepAllied,
                           ArrayList<Creep> listOfCreepEnemy,
                           int roundCounter) {

    respawnMechHeroIfDead(mechHero, roundCounter);
    respawnMechEnemyIfDead(mechEnemy, roundCounter);
    respawnCreepsIfDead(listOfCreepAllied, roundCounter);
    respawnCreepsIfDead(listOfCreepEnemy,roundCounter);
    setRespawnTimes(listOfCreepAllied, roundCounter);
    setRespawnTimes(listOfCreepEnemy, roundCounter);
  }

  public void respawnMechHeroIfDead(MechHero mechHero, int roundCounter){
    if (!mechHero.isAlive() && roundCounter - mechHero.getRoundDied() == mechHero.getRoundsToRespawn()) {
      mechHero.setAlive(true);
      mechHero.setStunned(false);
      mechHero.setUnitTargeted(null);
      mechHero.setMouseEventMarkingLocation(null);
      mechHero.setHealthPoints(mechHero.getRespawnHealthPoints());
      mechHero.setPosX(mechHero.getRespawnX());
      mechHero.setPosY(mechHero.getRespawnY());
      mechHero.setSwitchFeetInRound(roundCounter+mechHero.getSwitchFeetEveryXRound());
    }
  }

  public void respawnMechEnemyIfDead(Mech mech, int roundCounter) {

      if (!mech.isAlive() && roundCounter - mech.getRoundDied() == mech.getRoundsToRespawn()) {
        mech.setAlive(true);
        mech.setStunned(false);
        mech.setHealthPoints(mech.getRespawnHealthPoints());
        mech.setPosX(mech.getRespawnX());
        mech.setPosY(mech.getRespawnY());
        mech.setSwitchFeetInRound(roundCounter+mech.getSwitchFeetEveryXRound());
      }
  }

  public void respawnCreepsIfDead(ArrayList<Creep> listOfCreeps, int roundCounter){
    for(Creep creep: listOfCreeps){
      if(!creep.isAlive() &&
      roundCounter == creep.getRoundToRespawn()){
        creep.setAlive(true);
        creep.setStunned(false);
        creep.setHealthPoints(creep.getRespawnHealthPoints());
        creep.setPosX(creep.getRespawnX());
        creep.setPosY(creep.getRespawnY());
        creep.setSwitchFeetInRound(roundCounter+creep.getSwitchFeetEveryXRound());
        creep.setHeadingTowardsWaypoint(0);
      }
    }
  }

  public void setRespawnTimes(ArrayList<Creep> listOfCreeps, int roundcounter){
    for (Creep creep : listOfCreeps){
      if(roundcounter == creep.getRoundToRespawn()){
        creep.setRoundToRespawn(creep.getRoundToRespawn()+creep.getWaveSpawnTimer()*2);
      }
    }
  }
}
