package units;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Unit {
  //fields for positioning on board, image picking and drawing effects
  private int posX;
  private int posY;
  private int previousX;
  private int previousY;
  private int imageMiddleX;
  private int imageMiddleY;
  private String facingDirection;
  private String unitTypeForImage;
  private BufferedImage image;
  //fields for fighting
  private int healthPoints;
  private int armorRating;
  private int attackRange;
  private int attackDamage;
  //field for decision making
  private boolean isThreatToHeroUnit;
  private int powerScore;
  private String targetDirection;

  public boolean isThreatToHeroUnit() {
    return isThreatToHeroUnit;
  }

  public BufferedImage getImage() {
    return image;
  }

  public String getUnitTypeForImage() {
    return unitTypeForImage;
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

  public int getPowerScore() {
    return powerScore;
  }

  public String getTargetDirection() {
    return targetDirection;
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

  public void setUnitTypeForImage(String unitTypeForImage) {
    this.unitTypeForImage = unitTypeForImage;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public void setTargetDirection(String targetDirection) {
    this.targetDirection = targetDirection;
  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, posX, posY, null);
    }
  }

  public void setPowerScore(int powerScore) {
    this.powerScore = powerScore;
  }

  public void calculateImageCenterCoordinates() {
    setImageMiddleX(getPosX() + 36);
    setImageMiddleY(getPosY() + 36);
  }

  public double calculateDistanceBetweenUnits(Unit otherUnit) {
    int a = Math.abs(getPosX() - otherUnit.getPosX());
    int b = Math.abs(getPosY() - otherUnit.getPosY());
    double distance = Math.sqrt(a * a + b * b);
    return distance;
  }

  public void calculateTargetDirection(Unit targetUnit){
    int targetX = targetUnit.getPosX();
    int targetY = targetUnit.getPosY();

    if(posX == targetX && posY < targetY){
      setTargetDirection("S");
    } else if (posX == targetX && posY > targetY) {
      setTargetDirection("N");
    } else if (posX > targetX && posY == targetY) {
      setTargetDirection("W");
    } else if (posX < targetX && posY == targetY) {
      setTargetDirection("E");
    } else if (posX > targetX && posY < targetY ) {
      setTargetDirection("SW");
    } else if (posX < targetX && posY < targetY ) {
      setTargetDirection("SE");
    } else if (posX < targetX) {
      setTargetDirection("NE");
    } else if (posX > targetX) {
      setTargetDirection("NW");
    }
  }


}
