package seng201.team0.models;

/**
 * Represents a tower or item that can be bought in the game.
 */
public interface Purchasable {
    /**
     * Gets the cost of the item or tower.
     *
     * @return an int, the cost.
     */
    int getCost();

    /**
     * Gets the sell price of the item or tower.
     *
     * @return an int, the sell price.
     */
    int getSellPrice();

    /**
     * Gets the description of the item or tower.
     *
     * @return a String, the description.
     */
    String getDescription();

    /**
     * Gets the name of the item or tower.
     *
     * @return a String, the name.
     */
    String getName();
}
