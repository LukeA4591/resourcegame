package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert;

/**
 * Controller class for the setup screen.
 * It records the players selections and passes them to the game environment.
 */
public class SetupScreenController {
    /**
     * TextField which the player inputs their name.
     */
    @FXML
    private TextField playerName;
    /**
     * Slider which the player selects the amount of rounds to play.
     */
    @FXML
    private Slider roundSlider;
    /**
     * ComboBox which the player selects the game difficulty.
     */
    @FXML
    private ComboBox<String> difficultyBox;
    /**
     * VBox which shows the players selections.
     */
    @FXML
    private VBox detailsVBox;
    /**
     * String which records the player's name.
     */
    private String name;
    /**
     * int which records the chosen amount of rounds.
     */
    private int rounds;
    /**
     * String which records the chosen game difficulty.
     */
    private String difficulty;
    /**
     * boolean which records whether the player's information has been updated.
     */
    private boolean informationUpdated;
    /**
     * double which records the player's starting money.
     */
    private double startingMoney;
    /**
     * int which records the track distance.
     */
    private int trackDistance;
    /**
     * int which records the player's lives.
     */
    private int lives;
    /**
     * game environment which manages the state of the game.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Constructs a SetupScreenController which passes through the game environment as a parameter.
     *
     * @param tempEnvironment game environment which manages the state of the game.
     */
    public SetupScreenController(final GameEnvironment tempEnvironment) {
        this.informationUpdated = false;
        this.gameEnvironment = tempEnvironment;
    }

    /**
     * initializes the setup screen by adding available difficulties to the difficultyBox.
     */
    public void initialize() {
        difficultyBox.getItems().addAll( "Recruit", "Major", "Commander");

    }

    /**
     * Updates the GUI and records the players selections.
     */
    @FXML
    private void updateSelections() {

        this.name = playerName.getText();
        this.rounds = (int) roundSlider.getValue();
        this.difficulty = difficultyBox.getValue();

        if (nameChecker(name) && difficultyChecker()) {

            switch (difficulty) {
                case "Recruit":
                    this.startingMoney = 1000;
                    this.trackDistance = 1000;
                    this.lives = 3;
                    break;
                case "Major":
                    this.startingMoney = 800;
                    this.trackDistance = 900;
                    this.lives = 2;
                    break;
                case "Commander":
                    this.startingMoney = 600;
                    this.trackDistance = 800;
                    this.lives = 1;
                    break;

            }
            detailsVBox.getChildren().clear();
            detailsVBox.getChildren().addAll(
                    new Label("Player Name: " + name),
                    new Label("Rounds Selected: " + rounds),
                    new Label("Difficulty Selected: " + difficulty),
                    new Label("Starting Money: $" + startingMoney),
                    new Label("Track Distance: " + trackDistance + " metres"),
                    new Label("Lives: " + lives)
            );

            detailsVBox.setSpacing(5);
            informationUpdated = true;
        }
    }

    /**
     * Handles clicking the update button by updating the selections.
     */
    @FXML
    private void onUpdateClicked() {
        updateSelections();
    }

    /**
     * Handles clicking the proceed button by initializing a game and closing the setup screen.
     */
    @FXML
    private void onProceedClicked() {
        if (informationUpdated) {
            gameEnvironment.initializeGame(name, rounds, difficulty, startingMoney, trackDistance, lives);
            gameEnvironment.closeSetupScreen();
        }
        else {
            gameEnvironment.showAlert("Information not Updated", "Please update your information" +
                    " before continuing.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Checks if the player's name is valid. Takes the inputted name as a parameter and returns a boolean.
     *
     * @param name the player's inputted name.
     * @return true or false depending on the validity of the player's inputted name.
     */
    private boolean nameChecker(final String name) {
        if (name.length() > 15 || name.length() < 3 || !name.matches("^[0-9a-zA-Z]*$")) {
            gameEnvironment.showAlert("Invalid Name", "Name must be 3-15 characters long and not" +
                    " contain special characters.", Alert.AlertType.ERROR);
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Checks if the player has selected a valid game difficulty.
     *
     * @return true or false depending on the validity of the game difficulty selected.
     */
    private boolean difficultyChecker() {
        if (difficultyBox.getValue() == null) {
            gameEnvironment.showAlert("Invalid Difficulty", "Please select a difficulty.",
                    Alert.AlertType.ERROR);
            return false;
        }
        else {
            return true;
        }
    }
}
