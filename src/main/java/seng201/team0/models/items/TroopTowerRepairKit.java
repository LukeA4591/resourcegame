package seng201.team0.models.items;

public class TroopTowerRepairKit extends Item {

    public TroopTowerRepairKit() {
        super("Troop Tower Repair Kit", 500);
        setIsRepairKit(true);
    }

    public String getDescription() {
        return "This repair kit can repair any\ntroop tower.";
    }

    public String getTowerType() {
        return "Troops";
    }
}
