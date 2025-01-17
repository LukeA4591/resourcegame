package seng201.team0.models.items;

/**
 * Represents a medical supply drop item which provides bonus resources generated by medkit towers.
 */
public class MedicalSupplyDrop extends Item {

    /**
     * Constructs a Medical Supply drop with a predefined name and cost.
     */
    public MedicalSupplyDrop() {
        super("Medical Supply Drop", 500, false);
    }

    /**
     * Gets the type of tower that this item affects.
     *
     * @return The type of tower that this item affects, which is "Medkits".
     */
    public String getTowerType() {
        return "Medkits";
    }

    /**
     * Gets the description of this item.
     *
     * @return The description of this item.
     */
    public String getDescription() {
        return "An ariel supply drop that provides\n bonus resources generated by medkit towers.";
    }
}
