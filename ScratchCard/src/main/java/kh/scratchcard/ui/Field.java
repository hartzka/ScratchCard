package kh.scratchcard.ui;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import kh.scratchcard.domain.ScratchCard;
import org.uncommons.maths.random.MersenneTwisterRNG;

/**
 * Raaputuskenttäluokka. Sisältää tiedot ja grafiikat kentän kuvioista ja
 * tiedoista. Mahdollistaa raaputuskenttien raaputustoiminnallisuuden ja
 * avaamisen.
 */
public class Field extends Parent {

    private int x;
    private int x1;
    private int y;
    private int width;
    private int height;
    private int marginImage;
    private int imageHeight;
    private MersenneTwisterRNG mtrng;
    private ImageView view;
    private ImageView view2;
    private Canvas canvas;
    private int colorMode;
    private GraphicsContext gc;
    private String text;
    private boolean[][] opened;
    private int boards;
    private int openedCount;
    private ScratchCard sc;
    private boolean revealed;
    private boolean doubleLocked;
    private boolean lock;
    private int doubleImage;

    public Field(ScratchCard sc, int x, int y, final int width, int height) {
        this.sc = sc;
        this.x = x;
        this.x1 = x;
        this.y = y;
        this.width = width;
        this.height = height;
        lock = false;
        this.doubleImage = 0;
        doubleLocked = false;
        this.revealed = false;
        this.mtrng = new MersenneTwisterRNG();
        view2 = new ImageView();

        opened = new boolean[2000][2000];
        initializeOpened();
        boards = width * height;
        openedCount = 0;
        view = new ImageView();
        marginImage = width / 5;
        imageHeight = height - marginImage;
        view.setX(x + marginImage);
        view.setY(y + marginImage);

        canvas = new Canvas(width, height);
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
        gc = canvas.getGraphicsContext2D();
        text = "";

        gc.fillRect(0, 0, width, height);

        gc.setFill(Color.WHITESMOKE);
        gc.fillText(text, width / 2, height / 2);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

    }

