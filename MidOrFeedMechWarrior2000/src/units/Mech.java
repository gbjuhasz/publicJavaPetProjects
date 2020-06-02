package units;

import abilities.MechAbility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Mech extends Unit {
  //fields for fighting
  private int attackRange = 144;
  private int attackDamage = 55;
  private int healthPoints = 800;
  private int respawnHealthPoints = 800;
  private int energy = 350;
  private int respawnEnergy = 350;
  private int armorRating = 1;
  private int missChance = 1;
  private int roundsToRespawn = 1000;
  private int availableLevelUpPoints = 2;
  //fields for levelling
  private HashMap<Integer, Integer> xpBounty = new HashMap() {{
    put(1, 70);
    put(2, 118);
    put(3, 180);
    put(4, 255);
    put(5, 333);
    put(6, 427);
  }};
  //fields for decision making


  @Override
  public int getArmorRating() {
    return this.armorRating;
  }

  @Override
  public int getHealthPoints() {
    return this.healthPoints;
  }

  @Override
  public int getAttackDamage() {
    return this.attackDamage;
  }

  @Override
  public int getAttackRange() {
    return this.attackRange;
  }


  @Override
  public int getMissChance() {
    return this.missChance;
  }

  @Override
  public int getRespawnHealthPoints() {
    return this.respawnHealthPoints;
  }

  public int getRoundsToRespawn() {
    return this.roundsToRespawn;
  }

  public List<MechAbility> getAllAbilities(){
    List<MechAbility> completeListOfAbilities = new ArrayList<>();
    completeListOfAbilities.addAll(getListOfActiveAbilities());
    completeListOfAbilities.addAll(getListOfPassiveAbilities());
    completeListOfAbilities.addAll(getListOfAuras());
    return completeListOfAbilities;
  }

  public int getAvailableLevelUpPoints() {
    return availableLevelUpPoints;
  }

  public void setAvailableLevelUpPoints(int availableLevelUpPoints) {
    this.availableLevelUpPoints = availableLevelUpPoints;
  }

  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  @Override
  public HashMap<Integer, Integer> getXpBounty() {
    return xpBounty;
  }

  @Override
  public int getRespawnEnergy() {
    return this.respawnEnergy;
  }

  @Override
  public int getEnergy() {
    return this.energy;
  }

  @Override
  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public void setRoundsToRespawn(int roundsToRespawn) {
    this.roundsToRespawn = roundsToRespawn;
  }

  @Override
  public void setAttackDamage(int attackDamage) {
    this.attackDamage = attackDamage;
  }

  @Override
  public void setRespawnHealthPoints(int respawnHealthPoints) {
    this.respawnHealthPoints = respawnHealthPoints;
  }

  @Override
  public void setArmorRating(int armorRating) {
    this.armorRating = armorRating;
  }

  @Override
  public void setRespawnEnergy(int respawnEnergy) {
    this.respawnEnergy = respawnEnergy;
  }

  @Override
  public void setMissChance(int missChance) {
    this.missChance = missChance;
  }

  public void levelUpMech() {
    setRespawnHealthPoints(750 + (getLevel() * 50));
    setHealthPoints(respawnHealthPoints);
    setRespawnEnergy(300 + (getLevel() * 50));
    setEnergy(respawnEnergy);
    setAttackDamage(50 + (getLevel() * 5));
    setArmorRating(0 + getLevel());
    setRoundsToRespawn(getLevel() * 500);
    setAvailableLevelUpPoints(getAvailableLevelUpPoints()+1);
  }
}
