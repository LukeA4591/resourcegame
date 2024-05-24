package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.items.Item;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.towers.SupportTower;

import java.util.List;
/**
 * Controller class for the inventory. It manages the player's towers and items.
 * It allows the player view their inventory and to swap towers between main and reserve groups
 * ,and repair and remove broken towers.
 */
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
    private Label towerLevelLabel;

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
    private Button repairTowerButton;
    @FXML
    private Button removeTowerButton;
    @FXML
    private Button returnButton;

    @FXML
    private VBox descriptionVBox;

    private final ToggleGroup allToggleButtonsGroup = new ToggleGroup();
    private final ToggleGroup mainTowersToggleGroup = new ToggleGroup();
    private final ToggleGroup reserveTowersToggleGroup = new ToggleGroup();

    private List<ToggleButton> reserveTowerToggleButtons;
    private List<ToggleButton> mainTowerToggleButtons;
    private List<ToggleButton> itemToggleButtons;

    private boolean swappingTowers = false;

    private final GameEnvironment gameEnvironment;

    /**
     * Constructs the InventoryScreenController with the specified game environment.
     *
     * @param tempEnvironment the game environment that manages the state of the game.
     */
    public InventoryScreenController(final GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;

    }

    /**
     * Initializes the InventoryScreenController by initializing toggle buttons, updating player details,
     * updating buttons, and initializing the descriptions of towers and items.
     */
    @FXML
    public void initialize() {

        initializeToggleButtons();
        updatePlayerDetails();
        updateButtons();
        initializeDescriptions();

    }
    /**
     * Initializes the descriptions for towers and items upon their respective toggle buttons being selected.
     */
    @FXML
    private void initializeDescriptions() {

        ammunitionTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getMainTowerByName(
                ammunitionTowerButton.getText())));
        troopsTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getMainTowerByName(
                troopsTowerButton.getText())));
        medkitsTowerButton.setOnAction(event -> displayTowerInformation(gameEnvironment.getMainTowerByName(
                medkitsTowerButton.getText())));

        supportTowerButton.setOnAction(event -> displaySupportTowerInformation(gameEnvironment.getSupportTower()));

        reserveTower1Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(
                reserveTower1Button.getText())));
        reserveTower2Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(
                reserveTower2Button.getText())));
        reserveTower3Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(
                reserveTower3Button.getText())));
        reserveTower4Button.setOnAction(event -> displayTowerInformation(gameEnvironment.getReserveTowerByName(
                reserveTower4Button.getText())));

        item1Button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(
                item1Button.getText())));
        item2Button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(
                item2Button.getText())));
        item3Button.setOnAction(event -> displayItemInformation(gameEnvironment.getPlayerItemByName(
                item3Button.getText())));

    }
    /**
     * Displays information of the selected support tower.
     * Factored as such to allow reuse of the method in ShopScreenController.
     *
     * @param tower the selected tower which description is to be displayed.
     */
    @FXML
    private void displaySupportTowerInformation(final SupportTower tower) {

        towerLevelLabel.setText("");

        displaySupportTowerInformation(tower, descriptionVBox);

    }
    /**
     * Displays the selected support towers information in the description VBox.
     *
     * @param tower the selected support tower.
     * @param descriptionVBox the VBox to display the support tower information in.
     */
    @FXML
    static void displaySupportTowerInformation(final SupportTower tower, final VBox descriptionVBox) {

        if (tower != null) {
            descriptionVBox.getChildren().clear();

            descriptionVBox.getChildren().addAll(
                    new Label("Name: " + tower.getName()),
                    new Label("Support resource type: " + tower.getResourceType()),
                    new Label("Reload speed boost: " + tower.getReloadSpeedBoost() + " seconds"),
                    new Label("Cost: " + tower.getCost()),
                    new Label("Sell Price: " + tower.getSellPrice()),
                    new Label("Description: " + tower.getDescription())
            );
        }
    }

    /**
     * Displays the information of the selected tower.
     * Factored as such to allow for reuse of the code in ShopScreenController.
     *
     * @param tower the selected tower to have its description displayed.
     */
    @FXML
    private void displayTowerInformation(final Tower tower) {

        if (tower instanceof SupportTower) {
            displaySupportTowerInformation((SupportTower) tower);
        }
        else {

            towerLevelLabel.setText(tower.getLevelRepresentation());

            displayTowerInformation(tower, descriptionVBox);
        }
    }

    /**
     * Displays the selected tower's description in the description VBox.
     *
     * @param tower the selected tower to have its information displayed.
     * @param descriptionVBox the VBox to display the tower information in.
     */
    @FXML
    static void displayTowerInformation(final Tower tower, final VBox descriptionVBox) {
        descriptionVBox.getChildren().clear();

        descriptionVBox.getChildren().addAll(
                new Label("Name: " + tower.getName()),
                new Label("Resource type: " + tower.getResourceType()),
                new Label("Resources per click: " + tower.getResourceAmount()),
                new Label(String.format("Reload speed: %.2f seconds", tower.getReloadSpeed())),
                new Label("Cost: " + tower.getCost()),
                new Label("Sell price: " + tower.getSellPrice()),
                new Label("Description: " + tower.getDescription())
        );
    }

    /**
     * Displays the selected item's information
     * Factored as such to allow for reuse of the code in ShopScreenController.
     *
     * @param item the selected item to have its information displayed.
     */
    @FXML
    private void displayItemInformation(final Item item) {
        descriptionVBox.getChildren().clear();

        towerLevelLabel.setText("");

        displayItemInformation(item, descriptionVBox);
    }

    /**
     * Displays the selected item's information in the description VBox.
     *
     * @param item the item to have its information displayed.
     * @param descriptionVBox the VBox which shows the item's information.
     */
    @FXML
    static void displayItemInformation(final Item item, final VBox descriptionVBox) {
        if (!item.getIsRepairKit()) {
            descriptionVBox.getChildren().addAll(
                    new Label("Name: " + item.getName()),
                    new Label("Affects: " + item.getTowerType() + " Towers"),
                    new Label("Resource boost: 20"),
                    new Label("Cost: " + item.getCost()),
                    new Label("Sell price: " + item.getSellPrice()),
                    new Label("Description: " + item.getDescription())
            );
        }
        else {
            descriptionVBox.getChildren().addAll(
                    new Label("Name: " + item.getName()),
                    new Label("Repairs: " + item.getTowerType() + " Towers"),
                    new Label("Cost: " + item.getCost()),
                    new Label("Sell price: " + item.getSellPrice()),
                    new Label("Description: " + item.getDescription())
            );
        }
    }

    /**
     * Initializes the separate ToggleButton lists reserveTowerToggleButtons, mainTowerToggleButtons
     * , itemToggleButtons, as well as the allToggleButtonsGroup.
     */
    @FXML
    private void initializeToggleButtons() {

        List<ToggleButton> allToggleButtons = List.of(ammunitionTowerButton, troopsTowerButton, medkitsTowerButton,
                supportTowerButton, reserveTower1Button, reserveTower2Button,
                reserveTower3Button, reserveTower4Button, item1Button, item2Button,
                item3Button);

        for (ToggleButton toggleButton : allToggleButtons) {
            toggleButton.setToggleGroup(allToggleButtonsGroup);
        }

        reserveTowerToggleButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button,
                reserveTower4Button);
        mainTowerToggleButtons = List.of(ammunitionTowerButton,troopsTowerButton, medkitsTowerButton,
                supportTowerButton);
        itemToggleButtons = List.of(item1Button, item2Button, item3Button);
    }

    /**
     * Updates the player's details in the inventory such as their name, game difficulty, round count, current balance
     * , and lives left.
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
     * Handles clicking the return button by closing the inventory screen.
     */
    @FXML
    private void onReturnButtonClicked() {
        gameEnvironment.closeInventoryScreen();
    }

    /**
     * Handles clicking the swap towers button by allowing them to swap towers between the main
     * and reserve groups.
     */
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

    /**
     * Enables swapping towers mode by setting toggle groups and disabling other buttons.
     */
    @FXML
    private void enableSwapping() {
        swappingTowers = true;

        ammunitionTowerButton.setToggleGroup(mainTowersToggleGroup);
        troopsTowerButton.setToggleGroup(mainTowersToggleGroup);
        medkitsTowerButton.setToggleGroup(mainTowersToggleGroup);

        List<ToggleButton> reserveButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button,
                reserveTower4Button);

        for (ToggleButton button : reserveButtons) {
            button.setToggleGroup(reserveTowersToggleGroup);
        }

        returnButton.setDisable(true);
        infoLabel.setText("Tower Swap In Progress");

        repairTowerButton.setDisable(true);
        removeTowerButton.setDisable(true);
    }

    /**
     * Disables swapping towers mode by setting a toggle group and enabling other buttons.
     */
    @FXML
    private void disableSwapping() {
        swappingTowers = false;

        List<ToggleButton> allToggleButtons = List.of(ammunitionTowerButton, troopsTowerButton, medkitsTowerButton,
                reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button, item1Button,
                item2Button, item3Button);

        for (ToggleButton toggleButton : allToggleButtons) {
            toggleButton.setToggleGroup(allToggleButtonsGroup);
        }

        returnButton.setDisable(false);
        infoLabel.setText("");

        repairTowerButton.setDisable(false);
        removeTowerButton.setDisable(false);
    }

    /**
     * Swaps the selected towers by calling gameEnvironment and updating the UI.
     */
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

            if (mainTower != null && reserveTower != null) {
                gameEnvironment.swapTowers(mainTower, reserveTower);
                updateButtons();
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Tower Selection", "You must select two towers to" +
                    " swap them.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Updates the states of the buttons depending on the players towers and items.
     */
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



        for (int i = 0; i < reserveTowerToggleButtons.size(); i++) {

            if (i < gameEnvironment.getReserveTowers().size()) {
                reserveTowerToggleButtons.get(i).setText(gameEnvironment.getReserveTowers().get(i).getName());
                reserveTowerToggleButtons.get(i).setDisable(false);

            }
            else {
                reserveTowerToggleButtons.get(i).setText("Locked");
                reserveTowerToggleButtons.get(i).setDisable(true);
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

    /**
     * Handles clicking the repair tower button by allowing a tower to be repaired and calling to gameEnvironment
     * to repair the selected tower.
     * Shows alerts if needed to inform player if the action can or cannot be completed.
     */
    @FXML
    private void onRepairTowerButtonClicked() {

        ToggleButton selectedButton = (ToggleButton) allToggleButtonsGroup.getSelectedToggle();

        Tower selectedTower;

        if (selectedButton != null) {

            selectedTower = getSelectedTower(selectedButton);

            if (selectedTower != null) {

                if (selectedTower.isBroken()) {

                    Item repairKit = null;

                    for (ToggleButton button : itemToggleButtons) {

                        if (gameEnvironment.getPlayerItems().contains(gameEnvironment.getPlayerItemByName(
                                button.getText())) && gameEnvironment.getPlayerItemByName(button.getText()).
                                getTowerType().equals(selectedTower.getResourceType())) {

                            repairKit = gameEnvironment.getPlayerItemByName(button.getText());

                            gameEnvironment.useRepairKit(repairKit, selectedTower);

                            updateButtons();

                        }
                    }

                    if (repairKit == null) {
                        gameEnvironment.showAlert("No Valid Repair Kit", "No repair kits are " +
                                "available to repair this tower", Alert.AlertType.ERROR);
                    }

                } else {
                    gameEnvironment.showAlert("Tower Not Broken", "There is no need to use a repair " +
                            "kit on this tower because it is not broken.", Alert.AlertType.ERROR);
                }
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Tower Selection", "There is no tower selected " +
                    "for repairing", Alert.AlertType.ERROR);
        }
    }

    /**
     * Returns the selected tower based off of the selected button.
     *
     * @param selectedButton the button the player has selected.
     * @return the selectedTower or null if no tower is selected.
     */
    @FXML
    private Tower getSelectedTower(ToggleButton selectedButton) {

        Tower selectedTower = null;

        for (ToggleButton button : reserveTowerToggleButtons) {
            if (selectedButton.equals(button)) {
                selectedTower = gameEnvironment.getReserveTowerByName(selectedButton.getText());
            }
        }
        for (ToggleButton button : mainTowerToggleButtons) {
            if (selectedButton.equals(button)) {
                selectedTower = gameEnvironment.getMainTowerByName(selectedButton.getText());
            }
        }
        return selectedTower;
    }

    /**
     * Handles clicking the remove tower button by allowing a broken tower to be removed from the player's inventory.
     * Calls to gameEnvironment to remove the tower and shows alerts if needed to inform the player if it
     * is not necessary for the tower to be removed.
     */
    @FXML
    private void onRemoveTowerButtonClicked() {

        ToggleButton selectedButton = (ToggleButton) allToggleButtonsGroup.getSelectedToggle();

        Tower selectedTower;

        if (selectedButton != null) {

            selectedTower = getSelectedTower(selectedButton);

            if (selectedTower != null) {
                if (selectedTower.isBroken()) {

                    if (!gameEnvironment.getMainTowers().contains(selectedTower)) {
                        gameEnvironment.removeTower(selectedTower);
                    } else {
                        gameEnvironment.showAlert("Main Towers Cannot Be Removed", "Please swap the " +
                                "tower to reserves in order to remove it", Alert.AlertType.ERROR);
                    }
                } else {
                    gameEnvironment.showAlert("Tower Is Not Broken", "The selected tower is not broken" +
                            " and does not need to be removed", Alert.AlertType.ERROR);
                }
            }
        }
        else {
            gameEnvironment.showAlert("Invalid Tower Selection", "There is no tower selected " +
                    "for removal", Alert.AlertType.ERROR);
            }
    }
}

