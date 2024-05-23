package seng201.team0.models.items;

public class MedkitsTowerRepairKit extends Item {

    public MedkitsTowerRepairKit() {
        super("Troops Tower Repair Kit", 500);
        setIsRepairKit(true);
    }

    public String getDescription() {
        return "This repair kit can repair any\nmedkits tower.";
    }

    public String getTowerType() {
        return "Medkits";
    }
}