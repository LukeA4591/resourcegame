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

/**
 * Controller class for the shop screen.
 * It manages the player's interactions with the shop, including buying and selling items and towers.
 */
public class ShopScreenController {
    /**
     * Label to display the player's name.
     */
    @FXML
    private Label playerNameLabel;
    /**
     * Label to display the game difficulty.
     */
    @FXML
    private Label gameDifficultyLabel;
    /**
     * Label to display the game round count.
     */
    @FXML
    private Label roundCounterLabel;
    /**
     * Label to display the player's current balance.
     */
    @FXML
    private Label currentBalanceLabel;
    /**
     * Label to display the player's lives left.
     */
    @FXML
    private Label livesLeftLabel;
    /**
     * Label that displays a selected tower's level.
     */
    @FXML
    private Label towerLevelLabel;
    /**
     * ToggleButtons for selecting towers in the shop.
     */
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
    /**
     * ToggleButtons for selecting items in the shop.
     */
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
    /**
     * ToggleButtons for selecting the player's towers.
     */
    @FXML
    private ToggleButton playerTower1Button;
    @FXML
    private ToggleButton playerTower2Button;
    @FXML
    private ToggleButton playerTower3Button;
    @FXML
    private ToggleButton playerTower4Button;
    /**
     * ToggleButtons for selecting the player's items.
     */
    @FXML
    private ToggleButton playerItem1Button;
    @FXML
    private ToggleButton playerItem2Button;
    @FXML
    private ToggleButton playerItem3Button;
    /**
     * VBox that displays a selected tower or items information.
     */
    @FXML
    private VBox descriptionVBox;
    /**
     * ToggleGroup for setting up various ToggleGroups.
     */
    private final ToggleGroup toggleButtons = new ToggleGroup();
    /**
     * List of the tower shop toggle buttons.
     */
    private List<ToggleButton> towerShopButtons;
    /**
     * List of the player's items toggle buttons.
     */
    private List<ToggleButton> playerItemButtons;
    /**
     * List of the item shop toggle buttons.
     */
    private List<ToggleButton> shopItemsButtons;
    /**
     * List of the player's towers toggle buttons.
     */
    private List<ToggleButton> playerTowerButtons;
    /**
     * game environment that manages the game's state.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Constructs a ShopScreenController with the specified game environment.
     *
     * @param tempEnvironment the game environment in which the state of the game is stored.
     */
    public ShopScreenController(final GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    /**
     * Initializes the shop screen by setting the ToggleButton lists, updating player details
     * , initializing ToggleGroups, updating the Toggle Buttons, and initializing tower and item
     * descriptions.
     */
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

    /**
     * Initializes the descriptions of towers and items when they are selected.
     */
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

    /**
     * Displays the information of a selected support tower.
     *
     * @param tower the selected support tower.
     */
    @FXML
    private void displaySupportTowerInformation(final SupportTower tower) {

        towerLevelLabel.setText("");

        InventoryScreenController.displaySupportTowerInformation(tower, descriptionVBox);

    }

    /**
     * Displays the information of a selected item.
     *
     * @param item the selected item.
     */
    @FXML
    private void displayItemInformation(final Item item) {
        descriptionVBox.getChildren().clear();

        towerLevelLabel.setText("");

        InventoryScreenController.displayItemInformation(item, descriptionVBox);
    }

    /**
     * Displays the selected tower's information.
     *
     * @param tower the selected tower.
     */
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

    /**
     * Updates the player's details including their name, game difficulty, round number
     * , current balance, and lives left.
     */
    @FXML
    private void updatePlayerDetails() {
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        gameDifficultyLabel.setText("Difficulty: " + gameEnvironment.getGameDifficulty());
        roundCounterLabel.setText("Round: " + gameEnvironment.getCurrentRound() +
                " / " + gameEnvironment.getGameRounds());
        currentBalanceLabel.setText("Current Balance: $" + gameEnvironment.getCurrentBalance());
        livesLeftLabel.setText("Lives Left: " + gameEnvironment.getLivesLeft());
    }

    /**
     * Initializes the main ToggleGroup for selecting one button at a time in the shop.
     */
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

    /**
     * Updates the ToggleButtons based on the player's items and towers.
     */
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

    /**
     * Handles clicking the buy button by allowing the player to buy or not a tower or item in the shop.
     */
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

                    // Checks if the player can afford the tower and if they have space for it.
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

                // Checks if the player can afford the selected item and if they have space for it.
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

    /**
     * Handles clicking the sell button by allowing the player to sell or not a tower or item they have.
     */
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

    /**
     * Handles clicking the return button by closing the shop screen and returning to the game screen.
     */
    @FXML
    private void onReturnButtonClicked() {
        gameEnvironment.closeShopScreen();
    }
}
