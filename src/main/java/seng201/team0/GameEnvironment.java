package seng201.team0;

import javafx.scene.control.Alert;
import seng201.team0.gui.AlertHandler;
import seng201.team0.models.RandomEvent;
import seng201.team0.models.items.*;
import seng201.team0.models.towers.*;
import seng201.team0.models.towers.gametowers.*;
import seng201.team0.models.towers.supporttowers.AmmoRelayStation;
import seng201.team0.models.towers.supporttowers.MedicOutpost;
import seng201.team0.models.towers.supporttowers.TroopCommandPost;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Manages the game's state such as player and game information.
 * It also manages screen transitions and allows for some game services.
 */
public class GameEnvironment {
    /**
     * The handler to show alerts.
     */
    private final AlertHandler alertHandler;
    /**
     * The Consumers to launch each respective screen.
     */
    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> towerSelectScreenLauncher;
    private final Consumer<GameEnvironment> gameScreenLauncher;
    private final Consumer<GameEnvironment> shopScreenLauncher;
    private final Consumer<GameEnvironment> inventoryScreenLauncher;
    private final Consumer<GameEnvironment> endGameScreenLauncher;
    /**
     * The runnable to clear the screen.
     */
    private final Runnable clearScreen;
    /**
     * String for storing the player's name.
     */
    private String playerName;
    /**
     * int for storing the game's total rounds.
     */
    private int gameRounds;
    /**
     * int for storing the game's current round.
     */
    private int currentRound = 1;
    /**
     * String for storing the game difficulty.
     */
    private String gameDifficulty;
    /**
     * double for storing the current balance.
     */
    private double currentBalance;
    /**
     * int for storing the player's lives left.
     */
    private int livesLeft;
    /**
     * int for storing the track distance, scaled by difficulty.
     */
    private int trackDistance;
    /**
     * String for storing the round mode.
     */
    private String roundMode;
    /**
     * List for managing the main towers.
     */
    private final List<Tower> mainTowers;
    /**
     * List for managing the towers in the shop.
     */
    private final List<Tower> towersInShop;
    /**
     * List for managing the reserve towers.
     */
    private final List<Tower> reserveTowers;
    /**
     * SupportTower for managing the support tower.
     */
    private SupportTower supportTower = null;
    /**
     * List for storing the shop items.
     */
    private final List<Item> shopItems;
    /**
     * List for storing the player's items.
     */
    private final List<Item> playerItems;
    /**
     * Random for storing the random generator.
     */
    private final Random random;
    /**
     * boolean which indicates whether the game is won.
     */
    private boolean gameWon;

    /**
     * Constructs the game environment with the specified screen launchers, alert handler, and random generator.
     * It also initializes items and launches the setup screen.
     *
     * @param setupScreenLauncher the Consumer to launch the setup screen.
     * @param towerSelectScreenLauncher the Consumer to launch the tower select screen.
     * @param gameScreenLauncher the Consumer to launch the game screen.
     * @param shopScreenLauncher the Consumer to launch the shop screen.
     * @param inventoryScreenLauncher the Consumer to launch the inventory screen.
     * @param endGameScreenLauncher the Consumer to launch the end game screen.
     * @param clearScreen the Runnable to clear the screen in between transitions.
     * @param alertHandler the alert handler to show alerts.
     * @param random the random generator.
     */
    public GameEnvironment(final Consumer<GameEnvironment> setupScreenLauncher, final Consumer<GameEnvironment>
            towerSelectScreenLauncher, final Consumer<GameEnvironment> gameScreenLauncher,
            final Consumer<GameEnvironment> shopScreenLauncher, final Consumer<GameEnvironment> inventoryScreenLauncher,
            final Consumer<GameEnvironment> endGameScreenLauncher, final Runnable clearScreen, final AlertHandler
            alertHandler, final Random random) {

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

        this.random = random;

        initializeItems();
    }

    /**
     * Initializes items first available in the shop.
     */
    public void initializeItems() {
        shopItems.add(new AmmunitionTowerRepairKit());
        shopItems.add(new MedkitTowerRepairKit());
        shopItems.add(new TroopTowerRepairKit());
    }

    /**
     * Launches the setup screen.
     */
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    /**
     * Closes the setup screen and launches the tower select screen.
     */
    public void closeSetupScreen() {
        clearScreen.run();
        launchTowerSelectScreen();
    }

