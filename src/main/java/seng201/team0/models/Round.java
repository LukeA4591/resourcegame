package seng201.team0.models;

import seng201.team0.GameEnvironment;
import seng201.team0.models.carts.*;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;
import java.util.List;


public class Round {

    private final List<Cart> ammunitionCarts;
    private final List<Cart> medkitCarts;
    private final List<Cart> troopCarts;

    private final int roundNumber;
    private final String roundMode;
    private final GameEnvironment gameEnvironment;

    private int medKitsCollected;
    private int ammunitionCollected;
    private int troopsCollected;
    private int medKitsRequired = 0;
    private int ammunitionRequired = 0;
    private int troopsRequired = 0;
    private Tower medkitTower;
    private Tower ammunitionTower;
    private Tower troopTower;
    private int cartSpeed;

    public Round(final int roundNumber, final String roundMode, final GameEnvironment gameEnvironment) {

        this.roundNumber = roundNumber;
        this.roundMode = roundMode;
        this.gameEnvironment = gameEnvironment;

        this.ammunitionCarts = new ArrayList<>();
        this.medkitCarts = new ArrayList<>();
        this.troopCarts = new ArrayList<>();

        configureRound();
    }
    private void configureRound() {
        int numberOfCarts = 1 + ((roundNumber - 1) / 5);
        medKitsCollected = 0;
        ammunitionCollected = 0;
        troopsCollected = 0;
        cartSpeed = gameEnvironment.getTrackDistance() / (13 + numberOfCarts * 7);
        switch (roundMode) {
            case "Artillery Barrage":
                for (int i = 0; i < numberOfCarts; i++) {
                    troopCarts.add(new TroopCart(50 + 10 * roundNumber, cartSpeed));
                    medkitCarts.add(new MedkitCart(50 + 10 * roundNumber, cartSpeed));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    ammunitionCarts.add(new AmmunitionCart(50 + 10 * roundNumber, cartSpeed));
                }
                break;

            case "Ground Offensive":
                for (int i = 0; i < numberOfCarts; i++) {
                    ammunitionCarts.add(new AmmunitionCart(50 + 10 * roundNumber, cartSpeed));
                    medkitCarts.add(new MedkitCart(50 + 10 * roundNumber, cartSpeed));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    troopCarts.add(new TroopCart(50 + 10 * roundNumber, cartSpeed));
                }
                break;

            case "Rescue Operation":
                for (int i = 0; i < numberOfCarts; i++) {
                    ammunitionCarts.add(new AmmunitionCart(50 + 10 * roundNumber, cartSpeed));
                    troopCarts.add(new TroopCart(50 + 10 * roundNumber, cartSpeed));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    medkitCarts.add(new MedkitCart(50 + 10 * roundNumber, cartSpeed));
                }
                break;
        }

        for (Cart medKitCart : medkitCarts) {
            medKitsRequired += medKitCart.getSize();
        }
        for (Cart ammunitionCart : ammunitionCarts) {
            ammunitionRequired += ammunitionCart.getSize();
        }
        for (Cart troopCart : troopCarts) {
            troopsRequired += troopCart.getSize();
        }
        for (Tower tower : gameEnvironment.getMainTowers()) {
            switch (tower.getResourceType()) {
                case "Medkits" -> this.medkitTower = tower;
                case "Ammunition" -> this.ammunitionTower = tower;
                case "Troops" -> this.troopTower = tower;
            }
        }
    }

    public ArrayList<Integer> getNumCarts() {
        return new ArrayList<>(List.of(ammunitionCarts.size(),
                medkitCarts.size(), troopCarts.size()));
    }
    public int getMedKitsCollected() {
        return medKitsCollected;
    }
    public int getTroopsCollected() {
        return troopsCollected;
    }
    public int getAmmunitionCollected() {
        return ammunitionCollected;
    }
    public void increaseMedKitsCollected() {
        medKitsCollected += (int) medkitTower.getResourceAmount();
    }
    public void increaseAmmunitionCollected() {
        ammunitionCollected += (int) ammunitionTower.getResourceAmount();
    }
    public void increaseTroopsCollected() {
        troopsCollected += (int) troopTower.getResourceAmount();
    }
    public int getMedKitsRequired() {
        return medKitsRequired;
    }
    public int getAmmunitionRequired() {
        return ammunitionRequired;
    }
    public int getTroopsRequired() {
        return troopsRequired;
    }
    public double getMedkitTowerReload() {
        return medkitTower.getReloadSpeed();
    }
    public double getAmmunitionTowerReload() {
        return ammunitionTower.getReloadSpeed();
    }
    public double getTroopTowerReload() {
        return troopTower.getReloadSpeed();
    }
    public int getCartSpeed() {
        return cartSpeed;
    }
}