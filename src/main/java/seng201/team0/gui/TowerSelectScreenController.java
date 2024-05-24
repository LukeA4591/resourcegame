package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.Tower;
import javafx.scene.control.Alert;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import seng201.team0.models.towers.gametowers.*;


public class TowerSelectScreenController {

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
    private ToggleButton armouryButton;
    @FXML
    private ToggleButton arsenalButton;
    @FXML
    private ToggleButton barracksButton;
    @FXML
    private ToggleButton garrisonButton;
    @FXML
    private ToggleButton medicalTentButton;
    @FXML
    private ToggleButton medbayButton;
    @FXML
    private VBox towerStatsVBox;

    private final ToggleGroup ammunitionToggleGroup = new ToggleGroup();
    private final ToggleGroup troopsToggleGroup = new ToggleGroup();
    private final ToggleGroup medkitsToggleGroup = new ToggleGroup();

    private final GameEnvironment gameEnvironment;

    public TowerSelectScreenController(final GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {

        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: " + gameEnvironment.getCurrentRound() +
                " / " + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());

        armouryButton.setOnAction(event -> displayTowerInformation(new Armoury()));
        arsenalButton.setOnAction(event -> displayTowerInformation(new Arsenal()));
        barracksButton.setOnAction(event -> displayTowerInformation(new Barracks()));
        garrisonButton.setOnAction(event -> displayTowerInformation(new Garrison()));
        medbayButton.setOnAction(event -> displayTowerInformation(new Medbay()));
        medicalTentButton.setOnAction(event -> displayTowerInformation(new MedicalTent()));

        armouryButton.setToggleGroup(ammunitionToggleGroup);
        arsenalButton.setToggleGroup(ammunitionToggleGroup);
        barracksButton.setToggleGroup(troopsToggleGroup);
        garrisonButton.setToggleGroup(troopsToggleGroup);
        medicalTentButton.setToggleGroup(medkitsToggleGroup);
        medbayButton.setToggleGroup(medkitsToggleGroup);



    }
    @FXML
    public void displayTowerInformation(final Tower tower) {

        towerStatsVBox.getChildren().clear();
        towerStatsVBox.getChildren().addAll(
                new Label("Name: " + tower.getName()),
                new Label("Resource type: " + tower.getResourceType()),
                new Label("Resources per click: " + tower.getResourceAmount()),
                new Label("Reload speed: " + tower.getReloadSpeed() + " seconds"),
                new Label("Cost: $" + tower.getCost()),
                new Label("Sell Price: $" + tower.getSellPrice()),
                new Label("Description: " + tower.getDescription())
        );
    }

    @FXML
    private void onConfirmClicked() {
        if (checkTowersSelected()) {
            storeTowers();
            gameEnvironment.closeTowerSelectScreen();
        }
        else {
            gameEnvironment.showAlert("Invalid Tower Selection", "Please select your three starting" +
                    " towers.", Alert.AlertType.ERROR);
        }
    }


    public boolean checkTowersSelected() {
        return ammunitionToggleGroup.getSelectedToggle() != null && troopsToggleGroup.getSelectedToggle() != null
                && medkitsToggleGroup.getSelectedToggle() != null;
    }

    public void storeTowers() {

        if (ammunitionToggleGroup.getSelectedToggle() == armouryButton) {
            gameEnvironment.addTowerFromSelectScreen(new Armoury(), true);
            gameEnvironment.addTowerFromSelectScreen(new Arsenal(), false);
        }  else {
            gameEnvironment.addTowerFromSelectScreen(new Armoury(), false);
            gameEnvironment.addTowerFromSelectScreen(new Arsenal(), true);
        }

        if (troopsToggleGroup.getSelectedToggle() == barracksButton) {
            gameEnvironment.addTowerFromSelectScreen(new Barracks(), true);
            gameEnvironment.addTowerFromSelectScreen(new Garrison(), false);
        }  else {
            gameEnvironment.addTowerFromSelectScreen(new Barracks(), false);
            gameEnvironment.addTowerFromSelectScreen(new Garrison(), true);
        }

        if (medkitsToggleGroup.getSelectedToggle() == medicalTentButton) {
            gameEnvironment.addTowerFromSelectScreen(new MedicalTent(), true);
            gameEnvironment.addTowerFromSelectScreen(new Medbay(), false);
        } else {
            gameEnvironment.addTowerFromSelectScreen(new MedicalTent(), false);
            gameEnvironment.addTowerFromSelectScreen(new Medbay(), true);
        }

    }


}