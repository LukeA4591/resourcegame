package seng201.team0.gui;

import javafx.concurrent.Task;
import javafx.util.Duration;
import seng201.team0.GameEnvironment;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.*;
import seng201.team0.models.Round;
import seng201.team0.models.towers.*;
import java.util.*;


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
    private Label roundInfoLabel1;
    @FXML
    private Label roundInfoLabel2;
    @FXML
    private Label roundTimerLabel;
    @FXML
    private Label fillSupplyTruckLabel;
    @FXML
    private Label fillHumveeLabel;
    @FXML
    private Label fillAmbulanceLabel;

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
    private String roundDifficulty;
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
        ammoProgressBar.setProgress(0);
        troopProgressBar.setProgress(0);
        medKitProgressBar.setProgress(0);
        roundTimerProgressBar.setProgress(0);
        ammoProgressBar.setVisible(false);
        troopProgressBar.setVisible(false);
        medKitProgressBar.setVisible(false);
        roundTimerProgressBar.setVisible(false);
        roundTimerLabel.setVisible(false);
        fillSupplyTruckLabel.setVisible(false);
        fillAmbulanceLabel.setVisible(false);
        fillHumveeLabel.setVisible(false);
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
    private void onSelectDifficultyClicked(){
        this.roundDifficulty = roundDifficultyBox.getValue();
        if (roundDifficulty == null){
            gameEnvironment.showAlert("Invalid Difficulty", 
                    "Please chose a diffuculty for your next round", Alert.AlertType.ERROR);
        } else {
            difficultyLabel.setText("Difficulty: " + roundDifficulty);
            newRound = new Round(gameEnvironment.getCurrentRound(), gameEnvironment.getGameDifficulty(),
                    roundDifficulty, gameEnvironment);
            ArrayList<Integer> numCarts = newRound.getNumCarts();
            numAmmoCarts = numCarts.get(0);
            numMedCarts = numCarts.get(1);
            numTroopCarts = numCarts.get(2);
            roundInfoLabel1.setText(numAmmoCarts + " Ammunition Carts, " + numMedCarts + " Med-kit carts, " +
                    numTroopCarts + " Troop carts");

            switch (roundDifficulty) {
                case "Close-Quarters Combat":
                    roundInfoLabel2.setText("EDIT THIS");
                case "Standard Warfare":
                    roundInfoLabel2.setText("EDIT THIS");
                case "Sniper Combat":
                    roundInfoLabel2.setText("EDIT THIS");
            }
        }
    }
    @FXML
    private void onLoadSupplyTruckButton(){
        newRound.increaseAmmunitionCollected();
        ammunitionCollected = newRound.getAmmunitionCollected();
        if (ammunitionCollected >= ammunitionNeeded) {
            ammoProgressBar.setProgress(1);
            loadSupplyTruckButton.setDisable(true);
            fillSupplyTruckLabel.setText("Supply Truck full!");
        } else {
            ammoProgressBar.setProgress((double) ammunitionCollected / ammunitionNeeded);
            disableButtonForTime(loadSupplyTruckButton, newRound.getAmmoTowerReload());
        }
    }
    @FXML
    private void onLoadHumveeButton(){
        newRound.increaseTroopsCollected();
        troopsCollected = newRound.getTroopsCollected();
        if (troopsCollected >= troopsNeeded){
            troopProgressBar.setProgress(1);
            loadHumveeButton.setDisable(true);
            fillHumveeLabel.setText("Humvee full!");
        } else {
            troopProgressBar.setProgress((double) troopsCollected / troopsNeeded);
            disableButtonForTime(loadHumveeButton, newRound.getTroopTowerReload());
        }
    }
    @FXML
    private void onLoadAmbulanceButton(){
        newRound.increaseMedKitsCollected();
        medKitsCollected = newRound.getMedKitsCollected();
        if (medKitsCollected >= medKitsNeeded){
            medKitProgressBar.setProgress(1);
            loadAmbulanceButton.setDisable(true);
            fillAmbulanceLabel.setText("Ambulance full!");
        } else {
            medKitProgressBar.setProgress((double) medKitsCollected / medKitsNeeded);
            disableButtonForTime(loadAmbulanceButton, newRound.getMedTowerReload());
        }
    }

    @FXML
    private void onStartRoundButtonClicked() {






        if (roundDifficulty != null){

            if (!gameEnvironment.isMainTowerBroken()) {


                selectDifficultyLabel.setVisible(false);
                roundDifficultyBox.setVisible(false);
                selectDifficultyButton.setVisible(false);
                difficultyLabel.setVisible(false);
                roundInfoLabel1.setText("");
                roundInfoLabel2.setText("");
                shopButton.setVisible(false);
                inventoryButton.setVisible(false);
                startRoundButton.setVisible(false);
                ammoProgressBar.setVisible(true);
                troopProgressBar.setVisible(true);
                medKitProgressBar.setVisible(true);
                roundTimerProgressBar.setVisible(true);
                roundTimerLabel.setVisible(true);
                fillSupplyTruckLabel.setVisible(true);
                fillAmbulanceLabel.setVisible(true);
                fillHumveeLabel.setVisible(true);
                loadSupplyTruckButton.setDisable(false);
                loadAmbulanceButton.setDisable(false);
                loadHumveeButton.setDisable(false);
                medKitsNeeded = newRound.getMedKitsRequired();
                ammunitionNeeded = newRound.getAmmunitionRequired();
                troopsNeeded = newRound.getTroopsRequired();
                startProgressTimer(20);
            }
            else {
                gameEnvironment.showAlert("Main Tower is Broken", "Round cannot be played with a broken tower", Alert.AlertType.ERROR);
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Difficulty",
                    "Please choose a difficulty for your next round", Alert.AlertType.ERROR);
        }
    }
    private void disableButtonForTime(Button button, int seconds) {
        button.setDisable(true); // Disable the button

        // Create a Timeline to re-enable the button after the specified time
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(seconds),
                event -> button.setDisable(false) // Re-enable the button after the duration
        ));
        timeline.setCycleCount(1); // Only run once
        timeline.play(); // Start the timeline
    }
    private void startProgressTimer(int durationInSeconds) {
        // Reset progress
        double progress = 0.0;
        roundTimerProgressBar.setProgress(progress);

        // Define a Task to update progress
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < durationInSeconds; i++) {
                    Thread.sleep(1000); // Sleep for 1 second
                    updateProgress(i + 1, durationInSeconds);
                }
                return null;
            }
        };

        // Bind the progress of the task to the ProgressBar
        roundTimerProgressBar.progressProperty().bind(task.progressProperty());

        // Run the task in a background thread
        new Thread(task).start();
    }
}
