package kh.scratchcard.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kh.scratchcard.ui.Field;

public class Hand {

    private Field field1;
    private Field field2;
    private Field field3;
    private Field field4;
    private Field field5;
    private Field field6;
    private Field field7;
    private Field field8;
    private Field field9;
    private Random random;
    private boolean field1_win;
    private boolean field2_win;
    private boolean field3_win;
    private boolean field4_win;
    private boolean field5_win;
    private boolean field6_win;
    private boolean field7_win;
    private boolean field8_win;
    private boolean field9_win;
    private ScratchCard sc;

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
        field1_win = false;
        field2_win = false;
        field3_win = false;
        field4_win = false;
        field5_win = false;
        field6_win = false;
        field7_win = false;
        field8_win = false;
        field9_win = false;
        this.random = new Random();
        randomize_Hand();
    }
    public void randomize_Hand() {
        
        field1_win = false;
        field2_win = false;
        field3_win = false;
        field4_win = false;
        field5_win = false;
        field6_win = false;
        field7_win = false;
        field8_win = false;
        field9_win = false;
        field1.initialize(random.nextInt(9)+1);
        field2.initialize(random.nextInt(9)+1);
        field3.initialize(random.nextInt(9)+1);
        field4.initialize(random.nextInt(9)+1);
        field5.initialize(random.nextInt(9)+1);
        field6.initialize(random.nextInt(9)+1);
        field7.initialize(random.nextInt(9)+1);
        field8.initialize(random.nextInt(9)+1);
        field9.initialize(random.nextInt(9)+1);
    }

    
}
