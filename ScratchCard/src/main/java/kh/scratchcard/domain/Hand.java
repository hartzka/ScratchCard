package kh.scratchcard.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kh.scratchcard.ui.Field;

/**
 * Käsi, joka sisältää tietoa käynnissä olevan pelikierroksen voitoista ja
 * voittoluokista. Huolehtii myös kuvioiden arvonnasta ja voittojen
 * tarkistamisesta.
 */
public class Hand {

    private final Field field1;
    private final Field field2;
    private final Field field3;
    private final Field field4;
    private final Field field5;
    private final Field field6;
    private final Field field7;
    private final Field field8;
    private final Field field9;
    private final Random random;
    private WinCategory win1;
    private WinCategory win2;
    private WinCategory win3;
    private boolean field1Win;
    private boolean field2Win;
    private boolean field3Win;
    private boolean field4Win;
    private boolean field5Win;
    private boolean field6Win;
    private boolean field7Win;
    private boolean field8Win;
    private boolean field9Win;
    private ScratchCard sc;

    public Hand() throws SQLException {
        this.sc = new ScratchCard();
        this.field1 = new Field(sc, 0, 0, 0, 0);
        this.field2 = new Field(sc, 0, 0, 0, 0);
        this.field3 = new Field(sc, 0, 0, 0, 0);
        this.field4 = new Field(sc, 0, 0, 0, 0);
        this.field5 = new Field(sc, 0, 0, 0, 0);
        this.field6 = new Field(sc, 0, 0, 0, 0);
        this.field7 = new Field(sc, 0, 0, 0, 0);
        this.field8 = new Field(sc, 0, 0, 0, 0);
        this.field9 = new Field(sc, 0, 0, 0, 0);
        this.win1 = null;
        this.win2 = null;
        this.win3 = null;
        field1Win = false;
        field2Win = false;
        field3Win = false;
        field4Win = false;
        field5Win = false;
        field6Win = false;
        field7Win = false;
        field8Win = false;
        field9Win = false;
        this.random = new Random();
    }

    public Hand(ScratchCard sc, Field f1, Field f2, Field f3, Field f4, Field f5, Field f6, Field f7, Field f8, Field f9) {
        this.sc = sc;
        this.field1 = f1;
        this.field2 = f2;
        this.field3 = f3;
        this.field4 = f4;
        this.field5 = f5;
        this.field6 = f6;
        this.field7 = f7;
        this.field8 = f8;
        this.field9 = f9;
        this.win1 = WinCategory.NOTHING;
        this.win2 = WinCategory.NOTHING;
        this.win3 = WinCategory.NOTHING;
        field1Win = false;
        field2Win = false;
        field3Win = false;
        field4Win = false;
        field5Win = false;
        field6Win = false;
        field7Win = false;
        field8Win = false;
        field9Win = false;
        this.random = new Random();
    }

    /**
     * Voittokategorian arvonta
     *
     * @param win true = Arvotaan varma voitto, false = Normaali arvonta
     *
     * @return Voittokategorian, joka kuvaa voittoa. Mikäli arvonnan tulos on
     * "ei voittoa", palautetaan WinCategory.NOTHING.
     */
    public WinCategory randomizeWinCategory(boolean win) {
        int result = random.nextInt(10000000) + 1;
        if (win) {
            result = random.nextInt(2840911) + 1;
        }
        WinCategory winCateg = WinCategory.NOTHING;
        if (result <= 2820000) {
            winCateg = randomizeSmallWinCategory(result);
        } else if (result <= 2840911) {
            winCateg = randomizeLargeWinCategory(result);
        }
        return winCateg;
    }

