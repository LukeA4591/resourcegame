package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

public class Arsenal extends Tower {


    public Arsenal() {
        super("Arsenal", 25, 2.5, "Ammunition", 150, 1);
    }

    public String getDescription() {
        return "Stores and dispenses\nammunition with efficiency.";
    }
}
