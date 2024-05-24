package seng201.team0.models.towers;


public abstract class SupportTower extends Tower {

    public SupportTower(final String name, final double reloadSpeedBoost, final String resourceType, final int cost) {
        super(name, 0, reloadSpeedBoost, resourceType, cost, 1);
    }

    public void boostReloadSpeed(final Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() - this.getReloadSpeed());
        }
    }

    public void removeReloadSpeedBoost(final Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() + this.getReloadSpeed());
        }
    }
    public double getReloadSpeedBoost() {
        return this.getReloadSpeed();
    }


}
