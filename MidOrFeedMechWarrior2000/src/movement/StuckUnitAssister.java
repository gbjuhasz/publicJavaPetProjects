package movement;

import units.Unit;

public class StuckUnitAssister {

  IllegalMoveCheckerMapObjects illegalMoveCheckerMapObjects = new IllegalMoveCheckerMapObjects();

  public void helpIfUnitIsStuck(Unit unit) {
    String targetDirection = unit.getTargetDirection();
    int posX = unit.getPosX();
    int posY = unit.getPosY();
    String section = findMapsectionWhereUnitIsStuck(posX, posY);

    if(section.equals("top left")){
      helpUnitTopLeft(unit,posX,posY, targetDirection);
    } else if (section.equals("middle left")){
      helpUnitMiddleLeft(unit, posX, posY, targetDirection);
    } else if (section.equals("bottom left")){
      helpUnitBottomLeft(unit,posX,posY, targetDirection);
    } else if (section.equals("bridge")){
      helpUnitBridge(unit, posX,posY,targetDirection);
    } else if(section.equals("top right")){
      helpUnitTopRight(unit, posX, posY, targetDirection);
    } else {
      helpUnitBottomRight(unit,posX,posY,targetDirection);
    }
  }

  public String findMapsectionWhereUnitIsStuck(int posX, int posY) {
    if (posX <= 234 && posY <= 162) {
      return "top left";
    } else if (posX <= 198 &&
            posY >= 270 &&
            posY <= 450) {
      return "middle left";
    } else if (posX <= 198 &&
            posY >= 468) {
      return "bottom right";
    } else if (posX > 216 &&
            posX <= 486 &&
            posY >= 162 &&
            posY <= 378) {
      return "bridge";
    } else if (posX >= 486 &&
            posY <= 198) {
      return "top right";
    } else {
      return "bottom right";
    }
  }

  public void helpUnitTopLeft(Unit unit,
                              int posX,
                              int posY,
                              String targetDirection) {

    if (targetDirection.contains("N")) {
      unit.setPosX(posX - 18);
      unit.setPosY(posY + 36);
    } else {
      unit.setPosX(posX - 18);
    }
  }

  public void helpUnitMiddleLeft(Unit unit,
                                 int posX,
                                 int posY,
                                 String targetDirection) {
    if (targetDirection.contains("W")) {
      unit.setPosX(posX - 18);
    } else if (targetDirection.contains("E")) {
      unit.setPosX(posX + 18);
    } else if (targetDirection.equals("W")) {
      unit.setPosX(posX + 18);
      unit.setPosY(posY - 18);
    }
  }

  public void helpUnitBottomLeft(Unit unit,
                                 int posX,
                                 int posY,
                                 String targetDirection) {

    if (targetDirection.contains("S")) {
      unit.setPosX(posX - 18);
      unit.setPosY(posY - 36);
    } else {
      unit.setPosX(posX - 18);
    }
  }

  public void helpUnitBridge(Unit unit,
                             int posX,
                             int posY,
                             String targetDirection) {
    if(targetDirection.contains("N")){
      unit.setPosY(posY + 18);
    } else if (targetDirection.contains("S")){
      unit.setPosY(posY - 18);
    }
  }

  public void helpUnitTopRight(Unit unit,
                               int posX,
                               int posY,
                               String targetDirection){
    if (targetDirection.contains("N")) {
      unit.setPosX(posX + 18);
      unit.setPosY(posY + 36);
    } else {
      unit.setPosX(posX + 18);
    }
  }

  public void helpUnitBottomRight(Unit unit,
                                  int posX,
                                  int posY,
                                  String targetDirection){
    if (targetDirection.contains("S")) {
      unit.setPosX(posX + 18);
      unit.setPosY(posY - 36);
    } else {
      unit.setPosX(posX + 18);
    }
  }
}
