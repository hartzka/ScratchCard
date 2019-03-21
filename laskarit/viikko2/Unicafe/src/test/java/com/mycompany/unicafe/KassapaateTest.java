
package com.mycompany.unicafe;

import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    Kassapaate kassapaate;
    Random random;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
        random = new Random();
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
    
    @Test
    public void syoEdullisestiKateisellaKunMaksuRiittavaKassanRahamaaraKasvaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateisellaKunMaksuRiittavaKassanRahamaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKateisellaKunMaksuRiittavaVaihtorahaOikea() {
        assertEquals(kassapaate.syoEdullisesti(250), 10);
    }
    
    @Test
    public void syoMaukkaastiKateisellaKunMaksuRiittavaVaihtorahaOikea() {
        assertEquals(kassapaate.syoMaukkaasti(420), 20);
    }
    
    @Test
    public void syoEdullisestiKateisellaKunMaksuRiittavaEdullistenLounaidenMaaraKasvaa() {
        int n = random.nextInt(10)+1;
        for(int i = 0; i<n; i++) {
            kassapaate.syoEdullisesti(240);
        }
        assertEquals(n, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKateisellaKunMaksuRiittavaMaukkaidenLounaidenMaaraKasvaa() {
        int n = random.nextInt(10)+1;
        for(int i = 0; i<n; i++) {
            kassapaate.syoMaukkaasti(400);
        }
        assertEquals(n, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void syoEdullisestiKortillaKunMaksuRiittavaKortinSaldoOikein() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuRiittavaKortinSaldoOikein() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaKunMaksuRiittavaPalauttaaTrue() {
        assertEquals(kassapaate.syoMaukkaasti(kortti), true);
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuRiittavaPalauttaaTrue() {
        assertEquals(kassapaate.syoMaukkaasti(kortti), true);
    }
    
    @Test
    public void syoEdullisestiKortillaKunMaksuRiittavaEdullistenLounaidenMaaraKasvaa() {
        kortti.lataaRahaa(10000);
        int n = random.nextInt(10)+1;
        for(int i = 0; i<n; i++) {
            kassapaate.syoEdullisesti(kortti);
        }
        assertEquals(n, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuRiittavaMaukkaidenLounaidenMaaraKasvaa() {
        kortti.lataaRahaa(10000);
        int n = random.nextInt(10)+1;
        for(int i = 0; i<n; i++) {
            kassapaate.syoMaukkaasti(kortti);
        }
        assertEquals(n, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
     @Test
    public void syoEdullisestiKateisellaKunMaksuEiRiittavaKassanRahamaaraEiKasva() {
        kassapaate.syoEdullisesti(230);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateisellaKunMaksuEiRiittavaKassanRahamaaraEiKasva() {
        kassapaate.syoMaukkaasti(380);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKateisellaKunMaksuEiRiittavaVaihtorahaOikea() {
        assertEquals(kassapaate.syoEdullisesti(220), 220);
    }
    
    @Test
    public void syoMaukkaastiKateisellaKunMaksueiRiittavaVaihtorahaOikea() {
        assertEquals(kassapaate.syoMaukkaasti(300), 300);
    }
    
    @Test
    public void syoEdullisestiKateisellaKunMaksuEiRiittavaEdullistenLounaidenMaaraEiKasva() {
        kassapaate.syoEdullisesti(20);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKateisellaKunMaksuEiRiittavaMaukkaidenLounaidenMaaraEiKasva() {
        kassapaate.syoMaukkaasti(50);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void syoEdullisestiKortillaKunMaksuEiRiittavaKortinSaldoOikein() {
        kortti.otaRahaa(900);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuEiRiittavaKortinSaldoOikein() {
        kortti.otaRahaa(700);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaKunMaksuEiRiittavaPalauttaaFalse() {
        kortti.otaRahaa(800);
        assertEquals(kassapaate.syoMaukkaasti(kortti), false);
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuEiRiittavaPalauttaaFalse() {
        kortti.otaRahaa(610);
        assertEquals(kassapaate.syoMaukkaasti(kortti), false);
    }
    
    @Test
    public void syoEdullisestiKortillaKunMaksuEiRiittavaEdullistenLounaidenMaaraEiKasva() {
        kortti.otaRahaa(900);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuEiRiittavaMaukkaidenLounaidenMaaraEiKasva() {
        kortti.otaRahaa(800);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaKunMaksuRiittavaKassanRahamaaraEiKasva() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKortillaKunMaksuRiittavaKassanRahamaaraEiKasva() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void rahanLataaminenKortilleMuuttaaKortinSaldoa() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKortilleKasvattaaKassanRahamaaraa() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void negatiivisenRahanLataaminenKortilleEiMuutaKortinSaldoa() {
        kassapaate.lataaRahaaKortille(kortti, -1);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void negatiivisenRahanLataaminenKortilleEiKasvataKassanRahamaaraa() {
        kassapaate.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
