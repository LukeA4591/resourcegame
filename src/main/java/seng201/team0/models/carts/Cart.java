package seng201.team0.models.carts;

/**
 * Abstract class for carts with specific resource types to build off.
 */
public abstract class Cart {
    private int size;
    private int speed;

    /**
     * Construct a cart with its size and speed.
     * @param size cart capacity
     * @param speed cart speed
     */
    public Cart(final int size, final int speed) {
        this.size = size;
        this.speed = speed;
    }

    /**
     *
     * @return get cart size
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size set cart size
     */
    public void setSize(final int size) {
        this.size = size;
    }

    /**
     *
     * @return get cart speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed set cart speed.
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

}