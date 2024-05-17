package seng201.team0.gui;

import seng201.team0.GameEnvironment;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GameScreenController {

    @FXML
    private Label playerName;
    @FXML
    private Label gameDifficulty;
    @FXML
    private Label roundCounter;
    @FXML
    private Label currentBalance;
    @FXML
    private Label roundTimerLabel;
    @FXML
    private Button endRoundButton;
    @FXML
    private Label tower1Name;
    @FXML
    private Button makeAmmoButton;
    @FXML
    private Label tower2Name;
    @FXML
    private Button makeMedButton;
    @FXML
    private Label tower3Name;
    @FXML
    private Button makeTroopButton;
    @FXML
    private Label tower4Name;
    @FXML
    private Label ammoProgress;
    @FXML
    private Label autoGenAmmo;
    @FXML
    private Label medProgress;
    @FXML
    private Label autoGenMed;
    @FXML
    private Label troopProgress;
    @FXML
    private Label autoGenTroop;
    @FXML
    private Label tower4Info;

    private GameEnvironment gameEnvironment;

    public GameScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }
}
