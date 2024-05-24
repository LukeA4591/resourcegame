package seng201.team0.models.towers.supporttowers;

import seng201.team0.models.towers.SupportTower;

public class AmmoRelayStation extends SupportTower {

    public AmmoRelayStation() {
        super("Ammo Relay Station", 0.3, "Ammunition", 400);

    }

    public String getDescription() {
        return "Enhances the reload\nspeed of ammunition towers.";
    }
}
