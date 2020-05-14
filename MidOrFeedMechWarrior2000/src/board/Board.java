package board;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import mechherocontrol.MechHeroMouseClickReactionManager;
import mechherocontrol.MechHeroMouseCommandManager;
import respawn.RespawnManager;
import units.*;
import visualeffects.BuildingDepthEffect;
import visualeffects.HUD;

public class Board extends JComponent implements KeyListener, MouseListener {

  MapBuilder mapBuilder = new MapBuilder();
  ArrayList<Tile> cityMap = mapBuilder.buildMap();
  UnitLayout unitLayout = new UnitLayout();
  RespawnManager respawnManager = new RespawnManager();
  HUD hud = new HUD();
  OneRoundOfAction oneRoundOfAction = new OneRoundOfAction();
  BuildingDepthEffect buildingDepthEffect = new BuildingDepthEffect();
  int roundCounter = 0;

  public Board() {

    setPreferredSize(new Dimension(1120, 720));
    setVisible(true);
    unitLayout.placeUnitsOnMap();
    buildingDepthEffect.placeBuildingEffectOnMap();

  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    //Map drawing
    for (Tile tile : cityMap) {
      tile.draw(graphics);
    }
    //Unit placement
    unitLayout.getMechHero().draw(graphics);
    unitLayout.getMechEnemy().draw(graphics);
    unitLayout.getTurretAllied().draw(graphics);
    unitLayout.getTurretEnemy().draw(graphics);
    for (Creep creepAllied : unitLayout.getListOfCreepAllied()) {
      creepAllied.draw(graphics);
    }
    for (Creep creepEnemy : unitLayout.getListOfCreepEnemy()) {
      creepEnemy.draw(graphics);
    }
    //Information
    graphics.setColor(Color.WHITE);
    graphics.drawString(String.valueOf(roundCounter), 72, 120);
    graphics.drawString(String.valueOf(unitLayout.getCreepAllied3().isAlive()), 72, 150);

    graphics.drawString("X:" + unitLayout.getMechHero().getPosX(),
            72,
            40);
    graphics.drawString("Y:" + unitLayout.getMechHero().getPosY(),
            72,
            80);
    //HUD display
    hud.drawHUD(unitLayout.getMechHero(), unitLayout.getListOfEnemyUnits(), graphics);
    hud.drawHealthBars(unitLayout.getAllUnits(),graphics);

    //Building depth effects
    buildingDepthEffect.getBuildingBottomRightSide().draw(graphics);
    buildingDepthEffect.getBuildingLeftSide().draw(graphics);
    //DEBUGGING DRAWINGS
    graphics.setColor(Color.BLACK);
    int posYForCoordinates = 40;
    graphics.fillRect(720, 0, 400, 720);
    graphics.setColor(Color.RED);
    graphics.drawString(String.valueOf(unitLayout.getMechEnemy().getHealthPoints()), 720, 40);
    graphics.setColor(Color.WHITE);
    for (Creep creep : unitLayout.getListOfCreepAllied()) {
      graphics.drawString("x:" + creep.getPosX() + "y:" + creep.getPosY() + " respawn at:" + creep.getRoundToRespawn(), 760, posYForCoordinates);
      posYForCoordinates = posYForCoordinates + 40;
    }
    ;
    for (Creep creep : unitLayout.getListOfCreepEnemy()) {
      graphics.drawString("x:" + creep.getPosX() + "y:" + creep.getPosY() + " respawn at:" + creep.getRoundToRespawn(), 760, posYForCoordinates);
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
                  roundCounter);
          respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
          roundCounter++;
          repaint();
        }
      };
      Timer timer = new Timer(50, al);
      timer.start();
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

    if (SwingUtilities.isRightMouseButton(e)) {
      mechHeroMouseClickReactionManager.reactToRightClick(mechHero,
              mechEnemy,
              listOfCreepEnemy,
              turretEnemy,
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