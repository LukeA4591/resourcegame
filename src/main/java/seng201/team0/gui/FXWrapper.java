package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import java.io.IOException;
import java.util.Random;

/**
 * FXWrapper class manages the screens in the application and handles alerts.
 * It also initializes the game environment which is responsible for keeping track of the game's state.
 * Code for launching screens is reused and repurposed from Tutorial 2.
 */
public class FXWrapper implements AlertHandler {
    /**
     * Pane for the application to be run on.
     */
    @FXML
    private Pane pane;
    /**
     * Primary stage for the application.
     */
    private Stage stage;

    /**
     * Initializes the FXWrapper with the primary stage of the application.
     *
     * @param stage the primary stage of the application.
     */
    public void init(final Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::launchTowerSelectScreen, this::launchGameScreen,
                this::launchShopScreen, this::launchInventoryScreen, this::launchEndGameScreen, this::clearPane,
                this, new Random());
    }

    /**
     * Clears the plane between screens.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    /**
     * Launches the setup screen.
     *
     * @param gameEnvironment the game environment which manages the game's state.
     */
    public void launchSetupScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupLoader.setControllerFactory(param -> new SetupScreenController(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Setup Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the tower select screen.
     *
     * @param gameEnvironment the game environment which manages the game's state.
     */
    public void launchTowerSelectScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/tower_select_screen.fxml"));
            setupLoader.setControllerFactory(param -> new TowerSelectScreenController(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Select Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the game screen.
     *
     * @param gameEnvironment the game environment which manages the game's state.
     */
    public void launchGameScreen(final GameEnvironment gameEnvironment) {

        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/game_screen.fxml"));
            setupLoader.setControllerFactory(param -> new GameScreenController(gameEnvironment));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the shop screen.
     *
     * @param gameEnvironment the game environment which manages the game's state.
     */
    public void launchShopScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/shop_screen.fxml"));
            setupLoader.setControllerFactory(param -> new ShopScreenController(gameEnvironment));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the inventory screen.
     *
     * @param gameEnvironment the game environment which manages the game's state.
     */
    public void launchInventoryScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/inventory_screen.fxml"));
            setupLoader.setControllerFactory(param -> new InventoryScreenController(gameEnvironment));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the end game screen.
     *
     * @param gameEnvironment the game environment which manages the game's state.
     */
    public void launchEndGameScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/end_game_screen.fxml"));
            setupLoader.setControllerFactory(param -> new EndGameScreenController(gameEnvironment));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("End Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows an alert with a title, message, and alert type.
     *
     * @param title the title of the alert.
     * @param message the content message of the alert.
     * @param alertType the type of the alert.
     */
    @Override
    public void showAlert(final String title, final String message, final Alert.AlertType alertType) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

}