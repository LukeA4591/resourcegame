package seng201.team0.models.towers.supporttowers;

import seng201.team0.models.towers.SupportTower;
/**
 * A troop command post, a support tower that boosts the reload speed of other troop towers.
 */
public class TroopCommandPost extends SupportTower {
    /**
     * Constructs a troop command post with predetermined standard attributes.
     */
    public TroopCommandPost() {
        super("Troop Command Post", 0.3, "Troops",
                500);
    }

    /**
     * Gets the description of the troop command post.
     *
     * @return a String, the description.
     */
    public String getDescription() {
        return "Enhances the reload\nspeed of troop towers.";
    }
}
