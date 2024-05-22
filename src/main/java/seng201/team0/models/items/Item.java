package seng201.team0.models.items;

import seng201.team0.models.Purchasable;

public abstract class Item implements Purchasable {

    private String name;
    private int cost;
    private int sellPrice;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
        this.sellPrice = cost / 2;
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

}
