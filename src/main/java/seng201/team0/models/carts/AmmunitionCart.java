package seng201.team0.models.carts;

/**
 * A cart that holds resource type ammunition
 */
public class AmmunitionCart extends Cart {

    /**
     * The type of resource this cart holds, which is "Ammunition".
     */
    private final String resourceType;

    /**
     * construct an instance of ammunition cart.
     * @param size capacity of the cart
     * @param speed speed of the cart.
     */
    public AmmunitionCart(final int size, final int speed) {
        super(size, speed);
        this.resourceType = "Ammunition";
    }

    /**
     * Gets the type of resource this cart holds.
     *
     * @return The resource type, which is "Ammunition".
     */
    public String getResourceType() {
        return resourceType;
    }
}