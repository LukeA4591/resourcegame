package seng201.team0.models.items;

import seng201.team0.models.Purchasable;

public class Item {

    private String name;
    private int cost;
    private int resourceBoost;
    private String towerType;
    private int sellPrice;

    public Item(String name, int cost, int resourceBoost) {
        this.name = name;
        this.cost = cost;
        this.resourceBoost = resourceBoost;
        this.sellPrice = cost / 2;

        switch (name) {
            case "Ammo Crate":
                this.towerType = "Ammunition";
                break;
            case "Paratroopers":
                this.towerType = "Troops";
                break;
            case "Medical Supply Drop":
                this.towerType = "Medkits";
                break;
        }

    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }

    public int getResourceBoost() {
        return resourceBoost;
    }
    public String getTowerType() {
        return towerType;
    }

    public int getSellPrice() {
        return sellPrice;
    }


}
