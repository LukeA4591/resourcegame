package seng201.team0;

import javafx.scene.control.Alert;
import seng201.team0.models.RandomEvent;
import seng201.team0.models.items.AmmoCrate;
import seng201.team0.models.items.Item;
import seng201.team0.models.items.MedicalSupplyDrop;
import seng201.team0.models.items.Paratroopers;
import seng201.team0.models.towers.*;

import java.util.*;
import java.util.function.Consumer;

public class GameEnvironment {


    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> towerSelectScreenLauncher;
    private final Consumer<GameEnvironment> gameScreenLauncher;
    private final Consumer<GameEnvironment> shopScreenLauncher;
    private final Consumer<GameEnvironment> inventoryScreenLauncher;
    private final Consumer<GameEnvironment> endGameScreenLauncher;
    private final Runnable clearScreen;


    private String playerName;
    private int gameRounds;
    private int currentRound;
    private String gameDifficulty;
    private double currentBalance;
    private int livesLeft;


    private List<Tower> mainTowers;
    private List<Tower> towersInShop;
    private List<Tower> reserveTowers;

    private Tower supportTower = null;

    private List<Item> itemsInShop;
    private List<Item> playerItems;

    private final Random random = new Random();

    private boolean gameWon;



    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> towerSelectScreenLauncher,
                           Consumer<GameEnvironment> gameScreenLauncher, Consumer<GameEnvironment> shopScreenLauncher,
                           Consumer<GameEnvironment> inventoryScreenLauncher, Consumer<GameEnvironment> endGameScreenLauncher,
                           Runnable clearScreen) {

        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSelectScreenLauncher = towerSelectScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.endGameScreenLauncher = endGameScreenLauncher;
        launchSetupScreen();

        this.mainTowers = new ArrayList<>();
        this.reserveTowers = new ArrayList<>();

        this.towersInShop = new ArrayList<>();

        this.playerItems = new ArrayList<>();
        this.itemsInShop = new ArrayList<>();

        initializeItems();

    }

    public void initializeItems() {
        itemsInShop.add(new AmmoCrate(350, 100));
        itemsInShop.add(new Paratroopers(500, 100));
        itemsInShop.add(new MedicalSupplyDrop(650, 100));
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
    public void closeGameScreen(boolean isInventory) {
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

    public void launchEndGameScreen() {
        clearScreen.run();
        endGameScreenLauncher.accept(this);
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

    public int getCurrentRound(){
        return currentRound;
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
    public boolean isGameLost() {
        return livesLeft <= 0;
    }

    public void setSupportTower(Tower tower) {
        supportTower = tower;
    }
    public Tower getSupportTower() {
        return supportTower;
    }


    public boolean getGameWon() {
        return gameWon;
    }
    public void setGameWon(boolean gameWon) {
        this.gameWon = true;
    }

    public void setCurrentRound(int roundNumber) {
        this.currentRound = roundNumber;
    }



    public void addTowerFromSelection(Tower tower, boolean isSelected) {
        if (isSelected) {
            mainTowers.add(tower);
        }
        else {
            towersInShop.add(tower);
        }
    }

    public List<Tower> getMainTowers() {
        return mainTowers;
    }
    public List<Tower> getTowersInShop() {
        return towersInShop;
    }
    public List<Item> getItemsInShop() {
        return itemsInShop;
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
        for (Tower tower : towersInShop) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    public Item getItemInShopByName(String name) {
        for (Item item : itemsInShop) {
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
        itemsInShop.remove(item);
    }
    public void sellItem(Item item) {
        setCurrentBalance(currentBalance + item.getSellPrice());
        playerItems.remove(item);
        itemsInShop.add(item);
    }

    public void buyTower(Tower tower) {
        setCurrentBalance(currentBalance - tower.getCost());
        reserveTowers.add(tower);
        towersInShop.remove(tower);
    }
    public void sellTower(Tower tower) {
        setCurrentBalance(currentBalance + tower.getSellPrice());
        tower.setLevel(1);
        reserveTowers.remove(tower);


        towersInShop.add(createNewInstance(tower));
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



    public boolean shouldTriggerRandomEvent() {

        switch (gameDifficulty) {
            case "Recruit":
                return random.nextInt(100) < 15;

            case "Major":
                return random.nextInt(100) < 20;

            case "Commander":
                return random.nextInt(100) < 25;

        }
        return false;
    }

    public void initiateRandomEvent() {
        RandomEvent event = getRandomEvent();

        switch (event) {
            case ENEMY_AIR_STRIKE -> enemyAirStrike();
            case ENEMY_AMBUSH -> enemyAmbush();
            case COMMUNICATIONS_BREAKDOWN -> communicationsBreakdown();
            case MEDICAL_SUPPLY_LINE_SABOTAGE -> medicalSupplyLineSabotage();
        }
    }

    public RandomEvent getRandomEvent() {
        RandomEvent[] randomEvents = RandomEvent.values();
        return randomEvents[random.nextInt(randomEvents.length)];
    }

    public void enemyAirStrike() {

        Tower selectedTower;

        if (50 > random.nextInt(65)) {
            selectedTower = mainTowers.get(random.nextInt(mainTowers.size()));
        } else {

            if (reserveTowers.isEmpty()) {
                selectedTower = mainTowers.get(random.nextInt(mainTowers.size()));
            }

            selectedTower = reserveTowers.get(random.nextInt(reserveTowers.size()));
        }
        showAlert("ENEMY AIRSTRIKE", "An enemy airstrike has broken your " + selectedTower.getName() + " tower.", Alert.AlertType.INFORMATION);

        selectedTower.breakTower();

    }

    public void communicationsBreakdown() {

        List<Tower> allTowers = mainTowers;
        allTowers.addAll(reserveTowers);

        List<Tower> possibleTowers = new ArrayList<>();

        for (Tower tower : allTowers) {
            if (tower.getResourceType().equals("Troops")) {
                possibleTowers.add(tower);
            }
        }

        Tower selectedTower = possibleTowers.get(random.nextInt(possibleTowers.size()));

        showAlert("COMMUNICATIONS BREAKDOWN", "Our communication lines have been shut down!: " + selectedTower.getName() + " reload speed has been decreased.", Alert.AlertType.INFORMATION);

        selectedTower.communicationsBreakdown();

    }

    public void medicalSupplyLineSabotage() {

        List<Tower> allTowers = mainTowers;
        allTowers.addAll(reserveTowers);

        List<Tower> possibleTowers = new ArrayList<>();

        for (Tower tower : allTowers) {
            if (tower.getResourceType().equals("Medkits")) {
                possibleTowers.add(tower);
            }
        }

        Tower selectedTower = possibleTowers.get(random.nextInt(possibleTowers.size()));

        showAlert("MEDICAL SUPPLY LINE SABOTAGE", "Our medical supply lines have been sabotaged!: " + selectedTower.getName() + " reload speed has been decreased.", Alert.AlertType.INFORMATION);

        selectedTower.medicalSupplyLineSabotage();
    }

    public void enemyAmbush() {

        List<Tower> allTowers = mainTowers;
        allTowers.addAll(reserveTowers);

        List<Tower> possibleTowers = new ArrayList<>();

        for (Tower tower : allTowers) {
            if (tower.getResourceType().equals("Ammunition")) {
                possibleTowers.add(tower);
            }
        }

        Tower selectedTower = possibleTowers.get(random.nextInt(possibleTowers.size()));

        showAlert("ENEMY AMBUSH", "Our ammunition suppliers have been ambushed!: " + selectedTower.getName() + " reload speed has been decreased.", Alert.AlertType.INFORMATION);

        selectedTower.enemyAmbush();
    }


}
