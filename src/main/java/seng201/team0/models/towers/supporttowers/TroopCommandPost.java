package seng201.team0.models.towers.supporttowers;

import seng201.team0.models.towers.SupportTower;

public class TroopCommandPost extends SupportTower {

    public TroopCommandPost() {
        super("Troop Command Post", 0.3, "Troops", 500);
    }

    public String getDescription() {
        return "Enhances the reload\nspeed of troop towers.";
    }
}
