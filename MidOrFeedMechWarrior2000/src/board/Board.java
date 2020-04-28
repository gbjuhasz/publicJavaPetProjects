package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {


  public Board() {

    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);

    int rowNumber = 1;
    int columnNumber = 1;
    for (int i = 0; i < 720; i = i + 72) {
      for (int j = 0; j < 720; j = j + 72) {
        String fileLocationName = "/Users/BenceJuhasz/Desktop/" +
                "publicJavaPetProjects/publicJavaPetProjects/MidOrFeedMechWarrior2000/images/board/row-" +
                +rowNumber + "-col-" + columnNumber + ".jpg";
        Tile floor = new Tile(fileLocationName, i, j);
        floor.draw(graphics);
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
    // When the up or down keys hit, we change the position of our box
    if (e.getKeyCode() == KeyEvent.VK_UP) {

    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {

    }
    // and redraw to have a new picture with the new coordinates
    repaint();

  }

}