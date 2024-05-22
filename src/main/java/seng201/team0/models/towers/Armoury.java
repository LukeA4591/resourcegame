package seng201.team0.models.towers;

public class Armoury extends Tower {

    private static final double DEFAULT_RESOURCE_AMOUNT = 10.0;
    private static final double DEFAULT_RELOAD_SPEED = 1.5;
    private static final String DEFAULT_RESOURCE_TYPE = "Ammunition";
    private static final int DEFAULT_COST = 150;
    private static final int DEFAULT_LEVEL = 1;

    public Armoury() {
        super("Armoury", DEFAULT_RESOURCE_AMOUNT, DEFAULT_RELOAD_SPEED, DEFAULT_RESOURCE_TYPE, DEFAULT_COST, DEFAULT_LEVEL);

    }

    public String getDescription() {
        return "Provides a steady supply\nof ammunition to keep your troops well-armed.";
    }

}
