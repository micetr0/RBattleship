package ca.bcit.comp2613.battleship.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ship {
    
    @Id
    public String id;
    private Integer positionX1;
    private Integer positionX2;
    private Integer positionY1;
    private Integer positionY2;
    private Integer endurance;
    @ManyToOne
    private Board board;
    Random rand = new Random();
    
    //not required because its build-in
    public Ship() {}

//generated using constructor
    public Ship(Integer positionX1, Integer positionY1, Integer positionX2, Integer positionY2, Integer endurance) {
        id = Integer.toString(rand.nextInt(100));
        this.positionX1 = positionX1;
        this.positionX2 = positionX2;
        this.positionY1 = positionY1;
        this.positionY2 = positionY2;
        this.endurance = endurance;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public Integer getPositionX1() {
		return positionX1;
	}

	public void setPositionX1(Integer positionX1) {
		this.positionX1 = positionX1;
	}

	public Integer getPositionX2() {
		return positionX2;
	}

	public void setPositionX2(Integer positionX2) {
		this.positionX2 = positionX2;
	}

	public Integer getPositionY1() {
		return positionY1;
	}

	public void setPositionY1(Integer positionY1) {
		this.positionY1 = positionY1;
	}

	public Integer getPositionY2() {
		return positionY2;
	}

	public void setPositionY2(Integer positionY2) {
		this.positionY2 = positionY2;
	}

	public Integer getEndurance() {
        return endurance;
    }


    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }
    
    public void decrementEndurance() {
        endurance--;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        //removed endurance
        return "ship id: " + id + " position x1: " + positionX1 +  " position y1: " + positionY1 + " Endurance: " + endurance;
              
    }

//    public int compareTo (Ship s) {
//      int retval = this.getId().compareTo(s.getId());
//      return retval;
//    }
    

}

