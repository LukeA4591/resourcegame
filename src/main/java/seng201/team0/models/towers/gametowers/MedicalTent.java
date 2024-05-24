package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents a medical tent, a tower that loads medkit carts.
 */
public class MedicalTent extends Tower {
    /**
     * Constructs a medical tent with predetermined characteristics.
     */
    public MedicalTent() {
        super("Medical Tent",  20, 2, "Medkits",
                150, 1);
    }

    /**
     * Gets the description of the medical tent.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Delivers immediate medical\ncare to the front lines.";
    }
}
