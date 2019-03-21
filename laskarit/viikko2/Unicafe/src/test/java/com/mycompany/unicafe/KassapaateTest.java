
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kassapaate!=null);      
    }
    
    @Test
    public void palauttaaSaldon() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void palauttaaLounaidenMaaran() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty());
    }
}
