package seng201.team0.models.carts;

/**
 * A cart that holds resource type troops.
 */
public class TroopCart extends Cart {
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
     *
     * @return get cart resource type
     */
    public String getResourceType() {
        return resourceType;
    }
}