package seng201.team0.models.carts;

public abstract class Cart {
    private int size;
    private String resourceType;
    private int speed;

    public Cart(int size, String resourceType, int speed) {
        this.size = size;
        this.resourceType = resourceType;
        this.speed = speed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Abstract method to be implemented by subclasses
    public abstract void fillCart();
}