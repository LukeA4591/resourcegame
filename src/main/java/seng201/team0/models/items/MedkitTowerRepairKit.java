package seng201.team0.models.items;

public class MedkitTowerRepairKit extends Item {

    public MedkitTowerRepairKit() {
        super("Medkit Tower Repair Kit", 500, true);
    }

    public String getDescription() {
        return "This repair kit can repair any\nmedkit tower.";
    }

    public String getTowerType() {
        return "Medkits";
    }
}