package seng201.team0.models.items;

import seng201.team0.models.Purchasable;

/**
 * Abstract class Item that can be purchased and sold, items can repair or boost towers.
 */
public abstract class Item implements Purchasable {

    /**
     * The name of the item.
     */
    private final String name;

    /**
     * The cost of the item.
     */
    private final int cost;

    /**
     * The sell price of the item, which is half of the cost.
     */
    private final int sellPrice;

    /**
     * Indicates whether the item is a repair kit.
     */
    private final boolean isRepairKit;

    /**
     * Constructs an Item with the specified name, cost, and repair kit status.
     *
     * @param name The name of the item.
     * @param cost The cost of the item.
     * @param isRepairKit True if the item is a repair kit, false otherwise.
     */
    public Item(final String name, final int cost, final boolean isRepairKit) {
        this.name = name;
        this.cost = cost;
        this.sellPrice = cost / 2;
        this.isRepairKit = isRepairKit;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the cost of the item.
     *
     * @return The cost of the item.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the type of tower that the item affects.
     *
     * @return The type of tower that the item affects.
     */
    public abstract String getTowerType();

    /**
     * Gets the sell price of the item.
     *
     * @return The sell price of the item.
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Gets the description of the item.
     *
     * @return The description of the item.
     */
    public abstract String getDescription();

    /**
     * Checks if the item is a repair kit.
     *
     * @return True if the item is a repair kit, false otherwise.
     */
    public boolean getIsRepairKit() {
        return isRepairKit;
    }


}
