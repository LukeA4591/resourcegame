package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents a field hospital, a tower that loads medkit carts.
 */
public class FieldHospital extends Tower {
    /**
     * Constructs a field hospital with predetermined characteristics.
     */
    public FieldHospital() {
        super("Field Hospital", 35, 1.6, "Medkits",
                1200, 1);
    }

    /**
     * Gets the description of the field hospital.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "State-of-the-art medical facility\nensuring the highest quality medical supplies";
    }
}

