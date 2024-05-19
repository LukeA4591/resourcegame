package seng201.team0;

import seng201.team0.models.towers.Tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    private List<Tower> mainTowers;
    private List<Tower> unselectedTowers;
    private List<Tower> reserveTowers;

    private Map<Tower, Integer> towerLevels;



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

        this.mainTowers = new ArrayList<>();
        this.unselectedTowers = new ArrayList<>();
        this.towerLevels = new HashMap<>();
        this.reserveTowers = new ArrayList<>();

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
    public void closeGameScreen(Boolean isInventory) {
        clearScreen.run();
        if (isInventory) {
            launchInventoryScreen();
        }
        else launchShopScreen();
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
        livesLeft --;
    }
    public boolean isGameOver() {
        return livesLeft <= 0;
    }

    public int getTowerLevel(Tower tower) {
        return towerLevels.get(tower);
    }
    public void levelUpTower(Tower tower) {
        towerLevels.put(tower, tower.getLevel() + 1);
    }



    public void addTower(Tower tower, boolean isSelected) {
        if (isSelected) {
            mainTowers.add(tower);
            towerLevels.put(tower, 1);
        }
        else {
            unselectedTowers.add(tower);
            towerLevels.put(tower, 1);
        }


    }

    public List<Tower> getMainTowers() {
        return mainTowers;
    }
    public List<Tower> getUnselectedTowers() {
        return unselectedTowers;
    }

    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }

    public void swapTowers(Tower mainTower, Tower reserveTower) {

        if (mainTower.getResourceType().equals(reserveTower.getResourceType())) {

            int mainIndex = mainTowers.indexOf(mainTower);
            int reserveIndex = reserveTowers.indexOf(reserveTower);

            mainTowers.set(mainIndex, reserveTower);
            reserveTowers.set(reserveIndex, mainTower);
        }

    }


}
