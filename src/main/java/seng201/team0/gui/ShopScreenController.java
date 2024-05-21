package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.items.Item;

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
    private ToggleButton myTowerButton1;
    @FXML
    private ToggleButton myTowerButton2;
    @FXML
    private ToggleButton myTowerButton3;
    @FXML
    private ToggleButton myTowerButton4;

    @FXML
    private ToggleButton myItemButton1;
    @FXML
    private ToggleButton myItemButton2;
    @FXML
    private ToggleButton myItemButton3;


    @FXML
    private VBox descriptionVBox;


    private final ToggleGroup toggleButtons = new ToggleGroup();


    private List<ToggleButton> towerShopButtons;
    private List<ToggleButton> playerItemButtons;
    private List<ToggleButton> itemsInShopButtons;
    private List<ToggleButton> reserveTowerButtons;

    private GameEnvironment gameEnvironment;

    public ShopScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {

        towerShopButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button, tower6Button,
                tower7Button, tower8Button, tower9Button);
        reserveTowerButtons = List.of(myTowerButton1, myTowerButton2, myTowerButton3, myTowerButton4);
        playerItemButtons = List.of(myItemButton1, myItemButton2, myItemButton3);
        itemsInShopButtons = List.of(item1Button, item2Button, item3Button);

        updatePlayerDetails();
        initializeToggleButtonGroups();
        updateToggleButtons();
        initializeDescriptions();


    }
    @FXML
    private void initializeDescriptions() {

        for (ToggleButton button : towerShopButtons) {
            button.setOnAction(event -> displayTowerInformation(gameEnvironment.getTowerInShopByName(button.getText())));
        }

        item1Button.setOnAction(event -> displayItemInformation(gameEnvironment.getItemInShopByName(item1Button.getText())));
        item2Button.setOnAction(event -> displayItemInformation(gameEnvironment.getItemInShopByName(item2Button.getText())));
        item3Button.setOnAction(event -> displayItemInformation(gameEnvironment.getItemInShopByName(item3Button.getText())));

        myItemButton1.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(myItemButton1.getText())));
        myItemButton2.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(myItemButton2.getText())));
        myItemButton3.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(myItemButton3.getText())));

        myTowerButton1.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(myTowerButton1.getText())));
        myTowerButton2.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(myTowerButton2.getText())));
        myTowerButton3.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(myTowerButton3.getText())));
        myTowerButton4.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(myTowerButton4.getText())));


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
                tower6Button, tower7Button, tower8Button, tower9Button, item1Button, item2Button, item3Button, myItemButton1,
                myItemButton2, myItemButton3, myTowerButton1, myTowerButton2, myTowerButton3, myTowerButton4);

        for (ToggleButton button : buttons) {
            button.setToggleGroup(toggleButtons);

        }
    }

    @FXML
    private void updateToggleButtons() {

        for (int i = 0; i < gameEnvironment.getAvailableTowersInShop().size(); i++) {
            towerShopButtons.get(i).setDisable(false);
            towerShopButtons.get(i).setText(gameEnvironment.getAvailableTowersInShop().get(i).getName());
        }
        for (int i = gameEnvironment.getAvailableTowersInShop().size(); i < towerShopButtons.size(); i++) {

            towerShopButtons.get(i).setText("Locked");
            towerShopButtons.get(i).setDisable(true);
        }

        for (int i = 0; i < gameEnvironment.getAvailableItems().size(); i++) {
            itemsInShopButtons.get(i).setDisable(false);
            itemsInShopButtons.get(i).setText(gameEnvironment.getAvailableItems().get(i).getName());
        }
        for (int i = gameEnvironment.getAvailableItems().size(); i < itemsInShopButtons.size(); i++) {
            itemsInShopButtons.get(i).setText("Locked");
            itemsInShopButtons.get(i).setDisable(true);
        }

        for (int i = 0; i < gameEnvironment.getReserveTowers().size(); i++) {
            reserveTowerButtons.get(i).setDisable(false);
            reserveTowerButtons.get(i).setText(gameEnvironment.getReserveTowers().get(i).getName());
        }
        for (int i = gameEnvironment.getReserveTowers().size(); i < reserveTowerButtons.size(); i++) {
            reserveTowerButtons.get(i).setText("Locked");
            reserveTowerButtons.get(i).setDisable(true);
        }

        for (int i = 0; i < gameEnvironment.getPlayerItems().size(); i++) {
            playerItemButtons.get(i).setDisable(false);
            playerItemButtons.get(i).setText(gameEnvironment.getPlayerItems().get(i).getName());
        }
        for (int i = gameEnvironment.getPlayerItems().size(); i < playerItemButtons.size(); i++) {
            playerItemButtons.get(i).setText("Locked");
            playerItemButtons.get(i).setDisable(true);
        }


    }


    @FXML
    private void onBuyButtonClicked() {

        ToggleButton selectedButton = (ToggleButton) toggleButtons.getSelectedToggle();

        List<ToggleButton> towerButtonsForPurchase = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button, tower6Button);
        List<ToggleButton> itemButtonsForPurchase = List.of(item1Button, item2Button, item3Button);

        if (selectedButton != null) {

            if (towerButtonsForPurchase.contains(selectedButton)) {

                Tower selectedTower = gameEnvironment.getTowerInShopByName(selectedButton.getText());

                if (selectedTower != null && gameEnvironment.getCurrentBalance() >= selectedTower.getCost()) {
                    if (gameEnvironment.getReserveTowers().size() < 4) {
                        gameEnvironment.buyTower(selectedTower);
                        updatePlayerDetails();
                        selectedButton.setText("Purchased");
                        selectedButton.setDisable(true);
                        updateToggleButtons();

                        for (Item item : gameEnvironment.getPlayerItems()) {
                            gameEnvironment.applyItemEffect(item);
                        }

                    } else {
                        gameEnvironment.showAlert("Reserve Towers Full", "You do not have enough space in your inventory to purchase this tower.", Alert.AlertType.ERROR);
                    }
                } else {
                    gameEnvironment.showAlert("Insufficient Funds", "You do not have enough money to purchase this tower.", Alert.AlertType.ERROR);

                }
            } else if (itemButtonsForPurchase.contains(selectedButton)) {
                Item selectedItem = gameEnvironment.getItemInShopByName(selectedButton.getText());

                if (selectedItem != null && gameEnvironment.getCurrentBalance() >= selectedItem.getCost()) {

                    if (gameEnvironment.getPlayerItems().size() < 3) {
                        gameEnvironment.buyItem(selectedItem);
                        updatePlayerDetails();
                        selectedButton.setText("Purchased");
                        selectedButton.setDisable(true);
                        updateToggleButtons();
                        gameEnvironment.applyItemEffect(selectedItem);
                    } else {
                        gameEnvironment.showAlert("Item Slots Full", "You do not have enough space in your inventory to purchase this tower.", Alert.AlertType.ERROR);
                    }
                } else {
                    gameEnvironment.showAlert("Insufficient Funds", "You do not have enough money to purchase this item.", Alert.AlertType.ERROR);
                }
            } else {
                gameEnvironment.showAlert("Invalid Selection", "Please select a valid tower or item to buy it.", Alert.AlertType.ERROR);
            }
        } else {
            gameEnvironment.showAlert("No Selection", "Please select a tower or item to buy it.", Alert.AlertType.ERROR);
        }

    }


    @FXML
    private void onSellButtonClicked() {

        ToggleButton selectedButton = (ToggleButton) toggleButtons.getSelectedToggle();

        List<ToggleButton> playerTowerButtons = List.of(myTowerButton1, myTowerButton2, myTowerButton3, myTowerButton4);
        List<ToggleButton> playerItemButtons = List.of(myItemButton1, myItemButton2, myItemButton3);

        if (selectedButton != null) {
            if (playerTowerButtons.contains(selectedButton)) {

                Tower selectedTower = gameEnvironment.getReserveTowerByName(selectedButton.getText());

                if (selectedTower != null) {
                    gameEnvironment.sellTower(selectedTower);
                    selectedButton.setText("Locked");
                    selectedButton.setDisable(true);
                    updateToggleButtons();
                    updatePlayerDetails();
                }
            } else if (playerItemButtons.contains(selectedButton)) {

                Item selectedItem = gameEnvironment.getPlayerItemByName(selectedButton.getText());

                if (selectedItem != null) {
                    gameEnvironment.sellItem(selectedItem);
                    selectedButton.setText("Locked");
                    selectedButton.setDisable(true);
                    updateToggleButtons();
                    updatePlayerDetails();

                    gameEnvironment.removeItemEffect(selectedItem);
                }
            } else {
                gameEnvironment.showAlert("Invalid Selection", "Please select a valid tower or item to sell it.", Alert.AlertType.ERROR);
            }
        } else {
            gameEnvironment.showAlert("No Selection", "Please select a tower or item to buy it.", Alert.AlertType.ERROR);


        }
    }



    @FXML
    private void onReturnButtonClicked() {
        gameEnvironment.closeShopScreen();
    }

}
