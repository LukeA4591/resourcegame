package seng201.team0.models.items;

public class AmmoCrate extends Item {


    public AmmoCrate(String name, int cost) {
        super(name, cost);

    }

    public String getTowerType() {
        return "Ammunition";
    }

    public String getDescription() {
        return "A crate of ammunition which provides\nbonus resources generates by ammunition towers.";
    }

}
