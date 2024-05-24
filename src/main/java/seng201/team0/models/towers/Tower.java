package seng201.team0.models.towers;

import seng201.team0.models.Purchasable;

/**
 * Abstract Class that represents a tower in the game that can be purchased, sold, broken, and removed.
 * Towers have a name, resource type, and various other characteristics.
 */
public abstract class Tower implements Purchasable {
    /**
     * String, the name of the tower.
     */
    private String name;
    /**
     * double, the amounts of resources loaded by the tower into the carts per action.
     */
    private double resourceAmount;
    /**
     * double, the time between actions.
     */
    private double reloadSpeed;
    /**
     * String, the resource type that the tower produces.
     */
    private final String resourceType;
    /**
     * int, the level of the tower.
     */
    private int level;
    /**
     * int, the cost of the tower.
     */
    private final int cost;
    /**
     * int, the sell price of the tower.
     */
    private final int sellPrice;
    /**
     * boolean, determines whether the tower is broken.
     */
    private boolean isBroken;
    /**
     * The maximum allowed level of the tower.
     */
    private static final int MAX_LEVEL = 6;

    /**
     * Constructs a tower with the specified attributes.
     *
     * @param name a String, the tower's name.
     * @param resourceAmount a double, the tower's resource amount.
     * @param reloadSpeed a double, the tower's reload speed.
     * @param resourceType a String, the tower's resource type.
     * @param cost an int, the tower's cost.
     * @param level an int, the tower's level.
     */
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

    /**
     * Gets the name of the tower.
     *
     * @return a String, the tower's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the tower's name.
     *
     * @param name a String, the tower's new name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the tower's resource amount.
     *
     * @return a double, the resource amount.
     */
    public double getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Sets the tower's resource amount.
     *
     * @param resourceAmount a double, the new resource amount.
     */
    public void setResourceAmount(final double resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    /**
     * Gets the tower's reload speed.
     *
     * @return a double, the reload speed.
     */
    public double getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Sets the tower's reload speed.
     * Capped at 1 second.
     *
     * @param reloadSpeed a double, the new reload speed.
     */
    public void setReloadSpeed(final double reloadSpeed) {
        if (reloadSpeed <= 1) {
            this.reloadSpeed = 1;
        }
        else {
            this.reloadSpeed = reloadSpeed;
        }
    }

    /**
     * Gets the tower's resource type.
     *
     * @return a String, the resource type.
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Gets the tower's level.
     *
     * @return an Integer, the level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets the tower's level.
     * Capped at the maximum level.
     *
     * @param level an Integer, new level.
     */
    public void setLevel(final Integer level) {
        if (level >= MAX_LEVEL) {
            this.level = MAX_LEVEL;
        }
        else {
            this.level = level;
        }
    }

    /**
     * Gets the tower's cost.
     *
     * @return an int, the cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the tower's sell price.
     *
     * @return an int, the sell price.
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Gets the description of the tower.
     *
     * @return a String, the description.
     */
    public abstract String getDescription();

    /**
     * Checks if the tower is broken.
     *
     * @return a boolean, true if the tower is broken, false if not.
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Breaks the tower, marking it as broken by changing its name.
     */
    public void breakTower() {
        this.isBroken = true;
        setName("Broken " + name);
    }

    /**
     * Repairs the tower, marking it as repaired by reverting its name.
     */
    public void repairTower() {
        this.isBroken = false;
        setName(name.substring(7));
    }

    /**
     * Levels up the tower, increasing its resource amount and decreasing its reload speed.
     * Capped at the maximum level.
     */
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

    /**
     * Gets a string representation of the level to allow for represented a tower with max level.
     *
     * @return a String, the level.
     */
    public String getLevelRepresentation() {
        if (this.level >= MAX_LEVEL) {
            return "MAX LEVEL";
        } else {
            return "Level: " + level;
        }
    }

    /**
     * Applies the communications breakdown effect on the tower, increasing reload speed.
     */
    public void communicationsBreakdown() {
        reloadSpeed += 0.15;
    }
    /**
     * Applies the medical supply line sabotage effect on the tower, increasing reload speed.
     */
    public void medicalSupplyLineSabotage() {
        reloadSpeed += 0.15;
    }
    /**
     * Applies the enemy ambush effect on the tower, increasing reload speed.
     */
    public void enemyAmbush() {
        reloadSpeed += 0.15;
    }
}
