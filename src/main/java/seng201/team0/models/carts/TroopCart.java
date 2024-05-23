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

    public int getTroopCapacity() {
        return troopCapacity;
    }

    public void setTroopCapacity(int troopCapacity) {
        this.troopCapacity = troopCapacity;
    }

    @Override
    public void fillCart() {
        // Implementation specific to TroopCart
        System.out.println("Filling Troop Cart with " + troopCapacity + " soldiers. Cart size: " + getSize());
    }
}