package ca.bcit.comp2613.battleship.model;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.JButton;
import javax.swing.JPanel;

@Entity
public class Board extends JPanel{
    
    @Id
    private static final long serialVersionUID = 1L;
    //variable that will have multiple ship many to many relationship
    private JButton[][] grid;
    private boolean[][] gridFilled;
    private boolean[][] compGrid;
    private boolean[][] clicked;
    
    public static final int WIDTH = 11;
    public static final int LENGTH = 11;
    
    private Destroyer destroyer;
    private Submarine submarine;
    private Battleship battleship;
    private Carrier carrier;
    
    private boolean gameEnd;
    
    private int turn;
    
    private int score;
    private int miss;
    private int hit;
    
    Random rand = new Random();
    
    
    @OneToMany
    private List < Ship > ships;
        
    public Board() {
        setLayout(new GridLayout(WIDTH, LENGTH));
        setSize(850,850);
        turn = 0;
        gameEnd = false;
        hit = 0;
        miss = 0;
        createButtons();
        computerSetup();
        placeCompShips();
    }
    
    public long getBoardId() {
        return serialVersionUID;
    }
    
     public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }    
    
//    public void playGame() {
//        turn++;
//        if(turn % 2 == 1) {
//            //first player move
//        } else {
//            //comp player move
//        }
//    }
//    
//    public void playerMove() {
//        
//    }
    
    public void createButtons() {
        grid = new JButton[WIDTH][LENGTH];   
        gridFilled = new boolean[WIDTH][LENGTH];
        clicked = new boolean[WIDTH][LENGTH];
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                clicked[j][i] = false;
                gridFilled[j][i] = false;
                grid[j][i] = new JButton("(" + j + "," + i + ")");
                add(grid[j][i]);
                final int tempI = i;
                final int tempJ = j;
                grid[j][i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // * is hit
                        // X is missed
                        if(clicked[tempJ][tempI] == false) {
                            clicked[tempJ][tempI] = true;
                            if (gridFilled[tempJ][tempI] == true) {
                                hit++;
                                grid[tempJ][tempI].setText("*");
                                //comp's move
                            } else {
                                miss++;
                                grid[tempJ][tempI].setText("X");
                                //comp's move
                            }
                        } else {
                            System.out.println("You have already checked this grid");
                        }
                        
                    }
                });
            }
        }
    }
    
    public void placeCompShips() {
        int randomOne = rand.nextInt(10) + 1;
        int randomTwo = rand.nextInt(10) + 1;
        int direction = rand.nextInt(3);
        //destroyer comp
        gridFilled[randomOne][randomTwo] = true;
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            destroyer = new Destroyer(randomOne, randomTwo, randomOne+1, randomTwo, 2);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            destroyer = new Destroyer(randomOne, randomTwo, randomOne-1, randomTwo, 2);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            destroyer = new Destroyer(randomOne, randomTwo, randomOne, randomTwo+1, 2);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            destroyer = new Destroyer(randomOne, randomTwo, randomOne, randomTwo-1, 2);
            break;
        }
        
        //submarine comp
        while(gridFilled[randomOne][randomTwo] == true){
            randomOne = rand.nextInt(10) + 1;
            randomTwo = rand.nextInt(10) + 1;
        }
        
        direction = rand.nextInt(3);
        
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            gridFilled[randomOne + 2][randomTwo] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne+2, randomTwo, 3);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            gridFilled[randomOne - 2][randomTwo] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne-2, randomTwo, 3);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            gridFilled[randomOne][randomTwo + 2] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne, randomTwo+2, 3);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            gridFilled[randomOne][randomTwo - 2] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne, randomTwo-2, 3);
            break;
        }
        
        //battleship comp
        while(gridFilled[randomOne][randomTwo] == true){
            randomOne = rand.nextInt(10) + 1;
            randomTwo = rand.nextInt(10) + 1;
        }
        
        direction = rand.nextInt(3);
        
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            gridFilled[randomOne + 2][randomTwo] = true;
            gridFilled[randomOne + 3][randomTwo] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne+3, randomTwo, 4);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            gridFilled[randomOne - 2][randomTwo] = true;
            gridFilled[randomOne - 3][randomTwo] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne-3, randomTwo, 4);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            gridFilled[randomOne][randomTwo + 2] = true;
            gridFilled[randomOne][randomTwo + 3] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne, randomTwo+3, 4);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            gridFilled[randomOne][randomTwo - 2] = true;
            gridFilled[randomOne][randomTwo - 3] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne, randomTwo-3, 4);
            break;
        }
        
        //carrier comp
        while(gridFilled[randomOne][randomTwo] == true){
            randomOne = rand.nextInt(10) + 1;
            randomTwo = rand.nextInt(10) + 1;
        }
        
        direction = rand.nextInt(3);
        
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            gridFilled[randomOne + 2][randomTwo] = true;
            gridFilled[randomOne + 3][randomTwo] = true;
            gridFilled[randomOne + 4][randomTwo] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne+4, randomTwo, 5);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            gridFilled[randomOne - 2][randomTwo] = true;
            gridFilled[randomOne - 3][randomTwo] = true;
            gridFilled[randomOne - 4][randomTwo] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne-4, randomTwo, 5);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            gridFilled[randomOne][randomTwo + 2] = true;
            gridFilled[randomOne][randomTwo + 3] = true;
            gridFilled[randomOne][randomTwo + 4] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne, randomTwo+4, 5);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            gridFilled[randomOne][randomTwo - 2] = true;
            gridFilled[randomOne][randomTwo - 3] = true;
            gridFilled[randomOne][randomTwo - 4] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne, randomTwo-4, 5);
            break;
        }
        
    }
    
    public void computerSetup() {
        compGrid = new boolean[WIDTH][LENGTH];
        compGrid = SetupBoard.getFilled();
    }
}



