package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents an eagle's nest, a tower that loads ammunition carts.
 */
public class EaglesNest extends Tower {
    /**
     * Constructs an eagle's nest with predetermined characteristics.
     */
    public EaglesNest() {
        super("Eagle's Nest", 30, 1.6, "Ammunition",
                800, 1);
    }

    /**
     * Gets the description of the eagle's nest.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Elite munitions outpost\ndelivering the highest quality ammunition.";
    }
}

