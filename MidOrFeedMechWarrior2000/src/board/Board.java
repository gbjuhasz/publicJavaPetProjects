package board;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import mechherocontrol.MechHeroMouseClickReactionManager;
import respawn.RespawnManager;
import units.*;
import visualeffects.BuildingDepthEffectManager;
import visualeffects.HUDeffectsManager;
import visualeffects.RightClickVisualEffectManager;

public class Board extends JComponent implements KeyListener, MouseListener {

  MapBuilder mapBuilder = new MapBuilder();
  ArrayList<Tile> cityMap = mapBuilder.buildMap();
  UnitLayout unitLayout = new UnitLayout();
  HUDlayout huDlayout = new HUDlayout();
  RespawnManager respawnManager = new RespawnManager();
  HUDeffectsManager HUDeffectsManager = new HUDeffectsManager();
  OneRoundOfAction oneRoundOfAction = new OneRoundOfAction();
  EffectLayout effectLayout = new EffectLayout();
  BuildingDepthEffectManager buildingDepthEffectManager = new BuildingDepthEffectManager();
  RightClickVisualEffectManager rightClickVisualEffectManager = new RightClickVisualEffectManager();
  int roundCounter = 0;

  public Board() {

    setPreferredSize(new Dimension(720, 768));
    setVisible(true);
    unitLayout.placeUnitsOnMap();
    buildingDepthEffectManager.placeBuildingEffectOnMap();
    huDlayout.placeHud();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    //Map drawing
    for (Tile tile : cityMap) {
      tile.draw(graphics);
    }
    //Unit placement

    for (Unit unit : unitLayout.getAllUnitsOrderedByYCoordinate()) {
      unit.draw(graphics);
    }
    unitLayout.getTurretAllied().draw(graphics);
    unitLayout.getTurretEnemy().draw(graphics);

    //fight effects
    rightClickVisualEffectManager.drawUpRightClicks(unitLayout.getAllUnits(), roundCounter, graphics);
    //hud
    huDlayout.getHud().draw(graphics);
    //Information
    graphics.setColor(Color.WHITE);
    graphics.drawString(String.valueOf(roundCounter), 72, 120);

    graphics.drawString("X:" + unitLayout.getMechHero().getPosX(),
            72,
            40);
    graphics.drawString("Y:" + unitLayout.getMechHero().getPosY(),
            72,
            80);

    //HUDeffectsManager display
    HUDeffectsManager.drawHUD(unitLayout.getMechHero(), unitLayout.getListOfEnemyUnits(), graphics);
    HUDeffectsManager.drawUnitImageForHighlightedUnit(unitLayout.getAllUnits(),huDlayout.getHud(),graphics);
    HUDeffectsManager.drawHealthBars(unitLayout.getAllUnits(), graphics);
    HUDeffectsManager.drawInfoOfHighlightedUnit(unitLayout.getAllUnits(),graphics);

    //Building depth effects
    buildingDepthEffectManager.getBuildingBottomRightSide().draw(graphics);
    buildingDepthEffectManager.getBuildingLeftSide().draw(graphics);
    //DEBUGGING DRAWINGS
    graphics.setColor(Color.BLACK);
    int posYForCoordinates = 40;
    graphics.fillRect(720, 0, 400, 720);
    graphics.setColor(Color.RED);
    graphics.setColor(Color.WHITE);
    graphics.drawLine(unitLayout.getMechEnemy().getImageMiddleX()-2,
            unitLayout.getMechEnemy().getImageMiddleY()-2,
            unitLayout.getMechEnemy().getImageMiddleX() +2,
            unitLayout.getMechEnemy().getImageMiddleY()+2);
    graphics.drawLine(unitLayout.getMechEnemy().getImageMiddleX()+2,
            unitLayout.getMechEnemy().getImageMiddleY()-2,
            unitLayout.getMechEnemy().getImageMiddleX() -2,
            unitLayout.getMechEnemy().getImageMiddleY()+2);

    if (unitLayout.getMechHero().getUnitTargeted() != null) {
      graphics.drawString(unitLayout.getMechHero().getUnitTargeted().getUnitType(), 720, 160);
    }
    /*graphics.drawString(String.valueOf(unitLayout.getMechHero().getSwitchFeetEveryXRound()), 720, 200);
    graphics.drawString(String.valueOf(unitLayout.getMechHero().getSwitchFeetInRound()), 720, 240);



    if (unitLayout.getMechHero().getUnitTargeted() != null) {
      graphics.drawString(unitLayout.getMechHero().getUnitTargeted().getUnitType(), 760, 80);
    } else if (unitLayout.getMechHero().getMouseEventMarkingLocation() != null) {
      graphics.drawString(String.valueOf("X:" + unitLayout.getMechHero().getMouseEventMarkingLocation().getX())
              + "Y:" + String.valueOf(unitLayout.getMechHero().getMouseEventMarkingLocation().getY()), 760, 80);
    }

    for (Creep creep : unitLayout.getListOfCreepAllied()) {
      graphics.drawString("waypoint:" + creep.getHeadingTowardsWaypoint(), 760, posYForCoordinates);
      posYForCoordinates = posYForCoordinates + 40;
    }
    ;
    for (Creep creep : unitLayout.getListOfCreepEnemy()) {
      graphics.drawString("waypoint:" + creep.getHeadingTowardsWaypoint(), 760, posYForCoordinates);
      posYForCoordinates = posYForCoordinates + 40;
    }
    ;
    /*
    graphics.drawString(String.valueOf(unitLayout.getCreepAllied3().getPosX()), 360,360);
    graphics.drawString(String.valueOf(unitLayout.getCreepAllied3().getPosY()), 360,390);
    graphics.drawString(stuckUnitAssister.findMapsectionWhereUnitIsStuck(unitLayout.getCreepAllied3().getPosX(),
            unitLayout.getCreepAllied3().getPosY()), 360, 420);*/
  }

  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("Mid Or Feed: MechWarrior 2000");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    frame.addMouseListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener
  }

  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  // But actually we can use just this one for our goals here
  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {

      MechHero mechHero = unitLayout.getMechHero();
      MechEnemy mechEnemy = unitLayout.getMechEnemy();
      TurretEnemy turretEnemy = unitLayout.getTurretEnemy();
      TurretAllied turretAllied = unitLayout.getTurretAllied();
      ArrayList<Creep> listOfCreepAllied = unitLayout.getListOfCreepAllied();
      ArrayList<Creep> listOfCreepEnemy = unitLayout.getListOfCreepEnemy();
      List<Unit> listOfAllUnits = unitLayout.getAllUnits();
      List<Mech> listOfMechs = unitLayout.getListOfMechs();

      ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          oneRoundOfAction.performOneRoundOfAction(mechHero,
                  mechEnemy,
                  listOfCreepAllied,
                  listOfCreepEnemy,
                  turretAllied,
                  turretEnemy,
                  listOfAllUnits,
                  listOfMechs,
                  roundCounter);
          respawnManager.respawnUnits(mechHero, mechEnemy, listOfCreepAllied, listOfCreepEnemy, roundCounter);
          roundCounter++;
          repaint();
        }
      };
      Timer timer = new Timer(5, al);
      timer.start();
    }

    if (e.getKeyCode() == KeyEvent.VK_S) {
      unitLayout.getMechHero().setMouseEventMarkingLocation(null);
      unitLayout.getMechHero().setUnitTargeted(null);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    MechHero mechHero = unitLayout.getMechHero();
    MechEnemy mechEnemy = unitLayout.getMechEnemy();
    TurretEnemy turretEnemy = unitLayout.getTurretEnemy();
    ArrayList<Creep> listOfCreepEnemy = unitLayout.getListOfCreepEnemy();
    List<Unit> listOfAllUnits = unitLayout.getAllUnits();
    MechHeroMouseClickReactionManager mechHeroMouseClickReactionManager = new MechHeroMouseClickReactionManager();
    List<Mech> listOfMechs = unitLayout.getListOfMechs();


    if (SwingUtilities.isRightMouseButton(e)) {
      mechHeroMouseClickReactionManager.reactToRightClick(mechHero,
              mechEnemy,
              listOfCreepEnemy,
              turretEnemy,
              listOfMechs,
              e);
    } else {
      mechHeroMouseClickReactionManager.reactToLeftClick(listOfAllUnits, e);
      repaint();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}