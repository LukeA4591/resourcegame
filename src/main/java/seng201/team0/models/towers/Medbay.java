package seng201.team0.models.towers;

public class Medbay extends Tower {

    
    public Medbay() {
        super("Medbay",  20, 2, "Medkits", 150, 1);
    }

    public String getDescription() {
        return "Provides essential medical\nsupplies to injured soldiers";
    }
}