    /**
     * Launches the tower select screen.
     */
    public void launchTowerSelectScreen() {
        towerSelectScreenLauncher.accept(this);
    }

    /**
     * Closes the tower select screen and launches the game screen.
     */
    public void closeTowerSelectScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Launches the game screen.
     */
    public void launchGameScreen() {
        gameScreenLauncher.accept(this);
    }

    /**
     * Closing the game screen and launches the shop or inventory screen depending on the parameter isInventory.
     *
     * @param isInventory true for launching the inventory screen and false for launching the shop screen.
     */
    public void closeGameScreen(final boolean isInventory) {
        clearScreen.run();
        if (isInventory) {
            launchInventoryScreen();
        }
        else {
            launchShopScreen();
        }
    }

    /**
     * Refreshes the game screen.
     */
    public void refreshGameScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Launches the shop screen.
     */
    public void launchShopScreen() {
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }

    /**
     * Closes the Shop Screen and launches the game screen.
     */
    public void closeShopScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Launches the inventory screen.
     */
    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    /**
     * Closes the inventory screen and launches the game screen.
     */
    public void closeInventoryScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Launches the end game screen.
     */
    public void launchEndGameScreen() {
        clearScreen.run();
        endGameScreenLauncher.accept(this);
    }

    /**
     * Initializes the game with the specified parameters.
     *
     * @param name the name the player chose.
     * @param rounds the amount of rounds the player chose.
     * @param gameDifficulty the game difficulty the player chose.
     * @param startingMoney the starting balance.
     * @param trackDistance the track distance.
     * @param lives the lives the player has.
     */
    public void initializeGame(final String name, final int rounds, final String gameDifficulty, final double
            startingMoney, final int trackDistance, final int lives) {

        this.playerName = name;
        this.gameRounds = rounds;
        this.gameDifficulty = gameDifficulty;
        setCurrentBalance(startingMoney);
        this.trackDistance = trackDistance;
        this.livesLeft = lives;

    }

    /**
     * Gets the player's name.
     *
     * @return a String, the player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the game rounds.
     *
     * @return an int, the game rounds.
     */
    public int getGameRounds() {
        return gameRounds;
    }

    /**
     * Gets the current round.
     *
     * @return an int, the current round.
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets the current round at a specified round number.
     *
     * @param roundNumber the specified round number.
     */
    public void setCurrentRound(final int roundNumber) {
        this.currentRound = roundNumber;
    }

    /**
     * Gets the game difficulty.
     *
     * @return a String, the game difficulty.
     */
    public String getGameDifficulty() {
        return gameDifficulty;
    }

    /**
     * Sets the current balance.
     *
     * @param balance the specified new balance.
     */
    public void setCurrentBalance(final Double balance) {
        this.currentBalance = balance;
    }

    /**
     * Gets the current balance.
     *
     * @return a double, the current balance.
     */
    public double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Gets the lives left.
     *
     * @return an int, the lives left.
     */
    public int getLivesLeft() {
        return livesLeft;
    }

    /**
     * Loses one life.
     */
    public void loseLife() {
        livesLeft --;
    }

    /**
     * Determines whether the game is lost
     *
     * @return a boolean, true if the game is lost, false otherwise.
     */
    public boolean isGameLost() {
        return livesLeft <= 0;
    }

    /**
     * Determines whether the game is won.
     *
     * @return a boolean, true if the game is won, false otherwise.
     */
    public boolean isGameWon () {
        return currentRound >= gameRounds;
    }

    /**
     * Sets the support tower.
     *
     * @param tower the support tower to set as the support tower.
     */
    public void setSupportTower(final SupportTower tower) {
        supportTower = tower;
    }

    /**
     * Gets the support tower.
     *
     * @return a SupportTower, the support tower.
     */
    public SupportTower getSupportTower() {
        return supportTower;
    }

    /**
     * Gets the track distance.
     *
     * @return an int, the track distance.
     */
    public int getTrackDistance() {
        return trackDistance;
    }

    /**
     * Gets whether the game is won.
     *
     * @return a boolean, true if the game is won, false otherwise.
     */
    public boolean getGameWon() {
        return gameWon;
    }

