package seng201.team0.models;

import java.util.function.Consumer;

public class GameEnvironment {


    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> towerSelectScreenLauncher;
    private final Runnable clearScreen;


    private String playerName;
    private int gameRounds;
    private String gameDifficulty;

    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> towerSelectScreenLauncher
            , Runnable clearScreen) {
        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSelectScreenLauncher = towerSelectScreenLauncher;
        launchSetupScreen();
    }




    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    public void closeSetupScreen() {
        clearScreen.run();
        launchTowerSelectScreen();
    }

    public void launchTowerSelectScreen() {towerSelectScreenLauncher.accept(this);}




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
