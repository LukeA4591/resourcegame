package seng201.team0.models.items;

public class MedicalSupplyDrop extends Item {
    public MedicalSupplyDrop(int cost, int resourceBoost) {
        super("Medical Supply Drop", cost, resourceBoost);
    }

    public String getTowerType() {
        return "Medkits";
    }
}
