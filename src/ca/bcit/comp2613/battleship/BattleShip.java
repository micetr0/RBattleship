package ca.bcit.comp2613.battleship;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.bcit.comp2613.battleship.model.Board;

public class BattleShip {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Board board = new Board();
        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setSize(850, 150);
        JFrame f = new JFrame("test");
        f.setSize(850, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.add(board);
        f.add(ctrlPanel);
    }

}
