package seng201.team0.gui;

import seng201.team0.GameEnvironment;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.Round;
import seng201.team0.models.towers.*;
import seng201.team0.GameEnvironment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private Label selectDifficultyLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label cartInfoLabel;
    @FXML
    private Label roundTimerLabel;

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
    @FXML
    private Button selectDifficultyButton;
    @FXML
    private ComboBox<String> roundDifficultyBox;
    @FXML
    private ProgressBar ammoProgressBar;
    @FXML
    private ProgressBar troopProgressBar;
    @FXML
    private ProgressBar medKitProgressBar;
    @FXML
    private ProgressBar roundTimerProgressBar;
    private String difficulty;
    private GameEnvironment gameEnvironment;
    private int numMedCarts;
    private int numAmmoCarts;
    private int numTroopCarts;
    private int medKitsCollected;
    private int ammunitionCollected;
    private int troopsCollected;
    private int medKitsNeeded;
    private int ammunitionNeeded;
    private int troopsNeeded;
    private Round newRound;

    public GameScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {
        roundDifficultyBox.getItems().addAll("Close-Quarters Combat", "Standard Warfare", "Sniper Combat");
        ammoProgressBar.setVisible(false);
        troopProgressBar.setVisible(false);
        medKitProgressBar.setVisible(false);
        roundTimerProgressBar.setVisible(false);
        roundTimerLabel.setVisible(false);
        loadSupplyTruckButton.setDisable(true);
        loadAmbulanceButton.setDisable(true);
        loadHumveeButton.setDisable(true);
        updatePlayerDetails();
        updateTowerLabels();


    }

    @FXML
    private void updateTowerLabels() {

        for (Tower tower : gameEnvironment.getMainTowers()) {
            switch (tower.getResourceType()) {
                case "Ammunition":
                    tower1NameLabel.setText(tower.getName());
                    tower1LevelLabel.setText("Level " + tower.getLevel());

                case "Troops":
                    tower2NameLabel.setText(tower.getName());
                    tower2LevelLabel.setText("Level " + tower.getLevel());

                case "Medkits":
                    tower3NameLabel.setText(tower.getName());
                    tower3LevelLabel.setText("Level " + tower.getLevel());
            }
        }

        if (gameEnvironment.getSupportTower() != null) {
            tower4NameLabel.setText(gameEnvironment.getSupportTower().getName());
        }
        else {tower4NameLabel.setText("Locked");
        }

    }


    @FXML
    private void updatePlayerDetails() {
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: 0/" + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());
    }

    @FXML
    private void onShopButtonClicked() {
        gameEnvironment.closeGameScreen(false);
    }

    @FXML
    private void onInventoryButtonClicked() {
        gameEnvironment.closeGameScreen(true);
    }


    @FXML
    private void onDisplayDifficulty(){
        this.difficulty = roundDifficultyBox.getValue();
        if (difficulty == null){
            gameEnvironment.showAlert("Invalid Difficulty", 
                    "Please chose a diffuculty for your next round", Alert.AlertType.ERROR);
        } else {
            difficultyLabel.setText("Difficulty: " + difficulty);
            newRound = new Round(gameEnvironment.getCurrentRound(), gameEnvironment.getGameDifficulty(),
                    difficulty, gameEnvironment);
            ArrayList<Integer> numCarts = newRound.getNumCarts();
            numAmmoCarts = numCarts.get(0);
            numMedCarts = numCarts.get(1);
            numTroopCarts = numCarts.get(2);
            cartInfoLabel.setText(numAmmoCarts + " Ammunition Carts, " + numMedCarts + " Med-kit carts, " +
                    numTroopCarts + " Troop carts");
        }
    }
    @FXML
    private void onLoadSupplyTruckButton(){
        newRound.increaseAmmunitionCollected();
        ammunitionCollected = newRound.getAmmunitionCollected();
        ammoProgressBar.setProgress((double) ammunitionCollected / ammunitionNeeded);
    }
    @FXML
    private void onLoadHumveeButton(){
        newRound.increaseTroopsCollected();
        troopsCollected = newRound.getTroopsCollected();
        System.out.println(troopsCollected + " " + troopsNeeded);
        troopProgressBar.setProgress((double) troopsCollected / troopsNeeded);
    }
    @FXML
    private void onLoadAmbulanceButton(){
        newRound.increaseMedKitsCollected();
        medKitsCollected = newRound.getMedKitsCollected();
        System.out.println(medKitsCollected + " " + medKitsNeeded);
        medKitProgressBar.setProgress((double) medKitsCollected / medKitsNeeded);
    }

    @FXML
    private void onStartRoundButtonClicked() {
        if (difficulty == null){
            gameEnvironment.showAlert("Invalid Difficulty",
                    "Please chose a diffuculty for your next round", Alert.AlertType.ERROR);
        } else {
            selectDifficultyLabel.setVisible(false);
            roundDifficultyBox.setVisible(false);
            selectDifficultyButton.setVisible(false);
            difficultyLabel.setVisible(false);
            cartInfoLabel.setVisible(false);
            shopButton.setVisible(false);
            inventoryButton.setVisible(false);
            startRoundButton.setVisible(false);
            ammoProgressBar.setVisible(true);
            troopProgressBar.setVisible(true);
            medKitProgressBar.setVisible(true);
            roundTimerProgressBar.setVisible(true);
            roundTimerLabel.setVisible(true);
            loadSupplyTruckButton.setDisable(false);
            loadAmbulanceButton.setDisable(false);
            loadHumveeButton.setDisable(false);
            medKitsNeeded = newRound.getMedKitsRequired();
            ammunitionNeeded = newRound.getAmmunitionRequired();
            troopsNeeded = newRound.getTroopsRequired();

            Timer roundTimer = new Timer();
            TimerTask roundTask = new TimerTask() {
                @Override
                public void run() {

                }
            };
        }
    }
}
