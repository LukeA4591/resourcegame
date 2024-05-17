package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.*;

import javafx.scene.control.*;

public class TowerSelectScreenController {

    @FXML
    private Label playerName;
    @FXML
    private Label gameDifficulty;
    @FXML
    private Label roundCounter;
    @FXML
    private Label currentBalance;
    @FXML
    private Button armouryButton;
    @FXML
    private Button arsenalButton;
    @FXML
    private Button barracksButton;
    @FXML
    private Button garrisonButton;
    @FXML
    private Button medicalTentButton;
    @FXML
    private Button medbayButton;
    @FXML
    private VBox towerStatsVBox;


    private GameEnvironment gameEnvironment;

    public TowerSelectScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }
    @FXML
    public void initialize() {

        playerName.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficulty.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounter.setText("Round: 0/" + gameEnvironment.getGameRounds());
        currentBalance.setText("Current Balance: $" +gameEnvironment.getStartingBalance());

        armouryButton.setOnAction(event -> updateTowerStats(new Armoury()));
        arsenalButton.setOnAction(event -> updateTowerStats(new Arsenal()));
        barracksButton.setOnAction(event -> updateTowerStats(new Barracks()));
        garrisonButton.setOnAction(event -> updateTowerStats(new Garrison()));
        medbayButton.setOnAction(event -> updateTowerStats(new Medbay()));
        medicalTentButton.setOnAction(event -> updateTowerStats(new MedicalTent()));

    }

    public void updateTowerStats(Tower tower) {

        towerStatsVBox.getChildren().clear();
        towerStatsVBox.getChildren().addAll(
                new Label("Name:   " + tower.getName()),
                new Label("Resource type:   " + tower.getResourceType()),
                new Label("Resources per click:   " + tower.getResourceAmount()),
                new Label("Reload speed:   " + tower.getReloadSpeed()),
                new Label("Cost:   " + tower.getCost())
        );
    }



}