    /**
     * Apumetodi pienen voittokategorian arvonnalle
     *
     * @param result Arvonnan numeerinen tulos
     *
     * @return Voittokategorian
     */
    private WinCategory randomizeSmallWinCategory(int result) {
        WinCategory winCateg = WinCategory.NOTHING;
        if (result <= 1000000) {
            winCateg = WinCategory.X3ORANGE; //3x orange 2
        } else if (result <= 1850000) {
            winCateg = WinCategory.X1PINEAPPLE; //1x pineapple 3
        } else if (result <= 2350000) {
            winCateg = WinCategory.X3STRAWBERRY; // 3x strawberry 4
        } else if (result <= 2600000) {
            winCateg = WinCategory.X3PLUM; // 3x plum 5
        } else if (result <= 2700000) {
            winCateg = WinCategory.X2MELON; // 2x melon 6
        } else if (result <= 2770000) {
            winCateg = WinCategory.X3CHERRY; // 3x cherry 8
        } else if (result <= 2820000) {
            winCateg = WinCategory.X1BANANA; // 1x banana 10
        }
        return winCateg;
    }

    /**
     * Apumetodi suuren voittokategorian arvonnalle
     *
     * @param result Arvonnan numeerinen tulos
     *
     * @return Voittokategorian
     */
    private WinCategory randomizeLargeWinCategory(int result) {
        WinCategory winCateg = WinCategory.NOTHING;
        if (result <= 2830000) {
            winCateg = WinCategory.X2PINEAPPLE; // 2x pineapple 15
        } else if (result <= 2839800) {
            winCateg = WinCategory.X3MELON; // 3x melon 20
        } else if (result <= 2840800) {
            winCateg = WinCategory.X3BANANA; // 3x banana 100
        } else if (result <= 2840900) {
            winCateg = WinCategory.X3PINEAPPLE; // 3x pineapple 500
        } else if (result <= 2840910) {
            winCateg = WinCategory.X3PEAR; // 3x pear 5000
        } else if (result <= 2840911) {
            winCateg = WinCategory.X3GRAPES; // 3x grapes 50000
        }
        return winCateg;
    }

    /**
     * Voittojen alustus
     */
    public void initializeWins() {
        this.win1 = WinCategory.NOTHING;
        this.win2 = WinCategory.NOTHING;
        this.win3 = WinCategory.NOTHING;
        field1Win = false;
        field2Win = false;
        field3Win = false;
        field4Win = false;
        field5Win = false;
        field6Win = false;
        field7Win = false;
        field8Win = false;
        field9Win = false;
    }

    /**
     * Käden arvonta, arpoo kierroksen kuviot ja voitot.
     */
    public void randomizeHand() {
        initializeWins();
        WinCategory win = randomizeWinCategory(false);
        int fakeWin1 = random.nextInt(10);
        int fakeWin2 = random.nextInt(10);
        int fakeWin3 = random.nextInt(10);
        if (win == WinCategory.NOTHING) {
            win1 = WinCategory.NOTHING;
            win2 = WinCategory.NOTHING;
            win3 = WinCategory.NOTHING;
        } else {
            randomizeHandWithWIn(win);
        }
        actions(1, win1, field1, field2, field3, fakeWin1);
        actions(2, win2, field4, field5, field6, fakeWin2);
        actions(3, win3, field7, field8, field9, fakeWin3);
    }

    /**
     * Arpoo voittojen lukumäärän kädessä. Lukumäärä voi olla välillä 0-3.
     *
     * @return Voittojen lukumäärän
     */
    public int randomizeWinAmount() {
        int wins = 0;
        int rand = random.nextInt(100);
        if (rand <= 11) {
            wins = 0;
        } else if (rand <= 23) {
            int rand2 = random.nextInt(6);
            if (rand2 == 0) {
                wins = 1;
            } else if (rand2 == 1) {
                wins = 3;
            } else {
                wins = 2;
            }
        } else {
            wins = 1;
        }
        return wins;
    }

    /**
     * Käden voittojen ja kuvioiden arvonta voittojen lukumäärän mukaan
     *
     * @param win Voittojen lukumäärä
     */
    public void randomizeHandWithWIn(WinCategory win) {
        int wins = randomizeWinAmount();
        if (wins == 0) {
            win1 = WinCategory.NOTHING;
            win2 = WinCategory.NOTHING;
            win3 = WinCategory.NOTHING;
        } else if (wins == 1) {
            randomizeHandWith1Win(win);
        } else if (wins == 2) {
            randomizeHandWith2Wins(win);
        } else if (wins == 3) {
            randomizeHandWith3Wins(win);
        }

    }

