package board;

import units.Tile;

public class BuildingDepthEffect {

  private Tile buildingLeftSide;
  private Tile buildingBottomRightSide;

  public void placeBuildingEffectOnMap(){
    buildingLeftSide = new Tile ("images/buildingdeptheffect/BuildingTopLeft.png",2,269);
    buildingBottomRightSide = new Tile("images/buildingdeptheffect/BuildingTopBottomRight.png", 666, 422);
  }

  public Tile getBuildingBottomRightSide() {
    return buildingBottomRightSide;
  }

  public Tile getBuildingLeftSide() {
    return buildingLeftSide;
  }
}
