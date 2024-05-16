package seng201.team0.models;

public abstract class Tower implements Purchasable {
    private String name;
    private int resourceAmount;
    private double reloadSpeed;
    private String resourceType;
    private int level;
    private int cost;
    private int sellPrice;

    public Tower(String name, int resourceAmount, double reloadSpeed, String resourceType, int level,
            int cost, int sellPrice) {

        this.name = name;
        this.resourceAmount = resourceAmount;
        this.reloadSpeed = reloadSpeed;
        this.resourceType = resourceType;
        this.level = level;
        this.cost = cost;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }
    public void setName(int resourceAmount) {
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

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
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
}
