package seng201.team0.models.items;

/**
 * Represents a repair kit for troop towers.
 */
public class TroopTowerRepairKit extends Item {

    /**
     * Constructs a troop Tower Repair Kit with a predefined name and cost.
     */
    public TroopTowerRepairKit() {
        super("Troop Tower Repair Kit", 500, true);
    }

    /**
     * Gets the description of this item.
     *
     * @return The description of this item.
     */
    public String getDescription() {
        return "This repair kit can repair any\ntroop tower.";
    }

    /**
     * Gets the type of tower that this item affects.
     *
     * @return The type of tower that this item affects, which is "troops".
     */
    public String getTowerType() {
        return "Troops";
    }
}
