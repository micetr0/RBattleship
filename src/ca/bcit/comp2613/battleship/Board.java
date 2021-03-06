package ca.bcit.comp2613.battleship;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.bcit.comp2613.battleship.model.Battleship;
import ca.bcit.comp2613.battleship.model.Carrier;
import ca.bcit.comp2613.battleship.model.Destroyer;
import ca.bcit.comp2613.battleship.model.Player;
import ca.bcit.comp2613.battleship.model.ScoreBoard;
import ca.bcit.comp2613.battleship.model.Ship;
import ca.bcit.comp2613.battleship.model.Submarine;
import ca.bcit.comp2613.battleship.repository.PlayerRepository;
import ca.bcit.comp2613.battleship.repository.ScoreBoardRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

public class Board extends JPanel{
    
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
    
    private Destroyer playerDestroyer;
    private Submarine playerSubmarine;
    private Battleship playerBattleship;
    private Carrier playerCarrier;
    
    private boolean gameEnd;
    private boolean playerWin;
    private boolean compWin;
    
    private int turn;
    
    private float score;
    
//    score = total hit + total miss + turn bonus
//
//    		each hit + 10
//    		each miss - 5
//
//    		turn bonus?
//    		< 20 turns + 100
//    		< 40 turns + 50
//    		< 60 turns + 0
    			
    private float miss;
    private float hit;
    private float compMiss;
    private float compHit;
    public static ConfigurableApplicationContext context;
    private PlayerRepository playerRepository;
   // private ScoreBoardRepository scoreBoardRepository;
    private ScoreBoard scoreboard;
    private Player player;
    
    Random rand = new Random();
    
