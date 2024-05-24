package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.gui.AlertHandler;
import seng201.team0.models.towers.*;

import seng201.team0.GameEnvironment;
import seng201.team0.models.items.*;
import seng201.team0.models.towers.gametowers.*;
import seng201.team0.models.towers.supporttowers.AmmoRelayStation;
import seng201.team0.models.towers.supporttowers.MedicOutpost;
import seng201.team0.models.towers.supporttowers.TroopCommandPost;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;

public class GameEnvironmentTest {

    private GameEnvironment gameEnvironment;

    @BeforeEach
    public void init() {

        AlertHandler alertHandler = mock(AlertHandler.class);

        gameEnvironment = new GameEnvironment(gameEnvironment1 -> {}, gameEnvironment2 -> {}, gameEnvironment3 -> {},
                gameEnvironment4 -> {}, gameEnvironment5 -> {}, gameEnvironment6 -> {}, () -> {}, alertHandler,
                new Random(200));

        gameEnvironment.initializeGame("Player", 10, "Major", 800,
                900, 2);
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


        assertNull(gameEnvironment.getItemInShopByName("Paratroopers"));
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

        assertNull(gameEnvironment.getPlayerItemByName("Medkit Tower Repair Kit"));
    }

    @Test
    public void testBuyItem() {

        Item ammunitionRepairKit = new AmmunitionTowerRepairKit();

        gameEnvironment.buyItem(ammunitionRepairKit);

        assertEquals(800 - ammunitionRepairKit.getCost(), gameEnvironment.getCurrentBalance());
        assertTrue(gameEnvironment.getPlayerItems().contains(ammunitionRepairKit));
        assertFalse(gameEnvironment.getShopItems().contains(ammunitionRepairKit));

    }

    @Test
    public void testSellItem() {

        Item ammunitionRepairKit = new AmmunitionTowerRepairKit();

        gameEnvironment.buyItem(ammunitionRepairKit);
        gameEnvironment.sellItem(ammunitionRepairKit);

        assertEquals(800 + ammunitionRepairKit.getSellPrice() - ammunitionRepairKit.getCost(),
                gameEnvironment.getCurrentBalance());
        assertTrue(gameEnvironment.getShopItems().contains(ammunitionRepairKit));
        assertFalse(gameEnvironment.getPlayerItems().contains(ammunitionRepairKit));

    }

    @Test
    public void testBuyTower() {

        Tower armoury = new Armoury();
        SupportTower ammoRelayStation = new AmmoRelayStation();
        SupportTower medicOutpost = new MedicOutpost();

        gameEnvironment.getTowersInShop().addAll(List.of(armoury, ammoRelayStation, medicOutpost));

        gameEnvironment.buyTower(armoury);

        assertEquals(800 - armoury.getCost(), gameEnvironment.getCurrentBalance());
        assertTrue(gameEnvironment.getReserveTowers().contains(armoury));
        assertFalse(gameEnvironment.getTowersInShop().contains(armoury));

        gameEnvironment.setCurrentBalance(gameEnvironment.getCurrentBalance() + armoury.getCost());
        gameEnvironment.buyTower(ammoRelayStation);

        assertEquals(800 - ammoRelayStation.getCost(), gameEnvironment.getCurrentBalance());
        assertSame(gameEnvironment.getSupportTower(), ammoRelayStation);
        assertFalse(gameEnvironment.getTowersInShop().contains(ammoRelayStation));

        gameEnvironment.setCurrentBalance(gameEnvironment.getCurrentBalance() + ammoRelayStation.getCost());
        gameEnvironment.buyTower(medicOutpost);

        assertEquals(800 - medicOutpost.getCost(), gameEnvironment.getCurrentBalance());
        assertTrue(gameEnvironment.getReserveTowers().contains(medicOutpost));
        assertNotSame(gameEnvironment.getSupportTower(), medicOutpost);

    }

