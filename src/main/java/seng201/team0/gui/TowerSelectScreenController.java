package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.models.GameEnvironment;

import javafx.scene.control.*;

public class TowerSelectScreenController {
    @FXML
    private Label playerName;
    @FXML
    private Label gameDifficulty;
    @FXML
    private Label roundCounter;
    @FXML
    private Label currentBalance;


    private GameEnvironment gameEnvironment;

    public TowerSelectScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {

        playerName.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficulty.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounter.setText("Round: 0/" + gameEnvironment.getGameRounds());
    }
}
