package seng201.team0.models.carts;

public class TroopCart extends Cart {
    private int soldierCapacity;

    public TroopCart(int size, String resourceType, int speed, int soldierCapacity) {
        super(size, resourceType, speed);
        this.soldierCapacity = soldierCapacity;
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