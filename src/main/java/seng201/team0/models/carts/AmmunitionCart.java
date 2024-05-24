package seng201.team0.models.carts;

public class AmmunitionCart extends Cart {
    private final String resourceType;

    public AmmunitionCart(final int size, final int speed) {
        super(size, speed);
        this.resourceType = "Ammunition";
    }
    public String getResourceType() {
        return resourceType;
    }
}