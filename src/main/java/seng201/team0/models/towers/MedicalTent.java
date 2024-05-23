package seng201.team0.models.towers;

public class MedicalTent extends Tower {

    public MedicalTent() {
        super("Medical Tent",  20, 2, "Medkits", 150, 1);
    }

    public String getDescription() {
        return "Delivers immediate medical\ncare to the front lines.";
    }
}
