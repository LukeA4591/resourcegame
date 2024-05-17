package seng201.team0;

import java.util.function.Consumer;

public class GameEnvironment {


    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> towerSelectScreenLauncher;
    private final Consumer<GameEnvironment> gameScreenLauncher;
    private final Runnable clearScreen;


    private String playerName;
    private int gameRounds;
    private String gameDifficulty;

    private double startingBalance;





    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> towerSelectScreenLauncher
            , Consumer<GameEnvironment> gameScreenLauncher, Runnable clearScreen) {
        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSelectScreenLauncher = towerSelectScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
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
    public void closeTowerSelectScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    public void launchGameScreen() {
        gameScreenLauncher.accept(this);
    }




    public void startGame(String name, int rounds, String difficulty) {
        this.playerName = name;
        this.gameRounds = rounds;
        this.gameDifficulty = difficulty;
        setStartingBalance(difficulty);
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

    public void setStartingBalance(String difficulty) {
        switch (difficulty) {
            case "Private":
                startingBalance = 1500;
                break;
            case "Captain":
                startingBalance = 1000;
                break;
            case "General":
                startingBalance = 500;
                break;
        }
    }

    public double getStartingBalance() {
        return startingBalance;
    }

}
