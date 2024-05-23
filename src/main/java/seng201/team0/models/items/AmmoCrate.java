package seng201.team0.models.items;

public class AmmoCrate extends Item {


    public AmmoCrate() {
        super("Ammo Crate", 350, false);
    }

    public String getTowerType() {
        return "Ammunition";
    }

    public String getDescription() {
        return "A crate of ammunition which provides\nbonus resources generates by ammunition towers.";
    }


}
