package seng201.team0.models.items;

import seng201.team0.models.Purchasable;

public abstract class Item implements Purchasable {

    private String name;
    private int cost;
    private int sellPrice;

    private boolean isRepairKit;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
        this.sellPrice = cost / 2;
        this.isRepairKit = false;
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
    public void setIsRepairKit(boolean bool) {
        this.isRepairKit = bool;
    }


}
