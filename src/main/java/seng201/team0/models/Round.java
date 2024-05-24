package seng201.team0.models;

import seng201.team0.GameEnvironment;
import seng201.team0.models.carts.*;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;

/**
 * Represents a round in the game, handling the round calculations and managing the resources.
 */
public class Round {
    /**
     * List of ammunition carts for this round.
     */
    private final ArrayList<Cart> ammunitionCarts;

    /**
     * List of medkit carts for this round.
     */
    private final ArrayList<Cart> medkitCarts;

    /**
     * List of troop carts for this round.
     */
    private final ArrayList<Cart> troopCarts;

    /**
     * The current round number.
     */
    private final int roundNumber;

    /**
     * The current round mode.
     */
    private final String roundMode;

    /**
     * The game environment containing the game's state.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * The number of medkits collected in this round.
     */
    private int medKitsCollected;

    /**
     * The number of ammunition units collected in this round.
     */
    private int ammunitionCollected;

    /**
     * The number of troops collected in this round.
     */
    private int troopsCollected;

    /**
     * The number of medkits required for this round.
     */
    private int medKitsRequired = 0;

    /**
     * The number of ammunition units required for this round.
     */
    private int ammunitionRequired = 0;

    /**
     * The number of troops required for this round.
     */
    private int troopsRequired = 0;

    /**
     * The medkit tower used in this round.
     */
    private Tower medkitTower;

    /**
     * The ammunition tower used in this round.
     */
    private Tower ammunitionTower;

    /**
     * The troop tower used in this round.
     */
    private Tower troopTower;

    /**
     * The speed of the carts in this round.
     */
    private int cartSpeed;

    /**
     * Constructs a Round instance, initializes parameters, and creates lists to hold carts for each resource.
     * Calls the configureRound method to set up the round.
     *
     * @param roundNumber The current round number.
     * @param roundMode The current round mode.
     * @param gameEnvironment The game environment containing the game's state.
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
     * Sets collected resources to 0, calculates the number of carts and resources required, and sets towers.
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
     * Calculates the number of carts and their speed required for the round.
     * Creates cart instances depending on the round mode.
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
     * Calculates the total resources required for each resource type by iterating through all carts.
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
     * Sets the main towers to variables based on their resource type.
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
     * Returns the list of medkit carts.
     *
     * @return The list of medkit carts.
     */
    public ArrayList<Cart> getMedkitCarts() {
        return medkitCarts;
    }

    /**
     * Returns the list of ammunition carts.
     *
     * @return The list of ammunition carts.
     */
    public ArrayList<Cart> getAmmunitionCarts(){
        return ammunitionCarts;
    }

    /**
     * Returns the list of troop carts.
     *
     * @return The list of troop carts.
     */
    public ArrayList<Cart> getTroopCarts() {
        return troopCarts;
    }

    /**
     * Returns the number of medkits collected so far in this round.
     *
     * @return The number of medkits collected.
     */
    public int getMedKitsCollected() {
        return medKitsCollected;
    }

    /**
     * Returns the number of troops collected so far in this round.
     *
     * @return The number of troops collected.
     */
    public int getTroopsCollected() {
        return troopsCollected;
    }

    /**
     * Returns the number of ammunition units collected so far in this round.
     *
     * @return The number of ammunition units collected.
     */
    public int getAmmunitionCollected() {
        return ammunitionCollected;
    }

    /**
     * Increases the number of medkits collected by the amount generated by the medkit tower.
     */
    public void increaseMedKitsCollected() {
        medKitsCollected += (int) medkitTower.getResourceAmount();
    }

    /**
     * Increases the number of ammunition units collected by the amount generated by the ammunition tower.
     */
    public void increaseAmmunitionCollected() {
        ammunitionCollected += (int) ammunitionTower.getResourceAmount();
    }

    /**
     * Increases the number of troops collected by the amount generated by the troop tower.
     */
    public void increaseTroopsCollected() {
        troopsCollected += (int) troopTower.getResourceAmount();
    }

    /**
     * Returns the number of medkits required for this round.
     *
     * @return The number of medkits required.
     */
    public int getMedKitsRequired() {
        return medKitsRequired;
    }

    /**
     * Returns the number of ammunition units required for this round.
     *
     * @return The number of ammunition units required.
     */
    public int getAmmunitionRequired() {
        return ammunitionRequired;
    }

    /**
     * Returns the number of troops required for this round.
     *
     * @return The number of troops required.
     */
    public int getTroopsRequired() {
        return troopsRequired;
    }

    /**
     * Returns the reload speed for the medkit tower.
     *
     * @return The reload speed for the medkit tower.
     */
    public double getMedkitTowerReload() {
        return medkitTower.getReloadSpeed();
    }

    /**
     * Returns the reload speed for the ammunition tower.
     *
     * @return The reload speed for the ammunition tower.
     */
    public double getAmmunitionTowerReload() {
        return ammunitionTower.getReloadSpeed();
    }

    /**
     * Returns the reload speed for the troop tower.
     *
     * @return The reload speed for the troop tower.
     */
    public double getTroopTowerReload() {
        return troopTower.getReloadSpeed();
    }

    /**
     * Returns the cart speed for this round.
     *
     * @return The cart speed.
     */
    public int getCartSpeed() {
        return cartSpeed;
    }
}