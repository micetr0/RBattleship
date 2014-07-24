package ca.bcit.comp2613.battleship.model;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.JTextField;

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
        //setSize(850, 1000);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        gridPane = new JPanel();
        shipPane = new JPanel();
        
        gridPane.setLayout(new GridLayout(WIDTH, LENGTH));
        //gridPane.setSize(850, 150);
        createSetupButtons();
        
        shipPane.setLayout(new GridLayout(1, 1));
        //shipPane.setSize(850, 850);
        createTabbedPanel();
        
        add(gridPane);
        add(shipPane);
        
    }
    
    public void createTabbedPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent destroyer = makeTextPanel("Destroyer");
        JComponent submarine = makeTextPanel("Submarine");
        JComponent battleship = makeTextPanel("Battleship");
        JComponent carrier = makeTextPanel("Carrier");        
        
        tabbedPane.addTab("Destroyer", destroyer);
        tabbedPane.addTab("Submarine", submarine);
        tabbedPane.addTab("Battleship", battleship);
        tabbedPane.addTab("Carrier", carrier);
        
        shipPane.add(tabbedPane);
    }
    
    protected JComponent makeTextPanel(String textString) {
        String typeOfShip = textString;
        JPanel panel = new JPanel(false);
        JLabel label = new JLabel(textString);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel onePanel = new JPanel(new FlowLayout());
        
        JLabel positionOne = new JLabel("Position 1");
        JTextField coordXOne = new JTextField("X Coordinates");
        JTextField coordYOne = new JTextField("Y Coordinates");
        
        onePanel.add(positionOne);
        onePanel.add(coordXOne);
        onePanel.add(coordYOne);
        
        JPanel twoPanel = new JPanel(new FlowLayout());
        
        JLabel positionTwo = new JLabel("Position 2");
        JTextField coordXTwo = new JTextField("X Coordinates");
        JTextField coordYTwo = new JTextField("Y Coordinates");
        
        twoPanel.add(positionTwo);
        twoPanel.add(coordXTwo);
        twoPanel.add(coordYTwo);
        
        JButton save = new JButton("Save");
        panel.add(onePanel);
        panel.add(twoPanel);
        panel.add(save);
        return panel;
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
