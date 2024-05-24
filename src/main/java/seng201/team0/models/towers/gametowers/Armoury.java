package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

/**
 * Represents an armoury, a tower that loads ammunition carts.
 */
public class Armoury extends Tower {
    /**
     * Constructs an armoury with predetermined characteristics.
     */
    public Armoury() {
        super("Armoury", 20, 2, "Ammunition", 150, 1);
    }

    /**
     * Gets the description of the armoury.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Provides a steady supply\nof ammunition to keep your troops well-armed.";
    }

}
