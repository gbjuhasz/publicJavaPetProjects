package board;

import java.util.ArrayList;

public class MapBuilder {

  public ArrayList<Tile> buildMap() {
    int rowNumber = 1;
    int columnNumber = 1;
    ArrayList<Tile> listOfMapTiles = new ArrayList<>();
    for (int i = 0; i < 720; i = i + 72) {
      for (int j = 0; j < 720; j = j + 72) {
        String fileLocationName = "images/map/row-" +
                +rowNumber + "-col-" + columnNumber + ".jpg";
        Tile tile = new Tile(fileLocationName, i, j);
        listOfMapTiles.add(tile);
        rowNumber = rowNumber + 1;
        if (rowNumber > 10) {
          rowNumber = 1;
        }
      }
      columnNumber = columnNumber + 1;
      if (columnNumber > 10) {
        columnNumber = 1;
      }
    }
    return listOfMapTiles;
  }
}
