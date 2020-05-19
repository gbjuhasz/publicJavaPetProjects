package board;

public abstract class BoardComponent {
  private int posX;
  private int posY;
  private String unitType;


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
}
