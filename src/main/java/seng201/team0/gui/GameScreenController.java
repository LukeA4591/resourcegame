package seng201.team0.gui;

import javafx.application.Platform;
import javafx.util.Duration;
import seng201.team0.GameEnvironment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import seng201.team0.models.Round;
import seng201.team0.models.towers.Tower;

/**
 * Controller class for the game screen where the main gameplay functionality is carried out.
 */
public class GameScreenController {
    /**
     * Label for displaying player name.
     */
    @FXML
    private Label playerNameLabel;
    /**
     * label for displaying game difficulty.
     */
    @FXML
    private Label gameDifficultyLabel;
    /**
     * label for displaying round count.
     */
    @FXML
    private Label roundCounterLabel;
    /**
     * label for displaying current balance.
     */
    @FXML
    private Label currentBalanceLabel;
    /**
     * label for displaying lives left.
     */
    @FXML
    private Label livesLeftLabel;
    /**
     * Label for displaying tower 1 name.
     */
    @FXML
    private Label tower1NameLabel;
    /**
     * Label for displaying tower 1 level.
     */
    @FXML
    private Label tower1LevelLabel;
    /**
     * Label for displaying tower 2 name.
     */
    @FXML
    private Label tower2NameLabel;
    /**
     * Label for displaying tower 2 level.
     */
    @FXML
    private Label tower2LevelLabel;
    /**
     * Label for displaying tower 3 name.
     */
    @FXML
    private Label tower3NameLabel;
    /**
     * Label for displaying tower 3 level.
     */
    @FXML
    private Label tower3LevelLabel;
    /**
     * Label for displaying tower 4 name.
     */
    @FXML
    private Label tower4NameLabel;
    /**
     * Label for displaying the round mode.
     */
    @FXML
    private Label roundModeLabel;
    /**
     * label for displaying the round info.
     */
    @FXML
    private Label roundInfoLabel1;
    /**
     * label for displaying the round info.
     */
    @FXML
    private Label roundInfoLabel2;
    /**
     * label to help display the round timer.
     */
    @FXML
    private Label roundTimerLabel;
    /**
     * label to help display the fill supply truck button.
     */
    @FXML
    private Label fillSupplyTruckLabel;
    /**
     * label to help display the fill humvee button.
     */
    @FXML
    private Label fillHumveeLabel;
    /**
     * label to help display the fill ambulance button.
     */
    @FXML
    private Label fillAmbulanceLabel;
    /**
     * button to fill the supply truck.
     */
    @FXML
    private Button loadSupplyTruckButton;
    /**
     * button to fill the humvee.
     */
    @FXML
    private Button loadHumveeButton;
    /**
     * button to fill the ambulance.
     */
    @FXML
    private Button loadAmbulanceButton;
    /**
     * button to access the shop.
     */
    @FXML
    private Button shopButton;
    /**
     * button to access the inventory
     */
    @FXML
    private Button inventoryButton;
    /**
     * button to start the round.
     */
    @FXML
    private Button startRoundButton;
    /**
     * button to select the round mode.
     */
    @FXML
    private Button selectRoundModeButton;
    /**
     * combobox to view the round mode options.
     */
    @FXML
    private ComboBox<String> roundModeComboBox;
    /**
     * progress bar to show progress in filling ammunition carts.
     */
    @FXML
    private ProgressBar ammoProgressBar;
    /**
     * progress bar to show progress in filling the troop carts.
     */
    @FXML
    private ProgressBar troopProgressBar;
    /**
     * progress bar to show progress in filling the med-kit carts.
     */
    @FXML
    private ProgressBar medKitProgressBar;
    /**
     * progress bar to display the round time.
     */
    @FXML
    private ProgressBar roundTimerProgressBar;
    /**
     * String to hold the current round mode.
     */
    private String roundMode;
    /**
     * create a game environment variable
     */
    private final GameEnvironment gameEnvironment;
    /**
     * store the med-kits needed for the round
     */
    private int medKitsNeeded;
    /**
     * store the ammunition needed for the round
     */
    private int ammunitionNeeded;
    /**
     * store the troops needed for the round
     */
    private int troopsNeeded;
    /**
     * create a round variable.
     */
    private Round newRound;
    /**
     * create variable to ensure that endRound isn't called twice
     */
    private boolean called = true;
    /**
     * Variable to ensure that floating point arithmetic error doesn't affect the round.
     */
    int count;

