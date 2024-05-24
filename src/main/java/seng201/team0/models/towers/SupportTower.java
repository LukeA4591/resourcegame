package seng201.team0.models.towers;

/**
 * Represents a support tower that boosts the reload speed of towers of the same type.
 * Subclass of Tower.
 */
public abstract class SupportTower extends Tower {
    /**
     * Constructs a support tower with the specified characteristics.
     *
     * @param name a String, the name of the support tower.
     * @param reloadSpeedBoost a double, the reload speed boost that the tower gives.
     * @param resourceType a String, the resource type that the support tower affects.
     * @param cost an int, the cost of the support tower.
     */
    public SupportTower(final String name, final double reloadSpeedBoost, final String resourceType, final int cost) {
        super(name, 0, reloadSpeedBoost, resourceType, cost, 1);
    }

    /**
     * Boosts the reload speed of the specified tower if it matches the resource type.
     *
     * @param tower a Tower, the tower to boost the reload speed of.
     */
    public void boostReloadSpeed(final Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() - this.getReloadSpeed());
        }
    }

    /**
     * Removes the reload speed boost of the specified tower if it matches the resource type.
     *
     * @param tower a Tower, the tower to remove the reload speed boost from.
     */
    public void removeReloadSpeedBoost(final Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() + this.getReloadSpeed());
        }
    }

    /**
     * Gets the reload speed boost of the support tower.
     *
     * @return a double, the reload speed boost.
     */
    public double getReloadSpeedBoost() {
        return this.getReloadSpeed();
    }
}
