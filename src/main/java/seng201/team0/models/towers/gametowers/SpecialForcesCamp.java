package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

public class SpecialForcesCamp extends Tower {

    public SpecialForcesCamp() {
        super("Special Forces Camp", 40, 1.6, "Troops", 1600, 1);
    }

    public String getDescription() {
        return "Trains and deploys elite soldiers with\nunparalleled combat skills suitable for any mission";
    }
}

