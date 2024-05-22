package seng201.team0.models;

import seng201.team0.GameEnvironment;
import seng201.team0.models.carts.*;
import seng201.team0.models.towers.Tower;

import java.util.*;

public class Round {

    private List<Cart> ammunitionCarts;
    private List<Cart> medKitCarts;
    private List<Cart> troopCarts;

    private double trackDistance;
    private int roundNumber;
    private String difficulty;
    private String roundType;
    private GameEnvironment gameEnvironment;

    private int medKitsCollected;
    private int ammunitionCollected;
    private int troopsCollected;
    private int medKitsRequired = 0;
    private int ammunitionRequired = 0;
    private int troopsRequired = 0;
    private Tower medTower;
    private Tower ammoTower;
    private Tower troopTower;

    public Round(int roundNumber, String difficulty, String roundType, GameEnvironment gameEnvironment) {

        this.roundNumber = roundNumber;
        this.difficulty = difficulty;
        this.roundType = roundType;
        this.gameEnvironment = gameEnvironment;

        this.ammunitionCarts = new ArrayList<>();
        this.medKitCarts = new ArrayList<>();
        this.troopCarts = new ArrayList<>();

        configureRound();
    }
    private void configureRound() {
        int numberOfCarts = 1 + ((roundNumber - 1) / 5);
        medKitsCollected = 0;
        ammunitionCollected = 0;
        troopsCollected = 0;

        switch (roundType) {

            case "Close-Quarters Combat":
                for (int i = 0; i < numberOfCarts; i++) {
                    ammunitionCarts.add(new AmmunitionCart(0, 0));
                    medKitCarts.add(new MedkitCart(0, 0));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    troopCarts.add(new TroopCart(0, 0));
                }
                break;

            case "Standard Warfare":
                for (int i = 0; i < numberOfCarts; i++) {
                    ammunitionCarts.add(new AmmunitionCart(0, 0));
                    medKitCarts.add(new MedkitCart(0, 0));
                    troopCarts.add(new TroopCart(0, 0));
                }
                break;

            case "Sniper Combat":
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    ammunitionCarts.add(new AmmunitionCart(0, 0));
                }
                for (int i = 0; i < numberOfCarts; i++) {
                    medKitCarts.add(new MedkitCart(0, 0));
                    troopCarts.add(new TroopCart(0, 0));
                }
                break;
        }

        for (Cart medKitCart : medKitCarts){
            medKitsRequired += medKitCart.getSize();
        }
        for (Cart ammunitionCart : ammunitionCarts){
            ammunitionRequired += ammunitionCart.getSize();
        }
        for (Cart troopCart : troopCarts){
            troopsRequired += troopCart.getSize();
        }
        for (Tower tower : gameEnvironment.getMainTowers()){
            if (tower.getResourceType().equals("Medkit")){
                this.medTower = tower;
            } else if (tower.getResourceType().equals("Ammunition")) {
                this.ammoTower = tower;
            } else if (tower.getResourceType().equals("Troop")) {
                this.troopTower = tower;
            }
        }
    }
    public ArrayList<Integer> getNumCarts(){
        ArrayList<Integer> numCarts = new ArrayList<>(List.of(ammunitionCarts.size(),
                medKitCarts.size(), troopCarts.size()));
        return numCarts;
    }

    public ArrayList<Integer> iterateRound(ArrayList<String> resourceClicked) {
        for (String resource : resourceClicked){
            switch (resource) {
                case "Medkit":
                    medKitsCollected += (int) medTower.getResourceAmount();
                    break;
                case "Ammunition":
                    ammunitionCollected += (int) ammoTower.getResourceAmount();
                    break;
                case "Troop":
                    troopsCollected += (int) troopTower.getResourceAmount();
                    break;
            }
        }
        ArrayList<Integer> resourcesCollected = new ArrayList<Integer>(
                List.of(medKitsCollected, ammunitionCollected, troopsCollected));
        return resourcesCollected;
    }
}