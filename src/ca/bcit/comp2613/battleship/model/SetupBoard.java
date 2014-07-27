package ca.bcit.comp2613.battleship.model;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
    private Coordinates theShipCoord;
    private int shipTypeTab;
    
    Destroyer playerDestroyer;
    Submarine playerSubmarine;
    Battleship playerBattleship;
    Carrier playerCarrier;    
    
    private JTextField destroyerCoordXOne;
    private JTextField destroyerCoordYOne;
    private JTextField destroyerCoordXTwo;
    private JTextField destroyerCoordYTwo;
    private JTextField submarineCoordXOne;
    private JTextField submarineCoordYOne;
    private JTextField submarineCoordXTwo;
    private JTextField submarineCoordYTwo;
    private JTextField battleshipCoordXOne;
    private JTextField battleshipCoordYOne;
    private JTextField battleshipCoordXTwo;
    private JTextField battleshipCoordYTwo;
    private JTextField carrierCoordXOne;
    private JTextField carrierCoordYOne;
    private JTextField carrierCoordXTwo;
    private JTextField carrierCoordYTwo;
    
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
        
        playerDestroyer = new Destroyer(null, null, null, null, 2);
        //playerDestroyer.zeroCoordinates();
        playerSubmarine = new Submarine(null, null, null, null, 3);
        //playerSubmarine.zeroCoordinates();
        playerBattleship = new Battleship(null, null, null, null, 4);
        //playerBattleship.zeroCoordinates();
        playerCarrier = new Carrier(null, null, null, null, 5);
        //playerCarrier.zeroCoordinates();
    }
    
    public void createTabbedPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        //destroyer tab panel
        JPanel destroyerPanel = new JPanel(false);
        JLabel destroyerLabel = new JLabel("Destroyer");
        destroyerLabel.setHorizontalAlignment(JLabel.CENTER);
        destroyerPanel.setLayout(new BoxLayout(destroyerPanel, BoxLayout.Y_AXIS));
        
        JPanel destroyerOnePanel = new JPanel(new FlowLayout());
        
        JLabel destroyerPositionOne = new JLabel("Position 1");
        destroyerCoordXOne = new JTextField("X Coordinates");
        destroyerCoordYOne = new JTextField("Y Coordinates");
        
        destroyerOnePanel.add(destroyerPositionOne);
        destroyerOnePanel.add(destroyerCoordYOne);
        destroyerOnePanel.add(destroyerCoordXOne);
        
        JPanel destroyerTwoPanel = new JPanel(new FlowLayout());
        
        JLabel destroyerPositionTwo = new JLabel("Position 2");
        destroyerCoordXTwo = new JTextField("X Coordinates");
        destroyerCoordYTwo = new JTextField("Y Coordinates");
        
        destroyerTwoPanel.add(destroyerPositionTwo);
        destroyerTwoPanel.add(destroyerCoordYTwo);
        destroyerTwoPanel.add(destroyerCoordXTwo);
        
        
        destroyerPanel.add(destroyerOnePanel);
        destroyerPanel.add(destroyerTwoPanel);
        destroyerPanel.add(createShipEdit("Destroyer"));
        
        //Submarine Tab Panel              
         
        //JComponent submarine = makeShipPanel("Submarine");
        
        JPanel submarinePanel = new JPanel(false);
        JLabel submarineLabel = new JLabel("Submarine");
        submarineLabel.setHorizontalAlignment(JLabel.CENTER);
        submarinePanel.setLayout(new BoxLayout(submarinePanel, BoxLayout.Y_AXIS));
        
        JPanel submarineOnePanel = new JPanel(new FlowLayout());
        
        JLabel submarinePositionOne = new JLabel("Position 1");
        submarineCoordXOne = new JTextField("X Coordinates");
        submarineCoordYOne = new JTextField("Y Coordinates");
        
        submarineOnePanel.add(submarinePositionOne);
        submarineOnePanel.add(submarineCoordYOne);
        submarineOnePanel.add(submarineCoordXOne);
        
        JPanel submarineTwoPanel = new JPanel(new FlowLayout());
        
        JLabel submarinePositionTwo = new JLabel("Position 2");
        submarineCoordXTwo = new JTextField("X Coordinates");
        submarineCoordYTwo = new JTextField("Y Coordinates");
        
        submarineTwoPanel.add(submarinePositionTwo);
        submarineTwoPanel.add(submarineCoordYTwo);
        submarineTwoPanel.add(submarineCoordXTwo);
        
        
        submarinePanel.add(submarineOnePanel);
        submarinePanel.add(submarineTwoPanel);
        submarinePanel.add(createShipEdit("Submarine"));
        
        //Battleship tab panel
        
        JPanel battleshipPanel = new JPanel(false);
        JLabel battleshipLabel = new JLabel("Battleship");
        battleshipLabel.setHorizontalAlignment(JLabel.CENTER);
        battleshipPanel.setLayout(new BoxLayout(battleshipPanel, BoxLayout.Y_AXIS));
        
        JPanel battleshipOnePanel = new JPanel(new FlowLayout());
        
        JLabel battleshipPositionOne = new JLabel("Position 1");
        battleshipCoordXOne = new JTextField("X Coordinates");
        battleshipCoordYOne = new JTextField("Y Coordinates");
        
        battleshipOnePanel.add(battleshipPositionOne);
        battleshipOnePanel.add(battleshipCoordYOne);
        battleshipOnePanel.add(battleshipCoordXOne);
        
        JPanel battleshipTwoPanel = new JPanel(new FlowLayout());
        
        JLabel battleshipPositionTwo = new JLabel("Position 2");
        battleshipCoordXTwo = new JTextField("X Coordinates");
        battleshipCoordYTwo = new JTextField("Y Coordinates");
        
        battleshipTwoPanel.add(battleshipPositionTwo);
        battleshipTwoPanel.add(battleshipCoordYTwo);
        battleshipTwoPanel.add(battleshipCoordXTwo);
        
        
        battleshipPanel.add(battleshipOnePanel);
        battleshipPanel.add(battleshipTwoPanel);
        battleshipPanel.add(createShipEdit("Battleship"));
        
        
        //JComponent battleship = makeShipPanel("Battleship");
        //Carrier tab Panel
        
        JPanel carrierPanel = new JPanel(false);
        JLabel carrierLabel = new JLabel("Carrier");
        carrierLabel.setHorizontalAlignment(JLabel.CENTER);
        carrierPanel.setLayout(new BoxLayout(carrierPanel, BoxLayout.Y_AXIS));
        
        JPanel carrierOnePanel = new JPanel(new FlowLayout());
        
        JLabel carrierPositionOne = new JLabel("Position 1");
        carrierCoordXOne = new JTextField("X Coordinates");
        carrierCoordYOne = new JTextField("Y Coordinates");
        
        carrierOnePanel.add(carrierPositionOne);
        carrierOnePanel.add(carrierCoordYOne);
        carrierOnePanel.add(carrierCoordXOne);
        
        JPanel carrierTwoPanel = new JPanel(new FlowLayout());
        
        JLabel carrierPositionTwo = new JLabel("Position 2");
        carrierCoordXTwo = new JTextField("X Coordinates");
        carrierCoordYTwo = new JTextField("Y Coordinates");
        
        carrierTwoPanel.add(carrierPositionTwo);
        carrierTwoPanel.add(carrierCoordYTwo);
        carrierTwoPanel.add(carrierCoordXTwo);
        
        
        carrierPanel.add(carrierOnePanel);
        carrierPanel.add(carrierTwoPanel);
        carrierPanel.add(createShipEdit("Carrier"));
        
        //JComponent carrier = makeShipPanel("Carrier");        
        
        tabbedPane.addTab("Destroyer", destroyerPanel);
        tabbedPane.addTab("Submarine", submarinePanel);
        tabbedPane.addTab("Battleship", battleshipPanel);
        tabbedPane.addTab("Carrier", carrierPanel);
        
        shipPane.add(tabbedPane);
    }
    
