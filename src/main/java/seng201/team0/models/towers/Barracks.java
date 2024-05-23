package seng201.team0.models.towers;

public class Barracks extends Tower {


    public Barracks() {
        super("Barracks", 15, 1.5, "Troops", 150, 1);
    }

    public String getDescription() {
        return "Trains and deploys troops\nready for battle";
    }
}
