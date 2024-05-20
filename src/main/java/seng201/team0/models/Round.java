package seng201.team0.models;

import seng201.team0.models.carts.Cart;

import java.util.HashMap;
import java.util.List;

public class Round {

    private HashMap<String, List<Cart>> carts;
    private double trackDistance;
    private int roundNumber;
    private String difficulty;

    public Round(int roundNumber, String difficulty) {

        this.roundNumber = roundNumber;
        this.difficulty = difficulty;
        this.carts = new HashMap<>();

        configureRound();

    }

    private void configureRound() {
        int numberOfCarts = 1 + ((roundNumber - 1) / 5);
    }
}
