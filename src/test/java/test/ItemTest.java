package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.items.*;
import seng201.team0.models.towers.*;

public class ItemTest {

    private Item ammoCrate;
    private Item ammunitionTowerRepairKit;
    private Item medicalSupplyDrop;
    private Item medkitsTowerRepairKit;
    private Item paratroopers;
    private Item troopsTowerRepairKit;

    @BeforeEach
    public void init() {
        ammoCrate = new AmmoCrate();
        ammunitionTowerRepairKit = new AmmunitionTowerRepairKit();
        medicalSupplyDrop = new MedicalSupplyDrop();
        medkitsTowerRepairKit = new MedkitsTowerRepairKit();
        paratroopers = new Paratroopers();
        troopsTowerRepairKit = new TroopsTowerRepairKit();
    }

    @Test
    public void testInitialization() {
        assertNotNull(ammoCrate);
        assertNotNull(ammunitionTowerRepairKit);
        assertNotNull(medicalSupplyDrop);
        assertNotNull(medkitsTowerRepairKit);
        assertNotNull(paratroopers);
        assertNotNull(troopsTowerRepairKit);
    }
}
