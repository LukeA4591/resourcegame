package seng201.team0.models.carts;

/**
 * Abstract class representing a cart that holds specific types of resources.
 * Subclasses should define the specific resource types.
 */
public abstract class Cart {

    /**
     * The capacity of the cart.
     */
    private int size;

    /**
     * The speed of the cart.
     */
    private int speed;

    /**
     * Constructs a Cart with the specified size and speed.
     *
     * @param size  The capacity of the cart, indicating how much it can hold.
     * @param speed The speed of the cart, indicating how fast it can move.
     */
    public Cart(final int size, final int speed) {
        this.size = size;
        this.speed = speed;
    }

    /**
     * Returns the capacity of the cart.
     *
     * @return The size of the cart.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the capacity of the cart.
     *
     * @param size The new size of the cart.
     */
    public void setSize(final int size) {
        this.size = size;
    }

    /**
     * Returns the speed of the cart.
     *
     * @return The speed of the cart.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the cart.
     *
     * @param speed The new speed of the cart.
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

}