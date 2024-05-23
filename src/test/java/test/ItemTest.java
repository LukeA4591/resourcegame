package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.items.*;

public class ItemTest {

    private Item ammoCrate;
    private Item ammunitionTowerRepairKit;
    private Item medicalSupplyDrop;
    private Item medkitTowerRepairKit;
    private Item paratroopers;
    private Item troopTowerRepairKit;

    @BeforeEach
    public void init() {
        ammoCrate = new AmmoCrate();
        ammunitionTowerRepairKit = new AmmunitionTowerRepairKit();
        medicalSupplyDrop = new MedicalSupplyDrop();
        medkitTowerRepairKit = new MedkitTowerRepairKit();
        paratroopers = new Paratroopers();
        troopTowerRepairKit = new TroopTowerRepairKit();
    }

    @Test
    public void testInitialization() {
        assertNotNull(ammoCrate);
        assertNotNull(ammunitionTowerRepairKit);
        assertNotNull(medicalSupplyDrop);
        assertNotNull(medkitTowerRepairKit);
        assertNotNull(paratroopers);
        assertNotNull(troopTowerRepairKit);
    }

    @Test
    public void isRepairKit() {
        assertTrue(ammunitionTowerRepairKit.getIsRepairKit());
        assertTrue(medkitTowerRepairKit.getIsRepairKit());
        assertTrue(troopTowerRepairKit.getIsRepairKit());
        assertFalse(ammoCrate.getIsRepairKit());
        assertFalse(paratroopers.getIsRepairKit());
        assertFalse(medicalSupplyDrop.getIsRepairKit());
    }



}
