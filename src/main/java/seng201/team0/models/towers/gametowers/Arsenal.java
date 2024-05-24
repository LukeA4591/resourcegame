package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

/**
 * Represents an arsenal, a tower that loads ammunition carts.
 */
public class Arsenal extends Tower {
    /**
     * Constructs an arsenal with predetermined characteristics.
     */
    public Arsenal() {
        super("Arsenal", 25, 2.5, "Ammunition",
                150, 1);
    }

    /**
     * Gets the description of the arsenal.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Stores and dispenses\nammunition with efficiency.";
    }
}
