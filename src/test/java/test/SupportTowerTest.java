package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.towers.*;
import seng201.team0.models.towers.gametowers.Armoury;
import seng201.team0.models.towers.gametowers.Barracks;
import seng201.team0.models.towers.gametowers.MedicalTent;
import seng201.team0.models.towers.gametowers.SpecialForcesCamp;
import seng201.team0.models.towers.supporttowers.AmmoRelayStation;
import seng201.team0.models.towers.supporttowers.MedicOutpost;
import seng201.team0.models.towers.supporttowers.TroopCommandPost;

public class SupportTowerTest {

    private SupportTower ammoRelayStation;
    private SupportTower troopCommandPost;
    private SupportTower medicOutpost;
    private Tower armoury;
    private Tower barracks;
    private Tower medicalTent;
    private Tower specialForcesCamp;

    @BeforeEach
    public void init() {
        ammoRelayStation = new AmmoRelayStation();
        troopCommandPost = new TroopCommandPost();
        medicOutpost = new MedicOutpost();
        armoury = new Armoury();
        barracks = new Barracks();
        medicalTent = new MedicalTent();
        specialForcesCamp = new SpecialForcesCamp();
    }

    @Test
    public void testInitialization() {
        assertNotNull(ammoRelayStation);
        assertNotNull(troopCommandPost);
        assertNotNull(medicOutpost);
    }

    @Test
    public void testSellPrice() {
        assertEquals(ammoRelayStation.getCost() / 2, ammoRelayStation.getSellPrice());
        assertEquals(troopCommandPost.getCost() / 2, troopCommandPost.getSellPrice());
        assertEquals(medicOutpost.getCost() / 2, medicOutpost.getSellPrice());
    }

    @Test
    public void testBoostReloadSpeed() {

        double originalReloadSpeed = armoury.getReloadSpeed();
        ammoRelayStation.boostReloadSpeed(armoury);
        assertEquals(originalReloadSpeed - 0.3, armoury.getReloadSpeed());

        originalReloadSpeed = barracks.getReloadSpeed();
        troopCommandPost.boostReloadSpeed(barracks);
        assertEquals(originalReloadSpeed - 0.3, barracks.getReloadSpeed());

        originalReloadSpeed = medicalTent.getReloadSpeed();
        medicOutpost.boostReloadSpeed(medicalTent);
        assertEquals(originalReloadSpeed - 0.3, medicalTent.getReloadSpeed());


        specialForcesCamp.setLevel(4);

        originalReloadSpeed = specialForcesCamp.getReloadSpeed();

        troopCommandPost.boostReloadSpeed(specialForcesCamp);
        assertTrue(originalReloadSpeed > specialForcesCamp.getReloadSpeed());


    }

    @Test
    public void testRemoveReloadSpeed() {

        ammoRelayStation.boostReloadSpeed(armoury);
        double boostedReloadSpeed = armoury.getReloadSpeed();
        ammoRelayStation.removeReloadSpeedBoost(armoury);
        assertEquals(boostedReloadSpeed + 0.3, armoury.getReloadSpeed());

        troopCommandPost.boostReloadSpeed(barracks);
        boostedReloadSpeed = barracks.getReloadSpeed();
        troopCommandPost.removeReloadSpeedBoost(barracks);
        assertEquals(boostedReloadSpeed + 0.3, barracks.getReloadSpeed());

        medicOutpost.boostReloadSpeed(medicalTent);
        boostedReloadSpeed = medicalTent.getReloadSpeed();
        medicOutpost.removeReloadSpeedBoost(medicalTent);
        assertEquals(boostedReloadSpeed + 0.3, medicalTent.getReloadSpeed());

    }
}
