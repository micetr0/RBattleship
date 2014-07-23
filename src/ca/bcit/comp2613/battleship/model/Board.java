package ca.bcit.comp2613.battleship.model;

import java.awt.GridLayout;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.JButton;
import javax.swing.JFrame;

@Entity
public class Board extends JFrame{
    
    @Id
    private static final long serialVersionUID = 1L;
    //variable that will have multiple ship many to many relationship
    @OneToMany
    private List < Ship > ships;
        
    
    public Board() {
            setSize(500,500);
            setLayout(new GridLayout(11,11));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);            
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
        //creating "blueprints" for buttons
        JButton button[] = new JButton[121];
        for(int i = 0; i < 122; i++){
            button[i].setText(Integer.toString(i));
            add(button[i]);
        }
    }
}



