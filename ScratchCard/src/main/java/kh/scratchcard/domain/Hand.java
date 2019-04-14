package kh.scratchcard.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kh.scratchcard.ui.Field;

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

    public Hand() {
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

    public WinCategory randomizeSmallWinCategory(int result) {
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

    public WinCategory randomizeLargeWinCategory(int result) {
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

    private void actions(int n, WinCategory categ, Field f1, Field f2, Field f3, int fake) {
        if (categ != WinCategory.NOTHING) {
            if (null != categ) {
                switch (categ) {
                    case X3ORANGE:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 1);
                        break;
                    case X1PINEAPPLE:
                        actionsX1Pineapple(f1, f2, f3, n, fake);
                        break;
                    case X3STRAWBERRY:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 2);
                        break;
                    case X3PLUM:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 3);
                        break;
                    case X2MELON:
                        actionsX2Melon(f1, f2, f3, n);
                        break;
                    case X3CHERRY:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 4);
                        break;
                    case X2PINEAPPLE:
                        actionsX2Pineapple(f1, f2, f3, n);
                        break;
                    case X1BANANA:
                        actionsX1Banana(f1, f2, f3, n, fake);
                        break;
                    case X3MELON:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 5);
                        break;
                    case X3BANANA:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 6);
                        break;
                    case X3PINEAPPLE:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 7);
                        break;
                    case X3PEAR:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 8);
                        break;
                    case X3GRAPES:
                        actionsByWinCategory3Symbols(f1, f2, f3, n, 9);
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

    private void makeFieldWinsTrue(int[] fields) {
        for (int i : fields) {
            if (i <= 5) {
                makeLowFieldWinTrue(i);
            } else {
                makehighFieldWinTrue(i);
            }
        }
    }

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
            fake3Helper1(value, random, place1, place2, f1, f2, f3);
        } else if (value == 5) {
            fake3Helper2(value, random, place1, place2, f1, f2, f3);
        }
    }

    private void fake3Helper1(int value, Random random, int place1, int place2, Field f1, Field f2, Field f3) {
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

    private void fake3Helper2(int value, Random random, int place1, int place2, Field f1, Field f2, Field f3) {
        int value2 = value;
        while (value2 == value || value2 == 6 || value2 == 7) {
            value2 = random.nextInt(9) + 1;
        }
        int value3 = value;
        while (value3 == value || value3 == 6 || value3 == 7) {
            value3 = random.nextInt(9) + 1;
        }
        if (place1 != 0 && place2 != 0) {
            fake3Helper3(value, random, value2, value3, f1, f2, f3);
        } else if (place1 != 1 && place2 != 1) {
            fake3Helper3(value, random, value2, value3, f2, f1, f3);
        } else {
            fake3Helper3(value, random, value2, value3, f3, f2, f1);
        }
    }

    private void fake3Helper3(int value, Random random, int value2, int value3, Field f1, Field f2, Field f3) {
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

    private void initializeFieldsWithValues(Field f1, Field f2, Field f3, int value, int value2, int value3, int rand) {
        if (rand < 3) {
            initializeFieldsWithValuesWhenRandLessThan3(f1, f2, f3, value, value2, value3, rand);
        } else {
            initializeFieldsWithValuesWhenRandMoreThan3(f1, f2, f3, value, value2, value3, rand);
        }
    }

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

    private void openFields() {
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

    private List<Integer> checkFieldWins1(List<Integer> l) {
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

    private List<Integer> checkFieldWins2(List<Integer> l) {
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

    private void actionsX1Pineapple(Field f1, Field f2, Field f3, int n, int fake) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsByWinCategory1Symbol(f1, f2, f3, n, fake, 1, 7);
                break;
            case 1:
                actionsByWinCategory1Symbol(f2, f1, f3, n, fake, 2, 7);
                break;
            case 2:
                actionsByWinCategory1Symbol(f3, f2, f1, n, fake, 3, 7);
                break;
            default:
                break;
        }
    }

    private void actionsByWinCategory1Symbol(Field f1, Field f2, Field f3, int n, int fake, int column, int symbol) {
        f1.initialize(symbol);
        switch (n) {
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

    private void actionsByWinCategory2Symbols(Field f1, Field f2, Field f3, int n, int column1, int column2, int symbol) {
        f1.initialize(symbol);
        f2.initialize(symbol);
        switch (n) {
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

    private void actionsByWinCategory3Symbols(Field f1, Field f2, Field f3, int n, int category) {
        f1.initialize(category);
        f2.initialize(category);
        f3.initialize(category);
        switch (n) {
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

    private void actionsX2Melon(Field f1, Field f2, Field f3, int n) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsX2Melon2(f1, f2, f3, n, 1, 2);
                break;
            case 1:
                actionsX2Melon2(f2, f3, f1, n, 2, 3);
                break;
            case 2:
                actionsX2Melon2(f3, f1, f2, n, 1, 3);
                break;
            default:
                break;
        }
    }

    private void actionsX2Melon2(Field f1, Field f2, Field f3, int n, int column1, int column2) {
        actionsByWinCategory2Symbols(f1, f2, f3, n, column1, column2, 5);
        int value = 5;
        while (value == 5 || value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        f3.initialize(value);
    }

    private void actionsX2Pineapple(Field f1, Field f2, Field f3, int n) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsX2Pineapple2(f1, f2, f3, n, 1, 2);
                break;
            case 1:
                actionsX2Pineapple2(f2, f3, f1, n, 2, 3);
                break;
            case 2:
                actionsX2Pineapple2(f3, f1, f2, n, 1, 3);
                break;
            default:
                break;
        }
    }

    private void actionsX2Pineapple2(Field f1, Field f2, Field f3, int n, int column1, int column2) {
        actionsByWinCategory2Symbols(f1, f2, f3, n, column1, column2, 7);
        int value = 6;
        while (value == 6 || value == 7) {
            value = random.nextInt(9) + 1;
        }
        f3.initialize(value);
    }

    private void actionsX1Banana(Field f1, Field f2, Field f3, int n, int fake) {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                actionsByWinCategory1Symbol(f1, f2, f3, n, fake, 1, 6);
                break;
            case 1:
                actionsByWinCategory1Symbol(f2, f1, f3, n, fake, 2, 6);
                break;
            case 2:
                actionsByWinCategory1Symbol(f3, f2, f1, n, fake, 3, 6);
                break;
            default:
                break;
        }
    }

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

    private void randomizeHandWith3Wins(WinCategory win) {
        win1 = win;
        win2 = randomizeWinCategory(true);
        win3 = randomizeWinCategory(true);
    }

    public void setWin1(WinCategory winCategory) {
        this.win1 = winCategory;
    }

    private void setFieldWinTrue(int column) {
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
        } else if (column == 6) {
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
}
