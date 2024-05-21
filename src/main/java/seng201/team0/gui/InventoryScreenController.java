package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.items.Item;
import seng201.team0.models.towers.*;

import java.util.List;

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
    private Label infoLabel;

    @FXML
    private ToggleButton ammunitionTowerButton;
    @FXML
    private ToggleButton troopsTowerButton;
    @FXML
    private ToggleButton medkitsTowerButton;
    @FXML
    private ToggleButton supportTowerButton;

    @FXML
    private ToggleButton reserveTower1Button;
    @FXML
    private ToggleButton reserveTower2Button;
    @FXML
    private ToggleButton reserveTower3Button;
    @FXML
    private ToggleButton reserveTower4Button;

    @FXML
    private ToggleButton item1Button;
    @FXML
    private ToggleButton item2Button;
    @FXML
    private ToggleButton item3Button;

    @FXML
    private Button swapTowersButton;
    @FXML
    private Button returnButton;

    @FXML
    private VBox descriptionVBox;

    private final ToggleGroup allToggleButtonsGroup = new ToggleGroup();
    private final ToggleGroup mainTowersToggleGroup = new ToggleGroup();
    private final ToggleGroup reserveTowersToggleGroup = new ToggleGroup();

    private List<ToggleButton> reserveToggleButtons;

    private boolean swappingTowers = false;





    private GameEnvironment gameEnvironment;

    public InventoryScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;

    }

    @FXML
    public void initialize() {

        initializeToggleButtons();
        updatePlayerDetails();
        updateButtons();
        initializeDescriptions();

    }

    @FXML
    private void initializeDescriptions() {

        ammunitionTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getMainTowerByName(ammunitionTowerButton.getText())));
        troopsTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getMainTowerByName(troopsTowerButton.getText())));
        medkitsTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getMainTowerByName(medkitsTowerButton.getText())));
        supportTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getSupportTower()));

        reserveTower1Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(reserveTower1Button.getText())));
        reserveTower2Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(reserveTower2Button.getText())));
        reserveTower3Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(reserveTower3Button.getText())));
        reserveTower4Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(reserveTower4Button.getText())));

        item1Button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(item1Button.getText())));
        item2Button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(item2Button.getText())));
        item3Button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(item3Button.getText())));

    }


    @FXML
    private void displayTowerInformation(Tower tower) {

        descriptionVBox.getChildren().clear();

        descriptionVBox.getChildren().addAll(
                new Label("Name:  " + tower.getName()),
                new Label("Resource type:  " + tower.getResourceType()),
                new Label("Resources per click:  " + tower.getResourceAmount()),
                new Label("Reload speed:  " + tower.getReloadSpeed()),
                new Label("Cost:  " + tower.getCost())
        );
    }

    @FXML
    private void displayItemInformation(Item item) {
        descriptionVBox.getChildren().clear();

        descriptionVBox.getChildren().addAll(
                new Label("Name: " + item.getName()),
                new Label("Affects: " + item.getTowerType() + " Towers"),
                new Label("Resource Boost: " + item.getResourceBoost()),
                new Label("Cost: " + item.getCost()),
                new Label("Sell Price: " + item.getSellPrice())
        );
    }


    @FXML
    private void initializeToggleButtons() {

        List<ToggleButton> allToggleButtons = List.of(ammunitionTowerButton, troopsTowerButton, medkitsTowerButton, supportTowerButton, reserveTower1Button, reserveTower2Button,
                reserveTower3Button, reserveTower4Button, item1Button, item2Button,
                item3Button);

        for (ToggleButton toggleButton : allToggleButtons) {
            toggleButton.setToggleGroup(allToggleButtonsGroup);
        }

        reserveToggleButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button);

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
    private void onReturnButtonClicked() {
        gameEnvironment.closeInventoryScreen();
    }


    @FXML
    private void onSwapTowersButtonClicked() {

        if (!swappingTowers) {
            enableSwapping();
        }
        else {
            swapSelectedTowers();
            disableSwapping();

        }

    }

    @FXML
    private void enableSwapping() {
        swappingTowers = true;

        ammunitionTowerButton.setToggleGroup(mainTowersToggleGroup);
        troopsTowerButton.setToggleGroup(mainTowersToggleGroup);
        medkitsTowerButton.setToggleGroup(mainTowersToggleGroup);

        List<ToggleButton> reserveButtons= List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button);

        for (ToggleButton button : reserveButtons) {
            button.setToggleGroup(reserveTowersToggleGroup);
        }

        returnButton.setDisable(true);
        infoLabel.setText("Tower Swap In Progress");
    }

    @FXML
    private void disableSwapping() {
        swappingTowers = false;

        List<ToggleButton> allToggleButtons = List.of(ammunitionTowerButton, troopsTowerButton, medkitsTowerButton, reserveTower1Button, reserveTower2Button,
                reserveTower3Button, reserveTower4Button, item1Button, item2Button, item3Button);

        for (ToggleButton toggleButton : allToggleButtons) {
            toggleButton.setToggleGroup(allToggleButtonsGroup);
        }

        returnButton.setDisable(false);
        infoLabel.setText("");

    }

    @FXML
    private void swapSelectedTowers() {

        ToggleButton selectedMainTowerButton =  (ToggleButton) mainTowersToggleGroup.getSelectedToggle();
        ToggleButton selectedReserveTowerButton = (ToggleButton) reserveTowersToggleGroup.getSelectedToggle();

        Tower mainTower = null;
        Tower reserveTower = null;

        if (selectedMainTowerButton != null && selectedReserveTowerButton != null) {

            for (Tower tower : gameEnvironment.getMainTowers()) {
                if (tower.getName().equals(selectedMainTowerButton.getText())) {
                    mainTower = tower;
                    break;
                }
            }
            for (Tower tower : gameEnvironment.getReserveTowers()) {
                if (tower.getName().equals(selectedReserveTowerButton.getText())) {
                    reserveTower = tower;
                    break;
                }
            }

            if (mainTower != null && reserveTower!= null) {
                gameEnvironment.swapTowers(mainTower, reserveTower);
                updateButtons();
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Tower Selection", "You must select two towers to swap them.", Alert.AlertType.ERROR);

        }

    }

    @FXML
    private void updateButtons() {

        ammunitionTowerButton.setText(gameEnvironment.getMainTowers().get(0).getName());
        troopsTowerButton.setText(gameEnvironment.getMainTowers().get(1).getName());
        medkitsTowerButton.setText(gameEnvironment.getMainTowers().get(2).getName());

        if (gameEnvironment.getSupportTower() != null) {
            supportTowerButton.setText(gameEnvironment.getSupportTower().getName());
        }
        else {
            supportTowerButton.setText("Locked");
            supportTowerButton.setDisable(true);
        }



        for (int i = 0; i < reserveToggleButtons.size(); i++) {

            if (i < gameEnvironment.getReserveTowers().size()) {
                reserveToggleButtons.get(i).setText(gameEnvironment.getReserveTowers().get(i).getName());
                reserveToggleButtons.get(i).setDisable(false);

            }
            else {
                reserveToggleButtons.get(i).setText("Locked");
                reserveToggleButtons.get(i).setDisable(true);
            }
        }

        List<ToggleButton> itemButtons = List.of(item1Button, item2Button, item3Button);

        for (int i = 0; i < itemButtons.size(); i++) {
            if (i < gameEnvironment.getPlayerItems().size() && gameEnvironment.getPlayerItems().get(i) != null) {
                itemButtons.get(i).setText(gameEnvironment.getPlayerItems().get(i).getName());
                itemButtons.get(i).setDisable(false);
            } else {
                itemButtons.get(i).setText("Locked");
                itemButtons.get(i).setDisable(true);
            }
        }
    }


}
