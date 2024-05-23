package seng201.team0.models.towers;

public class Arsenal extends Tower {


    public Arsenal() {
        super("Arsenal", 20, 2, "Ammunition", 150, 1);
    }

    public String getDescription() {
        return "Stores and dispenses\nammunition with efficiency.";
    }
}
