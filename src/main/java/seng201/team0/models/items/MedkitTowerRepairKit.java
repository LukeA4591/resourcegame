package seng201.team0.models.items;

/**
 * Represents a repair kit for medkit towers.
 */
public class MedkitTowerRepairKit extends Item {

    /**
     * Constructs a Medkit Tower Repair Kit with a predefined name and cost.
     */
    public MedkitTowerRepairKit() {
        super("Medkit Tower Repair Kit", 500, true);
    }

    /**
     * Gets the description of this item.
     *
     * @return The description of this item.
     */
    public String getDescription() {
        return "This repair kit can repair any\nmedkit tower.";
    }

    /**
     * Gets the type of tower that this item affects.
     *
     * @return The type of tower that this item affects, which is "Medkits".
     */
    public String getTowerType() {
        return "Medkits";
    }
}