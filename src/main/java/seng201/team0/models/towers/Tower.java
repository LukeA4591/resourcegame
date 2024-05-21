package seng201.team0.models.towers;

import seng201.team0.models.Purchasable;

public abstract class Tower implements Purchasable {
    private String name;
    private double resourceAmount;
    private double reloadSpeed;
    private String resourceType;
    private Integer level;
    private int cost;
    private int sellPrice;
    private boolean isBroken;

    public Tower(String name, double resourceAmount, double reloadSpeed, String resourceType, int cost,
            int level) {

        this.name = name;
        this.resourceAmount = resourceAmount;
        this.reloadSpeed = reloadSpeed;
        this.resourceType = resourceType;
        this.level = level;
        this.cost = cost;
        this.sellPrice = cost / 2;
        this.isBroken = false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getResourceAmount() {
        return resourceAmount;
    }
    public void setResourceAmount(int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    public double getReloadSpeed() {
        return reloadSpeed;
    }
    public void setReloadSpeed(double reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDescription() {
        return "";
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken() {
        this.isBroken = true;
    }


    public void levelUp() {
        this.level++;
        this.reloadSpeed *= 0.98;
        this.resourceAmount *= 1.02;
    }
}
