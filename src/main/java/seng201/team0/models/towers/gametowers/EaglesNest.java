package seng201.team0.models.towers.gametowers;

import seng201.team0.models.towers.Tower;

public class EaglesNest extends Tower {


    public EaglesNest() {
        super("Eagle's Nest", 30, 1.6, "Ammunition", 800, 1);
    }

    public String getDescription() {
        return "Elite munitions outpost\ndelivering the highest quality ammunition.";
    }
}

