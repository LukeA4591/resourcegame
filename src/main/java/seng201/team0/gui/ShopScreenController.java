package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.Tower;

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


    @FXML
    private VBox descriptionVBox;



    private ToggleGroup toggleButtons = new ToggleGroup();



    private List<ToggleButton> towerButtons;

    private GameEnvironment gameEnvironment;

    public ShopScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {
        towerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button, tower6Button,
                tower7Button, tower8Button, tower9Button);

        updatePlayerDetails();
        initializeToggleButtonGroups();

        updateTowerToggleButtons();

        for (ToggleButton button : towerButtons) {
            button.setOnAction(event -> displayTowerInformation(gameEnvironment.getTowerInShopByName(button.getText())));
        }


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
    private void updateTowerToggleButtons() {

        for (int i = 0; i < gameEnvironment.getAvailableTowersInShop().size(); i++) {

            towerButtons.get(i).setText(gameEnvironment.getAvailableTowersInShop().get(i).getName());
        }

        for (int i = gameEnvironment.getAvailableTowersInShop().size(); i < towerButtons.size(); i++) {

            towerButtons.get(i).setText("Locked");
            towerButtons.get(i).setDisable(true);
        }

    }





    @FXML
    private void onBuyButtonClicked() {

        ToggleButton selectedButton = (ToggleButton) toggleButtons.getSelectedToggle();

        if (selectedButton != null) {

            Tower selectedTower = gameEnvironment.getTowerInShopByName(selectedButton.getText());

            if (selectedTower != null && gameEnvironment.getCurrentBalance() >= selectedTower.getCost()) {
                gameEnvironment.setCurrentBalance(gameEnvironment.getCurrentBalance() - selectedTower.getCost());
                gameEnvironment.buyTower(selectedTower);
                updatePlayerDetails();
                selectedButton.setText("Purchased");
                selectedButton.setDisable(true);
            }
            else {gameEnvironment.showAlert("Insufficient Funds", "You do not have enough money to purchase this tower.", Alert.AlertType.ERROR);

            }


        }
        else {gameEnvironment.showAlert("Invalid Tower Selection", "Please select a tower to buy it", Alert.AlertType.ERROR); }

    }

    @FXML
    private void onReturnButtonClicked() {
        gameEnvironment.closeShopScreen();
    }

}
