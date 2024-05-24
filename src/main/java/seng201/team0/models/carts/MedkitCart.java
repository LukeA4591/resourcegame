package seng201.team0.models.carts;

/**
 * A cart that holds resource type med-kit.
 */
public class MedkitCart extends Cart {

    /**
     * The type of resource this cart holds, which is "Med-kits".
     */
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
     * Gets the type of resource this cart holds.
     *
     * @return The resource type, which is "Med-kits".
     */
    public String getResourceType() {
        return resourceType;
    }
}