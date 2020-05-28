package units;

import abilities.Ability;
import board.BoardComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import visualeffects.Explosion;

public abstract class Unit extends BoardComponent {
  //fields for positioning on board, image picking
  private int previousX;
  private int previousY;
  private int imageMiddleX;
  private int imageMiddleY;
  private String facingDirection;
  private BufferedImage image;
  private boolean isHighlighted = false;
  private int switchFeetInRound = 10;
  private int switchFeetEveryXRound = 10;
  private String[] feetImageNames = {"EVEN", "ODD"};
  private String feetForward;
  //fields for drawing effects
  private boolean rightClickAttackedThisRound = false;
  private int rightClickVisibleForRounds = 50;
  private String lastAttackResult;
  private HashMap<String,Integer> xCoordinatesForRightClickEffectInEveryDirection = new HashMap<>();
  private HashMap<String,Integer> yCoordinatesForRightClickEffectInEveryDirection = new HashMap<>();
  private Explosion rightClickEffect = new Explosion();
  //fields for fighting
  private int respawnHealthPoints;
  private int healthPoints;
  private int energy;
  private int respawnEnergy;
  private int armorRating;
  private int attackRange;
  private int attackDamage;
  private int missChance;
  private int roundsPerAttack = 100;
  private int roundAttackedLastTime = -100;
  private int roundAttackNextTime;
  private Unit unitTargeted;
  private boolean isStunned;
  private int stunOverInRound;
  private ArrayList<Ability> listOfAbilities = new ArrayList();
  private boolean isInvisible;
  private int invisibleUntilRound;
  //fields for levelling
  private int level = 1;
  private int experiencePoints = 0;
  private HashMap<Integer, Integer> xpNeededForLevelUp = new HashMap() {{
    put(2, 230);
    put(3, 600);
    put(4, 1080);
    put(5, 1660);
    put(6, 2260);
  }};
  private HashMap<Integer, Integer> xpBounty;
  //fields for respawning
  private boolean isAlive = true;
  private int roundDied;
  private int respawnX;
  private int respawnY;
  //field for decision making
  private boolean isThreatToHeroUnit;
  private int powerScore;
  private String targetDirection;
  private int detectionRange;

  public boolean isThreatToHeroUnit() {
    return isThreatToHeroUnit;
  }

  public BufferedImage getImage() {
    return image;
  }

  public int getArmorRating() {
    return armorRating;
  }

  public int getAttackDamage() {
    return attackDamage;
  }

  public int getImageMiddleX() {
    return imageMiddleX;
  }

  public int getAttackRange() {
    return attackRange;
  }

  public int getHealthPoints() {
    return healthPoints;
  }

  public int getImageMiddleY() {
    return imageMiddleY;
  }

  public int getPreviousX() {
    return previousX;
  }

  public int getPreviousY() {
    return previousY;
  }

  public String getFacingDirection() {
    return facingDirection;
  }

  public int getPowerScore() {
    return powerScore;
  }

  public String getTargetDirection() {
    return targetDirection;
  }

  public int getDetectionRange() {
    return detectionRange;
  }

