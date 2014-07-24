package ca.bcit.comp2613.battleship.model;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SetupBoard extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel gridPane; 
    private JPanel shipPane;
    private JButton[][] setupGrid;
    
    public static final int WIDTH = 11;
    public static final int LENGTH = 11;
    
    public SetupBoard() {
        setSize(850, 1000);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        gridPane = new JPanel();
        shipPane = new JPanel();
        
        gridPane.setLayout(new GridLayout(WIDTH, LENGTH));
        gridPane.setSize(850, 850);
        createSetupButtons();
        
        
        shipPane.setSize(850, 150);
        
        add(gridPane);
        add(shipPane);
        
    }
    
    public void createSetupButtons() {
        setupGrid = new JButton[WIDTH][LENGTH];     
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                setupGrid[i][j] = new JButton("(" + j + "," + i + ")");
                gridPane.add(setupGrid[i][j]);
            }
            
        }
    }
}
