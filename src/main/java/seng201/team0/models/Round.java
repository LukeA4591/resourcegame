package seng201.team0.models;

import seng201.team0.GameEnvironment;
import seng201.team0.models.carts.*;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * round class that handles round calculations for one round.
 */
public class Round {
    /**
     * create a list of type Cart for each resource.
     */
    private final List<Cart> ammunitionCarts;
    private final List<Cart> medkitCarts;
    private final List<Cart> troopCarts;
    /**
     * current round.
     */
    private final int roundNumber;
    /**
     * current round mode.
     */
    private final String roundMode;
    /**
     * create a game environment variable
     */
    private final GameEnvironment gameEnvironment;
    /**
     * create a int to track resource collected for each resource.
     */
    private int medKitsCollected;
    private int ammunitionCollected;
    private int troopsCollected;
    /**
     * create an int to hold resources needed for each resource.
     */
    private int medKitsRequired = 0;
    private int ammunitionRequired = 0;
    private int troopsRequired = 0;
    /**
     * create a Tower for each resource
     */
    private Tower medkitTower;
    private Tower ammunitionTower;
    private Tower troopTower;
    /**
     * create an int to store cart speed
     */
    private int cartSpeed;

    /**
     * Constructs a round instance, initialises parameters and creates lists to hold carts for each resource.
     * make a call to configure round.
     * @param roundNumber the current round number.
     * @param roundMode the current round mode.
     * @param gameEnvironment the game environment containing the games state
     */
    public Round(final int roundNumber, final String roundMode, final GameEnvironment gameEnvironment) {

        this.roundNumber = roundNumber;
        this.roundMode = roundMode;
        this.gameEnvironment = gameEnvironment;

        this.ammunitionCarts = new ArrayList<>();
        this.medkitCarts = new ArrayList<>();
        this.troopCarts = new ArrayList<>();

        configureRound();
        setTowers();
    }

    /**
     * Set collected resources to 0, call methods to create carts, find resources required, and set towers.
     */
    private void configureRound() {
        medKitsCollected = 0;
        ammunitionCollected = 0;
        troopsCollected = 0;
        createCarts();
        resourcesRequired();
        setTowers();
    }

    /**
     * Calculate number of carts and cart speed required for the round. Create cart instances depending on the
     * round mode.
     */
    public void createCarts(){
        int numberOfCarts = 1 + ((roundNumber - 1) / 5);
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
    }

    /**
     * Calculate total resources required for each resource by iterating through all carts.
     */
    public void resourcesRequired(){
        for (Cart medKitCart : medkitCarts) {
            medKitsRequired += medKitCart.getSize();
        }
        for (Cart ammunitionCart : ammunitionCarts) {
            ammunitionRequired += ammunitionCart.getSize();
        }
        for (Cart troopCart : troopCarts) {
            troopsRequired += troopCart.getSize();
        }
    }

    /**
     * Set main towers to variables based on resource type.
     */
    public void setTowers(){
        for (Tower tower : gameEnvironment.getMainTowers()) {
            switch (tower.getResourceType()) {
                case "Medkits" -> this.medkitTower = tower;
                case "Ammunition" -> this.ammunitionTower = tower;
                case "Troops" -> this.troopTower = tower;
            }
        }
    }

    /**
     * Create a list of the amount of carts required for each resource.
     * @return a list of the amount of each type of cart.
     */
    public ArrayList<Integer> getNumCarts() {
        return new ArrayList<>(List.of(ammunitionCarts.size(),
                medkitCarts.size(), troopCarts.size()));
    }

    /**
     *
     * @return return the med-kits collected so far in a round.
     */
    public int getMedKitsCollected() {
        return medKitsCollected;
    }
    /**
     *
     * @return return the troops collected so far in a round.
     */
    public int getTroopsCollected() {
        return troopsCollected;
    }
    /**
     *
     * @return return the ammunition collected so far in a round.
     */
    public int getAmmunitionCollected() {
        return ammunitionCollected;
    }

    /**
     * add med_kits generated to med_kits collected.
     */
    public void increaseMedKitsCollected() {
        medKitsCollected += (int) medkitTower.getResourceAmount();
    }

    /**
     * add ammunition generates to ammunition collected.
     */
    public void increaseAmmunitionCollected() {
        ammunitionCollected += (int) ammunitionTower.getResourceAmount();
    }
    /**
     * add ammunition generates to troops collected.
     */
    public void increaseTroopsCollected() {
        troopsCollected += (int) troopTower.getResourceAmount();
    }

    /**
     *
     * @return the med_kits required for a round.
     */
    public int getMedKitsRequired() {
        return medKitsRequired;
    }

    /**
     *
     * @return the ammunition required for a round.
     */
    public int getAmmunitionRequired() {
        return ammunitionRequired;
    }

    /**
     *
     * @return the troops required for a round.
     */
    public int getTroopsRequired() {
        return troopsRequired;
    }
    /**
     *
     * @return the reload speed for med-kit tower.
     */
    public double getMedkitTowerReload() {
        return medkitTower.getReloadSpeed();
    }
    /**
     *
     * @return the reload speed for ammunition tower.
     */
    public double getAmmunitionTowerReload() {
        return ammunitionTower.getReloadSpeed();
    }
    /**
     *
     * @return the reload speed for troop tower.
     */
    public double getTroopTowerReload() {
        return troopTower.getReloadSpeed();
    }

    /**
     *
     * @return the cart speed for the round.
     */
    public int getCartSpeed() {
        return cartSpeed;
    }
}