package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

public class MedicalTent extends Tower {

    public MedicalTent() {
        super("Medical Tent",  20, 2, "Medkits",
                150, 1);
    }

    public String getDescription() {
        return "Delivers immediate medical\ncare to the front lines.";
    }
}