    /**
     * Sets game as won.
     *
     * @param gameWon a boolean, true if the game is won, false otherwise.
     */
    public void setGameWon(final boolean gameWon) {
        this.gameWon = gameWon;
    }

    /**
     * Adds a tower from the select screen based on whether they were selected.
     *
     * @param tower a Tower, the selected tower.
     * @param isSelected a boolean, true if the tower was selected, false if unselected.
     */
    public void addTowerFromSelectScreen(final Tower tower, final boolean isSelected) {
        if (isSelected) {
            mainTowers.add(tower);
        }
        else {
            towersInShop.add(tower);
        }
    }

    /**
     * Gets the main towers.
     *
     * @return a List of the main towers.
     */
    public List<Tower> getMainTowers() {
        return mainTowers;
    }

    /**
     * Gets the towers in the shop.
     *
     * @return a List of the towers in the shop.
     */
    public List<Tower> getTowersInShop() {
        return towersInShop;
    }

    /**
     * Gets the items in the shop.
     *
     * @return a List of the items in the shop.
     */
    public List<Item> getShopItems() {
        return shopItems;
    }

    /**
     * Gets the player's items.
     *
     * @return a List of the player's items.
     */
    public List<Item> getPlayerItems() {
        return playerItems;
    }

    /**
     * Gets the reserve towers.
     *
     * @return a List of the reserve towers.
     */
    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }

    /**
     * Swaps the specified main and reserve tower.
     * Also handles swapping support towers.
     *
     * @param mainTower the tower to be swapped to reserve.
     * @param reserveTower the tower to be swapped to main.
     */
    public void swapTowers(final Tower mainTower, final Tower reserveTower) {

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

            if (mainTower.getResourceType().equals(reserveTower.getResourceType()) && !(reserveTower instanceof
                    SupportTower)) {

                int mainIndex = mainTowers.indexOf(mainTower);
                int reserveIndex = reserveTowers.indexOf(reserveTower);

                mainTowers.set(mainIndex, reserveTower);
                reserveTowers.set(reserveIndex, mainTower);
            } else {
                showAlert("Incompatible Towers", "Towers must be of the same resource type to swap.",
                        Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Uses alertHandler to show an alert with the specified title, message, and alert type.
     *
     * @param title the title for the alert.
     * @param message the alert message.
     * @param alertType the alert type.
     */
    public void showAlert(final String title, final String message, final Alert.AlertType alertType) {
        alertHandler.showAlert(title, message, alertType);
    }

    /**
     * Gets a main tower by its name.
     *
     * @param name a String, the main tower's name.
     * @return a Tower, the specified main tower.
     */
    public Tower getMainTowerByName(final String name) {
        for (Tower tower : mainTowers) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    /**
     * Gets a reserve tower by its name.
     *
     * @param name a String, the reserve tower's name.
     * @return a Tower, the specified reserve tower.
     */
    public Tower getReserveTowerByName(final String name) {
        for (Tower tower : reserveTowers) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    /**
     * Gets a tower in the shop by its name.
     *
     * @param name a String, the tower in the shop's name.
     * @return a Tower, the specified tower in the shop.
     */
    public Tower getTowerInShopByName(final String name) {
        for (Tower tower : towersInShop) {
            if (tower.getName().equals(name)) {
                return tower;
            }
        }
        return null;
    }

    /**
     * Gets an item in the shop by its name.
     *
     * @param name a String, the item in the shop's name.
     * @return an Item, the specified item in the shop.
     */
    public Item getItemInShopByName(final String name) {
        for (Item item : shopItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Gets a player's item by its name.
     *
     * @param name a String, the player item's name.
     * @return an Item, the specified player item.
     */
    public Item getPlayerItemByName(final String name) {
        for (Item item : playerItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Buys an item from the shop.
     *
     * @param item an Item, the item to buy.
     */
    public void buyItem(final Item item) {
        setCurrentBalance(currentBalance - item.getCost());
        playerItems.add(item);
        shopItems.remove(item);
    }

    /**
     * Sells an item to the shop.
     *
     * @param item an Item, the item to sell.
     */
    public void sellItem(final Item item) {
        setCurrentBalance(currentBalance + item.getSellPrice());
        playerItems.remove(item);
        shopItems.add(item);
    }

    /**
     * Buys a tower from the shop.
     *
     * @param tower a Tower, the tower to buy.
     */
    public void buyTower(final Tower tower) {

        if (supportTower == null && tower instanceof SupportTower) {
            setCurrentBalance(currentBalance - tower.getCost());
            towersInShop.remove(tower);
            setSupportTower((SupportTower) tower);
        } else {
            setCurrentBalance(currentBalance - tower.getCost());
            reserveTowers.add(tower);
            towersInShop.remove(tower);
        }
    }

    /**
     * Sells a Tower to the shop.
     *
     * @param tower a Tower, the tower to sell.
     */
    public void sellTower(final Tower tower) {
        setCurrentBalance(currentBalance + tower.getSellPrice());
        reserveTowers.remove(tower);
        towersInShop.add(createNewInstance(tower));
    }

    /**
     * Creates a new instance of the specified tower.
     *
     * @param tower a tower, the tower to create a new instance of.
     * @return a tower, a new instance of the specified tower.
     */
    public Tower createNewInstance(final Tower tower) {
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

    /**
     * Applies the effect of an item.
     *
     * @param item an Item to apply the effect of.
     */
    public void applyItemEffect(final Item item) {
        if (!item.getIsRepairKit()) {
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

    /**
     * Removes the effect of an item.
     *
     * @param item an Item to remove the effect of.
     */
    public void removeItemEffect(final Item item) {
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

    /**
     * Determines whether a random event should be triggered based on game difficulty.
     *
     * @return a boolean, true if a random event will trigger, false if not.
     */
    public boolean shouldTriggerRandomEvent() {

        return switch (gameDifficulty) {
            case "Recruit" -> random.nextInt(100) < 15;
            case "Major" -> random.nextInt(100) < 20;
            case "Commander" -> random.nextInt(100) < 25;
            default -> false;
        };
    }

    /**
     * Initiates the random event by getting one.
     *
     */
    public void initiateRandomEvent() {
        RandomEvent event = getRandomEvent();

        switch (event) {
            case ENEMY_AIR_STRIKE:
                enemyAirStrike();
                break;
            case ENEMY_AMBUSH:
                enemyAmbush();
                break;
            case COMMUNICATIONS_BREAKDOWN:
                communicationsBreakdown();
                break;
            case MEDICAL_SUPPLY_LINE_SABOTAGE:
                medicalSupplyLineSabotage();
                break;
        }
    }

    /**
     * Gets a random, random event.
     *
     * @return a RandomEvent, the random event to be triggered.
     */
    public RandomEvent getRandomEvent() {
        RandomEvent[] randomEvents = RandomEvent.values();
        return randomEvents[random.nextInt(randomEvents.length)];
    }

    /**
     * Triggers an enemy airstrike random event and breaks a tower.
     * Chances increased of a tower breaking if it was used in the last round.
     */
    public void enemyAirStrike() {

        Tower selectedTower;

        if (50 > random.nextInt(65)) {
            selectedTower = mainTowers.get(random.nextInt(mainTowers.size()));
        }
        else if (reserveTowers.isEmpty()) {
            selectedTower = mainTowers.get(random.nextInt(mainTowers.size()));
        }
        else {
            selectedTower = reserveTowers.get(random.nextInt(reserveTowers.size()));
        }
        showAlert("ENEMY AIRSTRIKE", "An enemy airstrike has broken your " + selectedTower.getName() +
                " tower.", Alert.AlertType.INFORMATION);

        selectedTower.breakTower();
    }

    /**
     * Triggers a communications breakdown random event.
     * Decreases the reload speed of a random troops tower.
     */
    public void communicationsBreakdown() {

        List<Tower> allTowers = new ArrayList<>();
        allTowers.addAll(mainTowers);
        allTowers.addAll(reserveTowers);

        List<Tower> possibleTowers = new ArrayList<>();

        for (Tower tower : allTowers) {
            if (tower.getResourceType().equals("Troops")) {
                possibleTowers.add(tower);
            }
        }

        Tower selectedTower = possibleTowers.get(random.nextInt(possibleTowers.size()));

        showAlert("COMMUNICATIONS BREAKDOWN", "Our communication lines have been shut down!: " +
                selectedTower.getName() + " reload speed has been decreased.", Alert.AlertType.INFORMATION);

        selectedTower.communicationsBreakdown();

    }

    /**
     * Triggers a medical supply line sabotage random event.
     * Decreases the reload speed of a random medkit tower.
     */
    public void medicalSupplyLineSabotage() {

        List<Tower> allTowers = new ArrayList<>();
        allTowers.addAll(mainTowers);
        allTowers.addAll(reserveTowers);

        List<Tower> possibleTowers = new ArrayList<>();

        for (Tower tower : allTowers) {
            if (tower.getResourceType().equals("Medkits")) {
                possibleTowers.add(tower);
            }
        }

        Tower selectedTower = possibleTowers.get(random.nextInt(possibleTowers.size()));

        showAlert("MEDICAL SUPPLY LINE SABOTAGE", "Our medical supply lines have been sabotaged!: " +
                selectedTower.getName() + " reload speed has been decreased.", Alert.AlertType.INFORMATION);

        selectedTower.medicalSupplyLineSabotage();
    }

    /**
     * Triggers an enemy ambush random event.
     * Decreases the reload speed of a random ammunition tower.
     */
    public void enemyAmbush() {

        List<Tower> allTowers = new ArrayList<>();
        allTowers.addAll(mainTowers);
        allTowers.addAll(reserveTowers);

        List<Tower> possibleTowers = new ArrayList<>();

        for (Tower tower : allTowers) {
            if (tower.getResourceType().equals("Ammunition")) {
                possibleTowers.add(tower);
            }
        }

        Tower selectedTower = possibleTowers.get(random.nextInt(possibleTowers.size()));

        showAlert("ENEMY AMBUSH", "Our ammunition suppliers have been ambushed!: " +
                selectedTower.getName() + " reload speed has been decreased.", Alert.AlertType.INFORMATION);

        selectedTower.enemyAmbush();
    }

    /**
     * Uses a repair kit to repair a specified broken tower.
     *
     * @param repairKit an Item, the repair kit to use.
     * @param tower a Tower, the broken tower to repair.
     */
    public void useRepairKit(final Item repairKit, final Tower tower) {
        tower.repairTower();
        playerItems.remove(repairKit);

        showAlert("Tower Repaired!", tower.getName() + " has been repaired using " +
                repairKit.getName(), Alert.AlertType.INFORMATION);
    }

    /**
     * Removes a specified broken tower from the reserve towers.
     *
     * @param tower a Tower, the broken tower to be removed from reserve towers.
     */
    public void removeTower(final Tower tower) {

        reserveTowers.remove(tower);

        showAlert("Tower Removed", tower.getName() + " has been removed from your inventory",
                Alert.AlertType.INFORMATION);
    }

    /**
     * Determines if a main tower is broken.
     *
     * @return a boolean, true if a main tower is broken, false if not.
     */
    public boolean isMainTowerBroken() {

        for (Tower tower : mainTowers) {
            if (tower.isBroken()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the towers available in the shop.
     * Called after every round.
     */
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

    /**
     * Gets the bonus money for winning a round based on the current round.
     *
     * @return an int, the money won in the round.
     */
    public int getRoundWinBonus() {
        int prize = 200 + (currentRound - 1) * 20;
        currentBalance += prize;
        return prize;
    }

    /**
     * Levels up the main towers depending on the round mode.
     * Biasing 2 levels if the round favoured that tower type, 1 otherwise.
     * Called after every round.
     */
    public void levelUpTowers() {
        for (Tower tower : mainTowers) {
            switch (roundMode) {
                case("Artillery Barrage"):
                    if (tower.getResourceType().equals("Ammunition")) {
                        tower.levelUp();
                        tower.levelUp();
                    } else {
                        tower.levelUp();
                    }
                    break;
                case("Ground Offensive"):
                    if (tower.getResourceType().equals("Troops")) {
                        tower.levelUp();
                        tower.levelUp();
                    } else {
                        tower.levelUp();
                    }
                    break;
                case ("Rescue Operation"):
                    if (tower.getResourceType().equals("Medkits")) {
                        tower.levelUp();
                        tower.levelUp();
                    } else {
                        tower.levelUp();
                    }
            }
        }
    }

    /**
     * Sets the round mode
     *
     * @param roundMode a String, the round mode to set.
     */
    public void setRoundMode(final String roundMode) {
        this.roundMode = roundMode;
    }
}