    /**
     * Constructor for GameScreen Controller.
     *
     * @param tempEnvironment the game environment containing the games state
     */
    public GameScreenController(final GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    /**
     * Initializes the game screen. Sets the visibility of components used during the round to false.
     */
    @FXML
    public void initialize() {
        // display round modes in combo box
        roundModeComboBox.getItems().addAll("Artillery Barrage", "Ground Offensive", "Rescue Operation");
        // set progress bars to 0
        ammoProgressBar.setProgress(0);
        troopProgressBar.setProgress(0);
        medKitProgressBar.setProgress(0);
        roundTimerProgressBar.setProgress(0);
        // set visibility of round controls to false
        ammoProgressBar.setVisible(false);
        troopProgressBar.setVisible(false);
        medKitProgressBar.setVisible(false);
        roundTimerProgressBar.setVisible(false);
        roundTimerLabel.setVisible(false);
        fillSupplyTruckLabel.setVisible(false);
        fillAmbulanceLabel.setVisible(false);
        fillHumveeLabel.setVisible(false);
        // disable resources buttons until round starts
        loadSupplyTruckButton.setDisable(true);
        loadAmbulanceButton.setDisable(true);
        loadHumveeButton.setDisable(true);
        updatePlayerDetails();
        updateTowerLabels();


    }
    /**
     * Update labels for the towers in game screen.
     */
    @FXML
    private void updateTowerLabels() {
        for (Tower tower : gameEnvironment.getMainTowers()) {
            switch (tower.getResourceType()) {
                // set tower name and level labels for each resource
                case "Ammunition":
                    tower1NameLabel.setText(tower.getName());
                    tower1LevelLabel.setText(tower.getLevelRepresentation());
                    break;
                case "Troops":
                    tower2NameLabel.setText(tower.getName());
                    tower2LevelLabel.setText(tower.getLevelRepresentation());
                    break;
                case "Medkits":
                    tower3NameLabel.setText(tower.getName());
                    tower3LevelLabel.setText(tower.getLevelRepresentation());
                    break;
            }
        }
        // set support tower name
        if (gameEnvironment.getSupportTower() != null) {
            tower4NameLabel.setText(gameEnvironment.getSupportTower().getName());
        }
        else {
            tower4NameLabel.setText("Locked");
        }
    }

    /**
     * Updates the player details displayed on the game screen.
     */
    @FXML
    private void updatePlayerDetails() {
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: " + gameEnvironment.getCurrentRound() +
                " / " + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());
    }

    /**
     * Handles the event when shop button is clicked.
     */
    @FXML
    private void onShopButtonClicked() {
        gameEnvironment.closeGameScreen(false);
    }

    /**
     * Handles the event when inventory button is clicked.
     */
    @FXML
    private void onInventoryButtonClicked() {
        gameEnvironment.closeGameScreen(true);
    }

    /**
     * Handles the event when select round mode is clicked. If no round mode has been chosen display an alert.
     * If a round mode has been chosen, Initialise the round and number of carts then display the information.
     */
    @FXML
    private void onSelectRoundModeClicked() {
        // get round mode selected from combo box
        this.roundMode = roundModeComboBox.getValue();
        if (roundMode == null) {
            gameEnvironment.showAlert("Invalid Round Mode",
                    "Please choose a mode for your next round", Alert.AlertType.ERROR);
        } else {
            roundModeLabel.setText("Round Mode: " + roundMode);
            gameEnvironment.setRoundMode(roundMode);
            newRound = new Round(gameEnvironment.getCurrentRound(),
                    roundMode, gameEnvironment);
            int numberOfAmmunitionCarts = newRound.getAmmunitionCarts().size();
            int numberOfMedkitCarts = newRound.getMedkitCarts().size();
            int numberOfTroopCarts = newRound.getTroopCarts().size();
            roundInfoLabel1.setText(numberOfAmmunitionCarts + " Ammunition Carts | " + numberOfTroopCarts +
                    " Troop Carts | " + numberOfMedkitCarts + " Medkit Carts");

            switch (roundMode) {
                case "Artillery Barrage":
                    roundInfoLabel2.setText("+2 Ammunition Tower Levels | +1 Troop Tower Levels | +1 " +
                            "Medkit Tower Levels");
                    break;
                case "Ground Offensive":
                    roundInfoLabel2.setText("+1 Ammunition Tower Levels | +2 Troop Tower Levels | +1 " +
                            "Medkit Tower Levels");
                    break;
                case "Rescue Operation":
                    roundInfoLabel2.setText("+1 Ammunition Tower Levels | +1 Troop Tower Levels | +2 " +
                            "Medkit Tower Levels");
                    break;
            }
        }
    }

