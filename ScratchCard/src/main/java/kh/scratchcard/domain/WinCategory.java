package kh.scratchcard.domain;

public enum WinCategory {
    NOTHING(0),
    X3ORANGE(2), X1PINEAPPLE(3), X3STRAWBERRY(4), X3PLUM(5), X2MELON(6),
    X3CHERRY(8), X1BANANA(10), X2PINEAPPLE(15), X3MELON(20), X3BANANA(100),
    X3PINEAPPLE(500), X3PEAR(5000), X3GRAPES(50000);

    final int value;

    WinCategory(int value) {
        this.value = value;
    }
}
