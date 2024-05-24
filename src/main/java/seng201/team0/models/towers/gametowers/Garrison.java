package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents a garrison, a tower that loads troop carts.
 */
public class Garrison extends Tower {
    /**
     * Constructs a garrison with predetermined characteristics.
     */
    public Garrison() {
        super("Garrison", 25, 2.5, "Troops", 150,
                1);
    }

    /**
     * Gets the description of the garrison.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Military station ready to\ndeploy well trained troops.";
    }
}
