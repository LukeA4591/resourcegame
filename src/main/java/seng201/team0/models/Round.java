package seng201.team0.models;

import seng201.team0.GameEnvironment;
import seng201.team0.models.carts.*;
import seng201.team0.models.towers.Tower;

import java.util.*;

public class Round {

    private List<Cart> ammunitionCarts;
    private List<Cart> medkitCarts;
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
    private Tower medkitTower;
    private Tower ammunitionTower;
    private Tower troopTower;

    public Round(int roundNumber, String difficulty, String roundType, GameEnvironment gameEnvironment) {

        this.roundNumber = roundNumber;
        this.difficulty = difficulty;
        this.roundType = roundType;
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

        switch (roundType) {

            case "Artillery Barrage":
                for (int i = 0; i < numberOfCarts; i++) {
                    troopCarts.add(new TroopCart(50 + 10 * roundNumber, 0));
                    medkitCarts.add(new MedkitCart(50 + 10 * roundNumber, 0));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    ammunitionCarts.add(new AmmunitionCart(50 + 10 * roundNumber, 0));
                }
                break;

            case "Ground Offensive":
                for (int i = 0; i < numberOfCarts; i++) {
                    ammunitionCarts.add(new AmmunitionCart(50 + 10 * roundNumber, 0));
                    medkitCarts.add(new MedkitCart(50 + 10 * roundNumber, 0));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    troopCarts.add(new TroopCart(50 + 10 * roundNumber, 0));
                }
                break;

            case "Rescue Operation":
                for (int i = 0; i < numberOfCarts; i++) {
                    ammunitionCarts.add(new AmmunitionCart(50 + 10 * roundNumber, 0));
                    troopCarts.add(new TroopCart(50 + 10 * roundNumber, 0));
                }
                for (int i = 0; i < numberOfCarts + 1; i++) {
                    medkitCarts.add(new MedkitCart(50 + 10 * roundNumber, 0));
                }
                break;
        }

        for (Cart medKitCart : medkitCarts){
            medKitsRequired += medKitCart.getSize();
        }
        for (Cart ammunitionCart : ammunitionCarts){
            ammunitionRequired += ammunitionCart.getSize();
        }
        for (Cart troopCart : troopCarts){
            troopsRequired += troopCart.getSize();
        }
        for (Tower tower : gameEnvironment.getMainTowers()){
            switch (tower.getResourceType()) {
                case "Medkits" -> this.medkitTower = tower;
                case "Ammunition" -> this.ammunitionTower = tower;
                case "Troops" -> this.troopTower = tower;
            }
        }
    }
    public ArrayList<Integer> getNumCarts(){
        ArrayList<Integer> numCarts = new ArrayList<>(List.of(ammunitionCarts.size(),
                medkitCarts.size(), troopCarts.size()));
        return numCarts;
    }
    public int getMedKitsCollected(){
        return medKitsCollected;
    }
    public int getTroopsCollected(){
        return troopsCollected;
    }
    public int getAmmunitionCollected(){
        return ammunitionCollected;
    }
    public void increaseMedKitsCollected(){
        medKitsCollected += (int) medkitTower.getResourceAmount();
    }
    public void increaseAmmunitionCollected(){
        ammunitionCollected += (int) ammunitionTower.getResourceAmount();
    }
    public void increaseTroopsCollected(){
        troopsCollected += (int) troopTower.getResourceAmount();
    }
    public int getMedKitsRequired(){
        return medKitsRequired;
    }
    public int getAmmunitionRequired(){
        return ammunitionRequired;
    }
    public int getTroopsRequired(){
        return troopsRequired;
    }
    public int getMedkitTowerReload(){
        return (int) medkitTower.getReloadSpeed();
    }
    public int getAmmunitionTowerReload(){
        return (int) ammunitionTower.getReloadSpeed();
    }
    public int getTroopTowerReload(){
        return (int) troopTower.getReloadSpeed();
    }
}