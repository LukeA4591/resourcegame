package seng201.team0.models.towers;

public class Armoury extends Tower {


    public Armoury() {
        super("Armoury", 20, 2, "Ammunition", 150, 1);

    }

    public String getDescription() {
        return "Provides a steady supply\nof ammunition to keep your troops well-armed.";
    }

}
