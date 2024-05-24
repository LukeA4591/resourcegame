package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents a medbay, a tower that loads medkit carts.
 */
public class Medbay extends Tower {
    /**
     * Constructs a medbay with predetermined characteristics.
     */
    public Medbay() {
        super("Medbay",  25, 2.5, "Medkits", 150,
                1);
    }

    /**
     * Gets the description of the medbay.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Provides essential medical\nsupplies to injured soldiers";
    }
}
