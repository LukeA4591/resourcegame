package seng201.team0.models.towers;

public class MedicOutpost extends SupportTower {

    public MedicOutpost() {
        super("Medic Outpost", 0.3, "Medkits", 600);
    }
    public String getDescription() {
        return "Enhances the reload speed\nof medkit towers.";
    }
}
