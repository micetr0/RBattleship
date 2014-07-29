package ca.bcit.comp2613.battleship.model;

public class Carrier extends Ship {

    public Carrier(Integer positionX1, Integer positionY1, Integer positionX2, Integer positionY2, Integer endurance) {
        super(positionX1, positionY1, positionX2, positionY2, endurance);
    }
    
    public void zeroCoordinates() {
        setPositionX1(null);
        setPositionX2(null);
        setPositionY1(null);
        setPositionY2(null);
    }
}
