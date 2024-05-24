package seng201.team0.gui;

import javafx.scene.control.Alert;
/**
 * Interface to handle alerts outside GameEnvironment to improve testability.
 */
public interface AlertHandler {
    /**
     * Shows an alert with the specified title, message, and alert type.
     *
     * @param title the title of the alert.
     * @param message the content message of the alert.
     * @param alertType the type of the alert.
     */
    void showAlert(String title, String message, Alert.AlertType alertType);
}