    /**
     * Alustaa kentän asetukset
     *
     * @param number alustettavan värin numero. 1-3: käytetään normaalissa
     * pelissä, 4: käytössä tuplauksessa.
     *
     */
    public void initialize(int number) {
        this.colorMode = number; // 4 = double mode

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                canvas.setCursor(Cursor.CROSSHAIR);
                if (!lock) {
                    if (number == 4 && !doubleLocked) {
                        sc.setDoubleLocked();
                        doubleLocked = true;
                    }
                    gc.clearRect(event.getX() - width / 24, event.getY() - width / 12, width / 12, width / 6);
                    for (int i = (int) event.getY() - width / 12; i <= event.getY() + width / 12; i++) {
                        for (int j = (int) event.getX() - width / 24; j <= event.getX() + width / 24; j++) {
                            if (i >= 0 && j >= 0) {
                                if (opened[i][j] == false) {
                                    opened[i][j] = true;
                                    openedCount++;
                                    if (openedCount > 0.7 * boards) {
                                        open();
                                    }
                                }
                            }
                        }
                    }
                    gc.clearRect(event.getX() - width / 8, event.getY() - width / 20, width / 4, width / 10);
                    for (int i = (int) event.getY() - width / 20; i <= event.getY() + width / 20; i++) {
                        for (int j = (int) event.getX() - width / 8; j <= event.getX() + width / 8; j++) {
                            if (i >= 0 && j >= 0) {
                                if (opened[i][j] == false) {
                                    opened[i][j] = true;
                                    openedCount++;
                                    if (openedCount > 0.65 * boards) {
                                        open();
                                    }
                                }
                            }
                        }
                    }
                    gc.clearRect(event.getX() - width / 10, event.getY() - width / 10, width / 5, width / 5);
                    for (int i = (int) event.getY() - width / 10; i <= event.getY() + width / 10; i++) {
                        for (int j = (int) event.getX() - width / 10; j <= event.getX() + width / 10; j++) {
                            if (i >= 0 && j >= 0) {
                                if (opened[i][j] == false) {
                                    opened[i][j] = true;
                                    openedCount++;
                                    if (openedCount > 0.65 * boards) {
                                        open();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        String path = "/images/bg";
        int rand = mtrng.nextInt(18) + 1;
        if (rand > 1) {
            path += Integer.toString(rand);
        }
        path += ".png";
        if (number == 4) {
            path += "l";
        }
        Image img = ScratchCard.images.get(path);
        if (img == null) {
            if (path.endsWith("l")) {
                path = path.substring(0, path.length() - 1);
            }
            img = new Image(path, width, height, false, false, true);
            if (number == 4) {
                path += "l";
            }
            ScratchCard.images.put(path, img);
        }
        view2 = new ImageView(img);
        view2.setX(x);
        view2.setY(y);
        getChildren().addAll(view2, view);
        getChildren().add(canvas);
    }

    private void initializeOpened() {
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                opened[i][j] = false;
            }
        }
    }

    private void setCanvas(Canvas canvas) {
        if (colorMode == 1) {
            gc.setFill(Color.FORESTGREEN);
        } else if (colorMode == 2) {
            gc.setFill(Color.DEEPSKYBLUE);
        } else if (colorMode == 3) {
            gc.setFill(Color.DARKMAGENTA);
        } else if (colorMode == 4) {
            gc.setFill(Color.BLUEVIOLET);
        }
        gc.fillRect(0, 0, width, height);
        if (colorMode == 4) {
            gc.setStroke(Color.MINTCREAM);
        } else {
            gc.setStroke(Color.GRAY);
        }

        gc.save();
        gc.transform(new Affine(new Rotate(45, width / 2, height / 2)));
        gc.strokeRect(width / 4, height / 4, width / 2, height / 2);
        gc.restore();

        gc.strokeRect(width / 2 - (height * Math.sqrt(2) / 4), height / 2 - (height * Math.sqrt(2) / 4), height * Math.sqrt(2) / 2, height * Math.sqrt(2) / 2);
        gc.strokeRect(width / 2 - (height * Math.sqrt(2) / 4) + height * Math.sqrt(2) / 8, height / 2 - (height * Math.sqrt(2) / 4) + height * Math.sqrt(2) / 8, height * Math.sqrt(2) / 4, height * Math.sqrt(2) / 4);
    }

    private Image getImage(int number) {
        Image image = null;
        String path = "images/";
        int size = ScratchCard.imageSize;
        if (colorMode == 4) {
            path += ("t" + Integer.toString(number) + ".png");
            size = ScratchCard.doubleImageSize;
        } else {
            if (number == 1) {
                path += "orange.png";
            } else if (number == 2) {
                path += "strawberry.png";
            } else if (number == 3) {
                path += "plum.png";
            } else if (number == 4) {
                path += "cherry.png";
            } else if (number == 5) {
                path += "melon.png";
            } else if (number == 6) {
                path += "banana.png";
            } else if (number == 7) {
                path += "pineapple.png";
            } else if (number == 8) {
                path += "pear.png";
            } else {
                path += "grapes.png";
            }
        }
        image = ScratchCard.images.get(path);
        if (image == null) {
            image = new Image(path, size, size, false, false, true);
            ScratchCard.images.put(path, image);
        }
        return image;
    }

    /**
     * Alustaa kentän harmaan taustan tietyllä kuvalla ja asettaa kentän
     * symbolin.
     *
     * @param im Symbolin numero
     *
     */
    public void initializeBackGround(int im) {
        if (this.colorMode == 4) {
            this.doubleImage = im;
        }
        doubleLocked = false;
        lock = false;
        String path = "images/bg";
        int r = mtrng.nextInt(18) + 1;
        if (r > 1) {
            path += Integer.toString(r);
        }
        path += ".png";
        if (colorMode == 4) {
            path += "l";
        }
        Image img = ScratchCard.images.get(path);
        if (img == null && width > 0) {
            if (colorMode == 4) {
                path = path.substring(0, path.length() - 1);
            }
            img = new Image(path, width, height, false, false, true);
            if (colorMode == 4) {
                path += "l";
            }
            ScratchCard.images.put(path, img);
        }
        if (width > 0) {
            view2.setImage(img);
            view.setImage(getImage(im));
        }
        revealed = false;
        setCanvas(canvas);
    }

    /**
     * Käsittelee kentän avaamisen
     */
    public void open() {
        if (!revealed) {
            gc.clearRect(0, 0, width, height);
            openedCount = 0;
            lock = true;
            initializeOpened();
            revealed = true;
            if (colorMode == 4) {
                sc.doubleFieldOpened();
            } else {
                sc.checkOpened();
            }
        }
    }

    /**
     * Asettaa kentän vasemman yläkulman x-koordinaatin
     *
     * @param x x-koordinaatti
     *
     */
    public void setX(double x) {
        if (x < this.x - 1) {
            this.x = x1;
        } else {
            this.x = (int) x;
        }
        canvas.setTranslateX(this.x);
        view.setX(this.x + marginImage);
        view2.setX(this.x);

    }

    public int getX() {
        return this.x;
    }

    public boolean getOpened() {
        return revealed;
    }

    public void setRevealed(boolean b) {
        this.revealed = b;
    }

    public int getDoubleImageNumber() {
        return this.doubleImage;
    }

    public void setColorMode(int i) {
        this.colorMode = i;
    }
}
