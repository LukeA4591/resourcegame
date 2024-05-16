package seng201.team0.models;

import java.util.function.Consumer;

public class GameEnvironment {
    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Runnable clearScreen;
    private String playerName;
    private int gameRounds;
    private String gameDifficulty;

    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Runnable clearScreen) {
        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        launchSetupScreen();
    }


    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }


    public void startGame(String name, int rounds, String difficulty) {
        this.playerName = name;
        this.gameRounds = rounds;
        this.gameDifficulty = difficulty;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getGameRounds() {
        return gameRounds;
    }
    public String getGameDifficulty() {
        return gameDifficulty;
    }


}
