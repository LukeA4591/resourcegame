package seng201.team0.models.items;

import seng201.team0.models.Purchasable;

public abstract class Item implements Purchasable {

    private String name;

    private int cost;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;

    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }

}
