package seng201.team0.models.carts;

public class AmmunitionCart extends Cart {
    private int ammunitionCapacity;
    private String resourceType;

    public AmmunitionCart(int size, int speed) {
        super(size, speed);
        this.ammunitionCapacity = 0;
        this.resourceType = "Ammunition";
    }

    public int getAmmunitionCapacity() {
        return ammunitionCapacity;
    }
    public String getResourceType(){
        return resourceType;
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