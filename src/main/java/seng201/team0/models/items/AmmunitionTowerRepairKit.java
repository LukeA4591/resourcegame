package seng201.team0.models.items;

/**
 * Represents a repair kit for ammunition towers.
 */
public class AmmunitionTowerRepairKit extends Item {

    /**
     * Constructs a ammunition Tower Repair Kit with a predefined name and cost.
     */
    public AmmunitionTowerRepairKit() {
        super("Ammunition Tower Repair Kit", 500, true);
    }

    /**
     * Gets the type of tower that this item affects.
     *
     * @return The type of tower that this item affects, which is "ammunition".
     */
    public String getTowerType() {
        return "Ammunition";
    }

    /**
     * Gets the description of this item.
     *
     * @return The description of this item.
     */
    public String getDescription() {
        return "This repair kit can repair any\nammunition tower.";
    }
}
