package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.SupportTower;
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
    private Label towerLevelLabel;

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
    private ToggleButton item4Button;
    @FXML
    private ToggleButton item5Button;
    @FXML
    private ToggleButton item6Button;

    @FXML
    private ToggleButton playerTower1Button;
    @FXML
    private ToggleButton playerTower2Button;
    @FXML
    private ToggleButton playerTower3Button;
    @FXML
    private ToggleButton playerTower4Button;

    @FXML
    private ToggleButton playerItem1Button;
    @FXML
    private ToggleButton playerItem2Button;
    @FXML
    private ToggleButton playerItem3Button;


    @FXML
    private VBox descriptionVBox;


    private final ToggleGroup toggleButtons = new ToggleGroup();


    private List<ToggleButton> towerShopButtons;
    private List<ToggleButton> playerItemButtons;
    private List<ToggleButton> shopItemsButtons;
    private List<ToggleButton> playerTowerButtons;

    private final GameEnvironment gameEnvironment;

    public ShopScreenController(final GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {

        towerShopButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button, tower6Button,
                tower7Button, tower8Button, tower9Button);
        playerTowerButtons = List.of(playerTower1Button, playerTower2Button, playerTower3Button, playerTower4Button);
        playerItemButtons = List.of(playerItem1Button, playerItem2Button, playerItem3Button);
        shopItemsButtons = List.of(item1Button, item2Button, item3Button, item4Button, item5Button, item6Button);

        updatePlayerDetails();
        initializeToggleButtonGroups();
        updateToggleButtons();
        initializeDescriptions();
    }
    @FXML
    private void initializeDescriptions() {

        for (ToggleButton button : towerShopButtons) {
            button.setOnAction(event -> displayTowerInformation(gameEnvironment.getTowerInShopByName(
                    button.getText())));
        }
        for (ToggleButton button : shopItemsButtons) {
            button.setOnAction(event -> displayItemInformation(gameEnvironment.getItemInShopByName(
                    button.getText())));
        }
        for (ToggleButton button : playerItemButtons) {
            button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(
                    button.getText())));
        }
        for (ToggleButton button : playerTowerButtons) {
            button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(
                    button.getText())));
        }
    }

    @FXML
    private void displaySupportTowerInformation(final SupportTower tower) {

        towerLevelLabel.setText("");

        InventoryScreenController.displaySupportTowerInformation(tower, descriptionVBox);

    }


    @FXML
    private void displayItemInformation(final Item item) {
        descriptionVBox.getChildren().clear();

        towerLevelLabel.setText("");

        InventoryScreenController.displayItemInformation(item, descriptionVBox);
    }

    @FXML
    private void displayTowerInformation(final Tower tower) {

        if (tower instanceof SupportTower) {
            displaySupportTowerInformation((SupportTower) tower);
        }
        else {

            towerLevelLabel.setText(tower.getLevelRepresentation());

            InventoryScreenController.displayTowerInformation(tower, descriptionVBox);
        }
    }

    @FXML
    private void updatePlayerDetails() {
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: " + gameEnvironment.getCurrentRound() +
                " / " + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());
    }

    @FXML
    private void initializeToggleButtonGroups() {

        List<ToggleButton> buttons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button,
                tower6Button, tower7Button, tower8Button, tower9Button, item1Button, item2Button, item3Button,
                item4Button, item5Button, item6Button, playerItem1Button, playerItem2Button, playerItem3Button,
                playerTower1Button, playerTower2Button, playerTower3Button, playerTower4Button);

        for (ToggleButton button : buttons) {
            button.setToggleGroup(toggleButtons);

        }
    }

    @FXML
    private void updateToggleButtons() {

        for (int i = 0; i < gameEnvironment.getTowersInShop().size(); i++) {
            towerShopButtons.get(i).setDisable(false);
            towerShopButtons.get(i).setText(gameEnvironment.getTowersInShop().get(i).getName());
        }
        for (int i = gameEnvironment.getTowersInShop().size(); i < towerShopButtons.size(); i++) {

            towerShopButtons.get(i).setText("Locked");
            towerShopButtons.get(i).setDisable(true);
        }

        for (int i = 0; i < gameEnvironment.getShopItems().size(); i++) {
            shopItemsButtons.get(i).setDisable(false);
            shopItemsButtons.get(i).setText(gameEnvironment.getShopItems().get(i).getName());
        }
        for (int i = gameEnvironment.getShopItems().size(); i < shopItemsButtons.size(); i++) {
            shopItemsButtons.get(i).setText("Locked");
            shopItemsButtons.get(i).setDisable(true);
        }

        for (int i = 0; i < gameEnvironment.getReserveTowers().size(); i++) {
            playerTowerButtons.get(i).setDisable(false);
            playerTowerButtons.get(i).setText(gameEnvironment.getReserveTowers().get(i).getName());

            if (gameEnvironment.getReserveTowers().get(i).isBroken()) {
                playerTowerButtons.get(i).setDisable(true);
            }

        }
        for (int i = gameEnvironment.getReserveTowers().size(); i < playerTowerButtons.size(); i++) {
            playerTowerButtons.get(i).setText("Locked");
            playerTowerButtons.get(i).setDisable(true);
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

        if (selectedButton != null) {

            if (towerShopButtons.contains(selectedButton)) {

                Tower selectedTower = gameEnvironment.getTowerInShopByName(selectedButton.getText());


                if (selectedTower instanceof SupportTower) {
                    gameEnvironment.buyTower(selectedTower);
                    updatePlayerDetails();
                    updateToggleButtons();
                }
                else {

                    if (selectedTower != null && gameEnvironment.getCurrentBalance() >= selectedTower.getCost()) {
                        if (gameEnvironment.getReserveTowers().size() < 4) {
                            gameEnvironment.buyTower(selectedTower);
                            updatePlayerDetails();
                            updateToggleButtons();


                            for (Item item : gameEnvironment.getPlayerItems()) {
                                gameEnvironment.applyItemEffect(item);
                            }

                        } else {
                            gameEnvironment.showAlert("Reserve Towers Full", "You do not have enough" +
                                    " space" +
                                    " in your inventory to purchase this tower.", Alert.AlertType.ERROR);
                        }
                    }
                    else {
                        gameEnvironment.showAlert("Insufficient Funds", "You do not have enough money" +
                                " to " +
                                "purchase this tower.", Alert.AlertType.ERROR);
                    }

                }
            } else if (shopItemsButtons.contains(selectedButton)) {
                Item selectedItem = gameEnvironment.getItemInShopByName(selectedButton.getText());

                if (selectedItem != null && gameEnvironment.getCurrentBalance() >= selectedItem.getCost()) {

                    if (gameEnvironment.getPlayerItems().size() < 3) {
                        gameEnvironment.buyItem(selectedItem);
                        updatePlayerDetails();
                        updateToggleButtons();
                        gameEnvironment.applyItemEffect(selectedItem);
                    } else {
                        gameEnvironment.showAlert("Item Slots Full", "You do not have enough space in" +
                                " your inventory to purchase this tower.", Alert.AlertType.ERROR);
                    }
                } else {
                    gameEnvironment.showAlert("Insufficient Funds", "You do not have enough money to" +
                            " purchase this item.", Alert.AlertType.ERROR);
                }
            } else {
                gameEnvironment.showAlert("Invalid Selection", "Please select a valid tower or item" +
                        " to buy it.", Alert.AlertType.ERROR);
            }
        } else {
            gameEnvironment.showAlert("No Selection", "Please select a tower or item to buy it.",
                    Alert.AlertType.ERROR);
        }

    }


    @FXML
    private void onSellButtonClicked() {

        ToggleButton selectedButton = (ToggleButton) toggleButtons.getSelectedToggle();

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
                gameEnvironment.showAlert("Invalid Selection", "Please select a valid tower or item to" +
                        " sell it.", Alert.AlertType.ERROR);
            }
        } else {
            gameEnvironment.showAlert("No Selection", "Please select a tower or item to buy it.",
                    Alert.AlertType.ERROR);


        }
    }



    @FXML
    private void onReturnButtonClicked() {
        gameEnvironment.closeShopScreen();
    }

}
