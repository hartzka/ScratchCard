package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void palauttaaSaldon() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(2000);
        assertEquals("saldo: 20.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenVahentaaSaldoaOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }

    @Test
    public void rahanOttaminenEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanOikeaOttaminenPalauttaaTrue() {
        assertEquals(kortti.otaRahaa(5), true);
    }

    @Test
    public void rahanLiikaOttaminenPalauttaaFalse() {
        assertEquals(kortti.otaRahaa(11), false);
    }
}