    @Test
    public void testSellTower() {

        Tower armoury = new Armoury();
        SupportTower ammoRelayStation = new AmmoRelayStation();
        SupportTower medicOutpost = new MedicOutpost();

        gameEnvironment.getTowersInShop().addAll(List.of(armoury, ammoRelayStation, medicOutpost));

        gameEnvironment.buyTower(armoury);
        gameEnvironment.sellTower(armoury);

        assertEquals(800 - armoury.getCost() + armoury.getSellPrice(), gameEnvironment.getCurrentBalance());
        assertInstanceOf(Armoury.class, gameEnvironment.getTowersInShop().get(2));
        assertFalse(gameEnvironment.getReserveTowers().contains(armoury));

        gameEnvironment.setCurrentBalance(gameEnvironment.getCurrentBalance() + armoury.getSellPrice());
        gameEnvironment.buyTower(ammoRelayStation);
        gameEnvironment.sellTower(ammoRelayStation);

        assertEquals(800 - ammoRelayStation.getCost() + ammoRelayStation.getSellPrice(),
                gameEnvironment.getCurrentBalance());
        assertInstanceOf(AmmoRelayStation.class, gameEnvironment.getTowersInShop().get(2));
        assertFalse(gameEnvironment.getReserveTowers().contains(ammoRelayStation));

        gameEnvironment.setCurrentBalance(gameEnvironment.getCurrentBalance() + ammoRelayStation.getSellPrice());
        gameEnvironment.buyTower(medicOutpost);
        gameEnvironment.sellTower(medicOutpost);

        assertEquals(800 - medicOutpost.getCost() + medicOutpost.getSellPrice(),
                gameEnvironment.getCurrentBalance());
        assertInstanceOf(MedicOutpost.class, gameEnvironment.getTowersInShop().get(2));
        assertFalse(gameEnvironment.getReserveTowers().contains(medicOutpost));
    }

    @Test
    public void testApplyItemEffect() {

        Tower armoury = new Armoury();
        Tower medicalTent = new MedicalTent();
        Tower barracks = new Barracks();

        gameEnvironment.getMainTowers().add(armoury);
        gameEnvironment.getReserveTowers().add(medicalTent);
        gameEnvironment.getReserveTowers().add(barracks);

        Item ammoCrate = new AmmoCrate();

        double originalResourceAmount = armoury.getResourceAmount();

        gameEnvironment.applyItemEffect(ammoCrate);

        assertEquals(20 + originalResourceAmount, armoury.getResourceAmount());
        assertEquals(originalResourceAmount, medicalTent.getResourceAmount());
        assertEquals(originalResourceAmount, barracks.getResourceAmount());

    }

    @Test
    public void testRemoveItemEffect() {

        Tower armoury = new Armoury();
        Tower medicalTent = new MedicalTent();
        Tower barracks = new Barracks();

        gameEnvironment.getMainTowers().add(armoury);
        gameEnvironment.getReserveTowers().add(medicalTent);
        gameEnvironment.getReserveTowers().add(barracks);

        Item ammoCrate = new AmmoCrate();

        double originalResourceAmount = armoury.getResourceAmount();

        gameEnvironment.removeItemEffect(ammoCrate);

        assertEquals(originalResourceAmount - 20, armoury.getResourceAmount());
        assertEquals(originalResourceAmount, medicalTent.getResourceAmount());
        assertEquals(originalResourceAmount, barracks.getResourceAmount());
    }

    @Test
    public void testShouldTriggerRandomEvent() {

        gameEnvironment.initializeGame("Player", 10, "Recruit", 1000,
                1000, 3);
        assertTrue(gameEnvironment.shouldTriggerRandomEvent() || !gameEnvironment.shouldTriggerRandomEvent());

        gameEnvironment.initializeGame("Player", 10, "Major", 1000,
                1000, 3);
        assertTrue(gameEnvironment.shouldTriggerRandomEvent() || !gameEnvironment.shouldTriggerRandomEvent());

        gameEnvironment.initializeGame("Player", 10, "Commander", 1000,
                1000, 3);
        assertTrue(gameEnvironment.shouldTriggerRandomEvent() || !gameEnvironment.shouldTriggerRandomEvent());
    }

