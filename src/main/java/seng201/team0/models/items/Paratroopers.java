package seng201.team0.models.items;

public class Paratroopers extends Item {
    public Paratroopers(int cost, int resourceBoost) {
        super("Paratroopers", cost, resourceBoost);
    }

    public String getTowerType() {
        return "Troops";
    }
}
