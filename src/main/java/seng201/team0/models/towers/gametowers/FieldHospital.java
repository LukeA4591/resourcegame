package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

public class FieldHospital extends Tower {


    public FieldHospital() {
        super("Field Hospital", 35, 1.6, "Medkits",
                1200, 1);
    }

    public String getDescription() {
        return "State-of-the-art medical facility\nensuring the highest quality medical supplies";
    }
}