    @Test
    public void testInitiateRandomEvent() {
        gameEnvironment = spy(gameEnvironment);
        doNothing().when(gameEnvironment).enemyAirStrike();
        doNothing().when(gameEnvironment).enemyAmbush();
        doNothing().when(gameEnvironment).communicationsBreakdown();
        doNothing().when(gameEnvironment).medicalSupplyLineSabotage();

        for (int i = 0; i < 1000; i++) {
            gameEnvironment.initiateRandomEvent();
        }

        verify(gameEnvironment, atLeastOnce()).enemyAmbush();
        verify(gameEnvironment, atLeastOnce()).enemyAirStrike();
        verify(gameEnvironment, atLeastOnce()).communicationsBreakdown();
        verify(gameEnvironment, atLeastOnce()).medicalSupplyLineSabotage();

    }

    @Test
    public void testEnemyAirStrike() {

        Tower mainTower = new Armoury();
        gameEnvironment.getMainTowers().add(mainTower);
        gameEnvironment.enemyAirStrike();

        assertTrue(mainTower.isBroken());

        Tower reserveTower = new SpecialForcesCamp();
        gameEnvironment.getReserveTowers().add(reserveTower);


        gameEnvironment = spy(gameEnvironment);

        for (int i = 0; i < 100; i++) {
            gameEnvironment.enemyAirStrike();
        }

        assertTrue(reserveTower.isBroken());
    }

    @Test
    public void testCommunicationsBreakdown() {

        Tower mainTower = new Barracks();
        gameEnvironment.getMainTowers().add(mainTower);
        double originalReloadSpeed = mainTower.getReloadSpeed();

        gameEnvironment.communicationsBreakdown();

        assertEquals(originalReloadSpeed + 0.15, mainTower.getReloadSpeed());

        gameEnvironment.getMainTowers().remove(mainTower);

        Tower reserveTower = new Garrison();
        gameEnvironment.getReserveTowers().add(reserveTower);
        originalReloadSpeed = reserveTower.getReloadSpeed();

        gameEnvironment.communicationsBreakdown();

        assertEquals(originalReloadSpeed + 0.15, reserveTower.getReloadSpeed());

    }

    @Test
    public void testMedicalSupplyLineSabotage() {

        Tower mainTower = new MedicalTent();
        gameEnvironment.getMainTowers().add(mainTower);
        double originalReloadSpeed = mainTower.getReloadSpeed();

        gameEnvironment.medicalSupplyLineSabotage();

        assertEquals(originalReloadSpeed + 0.15, mainTower.getReloadSpeed());

        gameEnvironment.getMainTowers().remove(mainTower);

        Tower reserveTower = new Medbay();
        gameEnvironment.getReserveTowers().add(reserveTower);
        originalReloadSpeed = reserveTower.getReloadSpeed();

        gameEnvironment.medicalSupplyLineSabotage();

        assertEquals(originalReloadSpeed + 0.15, reserveTower.getReloadSpeed());

    }

    @Test
    public void testEnemyAmbush() {

        Tower mainTower = new Armoury();
        gameEnvironment.getMainTowers().add(mainTower);
        double originalReloadSpeed = mainTower.getReloadSpeed();

        gameEnvironment.enemyAmbush();

        assertEquals(originalReloadSpeed + 0.15, mainTower.getReloadSpeed());

        gameEnvironment.getMainTowers().remove(mainTower);

        Tower reserveTower = new Arsenal();
        gameEnvironment.getReserveTowers().add(reserveTower);
        originalReloadSpeed = reserveTower.getReloadSpeed();

        gameEnvironment.enemyAmbush();

        assertEquals(originalReloadSpeed + 0.15, reserveTower.getReloadSpeed());

    }

