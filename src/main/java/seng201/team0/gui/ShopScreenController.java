package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import seng201.team0.GameEnvironment;

import java.util.List;

public class ShopScreenController {

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
    private ToggleButton tower1Button;
    @FXML
    private ToggleButton tower2Button;
    @FXML
    private ToggleButton tower3Button;
    @FXML
    private ToggleButton tower4Button;
    @FXML
    private ToggleButton tower5Button;
    @FXML
    private ToggleButton tower6Button;
    @FXML
    private ToggleButton tower7Button;
    @FXML
    private ToggleButton tower8Button;
    @FXML
    private ToggleButton tower9Button;
    @FXML
    private ToggleButton item1Button;
    @FXML
    private ToggleButton item2Button;
    @FXML
    private ToggleButton item3Button;



    private ToggleGroup toggleButtons = new ToggleGroup();

    private GameEnvironment gameEnvironment;

    public ShopScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {

        updatePlayerDetails();
        initializeToggleButtonGroups();

        updateToggleButtons();


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
    private void initializeToggleButtonGroups() {

        List<ToggleButton> buttons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button,
                tower6Button, tower7Button, tower8Button, tower9Button, item1Button, item2Button, item3Button);

        for (ToggleButton button : buttons) {
            button.setToggleGroup(toggleButtons);

        }
    }

    @FXML
    private void updateToggleButtons() {



    }





    @FXML
    private void onBuyButtonClicked() {

    }

    @FXML
    private void onReturnButtonClicked() {
        gameEnvironment.closeShopScreen();
    }

}
