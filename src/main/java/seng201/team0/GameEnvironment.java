package seng201.team0;

import javafx.scene.control.Alert;
import seng201.team0.gui.AlertHandler;
import seng201.team0.models.RandomEvent;
import seng201.team0.models.items.*;
import seng201.team0.models.towers.*;

import java.util.*;
import java.util.function.Consumer;

public class GameEnvironment {

    private AlertHandler alertHandler;


    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> towerSelectScreenLauncher;
    private final Consumer<GameEnvironment> gameScreenLauncher;
    private final Consumer<GameEnvironment> shopScreenLauncher;
    private final Consumer<GameEnvironment> inventoryScreenLauncher;
    private final Consumer<GameEnvironment> endGameScreenLauncher;
    private final Runnable clearScreen;


    private String playerName;
    private int gameRounds;
    private int currentRound = 1;
    private String gameDifficulty;
    private double currentBalance;
    private int livesLeft;
    private int trackDistance;


    private final List<Tower> mainTowers;
    private final List<Tower> towersInShop;
    private final List<Tower> reserveTowers;

    private SupportTower supportTower = null;

    private final List<Item> shopItems;
    private final List<Item> playerItems;

    private final Random random = new Random();

    private boolean gameWon;



    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> towerSelectScreenLauncher,
                           Consumer<GameEnvironment> gameScreenLauncher, Consumer<GameEnvironment> shopScreenLauncher,
                           Consumer<GameEnvironment> inventoryScreenLauncher, Consumer<GameEnvironment> endGameScreenLauncher,
                           Runnable clearScreen, AlertHandler alertHandler) {

        this.alertHandler = alertHandler;
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
        this.shopItems = new ArrayList<>();

        initializeItems();

    }

    public void initializeItems() {
        shopItems.add(new AmmunitionTowerRepairKit());
        shopItems.add(new MedkitTowerRepairKit());
        shopItems.add(new TroopTowerRepairKit());
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
    public void refreshGameScreen(){
        clearScreen.run();
        launchGameScreen();
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



    public void initializeGame(String name, int rounds, String gameDifficulty, double startingMoney, int trackDistance, int lives) {

        this.playerName = name;
        this.gameRounds = rounds;
        this.gameDifficulty = gameDifficulty;
        setCurrentBalance(startingMoney);
        this.trackDistance = trackDistance;
        this.livesLeft = lives;

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
    public void setCurrentRound(int roundNumber) {
        this.currentRound = roundNumber;
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
    public boolean isGameWon () { return currentRound >= gameRounds; }

    public void setSupportTower(SupportTower tower) {
        supportTower = tower;
    }
    public SupportTower getSupportTower() {
        return supportTower;
    }

    public int getTrackDistance(){ return trackDistance; }

    public boolean getGameWon() {
        return gameWon;
    }
    public void setGameWon(boolean gameWon) {
        this.gameWon = true;
    }




    public void addTowerFromSelectScreen(Tower tower, boolean isSelected) {
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
    public List<Item> getShopItems() {
        return shopItems;
    }
    public List<Item> getPlayerItems() {
        return playerItems;
    }
    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }


    public void swapTowers(Tower mainTower, Tower reserveTower) {

        if (mainTower instanceof SupportTower && reserveTower instanceof SupportTower) {

            int reserveIndex = reserveTowers.indexOf(reserveTower);

            for (Tower tower : mainTowers) {
                ((SupportTower) mainTower).removeReloadSpeedBoost(tower);

                ((SupportTower) reserveTower).boostReloadSpeed(tower);
            }
            for (Tower tower : reserveTowers) {
                ((SupportTower) mainTower).removeReloadSpeedBoost(tower);

                ((SupportTower) reserveTower).boostReloadSpeed(tower);
            }

            reserveTowers.set(reserveIndex, mainTower);
            supportTower = (SupportTower) reserveTower;
        }
        else {

            if (mainTower.getResourceType().equals(reserveTower.getResourceType()) && !(reserveTower instanceof SupportTower)) {

                int mainIndex = mainTowers.indexOf(mainTower);
                int reserveIndex = reserveTowers.indexOf(reserveTower);

                mainTowers.set(mainIndex, reserveTower);
                reserveTowers.set(reserveIndex, mainTower);
            } else {
                showAlert("Incompatible Towers", "Towers must be of the same resource type to swap.", Alert.AlertType.ERROR);
            }
        }

    }

    public void showAlert(String title, String message, Alert.AlertType alertType) {
        alertHandler.showAlert(title, message, alertType);
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
        for (Item item : shopItems) {
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
        shopItems.remove(item);
    }
    public void sellItem(Item item) {
        setCurrentBalance(currentBalance + item.getSellPrice());
        playerItems.remove(item);
        shopItems.add(item);
    }

    public void buyTower(Tower tower) {

        if (supportTower == null && tower instanceof SupportTower) {
            setCurrentBalance(currentBalance - tower.getCost());
            towersInShop.remove(tower);
            setSupportTower((SupportTower) tower);
        }
        else {
            setCurrentBalance(currentBalance - tower.getCost());
            reserveTowers.add(tower);
            towersInShop.remove(tower);
        }
    }
    public void sellTower(Tower tower) {
        setCurrentBalance(currentBalance + tower.getSellPrice());
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
        } else if (tower instanceof AmmoRelayStation) {
            return new AmmoRelayStation();
        } else if (tower instanceof MedicOutpost) {
            return new MedicOutpost();
        } else if (tower instanceof TroopCommandPost) {
            return new TroopCommandPost();
        }

            return null;
    }


    public void applyItemEffect(Item item) {
        if (!item.getIsRepairKit()){
            for (Tower tower : mainTowers) {
                if (item.getTowerType().equals(tower.getResourceType())) {
                    tower.setResourceAmount((int) (tower.getResourceAmount() + 20));
                }
            }
            for (Tower tower : reserveTowers) {
                if (item.getTowerType().equals(tower.getResourceType())) {
                    tower.setResourceAmount((int) (tower.getResourceAmount() + 20));
                }
            }
        }
    }

    public void removeItemEffect(Item item) {
        if (!item.getIsRepairKit()) {
            for (Tower tower : mainTowers) {
                if (item.getTowerType().equals(tower.getResourceType())) {
                    tower.setResourceAmount((int) (tower.getResourceAmount() - 20));
                }
            }
            for (Tower tower : reserveTowers) {
                if (item.getTowerType().equals(tower.getResourceType())) {
                    tower.setResourceAmount((int) (tower.getResourceAmount() - 20));
                }
            }
        }
    }


    public boolean shouldTriggerRandomEvent() {

        return switch (gameDifficulty) {
            case "Recruit" -> random.nextInt(100) < 15;
            case "Major" -> random.nextInt(100) < 20;
            case "Commander" -> random.nextInt(100) < 25;
            default -> false;
        };
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
        }
        else if (reserveTowers.isEmpty()){
                selectedTower = mainTowers.get(random.nextInt(mainTowers.size()));
            }
        else {
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

        if (!reserveTowers.isEmpty()) {
            allTowers.addAll(reserveTowers);
        }


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

    public void useRepairKit(Item repairKit, Tower tower) {
        tower.repairTower();
        playerItems.remove(repairKit);

        showAlert("Tower Repaired!", tower.getName() + " has been repaired using " + repairKit.getName(), Alert.AlertType.INFORMATION);
    }

    public void removeTower(Tower tower) {

        reserveTowers.remove(tower);

        showAlert("Tower Removed", tower.getName() + " has been removed from your inventory", Alert.AlertType.INFORMATION);
    }

    public boolean isMainTowerBroken() {

        for (Tower tower : mainTowers) {
            if (tower.isBroken()) {
                return true;
            }
        }
        return false;
    }

    public void updateShopPostRound() {

        if (currentRound == 4) {
            towersInShop.add(new EaglesNest());
        }
        if (currentRound == 8) {
            towersInShop.add(new FieldHospital());
        }
        if (currentRound == 12) {
            towersInShop.add(new SpecialForcesCamp());
        }
        if (currentRound == 3) {
            shopItems.add(new AmmoCrate());
        }
        if (currentRound == 6) {
            shopItems.add(new Paratroopers());
        }
        if (currentRound == 10) {
            shopItems.add(new MedicalSupplyDrop());
        }
        if (currentRound == 5) {
            towersInShop.add(new AmmoRelayStation());
        }
        if (currentRound == 8) {
            towersInShop.add(new TroopCommandPost());
        }
        if (currentRound == 11) {
            towersInShop.add(new MedicOutpost());
        }

    }

    public int roundWinPrize(){
        int prize = 200 + (currentRound - 1) * 20;
        currentBalance += prize;
        return prize;
    }
    public void levelUpTowers(ArrayList<Integer> carts){
        int numberOfAmmunitionCarts = carts.get(0);
        int numberOfMedkitCarts = carts.get(1);
        int numberOfTroopCarts = carts.get(2);
        for (Tower tower : mainTowers){
            String resourceType = tower.getResourceType();
            if (resourceType.equals("Medkits")) {
                for (int i = 0; i < numberOfMedkitCarts; i++){
                    tower.levelUp();
                }
            }
            if (resourceType.equals("Ammunition")) {
                for (int i = 0; i < numberOfAmmunitionCarts; i++) {
                    tower.levelUp();
                }
            }
            if (resourceType.equals("Troops")){
                for (int i = 0; i < numberOfTroopCarts; i++){
                    tower.levelUp();
                }
            }
        }
    }
}

