package seng201.team0.models.carts;

public abstract class Cart {
    private int size;
    private int speed;

    public Cart(int size, int speed) {
        this.size = size;
        this.speed = speed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}