package seng201.team0;

import javafx.scene.control.Alert;
import seng201.team0.models.items.AmmoCrate;
import seng201.team0.models.items.Item;
import seng201.team0.models.items.MedicalSupplyDrop;
import seng201.team0.models.items.Paratroopers;
import seng201.team0.models.towers.*;

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
    private int currentRound;
    private String gameDifficulty;
    private double currentBalance;
    private int livesLeft;


    private List<Tower> mainTowers;
    private List<Tower> availableTowersInShop;
    private List<Tower> allPossibleTowers;
    private List<Tower> reserveTowers;

    private Tower supportTower = null;

    private List<Item> availableItems;
    private List<Item> playerItems;



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
        this.reserveTowers = new ArrayList<>();

        this.availableTowersInShop = new ArrayList<>();
        this.allPossibleTowers = new ArrayList<>();

        this.playerItems = new ArrayList<>();
        this.availableItems = new ArrayList<>();

        initializeItems();

    }

    public void initializeItems() {
        availableItems.add(new AmmoCrate(0, 0));
        availableItems.add(new Paratroopers(0, 0));
        availableItems.add(new MedicalSupplyDrop(0, 0));
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

    public void setSupportTower(Tower tower) {
        supportTower = tower;
    }
    public Tower getSupportTower() {
        return supportTower;
    }



    public void addTowerFromSelection(Tower tower, boolean isSelected) {
        if (isSelected) {
            mainTowers.add(tower);
        }
        else {
            availableTowersInShop.add(tower);
        }
    }

    public List<Tower> getMainTowers() {
        return mainTowers;
    }

    public List<Tower> getAvailableTowersInShop() {
        return availableTowersInShop;
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }
    public List<Item> getPlayerItems() {
        return playerItems;
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
        else {
            showAlert("Invalid Resource Type", "Towers must be of the same resource type to swap.", Alert.AlertType.ERROR);
        }

    }

    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public Tower getMainTowerByName(String name) {
        for (Tower tower : mainTowers) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    public Tower getReserveTowerByName(String name) {
        for (Tower tower : reserveTowers) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    public Tower getTowerInShopByName(String name) {
        for (Tower tower : availableTowersInShop) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    public Item getItemInShopByName(String name) {
        for (Item item : availableItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Item getPlayerItemByName(String name) {
        for (Item item : playerItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }



    public void buyItem(Item item) {
        setCurrentBalance(currentBalance - item.getCost());
        playerItems.add(item);
        availableItems.remove(item);
    }
    public void sellItem(Item item) {
        setCurrentBalance(currentBalance + item.getSellPrice());
        playerItems.remove(item);
        availableItems.add(item);
    }

    public void buyTower(Tower tower) {
        setCurrentBalance(currentBalance - tower.getCost());
        reserveTowers.add(tower);
        availableTowersInShop.remove(tower);
    }
    public void sellTower(Tower tower) {
        setCurrentBalance(currentBalance + tower.getSellPrice());
        tower.setLevel(1);
        reserveTowers.remove(tower);


        availableTowersInShop.add(createNewInstance(tower));
    }

    private Tower createNewInstance(Tower tower) {
        if (tower instanceof Armoury) {
            return new Armoury();
        } else if (tower instanceof Barracks) {
            return new Barracks();
        } else if (tower instanceof Medbay) {
            return new Medbay();
        } else if (tower instanceof Arsenal) {
            return new Arsenal();
        } else if (tower instanceof Garrison) {
            return new Garrison();
        } else if (tower instanceof MedicalTent) {
            return new MedicalTent();
        } else if (tower instanceof EaglesNest) {
            return new EaglesNest();
        } else if (tower instanceof SpecialForcesCamp) {
            return new SpecialForcesCamp();
        } else if (tower instanceof FieldHospital) {
            return new FieldHospital();
        }
            return null;
    }

    public void applyItemEffect(Item item) {

        for (Tower tower : mainTowers) {
            if (item.getTowerType().equals(tower.getResourceType())) {
                tower.setResourceAmount((int) (tower.getResourceAmount() * 1.5));
            }
        }
        for (Tower tower : reserveTowers) {
            if (item.getTowerType().equals(tower.getResourceType())) {
                tower.setResourceAmount((int) (tower.getResourceAmount() * 1.5));
            }
        }
    }

    public void removeItemEffect(Item item) {

        for (Tower tower : mainTowers) {
            if (item.getTowerType().equals(tower.getResourceType())) {
                tower.setResourceAmount((int) (tower.getResourceAmount() / 1.5));
            }
        }
        for (Tower tower : reserveTowers) {
            if (item.getTowerType().equals(tower.getResourceType())) {
                tower.setResourceAmount((int) (tower.getResourceAmount() / 1.5));
            }
        }
    }


}
