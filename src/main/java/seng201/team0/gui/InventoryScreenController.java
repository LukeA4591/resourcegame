package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;

public class InventoryScreenController {

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
    private ToggleButton ammunitionTowerButton;
    @FXML
    private ToggleButton troopsTowerButton;
    @FXML
    private ToggleButton medkitsTowerButton;

    @FXML
    private ToggleButton reserveTower1Button;
    @FXML
    private ToggleButton reserveTower2Button;
    @FXML
    private ToggleButton reserveTower3Button;
    @FXML
    private ToggleButton reserveTower4Button;
    @FXML
    private ToggleButton reserveTower5Button;
    @FXML
    private ToggleButton reserveTower6Button;
    @FXML
    private ToggleButton item1Button;
    @FXML
    private ToggleButton item2Button;
    @FXML
    private ToggleButton item3Button;

    @FXML
    private Button swapTowersButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button returnButton;

    @FXML
    private VBox descriptionVBox;




    private GameEnvironment gameEnvironment;

    public InventoryScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {

        updatePlayerDetails();

        ammunitionTowerButton.setText(gameEnvironment.getSelectedTowers().get("Ammunition").getName());
        troopsTowerButton.setText(gameEnvironment.getSelectedTowers().get("Troops").getName());
        medkitsTowerButton.setText(gameEnvironment.getSelectedTowers().get("Medkits").getName());




    }

    @FXML
    public void updatePlayerDetails() {
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: 0/" + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());
    }

    @FXML
    public void onReturnButtonClicked() {
        gameEnvironment.closeInventoryScreen();
    }
}
