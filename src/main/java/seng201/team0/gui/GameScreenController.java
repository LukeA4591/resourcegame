package seng201.team0.gui;

import seng201.team0.GameEnvironment;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.towers.*;

public class GameScreenController {

    @FXML
    private Label playerNameLabel;
    @FXML
    private Label gameDifficultyLabel;
    @FXML
    private Label roundCounterLabel;
    @FXML
    private Label currentBalanceLabel;
    @FXML
    private Label livesLeftLabel;

    @FXML
    private Label tower1NameLabel;
    @FXML
    private Label tower1LevelLabel;
    @FXML
    private Label tower2NameLabel;
    @FXML
    private Label tower2LevelLabel;
    @FXML
    private Label tower3NameLabel;
    @FXML
    private Label tower3LevelLabel;
    @FXML
    private Label tower4NameLabel;
    @FXML
    private Label tower4LevelLabel;

    @FXML
    private Button loadSupplyTruckButton;
    @FXML
    private Button loadHumveeButton;
    @FXML
    private Button loadAmbulanceButton;

    @FXML
    private Button shopButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button startRoundButton;

    private GameEnvironment gameEnvironment;

    public GameScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {

        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: 0/" + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());

        tower1NameLabel.setText(gameEnvironment.getSelectedTowers().get("Ammunition").getName());
        tower2NameLabel.setText(gameEnvironment.getSelectedTowers().get("Troops").getName());
        tower3NameLabel.setText(gameEnvironment.getSelectedTowers().get("Medkits").getName());

        tower1LevelLabel.setText("Level " + gameEnvironment.getTowerLevel(gameEnvironment.getSelectedTowers().get("Ammunition")));
        tower2LevelLabel.setText("Level " + gameEnvironment.getTowerLevel(gameEnvironment.getSelectedTowers().get("Troops")));
        tower3LevelLabel.setText("Level " + gameEnvironment.getTowerLevel(gameEnvironment.getSelectedTowers().get("Medkits")));


    }

    @FXML
    public void onShopButtonClicked() {
        gameEnvironment.launchShopScreen();
    }

    @FXML
    public void onInventoryButtonClicked() {
        gameEnvironment.launchInventoryScreen();
    }

    @FXML
    public void onStartRoundButtonClicked() {
        gameEnvironment.closeGameScreen(); // change this
    }
}
