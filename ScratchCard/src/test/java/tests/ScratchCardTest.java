
package tests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kh.scratchcard.domain.Hand;
import kh.scratchcard.domain.ScratchCard;
import kh.scratchcard.domain.WinCategory;
import kh.scratchcard.ui.Field;
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
    public void setUp() throws SQLException {
        sc = new ScratchCard();
        field1 = new Field(sc, 0, 0, 0, 0);
        field2 = new Field(sc, 0, 0, 0, 0);
        field3 = new Field(sc, 0, 0, 0, 0);
        field4 = new Field(sc, 0, 0, 0, 0);
        field5 = new Field(sc, 0, 0, 0, 0);
        field6 = new Field(sc, 0, 0, 0, 0);
        field7 = new Field(sc, 0, 0, 0, 0);
        field8 = new Field(sc, 0, 0, 0, 0);
        field9 = new Field(sc, 0, 0, 0, 0);
    }
    
    @Test
    public void cardExists() {
        assertTrue(sc != null);
    }
    
    @Test
    public void checkOpenedWorksNoWins() {
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
    public void checkOpenedWorksWins() {
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
        sc.getHand().setWin1(WinCategory.X2MELON);
        sc.checkOpened();
        assertTrue(sc.getRoundWin() == 6);
        assertTrue(ui.getWinningCards() == 1);
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
    public void doubleFieldOpenedWorksWithLargeRoundWin() {
        sc.setRoundWin(50000);
        sc.doubleFieldOpened();
        sc.getUi().setUnbelievableText();
        assertTrue(sc.getUi().getWinText().get().contains("Unbelievable!"));
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
    
    @Test
    public void setDoubleLockedWorks() {
        sc.setDoubleLocked();
        assertTrue(sc.getDoubleLocked() == true);
        assertTrue(sc.getProgress() == true);
    }
    
    @Test
    public void handleRoundWinWorks() {
        sc.setRoundWin(10);
        sc.setRoundWinStart(5);
        sc.handleRoundWin();
        assertTrue(sc.getMoneyTotal().get() == 10);
        assertTrue(sc.getMoneySession().get() == 10);
        assertTrue(sc.getDoubleBestMultiplier() == 2);
        assertTrue(sc.getRoundWin() == 0);
    }
    
    @Test
    public void doubleImageIsInitializedCorrectly() {
        Map<Integer, Integer> images = new HashMap<>();
        images.put(1, 0);
        images.put(2, 0);
        images.put(3, 0);
        images.put(4, 0);
        for (int i = 0; i<636; i++) {
            sc.randomizeDoubleImage();
            int image = sc.getDoubleImage();
            images.put(image, images.get(image) + 1);
        }
        assertTrue(images.get(4) < 35);
        assertTrue(images.get(3) < 130);
        assertTrue(images.get(2) < 300);
        assertTrue(images.get(1) < 400);
    }
    
    @Test
    public void newCardIsHandled() throws SQLException {
        Hand hand = new Hand(sc, field1, field2, field3, field4, field5, field6, field7, field8, field9);
        sc.setHand(hand);
        sc.handleNewCard();
        assertTrue(sc.getUi().getWinText().get().contains("Win"));
        assertTrue(sc.getProgress() == true);
        assertTrue(sc.getPlay() == false);
    }
    
    @Test
    public void statsAreSetUp() throws SQLException {
        assertTrue(sc.getData() == null);
        sc.setUpStats();
        assertFalse(sc.getData() == null);
    }
    
    @Test
    public void dataIsSaved() throws SQLException {
        sc.setTest(true);
        sc.setRoundWinStart(2);
        sc.setRoundWin(4);
        sc.save();
        assertTrue(sc.getMoneyTotal().get() == 4);
        assertTrue(sc.getDoubleBestMultiplier() == 2);
        assertTrue(sc.getData().getMoneyTotal() == 4);
        assertTrue(sc.getData().getDoubleUpWins() == 2);
    }
    
    @Test
    public void dataIsSavedWhenDoubleLocked() throws SQLException {
        sc.setTest(true);
        sc.setRoundWinStart(2);
        sc.setRoundWin(2);
        sc.setDoubleLocked();
        sc.save();
        assertTrue(sc.getUi().getDoubleUpLosses().get() == 2);
    }
}
