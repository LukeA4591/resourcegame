package seng201.team0.models.items;

public class TroopsTowerRepairKit extends Item {

    private final String towerType;
    public TroopsTowerRepairKit() {
        super("Troops Tower Repair Kit", 500);
        this.towerType = "Troops";
    }

    public String getDescription() {
        return "This repair kit can repair any\ntroops towers.";
    }

    public String getTowerType() {
        return towerType;
    }
}
