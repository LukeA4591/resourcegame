package seng201.team0.models.items;

public class AmmunitionTowerRepairKit extends Item {



    public AmmunitionTowerRepairKit() {
        super("Ammunition Tower Repair Kit", 500, true);
    }

    public String getTowerType() {
        return "Ammunition";
    }

    public String getDescription() {
        return "This repair kit can repair any\nammunition tower.";
    }
}
