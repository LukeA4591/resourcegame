package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;
/**
 * Represents a barracks, a tower that loads troop carts.
 */
public class Barracks extends Tower {
    /**
     * Constructs a barracks with predetermined characteristics.
     */
    public Barracks() {
        super("Barracks", 20, 2, "Troops",
                150, 1);
    }

    /**
     * Gets the description of the barracks.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Trains and deploys troops\nready for battle";
    }
}
