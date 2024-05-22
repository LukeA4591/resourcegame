package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import javafx.scene.control.*;

public class EndGameScreenController {

    @FXML
    private Label gameResultLabel;
    @FXML
    private VBox statsVBox;

    private GameEnvironment gameEnvironment;

    public EndGameScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {

        if (gameEnvironment.getGameWon()) {
            gameResultLabel.setText("VICTORY!");
        }
        else {
            gameResultLabel.setText("DEFEAT");
        }

        setStatsVBox();

    }


    @FXML
    public void setStatsVBox() {

        statsVBox.getChildren().addAll(
                new Label("Player Name: " + gameEnvironment.getPlayerName()),
                new Label("Completed rounds: " + gameEnvironment.getCurrentRound() + " out of " + gameEnvironment.getGameRounds()),
                new Label("Final Balance: " + gameEnvironment.getCurrentBalance())
        );

        statsVBox.setSpacing(4);



    }
}