  public int getMissChance() {
    return missChance;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public int getRoundDied() {
    return roundDied;
  }

  public int getRespawnX() {
    return respawnX;
  }

  public int getRespawnY() {
    return respawnY;
  }

  public int getRespawnHealthPoints() {
    return respawnHealthPoints;
  }

  public int getSwitchFeetInRound() {
    return switchFeetInRound;
  }

  public int getSwitchFeetEveryXRound() {
    return switchFeetEveryXRound;
  }

  public String[] getFeetImageNames() {
    return feetImageNames;
  }

  public int getRoundAttackedLastTime() {
    return roundAttackedLastTime;
  }

  public int getRoundAttackNextTime() {
    return roundAttackNextTime;
  }

  public int getRoundsPerAttack() {
    return roundsPerAttack;
  }

  public HashMap<String, Integer> getxCoordinatesForRightClickEffectInEveryDirection() {
    return xCoordinatesForRightClickEffectInEveryDirection;
  }

  public HashMap<String, Integer> getyCoordinatesForRightClickEffectInEveryDirection() {
    return yCoordinatesForRightClickEffectInEveryDirection;
  }

  public HashMap<Integer, Integer> getXpBounty() {
    return xpBounty;
  }

  public boolean isHighlighted() {
    return isHighlighted;
  }

  public String getFeetForward() {
    return feetForward;
  }

  public int getLevel() {
    return level;
  }

  public int getExperiencePoints() {
    return experiencePoints;
  }

  public HashMap<Integer, Integer> getXpNeededForLevelUp() {
    return xpNeededForLevelUp;
  }

  public Unit getUnitTargeted() {
    return unitTargeted;
  }

  public int getRightClickVisibleForRounds() {
    return rightClickVisibleForRounds;
  }

  public String getLastAttackResult() {
    return lastAttackResult;
  }

  public Explosion getRightClickEffect() {
    return rightClickEffect;
  }

  public ArrayList<Ability> getListOfAbilities() {
    return listOfAbilities;
  }

  public int getEnergy() {
    return energy;
  }

  public int getRespawnEnergy() {
    return respawnEnergy;
  }

  public int getStunOverInRound() {
    return stunOverInRound;
  }

  public int getInvisibleUntilRound() {
    return invisibleUntilRound;
  }

  public boolean isInvisible() {
    return isInvisible;
  }

  public boolean isStunned() {
    return isStunned;
  }

  public boolean isRightClickAttackedThisRound() {
    return rightClickAttackedThisRound;
  }


  public void setStunned(boolean stunned) {
    isStunned = stunned;
  }

  public void setStunOverInRound(int stunOverInRound) {
    this.stunOverInRound = stunOverInRound;
  }

  public void setLastAttackResult(String lastAttackResult) {
    this.lastAttackResult = lastAttackResult;
  }

  public void setFeetForward(String feetForward) {
    this.feetForward = feetForward;
  }

  public void setArmorRating(int armorRating) {
    this.armorRating = armorRating;
  }

  public void setAttackDamage(int attackDamage) {
    this.attackDamage = attackDamage;
  }

  public void setAttackRange(int attackRange) {
    this.attackRange = attackRange;
  }

  public void setFacingDirection(String facingDirection) {
    this.facingDirection = facingDirection;
  }

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  public void setImageMiddleX(int imageMiddleX) {
    this.imageMiddleX = imageMiddleX;
  }

  public void setImageMiddleY(int imageMiddleY) {
    this.imageMiddleY = imageMiddleY;
  }

  public void setPreviousX(int previousX) {
    this.previousX = previousX;
  }

  public void setPreviousY(int previousY) {
    this.previousY = previousY;
  }

  public void setThreatToHeroUnit(boolean threatToHeroUnit) {
    this.isThreatToHeroUnit = threatToHeroUnit;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public void setTargetDirection(String targetDirection) {
    this.targetDirection = targetDirection;
  }

  public void setDetectionRange(int detectionRange) {
    this.detectionRange = detectionRange;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public void setPowerScore(int powerScore) {
    this.powerScore = powerScore;
  }

  public void setMissChance(int missChance) {
    this.missChance = missChance;
  }

  public void setRoundDied(int roundDied) {
    this.roundDied = roundDied;
  }

  public void setRespawnX(int respawnX) {
    this.respawnX = respawnX;
  }

  public void setRespawnY(int respawnY) {
    this.respawnY = respawnY;
  }

  public void setRespawnHealthPoints(int respawnHealthPoints) {
    this.respawnHealthPoints = respawnHealthPoints;
  }

  public void setHighlighted(boolean highlighted) {
    isHighlighted = highlighted;
  }

  public void setSwitchFeetInRound(int switchFeetInRound) {
    this.switchFeetInRound = switchFeetInRound;
  }

  public void setSwitchFeetEveryXRound(int switchFeetEveryXRound) {
    this.switchFeetEveryXRound = switchFeetEveryXRound;
  }

  public void setExperiencePoints(int experiencePoints) {
    this.experiencePoints = experiencePoints;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setRoundAttackedLastTime(int roundAttackedLastTime) {
    this.roundAttackedLastTime = roundAttackedLastTime;
  }

  public void setRoundAttackNextTime(int roundAttackNextTime) {
    this.roundAttackNextTime = roundAttackNextTime;
  }

  public void setRoundsPerAttack(int roundsPerAttack) {
    this.roundsPerAttack = roundsPerAttack;
  }

  public void setRightClickAttackedThisRound(boolean rightClickAttackedThisRound) {
    this.rightClickAttackedThisRound = rightClickAttackedThisRound;
  }

  public void setRightClickEffect(Explosion rightClickEffect) {
    this.rightClickEffect = rightClickEffect;
  }

  public void setUnitTargeted(Unit unitTargeted) {
    this.unitTargeted = unitTargeted;
  }

  public void setRespawnEnergy(int respawnEnergy) {
    this.respawnEnergy = respawnEnergy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public void setInvisible(boolean invisible) {
    isInvisible = invisible;
  }

  public void setInvisibleUntilRound(int invisibleUntilRound) {
    this.invisibleUntilRound = invisibleUntilRound;
  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, super.getPosX(), super.getPosY(), null);
    }
  }

  public void calculateImageCenterCoordinates() {
    setImageMiddleX(getPosX() + 36);
    setImageMiddleY(getPosY() + 36);
  }

  public double calculateDistanceBetweenUnits(BoardComponent boardComponent) {
    int a = Math.abs(getPosX() - boardComponent.getPosX());
    int b = Math.abs(getPosY() - boardComponent.getPosY());
    double distance = Math.sqrt(a * a + b * b);
    return distance;
  }

  public void calculateTargetDirection(BoardComponent boardComponent) {
    int targetX = boardComponent.getPosX();
    int targetY = boardComponent.getPosY();

    if (super.getPosX() == targetX && super.getPosY() < targetY) {
      setTargetDirection("S");
    } else if (super.getPosX() == targetX && super.getPosY() > targetY) {
      setTargetDirection("N");
    } else if (super.getPosX() > targetX && super.getPosY() == targetY) {
      setTargetDirection("W");
    } else if (super.getPosX() < targetX && super.getPosY() == targetY) {
      setTargetDirection("E");
    } else if (super.getPosX() > targetX && super.getPosY() < targetY) {
      setTargetDirection("SW");
    } else if (super.getPosX() < targetX && super.getPosY() < targetY) {
      setTargetDirection("SE");
    } else if (super.getPosX() < targetX) {
      setTargetDirection("NE");
    } else if (super.getPosX() > targetX) {
      setTargetDirection("NW");
    }
  }

  public ArrayList<Unit> findUnitsWithinRangeOfAreaEffectAroundUnit(Unit unitTargeted, ArrayList<Unit> listOfUnits, int rangeAOE){
    ArrayList<Unit> unitsInAoe = new ArrayList<>();
    for (Unit unitAroundTarget: listOfUnits
         ) {
      if(unitTargeted.calculateDistanceBetweenUnits(unitAroundTarget) <= rangeAOE){
        unitsInAoe.add(unitAroundTarget);
      }
    }
    return unitsInAoe;
  }
}
