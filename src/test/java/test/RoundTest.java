package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.gui.AlertHandler;
import seng201.team0.models.Round;
import seng201.team0.models.carts.Cart;
import seng201.team0.models.towers.gametowers.Armoury;
import seng201.team0.models.towers.gametowers.Barracks;
import seng201.team0.models.towers.gametowers.MedicalTent;
import seng201.team0.models.towers.Tower;

import static org.mockito.Mockito.*;

import java.util.Random;

public class RoundTest {
    private Round ammunitionRound;
    private Round medkitRound;
    private Round troopRound;
    private Tower medkitTower;
    private Tower ammunitionTower;
    private Tower troopTower;

    @BeforeEach
    public void init() {

        AlertHandler alertHandler = mock(AlertHandler.class);

        GameEnvironment gameEnvironment = new GameEnvironment(gameEnvironment1 -> {}, gameEnvironment2 -> {},
                gameEnvironment3 -> {}, gameEnvironment4 -> {}, gameEnvironment5 -> {}, gameEnvironment6 -> {},
                () -> {}, alertHandler, new Random());

        medkitTower = new MedicalTent();
        ammunitionTower = new Armoury();
        troopTower = new Barracks();

        gameEnvironment.getMainTowers().add(medkitTower);
        gameEnvironment.getMainTowers().add(troopTower);
        gameEnvironment.getMainTowers().add(ammunitionTower);

        ammunitionRound = new Round(1,  "Artillery Barrage", gameEnvironment);
        medkitRound = new Round(1,  "Ground Offensive", gameEnvironment);
        troopRound = new Round(1,  "Rescue Operation", gameEnvironment);

    }

    @Test
    public void testInitialization() {
        assertNotNull(ammunitionRound);
        assertNotNull(medkitRound);
        assertNotNull(troopRound);
    }

    @Test
    public void testCreateCarts() {

        assertEquals(2, ammunitionRound.getAmmunitionCarts().size());
        assertEquals(1, ammunitionRound.getMedkitCarts().size());
        assertEquals(1, ammunitionRound.getTroopCarts().size());

        assertEquals(1, troopRound.getAmmunitionCarts().size());
        assertEquals(2, troopRound.getMedkitCarts().size());
        assertEquals(1, troopRound.getTroopCarts().size());

        assertEquals(1, medkitRound.getAmmunitionCarts().size());
        assertEquals(1, medkitRound.getMedkitCarts().size());
        assertEquals(2, medkitRound.getTroopCarts().size());
    }

    @Test
    public void testResourcesRequired() {

        int expectedAmmunitionRequired = ammunitionRound.getAmmunitionCarts().stream().mapToInt(Cart::getSize).sum();
        int expectedMedkitsRequired = ammunitionRound.getMedkitCarts().stream().mapToInt(Cart::getSize).sum();
        int expectedTroopsRequired = ammunitionRound.getTroopCarts().stream().mapToInt(Cart::getSize).sum();

        assertEquals(expectedAmmunitionRequired, ammunitionRound.getAmmunitionRequired());
        assertEquals(expectedMedkitsRequired, ammunitionRound.getMedKitsRequired());
        assertEquals(expectedTroopsRequired, ammunitionRound.getTroopsRequired());
    }

    @Test
    public void testIncreaseMedkitsCollected() {
        int originalMedkits = medkitRound.getMedKitsCollected();
        medkitRound.increaseMedKitsCollected();
        assertEquals(originalMedkits + medkitTower.getResourceAmount(), medkitRound.getMedKitsCollected());
    }

    @Test
    public void testIncreaseTroopsCollected() {
        int originalTroops = troopRound.getTroopsCollected();
        troopRound.increaseMedKitsCollected();
        assertEquals(originalTroops + troopTower.getResourceAmount(), troopRound.getMedKitsCollected());
    }

    @Test
    public void testIncreaseAmmunitionCollected() {
        int originalAmmunition = ammunitionRound.getAmmunitionCollected();
        ammunitionRound.increaseMedKitsCollected();
        assertEquals(originalAmmunition + ammunitionTower.getResourceAmount(),
                ammunitionRound.getMedKitsCollected());
    }

}
