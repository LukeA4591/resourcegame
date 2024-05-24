package seng201.team0.models.towers.supporttowers;

import seng201.team0.models.towers.SupportTower;

/**
 * An ammo relay station, a support tower that boosts the reload speed of other ammunition towers.
 */
public class AmmoRelayStation extends SupportTower {
    /**
     * Constructs an ammo relay station with predetermined standard attributes.
     */
    public AmmoRelayStation() {
        super("Ammo Relay Station", 0.3, "Ammunition", 400);

    }

    /**
     * Gets the description of the ammo relay station.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Enhances the reload\nspeed of ammunition towers.";
    }
}
