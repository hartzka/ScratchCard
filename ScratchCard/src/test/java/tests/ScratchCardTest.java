
package tests;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import kh.scratchcard.domain.Hand;
import kh.scratchcard.domain.ScratchCard;
import kh.scratchcard.domain.WinCategory;
import kh.scratchcard.ui.Ui;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ScratchCardTest {
    ScratchCard sc;
    Ui ui;
    Field field1;
    Field field2;
    Field field3;
    Field field4;
    Field field5;
    Field field6;
    Field field7;
    Field field8;
    Field field9;
    
    @Before
    public void setUp() {
        sc = new ScratchCard();
    }
    
    @Test
    public void cardExists() {
        assertTrue(sc != null);
    }
    
    @Test
    public void checkOpenedWorks() {
        Ui ui = sc.getUi();
        ui.getField(1).setRevealed(true);
        ui.getField(2).setRevealed(true);
        ui.getField(3).setRevealed(true);
        ui.getField(4).setRevealed(true);
        ui.getField(5).setRevealed(true);
        ui.getField(6).setRevealed(true);
        ui.getField(7).setRevealed(true);
        ui.getField(8).setRevealed(true);
        ui.getField(9).setRevealed(true);
        sc.setHand(new Hand(sc, ui.getField(1), ui.getField(2), ui.getField(3), ui.getField(4), ui.getField(5), ui.getField(6), ui.getField(7), ui.getField(8), ui.getField(9)));
        sc.checkOpened();
 
        assertTrue(sc.getProgress() == false);
        assertTrue(sc.getPlay() == true);
    }
    
    @Test
    public void checkWinsWorksWithSmallWins() {
        List<WinCategory> list = new ArrayList<>();
        list.add(WinCategory.X3ORANGE);
        list.add(WinCategory.X1PINEAPPLE);
        list.add(WinCategory.X3STRAWBERRY);
        sc.checkWins(list);
        
        assertTrue(sc.getRoundWin() == 9);
        assertTrue(sc.getRoundWinStart() == 9);
        assertFalse(sc.getUi().getWinText().getValue().contains("Unbelievable!"));
    }
    
    @Test
    public void checkWinsWorksWithLargeWins() {
        List<WinCategory> list = new ArrayList<>();
        list.add(WinCategory.X3GRAPES);
        list.add(WinCategory.X3PEAR);
        list.add(WinCategory.X3PINEAPPLE);
        sc.checkWins(list);
        
        assertTrue(sc.getRoundWin() == 55500);
        assertTrue(sc.getRoundWinStart() == 55500);
        assertTrue(sc.getUi().getWinText().getValue().contains("Unbelievable!"));
    }
    
    @Test
    public void doubleWinIsHandled() {
        sc.setDoubleImage(1);
        sc.setDoubleOption1(true);
        sc.setRoundWin(10);
        sc.handleDoubleWin();
        assertTrue(sc.getRoundWin() == 20);
    }
    
    @Test
    public void doubleWinIsHandled2() {
        sc.setDoubleImage(2);
        sc.setDoubleOption2(true);
        sc.setRoundWin(10);
        sc.handleDoubleWin();
        assertTrue(sc.getRoundWin() == 30);
    }
    
    @Test
    public void doubleWinIsHandled3() {
        sc.setDoubleImage(3);
        sc.setDoubleOption3(true);
        sc.setRoundWin(10);
        sc.handleDoubleWin();
        assertTrue(sc.getRoundWin() == 70);
    }
    
    @Test
    public void doubleWinIsHandled4() {
        sc.setDoubleImage(4);
        sc.setDoubleOption4(true);
        sc.setRoundWin(10);
        sc.handleDoubleWin();
        assertTrue(sc.getRoundWin() == 300);
    }
    
    @Test
    public void doubleWinIsNotHandledWhenItShouldNotBe() {
        sc.setDoubleImage(1);
        sc.setDoubleOption3(true);
        sc.setRoundWin(10);
        sc.handleDoubleWin();
        assertTrue(sc.getRoundWin() == 0);
    }
    
    @Test
    public void doubleFieldOpenedWorks() {
        sc.doubleFieldOpened();
        assertTrue(sc.getDoubleLocked() == true);
        assertTrue(sc.getDoubleVisible() == false);
        assertTrue(sc.getProgress() == false);
    }
    
    @Test
    public void doubleWinsAreUpdated() {
        sc.setRoundWin(2);
        sc.updateDoubleWins();
        assertTrue(sc.getRoundWinX2() == 4);
        assertTrue(sc.getRoundWinX3() == 6);
        assertTrue(sc.getRoundWinX7() == 14);
        assertTrue(sc.getRoundWinX30() == 60);
    }
    
    @Test
    public void doubleOptionsAreSetToFalse() {
        sc.setDoubleOptionsFalse();
        assertTrue(sc.getDoubleOption1() == false);
        assertTrue(sc.getDoubleOption2() == false);
        assertTrue(sc.getDoubleOption3() == false);
        assertTrue(sc.getDoubleOption4() == false);
    }
}
