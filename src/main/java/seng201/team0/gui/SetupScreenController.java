package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.models.GameEnvironment;

import javafx.scene.control.*;

public class SetupScreenController {

    @FXML
    private TextField playerName;
    @FXML
    private Slider roundSlider;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    private GameEnvironment gameEnvironment;
    public SetupScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    public void initialize() {

    }


}