    /**
     * Apumetodi käden voittojen ja kuvioiden arvonnalle
     *
     * @param row Rivin numero 1-3
     * @param categ Rivin voittokategoria
     * @param f1 Rivin vasemmanpuoleisin raaputuskenttä
     * @param f2 Rivin keskimmäinen raaputuskenttä
     * @param f3 Rivin oikeanpuoleisin raaputuskenttä
     * @param fake Arvottu huijausvoiton numero. Huijausvoittoja käytetään
     * riveissä, jotka eivät voita. Niiden ansiosta riveille tulee useammin
     * symboleja niin, että näyttäisi, että "voitto jäi taas yhden päähän". Näin
     * arpoihin tuodaan lisää jännitystä.
     */
    public void actions(int row, WinCategory categ, Field f1, Field f2, Field f3, int fake) {
        if (categ != WinCategory.NOTHING) {
            if (null != categ) {
                switch (categ) {
                    case X3ORANGE:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 1);
                        break;
                    case X1PINEAPPLE:
                        actionsX1Pineapple(f1, f2, f3, row, fake);
                        break;
                    case X3STRAWBERRY:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 2);
                        break;
                    case X3PLUM:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 3);
                        break;
                    case X2MELON:
                        actionsX2Melon(f1, f2, f3, row);
                        break;
                    case X3CHERRY:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 4);
                        break;
                    case X2PINEAPPLE:
                        actionsX2Pineapple(f1, f2, f3, row);
                        break;
                    case X1BANANA:
                        actionsX1Banana(f1, f2, f3, row, fake);
                        break;
                    case X3MELON:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 5);
                        break;
                    case X3BANANA:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 6);
                        break;
                    case X3PINEAPPLE:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 7);
                        break;
                    case X3PEAR:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 8);
                        break;
                    case X3GRAPES:
                        actionsByWinCategory3Symbols(f1, f2, f3, row, 9);
                        break;
                    default:
                        break;
                }
            }
        } else if (fake > 5) {
            fake3(f1, f2, f3);
        } else {
            noFake3(f1, f2, f3);
        }
    }

    /**
     * Kenttävoittojen merkitseminen kentän mukaan
     *
     * @param fields Lista merkittävistä kentistä, joissa on voittava symboli
     */
    public void makeFieldWinsTrue(int[] fields) {
        for (int i : fields) {
            if (i <= 5) {
                makeLowFieldWinTrue(i);
            } else {
                makehighFieldWinTrue(i);
            }
        }
    }

    /**
     * Apumetodi kenttävoittojen merkitsemiseen kentän mukaan
     *
     * @param field Merkittävä kenttä
     */
    private void makeLowFieldWinTrue(int field) {
        if (field == 1) {
            field1Win = true;
        } else if (field == 2) {
            field2Win = true;
        } else if (field == 3) {
            field3Win = true;
        } else if (field == 4) {
            field4Win = true;
        } else if (field == 5) {
            field5Win = true;
        }
    }

    /**
     * Apumetodi kenttävoittojen merkitsemiseen kentän mukaan
     *
     * @param field Merkittävä kenttä
     */
    private void makehighFieldWinTrue(int field) {
        if (field == 6) {
            field6Win = true;
        } else if (field == 7) {
            field7Win = true;
        } else if (field == 8) {
            field8Win = true;
        } else if (field == 9) {
            field9Win = true;
        }
    }

    /**
     * Kahden kentän huijausvoiton arpominen, kun kolmas kenttä voittaa. Alustaa
     * kaksi kenttää samalla huijaussymbolilla niin, että kolmen symbolin voitto
     * jää yhden päähän. Kolmanteen kenttään arvotaan voittava symboli (joko
     * ananas tai banaani). Tapaukset value = 6 (banaani) ja value = 7 (ananas)
     * eivät kelpaa, koska nämä saattavat jo olla mukana yhden kuvion voitossa.
     * Melonin tapauksessa (value = 5) ei voida arpoa kahta melonia, koska ne
     * oikeuttaisivat voittpon.
     *
     * @param field1 Ensimmäinen kenttä, johon tulee huijaussymboli
     * @param field2 Toinen kenttä, johon tulee huijaussymboli
     */
    private void fake2(Field f1, Field f2) {
        int value = 6;
        while (value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        if (value == 5) {
            int value2 = 6;
            while (value2 == 5 || value2 == 6 || value2 == 7) {
                value2 = random.nextInt(9) + 1;
            }
            fake2InSomeOrder(f1, f2, value, value2);
        } else {
            f1.initialize(value);
            f2.initialize(value);
        }
    }

    /**
     * Melonin ja jonkin muun symbolin (ei banaani tai ananas) laittaminen
     * jossain järjestyksessä kenttiin arvojen mukaan
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param value Ensimmäinen arvo
     * @param value2 Toinen arvo
     */
    private void fake2InSomeOrder(Field f1, Field f2, int value, int value2) {
        int which = random.nextInt(2);
        if (which == 0) {
            f1.initialize(value);
            f2.initialize(value2);
        } else {
            f2.initialize(value);
            f1.initialize(value2);
        }
    }

    /**
     * Arpoo rehellisesti kaksi kuviota kenttiin ilman huijausta, kun kolmas
     * kenttä voittaa. Banaania, ananasta ja kahta melonia ei taaskaan
     * hyväksytä, koska ne oikeuttaisivat voittoon.
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     */
    private void nofake2(Field f1, Field f2) {
        int value = 6;
        while (value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        f1.initialize(value);
        int value2 = 6;
        while (value2 == 6 || value2 == 7 || (value == 5 && value2 == 5)) {
            value2 = random.nextInt(9) + 1;
        }
        f2.initialize(value2);
    }

    /**
     * Huijausvoiton arpominen kolmeen kenttään, kun voittoa ei ole rivillä.
     * Huijausvoitossa on yhdellä rivillä voitto "yhden päässä".
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     */
    private void fake3(Field f1, Field f2, Field f3) {
        int value = 7;
        while (value == 7) {
            value = random.nextInt(9) + 1;
        }
        int place1 = random.nextInt(3);
        int place2 = random.nextInt(3);
        while (place2 == place1) {
            place2 = random.nextInt(3);
        }
        if (value == 1 || value == 2 || value == 3 || value == 4 || value == 6 || value == 8 || value == 9) {
            fake3Helper1(value, place1, place2, f1, f2, f3);
        } else if (value == 5) {
            fake3Helper2(value, place1, place2, f1, f2, f3);
        }
    }

    /**
     * Alustaa kahden symbolin huijausvoiton kahteen kenttään jossain
     * järjestyksessä.
     *
     * @param value Alustettava arvo
     * @param place1 Rivillä oleva ensimmäinen paikka, jonka kenttään alustetaan
     * arvo. Välillä 1-3.
     * @param place2 Rivillä oleva toinen paikka, jonka kenttään alustetaan
     * arvo. Välillä 1-3.
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     */
    private void fake3Helper1(int value, int place1, int place2, Field f1, Field f2, Field f3) {
        int value2 = value;
        while (value2 == value || value2 == 6 || value2 == 7) {
            value2 = random.nextInt(9) + 1;
        }
        if (place1 != 0 && place2 != 0) {
            f1.initialize(value2);
            f2.initialize(value);
            f3.initialize(value);
        } else if (place1 != 1 && place2 != 1) {
            f1.initialize(value);
            f2.initialize(value2);
            f3.initialize(value);
        } else {
            f1.initialize(value);
            f2.initialize(value);
            f3.initialize(value2);
        }
    }

    /**
     * Alustaa kahden symbolin, joissa on yksi meloni mukana, huijausvoiton
     * kahteen kenttään.
     *
     * @param value Alustettava arvo
     * @param place1 Rivillä oleva ensimmäinen paikka, jonka kenttään alustetaan
     * arvo. Välillä 1-3.
     * @param place2 Rivillä oleva toinen paikka, jonka kenttään alustetaan
     * arvo. Välillä 1-3.
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     */
    private void fake3Helper2(int value, int place1, int place2, Field f1, Field f2, Field f3) {
        int value2 = value;
        while (value2 == value || value2 == 6 || value2 == 7) {
            value2 = random.nextInt(9) + 1;
        }
        int value3 = value;
        while (value3 == value || value3 == 6 || value3 == 7) {
            value3 = random.nextInt(9) + 1;
        }
        if (place1 != 0 && place2 != 0) {
            fake3Helper3(value, value2, value3, f1, f2, f3);
        } else if (place1 != 1 && place2 != 1) {
            fake3Helper3(value, value2, value3, f2, f1, f3);
        } else {
            fake3Helper3(value, value2, value3, f3, f2, f1);
        }
    }

    /**
     * Apumetodi kahden symbolin huijausvoiton alustamisessa, joissa on yksi
     * meloni mukana, kahteen kenttään jossain järjestyksessä.
     *
     * @param value Alustettava arvo (tässä meloni eli value = 5)
     * @param place1 Rivillä oleva ensimmäinen paikka, jonka kenttään alustetaan
     * arvo. Välillä 1-3.
     * @param place2 Rivillä oleva toinen paikka, jonka kenttään alustetaan
     * arvo. Välillä 1-3.
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     */
    private void fake3Helper3(int value, int value2, int value3, Field f1, Field f2, Field f3) {
        f1.initialize(value);
        int rand = random.nextInt(2);
        if (rand == 0) {
            f2.initialize(value2);
            f3.initialize(value3);
        } else {
            f2.initialize(value3);
            f3.initialize(value2);
        }
    }

    /**
     * Arpoo satunnaisesti ei-voittavan yhdistelmän kolmeen kenttään.
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     */
    private void noFake3(Field f1, Field f2, Field f3) {
        int value = 6;
        while (value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        int value2 = 6;
        while (value2 == 6 || value2 == 7 || (value == value2 && value == 5)) {
            value2 = random.nextInt(9) + 1;
        }
        int value3 = 6;
        while (value3 == 6 || value3 == 7 || (value == value2 && value == value3) || ((value3 == 5) && (value == value3 || value2 == value3))) {
            value3 = random.nextInt(9) + 1;
        }
        int rand = random.nextInt(6);
        initializeFieldsWithValues(f1, f2, f3, value, value2, value3, rand);
    }

    /**
     * Alustaa kentät tietyillä symboliarvoilla.
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     * @param value Alustettava arvo 1
     * @param value2 Alustettava arvo 2
     * @param value3 Alustettava arvo 3
     * @param rand satunnaisluku välillä 0-5
     */
    public void initializeFieldsWithValues(Field f1, Field f2, Field f3, int value, int value2, int value3, int rand) {
        if (rand < 3) {
            initializeFieldsWithValuesWhenRandLessThan3(f1, f2, f3, value, value2, value3, rand);
        } else {
            initializeFieldsWithValuesWhenRandMoreThan3(f1, f2, f3, value, value2, value3, rand);
        }
    }

    /**
     * Alustaa kentät tietyillä symboliarvoilla, kun rand on välillä 0-2.
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     * @param value Alustettava arvo 1
     * @param value2 Alustettava arvo 2
     * @param value3 Alustettava arvo 3
     * @param rand satunnaisluku välillä 0-2
     */
    private void initializeFieldsWithValuesWhenRandLessThan3(Field f1, Field f2, Field f3, int value, int value2, int value3, int rand) {
        if (rand == 0) {
            f1.initialize(value);
            f2.initialize(value2);
            f3.initialize(value3);
        } else if (rand == 1) {
            f1.initialize(value);
            f2.initialize(value3);
            f3.initialize(value2);
        } else if (rand == 2) {
            f1.initialize(value2);
            f2.initialize(value);
            f3.initialize(value3);
        }
    }

    /**
     * Alustaa kentät tietyillä symboliarvoilla, kun rand on välillä 3-5.
     *
     * @param f1 Ensimmäinen kenttä
     * @param f2 Toinen kenttä
     * @param f3 Kolmas kenttä
     * @param value Alustettava arvo 1
     * @param value2 Alustettava arvo 2
     * @param value3 Alustettava arvo 3
     * @param rand satunnaisluku välillä 3-5
     */
    private void initializeFieldsWithValuesWhenRandMoreThan3(Field f1, Field f2, Field f3, int value, int value2, int value3, int rand) {
        if (rand == 3) {
            f1.initialize(value2);
            f2.initialize(value3);
            f3.initialize(value);
        } else if (rand == 4) {
            f1.initialize(value3);
            f2.initialize(value2);
            f3.initialize(value);
        } else if (rand == 5) {
            f1.initialize(value3);
            f2.initialize(value);
            f3.initialize(value2);
        }
    }

    /**
     * Avaa raaputuskentät
     *
     */
    public void openFields() {
        field1.open();
        field2.open();
        field3.open();
        field4.open();
        field5.open();
        field6.open();
        field7.open();
        field8.open();
        field9.open();
    }

    /**
     * Palauttaa listan käden voittokategorioista ja asettaa voittojen
     * korostukset ui:lle.
     *
     * @return Lista voittokategorioista
     */
    public List<WinCategory> reveal() {
        openFields();
        List<WinCategory> list = new ArrayList();
        if (win1 != WinCategory.NOTHING) {
            list.add(win1);
        }
        if (win2 != WinCategory.NOTHING) {
            list.add(win2);
        }
        if (win3 != WinCategory.NOTHING) {
            list.add(win3);
        }
        List<Integer> l = new ArrayList<Integer>();
        l = checkFieldWins1(l);
        l = checkFieldWins2(l);
        sc.getUi().setWinBorders(l);
        return list;
    }

    /**
     * Palauttaa listan käden voittavista kentistä 1-5.
     *
     * @param l Lista, johon lisätään voittavien kenttien numerot
     * 
     * @return Lista voittavista kentistä
     */
    public List<Integer> checkFieldWins1(List<Integer> l) {
        if (field1Win) {
            l.add(1);
        }
        if (field2Win) {
            l.add(2);
        }
        if (field3Win) {
            l.add(3);
        }
        if (field4Win) {
            l.add(4);
        }
        if (field5Win) {
            l.add(5);
        }
        return l;
    }

    /**
     * Palauttaa listan käden voittavista kentistä 6-9.
     *
     * @param l Lista, johon lisätään voittavien kenttien numerot   
     * 
     * @return Lista voittavista kentistä
     */
    public List<Integer> checkFieldWins2(List<Integer> l) {
        if (field6Win) {
            l.add(6);
        }
        if (field7Win) {
            l.add(7);
        }
        if (field8Win) {
            l.add(8);
        }
        if (field9Win) {
            l.add(9);
        }
        return l;
    }

    /**
     * Palauttaa listan käden voittavista kategorioista testissä.
     *
     * @return Lista voittavista kategorioista
     */
    public List<WinCategory> revealTest() {

        List<WinCategory> list = new ArrayList();
        if (win1 != WinCategory.NOTHING) {
            list.add(win1);
        }
        if (win2 != WinCategory.NOTHING) {
            list.add(win2);
        }
        if (win3 != WinCategory.NOTHING) {
            list.add(win3);
        }

        return list;
    }

    /**
     * Voiton 1 x ananas toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     * @param fake huijausnumero
     */
    private void actionsX1Pineapple(Field f1, Field f2, Field f3, int row, int fake) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsByWinCategory1Symbol(f1, f2, f3, row, fake, 1, 7);
                break;
            case 1:
                actionsByWinCategory1Symbol(f2, f1, f3, row, fake, 2, 7);
                break;
            case 2:
                actionsByWinCategory1Symbol(f3, f2, f1, row, fake, 3, 7);
                break;
            default:
                break;
        }
    }

    /**
     * Yhden voittavan symbolin toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     * @param fake huijausnumero
     * @param column sarake
     * @param symbol symbolinumero
     */
    private void actionsByWinCategory1Symbol(Field f1, Field f2, Field f3, int row, int fake, int column, int symbol) {
        f1.initialize(symbol);
        switch (row) {
            case 1:
                setFieldWinTrue(column);
                break;
            case 2:
                setFieldWinTrue(column + 3);
                break;
            default:
                setFieldWinTrue(column + 6);
                break;
        }
        if (fake > 5) {
            fake2(f2, f3);
        } else {
            nofake2(f2, f3);
        }
    }

    /**
     * Kahden voittavan symbolin toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param row Rivi
     * @param column1 sarake1
     * @param column2 sarake2
     * @param symbol symbolinumero
     */
    private void actionsByWinCategory2Symbols(Field f1, Field f2, int row, int column1, int column2, int symbol) {
        f1.initialize(symbol);
        f2.initialize(symbol);
        switch (row) {
            case 1:
                makeFieldWinsTrue(new int[]{column1, column2});
                break;
            case 2:
                makeFieldWinsTrue(new int[]{column1 + 3, column2 + 3});
                break;
            default:
                makeFieldWinsTrue(new int[]{column1 + 6, column2 + 6});
                break;
        }
    }

    /**
     * Kolmen voittavan symbolin toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     * @param category kategorianumero
     */
    private void actionsByWinCategory3Symbols(Field f1, Field f2, Field f3, int row, int category) {
        f1.initialize(category);
        f2.initialize(category);
        f3.initialize(category);
        switch (row) {
            case 1:
                makeFieldWinsTrue(new int[]{1, 2, 3});
                break;
            case 2:
                makeFieldWinsTrue(new int[]{4, 5, 6});
                break;
            default:
                makeFieldWinsTrue(new int[]{7, 8, 9});
                break;
        }
    }

    /**
     * Voiton 2 x meloni toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     */
    private void actionsX2Melon(Field f1, Field f2, Field f3, int row) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsX2Melon2(f1, f2, f3, row, 1, 2);
                break;
            case 1:
                actionsX2Melon2(f2, f3, f1, row, 2, 3);
                break;
            case 2:
                actionsX2Melon2(f3, f1, f2, row, 1, 3);
                break;
            default:
                break;
        }
    }

    /**
     * Voiton 2 x meloni toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     * @param column1 sarake 1
     * @param column2 sarake 2
     */
    private void actionsX2Melon2(Field f1, Field f2, Field f3, int row, int column1, int column2) {
        actionsByWinCategory2Symbols(f1, f2, row, column1, column2, 5);
        int value = 5;
        while (value == 5 || value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        f3.initialize(value);
    }

    /**
     * Voiton 2 x ananas toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     */
    private void actionsX2Pineapple(Field f1, Field f2, Field f3, int row) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsX2Pineapple2(f1, f2, f3, row, 1, 2);
                break;
            case 1:
                actionsX2Pineapple2(f2, f3, f1, row, 2, 3);
                break;
            case 2:
                actionsX2Pineapple2(f3, f1, f2, row, 1, 3);
                break;
            default:
                break;
        }
    }

    /**
     * Voiton 2 x ananas toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     * @param column1 sarake 1
     * @param column2 sarake 2
     */
    private void actionsX2Pineapple2(Field f1, Field f2, Field f3, int n, int column1, int column2) {
        actionsByWinCategory2Symbols(f1, f2, n, column1, column2, 7);
        int value = 6;
        while (value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        f3.initialize(value);
    }

    /**
     * Voiton 1 x banaani toiminnot
     *
     * @param f1 Kenttä 1
     * @param f2 Kenttä 2
     * @param f3 Kenttä 3
     * @param row Rivi
     * @param fake huijausnumero
     */
    private void actionsX1Banana(Field f1, Field f2, Field f3, int row, int fake) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsByWinCategory1Symbol(f1, f2, f3, row, fake, 1, 6);
                break;
            case 1:
                actionsByWinCategory1Symbol(f2, f1, f3, row, fake, 2, 6);
                break;
            case 2:
                actionsByWinCategory1Symbol(f3, f2, f1, row, fake, 3, 6);
                break;
            default:
                break;
        }
    }

    /**
     * Alustaa käden yhdellä voitolla
     *
     * @param win alustettava voittokategoria
     */
    private void randomizeHandWith1Win(WinCategory win) {
        int rand3 = random.nextInt(3);
        switch (rand3) {
            case 0:
                win1 = win;
                break;
            case 1:
                win2 = win;
                break;
            case 2:
                win3 = win;
                break;
            default:
                break;
        }
    }

    /**
     * Alustaa käden kahdella voitolla
     *
     * @param win alustettava voittokategoria
     */
    private void randomizeHandWith2Wins(WinCategory win) {
        int rand4 = random.nextInt(3);
        switch (rand4) {
            case 0:
                win1 = win;
                win2 = randomizeWinCategory(true);
                break;
            case 1:
                win1 = win;
                win3 = randomizeWinCategory(true);
                break;
            case 2:
                win2 = win;
                win3 = randomizeWinCategory(true);
                break;
            default:
                break;
        }
    }

    /**
     * Alustaa käden kolmella voitolla
     *
     * @param win alustettava voittokategoria
     */
    private void randomizeHandWith3Wins(WinCategory win) {
        win1 = win;
        win2 = randomizeWinCategory(true);
        win3 = randomizeWinCategory(true);
    }

    public void setWin1(WinCategory winCategory) {
        this.win1 = winCategory;
    }

    /**
     * Asettaa kenttävoiton trueksi kentän mukaan
     *
     * @param column alustettava kenttänumero
     */
    public void setFieldWinTrue(int column) {
        if (column <= 5) {
            setFieldWinTrue1(column);
        } else {
            setFieldWinTrue2(column);
        }
    }

    private void setFieldWinTrue1(int column) {
        if (column == 1) {
            field1Win = true;
        } else if (column == 2) {
            field2Win = true;
        } else if (column == 3) {
            field3Win = true;
        } else if (column == 4) {
            field4Win = true;
        } else if (column == 5) {
            field5Win = true;
        }
    }

    private void setFieldWinTrue2(int column) {
        if (column == 6) {
            field6Win = true;
        } else if (column == 7) {
            field7Win = true;
        } else if (column == 8) {
            field8Win = true;
        } else if (column == 9) {
            field9Win = true;
        }
    }

    public WinCategory getWin1() {
        return win1;
    }

    public WinCategory getWin2() {
        return win2;
    }

    public WinCategory getWin3() {
        return win3;
    }

    public boolean getFieldWin1() {
        return field1Win;
    }

    public boolean getFieldWin5() {
        return field5Win;
    }

    public boolean getFieldWin9() {
        return field9Win;
    }

    public boolean getFieldWin2() {
        return field2Win;
    }

    public boolean getFieldWin3() {
        return field3Win;
    }

    public boolean getFieldWin4() {
        return field4Win;
    }

    public boolean getFieldWin6() {
        return field6Win;
    }

    public boolean getFieldWin7() {
        return field7Win;
    }

    public boolean getFieldWin8() {
        return field8Win;
    }

    public void setWin2(WinCategory winCategory) {
        win2 = winCategory;
    }

    public void setWin3(WinCategory winCategory) {
        win3 = winCategory;
    }

    public void setField1Win(boolean b) {
        field1Win = b;
    }

    public void setField2Win(boolean b) {
        field2Win = b;
    }

    public void setField3Win(boolean b) {
        field3Win = b;
    }

    public void setField4Win(boolean b) {
        field4Win = b;
    }

    public void setField5Win(boolean b) {
        field5Win = b;
    }

    public void setField6Win(boolean b) {
        field6Win = b;
    }

    public void setField7Win(boolean b) {
        field7Win = b;
    }

    public void setField8Win(boolean b) {
        field8Win = b;
    }

    public void setField9Win(boolean b) {
        field9Win = b;
    }

    /**
     * Alustaa käden voitot nollaksi
     *
     */
    public void initialize() {
        win1 = WinCategory.NOTHING;
        win2 = WinCategory.NOTHING;
        win3 = WinCategory.NOTHING;
        field1Win = false;
        field2Win = false;
        field3Win = false;
        field4Win = false;
        field5Win = false;
        field6Win = false;
        field7Win = false;
        field8Win = false;
        field9Win = false;
    }
}
