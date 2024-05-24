package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import seng201.team0.GameEnvironment;
import javafx.scene.control.Label;

/**
 * Controller class for the end game screen. Shows the game result and statistics.
 */
public class EndGameScreenController {
    /**
     * Label that shows the games result
     */
    @FXML
    private Label gameResultLabel;
    /**
     * VBox that displays end game statistics.
     */
    @FXML
    private VBox statsVBox;
    /**
     * game environment which manages the game's state.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Constructs an EndGameScreenController with the specified game environment.
     *
     * @param tempEnvironment the game environment containing the game's state.
     */
    public EndGameScreenController(final GameEnvironment tempEnvironment) {
        this.gameEnvironment = tempEnvironment;
    }

    /**
     * Initializes the end game screen by setting label texts and colours.
     */
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

    /**
     * Sets up the statistics of the end game screen with the player's name, rounds, final balance, and difficulty.
     */
    @FXML
    public void setStatsVBox() {

        Label label1 = new Label("Player Name: " + gameEnvironment.getPlayerName());
        Label label2 = new Label("Completed rounds: " + gameEnvironment.getCurrentRound() + " out of "
                + gameEnvironment.getGameRounds());
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
