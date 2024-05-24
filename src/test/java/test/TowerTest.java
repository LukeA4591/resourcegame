package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.towers.*;
import seng201.team0.models.towers.gametowers.*;

public class TowerTest {

    private Tower armoury;
    private Tower arsenal;
    private Tower barracks;
    private Tower garrison;
    private Tower medicalTent;
    private Tower medbay;
    private Tower eaglesNest;
    private Tower specialForcesCamp;
    private Tower fieldHospital;


    @BeforeEach
    public void init() {
        armoury = new Armoury();
        arsenal = new Arsenal();
        barracks = new Barracks();
        garrison = new Garrison();
        medicalTent = new MedicalTent();
        medbay = new Medbay();
        eaglesNest = new EaglesNest();
        specialForcesCamp = new SpecialForcesCamp();
        fieldHospital = new FieldHospital();
    }

    @Test
    public void testInitialization() {
        assertNotNull(armoury);
        assertNotNull(arsenal);
        assertNotNull(barracks);
        assertNotNull(garrison);
        assertNotNull(medicalTent);
        assertNotNull(medbay);
        assertNotNull(eaglesNest);
        assertNotNull(specialForcesCamp);
        assertNotNull(fieldHospital);
    }

    @Test
    public void testSellPrice() {
        assertEquals(armoury.getCost() / 2, armoury.getSellPrice());
        assertEquals(arsenal.getCost() / 2, arsenal.getSellPrice());
        assertEquals(barracks.getCost() / 2, barracks.getSellPrice());
        assertEquals(garrison.getCost() / 2, garrison.getSellPrice());
        assertEquals(medbay.getCost() / 2, medbay.getSellPrice());
        assertEquals(medicalTent.getCost() / 2, medicalTent.getSellPrice());
        assertEquals(eaglesNest.getCost() / 2, eaglesNest.getSellPrice());
        assertEquals(specialForcesCamp.getCost() / 2, specialForcesCamp.getSellPrice());
        assertEquals(fieldHospital.getCost() / 2, fieldHospital.getSellPrice());
    }

    @Test
    public void testCommunicationsBreakDown() {
        double originalReloadSpeed = barracks.getReloadSpeed();
        barracks.communicationsBreakdown();
        assertEquals(originalReloadSpeed * 1.2, barracks.getReloadSpeed());
    }

    @Test
    public void testMedicalSupplyLineSabotage() {
        double originalReloadSpeed = medbay.getReloadSpeed();
        medbay.communicationsBreakdown();
        assertEquals(originalReloadSpeed * 1.2, medbay.getReloadSpeed());
    }

    @Test
    public void testEnemyAmbush() {
        double originalReloadSpeed = armoury.getReloadSpeed();
        armoury.communicationsBreakdown();
        assertEquals(originalReloadSpeed * 1.2, armoury.getReloadSpeed());
    }

    @Test
    public void testBreakAndRepair() {

        String originalName = armoury.getName();
        armoury.breakTower();
        assertTrue(armoury.isBroken());
        assertEquals(armoury.getName(), "Broken " + originalName);
        armoury.repairTower();
        assertFalse(armoury.isBroken());
        assertEquals(originalName, armoury.getName());
    }

    @Test
    public void testLeveling() {

        double originalReloadSpeed = armoury.getReloadSpeed();
        double originalResourceAmount = armoury.getResourceAmount();

        armoury.levelUp();

        assertEquals("Level: " + 2, armoury.getLevelRepresentation());
        assertEquals(2, armoury.getLevel());
        assertEquals(originalReloadSpeed - 0.05, armoury.getReloadSpeed());
        assertEquals(originalResourceAmount + 2, armoury.getResourceAmount());

        armoury.setLevel(100);

        assertEquals("MAX LEVEL", armoury.getLevelRepresentation());
        assertEquals(6, armoury.getLevel());


        armoury.setReloadSpeed(0.5);

        assertEquals(1, armoury.getReloadSpeed());
    }

}
