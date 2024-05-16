package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.models.GameEnvironment;

import javafx.scene.control.*;


public class SetupScreenController {

    @FXML
    private TextField playerName;
    @FXML
    private Slider roundSlider;
    @FXML
    private ComboBox<String> difficultyBox;
    @FXML
    private Label selectedPlayerName;
    @FXML
    private Label selectedRoundNumber;
    @FXML
    private Label selectedDifficulty;

    private String name;
    private int rounds;
    private String difficulty;
    private boolean informationUpdated;

    private GameEnvironment gameEnvironment;

    public SetupScreenController(GameEnvironment tempEnvironment) {
        this.informationUpdated = false;
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {
        difficultyBox.getItems().addAll( "Private", "Captain", "General");

    }
    @FXML
    public void updateSelections() {

        this.name = playerName.getText();
        this.rounds = (int) roundSlider.getValue();
        this.difficulty = difficultyBox.getValue();

        if (nameChecker(name) && difficultyChecker()) {
            selectedPlayerName.setText("Player's Name: " + name);
            selectedRoundNumber.setText("Rounds Selected: " + rounds);
            selectedDifficulty.setText("Difficulty Selected: " + difficulty);
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
            gameEnvironment.startGame(name, rounds, difficulty);
            gameEnvironment.closeSetupScreen();
        }
        else
            showAlert("Information not Updated", "Please update your information before continuing.");
    }


    public boolean nameChecker(String name) {
        if (name.length() > 15 || name.length() < 3 || !name.matches("^[0-9a-zA-Z]*$")) {
            showAlert("Invalid Name", "Name must be 3-15 characters long and not contain special characters.");
            return false;
        }
        else
            return true;
    }

    public boolean difficultyChecker() {
        if (difficultyBox.getValue() == null) {
            showAlert("Invalid Difficulty", "Please select a difficulty.");
            return false;
        }
        else
            return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }


}
