package ca.bcit.comp2613.battleship.model;

public class Battleship extends Ship{

    private Integer positionX3;
    private Integer positionY3;
    private Integer positionX4;
    private Integer positionY4;
    
    public Battleship(Integer positionX1, Integer positionY1, Integer positionX2, Integer positionY2, Integer positionX3, Integer positionY3, Integer positionX4, Integer positionY4, Integer endurance) {
        super(positionX1, positionY1, positionX2, positionY2, endurance);
        this.positionX3 = positionX3;
        this.positionY3 = positionY3;
        this.positionX4 = positionX4;
        this.positionY4 = positionY4;
    }
    
    public void zeroCoordinates() {
        setPositionX1(null);
        setPositionX2(null);
        setPositionY1(null);
        setPositionY2(null);
    }

    public Integer getPositionX3() {
        return positionX3;
    }

    public void setPositionX3(Integer positionX3) {
        this.positionX3 = positionX3;
    }

    public Integer getPositionY3() {
        return positionY3;
    }

    public void setPositionY3(Integer positionY3) {
        this.positionY3 = positionY3;
    }

    public Integer getPositionX4() {
        return positionX4;
    }

    public void setPositionX4(Integer positionX4) {
        this.positionX4 = positionX4;
    }

    public Integer getPositionY4() {
        return positionY4;
    }

    public void setPositionY4(Integer positionY4) {
        this.positionY4 = positionY4;
    }
    
    
}
