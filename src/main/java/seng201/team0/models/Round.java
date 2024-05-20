package seng201.team0.models;

import seng201.team0.models.carts.Cart;

import java.util.HashMap;
import java.util.List;

public class Round {

    private HashMap<String, List<Cart>> carts;
    private double trackDistance;
    private int roundNumber;
    private String difficulty;

    private String roundType;

    public Round(int roundNumber, String difficulty, String roundType) {

        this.roundNumber = roundNumber;
        this.difficulty = difficulty;
        this.carts = new HashMap<>();
        this.roundType = roundType;

        configureRound();

    }

    private void configureRound() {

        switch (roundType) {

            case "Close-Quarters Combat":
                break;

            case "Standard Warfare":
                break;

            case "":
        }
        int numberOfCarts = 1 + ((roundNumber - 1) / 5);
    }
}
