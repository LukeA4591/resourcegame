package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.*;
import javafx.scene.control.Alert.*;

import javafx.scene.control.*;

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


    private GameEnvironment gameEnvironment;

    public TowerSelectScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {

        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: 0/" + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());

        armouryButton.setOnAction(event -> updateTowerStats(new Armoury()));
        arsenalButton.setOnAction(event -> updateTowerStats(new Arsenal()));
        barracksButton.setOnAction(event -> updateTowerStats(new Barracks()));
        garrisonButton.setOnAction(event -> updateTowerStats(new Garrison()));
        medbayButton.setOnAction(event -> updateTowerStats(new Medbay()));
        medicalTentButton.setOnAction(event -> updateTowerStats(new MedicalTent()));

        armouryButton.setToggleGroup(ammunitionToggleGroup);
        arsenalButton.setToggleGroup(ammunitionToggleGroup);
        barracksButton.setToggleGroup(troopsToggleGroup);
        garrisonButton.setToggleGroup(troopsToggleGroup);
        medicalTentButton.setToggleGroup(medkitsToggleGroup);
        medbayButton.setToggleGroup(medkitsToggleGroup);



    }
    @FXML
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

    @FXML
    private void onConfirmClicked() {
        if (checkTowersSelected()) {
            storeTowers();
            gameEnvironment.closeTowerSelectScreen();
        }
        else showAlert("Invalid Tower Selection", "Please select your three starting towers.");
    }


    public boolean checkTowersSelected() {
        return ammunitionToggleGroup.getSelectedToggle() != null && troopsToggleGroup.getSelectedToggle() != null
                && medkitsToggleGroup.getSelectedToggle() != null;
    }

    public void storeTowers() {

        if (ammunitionToggleGroup.getSelectedToggle() == armouryButton) {
            gameEnvironment.addTower(new Armoury(), true);
            gameEnvironment.addTower(new Arsenal(), false);
        }  else {
            gameEnvironment.addTower(new Armoury(), false);
            gameEnvironment.addTower(new Arsenal(), true);
        }

        if (troopsToggleGroup.getSelectedToggle() == barracksButton) {
            gameEnvironment.addTower(new Barracks(), true);
            gameEnvironment.addTower(new Garrison(), false);
        }  else {
            gameEnvironment.addTower(new Barracks(), false);
            gameEnvironment.addTower(new Garrison(), true);
        }

        if (medkitsToggleGroup.getSelectedToggle() == medicalTentButton) {
            gameEnvironment.addTower(new MedicalTent(), true);
            gameEnvironment.addTower(new Medbay(), false);
        } else {
            gameEnvironment.addTower(new MedicalTent(), false);
            gameEnvironment.addTower(new Medbay(), true);
        }

    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }





}