//    protected JComponent makeShipPanel(String textString) {
//        String typeOfShip = textString;
//        JPanel panel = new JPanel(false);
//        JLabel label = new JLabel(textString);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        
//        JPanel onePanel = new JPanel(new FlowLayout());
//        
//        JLabel positionOne = new JLabel("Position 1");
//        JTextField coordXOne = new JTextField("X Coordinates");
//        JTextField coordYOne = new JTextField("Y Coordinates");
//        
//        onePanel.add(positionOne);
//        onePanel.add(coordXOne);
//        onePanel.add(coordYOne);
//        
//        JPanel twoPanel = new JPanel(new FlowLayout());
//        
//        JLabel positionTwo = new JLabel("Position 2");
//        JTextField coordXTwo = new JTextField("X Coordinates");
//        JTextField coordYTwo = new JTextField("Y Coordinates");
//        
//        twoPanel.add(positionTwo);
//        twoPanel.add(coordXTwo);
//        twoPanel.add(coordYTwo);
//        
//        
//        panel.add(onePanel);
//        panel.add(twoPanel);
//        panel.add(createShipEdit(textString));
//        return panel;
//    }
    
    public JPanel createShipEdit(String shipType) {
        JPanel threePanel = new JPanel();
        if(shipType.equals("Destroyer")){
            JPanel destroyerPanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            JButton save = new JButton("Save");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 1;
                    playerDestroyer.zeroCoordinates();
                }
            });
            destroyerPanel.add(edit);
            destroyerPanel.add(save);
            threePanel = destroyerPanel;
        }
        if(shipType.equals("Submarine")){
            JPanel submarinePanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            JButton save = new JButton("Save");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 2;
                    playerSubmarine.zeroCoordinates();
                }
            });
            submarinePanel.add(edit);
            submarinePanel.add(save);
            threePanel = submarinePanel;
        }
        if(shipType.equals("Battleship")){
            JPanel battleshipPanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            JButton save = new JButton("Save");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 3;
                    playerBattleship.zeroCoordinates();
                }
            });
            battleshipPanel.add(edit);
            battleshipPanel.add(save);
            threePanel = battleshipPanel;
        }
        if(shipType.equals("Carrier")){
            JPanel carrierPanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            JButton save = new JButton("Save");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 4;
                    playerCarrier.zeroCoordinates();
                }
            });
            carrierPanel.add(edit);
            carrierPanel.add(save);
            threePanel = carrierPanel;
        }
        return threePanel;
    }
    
    public void setShipCoordinates() {
        if (shipTypeTab == 1) {
            if(playerDestroyer.getPositionX1() == null){
                playerDestroyer.setPositionX1(theShipCoord.getxCoord());
                destroyerCoordXOne.setText(Integer.toString(theShipCoord.getxCoord()));
                playerDestroyer.setPositionY1(theShipCoord.getyCoord());
                destroyerCoordYOne.setText(Integer.toString(theShipCoord.getyCoord()));
            } else {
                if( ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == 1) )
                        || ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == -1) )
                        || ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == 1 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        || ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == -1 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        ) {
                    playerDestroyer.setPositionX2(theShipCoord.getxCoord());
                    destroyerCoordXTwo.setText(Integer.toString(theShipCoord.getxCoord()));
                    playerDestroyer.setPositionY2(theShipCoord.getyCoord());
                    destroyerCoordYTwo.setText(Integer.toString(theShipCoord.getyCoord()));
                } else {
                    System.out.println("Invalid entry.  Destroyers take up 2 grid.");
                }                
            }
            //use theShipCoordinates to set ship coord
        }
        if (shipTypeTab == 2) {
            if(playerSubmarine.getPositionX1() == null){
                playerSubmarine.setPositionX1(theShipCoord.getxCoord());
                submarineCoordXOne.setText(Integer.toString(theShipCoord.getxCoord()));
                playerSubmarine.setPositionY1(theShipCoord.getyCoord());
                submarineCoordYOne.setText(Integer.toString(theShipCoord.getyCoord()));
            } else {
                if( ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == 2) )
                        || ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == -2) )
                        || ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == 2 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        || ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == -2 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        ) {
                    playerSubmarine.setPositionX2(theShipCoord.getxCoord());
                    submarineCoordXTwo.setText(Integer.toString(theShipCoord.getxCoord()));
                    playerSubmarine.setPositionY2(theShipCoord.getyCoord());
                    submarineCoordYTwo.setText(Integer.toString(theShipCoord.getyCoord()));
                } else {
                    System.out.println("Invalid entry.  Submarines take up 3 grid");
                }
                
            }
            //use theShipCoordinates to set ship coord
        }
        if (shipTypeTab == 3) {
            if(playerBattleship.getPositionX1() == null){
                playerBattleship.setPositionX1(theShipCoord.getxCoord());
                battleshipCoordXOne.setText(Integer.toString(theShipCoord.getxCoord()));
                playerBattleship.setPositionY1(theShipCoord.getyCoord());
                battleshipCoordYOne.setText(Integer.toString(theShipCoord.getyCoord()));
            } else {
                if( ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == 3) )
                        || ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == -3) )
                        || ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == 3 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        || ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == -3 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        ) {
                    playerBattleship.setPositionX2(theShipCoord.getxCoord());
                    battleshipCoordXTwo.setText(Integer.toString(theShipCoord.getxCoord()));
                    playerBattleship.setPositionY2(theShipCoord.getyCoord());
                    battleshipCoordYTwo.setText(Integer.toString(theShipCoord.getyCoord()));
                } else {
                    System.out.println("Invalid entry.  Battleships take up 4 grid");
                }
                
            }
            //use theShipCoordinates to set ship coord
        }
        if (shipTypeTab == 4) {
            if(playerCarrier.getPositionX1() == null){
                playerCarrier.setPositionX1(theShipCoord.getxCoord());
                carrierCoordXOne.setText(Integer.toString(theShipCoord.getxCoord()));
                playerCarrier.setPositionY1(theShipCoord.getyCoord());
                carrierCoordYOne.setText(Integer.toString(theShipCoord.getyCoord()));
            } else {
                if( ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == 4) )
                        || ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == -4) )
                        || ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == 4 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        || ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == -4 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                        ) {
                    playerCarrier.setPositionX2(theShipCoord.getxCoord());
                    carrierCoordXTwo.setText(Integer.toString(theShipCoord.getxCoord()));
                    playerCarrier.setPositionY2(theShipCoord.getyCoord());
                    carrierCoordYTwo.setText(Integer.toString(theShipCoord.getyCoord()));
                } else {
                    System.out.println("Invalid entry.  Carriers take up 5 grid");
                }
                
            }
        }
    }
    
    public void createSetupButtons() {
        setupGrid = new JButton[WIDTH][LENGTH];     
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                setupGrid[i][j] = new JButton("(" + j + "," + i + ")");
                gridPane.add(setupGrid[i][j]);
                final int tempI = i;
                final int tempJ = j;
                setupGrid[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Coordinates shipCoord = new Coordinates(tempI, tempJ);
                        theShipCoord = shipCoord;
                        setShipCoordinates();                      
                    }
                });
            }
            
        }
    }
}
