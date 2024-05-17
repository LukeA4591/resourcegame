package seng201.team0.models.carts;

public class AmmunitionCart extends Cart {
    private int ammunitionCapacity;

    public AmmunitionCart(int size, String resourceType, int speed, int ammunitionCapacity) {
        super(size, resourceType, speed);
        this.ammunitionCapacity = ammunitionCapacity;
    }

    public int getAmmunitionCapacity() {
        return ammunitionCapacity;
    }

    public void setAmmunitionCapacity(int ammunitionCapacity) {
        this.ammunitionCapacity = ammunitionCapacity;
    }

    @Override
    public void fillCart() {
        // Implementation specific to AmmunitionCart
        System.out.println("Filling Ammunition Cart with " + ammunitionCapacity + " units of ammunition.");
    }
}