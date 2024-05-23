package seng201.team0.models.items;

public class TroopsTowerRepairKit extends Item {

    public TroopsTowerRepairKit() {
        super("Troops Tower Repair Kit", 500);
        setIsRepairKit(true);
    }

    public String getDescription() {
        return "This repair kit can repair any\ntroops tower.";
    }

    public String getTowerType() {
        return "Troops";
    }
}
