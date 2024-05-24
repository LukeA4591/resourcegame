package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents a special forces camp, a tower that loads troop carts.
 */
public class SpecialForcesCamp extends Tower {
    /**
     * Constructs a special forces camp with predetermined characteristics.
     */
    public SpecialForcesCamp() {
        super("Special Forces Camp", 40, 1.6,
                "Troops", 1600, 1);
    }

    /**
     * Gets the description of the special forces camp.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Trains and deploys elite soldiers with\nunparalleled combat skills suitable for any mission";
    }
}