    /**
     * Handles the event when load supply truck button is clicked. Adds ammunition to the total already collected.
     * Checks if ammunition collected is more or equal to ammunition needed. If it is, disable button and display text.
     * If not display new collected ammunition in progress bar.
     */
    @FXML
    private void onLoadSupplyTruckButtonClicked() {
        // add the resources gathered by ammunition tower to round instance
        newRound.increaseAmmunitionCollected();
        int ammunitionCollected = newRound.getAmmunitionCollected();

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

    /**
     * Handles the event when load humvee button is clicked. Adds troops to the total already collected.
     * Checks if troops collected is more or equal to troops needed. If it is, disable button and display text.
     * If not display new collected troops in progress bar.
     */
    @FXML
    private void onLoadHumveeButtonClicked() {
        // add the resources gathered by troop tower to round instance
        newRound.increaseTroopsCollected();
        int troopsCollected = newRound.getTroopsCollected();
        if (troopsCollected >= troopsNeeded) {
            troopProgressBar.setProgress(1);
            loadHumveeButton.setDisable(true);
            fillHumveeLabel.setText("Humvee full!");
            checkRoundDone();
        } else {
            troopProgressBar.setProgress((double) troopsCollected / troopsNeeded);
            disableButtonForTime(loadHumveeButton, newRound.getTroopTowerReload());
        }
    }

    /**
     * Handles the event when load ambulance button is clicked. Adds med-kits to the total already collected.
     * Checks if med-kits collected is more or equal to med-kits needed. If it is, disable button and display text.
     * If not display new collected med-kits in progress bar.
     */
    @FXML
    private void onLoadAmbulanceButtonClicked() {
        // add the resources gathered by med-kit tower to round instance
        newRound.increaseMedKitsCollected();
        int medKitsCollected = newRound.getMedKitsCollected();
        if (medKitsCollected >= medKitsNeeded) {
            medKitProgressBar.setProgress(1);
            loadAmbulanceButton.setDisable(true);
            fillAmbulanceLabel.setText("Ambulance full!");
            checkRoundDone();
        } else {
            medKitProgressBar.setProgress((double) medKitsCollected / medKitsNeeded);
            disableButtonForTime(loadAmbulanceButton, newRound.getMedkitTowerReload());
        }
    }

    /**
     * Handles the event when start round button is clicked. Sets the visibility for all controls not needed in
     * the round to false. Sets all others to true. Starts the round timer.
     */
    @FXML
    private void onStartRoundButtonClicked() {

        if (roundMode != null) {

            if (!gameEnvironment.isMainTowerBroken()) {
                // set visibility for game screen components not needed for the round to false
                roundModeComboBox.setVisible(false);
                selectRoundModeButton.setVisible(false);
                roundModeLabel.setVisible(false);
                roundInfoLabel1.setVisible(false);
                roundInfoLabel2.setVisible(false);
                shopButton.setVisible(false);
                inventoryButton.setVisible(false);
                startRoundButton.setVisible(false);
                // set visibility for round components to true
                ammoProgressBar.setVisible(true);
                troopProgressBar.setVisible(true);
                medKitProgressBar.setVisible(true);
                roundTimerProgressBar.setVisible(true);
                roundTimerLabel.setVisible(true);
                fillSupplyTruckLabel.setVisible(true);
                fillAmbulanceLabel.setVisible(true);
                fillHumveeLabel.setVisible(true);
                // enable load resource buttons
                loadSupplyTruckButton.setDisable(false);
                loadAmbulanceButton.setDisable(false);
                loadHumveeButton.setDisable(false);
                medKitsNeeded = newRound.getMedKitsRequired();
                ammunitionNeeded = newRound.getAmmunitionRequired();
                troopsNeeded = newRound.getTroopsRequired();
                startProgressTimer(gameEnvironment.getTrackDistance() / newRound.getCartSpeed());
            }
            else {
                gameEnvironment.showAlert("Main Tower is Broken", "Round cannot be played with a " +
                        "broken tower", Alert.AlertType.ERROR);
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Round Mode",
                    "Please choose a Round Mode for your next round", Alert.AlertType.ERROR);
        }
    }

    /**
     * disables a button for an amount of time, seconds. Disables the button then creates a new Timeline instance
     * the Timeline contains a KeyFrame instance that uses Duration.millis to pause for parameter seconds.
     * Button is then enabled.
     * @param button button to be disabled for some time
     * @param seconds disables button for set amount of time
     */
    private void disableButtonForTime(final Button button, final double seconds) {
        button.setDisable(true); // Disable the button

        // Create a Timeline to re-enable the button after the specified time
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(seconds * 1000),
                event -> button.setDisable(false) // Re-enable the button after the duration

        ));
        timeline.setCycleCount(1);
        timeline.play(); // Start the timeline
    }

    /**
     * Called when the round starts. Takes a duration in seconds and displays the time interval on a progress bar.
     * First creates a Timeline instance. A Timeline takes a KeyFrames instance. The keyframe instance takes 2 parameters,
     * time, and event. This keyframe performs every 1 second, the event is updating the progress bar by 1 second. This
     * is repeated howe many seconds are in the round.
     * @param durationInSeconds The time for the total progress timer to take.
     */
    private void startProgressTimer(final int durationInSeconds) {
        count = 0;

        roundTimerProgressBar.setProgress(0.0);

        // Define a Timeline to update progress bar
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    double progress = roundTimerProgressBar.getProgress() + 1.0 / durationInSeconds;
                    roundTimerProgressBar.setProgress(progress);
                    count += 1;
                    if (count == durationInSeconds) {
                        roundTimerProgressBar.setProgress(1);
                    } // update progress bar every second and check if it is full
                })
        );
        timeline.setCycleCount(durationInSeconds); // repeat for however many seconds in the round
        timeline.setOnFinished(event -> checkRoundDone());
        timeline.play();
    }

    /**
     * This event is called when a cart is filled or the round timer finishes. If all three carts are full, set
     * roundWon to true. If the timer finishes set the round to lost.
     */
    private void checkRoundDone() {
        Platform.runLater(() -> {
            if (medKitProgressBar.getProgress() == 1 &&
                    ammoProgressBar.getProgress() == 1 &&
                    troopProgressBar.getProgress() == 1 && called) {
                called = false;
                endRound(true);
            } else if (called && roundTimerProgressBar.getProgress() >= 1) {
                endRound(false);
            }
        }
        );
    }

    /**
     * Handles when the round is finished. If round is won, display alert and check if it was the final round.
     * If round is lost display alert and lose a life and check if the game is lost. This method also initialises a
     * random event.
     * @param roundWon true if the round was won, otherwise false.
     */
    private void endRound(final boolean roundWon) {
        if (roundWon) {

            int roundWinBonus = gameEnvironment.getRoundWinBonus();
            gameEnvironment.showAlert("Round Completed!",
                    "Congratulations you have beaten round " + gameEnvironment.getCurrentRound() +
                            " and you have won $" + roundWinBonus + "! \nYour towers " +
                            "have now leveled up!", Alert.AlertType.INFORMATION);

            if (gameEnvironment.isGameWon()) {
                gameEnvironment.setGameWon(true);
                gameEnvironment.launchEndGameScreen();

            } else {

                if (gameEnvironment.shouldTriggerRandomEvent()) {
                    gameEnvironment.initiateRandomEvent();
                }

                gameEnvironment.updateShopPostRound();
                gameEnvironment.setCurrentRound(gameEnvironment.getCurrentRound() + 1);
                gameEnvironment.levelUpTowers();
                gameEnvironment.refreshGameScreen();
            }
        } else {

            gameEnvironment.loseLife();

            if (gameEnvironment.isGameLost()) {
                gameEnvironment.launchEndGameScreen();
            } else {

                if (gameEnvironment.shouldTriggerRandomEvent()) {
                    gameEnvironment.initiateRandomEvent();
                }

                gameEnvironment.showAlert("Round failed!", "You have lost a life",
                        Alert.AlertType.INFORMATION);


                gameEnvironment.updateShopPostRound();
                gameEnvironment.refreshGameScreen();
            }
        }

    }
}
