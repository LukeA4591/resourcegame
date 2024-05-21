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

    public Round(int roundNumber, String difficulty, String roundType, GameEnvironment gameEnvironment) {

        this.roundNumber = roundNumber;
        this.difficulty = difficulty;
        this.roundType = roundType;
        this.gameEnvironment = gameEnvironment;

        this.ammunitionCarts = new ArrayList<>();
        this.medKitCarts = new ArrayList<>();
        this.troopCarts = new ArrayList<>();
        configureRound();
        playRound();
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
                for (int i = 0; i < numberOfCarts; i++) {
                    troopCarts.add(new TroopCart(0, 0));
                }
                break;

            case "Standard Warfare":
                for (int j = 0; j < numberOfCarts; j++) {
                    ammunitionCarts.add(new AmmunitionCart(0, 0));
                    medKitCarts.add(new MedkitCart(0, 0));
                    troopCarts.add(new TroopCart(0, 0));
                }
                break;

            case "Sniper Combat":
                for (int i = 0; i < numberOfCarts; i++) {
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
    }

    private void playRound() {
        List<Tower> towers = gameEnvironment.getMainTowers();
        Timer timer = new Timer();
        TimerTask roundTask = new TimerTask() {
            @Override
            public void run() {
                for (Tower tower : towers) {
                    switch (tower.getResourceType()) {
                        case "Medkit":
                            medKitsCollected += tower.getResourceAmount();
                            break;
                        case "Ammunition":
                            ammunitionCollected += tower.getResourceAmount();
                            break;
                        case "Troop":
                            troopsCollected += tower.getResourceAmount();
                            break;
                    }
                }
                System.out.println("Medkits: " + medKitsCollected + ", Ammunition: " + ammunitionCollected + ", Troops: " + troopsCollected);

                if (medKitsCollected >= medKitsRequired &&
                        ammunitionCollected >= ammunitionRequired &&
                        troopsCollected >= troopsRequired) {
                    timer.cancel();
                    System.out.println("Round successful!");
                }
            }
        };
        timer.scheduleAtFixedRate(roundTask, 0, 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                if (medKitsCollected >= medKitsRequired &&
                        ammunitionCollected >= ammunitionRequired &&
                        troopsCollected >= troopsRequired) {
                    System.out.println("Round successful!");
                } else {
                    System.out.println("Round failed.");
                }
            }
        }, 20000);
    }
}
