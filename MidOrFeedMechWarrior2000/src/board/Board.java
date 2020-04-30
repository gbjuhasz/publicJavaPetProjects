package board;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import movement.MechHeroMovementManager;
import units.MechHero;
import units.Tile;

public class Board extends JComponent implements KeyListener {

  MapBuilder mapBuilder = new MapBuilder();
  ArrayList<Tile> cityMap = mapBuilder.buildMap();
  UnitLayout unitLayout = new UnitLayout();
  MechHeroMovementManager mechHeroMovementManager = new MechHeroMovementManager();
  int roundCounter = 1;

  public Board() {

    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
    unitLayout.placeUnitsOnMap();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for (Tile tile : cityMap) {
      tile.draw(graphics);
    }

    unitLayout.getMechHero().draw(graphics);
    graphics.setColor(Color.WHITE);
    graphics.drawString("X:" + unitLayout.getMechHero().getPosX(),
            72,
            40);
    graphics.drawString("Y:" + unitLayout.getMechHero().getPosY(),
            72,
            80);
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

    // When the up or down keys hit, we change the position of our box
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      roundCounter++;
      mechHeroMovementManager.moveMechHeroWithKeys(mechHero, 0, -18, roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      roundCounter++;
      mechHeroMovementManager.moveMechHeroWithKeys(mechHero, 0, 18, roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      roundCounter++;
      mechHeroMovementManager.moveMechHeroWithKeys(mechHero, 18, 0, roundCounter);
      repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      roundCounter++;
      mechHeroMovementManager.moveMechHeroWithKeys(mechHero, -18, 0, roundCounter);
      repaint();
    } else {
      repaint();
    }
    // and redraw to have a new picture with the new coordinates
    repaint();
  }

}