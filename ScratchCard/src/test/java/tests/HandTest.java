package tests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kh.scratchcard.domain.Hand;
import kh.scratchcard.domain.WinCategory;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class HandTest {

    Hand hand;

    @Before
    public void setUp() throws SQLException {
        hand = new Hand();
    }

    @Test
    public void handExists() {
        assertTrue(hand != null);
    }

    @Test
    public void randomizeWinCategoryWithWinWorks() {
        HashMap<WinCategory, Integer> h = new HashMap<>();
        h.put(WinCategory.X3GRAPES, 0);
        for (int i = 0; i < 2840911; i++) {
            WinCategory wc = hand.randomizeWinCategory(true);
            h.put(wc, h.getOrDefault(wc, 0) + 1);
        }
        assertTrue(h.get(WinCategory.X3ORANGE) > 900000);
        assertTrue(h.get(WinCategory.X3ORANGE) < 1100000);

        assertTrue(h.get(WinCategory.X1PINEAPPLE) > 750000);
        assertTrue(h.get(WinCategory.X1PINEAPPLE) < 950000);

        assertTrue(h.get(WinCategory.X3STRAWBERRY) > 400000);
        assertTrue(h.get(WinCategory.X3STRAWBERRY) < 600000);

        assertTrue(h.get(WinCategory.X3PLUM) > 150000);
        assertTrue(h.get(WinCategory.X3PLUM) < 350000);

        assertTrue(h.get(WinCategory.X2MELON) > 50000);
        assertTrue(h.get(WinCategory.X2MELON) < 150000);

        assertTrue(h.get(WinCategory.X3CHERRY) > 40000);
        assertTrue(h.get(WinCategory.X3CHERRY) < 100000);

        assertTrue(h.get(WinCategory.X1BANANA) > 20000);
        assertTrue(h.get(WinCategory.X1BANANA) < 80000);

        assertTrue(h.get(WinCategory.X2PINEAPPLE) > 5000);
        assertTrue(h.get(WinCategory.X2PINEAPPLE) < 15000);

        assertTrue(h.get(WinCategory.X3MELON) > 4800);
        assertTrue(h.get(WinCategory.X3MELON) < 14800);

        assertTrue(h.get(WinCategory.X3BANANA) > 400);
        assertTrue(h.get(WinCategory.X3BANANA) < 1600);

        assertTrue(h.get(WinCategory.X3PINEAPPLE) > 30);
        assertTrue(h.get(WinCategory.X3PINEAPPLE) < 170);

        assertTrue(h.get(WinCategory.X3PEAR) > 1);
        assertTrue(h.get(WinCategory.X3PEAR) < 25);

        assertTrue(h.get(WinCategory.X3GRAPES) < 10);
    }

    @Test
    public void randomizeWinCategorWorks() {
        HashMap<WinCategory, Integer> h = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            WinCategory wc = hand.randomizeWinCategory(false);
            if (wc == WinCategory.NOTHING) {
                h.put(wc, h.getOrDefault(wc, 0) + 1);
            }
        }
        assertTrue(h.get(WinCategory.NOTHING) > 665909);
        assertTrue(h.get(WinCategory.NOTHING) < 765909);
    }

    @Test
    public void winsAreInitialized() {
        hand.initializeWins();
        assertTrue(hand.getWin1() == WinCategory.NOTHING);
        assertTrue(hand.getWin2() == WinCategory.NOTHING);
        assertTrue(hand.getWin3() == WinCategory.NOTHING);
        assertTrue(hand.getFieldWin1() == false);
        assertTrue(hand.getFieldWin5() == false);
        assertTrue(hand.getFieldWin9() == false);
    }

    @Test
    public void handIsRandomized() throws SQLException {
        boolean fieldWin1 = false;
        boolean fieldWin2 = false;
        boolean fieldWin3 = false;
        boolean fieldWin4 = false;
        boolean fieldWin5 = false;
        boolean fieldWin6 = false;
        boolean fieldWin7 = false;
        boolean fieldWin8 = false;
        boolean fieldWin9 = false;
        boolean win1 = false;
        boolean win2 = false;
        boolean win3 = false;
        
        for (int i = 0; i < 100000; i++) {
            hand.initialize();
            hand.randomizeHand();
            if (hand.getWin1() != WinCategory.NOTHING) {
                win1 = true;
            }
            if (hand.getWin2() != WinCategory.NOTHING) {
                win2 = true;
            }
            if (hand.getWin3() != WinCategory.NOTHING) {
                win3 = true;
            }
            if (hand.getFieldWin1() == true) {
                fieldWin1 = true;
            }
            if (hand.getFieldWin2() == true) {
                fieldWin2 = true;
            }
            if (hand.getFieldWin3() == true) {
                fieldWin3 = true;
            }
            if (hand.getFieldWin4() == true) {
                fieldWin4 = true;
            }
            if (hand.getFieldWin5() == true) {
                fieldWin5 = true;
            }
            if (hand.getFieldWin6() == true) {
                fieldWin6 = true;
            }
            if (hand.getFieldWin7() == true) {
                fieldWin7 = true;
            }
            if (hand.getFieldWin8() == true) {
                fieldWin8 = true;
            }
            if (hand.getFieldWin9() == true) {
                fieldWin9 = true;
            }
        }

        assertTrue(fieldWin1 == true);
        assertTrue(fieldWin2 == true);
        assertTrue(fieldWin3 == true);
        assertTrue(fieldWin4 == true);
        assertTrue(fieldWin5 == true);
        assertTrue(fieldWin6 == true);
        assertTrue(fieldWin7 == true);
        assertTrue(fieldWin8 == true);
        assertTrue(fieldWin9 == true);
        assertTrue(win1 == true);
        assertTrue(win2 == true);
        assertTrue(win3 == true);
    }

    @Test
    public void testRevealWorking() {
        hand.setWin1(WinCategory.X3CHERRY);
        hand.setWin2(WinCategory.X1BANANA);
        hand.setWin3(WinCategory.X2MELON);

        List<WinCategory> l = hand.revealTest();
        assertTrue(l.contains(WinCategory.X1BANANA));
        assertTrue(l.contains(WinCategory.X3CHERRY));
        assertTrue(l.contains(WinCategory.X2MELON));
    }
    
    @Test
    public void revealWorking() {
        hand.setWin1(WinCategory.X3CHERRY);
        hand.setWin2(WinCategory.X1BANANA);
        hand.setWin3(WinCategory.X2MELON);

        List<WinCategory> l = hand.reveal();
        assertTrue(l.contains(WinCategory.X1BANANA));
        assertTrue(l.contains(WinCategory.X3CHERRY));
        assertTrue(l.contains(WinCategory.X2MELON));
    }

    @Test
    public void fieldWinsAreChecked() {
        hand.setField1Win(true);
        hand.setField2Win(true);
        hand.setField3Win(true);
        hand.setField4Win(true);
        hand.setField5Win(true);
        hand.setField6Win(true);
        hand.setField7Win(true);
        hand.setField8Win(true);
        hand.setField9Win(true);

        List<Integer> wins = new ArrayList<>();
        wins = hand.checkFieldWins1(wins);
        wins = hand.checkFieldWins2(wins);

        assertTrue(wins.contains(1));
        assertTrue(wins.contains(2));
        assertTrue(wins.contains(3));
        assertTrue(wins.contains(4));
        assertTrue(wins.contains(5));
        assertTrue(wins.contains(6));
        assertTrue(wins.contains(7));
        assertTrue(wins.contains(8));
        assertTrue(wins.contains(9));
    }

}
