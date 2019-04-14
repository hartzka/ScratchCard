package kh.scratchcard.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kh.scratchcard.ui.Field;
import kh.scratchcard.ui.Ui;

public class ScratchCard extends Application {

    private Random random = new Random();
    private boolean tableVisible = true;

    public static int imageSize;
    public static int doubleImageSize;
    private Hand hand;
    private SimpleBooleanProperty play = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty progress = new SimpleBooleanProperty(false);

    SimpleIntegerProperty moneyTotal = new SimpleIntegerProperty(0);
    SimpleIntegerProperty moneySession = new SimpleIntegerProperty(0);
    boolean doubleVisible = false;
    boolean doubleLocked = false;

    boolean doubleOption1 = false;
    boolean doubleOption2 = false;
    boolean doubleOption3 = false;
    boolean doubleOption4 = false;
    int doubleImage;
    public static HashMap<String, Image> images;
    int doubleBestMultiplier = 0;

    private SimpleIntegerProperty roundWinStart = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWin = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx2 = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx3 = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx7 = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx30 = new SimpleIntegerProperty(0);
    private boolean statsVisible = false;

    private Ui ui;

    public ScratchCard() {
        images = new HashMap<>();
        ui = new Ui(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        imageSize = (int) (0.19 * 0.6 * Screen.getPrimary().getVisualBounds().getHeight());
        doubleImageSize = (int) (imageSize + (int) ((0.4) * (0.35 * Screen.getPrimary().getVisualBounds().getWidth() - (1.5 * 0.19 * Screen.getPrimary().getVisualBounds().getHeight()))) / 2);

        ui.start(primaryStage);
        ui.getWin().textProperty().bind(ui.getWinText().concat(roundWin.asString()).concat(".00"));
        ui.getNewButton().disableProperty().bind(progress);
        ui.getClaimButton().disableProperty().bind(play);
        hand = new Hand(this, ui.getField(1), ui.getField(2), ui.getField(3), ui.getField(4), ui.getField(5), ui.getField(6), ui.getField(7), ui.getField(8), ui.getField(9));
    }

    public void checkOpened() {
        if (ui.getField(1).getOpened() && ui.getField(2).getOpened() && ui.getField(3).getOpened() && ui.getField(4).getOpened() && ui.getField(5).getOpened() && ui.getField(6).getOpened() && ui.getField(7).getOpened() && ui.getField(8).getOpened() && ui.getField(9).getOpened()) {
            List<WinCategory> wins = hand.reveal();
            progress.set(false);
            play.set(true);
            if (!wins.isEmpty()) {
                checkWins(wins);
                ui.incrementWinningCards();
            }
        }
    }

    public void checkWins(List<WinCategory> wins) {
        int win = 0;
        for (WinCategory c : wins) {
            win += c.value;
            ui.addToTotalWins(c.value);
            ui.addToWinCategory(c);
        }
        roundWin.set(win);
        roundWinStart.set(win);
        ui.setWinVisible(true);
        if (win < 50000) {
            ui.setDoubleButtonDisable(false);
        } else {
            ui.setUnbelievableText();
            ui.fillWin(Color.AZURE);
        }
    }

    public void handleDoubleWin() {
        boolean win = true;
        if (doubleImage == 1 && doubleOption1) {
            roundWin.set(roundWin.get() * 2);
            ui.setWhiteRectsVisible(new int[]{1}, new boolean[]{false});
        } else if (doubleImage == 2 && doubleOption2) {
            roundWin.set(roundWin.get() * 3);
            ui.setWhiteRectsVisible(new int[]{2}, new boolean[]{false});
        } else if (doubleImage == 3 && doubleOption3) {
            roundWin.set(roundWin.get() * 7);
            ui.setWhiteRectsVisible(new int[]{3}, new boolean[]{false});
        } else if (doubleImage == 4 && doubleOption4) {
            roundWin.set(roundWin.get() * 30);
            ui.setWhiteRectsVisible(new int[]{4}, new boolean[]{false});
        } else {
            handleNoDoubleWin();
            win = false;
        }
        ui.updateMaxDoubleWin(win, roundWin);
    }

    private void handleNoDoubleWin() {
        SimpleIntegerProperty losses = ui.getDoubleUpLosses();
        ui.getDoubleUpLosses().set(losses.get() + roundWinStart.get());
        roundWin.set(0);
        roundWinStart.set(0);
        ui.setWinVisible(false);
        ui.hideDoubleChoices();
        ui.handleDoubleFieldOpened();
        doubleLocked = true;
    }

    public void doubleFieldOpened() {
        doubleLocked = false;
        handleDoubleWin();
        updateDoubleWins();

        if (roundWin.get() >= 50000) {
            ui.hideDoubleChoices();
            ui.handleDoubleFieldOpened();
            ui.setUnbelievableText();
            ui.fillWin(Color.AZURE);
        }
        doubleVisible = false;
        progress.set(false);
        setDoubleOptionsFalse();
    }

    public void setDoubleLocked() {
        doubleLocked = true;
        progress.set(true);
    }

    public void randomizeDoubleImage() {
        int a = random.nextInt(212) + 1;
        Field doubleField = ui.getDoubleField();
        if (a <= 105) {
            doubleImage = 1;
            doubleField.initialize(1);
        } else if (a <= 175) {
            doubleImage = 2;
            doubleField.initialize(2);
        } else if (a <= 205) {
            doubleImage = 3;
            doubleField.initialize(3);
        } else if (a <= 212) {
            doubleImage = 4;
            doubleField.initialize(4);
        }
    }

    public void updateDoubleWins() {
        roundWinx2.set(roundWin.get() * 2);
        roundWinx3.set(roundWin.get() * 3);
        roundWinx7.set(roundWin.get() * 7);
        roundWinx30.set(roundWin.get() * 30);
    }

    public void setDoubleLocked(boolean b) {
        this.doubleLocked = b;
    }

    public boolean getDoubleOption1() {
        return doubleOption1;
    }

    public Ui getUi() {
        return this.ui;
    }

    public SimpleIntegerProperty getMoneyTotal() {
        return this.moneyTotal;
    }

    public SimpleIntegerProperty getMoneySession() {
        return this.moneySession;
    }

    public void setDoubleOptionsFalse() {
        doubleOption1 = false;
        doubleOption2 = false;
        doubleOption3 = false;
        doubleOption4 = false;
    }

    public boolean getProgress() {
        return progress.get();
    }

    public void setHand(Hand h) {
        this.hand = h;
    }

    public boolean getPlay() {
        return play.get();
    }

    public boolean getDoubleLocked() {
        return this.doubleLocked;
    }

    public void setDoubleOption1(boolean b) {
        this.doubleOption1 = b;
    }

    public void setDoubleVisible(boolean b) {
        this.doubleVisible = b;
    }

    public boolean getDoubleVisible() {
        return this.doubleVisible;
    }

    public boolean getDoubleOption2() {
        return this.doubleOption2;
    }

    public void setDoubleOption2(boolean b) {
        this.doubleOption2 = b;
    }

    public boolean getDoubleOption3() {
        return this.doubleOption3;
    }

    public void setDoubleOption3(boolean b) {
        this.doubleOption3 = b;
    }

    public boolean getDoubleOption4() {
        return this.doubleOption4;
    }

    public void setDoubleOption4(boolean b) {
        this.doubleOption4 = b;
    }

    public boolean getTableVisible() {
        return this.tableVisible;
    }

    public void toggleTableVisible() {
        this.tableVisible = !tableVisible;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setProgress(boolean b) {
        this.progress.set(b);
    }

    public void setPlay(boolean b) {
        this.play.set(b);
    }

    public int getRoundWin() {
        return this.roundWin.get();
    }

    public void handleRoundWin() {
        moneyTotal.set(moneyTotal.get() + roundWin.get());
        moneySession.set(moneySession.get() + roundWin.get());
        if (roundWin.get() > roundWinStart.get()) {
            if (roundWin.get() / roundWinStart.get() > doubleBestMultiplier) {
                doubleBestMultiplier = roundWin.get() / roundWinStart.get();
                ui.getDoubleBestWin().setText("   " + Integer.toString(roundWinStart.get()) + ".00 --> " + Integer.toString(roundWin.get()) + ".00   ");
            }
            SimpleIntegerProperty doubleWin = ui.getDoubleWin();
            ui.getDoubleWin().set(doubleWin.get() + roundWin.get() - roundWinStart.get());
        }
        roundWin.set(0);
        roundWinStart.set(0);
        ui.setWinVisible(false);
        ui.setDoubleButtonDisable(true);
        ui.hideDoubleChoices();
        ui.handleNewButtonWinTaking();
        doubleVisible = false;
        setDoubleOptionsFalse();
    }

    public void handleNoRoundWin() {
        hand.randomizeHand();
        ui.getWinText().set("Win: ");
        doubleVisible = false;
        setDoubleOptionsFalse();
        ui.handleNewButtonNewCard();
        progress.set(true);
        play.set(false);
    }

    public int getRoundWinStart() {
        return this.roundWinStart.get();
    }

    public void setDoubleImage(int i) {
        this.doubleImage = i;
    }

    public void setRoundWin(int i) {
        this.roundWin.set(i);
    }

    public int getRoundWinX2() {
        return this.roundWinx2.get();
    }

    public int getRoundWinX3() {
        return this.roundWinx3.get();
    }

    public int getRoundWinX7() {
        return this.roundWinx7.get();
    }

    public int getRoundWinX30() {
        return this.roundWinx30.get();
    }

    public boolean getStatsVisible() {
        return this.statsVisible;
    }

    public void toggleStatsVisible() {
        statsVisible = !statsVisible;
    }

    public int getDoubleImage() {
        return this.doubleImage;
    }

    public void setRoundWinStart(int i) {
        this.roundWinStart.set(i);
    }

    public int getDoubleBestMultiplier() {
        return this.doubleBestMultiplier;
    }
}
