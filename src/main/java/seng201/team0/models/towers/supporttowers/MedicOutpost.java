package seng201.team0.models.towers.supporttowers;

import seng201.team0.models.towers.SupportTower;
/**
 * A medic outpost, a support tower that boosts the reload speed of other medkit towers.
 */
public class MedicOutpost extends SupportTower {
    /**
     * Constructs a medic outpost with predetermined standard attributes.
     */
    public MedicOutpost() {
        super("Medic Outpost", 0.3, "Medkits", 600);
    }

    /**
     * Gets the description of the medic outpost.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Enhances the reload\nspeed of medkit towers";
    }
}