    private List < Ship > ships;
    
    
    public Board() {
    	context = SpringApplication.run(H2Config.class);
    	playerRepository = context.getBean(PlayerRepository.class);
//    	ScoreBoard scoreBoardRepository = context.getBean(ScoreBoard.class);
//    	scoreboard = new ScoreBoard();
    	
    	
    	EntityManagerFactory emf = (EntityManagerFactory) context
				.getBean("entityManagerFactory");
    	
        setLayout(new GridLayout(WIDTH, LENGTH));
        setSize(600,600);
        //added init score
        playerDestroyer = SetupBoard.getPlayerDestroyer(); 
        playerSubmarine = SetupBoard.getPlayerSubmarine();
        playerBattleship = SetupBoard.getPlayerBattleship();
        playerCarrier = SetupBoard.getPlayerCarrier();
        
        score = 0;
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
    
    /**
     * Computer's turn.  Randomly picks two integers between 1-10. 
     * Check's if it's been selected before.  If it has, randomize until one that hasn't.
     */
    public void compMove() {
        int compMoveOne = rand.nextInt(10) + 1;
        int compMoveTwo = rand.nextInt(10) + 1;
        
        //Loop until one that hasn't been selected
        while (compSelected[compMoveOne][compMoveTwo] == true) {
            compMoveOne = rand.nextInt(10) + 1;
            compMoveTwo = rand.nextInt(10) + 1;
        }
        
        //mark as hit or miss.
        compSelected[compMoveOne][compMoveTwo] = true;
        if (compGrid[compMoveOne][compMoveTwo] == true) {
            compHit++;
            checkPlayerShipCoordinates(compMoveOne,compMoveTwo);
            
        } else {
            compMiss++;
            checkPlayerShipCoordinates(compMoveOne,compMoveTwo);
        }
        
        //checks if computer has won after every move
        checkCompWin();
        if (gameEnd == true) {
            gameEnd();
            SetupBoard.dispose();
        }
    }
    
    /**
     * If endurance of all ships reach zero.  Set game end as true as well compWin as true.
     */
    public void checkCompWin() {
        if(playerDestroyer.getEndurance() == 0 && playerSubmarine.getEndurance() == 0 && playerBattleship.getEndurance() == 0  && playerCarrier.getEndurance() == 0) {
            gameEnd = true;
            compWin = true;
        }
    }
    
    /**
     * If endurance of all ships reach zero.  Set game end as true as well as playerWin as true.
     */
    public void checkPlayerWin() {
        if(destroyer.getEndurance() == 0 && submarine.getEndurance() == 0 && battleship.getEndurance() == 0  && carrier.getEndurance() == 0) {
            gameEnd = true;
            playerWin = true;
        }
    }
    
    /**
     * Checks which ship has been hit.  Decreases it's endurance.
     * @param x
     * @param y
     */
    public void checkPlayerShipCoordinates(int x, int y) {
        if( (x == playerDestroyer.getPositionX1() && y == playerDestroyer.getPositionY1()) || (x == playerDestroyer.getPositionX2() && y == playerDestroyer.getPositionY2()) ) {
            playerDestroyer.decrementEndurance();
        }
        if((x == playerSubmarine.getPositionX1() && y == playerSubmarine.getPositionY1()) || (x == playerSubmarine.getPositionX2() && y == playerSubmarine.getPositionY2()) || (x == playerSubmarine.getPositionX3() && y == playerSubmarine.getPositionY3() ) ) {
            playerSubmarine.decrementEndurance();
        }
        if((x == playerBattleship.getPositionX1() && y == playerBattleship.getPositionY1()) || (x == playerBattleship.getPositionX2() && y == playerBattleship.getPositionY2()) || (x == playerBattleship.getPositionX3() && y == playerBattleship.getPositionY3() ) || (x == playerBattleship.getPositionX4() && y == playerBattleship.getPositionY4())) {
            playerBattleship.decrementEndurance();
        }
        if((x == playerCarrier.getPositionX1() && y == playerCarrier.getPositionY1()) || (x == playerCarrier.getPositionX2() && y == playerCarrier.getPositionY2()) || (x == playerCarrier.getPositionX3() && y == playerCarrier.getPositionY3() ) || (x == playerCarrier.getPositionX4() && y == playerCarrier.getPositionY4()) || (x == playerCarrier.getPositionX5() && y == playerCarrier.getPositionY5())) {
            playerCarrier.decrementEndurance();
        }
    }
    
    /**
     * Checks which ship has been hit.  Decreases it's endurance.
     * @param x
     * @param y
     */
    public void checkShipCoordinates(int x, int y) {
        if( (x == destroyer.getPositionX1() && y == destroyer.getPositionY1()) || (x == destroyer.getPositionX2() && y == destroyer.getPositionY2()) ) {
            destroyer.decrementEndurance();
        }
        if((x == submarine.getPositionX1() && y == submarine.getPositionY1()) || (x == submarine.getPositionX2() && y == submarine.getPositionY2()) || (x == submarine.getPositionX3() && y == submarine.getPositionY3() ) ) {
            submarine.decrementEndurance();
        }
        if((x == battleship.getPositionX1() && y == battleship.getPositionY1()) || (x == battleship.getPositionX2() && y == battleship.getPositionY2()) || (x == battleship.getPositionX3() && y == battleship.getPositionY3() ) || (x == battleship.getPositionX4() && y == battleship.getPositionY4())) {
            battleship.decrementEndurance();
        }
        if((x == carrier.getPositionX1() && y == carrier.getPositionY1()) || (x == carrier.getPositionX2() && y == carrier.getPositionY2()) || (x == carrier.getPositionX3() && y == carrier.getPositionY3() ) || (x == carrier.getPositionX4() && y == carrier.getPositionY4()) || (x == carrier.getPositionX5() && y == carrier.getPositionY5())) {
            carrier.decrementEndurance();
        }
    }
    
    /**
     * Creates the buttons for the board.  Sets action listeners for all the buttons.
     */
    public void createButtons() {
        grid = new JButton[WIDTH][LENGTH];   
        gridFilled = new boolean[WIDTH][LENGTH];
        clicked = new boolean[WIDTH][LENGTH];
        compSelected = new boolean[WIDTH][LENGTH];
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                //set the boarder buttons to do nothing with just numbers.
                if(i == 0){
                    clicked[j][i] = false;
                    compSelected[j][i] = false;
                    gridFilled[j][i] = false;
                    grid[j][i] = new JButton(""+j);
                    add(grid[j][i]);
                } else if (j == 0){
                    clicked[j][i] = false;
                    compSelected[j][i] = false;
                    gridFilled[j][i] = false;
                    grid[j][i] = new JButton(""+i);
                    add(grid[j][i]);
                } else {
                    //set the buttons with empty strings.  
                    clicked[j][i] = false;
                    compSelected[j][i] = false;
                    gridFilled[j][i] = false;
                    grid[j][i] = new JButton("");
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
                                    turn++;
                                    checkShipCoordinates(tempJ, tempI);
                                } else {
                                    miss++;
                                    grid[tempJ][tempI].setText("X");
                                    turn++;
                                    checkShipCoordinates(tempJ, tempI);
                                }
                            } else {
                                System.out.println("You have already checked this grid");
                            }
                            
                            //check if player has won after every move.                            
                            checkPlayerWin();
                            System.out.println(gameEnd);
                            if(gameEnd == true) {
                                gameEnd();
                                SetupBoard.dispose();
                            } else {
                                compMove();
                            }
                            
                        }
                    });
                }
            }
        }
    }
    
    /**
     * Creates new frame for results of the game.
     */
    public void gameEnd() {
        System.out.println("game end method");
        JFrame endGame = new JFrame("Results!");
        int bonus;
        int temp;
        if(turn < 21) {
        	bonus = 60;
        } else if (21 < turn && turn < 41) {
        	bonus = 40;
        } else {
        	bonus = 20;
        }
        score = (hit*10) + (miss*5) + bonus;
        
        endGame.setSize(250,250);
        endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endGame.setVisible(true);
        JPanel end = new JPanel();
        end.setLayout(new FlowLayout());
        JLabel winner = new JLabel("");
        JLabel totalScore = new JLabel("Score: ");
        JLabel percentageHit = new JLabel("");
        JLabel percentageMiss = new JLabel("");
        float hitPercentage;
        float missPercentage;
        if(playerWin == true) {
            winner.setText("You Win!  |   ");
            totalScore.setText("Score: " + score + "   |   ");
            hitPercentage = (hit / (hit + miss)) * 100;
            missPercentage = (miss / (hit + miss)) * 100;
            System.out.println(miss + " " + hit + " " + missPercentage + " " + hitPercentage);
            percentageHit.setText("% Hit: " + hitPercentage + "%    |   ");
            percentageMiss.setText("% Miss: " + missPercentage + "%");
        }
        if(compWin == true) {
            winner.setText("You Lose!");
            totalScore.setText("Score: " + score + "   |   ");
            hitPercentage = (hit / (hit + miss)) * 100;
            missPercentage = (miss / (hit + miss)) * 100;
            System.out.println(miss + " " + hit + " " + missPercentage + " " + hitPercentage);
            percentageHit.setText("% Hit: " + hitPercentage + "%    |   ");
            percentageMiss.setText("% Miss: " + missPercentage + "%");
        }
        end.add(winner);
        end.add(totalScore);
        end.add(percentageHit);
        end.add(percentageMiss);
        endGame.add(end);
        
        player = Menu.getPlayer();
        player.setScore(score);
        player.setHitRatio(hit / (hit + miss));
        player.setMissRatio(miss / (hit + miss));
        playerRepository.save(player);
 //       scoreboard = Menu.getScoreboard();
 //       scoreBoardRepository.save(scoreboard);
        
    }
    
    /**
     * Makes sure destroyer doesn't go out of bound.
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public int checkBorderDestroyer(int x, int y, int direction) {
        int result;
        while (x == 10 && direction == 0) {
            direction = rand.nextInt(3);
            while(y == 1 && direction == 3) {
                direction = rand.nextInt(3);
            }
            while(y == 10 && direction == 2) {
                direction = rand.nextInt(3);
            }
        }
        while (x == 1 && direction == 1) {
            direction = rand.nextInt(3);
            while(y == 1 && direction == 3){
                direction = rand.nextInt(3);
            }
            while(y == 10 && direction == 2) {
                direction = rand.nextInt(3);
            }
        }
        while (y == 10 && direction == 2) {
            direction = rand.nextInt(3);
            while(x == 1 && direction == 1) {
                direction = rand.nextInt(3);
            }
            while(x == 10 && direction == 0) {
                direction = rand.nextInt(3);
            }
        }
        while (y == 1 && direction == 3) {
            direction = rand.nextInt(3);
            while(x == 1 && direction == 1) {
                direction = rand.nextInt(3);
            }
            while(x == 10 && direction == 0) {
                direction = rand.nextInt(3);
            }
        }
        result = direction;
        return result;
    }
    
    /**
     * Makes sure other ships don't go out of bounds.
     * @param x
     * @param y
     * @param direction
     * @param maxValue
     * @param minValue
     * @return
     */
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
    
    /**
     * Place computer ships at random locations on board.
     */
    public void placeCompShips() {
        //Randomize the location.  randomOne represents x coordinates, randomTwo y coordinates.  Direction is which direction to go (up down left right).
        int randomOne = rand.nextInt(10) + 1;
        int randomTwo = rand.nextInt(10) + 1;
        int direction = rand.nextInt(3);
        
        //Place computer destroyer
        gridFilled[randomOne][randomTwo] = true;
        
        direction = checkBorderDestroyer(randomOne, randomTwo, direction);
        
        //debugging use
//        System.out.println(randomOne + " " + randomTwo + " " + direction);
        
        
//        while (randomOne == 10 && direction == 0) {
//            direction = rand.nextInt(3);
//            while(randomTwo == 0 && direction == 3) {
//                direction = rand.nextInt(3);
//            }
//            while(randomTwo == 10 && direction == 2) {
//                direction = rand.nextInt(3);
//            }
//        }
        
        //checks which direction it's going and creates an object with those coordinates
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
        
        //Place computer submarine
        while(gridFilled[randomOne][randomTwo] == true){
            randomOne = rand.nextInt(10) + 1;
            randomTwo = rand.nextInt(10) + 1;
            System.out.println("test");
        }
        gridFilled[randomOne][randomTwo] = true;
        
        direction = checkBorderShips(randomOne, randomTwo, direction, 8, 3);
        
        //for debugging use
        //System.out.println(randomOne + " " + randomTwo + " " + direction);
        
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            gridFilled[randomOne + 2][randomTwo] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne+1, randomTwo, randomOne+2, randomTwo, 3);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            gridFilled[randomOne - 2][randomTwo] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne-1, randomTwo, randomOne-2, randomTwo, 3);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            gridFilled[randomOne][randomTwo + 2] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne, randomTwo+1, randomOne, randomTwo+2, 3);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            gridFilled[randomOne][randomTwo - 2] = true;
            submarine = new Submarine(randomOne, randomTwo, randomOne, randomTwo-1, randomOne, randomTwo-2, 3);
            break;
        }
        
        //Place comp battleship
        while(gridFilled[randomOne][randomTwo] == true){
            randomOne = rand.nextInt(10) + 1;
            randomTwo = rand.nextInt(10) + 1;
            System.out.println("test");
        }
        gridFilled[randomOne][randomTwo] = true;
        
        direction = checkBorderShips(randomOne, randomTwo, direction, 7, 4);
        
        //for debugging use
        //System.out.println(randomOne + " " + randomTwo + " " + direction);
        
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            gridFilled[randomOne + 2][randomTwo] = true;
            gridFilled[randomOne + 3][randomTwo] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne+1, randomTwo, randomOne+2, randomTwo, randomOne+3, randomTwo, 4);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            gridFilled[randomOne - 2][randomTwo] = true;
            gridFilled[randomOne - 3][randomTwo] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne-1, randomTwo, randomOne-2, randomTwo, randomOne-3, randomTwo, 4);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            gridFilled[randomOne][randomTwo + 2] = true;
            gridFilled[randomOne][randomTwo + 3] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne, randomTwo+1, randomOne, randomTwo+2, randomOne, randomTwo+3, 4);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            gridFilled[randomOne][randomTwo - 2] = true;
            gridFilled[randomOne][randomTwo - 3] = true;
            battleship = new Battleship(randomOne, randomTwo, randomOne, randomTwo-1, randomOne, randomTwo-2, randomOne, randomTwo-3, 4);
            break;
        }
        
        //Place comp Carrier
        while(gridFilled[randomOne][randomTwo] == true){
            randomOne = rand.nextInt(10) + 1;
            randomTwo = rand.nextInt(10) + 1;
            System.out.println("test");
        }
        gridFilled[randomOne][randomTwo] = true;
        direction = checkBorderShips(randomOne, randomTwo, direction, 6, 5);
        
        //debugging use.
        //System.out.println(randomOne + " " + randomTwo + " " + direction);
        
        switch (direction){
        case 0: 
            gridFilled[randomOne + 1][randomTwo] = true;
            gridFilled[randomOne + 2][randomTwo] = true;
            gridFilled[randomOne + 3][randomTwo] = true;
            gridFilled[randomOne + 4][randomTwo] = true;
            
            carrier = new Carrier(randomOne, randomTwo, randomOne+1, randomTwo, randomOne+2, randomTwo, randomOne+3, randomTwo, randomOne+4, randomTwo, 5);
            break;
        case 1:
            gridFilled[randomOne - 1][randomTwo] = true;
            gridFilled[randomOne - 2][randomTwo] = true;
            gridFilled[randomOne - 3][randomTwo] = true;
            gridFilled[randomOne - 4][randomTwo] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne-1, randomTwo, randomOne-2, randomTwo, randomOne-3, randomTwo, randomOne-4, randomTwo, 5);
            break;
        case 2:
            gridFilled[randomOne][randomTwo + 1] = true;
            gridFilled[randomOne][randomTwo + 2] = true;
            gridFilled[randomOne][randomTwo + 3] = true;
            gridFilled[randomOne][randomTwo + 4] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne, randomTwo+1, randomOne, randomTwo+2, randomOne, randomTwo+3, randomOne, randomTwo+4, 5);
            break;
        case 3:
            gridFilled[randomOne][randomTwo - 1] = true;
            gridFilled[randomOne][randomTwo - 2] = true;
            gridFilled[randomOne][randomTwo - 3] = true;
            gridFilled[randomOne][randomTwo - 4] = true;
            carrier = new Carrier(randomOne, randomTwo, randomOne, randomTwo-1, randomOne, randomTwo-2, randomOne, randomTwo-3, randomOne, randomTwo-4, 5);
            break;
        }
        
    }
    
    /**
     * Create board for computer to play on.
     */
    public void computerSetup() {
        compGrid = new boolean[WIDTH][LENGTH];
        compGrid = SetupBoard.getFilled();
    }
}



