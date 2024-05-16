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
    @FXML
    private Button proceed;

    private GameEnvironment gameEnvironment;

    public SetupScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {
        difficultyBox.getItems().addAll( "Private", "Captain", "General");

    }

    @FXML
    public void updateSelections() {

        String name = playerName.getText();
        int rounds = (int) roundSlider.getValue();
        String difficulty = difficultyBox.getValue();

        if (nameChecker(name) & difficultyChecker()) {
            selectedPlayerName.setText("Player's Name: " + name);
            selectedRoundNumber.setText("Rounds Selected: " + rounds);
            selectedDifficulty.setText("Difficulty Selected: " + difficulty);
        }
    }

    @FXML
    private void onUpdateClicked() {
        updateSelections();
    }


    public boolean nameChecker(String name) {
        if (name.length() > 15 | name.length() < 3 | !name.matches("^[0-9a-zA-Z]*$")) {
            showAlert("Invalid Name", "Name must be 3-15 characters long and not contain special characters.");
            return false;
        }
        return true;
    }

    public boolean difficultyChecker() {
        if (difficultyBox.getValue() == null) {
            showAlert("Invalid Difficulty", "Please select a difficulty");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
