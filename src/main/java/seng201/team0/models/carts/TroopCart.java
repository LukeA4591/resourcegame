package seng201.team0.models.carts;

public class TroopCart extends Cart {
    private final String resourceType;

    public TroopCart(int size, int speed) {
        super(size, speed);
        this.resourceType = "Troops";
    }
    public String getResourceType() {
        return resourceType;
    }
}