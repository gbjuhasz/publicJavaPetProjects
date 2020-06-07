package board;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BoardComponent {
  private Integer posX;
  private Integer posY;
  private String unitType;
  BufferedImage image;

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public int getPosX() {
    return posX;
  }

  public String getUnitType() {
    return unitType;
  }

  public void setUnitType(String unitType) {
    this.unitType = unitType;
  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, posX, posY, null);
    }
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public BufferedImage getImage() {
    return image;
  }

  public double calculateDistanceBetweenUnits(BoardComponent boardComponent) {
    int a = Math.abs(getPosX() - boardComponent.getPosX());
    int b = Math.abs(getPosY() - boardComponent.getPosY());
    double distance = Math.sqrt(a * a + b * b);
    return distance;
  }
}
