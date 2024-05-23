package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.towers.*;

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
    }


}
