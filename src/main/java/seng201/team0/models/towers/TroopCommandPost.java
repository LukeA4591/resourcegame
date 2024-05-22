package seng201.team0.models.towers;

public class TroopCommandPost extends SupportTower {

    public TroopCommandPost() {
        super("Troop Command Post", 0.3, "Troops", 500);
    }

    public String getDescription() {
        return "Enhances the reload speed\nof troop towers.";
    }
}
