package ca.bcit.comp2613.battleship.model;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private boolean[][] compSelected;
    
    public static final int WIDTH = 11;
    public static final int LENGTH = 11;
    
    private Destroyer destroyer;
    private Submarine submarine;
    private Battleship battleship;
    private Carrier carrier;
    
    private boolean gameEnd;
    private boolean playerWin;
    private boolean compWin;
    
    private int turn;
    
    private int score;
    private int miss;
    private int hit;
    private int compMiss;
    private int compHit;
    
    Random rand = new Random();
    
    
    @OneToMany
    private List < Ship > ships;
        
    public Board() {
        setLayout(new GridLayout(WIDTH, LENGTH));
        setSize(850,850);
        turn = 0;
        gameEnd = false;
        playerWin = false;
        compWin = false;
        hit = 0;
        miss = 0;
        compHit = 0;
        compMiss = 0;
        createButtons();
        placeCompShips();
        computerSetup();
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
    
    public void compMove() {
        int compMoveOne = rand.nextInt(10) + 1;
        int compMoveTwo = rand.nextInt(10) + 1;
        while (compSelected[compMoveOne][compMoveTwo] == true) {
            compMoveOne = rand.nextInt(10) + 1;
            compMoveTwo = rand.nextInt(10) + 1;
        }
        compSelected[compMoveOne][compMoveTwo] = true;
        if (compGrid[compMoveOne][compMoveTwo] == true) {
            compHit++;
        } else {
            compMiss++;
        }
        //check if comp hit all player's ship.  All compGrid == compSelected.  means computer finished.
        checkCompWin();
        if (gameEnd == true) {
            gameEnd();
        }
    }
    
    public void checkCompWin() {
        if(Arrays.equals(compGrid, compSelected)) {
            gameEnd = true;
            compWin = true;
        }
    }
    
    public void checkPlayerWin() {
        if(Arrays.equals(clicked, gridFilled)) {
            gameEnd = true;
            playerWin = true;
        }
    }
    
    public void createButtons() {
        grid = new JButton[WIDTH][LENGTH];   
        gridFilled = new boolean[WIDTH][LENGTH];
        clicked = new boolean[WIDTH][LENGTH];
        compSelected = new boolean[WIDTH][LENGTH];
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                clicked[j][i] = false;
                compSelected[j][i] = false;
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
                                compMove();
                            } else {
                                miss++;
                                grid[tempJ][tempI].setText("X");
                                compMove();
                            }
                        } else {
                            System.out.println("You have already checked this grid");
                        }
                        //run check if all ships are hit. all gridFilled == clicked.  means player finished.  
                        checkPlayerWin();
                        System.out.println(gameEnd);
                        if(gameEnd == true) {
                            gameEnd();
                        }
                    }
                });
            }
        }
        System.out.println(Arrays.equals(clicked, gridFilled));
    }
    
    public void gameEnd() {
        System.out.println("game end method");
        JFrame endGame = new JFrame("test");
        endGame.setSize(250,250);
        endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endGame.setVisible(true);
        JPanel end = new JPanel();
        JLabel winner = new JLabel("");
        if(playerWin == true) {
            winner.setText("You Win!");
        }
        if(compWin == true) {
            winner.setText("You Lose!");
        }
        end.add(winner);
        
        endGame.add(end);
        
    }
    
    public int checkBorderDestroyer(int x, int y, int direction) {
        int result;
        while (x == 10 && direction == 0) {
            direction = rand.nextInt(3);
            while(y == 0 && direction == 3) {
                direction = rand.nextInt(3);
            }
            while(y == 10 && direction == 2) {
                direction = rand.nextInt(3);
            }
        }
        while (x == 0 && direction == 1) {
            direction = rand.nextInt(3);
            while(y == 0 && direction == 3){
                direction = rand.nextInt(3);
            }
            while(y == 10 && direction == 2) {
                direction = rand.nextInt(3);
            }
        }
        while (y == 10 && direction == 2) {
            direction = rand.nextInt(3);
            while(x == 0 && direction == 1) {
                direction = rand.nextInt(3);
            }
            while(x == 10 && direction == 0) {
                direction = rand.nextInt(3);
            }
        }
        while (y == 0 && direction == 3) {
            direction = rand.nextInt(3);
            while(x == 0 && direction == 1) {
                direction = rand.nextInt(3);
            }
            while(x == 10 && direction == 0) {
                direction = rand.nextInt(3);
            }
        }
        result = direction;
        return result;
    }
    
    public int checkBorderShips(int x, int y, int direction, int maxValue, int minValue) {
        int result;
        while (x > maxValue && direction == 0) {
            direction = rand.nextInt(3);
            while(y < minValue && direction == 3) {
                direction = rand.nextInt(3);
            }
            while(y > maxValue && direction == 2) {
                direction = rand.nextInt(3);
            }
        }
        while (x < minValue && direction == 1) {
            direction = rand.nextInt(3);
            while(y < minValue && direction == 3){
                direction = rand.nextInt(3);
            }
            while(y > maxValue && direction == 2) {
                direction = rand.nextInt(3);
            }
        }
        while (y > maxValue && direction == 2) {
            direction = rand.nextInt(3);
            while(x < minValue && direction == 1) {
                direction = rand.nextInt(3);
            }
            while(x > maxValue && direction == 0) {
                direction = rand.nextInt(3);
            }
        }
        while (y < minValue && direction == 3) {
            direction = rand.nextInt(3);
            while(x < minValue && direction == 1) {
                direction = rand.nextInt(3);
            }
            while(x > maxValue && direction == 0) {
                direction = rand.nextInt(3);
            }
        }
        result = direction;
        return result;
    }
    
    public void placeCompShips() {
        int randomOne = rand.nextInt(10) + 1;
        int randomTwo = rand.nextInt(10) + 1;
        int direction = rand.nextInt(3);
        //destroyer comp
        gridFilled[randomOne][randomTwo] = true;
        
        direction = checkBorderDestroyer(randomOne, randomTwo, direction);
        System.out.println(randomOne + " " + randomTwo + " " + direction);
//        while (randomOne == 10 && direction == 0) {
//            direction = rand.nextInt(3);
//            while(randomTwo == 0 && direction == 3) {
//                direction = rand.nextInt(3);
//            }
//            while(randomTwo == 10 && direction == 2) {
//                direction = rand.nextInt(3);
//            }
//        }
        
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
            System.out.println("test");
        }
        gridFilled[randomOne][randomTwo] = true;
        
        direction = checkBorderShips(randomOne, randomTwo, direction, 8, 3);
        System.out.println(randomOne + " " + randomTwo + " " + direction);
        
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
            System.out.println("test");
        }
        gridFilled[randomOne][randomTwo] = true;
        
        direction = checkBorderShips(randomOne, randomTwo, direction, 7, 4);
        System.out.println(randomOne + " " + randomTwo + " " + direction);
        
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
            System.out.println("test");
        }
        gridFilled[randomOne][randomTwo] = true;
        direction = checkBorderShips(randomOne, randomTwo, direction, 6, 5);
        System.out.println(randomOne + " " + randomTwo + " " + direction);
        
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



