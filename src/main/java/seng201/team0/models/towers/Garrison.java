package seng201.team0.models.towers;

public class Garrison extends Tower {


    public Garrison() {
        super("Garrison", 20, 2, "Troops", 150, 1);
    }

    public String getDescription() {
        return "Military station ready to\ndeploy well trained troops.";
    }
}