    @Test
    public void testUseRepairKit() {
        Tower armoury = new Armoury();
        armoury.breakTower();
        Item ammunitionTowerRepairKit = new AmmunitionTowerRepairKit();

        gameEnvironment.getMainTowers().add(armoury);
        gameEnvironment.getPlayerItems().add(ammunitionTowerRepairKit);

        gameEnvironment.useRepairKit(ammunitionTowerRepairKit, armoury);

        assertFalse(armoury.isBroken());
        assertFalse(gameEnvironment.getPlayerItems().contains(ammunitionTowerRepairKit));
    }

    @Test
    public void testRemoveTower() {

        Tower armoury = new Armoury();

        gameEnvironment.getReserveTowers().add(armoury);
        gameEnvironment.removeTower(armoury);

        assertFalse(gameEnvironment.getReserveTowers().contains(armoury));

    }

    @Test
    public void isMainTowerBroken() {

        assertFalse(gameEnvironment.isMainTowerBroken());

        Tower armoury = new Armoury();
        Tower barracks = new Barracks();
        Tower medicalTent = new MedicalTent();

        gameEnvironment.getMainTowers().addAll(List.of(armoury, barracks, medicalTent));

        assertFalse(gameEnvironment.isMainTowerBroken());

        barracks.breakTower();

        assertTrue(gameEnvironment.isMainTowerBroken());
    }

    @Test
    public void testUpdateShopPostRound() {

        gameEnvironment.setCurrentRound(3);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getShopItems().stream().anyMatch(item -> item instanceof AmmoCrate));

        gameEnvironment.setCurrentRound(4);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getTowersInShop().stream().anyMatch(tower -> tower instanceof EaglesNest));

        gameEnvironment.setCurrentRound(5);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getTowersInShop().stream().anyMatch(tower -> tower instanceof AmmoRelayStation));

        gameEnvironment.setCurrentRound(6);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getShopItems().stream().anyMatch(item -> item instanceof Paratroopers));

        gameEnvironment.setCurrentRound(8);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getTowersInShop().stream().anyMatch(tower -> tower instanceof FieldHospital));
        assertTrue(gameEnvironment.getTowersInShop().stream().anyMatch(tower -> tower instanceof TroopCommandPost));

        gameEnvironment.setCurrentRound(10);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getShopItems().stream().anyMatch(item -> item instanceof MedicalSupplyDrop));

        gameEnvironment.setCurrentRound(11);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getTowersInShop().stream().anyMatch(tower -> tower instanceof MedicOutpost));

        gameEnvironment.setCurrentRound(12);
        gameEnvironment.updateShopPostRound();
        assertTrue(gameEnvironment.getTowersInShop().stream().anyMatch(tower -> tower instanceof SpecialForcesCamp));

    }

    @Test
    public void testGetRoundWinBonus() {

        gameEnvironment.setCurrentRound(1);
        int bonus = gameEnvironment.getRoundWinBonus();
        assertEquals(200, bonus);
        assertEquals(1000, gameEnvironment.getCurrentBalance());

        gameEnvironment.setCurrentRound(7);
        bonus = gameEnvironment.getRoundWinBonus();
        assertEquals(320, bonus);
        assertEquals(1320, gameEnvironment.getCurrentBalance());

        gameEnvironment.setCurrentRound(14);
        bonus = gameEnvironment.getRoundWinBonus();
        assertEquals(460, bonus);
        assertEquals(1780, gameEnvironment.getCurrentBalance());

    }

    @Test
    public void testLevelUpTowers() {
        Tower armoury = new Armoury();
        Tower barracks = new Barracks();
        Tower medicalTent = new MedicalTent();

        gameEnvironment.getMainTowers().addAll(List.of(armoury, barracks, medicalTent));

        gameEnvironment.setRoundMode("Rescue Operation");

        gameEnvironment.levelUpTowers();

        assertEquals(2, armoury.getLevel());
        assertEquals(2, barracks.getLevel());
        assertEquals(3, medicalTent.getLevel());
    }
}
