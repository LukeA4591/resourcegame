package seng201.team0.models.items;

public class MedkitsTowerRepairKit extends Item {

    private final String towerType;
    public MedkitsTowerRepairKit() {
        super("Troops Tower Repair Kit", 500);
        this.towerType = "Troops";
    }

    public String getDescription() {
        return "This repair kit can repair any\nmedkits towers.";
    }

    public String getTowerType() {
        return towerType;
    }
}