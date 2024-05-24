package seng201.team0.models.carts;

/**
 * A cart that holds resource type med-kit.
 */
public class MedkitCart extends Cart {
    private final String resourceType;

    /**
     * construct an instance of med-kit cart.
     * @param size capacity of the cart
     * @param speed speed of the cart.
     */
    public MedkitCart(final int size, final int speed) {
        super(size, speed);
        this.resourceType = "Medkits";
    }
    /**
     *
     * @return get cart resource type/
     */
    public String getResourceType() {
        return resourceType;
    }
}