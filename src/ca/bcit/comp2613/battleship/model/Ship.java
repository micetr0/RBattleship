package ca.bcit.comp2613.battleship.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ship {
    
    @Id
    public String id;
    private Integer positionX;
    private Integer positionY;
    private Integer endurance;
    public ShipType shipType;
    @ManyToOne
    private Board board;
    
    //not required because its build-in
    public Ship() {}

//generated using constructor
    public Ship(String id, Integer positionX, Integer positionY, Integer endurance, ShipType shipType) {
        super();
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.endurance = endurance;
        this.shipType = shipType; 
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Integer getPositionX() {
        return positionX;
    }


    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }


    public Integer getEndurance() {
        return endurance;
    }


    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }
    
    public ShipType getShipType() {
        return shipType;
    }
    
    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        //removed endurance
        return "ship id: " + id + " position x: " + positionX +  " position y: " + positionY + " ship type: " + shipType + " Endurance: " + endurance;
              
    }

//    public int compareTo (Ship s) {
//      int retval = this.getId().compareTo(s.getId());
//      return retval;
//    }
    

}

