package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import seng201.team0.models.towers.Armoury;
import seng201.team0.models.towers.Tower;

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
    private ToggleButton ammunitionTowerButton;
    @FXML
    private ToggleButton troopsTowerButton;
    @FXML
    private ToggleButton medkitsTowerButton;

    @FXML
    private ToggleButton reserveTower1Button;
    @FXML
    private ToggleButton reserveTower2Button;
    @FXML
    private ToggleButton reserveTower3Button;
    @FXML
    private ToggleButton reserveTower4Button;
    @FXML
    private ToggleButton reserveTower5Button;
    @FXML
    private ToggleButton reserveTower6Button;
    @FXML
    private ToggleButton item1Button;
    @FXML
    private ToggleButton item2Button;
    @FXML
    private ToggleButton item3Button;

    @FXML
    private Button swapTowersButton;
    @FXML
    private Button sellButton;
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
    }


    @FXML
    private void initializeToggleButtons() {

        List<ToggleButton> allToggleButtons = List.of(ammunitionTowerButton, troopsTowerButton, medkitsTowerButton, reserveTower1Button, reserveTower2Button,
                reserveTower3Button, reserveTower4Button, reserveTower5Button, reserveTower6Button, item1Button, item2Button,
                item3Button);

        for (ToggleButton toggleButton : allToggleButtons) {
            toggleButton.setToggleGroup(allToggleButtonsGroup);
        }

        reserveToggleButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button,
                reserveTower5Button, reserveTower6Button);


        item1Button.setText("Locked");
        item1Button.setDisable(true);
        item2Button.setText("Locked");
        item2Button.setDisable(true);
        item3Button.setText("Locked");
        item3Button.setDisable(true);

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

        List<ToggleButton> reserveButtons= List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button,
                reserveTower5Button, reserveTower6Button);

        for (ToggleButton button : reserveButtons) {
            button.setToggleGroup(reserveTowersToggleGroup);
        }
    }

    @FXML
    private void disableSwapping() {
        swappingTowers = false;

        List<ToggleButton> allToggleButtons = List.of(ammunitionTowerButton, troopsTowerButton, medkitsTowerButton, reserveTower1Button, reserveTower2Button,
                reserveTower3Button, reserveTower4Button, reserveTower5Button, reserveTower6Button, item1Button, item2Button,
                item3Button);

        for (ToggleButton toggleButton : allToggleButtons) {
            toggleButton.setToggleGroup(allToggleButtonsGroup);
        }
    }

    @FXML
    private void swapSelectedTowers() {

        ToggleButton selectedMainTowerButton =  (ToggleButton) mainTowersToggleGroup.getSelectedToggle();
        ToggleButton selectedReserveTowerButton = (ToggleButton) reserveTowersToggleGroup.getSelectedToggle();

        Tower mainTower = null;
        Tower reserveTower = null;

        if (selectedMainTowerButton != null && selectedReserveTowerButton != null) {

            for (int i = 0; i < gameEnvironment.getMainTowers().size(); i++) {
                if (gameEnvironment.getMainTowers().get(i).getName().equals(selectedMainTowerButton.getText())) {
                    mainTower = gameEnvironment.getMainTowers().get(i);
                }
            }
            for (int i = 0; i < gameEnvironment.getMainTowers().size(); i++) {
                if (gameEnvironment.getMainTowers().get(i).getName().equals(selectedReserveTowerButton.getText())) {
                    reserveTower = gameEnvironment.getReserveTowers().get(i);
                }
            }

            assert mainTower != null && reserveTower!= null;
            gameEnvironment.swapTowers(mainTower, reserveTower);

            updateButtons();

        }

    }

    @FXML
    private void updateButtons() {

        ammunitionTowerButton.setText(gameEnvironment.getMainTowers().get(0).getName());
        troopsTowerButton.setText(gameEnvironment.getMainTowers().get(1).getName());
        medkitsTowerButton.setText(gameEnvironment.getMainTowers().get(2).getName());

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
    }
}
