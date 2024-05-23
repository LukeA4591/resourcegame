package seng201.team0.models.towers;

import seng201.team0.models.Purchasable;

public abstract class Tower implements Purchasable {
    private String name;
    private double resourceAmount;
    private double reloadSpeed;
    private String resourceType;
    private int level;
    private int cost;
    private int sellPrice;
    private boolean isBroken;
    private static final int MAX_LEVEL = 6;

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

    public abstract String getDescription();

    public boolean isBroken() {
        return isBroken;
    }

    public void breakTower() {
        this.isBroken = true;
        setName("Broken " + name);
    }
    public void repairTower() {
        this.isBroken = false;
        setName(name.substring(7));
    }

    public void levelUp() {

        if (level + 1 >= MAX_LEVEL) {
            this.level = MAX_LEVEL;
        }
        else {
            level++;
            reloadSpeed *= 0.95;
            resourceAmount *= 1.05;
        }
    }

    public String getLevelRepresentation() {
        if (this.level >= MAX_LEVEL) {
            return "MAX LEVEL";
        }
        else {
            return "Level: " + level;
        }
    }


    public void communicationsBreakdown() {
        reloadSpeed *= 1.2;
    }

    public void medicalSupplyLineSabotage() {
        reloadSpeed *= 1.2;
    }

    public void enemyAmbush() {
        reloadSpeed *= 1.2;
    }

}
