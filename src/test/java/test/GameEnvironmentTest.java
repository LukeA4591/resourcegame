package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.gui.AlertHandler;
import seng201.team0.models.towers.*;

import seng201.team0.GameEnvironment;
import seng201.team0.models.items.*;

import static org.mockito.Mockito.*;

public class GameEnvironmentTest {

    private GameEnvironment gameEnvironment;
    private AlertHandler alertHandler;

    @BeforeEach
    public void init() {

        alertHandler = mock(AlertHandler.class);

        gameEnvironment = new GameEnvironment(gameEnvironment1 -> {}, gameEnvironment2 -> {}, gameEnvironment3 -> {},
                gameEnvironment4 -> {}, gameEnvironment5 -> {}, gameEnvironment6 -> {}, () -> {}, alertHandler);

        gameEnvironment.initializeGame("Player", 10, "Major", 800, 900, 2);
    }


    @Test
    public void testInitializeGame() {

        assertEquals("Player", gameEnvironment.getPlayerName());
        assertEquals(10, gameEnvironment.getGameRounds());
        assertEquals("Major", gameEnvironment.getGameDifficulty());
        assertEquals(800, gameEnvironment.getCurrentBalance());
        assertEquals(900, gameEnvironment.getTrackDistance());
        assertEquals(2, gameEnvironment.getLivesLeft());

    }

    @Test
    public void testInitializeItems() {

        assertEquals(3, gameEnvironment.getShopItems().size());
        assertInstanceOf(AmmunitionTowerRepairKit.class, gameEnvironment.getShopItems().get(0));
        assertInstanceOf(MedkitTowerRepairKit.class, gameEnvironment.getShopItems().get(1));
        assertInstanceOf(TroopTowerRepairKit.class, gameEnvironment.getShopItems().get(2));


    }

    @Test
    public void testAddTowerFromSelectScreen() {
        Tower armoury = new Armoury();
        Tower arsenal = new Arsenal();

        gameEnvironment.addTowerFromSelectScreen(armoury, true);
        gameEnvironment.addTowerFromSelectScreen(arsenal, false);

        assertTrue(gameEnvironment.getMainTowers().contains(armoury));
        assertFalse(gameEnvironment.getReserveTowers().contains(armoury));
        assertTrue(gameEnvironment.getTowersInShop().contains(arsenal));
        assertFalse(gameEnvironment.getMainTowers().contains(arsenal));
    }

    @Test
    public void testSwapSupportTowers() {

        SupportTower mainSupportTower = new AmmoRelayStation();
        SupportTower reserveSupportTower = new TroopCommandPost();

        gameEnvironment.getMainTowers().add(new Barracks());
        gameEnvironment.getMainTowers().add(new Armoury());

        double originalReloadSpeedBarracks = gameEnvironment.getMainTowers().get(0).getReloadSpeed();
        double originalReloadSpeedArmoury = gameEnvironment.getMainTowers().get(1).getReloadSpeed();


        gameEnvironment.setSupportTower(mainSupportTower);
        gameEnvironment.getReserveTowers().add(reserveSupportTower);

        gameEnvironment.swapTowers(mainSupportTower, reserveSupportTower);

        assertEquals(reserveSupportTower, gameEnvironment.getSupportTower());
        assertEquals(mainSupportTower, gameEnvironment.getReserveTowers().get(0));




        for (Tower tower : gameEnvironment.getMainTowers()) {
            if (tower.getResourceType().equals("Troops")) {
                assertEquals(originalReloadSpeedBarracks - 0.3, tower.getReloadSpeed());
            }
            if (tower.getResourceType().equals("Ammunition")) {
                assertEquals(originalReloadSpeedArmoury + 0.3, tower.getReloadSpeed());
            }
        }

    }


    @Test
    public void testSwapNonSupportTowers() {

        Tower armoury = new Armoury();
        Tower arsenal = new Arsenal();

        gameEnvironment.getMainTowers().add(armoury);
        gameEnvironment.getReserveTowers().add(arsenal);


        gameEnvironment.swapTowers(armoury, arsenal);

        assertEquals(gameEnvironment.getMainTowers().get(0), arsenal);
        assertEquals(gameEnvironment.getReserveTowers().get(0), armoury);

    }

