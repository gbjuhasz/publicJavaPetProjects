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
import visualeffects.HUDgraphicsManager;
import visualeffects.RightClickVisualEffectManager;

public class Board extends JComponent implements KeyListener, MouseListener {

  MapBuilder mapBuilder = new MapBuilder();
  ArrayList<Tile> cityMap = mapBuilder.buildMap();
  UnitLayout unitLayout = new UnitLayout();
  HUDlayout hudLayout = new HUDlayout();
  RespawnManager respawnManager = new RespawnManager();
  HUDgraphicsManager HUDgraphicsManager = new HUDgraphicsManager();
  OneRoundOfAction oneRoundOfAction = new OneRoundOfAction();
  EffectLayout effectLayout = new EffectLayout();
  BuildingDepthEffectManager buildingDepthEffectManager = new BuildingDepthEffectManager();
  RightClickVisualEffectManager rightClickVisualEffectManager = new RightClickVisualEffectManager();
  int roundCounter = 0;
  String ePosX = "0";
  String ePosY = "0";

  public Board() {

    setPreferredSize(new Dimension(720, 768));
    setVisible(true);
    unitLayout.placeUnitsOnMap();
    buildingDepthEffectManager.placeBuildingEffectOnMap();
    hudLayout.placeHud();
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
    hudLayout.getHud().draw(graphics);
    //Information
    graphics.setColor(Color.WHITE);
    graphics.drawString(String.valueOf(roundCounter), 72, 120);

    graphics.drawString("X:" + unitLayout.getMechHero().getPosX(),
            72,
            40);
    graphics.drawString("Y:" + unitLayout.getMechHero().getPosY(),
            72,
            80);

    //HUDgraphicsManager display
    HUDgraphicsManager.drawXPBar(unitLayout.getMechHero(), graphics);
    hudLayout.placeHud();
    HUDgraphicsManager.drawRectangle(unitLayout.getMechHero(), unitLayout.getListOfEnemyUnits(), graphics);
    HUDgraphicsManager.drawUnitImageForHighlightedUnit(unitLayout.getAllUnits(), hudLayout.getHud(), graphics);
    HUDgraphicsManager.drawAbilityIcons(unitLayout.getMechHero(), graphics);
    HUDgraphicsManager.drawInfoBars(unitLayout.getAllUnits(), graphics);
    HUDgraphicsManager.drawInfoOfHighlightedUnit(unitLayout.getAllUnits(), graphics);

    //Building depth effects
    buildingDepthEffectManager.getBuildingBottomRightSide().draw(graphics);
    buildingDepthEffectManager.getBuildingLeftSide().draw(graphics);
    //DEBUGGING DRAWINGS
    graphics.setColor(Color.BLACK);
    int posYForCoordinates = 40;
    graphics.fillRect(720, 0, 400, 720);
    graphics.setColor(Color.WHITE);
    graphics.drawRect(360, 140, 1, 1);
    graphics.drawRect(360, 600, 1, 1);
    graphics.drawString(ePosX, 360, 70);
    graphics.drawString(ePosY, 360, 100);
//    graphics.drawString("X", 360, 140);
    graphics.setColor(Color.RED);
    graphics.setColor(Color.WHITE);
    graphics.drawLine(unitLayout.getMechEnemy().getImageMiddleX() - 2,
            unitLayout.getMechEnemy().getImageMiddleY() - 2,
            unitLayout.getMechEnemy().getImageMiddleX() + 2,
            unitLayout.getMechEnemy().getImageMiddleY() + 2);
    graphics.drawLine(unitLayout.getMechEnemy().getImageMiddleX() + 2,
            unitLayout.getMechEnemy().getImageMiddleY() - 2,
            unitLayout.getMechEnemy().getImageMiddleX() - 2,
            unitLayout.getMechEnemy().getImageMiddleY() + 2);

    if (unitLayout.getMechHero().getLastAttackResult() != null) {
      graphics.drawString(String.valueOf(unitLayout.getMechHero().getLastAttackResult()), 72, 160);
    }
  /*  graphics.drawString(String.valueOf(unitLayout.getMechHero().getAvailableLevelUpPoints()), 72, 200);
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
      for (int i = 0; i < unitLayout.getMechHero().getListOfActiveAbilities().size(); i++) {
        unitLayout.getMechHero().getListOfActiveAbilities().get(i).setActivated(false);
      }
    }

    if (e.getKeyCode() == KeyEvent.VK_Q) {
      if (unitLayout.getMechHero().getListOfActiveAbilities().get(0).getLevel() != 0 &&
              unitLayout.getMechHero().getEnergy() > unitLayout.getMechHero().getListOfActiveAbilities().get(0).getEnergyCost() &&
              unitLayout.getMechHero().getListOfActiveAbilities().get(0).getCanBeusedAgainInRound() <= roundCounter) {
        unitLayout.getMechHero().getListOfActiveAbilities().get(0).setActivated(true);
        unitLayout.getMechHero().getListOfActiveAbilities().get(1).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(2).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(3).setActivated(false);

      }
    }

    if (e.getKeyCode() == KeyEvent.VK_W) {
      if (unitLayout.getMechHero().getListOfActiveAbilities().get(1).getLevel() != 0 &&
              unitLayout.getMechHero().getEnergy() > unitLayout.getMechHero().getListOfActiveAbilities().get(1).getEnergyCost() &&
              unitLayout.getMechHero().getListOfActiveAbilities().get(1).getCanBeusedAgainInRound() <= roundCounter) {
        unitLayout.getMechHero().getListOfActiveAbilities().get(0).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(1).setActivated(true);
        unitLayout.getMechHero().getListOfActiveAbilities().get(2).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(3).setActivated(false);
      }
    }

    if (e.getKeyCode() == KeyEvent.VK_E) {
      if (unitLayout.getMechHero().getListOfActiveAbilities().get(2).getLevel() != 0 &&
              unitLayout.getMechHero().getEnergy() > unitLayout.getMechHero().getListOfActiveAbilities().get(2).getEnergyCost() &&
              unitLayout.getMechHero().getListOfActiveAbilities().get(2).getCanBeusedAgainInRound() <= roundCounter) {
        unitLayout.getMechHero().getListOfActiveAbilities().get(0).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(1).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(3).setActivated(false);
        unitLayout.getMechHero().getListOfActiveAbilities().get(2).useAbility(unitLayout.getMechHero(), unitLayout.getMechHero(), roundCounter);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_R) {
      if (unitLayout.getMechHero().getListOfActiveAbilities().get(3).getLevel() != 0) {
        if (unitLayout.getMechHero().getEnergy() > unitLayout.getMechHero().getListOfActiveAbilities().get(3).getEnergyCost() &&
                unitLayout.getMechHero().getListOfActiveAbilities().get(3).getCanBeusedAgainInRound() <= roundCounter) {
          unitLayout.getMechHero().getListOfActiveAbilities().get(0).setActivated(false);
          unitLayout.getMechHero().getListOfActiveAbilities().get(1).setActivated(false);
          unitLayout.getMechHero().getListOfActiveAbilities().get(2).setActivated(false);
          unitLayout.getMechHero().getListOfActiveAbilities().get(3).setActivated(true);
        } else if (unitLayout.getMechHero().getListOfPassiveAbilities().get(3).getLevel() != 0) {
          if (!unitLayout.getMechHero().getListOfPassiveAbilities().get(3).isTurnedOn()) {
            unitLayout.getMechHero().getListOfPassiveAbilities().get(3).setTurnedOn(true);
          } else {
            unitLayout.getMechHero().getListOfPassiveAbilities().get(3).setTurnedOn(false);
          }
          unitLayout.getMechHero().getListOfActiveAbilities().get(0).setActivated(false);
          unitLayout.getMechHero().getListOfActiveAbilities().get(1).setActivated(false);
          unitLayout.getMechHero().getListOfActiveAbilities().get(2).setActivated(false);
        } else if (unitLayout.getMechHero().getListOfAuras().get(3).getLevel() != 0) {
          if (!unitLayout.getMechHero().getListOfPassiveAbilities().get(3).isTurnedOn()) {
            unitLayout.getMechHero().getListOfPassiveAbilities().get(3).setTurnedOn(true);
          } else {
            unitLayout.getMechHero().getListOfPassiveAbilities().get(3).setTurnedOn(false);
            unitLayout.getMechHero().getListOfActiveAbilities().get(0).setActivated(false);
            unitLayout.getMechHero().getListOfActiveAbilities().get(1).setActivated(false);
            unitLayout.getMechHero().getListOfActiveAbilities().get(2).setActivated(false);
          }
        }
      }
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
    ePosX = String.valueOf(e.getX());
    ePosY = String.valueOf(e.getY());


    if (SwingUtilities.isRightMouseButton(e)) {
      mechHeroMouseClickReactionManager.reactToRightClick(mechHero,
              listOfAllUnits,
              e);
    } else {
      mechHeroMouseClickReactionManager.reactToLeftClick(mechHero, listOfAllUnits, e, roundCounter);
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