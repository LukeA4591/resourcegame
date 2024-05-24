package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert;



public class SetupScreenController {

    @FXML
    private TextField playerName;
    @FXML
    private Slider roundSlider;
    @FXML
    private ComboBox<String> difficultyBox;
    @FXML
    private VBox detailsVBox;
    private String name;
    private int rounds;
    private String difficulty;
    private boolean informationUpdated;


    private double startingMoney;
    private int trackDistance;
    private int lives;

    private final GameEnvironment gameEnvironment;

    public SetupScreenController(final GameEnvironment tempEnvironment) {
        this.informationUpdated = false;
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {
        difficultyBox.getItems().addAll( "Recruit", "Major", "Commander");

    }
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

    @FXML
    private void onUpdateClicked() {
        updateSelections();
    }
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
