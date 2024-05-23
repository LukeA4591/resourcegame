package seng201.team0.models.items;

public class Paratroopers extends Item {

    public Paratroopers() {
        super("Paratroopers", 650);
    }

    public String getTowerType() {
        return "Troops";
    }

    public String getDescription() {
        return "A platoon of paratroopers which provide\nbonus resources generated by troop towers.";
    }
}
