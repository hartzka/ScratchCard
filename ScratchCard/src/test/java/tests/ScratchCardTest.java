
package tests;

import kh.scratchcard.domain.ScratchCard;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ScratchCardTest {
    ScratchCard sc;
    
    @Before
    public void setUp() {
        sc = new ScratchCard();
    }
    
    @Test
    public void cardExists() {
        assertTrue(sc != null);
    }
}
