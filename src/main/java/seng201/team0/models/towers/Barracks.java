package seng201.team0.models.towers;

public class Barracks extends Tower {


    public Barracks() {
        super("Barracks", 20, 2, "Troops", 150, 1);
    }

    public String getDescription() {
        return "Trains and deploys troops\nready for battle";
    }
}
