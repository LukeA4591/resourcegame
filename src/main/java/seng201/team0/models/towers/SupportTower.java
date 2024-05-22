package seng201.team0.models.towers;

import seng201.team0.models.Purchasable;

public abstract class SupportTower implements Purchasable {

    private String name;
    private double reloadSpeedBoost;
    private String resourceType;
    private int cost;
    private int sellPrice;


    public SupportTower(String name, double reloadSpeedBoost, String resourceType, int cost) {
        this.name = name;
        this.reloadSpeedBoost = reloadSpeedBoost;
        this.resourceType = resourceType;
        this.cost = cost;
        this.sellPrice = cost / 2;
    }

    public String getName() {
        return name;
    }

    public double getReloadSpeedBoost() {
        return reloadSpeedBoost;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getCost() {
        return cost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public abstract String getDescription();

    public void applyReloadSpeedBoost(Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() - this.getReloadSpeedBoost());
        }
    }
    public void removeReloadSpeedBoost(Tower tower) {
        if (tower.getResourceType().equals(this.getResourceType())) {
            tower.setReloadSpeed(tower.getReloadSpeed() + this.getReloadSpeedBoost());
        }
    }
}
