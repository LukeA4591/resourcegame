package seng201.team0.models.items;

public class AmmunitionTowerRepairKit extends Item {

    private final String towerType;

    public AmmunitionTowerRepairKit() {
        super("Ammunition Tower Repair Kit", 500);
        this.towerType = "Ammunition";
    }

    public String getTowerType() {
        return towerType;
    }

    public String getDescription() {
        return "This repair kit can repair any\nammunition towers.";
    }
}
