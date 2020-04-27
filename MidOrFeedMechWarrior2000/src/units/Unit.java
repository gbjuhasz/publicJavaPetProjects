package units;

public abstract class Unit {
  //fields for positioning on board, image picking and drawing effects
  private int posX;
  private int posY;
  private int previousX;
  private int previousY;
  private int imageMiddleX;
  private int imageMiddleY;
  private String facingDirection;
  //fields for fighting
  private int healthPoints;
  private int armorRating;
  private int attackRange;
  private int attackDamage;
  //field for decision making
  private boolean isThreatToHeroUnit;

  public boolean isThreatToHeroUnit() {
    return isThreatToHeroUnit;
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

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
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

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public void setPosY(int posY) {
    this.posY = posY;
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
}
