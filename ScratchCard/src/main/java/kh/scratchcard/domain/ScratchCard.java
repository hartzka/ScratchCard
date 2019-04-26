package kh.scratchcard.domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kh.scratchcard.dao.Data;
import kh.scratchcard.dao.DataDao;
import kh.scratchcard.dao.Database;
import kh.scratchcard.ui.Field;
import kh.scratchcard.ui.Ui;

/**
 * Sovelluslogiikan raaputusarpaluokka, joka sisältää tietoa käynnissä olevasta
 * pelisessiosta, ja jonka kautta tietoa haetaan ja päivitetään.
 */
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
    private boolean test = false;

    private Ui ui;

    private Database database;
    private DataDao dataDao;
    private Data data;

    public ScratchCard() throws SQLException {
        images = new HashMap<>();
        ui = new Ui(this);
        try {
            database = new Database("jdbc:sqlite:data.db");
            dataDao = new DataDao(database);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScratchCard.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        System.out.println("Welcome!");
        setUpStats();
    }

    /**
     * Tarkistaa, ovatko kaikki raaputuskentät avattu. Jos näin on, muuttaa
     * pelitilanteita ja katsoo, onko tullut voittoja käynnissä olevalla
     * kierroksella.
     */
    public void checkOpened() {
        if (ui.getFieldsOpened()) {
            List<WinCategory> wins = hand.reveal();
            progress.set(false);
            play.set(true);
            if (!wins.isEmpty()) {
                checkWins(wins);
                ui.incrementWinningCards();
            }
        }
    }

    /**
     * Tarkistaa voitot. Jokainen yksittäinen voitto lisätään kierrosvoittoon.
     * Jos voitto on korkeintaan 50000, voiton voi tuplata, muuten näytetään
     * onnitteluteksti.
     *
     * @param wins Lista voittokategorioista kierroksella
     */
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

    /**
     * Käsittelee tuplauskentän avaamiseen liityvät toiminnot.
     */
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

    /**
     * Tarkistaa, onko tuplauksessa tullut voittoa. Jos on, kierrosvoitto kasvaa
     * tuplauskertoimella ja tuplausta voi jatkaa tai ottaa voiton talteen.
     * Muuten kutsutaan handleNoDoubleWin()-metodia, kierrosvoitto nollaantuu ja
     * tuplaus on hävitty.
     */
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

    public void setDoubleLocked() {
        doubleLocked = true;
        progress.set(true);
    }

    /**
     * Arpoo tuplauskenttään tulevan symbolin ja alustaa tuplauskentän tällä
     * symbolilla. Arvot 1-105: sitruuna Arvot 106-175: kiivi Arvot 176-205:
     * vadelma Arvot 206-212: mustaherukka
     */
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

    /**
     * Päivittää mahdolliset tuplausvoittoluokat
     */
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

    /**
     * Päivittää kaikki tuplausvalinnat falseiksi.
     */
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

    /**
     * Käsittelee kierrosvoittoon liittyvät toiminnot. Päivittää
     * kokonaisrahatilanteen ja muuta statistiikkaa.
     */
    public void handleRoundWin() {
        moneyTotal.set(moneyTotal.get() + roundWin.get());
        moneySession.set(moneySession.get() + roundWin.get());
        if (roundWin.get() > roundWinStart.get()) {
            if (roundWin.get() / roundWinStart.get() > doubleBestMultiplier) {
                doubleBestMultiplier = roundWin.get() / roundWinStart.get();
                ui.getDoubleBestWin().setText("   " + Integer.toString(roundWinStart.get()) + ".00 --> " + Integer.toString(roundWin.get()) + ".00   ");
            }
            SimpleIntegerProperty doubleWin = ui.getDoubleUpWins();
            ui.getDoubleUpWins().set(doubleWin.get() + roundWin.get() - roundWinStart.get());
        }
        System.out.println("Win: " + roundWin.get());
        roundWin.set(0);
        roundWinStart.set(0);
        ui.setWinVisible(false);
        ui.setDoubleButtonDisable(true);
        ui.hideDoubleChoices();
        doubleVisible = false;
        setDoubleOptionsFalse();
    }

    /**
     * Käsittelee uuden arvan ostamisen ja uuden kierroksen alkamisen. Kutsuu
     * kättä arpomaan uudet kuviot ja päivittää pelitilanteita.
     */
    public void handleNewCard() {
        hand.randomizeHand();
        ui.getWinText().set("Win: ");
        doubleVisible = false;
        setDoubleOptionsFalse();
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

    /**
     * Alustaa tilastot pelin alussa. Hakee tiedot tietokannasta dataDao:n
     * avulla.
     * 
     * @throws java.sql.SQLException jos tietokannan käsittelyssä taphtuu virhe
     */
    public void setUpStats() throws SQLException {
        data = dataDao.findOne();
        if (data != null) {
            moneyTotal.set(data.getMoneyTotal());
            System.out.println("Money: " + moneyTotal.get());
            ui.setStats(data);
        } else {
            System.out.println("Money: 0");
        }
    }

    /**
     * Tallentaa statistiikkaa pelin lopussa dataDao:n avulla tietokantaan.
     * 
     * @throws java.sql.SQLException jos tietokannan käsittelyssä taphtuu virhe
     */
    public void save() throws SQLException {
        if (doubleLocked) {
            SimpleIntegerProperty losses = ui.getDoubleUpLosses();
            ui.getDoubleUpLosses().set(losses.get() + roundWinStart.get());
            roundWin.set(0);
            roundWinStart.set(0);
        }
        if (roundWin.get() > 0) {
            moneyTotal.set(moneyTotal.get() + roundWin.get());
            moneySession.set(moneySession.get() + roundWin.get());
            if (roundWin.get() > roundWinStart.get()) {
                if (roundWin.get() / roundWinStart.get() > doubleBestMultiplier) {
                    doubleBestMultiplier = roundWin.get() / roundWinStart.get();
                    ui.getDoubleBestWin().setText("   " + Integer.toString(roundWinStart.get()) + ".00 --> " + Integer.toString(roundWin.get()) + ".00   ");
                }

                ui.getDoubleUpWins().set(ui.getDoubleUpWins().get() + roundWin.get() - roundWinStart.get());
            }
            roundWin.set(0);
        }

        data = new Data();
        data.setMoneyTotal(moneyTotal.get());
        data.setPlayedTotal(ui.getPlayedTotal());
        data.setWin2(ui.getWin2());
        data.setWin3(ui.getWin3());
        data.setWin4(ui.getWin4());
        data.setWin5(ui.getWin5());
        data.setWin6(ui.getWin6());
        data.setWin8(ui.getWin8());
        data.setWin10(ui.getWin10());
        data.setWin15(ui.getWin15());
        data.setWin20(ui.getWin20());
        data.setWin100(ui.getWin100());
        data.setWin500(ui.getWin500());
        data.setWin5000(ui.getWin5000());
        data.setWin50000(ui.getWin50000());
        data.setTotalWins(ui.getTotalWins());
        data.setWinningCards(ui.getWinningCards());
        data.setDoubleUpWins(ui.getDoubleUpWins().get());
        data.setDoubleUpLosses(ui.getDoubleUpLosses().get());
        data.setDoubleUpMaxWin(ui.getDoubleMaxWin());
        data.setDoubleUpBestResult(ui.getDoubleUpBestResult());
        if (!test) {
            dataDao.save(data);
        }
    }

    public Data getData() {
        return data;
    }

    public void setTest(boolean b) {
        test = b;
    }
}
