package seng201.team0.models.towers;

import seng201.team0.models.Purchasable;

public abstract class Tower implements Purchasable {
    private String name;
    private double resourceAmount;
    private double reloadSpeed;
    private final String resourceType;
    private int level;
    private final int cost;
    private final int sellPrice;
    private boolean isBroken;
    private static final int MAX_LEVEL = 6;

    public Tower(final String name, final double resourceAmount, final double reloadSpeed, final String resourceType,
                 final int cost, final int level) {

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
    public void setName(final String name) {
        this.name = name;
    }

    public double getResourceAmount() {
        return resourceAmount;
    }
    public void setResourceAmount(final double resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    public double getReloadSpeed() {
        return reloadSpeed;
    }
    public void setReloadSpeed(final double reloadSpeed) {
        if (reloadSpeed <= 1) {
            this.reloadSpeed = 1;
        }
        else {
            this.reloadSpeed = reloadSpeed;
        }
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(final Integer level) {
        if (level >= MAX_LEVEL) {
            this.level = MAX_LEVEL;
        }
        else {
            this.level = level;
        }
    }

    public int getCost() {
        return cost;
    }

    public int getSellPrice() {
        return sellPrice;
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
            setReloadSpeed(reloadSpeed - 0.05);
            setResourceAmount(resourceAmount + 2);
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
        reloadSpeed += 0.15;
    }

    public void medicalSupplyLineSabotage() {
        reloadSpeed += 0.15;
    }

    public void enemyAmbush() {
        reloadSpeed += 0.15;
    }

}
