package ca.bcit.comp2613.battleship;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.bcit.comp2613.battleship.model.Board;
import ca.bcit.comp2613.battleship.model.Menu;
import ca.bcit.comp2613.battleship.model.SetupBoard;

public class BattleShip {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Board board = new Board();
        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setSize(850, 150);
        
        JFrame g = new JFrame("test");
        g.setSize(850, 1000);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setVisible(true);
        g.add(board);
        g.add(ctrlPanel);
        
        SetupBoard setup = new SetupBoard();

      JFrame f = new JFrame("test");
      f.setSize(850, 500);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
      f.add(setup);
      

      
      Menu menu = new Menu();
      }
    
//        Board board = new Board();
//        JPanel ctrlPanel = new JPanel();
//        ctrlPanel.setSize(850, 150);
//        JFrame f = new JFrame("test");
//        f.setSize(850, 1000);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//        f.add(board);
//        f.add(ctrlPanel);

}
