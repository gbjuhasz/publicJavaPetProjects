package board;

import decisionmaking.BotReaction;
import java.util.List;
import mechherocontrol.MechHeroAttackManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import mechherocontrol.MechHeroKeyMovementManager;
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
  /* CreepAlliedDecisionMaker creepAlliedDecisionMaker = new CreepAlliedDecisionMaker();
   MechEnemyDecisionMaker mechEnemyDecisionMaker = new MechEnemyDecisionMaker();
   CreepEnemyDecisionMaker creepEnemyDecisionMaker = new CreepEnemyDecisionMaker();*/
  MechHeroKeyMovementManager mechHeroKeyMovementManager = new MechHeroKeyMovementManager();
  BotReaction botReaction = new BotReaction();
  BuildingDepthEffect buildingDepthEffect = new BuildingDepthEffect();
  MechHeroAttackManager mechHeroAttackManager = new MechHeroAttackManager();
  int roundCounter = 0;
  //for debugging

  public Board() {

    setPreferredSize(new Dimension(1120, 720));
    setVisible(true);
    unitLayout.placeUnitsOnMap();
    buildingDepthEffect.placeBuildingEffectOnMap();

  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);

    for (Tile tile : cityMap) {
      tile.draw(graphics);
    }

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

    for (Unit unit : unitLayout.getListOfEnemyUnits()) {
      if (unitLayout.getMechHero().calculateDistanceBetweenUnits(unit) < unitLayout.getMechHero().getAttackRange()) {
        if (unit.getHealthPoints() <= unitLayout.getMechHero().getAttackDamage() &&
                unitLayout.getMechHero().getAttackRange() >= unitLayout.getMechHero().calculateDistanceBetweenUnits(unit)) {
          graphics.setColor(Color.RED);
        } else if (unit.getHealthPoints() > unitLayout.getMechHero().getAttackDamage() &&
                unitLayout.getMechHero().getAttackRange() >= unitLayout.getMechHero().calculateDistanceBetweenUnits(unit)) {
          graphics.setColor(Color.GREEN);
        } else {
          graphics.setColor(Color.WHITE);
        }
        ArrayList<Integer> crosshairCoordinates = hud.findCrosshairCoordinates(unit);
        graphics.drawRect(crosshairCoordinates.get(0),
                crosshairCoordinates.get(1),
                crosshairCoordinates.get(2),
                crosshairCoordinates.get(2));
        graphics.drawString(unit.getCanBeAttackedWithThisButton(),
                crosshairCoordinates.get(0),
                crosshairCoordinates.get(1) - 10);
        if (unit.getHealthPoints() <= unitLayout.getMechHero().getAttackDamage()) {
          graphics.setColor(Color.RED);
          graphics.drawString(String.valueOf(unit.getHealthPoints()), crosshairCoordinates.get(0) + 72, crosshairCoordinates.get(1) - 10);
        } else {
          graphics.drawString(String.valueOf(unit.getHealthPoints()), crosshairCoordinates.get(0) + 72, crosshairCoordinates.get(1) - 10);
        }
        graphics.setColor(Color.GREEN);
      }
    }
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
    JFrame frame = new JFrame("MidOrFeed:MechWarrior2000");
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
    MechHero mechHero = unitLayout.getMechHero();
    MechEnemy mechEnemy = unitLayout.getMechEnemy();
    TurretEnemy turretEnemy = unitLayout.getTurretEnemy();
    TurretAllied turretAllied = unitLayout.getTurretAllied();
    ArrayList<Creep> listOfCreepAllied = unitLayout.getListOfCreepAllied();
    ArrayList<Creep> listOfCreepEnemy = unitLayout.getListOfCreepEnemy();

/*
    // When the up or down keys hit, we change the position of our box
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroKeyMovementManager.moveMechHeroWithKeys(mechHero, 0, -18, roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroKeyMovementManager.moveMechHeroWithKeys(mechHero, 0, 18, roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);

      mechHeroKeyMovementManager.moveMechHeroWithKeys(mechHero, 18, 0, roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroKeyMovementManager.moveMechHeroWithKeys(mechHero, -18, 0, roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, mechEnemy, roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_1) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, listOfCreepEnemy.get(0), roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_2) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, listOfCreepEnemy.get(1), roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_3) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, listOfCreepEnemy.get(2), roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_Q) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, listOfCreepEnemy.get(3), roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_W) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, listOfCreepEnemy.get(4), roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_E) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, listOfCreepEnemy.get(5), roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,
              roundCounter);
      repaint();
      // and redraw to have a new picture with the new coordinates
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_T) {
      roundCounter++;
      respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
      mechHeroAttackManager.attackTargetUnit(mechHero, turretEnemy, roundCounter);
      botReaction.makeBotsReactToPlayerAction(mechHero,
              mechEnemy,
              listOfCreepAllied,
              listOfCreepEnemy,
              turretAllied,
              turretEnemy,

              roundCounter);
      repaint();
    } else {
      repaint();
    }
    */
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    MechHero mechHero = unitLayout.getMechHero();
    MechEnemy mechEnemy = unitLayout.getMechEnemy();
    TurretEnemy turretEnemy = unitLayout.getTurretEnemy();
    TurretAllied turretAllied = unitLayout.getTurretAllied();
    ArrayList<Creep> listOfCreepAllied = unitLayout.getListOfCreepAllied();
    ArrayList<Creep> listOfCreepEnemy = unitLayout.getListOfCreepEnemy();
    List<Unit> listOfAllUnits = unitLayout.getAllUnits();
    MechHeroMouseCommandManager mechHeroMouseCommandManager = new MechHeroMouseCommandManager();
    roundCounter++;
    respawnManager.respawnUnits(unitLayout.getListOfMechs(), listOfCreepAllied, listOfCreepEnemy, roundCounter);
    mechHeroMouseCommandManager.reactToMouseAction(mechHero,
            mechEnemy,
            listOfCreepAllied,
            listOfCreepEnemy,
            turretAllied,
            turretEnemy,
            roundCounter,
            e);
    botReaction.makeBotsReactToPlayerAction(mechHero,
            mechEnemy,
            listOfCreepAllied,
            listOfCreepEnemy,
            turretAllied,
            turretEnemy,
            listOfAllUnits,
            roundCounter);
    repaint();
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