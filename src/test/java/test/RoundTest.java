package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Round;
import seng201.team0.models.towers.Armoury;
import seng201.team0.models.towers.Barracks;
import seng201.team0.models.towers.MedicalTent;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RoundTest {
    private GameEnvironment gameEnvironment;
    private Round ammunitionRound;
    private Round medkitRound;
    private Round troopRound;
    private Tower medkitTower;
    private Tower ammunitionTower;
    private Tower troopTower;


    @BeforeEach
    public void init() {
        gameEnvironment = new GameEnvironment(gameEnvironment1 -> {}, gameEnvironment2 -> {},
                gameEnvironment3 -> {}, gameEnvironment4 -> {}, gameEnvironment5 -> {}, gameEnvironment6 -> {},
                () -> {});

        medkitTower = new MedicalTent();
        ammunitionTower = new Armoury();
        troopTower = new Barracks();

        gameEnvironment.getMainTowers().add(medkitTower);
        gameEnvironment.getMainTowers().add(troopTower);
        gameEnvironment.getMainTowers().add(ammunitionTower);

        ammunitionRound = new Round(1, "Recruit", "Artillery Barrage", gameEnvironment);
        medkitRound = new Round(1, "Recruit", "Ground Offensive", gameEnvironment);
        troopRound = new Round(1, "Recruit", "Rescue Operation", gameEnvironment);

    }

    @Test
    public void testInitialization() {
        assertNotNull(ammunitionRound);
        assertNotNull(medkitRound);
        assertNotNull(troopRound);
    }

    @Test
    public void testConfigureRound() {

        ArrayList<Integer> numCartsAmmunition = ammunitionRound.getNumCarts();
        ArrayList<Integer> numCartsTroops = troopRound.getNumCarts();
        ArrayList<Integer> numCartsMedkits = medkitRound.getNumCarts();

        assertEquals(2, numCartsAmmunition.get(0));
        assertEquals(1, numCartsAmmunition.get(1));
        assertEquals(1, numCartsAmmunition.get(2));

        assertEquals(1, numCartsTroops.get(0));
        assertEquals(2, numCartsTroops.get(1));
        assertEquals(1, numCartsTroops.get(2));

        assertEquals(1, numCartsMedkits.get(0));
        assertEquals(1, numCartsMedkits.get(1));
        assertEquals(2, numCartsMedkits.get(2));



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
        assertEquals(originalAmmunition + ammunitionTower.getResourceAmount(), ammunitionRound.getMedKitsCollected());
    }




}