    @Test
    public void testSwapIncompatibleTowers() {

        Tower armoury = new Armoury();
        Tower medicalTent = new MedicalTent();
        SupportTower ammoRelayStation = new AmmoRelayStation();
        SupportTower medicOutpost = new MedicOutpost();

        gameEnvironment.getMainTowers().add(armoury);
        gameEnvironment.getReserveTowers().add(medicalTent);
        gameEnvironment.getReserveTowers().add(ammoRelayStation);
        gameEnvironment.getReserveTowers().add(medicOutpost);

        gameEnvironment.swapTowers(armoury,medicalTent);
        gameEnvironment.swapTowers(armoury, ammoRelayStation);
        gameEnvironment.swapTowers(armoury, medicOutpost);

        assertTrue(gameEnvironment.getMainTowers().contains(armoury));
        assertFalse(gameEnvironment.getMainTowers().contains(medicalTent));
        assertTrue(gameEnvironment.getReserveTowers().contains(medicalTent));
        assertFalse(gameEnvironment.getReserveTowers().contains(armoury));
        assertTrue(gameEnvironment.getReserveTowers().contains(ammoRelayStation));
        assertNotSame(gameEnvironment.getSupportTower(), medicOutpost);
        assertNotSame(gameEnvironment.getSupportTower(), ammoRelayStation);
    }

    @Test
    public void testGetMainTowerByName() {

        assertNull(gameEnvironment.getMainTowerByName("Armoury"));

        Tower armoury = new Armoury();
        Tower medicalTent = new MedicalTent();
        Tower barracks = new Barracks();

        gameEnvironment.getMainTowers().add(armoury);
        gameEnvironment.getMainTowers().add(medicalTent);
        gameEnvironment.getMainTowers().add(barracks);

        assertEquals(medicalTent, gameEnvironment.getMainTowerByName(medicalTent.getName()));

        assertNull(gameEnvironment.getMainTowerByName("Garrison"));
    }

    @Test
    public void testGetReserveTowerByName() {

        assertNull(gameEnvironment.getReserveTowerByName("Armoury"));

        Tower armoury = new Armoury();
        Tower medicalTent = new MedicalTent();
        Tower barracks = new Barracks();

        gameEnvironment.getReserveTowers().add(armoury);
        gameEnvironment.getReserveTowers().add(medicalTent);
        gameEnvironment.getReserveTowers().add(barracks);

        assertEquals(medicalTent, gameEnvironment.getReserveTowerByName(medicalTent.getName()));

        assertNull(gameEnvironment.getReserveTowerByName("Garrison"));
    }

    @Test
    public void testGetTowerInShopByName() {

        assertNull(gameEnvironment.getTowerInShopByName("Armoury"));

        Tower armoury = new Armoury();
        Tower medicalTent = new MedicalTent();
        Tower barracks = new Barracks();

        gameEnvironment.getTowersInShop().add(armoury);
        gameEnvironment.getTowersInShop().add(medicalTent);
        gameEnvironment.getTowersInShop().add(barracks);

        assertEquals(medicalTent, gameEnvironment.getTowerInShopByName(medicalTent.getName()));

        assertNull(gameEnvironment.getTowerInShopByName("Garrison"));
    }

    @Test
    public void testGetItemInShopByName() {

        assertNull(gameEnvironment.getItemInShopByName("Ammo Crate"));

        Item ammoCrate = new AmmoCrate();
        Item medicalSupplyDrop = new MedicalSupplyDrop();
        Item troopTowerRepairKit = new TroopTowerRepairKit();

        gameEnvironment.getShopItems().add(ammoCrate);
        gameEnvironment.getShopItems().add(medicalSupplyDrop);
        gameEnvironment.getShopItems().add(troopTowerRepairKit);

        assertEquals(medicalSupplyDrop, gameEnvironment.getItemInShopByName(medicalSupplyDrop.getName()));

        assertNull(gameEnvironment.getMainTowerByName("Medkit Tower Repair Kit"));
    }

    @Test
    public void testGetPlayerItemByName() {

        assertNull(gameEnvironment.getPlayerItemByName("Ammo Crate"));

        Item ammoCrate = new AmmoCrate();
        Item medicalSupplyDrop = new MedicalSupplyDrop();
        Item troopTowerRepairKit = new TroopTowerRepairKit();

        gameEnvironment.getPlayerItems().add(ammoCrate);
        gameEnvironment.getPlayerItems().add(medicalSupplyDrop);
        gameEnvironment.getPlayerItems().add(troopTowerRepairKit);

        assertEquals(medicalSupplyDrop, gameEnvironment.getPlayerItemByName(medicalSupplyDrop.getName()));

        assertNull(gameEnvironment.getMainTowerByName("Medkit Tower Repair Kit"));
    }

    @Test
    public void testBuyItem() {

        gameEnvironment.initializeItems();




    }

}
