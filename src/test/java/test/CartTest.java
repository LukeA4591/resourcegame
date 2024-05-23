package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.carts.AmmunitionCart;
import seng201.team0.models.carts.Cart;
import seng201.team0.models.carts.MedkitCart;
import seng201.team0.models.carts.TroopCart;

public class CartTest {

    private Cart ammunitionCart;
    private Cart medkitCart;
    private Cart troopCart;

    @BeforeEach
    public void init() {
        ammunitionCart = new AmmunitionCart(50, 50);
        medkitCart = new MedkitCart(50, 50);
        troopCart = new TroopCart(50, 50);
    }

    @Test
    public void testInitialization() {
        assertNotNull(ammunitionCart);
        assertNotNull(medkitCart);
        assertNotNull(troopCart);
    }
}
