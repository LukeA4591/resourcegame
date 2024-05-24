package seng201.team0.gui;

import javafx.scene.control.Alert;

public interface AlertHandler {

    void showAlert(String title, String message, Alert.AlertType alertType);
}
