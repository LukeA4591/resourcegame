package seng201.team0.models.carts;

public class TroopCart extends Cart {
    private int troopCapacity;
    private String resourceType;

    public TroopCart(int size, int speed) {
        super(size, speed);
        this.troopCapacity = 0;
        this.resourceType = "Troops";
    }
    public String getResourceType(){
        return resourceType;
    }
}