package ca.bcit.comp2613.battleship.model;

import java.awt.GridLayout;
import java.util.List;

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
    private static final int WIDTH = 11;
    private static final int LENGTH = 11;
    
    @OneToMany
    private List < Ship > ships;
        
    
    public Board() {
            setLayout(new GridLayout(WIDTH, LENGTH));
            setSize(850,850);
            createButtons();
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
    
    public void createButtons() {
        grid = new JButton[WIDTH][LENGTH];     
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                grid[i][j] = new JButton("(" + j + "," + i + ")");
                add(grid[i][j]);
            }
            
        }
    }
}



