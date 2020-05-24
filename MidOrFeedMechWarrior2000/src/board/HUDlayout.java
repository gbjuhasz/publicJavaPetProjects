package board;

import visualeffects.HUD;

public class HUDlayout {

  private HUD hud;

  public void placeHud() {
    hud = new HUD("images/hud/hud.png", 0, 621);
  }


  public HUD getHud() {
    return hud;
  }
}

