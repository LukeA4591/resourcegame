package seng201.team0.models.towers;

public abstract class SupportTower extends Tower {

    public SupportTower(String name, double reloadSpeedBoost, String resourceType, int cost) {
        super(name, 0, reloadSpeedBoost, resourceType, cost, 1);
    }

    public void boostReloadSpeed(Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() - this.getReloadSpeed());
        }
    }

    public void removeReloadSpeedBoost(Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() - this.getReloadSpeed());
        }
    }
    public double getReloadSpeedBoost() {
        return this.getReloadSpeed();
    }


}
