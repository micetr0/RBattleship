package ca.bcit.comp2613.battleship.model;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    private static boolean [][] filled;
    
    public static final int WIDTH = 11;
    public static final int LENGTH = 11;
    
    private Coordinates theShipCoord;

    private Coordinates destroyerShipCoordOne;
    private Coordinates destroyerShipCoordTwo;
    private Coordinates submarineShipCoordOne;
    private Coordinates submarineShipCoordTwo;
    private Coordinates battleshipShipCoordOne;
    private Coordinates battleshipShipCoordTwo;
    private Coordinates carrierShipCoordOne;
    private Coordinates carrierShipCoordTwo;
    private int shipTypeTab;
    
    private static Destroyer playerDestroyer;
    private static Submarine playerSubmarine;
    private static Battleship playerBattleship;
    private static Carrier playerCarrier;    
    
    private JTextField textDestroyerCoordXOne;
    private JTextField textDestroyerCoordYOne;
    private JTextField textDestroyerCoordXTwo;
    private JTextField textDestroyerCoordYTwo;
    private JTextField textSubmarineCoordXOne;
    private JTextField textSubmarineCoordYOne;
    private JTextField textSubmarineCoordXTwo;
    private JTextField textSubmarineCoordYTwo;
    private JTextField textBattleshipCoordXOne;
    private JTextField textBattleshipCoordYOne;
    private JTextField textBattleshipCoordXTwo;
    private JTextField textBattleshipCoordYTwo;
    private JTextField textCarrierCoordXOne;
    private JTextField textCarrierCoordYOne;
    private JTextField textCarrierCoordXTwo;
    private JTextField textCarrierCoordYTwo;
    
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
        playerSubmarine = new Submarine(null, null, null, null, null, null, 3);
        //playerSubmarine.zeroCoordinates();
        playerBattleship = new Battleship(null, null, null, null, null, null, null, null, 4);
        //playerBattleship.zeroCoordinates();
        playerCarrier = new Carrier(null, null, null, null, null, null, null, null, null, null, 5);
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
        textDestroyerCoordXOne = new JTextField("X Coordinates");
        textDestroyerCoordXOne.setColumns(10);
        textDestroyerCoordYOne = new JTextField("Y Coordinates");
        textDestroyerCoordYOne.setColumns(10);
        
        destroyerOnePanel.add(destroyerPositionOne);
        destroyerOnePanel.add(textDestroyerCoordXOne);
        destroyerOnePanel.add(textDestroyerCoordYOne);
        
        JPanel destroyerTwoPanel = new JPanel(new FlowLayout());
        
        JLabel destroyerPositionTwo = new JLabel("Position 2");
        textDestroyerCoordXTwo = new JTextField("X Coordinates");
        textDestroyerCoordXTwo.setColumns(10);
        textDestroyerCoordYTwo = new JTextField("Y Coordinates");
        textDestroyerCoordYTwo.setColumns(10);
        
        destroyerTwoPanel.add(destroyerPositionTwo);
        destroyerTwoPanel.add(textDestroyerCoordXTwo);
        destroyerTwoPanel.add(textDestroyerCoordYTwo);
        
        
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
        textSubmarineCoordXOne = new JTextField("X Coordinates");
        textSubmarineCoordXOne.setColumns(10);
        textSubmarineCoordYOne = new JTextField("Y Coordinates");
        textSubmarineCoordYOne.setColumns(10);
        
        submarineOnePanel.add(submarinePositionOne);
        submarineOnePanel.add(textSubmarineCoordXOne);
        submarineOnePanel.add(textSubmarineCoordYOne);
        
        JPanel submarineTwoPanel = new JPanel(new FlowLayout());
        
        JLabel submarinePositionTwo = new JLabel("Position 2");
        textSubmarineCoordXTwo = new JTextField("X Coordinates");
        textSubmarineCoordXTwo.setColumns(10);
        textSubmarineCoordYTwo = new JTextField("Y Coordinates");
        textSubmarineCoordYTwo.setColumns(10);
        
        submarineTwoPanel.add(submarinePositionTwo);
        submarineTwoPanel.add(textSubmarineCoordXTwo);
        submarineTwoPanel.add(textSubmarineCoordYTwo);
        
        
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
        textBattleshipCoordXOne = new JTextField("X Coordinates");
        textBattleshipCoordXOne.setColumns(10);
        textBattleshipCoordYOne = new JTextField("Y Coordinates");
        textBattleshipCoordYOne.setColumns(10);
        
        battleshipOnePanel.add(battleshipPositionOne);
        battleshipOnePanel.add(textBattleshipCoordXOne);
        battleshipOnePanel.add(textBattleshipCoordYOne);
        
        JPanel battleshipTwoPanel = new JPanel(new FlowLayout());
        
        JLabel battleshipPositionTwo = new JLabel("Position 2");
        textBattleshipCoordXTwo = new JTextField("X Coordinates");
        textBattleshipCoordXTwo.setColumns(10);
        textBattleshipCoordYTwo = new JTextField("Y Coordinates");
        textBattleshipCoordYTwo.setColumns(10);
        
        battleshipTwoPanel.add(battleshipPositionTwo);
        battleshipTwoPanel.add(textBattleshipCoordXTwo);
        battleshipTwoPanel.add(textBattleshipCoordYTwo);
        
        
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
        textCarrierCoordXOne = new JTextField("X Coordinates");
        textCarrierCoordXOne.setColumns(10);
        textCarrierCoordYOne = new JTextField("Y Coordinates");
        textCarrierCoordYOne.setColumns(10);
        
        carrierOnePanel.add(carrierPositionOne);
        carrierOnePanel.add(textCarrierCoordXOne);
        carrierOnePanel.add(textCarrierCoordYOne);
        
        JPanel carrierTwoPanel = new JPanel(new FlowLayout());
        
        JLabel carrierPositionTwo = new JLabel("Position 2");
        textCarrierCoordXTwo = new JTextField("X Coordinates");
        textCarrierCoordXTwo.setColumns(10);
        textCarrierCoordYTwo = new JTextField("Y Coordinates");
        textCarrierCoordYTwo.setColumns(10);
        
        carrierTwoPanel.add(carrierPositionTwo);
        carrierTwoPanel.add(textCarrierCoordXTwo);
        carrierTwoPanel.add(textCarrierCoordYTwo);
        
        
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
            final JPanel destroyerPanel = new JPanel(new FlowLayout());
            
            final JButton edit = new JButton("Edit");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 1;
                    playerDestroyer.zeroCoordinates();
                    if(destroyerShipCoordOne != null) {
                        setupGrid[destroyerShipCoordOne.getxCoord()][destroyerShipCoordOne.getyCoord()].setText("");
                        setupGrid[destroyerShipCoordTwo.getxCoord()][destroyerShipCoordTwo.getyCoord()].setText("");
                        filled[destroyerShipCoordOne.getxCoord()][destroyerShipCoordOne.getyCoord()] = false;
                        filled[destroyerShipCoordTwo.getxCoord()][destroyerShipCoordTwo.getyCoord()] = false;
                    }
                    textDestroyerCoordXOne.setText("X Coordinates");
                    textDestroyerCoordYOne.setText("Y Coordinates");
                    textDestroyerCoordXTwo.setText("X Coordinates");
                    textDestroyerCoordYTwo.setText("Y Coordinates");
                }
            });
            destroyerPanel.add(edit);
            final JButton startGame = new JButton("Start");
            startGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    startGame();
                    destroyerPanel.remove(startGame);
                }
            });
            
            destroyerPanel.add(startGame);
            threePanel = destroyerPanel;
        }
        if(shipType.equals("Submarine")){
            JPanel submarinePanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 2;
                    playerSubmarine.zeroCoordinates();
                    if(submarineShipCoordOne != null) {
                        setupGrid[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord()].setText("");
                        setupGrid[submarineShipCoordTwo.getxCoord()][submarineShipCoordTwo.getyCoord()].setText("");
                        if ( (submarineShipCoordOne.getxCoord() == submarineShipCoordTwo.getxCoord())  &&  ( (submarineShipCoordOne.getyCoord() - submarineShipCoordTwo.getyCoord()) < 0 ) ) {
                            setupGrid[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() + 1].setText("");
                            filled[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() + 1] = false;
                        }
                        if ( (submarineShipCoordOne.getxCoord() == submarineShipCoordTwo.getxCoord())  &&  ( (submarineShipCoordOne.getyCoord() - submarineShipCoordTwo.getyCoord()) > 0 ) ) {
                            setupGrid[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() - 1].setText("");
                            filled[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() - 1] = false;
                        } 
                        if ( ((submarineShipCoordOne.getxCoord() - submarineShipCoordTwo.getxCoord()) < 0)  &&  ( submarineShipCoordOne.getyCoord() == submarineShipCoordTwo.getyCoord()) )  {
                            setupGrid[submarineShipCoordOne.getxCoord() + 1][submarineShipCoordOne.getyCoord()].setText("");
                            filled[submarineShipCoordOne.getxCoord() + 1][submarineShipCoordOne.getyCoord()] = false;
                        }
                        if ( ((submarineShipCoordOne.getxCoord() - submarineShipCoordTwo.getxCoord()) > 0)  &&  ( (submarineShipCoordOne.getyCoord() == submarineShipCoordTwo.getyCoord()) ) ) {
                            setupGrid[submarineShipCoordOne.getxCoord() - 1][submarineShipCoordOne.getyCoord()].setText("");
                            filled[submarineShipCoordOne.getxCoord() - 1][submarineShipCoordOne.getyCoord()] = false;
                        }
                    }
                    textSubmarineCoordXOne.setText("X Coordinates");
                    textSubmarineCoordYOne.setText("Y Coordinates");
                    textSubmarineCoordXTwo.setText("X Coordinates");
                    textSubmarineCoordYTwo.setText("Y Coordinates");
                }
            });
            submarinePanel.add(edit);
            threePanel = submarinePanel;
        }
        if(shipType.equals("Battleship")){
            JPanel battleshipPanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 3;
                    playerBattleship.zeroCoordinates();
                    if (battleshipShipCoordOne != null) {
                        setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord()].setText("");
                        filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord()] = false;
                        setupGrid[battleshipShipCoordTwo.getxCoord()][battleshipShipCoordTwo.getyCoord()].setText("");
                        filled[battleshipShipCoordTwo.getxCoord()][battleshipShipCoordTwo.getyCoord()] = false;
                        if ( (battleshipShipCoordOne.getxCoord() == battleshipShipCoordTwo.getxCoord())  &&  ( (battleshipShipCoordOne.getyCoord() - battleshipShipCoordTwo.getyCoord()) < 0 ) ) {
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 1].setText("");
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 2].setText("");
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 1] = false;
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 2] = false;
                        }
                        if ( (battleshipShipCoordOne.getxCoord() == battleshipShipCoordTwo.getxCoord())  &&  ( (battleshipShipCoordOne.getyCoord() - battleshipShipCoordTwo.getyCoord()) > 0 ) ) {
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 1].setText("");
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 1] = false;
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 2].setText("");
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 2] = false;
                        } 
                        if ( ((battleshipShipCoordOne.getxCoord() - battleshipShipCoordTwo.getxCoord()) < 0)  &&  ( battleshipShipCoordOne.getyCoord() == battleshipShipCoordTwo.getyCoord()) )  {
                            setupGrid[battleshipShipCoordOne.getxCoord() + 1][battleshipShipCoordOne.getyCoord()].setText("");
                            filled[battleshipShipCoordOne.getxCoord() + 1][battleshipShipCoordOne.getyCoord()] = false;
                            setupGrid[battleshipShipCoordOne.getxCoord() + 2][battleshipShipCoordOne.getyCoord()].setText("");
                            filled[battleshipShipCoordOne.getxCoord() + 2][battleshipShipCoordOne.getyCoord()] = false;
                        }
                        if ( ((battleshipShipCoordOne.getxCoord() - battleshipShipCoordTwo.getxCoord()) > 0)  &&  ( (battleshipShipCoordOne.getyCoord() == battleshipShipCoordTwo.getyCoord()) ) ) {
                            setupGrid[battleshipShipCoordOne.getxCoord() - 1][battleshipShipCoordOne.getyCoord()].setText("");
                            filled[battleshipShipCoordOne.getxCoord() - 1][battleshipShipCoordOne.getyCoord()] = false;
                            setupGrid[battleshipShipCoordOne.getxCoord() - 2][battleshipShipCoordOne.getyCoord()].setText("");
                            filled[battleshipShipCoordOne.getxCoord() - 2][battleshipShipCoordOne.getyCoord()] = false;
                        }
                    }
                    textBattleshipCoordXOne.setText("X Coordinates");
                    textBattleshipCoordYOne.setText("Y Coordinates");
                    textBattleshipCoordXTwo.setText("X Coordinates");
                    textBattleshipCoordYTwo.setText("Y Coordinates");
                }
            });
            battleshipPanel.add(edit);
            threePanel = battleshipPanel;
        }
        if(shipType.equals("Carrier")){
            JPanel carrierPanel = new JPanel(new FlowLayout());
            
            JButton edit = new JButton("Edit");
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shipTypeTab = 4;
                    playerCarrier.zeroCoordinates();
                    if (carrierShipCoordOne != null){
                        setupGrid[carrierShipCoordTwo.getxCoord()][carrierShipCoordTwo.getyCoord()].setText("");
                        filled[carrierShipCoordTwo.getxCoord()][carrierShipCoordTwo.getyCoord()] = false;
                        setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord()].setText("");
                        filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord()] = false;
                        if ( (carrierShipCoordOne.getxCoord() == carrierShipCoordTwo.getxCoord())  &&  ( (carrierShipCoordOne.getyCoord() - carrierShipCoordTwo.getyCoord()) < 0 ) ) {
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 1].setText("");
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 2].setText("");
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 3].setText("");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 1] = false;
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 2] = false;
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 3] = false;
                        }
                        if ( (carrierShipCoordOne.getxCoord() == carrierShipCoordTwo.getxCoord())  &&  ( (carrierShipCoordOne.getyCoord() - carrierShipCoordTwo.getyCoord()) > 0 ) ) {
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 1].setText("");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 1] = false;
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 2].setText("");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 2] = false;
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 3].setText("");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 3] = false;
                        } 
                        if ( ((carrierShipCoordOne.getxCoord() - carrierShipCoordTwo.getxCoord()) < 0)  &&  ( carrierShipCoordOne.getyCoord() == carrierShipCoordTwo.getyCoord()) )  {
                            setupGrid[carrierShipCoordOne.getxCoord() + 1][carrierShipCoordOne.getyCoord()].setText("");
                            filled[carrierShipCoordOne.getxCoord() + 1][carrierShipCoordOne.getyCoord()] = false;
                            setupGrid[carrierShipCoordOne.getxCoord() + 2][carrierShipCoordOne.getyCoord()].setText("");
                            filled[carrierShipCoordOne.getxCoord() + 2][carrierShipCoordOne.getyCoord()] = false;
                            setupGrid[carrierShipCoordOne.getxCoord() + 3][carrierShipCoordOne.getyCoord()].setText("");
                            filled[carrierShipCoordOne.getxCoord() + 3][carrierShipCoordOne.getyCoord()] = false;
                        }
                        if ( ((carrierShipCoordOne.getxCoord() - carrierShipCoordTwo.getxCoord()) > 0)  &&  ( (carrierShipCoordOne.getyCoord() == carrierShipCoordTwo.getyCoord()) ) ) {
                            setupGrid[carrierShipCoordOne.getxCoord() - 1][carrierShipCoordOne.getyCoord()].setText("");
                            filled[carrierShipCoordOne.getxCoord() - 1][carrierShipCoordOne.getyCoord()] = false;
                            setupGrid[carrierShipCoordOne.getxCoord() - 2][carrierShipCoordOne.getyCoord()].setText("");
                            filled[carrierShipCoordOne.getxCoord() - 2][carrierShipCoordOne.getyCoord()] = false;
                            setupGrid[carrierShipCoordOne.getxCoord() - 3][carrierShipCoordOne.getyCoord()].setText("");
                            filled[carrierShipCoordOne.getxCoord() - 3][carrierShipCoordOne.getyCoord()] = false;
                        }
                    }
                    textCarrierCoordXOne.setText("X Coordinates");
                    textCarrierCoordYOne.setText("Y Coordinates");
                    textCarrierCoordXTwo.setText("X Coordinates");
                    textCarrierCoordYTwo.setText("Y Coordinates");
                }
            });
            carrierPanel.add(edit);
            threePanel = carrierPanel;
        }
        return threePanel;
    }
    
    public void startGame() {
        if( (playerDestroyer.getPositionX1() == null) || (playerSubmarine.getPositionX1() == null) || (playerBattleship.getPositionX1() == null) || (playerCarrier.getPositionX1() == null)) {
            System.out.println("Please place all ships");
        } else {
            Board board = new Board();
            JPanel holder = new JPanel();
            holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
            JPanel ctrlPanel = new JPanel();
            JButton viewSetup = new JButton("View Ships");
            viewSetup.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(Menu.getFrameVisibility() == false){
                        Menu.setupFrameVisibility(true);
                    } else {
                        Menu.setupFrameVisibility(false);
                    }
                }
            });
            ctrlPanel.setSize(600, 100);
            ctrlPanel.add(viewSetup);
            JFrame f = new JFrame("BattleShip");
            holder.add(board);
            holder.add(ctrlPanel);
            f.setSize(600, 700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
            f.add(holder);
//            f.add(ctrlPanel);
            Menu.setupFrameVisibility(false);
        }
    }
    
    public void setShipCoordinates() {
        if(filled[theShipCoord.getxCoord()][theShipCoord.getyCoord()] == false){
            if (shipTypeTab == 1) {
                if(playerDestroyer.getPositionX1() == null){
                    destroyerShipCoordOne = theShipCoord;
                    playerDestroyer.setPositionX1(destroyerShipCoordOne.getxCoord());
                    textDestroyerCoordXOne.setText(Integer.toString(destroyerShipCoordOne.getxCoord()));
                    playerDestroyer.setPositionY1(destroyerShipCoordOne.getyCoord());
                    textDestroyerCoordYOne.setText(Integer.toString(destroyerShipCoordOne.getyCoord()));
                    setupGrid[destroyerShipCoordOne.getxCoord()][destroyerShipCoordOne.getyCoord()].setText("Ship");
                    filled[destroyerShipCoordOne.getxCoord()][destroyerShipCoordOne.getyCoord()] = true;
                } else if (playerDestroyer.getPositionX2() == null) {
                    if( ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == 1) )
                            || ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == -1) )
                            || ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == 1 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            || ( ((playerDestroyer.getPositionX1() - theShipCoord.getxCoord() ) == -1 ) && ((playerDestroyer.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            ) {
                        destroyerShipCoordTwo = theShipCoord;
                        playerDestroyer.setPositionX2(destroyerShipCoordTwo.getxCoord());
                        textDestroyerCoordXTwo.setText(Integer.toString(destroyerShipCoordTwo.getxCoord()));
                        playerDestroyer.setPositionY2(destroyerShipCoordTwo.getyCoord());
                        textDestroyerCoordYTwo.setText(Integer.toString(destroyerShipCoordTwo.getyCoord()));
                        setupGrid[destroyerShipCoordTwo.getxCoord()][destroyerShipCoordTwo.getyCoord()].setText("Ship");
                        filled[destroyerShipCoordTwo.getxCoord()][destroyerShipCoordTwo.getyCoord()] = true;
                        
//                        System.out.println(playerDestroyer.getPositionY1());
//                        System.out.println(playerDestroyer.getPositionX1());
//                        System.out.println(playerDestroyer.getPositionY2());
//                        System.out.println(playerDestroyer.getPositionX2());
                    } else {
                        System.out.println("Invalid entry.  Destroyers take up 2 grid.");
                        textDestroyerCoordXTwo.setText("X Coordinates");
                        textDestroyerCoordYTwo.setText("Y Coordinates");
                    }                
                }
                //use theShipCoordinates to set ship coord
            }
            if (shipTypeTab == 2) {
                if(playerSubmarine.getPositionX1() == null){
                    submarineShipCoordOne = theShipCoord;
                    playerSubmarine.setPositionX1(submarineShipCoordOne.getxCoord());
                    textSubmarineCoordXOne.setText(Integer.toString(submarineShipCoordOne.getxCoord()));
                    playerSubmarine.setPositionY1(submarineShipCoordOne.getyCoord());
                    textSubmarineCoordYOne.setText(Integer.toString(submarineShipCoordOne.getyCoord()));
                    setupGrid[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord()].setText("Ship");
                } else if (playerSubmarine.getPositionX2() == null){
                    if( ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == 2) )
                            || ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == -2) )
                            || ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == 2 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            || ( ((playerSubmarine.getPositionX1() - theShipCoord.getxCoord() ) == -2 ) && ((playerSubmarine.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            ) {
                        submarineShipCoordTwo = theShipCoord;
                        playerSubmarine.setPositionX3(submarineShipCoordTwo.getxCoord());
                        textSubmarineCoordXTwo.setText(Integer.toString(submarineShipCoordTwo.getxCoord()));
                        playerSubmarine.setPositionY3(submarineShipCoordTwo.getyCoord());
                        textSubmarineCoordYTwo.setText(Integer.toString(submarineShipCoordTwo.getyCoord()));
                        setupGrid[submarineShipCoordTwo.getxCoord()][submarineShipCoordTwo.getyCoord()].setText("Ship");
                        if ( (submarineShipCoordOne.getxCoord() == submarineShipCoordTwo.getxCoord())  &&  ( (submarineShipCoordOne.getyCoord() - submarineShipCoordTwo.getyCoord()) < 0 ) ) {
                            setupGrid[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() + 1].setText("Ship");
                            filled[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() + 1] = true;
                            playerSubmarine.setPositionX2(submarineShipCoordOne.getxCoord());
                            playerSubmarine.setPositionY2(submarineShipCoordOne.getyCoord()+1);
                        }
                        if ( (submarineShipCoordOne.getxCoord() == submarineShipCoordTwo.getxCoord())  &&  ( (submarineShipCoordOne.getyCoord() - submarineShipCoordTwo.getyCoord()) > 0 ) ) {
                            setupGrid[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() - 1].setText("Ship");
                            filled[submarineShipCoordOne.getxCoord()][submarineShipCoordOne.getyCoord() - 1] = true;
                            playerSubmarine.setPositionX2(submarineShipCoordOne.getxCoord());
                            playerSubmarine.setPositionY2(submarineShipCoordOne.getyCoord()-1);
                        } 
                        if ( ((submarineShipCoordOne.getxCoord() - submarineShipCoordTwo.getxCoord()) < 0)  &&  ( submarineShipCoordOne.getyCoord() == submarineShipCoordTwo.getyCoord()) )  {
                            setupGrid[submarineShipCoordOne.getxCoord() + 1][submarineShipCoordOne.getyCoord()].setText("Ship");
                            filled[submarineShipCoordOne.getxCoord() + 1][submarineShipCoordOne.getyCoord()] = true;
                            playerSubmarine.setPositionX2(submarineShipCoordOne.getxCoord()+1);
                            playerSubmarine.setPositionY2(submarineShipCoordOne.getyCoord());
                        }
                        if ( ((submarineShipCoordOne.getxCoord() - submarineShipCoordTwo.getxCoord()) > 0)  &&  ( (submarineShipCoordOne.getyCoord() == submarineShipCoordTwo.getyCoord()) ) ) {
                            setupGrid[submarineShipCoordOne.getxCoord() - 1][submarineShipCoordOne.getyCoord()].setText("Ship");
                            filled[submarineShipCoordOne.getxCoord() - 1][submarineShipCoordOne.getyCoord()] = true;
                            playerSubmarine.setPositionX2(submarineShipCoordOne.getxCoord()-1);
                            playerSubmarine.setPositionY2(submarineShipCoordOne.getyCoord());
                        }
                    } else {
                        System.out.println("Invalid entry.  Submarines take up 3 grid");
                        textSubmarineCoordXTwo.setText("X Coordinates");
                        textSubmarineCoordYTwo.setText("Y Coordinates");
                    }
                    
                }
                //use theShipCoordinates to set ship coord
            }
            if (shipTypeTab == 3) {
                if(playerBattleship.getPositionX1() == null){
                    battleshipShipCoordOne = theShipCoord;
                    playerBattleship.setPositionX1(battleshipShipCoordOne.getxCoord());
                    textBattleshipCoordXOne.setText(Integer.toString(battleshipShipCoordOne.getxCoord()));
                    playerBattleship.setPositionY1(battleshipShipCoordOne.getyCoord());
                    textBattleshipCoordYOne.setText(Integer.toString(battleshipShipCoordOne.getyCoord()));
                    setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord()].setText("Ship");
                    filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord()] = true;
                } else if (playerBattleship.getPositionX2() == null) {
                    if( ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == 3) )
                            || ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == -3) )
                            || ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == 3 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            || ( ((playerBattleship.getPositionX1() - theShipCoord.getxCoord() ) == -3 ) && ((playerBattleship.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            ) {
                        battleshipShipCoordTwo = theShipCoord;
                        playerBattleship.setPositionX4(battleshipShipCoordTwo.getxCoord());
                        textBattleshipCoordXTwo.setText(Integer.toString(battleshipShipCoordTwo.getxCoord()));
                        playerBattleship.setPositionY4(battleshipShipCoordTwo.getyCoord());
                        textBattleshipCoordYTwo.setText(Integer.toString(battleshipShipCoordTwo.getyCoord()));
                        setupGrid[battleshipShipCoordTwo.getxCoord()][battleshipShipCoordTwo.getyCoord()].setText("Ship");
                        filled[battleshipShipCoordTwo.getxCoord()][battleshipShipCoordTwo.getyCoord()] = true;
                        if ( (battleshipShipCoordOne.getxCoord() == battleshipShipCoordTwo.getxCoord())  &&  ( (battleshipShipCoordOne.getyCoord() - battleshipShipCoordTwo.getyCoord()) < 0 ) ) {
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 1].setText("Ship");
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 2].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 1] = true;
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() + 2] = true;
                            playerBattleship.setPositionX2(battleshipShipCoordOne.getxCoord());
                            playerBattleship.setPositionY2(battleshipShipCoordOne.getyCoord()+1);
                            playerBattleship.setPositionX3(battleshipShipCoordOne.getxCoord());
                            playerBattleship.setPositionY3(battleshipShipCoordOne.getyCoord()+2);
                            
                        }
                        if ( (battleshipShipCoordOne.getxCoord() == battleshipShipCoordTwo.getxCoord())  &&  ( (battleshipShipCoordOne.getyCoord() - battleshipShipCoordTwo.getyCoord()) > 0 ) ) {
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 1].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 1] = true;
                            setupGrid[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 2].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord()][battleshipShipCoordOne.getyCoord() - 2] = true;
                            playerBattleship.setPositionX2(battleshipShipCoordOne.getxCoord());
                            playerBattleship.setPositionY2(battleshipShipCoordOne.getyCoord()-1);
                            playerBattleship.setPositionX3(battleshipShipCoordOne.getxCoord());
                            playerBattleship.setPositionY3(battleshipShipCoordOne.getyCoord()-2);
                        } 
                        if ( ((battleshipShipCoordOne.getxCoord() - battleshipShipCoordTwo.getxCoord()) < 0)  &&  ( battleshipShipCoordOne.getyCoord() == battleshipShipCoordTwo.getyCoord()) )  {
                            setupGrid[battleshipShipCoordOne.getxCoord() + 1][battleshipShipCoordOne.getyCoord()].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord() + 1][battleshipShipCoordOne.getyCoord()] = true;
                            setupGrid[battleshipShipCoordOne.getxCoord() + 2][battleshipShipCoordOne.getyCoord()].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord() + 2][battleshipShipCoordOne.getyCoord()] = true;
                            playerBattleship.setPositionX2(battleshipShipCoordOne.getxCoord()+1);
                            playerBattleship.setPositionY2(battleshipShipCoordOne.getyCoord());
                            playerBattleship.setPositionX3(battleshipShipCoordOne.getxCoord()+2);
                            playerBattleship.setPositionY3(battleshipShipCoordOne.getyCoord());
                        }
                        if ( ((battleshipShipCoordOne.getxCoord() - battleshipShipCoordTwo.getxCoord()) > 0)  &&  ( (battleshipShipCoordOne.getyCoord() == battleshipShipCoordTwo.getyCoord()) ) ) {
                            setupGrid[battleshipShipCoordOne.getxCoord() - 1][battleshipShipCoordOne.getyCoord()].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord() - 1][battleshipShipCoordOne.getyCoord()] = true;
                            setupGrid[battleshipShipCoordOne.getxCoord() - 2][battleshipShipCoordOne.getyCoord()].setText("Ship");
                            filled[battleshipShipCoordOne.getxCoord() - 2][battleshipShipCoordOne.getyCoord()] = true;
                            playerBattleship.setPositionX2(battleshipShipCoordOne.getxCoord()-1);
                            playerBattleship.setPositionY2(battleshipShipCoordOne.getyCoord());
                            playerBattleship.setPositionX3(battleshipShipCoordOne.getxCoord()-2);
                            playerBattleship.setPositionY3(battleshipShipCoordOne.getyCoord());
                        }
                    } else {
                        System.out.println("Invalid entry.  Battleships take up 4 grid");
                        textBattleshipCoordXTwo.setText("X Coordinates");
                        textBattleshipCoordYTwo.setText("Y Coordinates");
                    }
                    
                }
                //use theShipCoordinates to set ship coord
            }
            if (shipTypeTab == 4) {
                if(playerCarrier.getPositionX1() == null){
                    carrierShipCoordOne = theShipCoord;
                    playerCarrier.setPositionX1(carrierShipCoordOne.getxCoord());
                    textCarrierCoordXOne.setText(Integer.toString(carrierShipCoordOne.getxCoord()));
                    playerCarrier.setPositionY1(carrierShipCoordOne.getyCoord());
                    textCarrierCoordYOne.setText(Integer.toString(carrierShipCoordOne.getyCoord()));
                    setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord()].setText("Ship");
                    filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord()] = true;
                } else if (playerCarrier.getPositionX2() == null){
                    if( ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == 4) )
                            || ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == 0 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == -4) )
                            || ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == 4 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            || ( ((playerCarrier.getPositionX1() - theShipCoord.getxCoord() ) == -4 ) && ((playerCarrier.getPositionY1() - theShipCoord.getyCoord()) == 0) )
                            ) {
                        carrierShipCoordTwo = theShipCoord;
                        playerCarrier.setPositionX5(carrierShipCoordTwo.getxCoord());
                        textCarrierCoordXTwo.setText(Integer.toString(carrierShipCoordTwo.getxCoord()));
                        playerCarrier.setPositionY5(carrierShipCoordTwo.getyCoord());
                        textCarrierCoordYTwo.setText(Integer.toString(carrierShipCoordTwo.getyCoord()));
                        setupGrid[carrierShipCoordTwo.getxCoord()][carrierShipCoordTwo.getyCoord()].setText("Ship");
                        filled[carrierShipCoordTwo.getxCoord()][carrierShipCoordTwo.getyCoord()] = true;
                        if ( (carrierShipCoordOne.getxCoord() == carrierShipCoordTwo.getxCoord())  &&  ( (carrierShipCoordOne.getyCoord() - carrierShipCoordTwo.getyCoord()) < 0 ) ) {
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 1].setText("Ship");
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 2].setText("Ship");
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 3].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 1] = true;
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 2] = true;
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() + 3] = true;
                            playerCarrier.setPositionX2(carrierShipCoordOne.getxCoord());
                            playerCarrier.setPositionY2(carrierShipCoordOne.getyCoord()+1);
                            playerCarrier.setPositionX3(carrierShipCoordOne.getxCoord());
                            playerCarrier.setPositionY3(carrierShipCoordOne.getyCoord()+2);
                            playerCarrier.setPositionX4(carrierShipCoordOne.getxCoord());
                            playerCarrier.setPositionY4(carrierShipCoordOne.getyCoord()+3);
                        }
                        if ( (carrierShipCoordOne.getxCoord() == carrierShipCoordTwo.getxCoord())  &&  ( (carrierShipCoordOne.getyCoord() - carrierShipCoordTwo.getyCoord()) > 0 ) ) {
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 1].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 1] = true;
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 2].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 2] = true;
                            setupGrid[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 3].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord()][carrierShipCoordOne.getyCoord() - 3] = true;
                            playerCarrier.setPositionX2(carrierShipCoordOne.getxCoord());
                            playerCarrier.setPositionY2(carrierShipCoordOne.getyCoord()-1);
                            playerCarrier.setPositionX3(carrierShipCoordOne.getxCoord());
                            playerCarrier.setPositionY3(carrierShipCoordOne.getyCoord()-2);
                            playerCarrier.setPositionX4(carrierShipCoordOne.getxCoord());
                            playerCarrier.setPositionY4(carrierShipCoordOne.getyCoord()-3);
                        } 
                        if ( ((carrierShipCoordOne.getxCoord() - carrierShipCoordTwo.getxCoord()) < 0)  &&  ( carrierShipCoordOne.getyCoord() == carrierShipCoordTwo.getyCoord()) )  {
                            setupGrid[carrierShipCoordOne.getxCoord() + 1][carrierShipCoordOne.getyCoord()].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord() + 1][carrierShipCoordOne.getyCoord()] = true;
                            setupGrid[carrierShipCoordOne.getxCoord() + 2][carrierShipCoordOne.getyCoord()].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord() + 2][carrierShipCoordOne.getyCoord()] = true;
                            setupGrid[carrierShipCoordOne.getxCoord() + 3][carrierShipCoordOne.getyCoord()].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord() + 3][carrierShipCoordOne.getyCoord()] = true;
                            playerCarrier.setPositionX2(carrierShipCoordOne.getxCoord()+1);
                            playerCarrier.setPositionY2(carrierShipCoordOne.getyCoord());
                            playerCarrier.setPositionX3(carrierShipCoordOne.getxCoord()+2);
                            playerCarrier.setPositionY3(carrierShipCoordOne.getyCoord());
                            playerCarrier.setPositionX4(carrierShipCoordOne.getxCoord()+3);
                            playerCarrier.setPositionY4(carrierShipCoordOne.getyCoord());
                        }
                        if ( ((carrierShipCoordOne.getxCoord() - carrierShipCoordTwo.getxCoord()) > 0)  &&  ( (carrierShipCoordOne.getyCoord() == carrierShipCoordTwo.getyCoord()) ) ) {
                            setupGrid[carrierShipCoordOne.getxCoord() - 1][carrierShipCoordOne.getyCoord()].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord() - 1][carrierShipCoordOne.getyCoord()] = true;
                            setupGrid[carrierShipCoordOne.getxCoord() - 2][carrierShipCoordOne.getyCoord()].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord() - 2][carrierShipCoordOne.getyCoord()] = true;
                            setupGrid[carrierShipCoordOne.getxCoord() - 3][carrierShipCoordOne.getyCoord()].setText("Ship");
                            filled[carrierShipCoordOne.getxCoord() - 3][carrierShipCoordOne.getyCoord()] = true;
                            playerCarrier.setPositionX2(carrierShipCoordOne.getxCoord()-1);
                            playerCarrier.setPositionY2(carrierShipCoordOne.getyCoord());
                            playerCarrier.setPositionX3(carrierShipCoordOne.getxCoord()-2);
                            playerCarrier.setPositionY3(carrierShipCoordOne.getyCoord());
                            playerCarrier.setPositionX4(carrierShipCoordOne.getxCoord()-3);
                            playerCarrier.setPositionY4(carrierShipCoordOne.getyCoord());
                        }
                    } else {
                        System.out.println("Invalid entry.  Carriers take up 5 grid");
                        textCarrierCoordXTwo.setText("X Coordinates");
                        textCarrierCoordYTwo.setText("Y Coordinates");
                    }
                    
                }
            }
        } else {
            System.out.println("Space is occupied");
        }
        
    }
    
    public void createSetupButtons() {
        setupGrid = new JButton[WIDTH][LENGTH];     
        filled = new boolean[WIDTH][LENGTH];
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                if( i == 0 || j == 0) {
                    setupGrid[j][i] = new JButton("(" + j + "," + i + ")");
                    filled[j][i] = false;
                    gridPane.add(setupGrid[j][i]);
                } else {
                    filled[j][i] = false;
                    setupGrid[j][i] = new JButton("");
                    gridPane.add(setupGrid[j][i]);
                    final int tempI = i;
                    final int tempJ = j;
                    setupGrid[j][i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Coordinates shipCoord = new Coordinates(tempJ, tempI);
                            theShipCoord = shipCoord;
                            setShipCoordinates();                      
                        }
                    });
                }
            }
        }
    }

    public static boolean[][] getFilled() {
        return filled;
    }

    public void setFilled(boolean[][] filled) {
        this.filled = filled;
    }

    public static Destroyer getPlayerDestroyer() {
        return playerDestroyer;
    }

    public void setPlayerDestroyer(Destroyer playerDestroyer) {
        this.playerDestroyer = playerDestroyer;
    }

    public static Submarine getPlayerSubmarine() {
        return playerSubmarine;
    }

    public void setPlayerSubmarine(Submarine playerSubmarine) {
        this.playerSubmarine = playerSubmarine;
    }

    public static Battleship getPlayerBattleship() {
        return playerBattleship;
    }

    public void setPlayerBattleship(Battleship playerBattleship) {
        this.playerBattleship = playerBattleship;
    }

    public static Carrier getPlayerCarrier() {
        return playerCarrier;
    }

    public void setPlayerCarrier(Carrier playerCarrier) {
        this.playerCarrier = playerCarrier;
    }
    
    
    
}
