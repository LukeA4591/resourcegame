package seng201.team0.models.items;

public class AmmoCrate extends Item {


    public AmmoCrate(int cost, int resourceBoost) {
        super("Ammo Crate", cost, resourceBoost);

    }

    public String getTowerType() {
        return "Ammunition";
    }

}
