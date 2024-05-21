package seng201.team0.models.carts;

public class TroopCart extends Cart {
    private int soldierCapacity;
    private String resourceType;

    public TroopCart(int size, int speed) {
        super(size, speed);
        this.soldierCapacity = 0;
        this.resourceType = "Troop";
    }
    public String getResourceType(){
        return resourceType;
    }

    public int getSoldierCapacity() {
        return soldierCapacity;
    }

    public void setSoldierCapacity(int soldierCapacity) {
        this.soldierCapacity = soldierCapacity;
    }

    @Override
    public void fillCart() {
        // Implementation specific to TroopCart
        System.out.println("Filling Troop Cart with " + soldierCapacity + " soldiers. Cart size: " + getSize());
    }
}