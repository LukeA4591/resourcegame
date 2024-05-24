package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import javafx.scene.control.*;

public class EndGameScreenController {

    @FXML
    private Label gameResultLabel;
    @FXML
    private VBox statsVBox;

    private final GameEnvironment gameEnvironment;

    public EndGameScreenController(GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    @FXML
    public void initialize() {

        if (gameEnvironment.getGameWon()) {
            gameResultLabel.setText("VICTORY!");
            gameResultLabel.setStyle("-fx-text-fill: gold");
        }
        else {
            gameResultLabel.setText("DEFEAT");
            gameResultLabel.setStyle("-fx-text-fill: red");
        }

        setStatsVBox();

    }


    @FXML
    public void setStatsVBox() {

        Label label1 = new Label("Player Name: " + gameEnvironment.getPlayerName());
        Label label2 = new Label("Completed rounds: " + gameEnvironment.getCurrentRound() + " out of " + gameEnvironment.getGameRounds());
        Label label3 = new Label("Final Balance: " + gameEnvironment.getCurrentBalance());
        Label label4 = new Label("Difficulty: " + gameEnvironment.getGameDifficulty());

        label1.setStyle("-fx-font-size: 20");
        label2.setStyle("-fx-font-size: 20");
        label3.setStyle("-fx-font-size: 20");
        label4.setStyle("-fx-font-size: 20");

        statsVBox.getChildren().addAll(label1, label2, label3, label4);

        statsVBox.setSpacing(5);
        statsVBox.setAlignment(Pos.CENTER);



    }
}
