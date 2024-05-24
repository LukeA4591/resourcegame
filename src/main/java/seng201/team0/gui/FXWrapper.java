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

public class FXWrapper implements AlertHandler {
    @FXML
    private Pane pane;
    private Stage stage;

    public void init(final Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::launchTowerSelectScreen, this::launchGameScreen,
                this::launchShopScreen, this::launchInventoryScreen, this::launchEndGameScreen, this::clearPane,
                this, new Random());
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

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