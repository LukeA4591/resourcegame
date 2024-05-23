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
    private Label roundModeLabel;
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
    private Button selectRoundModeButton;
    @FXML
    private ComboBox<String> roundModeComboBox;
    @FXML
    private ProgressBar ammoProgressBar;
    @FXML
    private ProgressBar troopProgressBar;
    @FXML
    private ProgressBar medKitProgressBar;
    @FXML
    private ProgressBar roundTimerProgressBar;
    private String roundMode;
    private final GameEnvironment gameEnvironment;
    private int numberOfMedkitCarts;
    private int numberOfAmmunitionCarts;
    private  int numberOfTroopCarts;
    private int medKitsCollected;
    private int ammunitionCollected;
    private int troopsCollected;
    private int medKitsNeeded;
    private int ammunitionNeeded;
    private int troopsNeeded;
    private Round newRound;
    private boolean called;

    public GameScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
        called = true;
    }

    @FXML
    public void initialize() {
        roundModeComboBox.getItems().addAll("Artillery Barrage", "Ground Offensive", "Rescue Operation");
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
                    tower1LevelLabel.setText(tower.getLevelRepresentation());

                case "Troops":
                    tower2NameLabel.setText(tower.getName());
                    tower2LevelLabel.setText(tower.getLevelRepresentation());

                case "Medkits":
                    tower3NameLabel.setText(tower.getName());
                    tower3LevelLabel.setText(tower.getLevelRepresentation());
            }
        }

        if (gameEnvironment.getSupportTower() != null) {
            tower4NameLabel.setText(gameEnvironment.getSupportTower().getName());
        }
        else {
            tower4NameLabel.setText("Locked");
        }

    }


    @FXML
    private void updatePlayerDetails() {
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: " + gameEnvironment.getCurrentRound() +
                " / " + gameEnvironment.getGameRounds());
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
    private void onSelectRoundModeClicked(){
        this.roundMode = roundModeComboBox.getValue();
        if (roundMode == null){
            gameEnvironment.showAlert("Invalid Round Mode",
                    "Please chose a mode for your next round", Alert.AlertType.ERROR);
        } else {
            roundModeLabel.setText("Round Mode: " + roundMode);
            newRound = new Round(gameEnvironment.getCurrentRound(), gameEnvironment.getGameDifficulty(),
                    roundMode, gameEnvironment);
            ArrayList<Integer> numCarts = newRound.getNumCarts();
            numberOfAmmunitionCarts = numCarts.get(0);
            numberOfMedkitCarts = numCarts.get(1);
            numberOfTroopCarts = numCarts.get(2);
            roundInfoLabel1.setText(numberOfAmmunitionCarts + " Ammunition Carts | " + numberOfTroopCarts + " Troop Carts | " +
                    numberOfMedkitCarts + " Medkit Carts");

            switch (roundMode) {
                case "Artillery Barrage":
                    roundInfoLabel2.setText("+2 Ammunition Tower Levels | +1 Troop Tower Levels | +1 Medkit Tower Levels");
                    break;
                case "Ground Offensive":
                    roundInfoLabel2.setText("+1 Ammunition Tower Levels | +2 Troop Tower Levels | +1 Medkit Tower Levels");
                    break;
                case "Rescue Operation":
                    roundInfoLabel2.setText("+1 Ammunition Tower Levels | +1 Troop Tower Levels | +2 Medkit Tower Levels");
                    break;
            }




        }
    }
    @FXML
    private void onLoadSupplyTruckButtonClicked(){
        newRound.increaseAmmunitionCollected();
        ammunitionCollected = newRound.getAmmunitionCollected();
        if (ammunitionCollected >= ammunitionNeeded) {
            ammoProgressBar.setProgress(1);
            loadSupplyTruckButton.setDisable(true);
            fillSupplyTruckLabel.setText("Supply Truck full!");
            checkRoundDone();
        } else {
            ammoProgressBar.setProgress((double) ammunitionCollected / ammunitionNeeded);
            disableButtonForTime(loadSupplyTruckButton, newRound.getAmmunitionTowerReload());
        }
    }
    @FXML
    private void onLoadHumveeButtonClicked(){
        newRound.increaseTroopsCollected();
        troopsCollected = newRound.getTroopsCollected();
        if (troopsCollected >= troopsNeeded){
            troopProgressBar.setProgress(1);
            loadHumveeButton.setDisable(true);
            fillHumveeLabel.setText("Humvee full!");
            checkRoundDone();
        } else {
            troopProgressBar.setProgress((double) troopsCollected / troopsNeeded);
            disableButtonForTime(loadHumveeButton, newRound.getTroopTowerReload());
        }
    }
    @FXML
    private void onLoadAmbulanceButtonClicked(){
        newRound.increaseMedKitsCollected();
        medKitsCollected = newRound.getMedKitsCollected();
        if (medKitsCollected >= medKitsNeeded){
            medKitProgressBar.setProgress(1);
            loadAmbulanceButton.setDisable(true);
            fillAmbulanceLabel.setText("Ambulance full!");
            checkRoundDone();
        } else {
            medKitProgressBar.setProgress((double) medKitsCollected / medKitsNeeded);
            disableButtonForTime(loadAmbulanceButton, newRound.getMedkitTowerReload());
        }
    }

    @FXML
    private void onStartRoundButtonClicked() {

        if (roundMode != null){

            if (!gameEnvironment.isMainTowerBroken()) {

                roundModeComboBox.setVisible(false);
                selectRoundModeButton.setVisible(false);
                roundModeLabel.setVisible(false);
                roundInfoLabel1.setVisible(false);
                roundInfoLabel2.setVisible(false);
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
                startProgressTimer(gameEnvironment.getTrackDistance() / newRound.getCartSpeed());
            }
            else {
                gameEnvironment.showAlert("Main Tower is Broken", "Round cannot be played with a broken tower", Alert.AlertType.ERROR);
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Round Mode",
                    "Please choose a Round Mode for your next round", Alert.AlertType.ERROR);
        }
    }
    private void disableButtonForTime(Button button, double seconds) {
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
            @Override
            protected void succeeded() {
                super.succeeded();
                // Call your function here
                checkRoundDone();
            }
        };
        // Bind the progress of the task to the ProgressBar
        roundTimerProgressBar.progressProperty().bind(task.progressProperty());
        // Run the task in a background thread
        new Thread(task).start();
    }

    private void checkRoundDone(){
        if (medKitProgressBar.getProgress() == 1 &&
                ammoProgressBar.getProgress() == 1 &&
                troopProgressBar.getProgress() == 1 && called){
            called = false;
            endRound(true);
        } else if (called && roundTimerProgressBar.getProgress() == 1){
            endRound(false);
        }
    }

    private void endRound(boolean roundFinish){
        if (roundFinish){

            int roundWinPrize = gameEnvironment.roundWinPrize();
            gameEnvironment.showAlert("Round Completed!",
                    "Congratulations you have beaten round " + gameEnvironment.getCurrentRound() +
                            " and you have won $" + roundWinPrize + "! \nYour towers " +
                            "have now leveled up!", Alert.AlertType.INFORMATION);
            if (gameEnvironment.isGameWon()){
                gameEnvironment.setGameWon(true);
                gameEnvironment.launchEndGameScreen();
            } else {

                if (gameEnvironment.shouldTriggerRandomEvent()) {
                    gameEnvironment.initiateRandomEvent();
                }

                gameEnvironment.updateShopPostRound();
                gameEnvironment.setCurrentRound(gameEnvironment.getCurrentRound() + 1);
                gameEnvironment.levelUpTowers(newRound.getNumCarts());
                gameEnvironment.refreshGameScreen();
            }
        } else {
            gameEnvironment.loseLife();
            if (gameEnvironment.isGameLost()){
                gameEnvironment.launchEndGameScreen();
            } else {
                gameEnvironment.showAlert("Round failed!", "You have lost a life", Alert.AlertType.INFORMATION);

                if (gameEnvironment.shouldTriggerRandomEvent()) {
                    gameEnvironment.initiateRandomEvent();
                }

                gameEnvironment.updateShopPostRound();
                gameEnvironment.refreshGameScreen();
            }
        }

    }
}
