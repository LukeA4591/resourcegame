package seng201.team0.models.carts;

/**
 * A cart that holds resource type troops.
 */
public class TroopCart extends Cart {

    /**
     * The type of resource this cart holds, which is "Troops".
     */
    private final String resourceType;

    /**
     * construct an instance of troop cart.
     * @param size capacity of the cart
     * @param speed speed of the cart.
     */
    public TroopCart(final int size, final int speed) {
        super(size, speed);
        this.resourceType = "Troops";
    }

    /**
     * Gets the type of resource this cart holds.
     *
     * @return The resource type, which is "Troops".
     */
    public String getResourceType() {
        return resourceType;
    }
}