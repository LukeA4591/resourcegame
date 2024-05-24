package seng201.team0.models.items;

import seng201.team0.models.Purchasable;

public abstract class Item implements Purchasable {

    private final String name;
    private final int cost;
    private final int sellPrice;

    private final boolean isRepairKit;

    public Item(final String name, final int cost, final boolean isRepairKit) {
        this.name = name;
        this.cost = cost;
        this.sellPrice = cost / 2;
        this.isRepairKit = isRepairKit;
    }


    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }

    public abstract String getTowerType();

    public int getSellPrice() {
        return sellPrice;
    }

    public abstract String getDescription();

    public boolean getIsRepairKit() {
        return isRepairKit;
    }


}
