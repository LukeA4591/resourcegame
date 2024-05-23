package seng201.team0.models.carts;

public class AmmunitionCart extends Cart {
    private int ammunitionCapacity;
    private String resourceType;

    public AmmunitionCart(int size, int speed) {
        super(size, speed);
        this.ammunitionCapacity = 0;
        this.resourceType = "Ammunition";
    }
    public String getResourceType(){ return resourceType; }
}