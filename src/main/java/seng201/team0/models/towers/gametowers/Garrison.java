package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

public class Garrison extends Tower {


    public Garrison() {
        super("Garrison", 25, 2.5, "Troops", 150, 1);
    }

    public String getDescription() {
        return "Military station ready to\ndeploy well trained troops.";
    }
}
