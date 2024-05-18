package seng201.team0;

import seng201.team0.models.towers.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameEnvironment {


    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> towerSelectScreenLauncher;
    private final Consumer<GameEnvironment> gameScreenLauncher;
    private final Consumer<GameEnvironment> shopScreenLauncher;
    private final Consumer<GameEnvironment> inventoryScreenLauncher;
    private final Runnable clearScreen;


    private String playerName;
    private int gameRounds;
    private String gameDifficulty;
    private double currentBalance;
    private int livesLeft;


    private List<Tower> selectedTowers;
    private List<Tower> unselectedTowers;



    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> towerSelectScreenLauncher,
                           Consumer<GameEnvironment> gameScreenLauncher, Consumer<GameEnvironment> shopScreenLauncher,
                           Consumer<GameEnvironment> inventoryScreenLauncher, Runnable clearScreen) {

        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSelectScreenLauncher = towerSelectScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        launchSetupScreen();

        this.selectedTowers = new ArrayList<>();
        this.unselectedTowers = new ArrayList<>();
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
    public void closeGameScreen() {
        clearScreen.run();
        launchShopScreen();
    }

    public void launchShopScreen() {
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }
    public void closeShopScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    public void launchInventoryScreen() {
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }
    public void closeInventoryScreen() {
        clearScreen.run();
        launchGameScreen();
    }






    public void startGame(String name, int rounds, String difficulty) {

        this.playerName = name;
        this.gameRounds = rounds;
        this.gameDifficulty = difficulty;

        switch (difficulty) {
            case "Recruit":
                setCurrentBalance(1500.00);
                livesLeft = 3;
                break;
            case "Major":
                setCurrentBalance(1000.00);
                livesLeft = 2;
                break;
            case "Commander":
                setCurrentBalance(500.00);
                livesLeft = 1;
                break;
        }
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


    public void setCurrentBalance(Double balance) {
        this.currentBalance = balance;
    }
    public double getCurrentBalance() {
        return currentBalance;
    }


    public int getLivesLeft() {
        return livesLeft;
    }
    public void loseLife() {
        livesLeft -= 1;
    }
    public boolean isGameOver() {
        return livesLeft <= 0;
    }



    public void addTower(Tower tower, boolean isSelected) {
        if (isSelected) {
            selectedTowers.add(tower);
        }
        else unselectedTowers.add(tower);
    }

}
