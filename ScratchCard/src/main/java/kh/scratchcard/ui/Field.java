package kh.scratchcard.ui;

import java.util.Random;

import javafx.event.EventHandler;
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

public class Field extends Parent {

    private int x;
    private int x1;
    private int y;
    private int width;
    private int height;
    private int marginImage;
    private int imageHeight;
    private Image image;
    private Random random;
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

    public Field(ScratchCard sc, final int num, int x, int y, final int wi, int he) {
        this.sc = sc;
        this.x = x;
        this.x1 = x;
        this.y = y;
        this.width = wi;
        this.height = he;
        lock = false;
        this.colorMode = num; // 4 = double mode
        doubleLocked = false;
        this.revealed = false;
        this.random = new Random();

        String path = "/images/tausta";
        int rand = random.nextInt(18) + 1;
        if (rand > 1) {
            path += Integer.toString(rand);
        }
        path += ".png";
        if (num == 4) {
            path += "l";
        }
        Image img = ScratchCard.images.get(path);
        if (img == null) {
            if (path.endsWith("l")) {
                path = path.substring(0, path.length() - 1);
            }
            img = new Image(path, wi, he, false, false, true);
            if (num == 4) {
                path += "l";
            }
            ScratchCard.images.put(path, img);
        }
        view2 = new ImageView(img);
        view2.setX(x);
        view2.setY(y);

        opened = new boolean[2000][2000];
        initializeOpened();
        boards = wi * he;
        openedCount = 0;
        view = new ImageView();
        marginImage = wi / 5;
        imageHeight = he - marginImage;
        view.setX(x + marginImage);
        view.setY(y + marginImage);
        getChildren().addAll(view2, view);
        canvas = new Canvas(wi, he);
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
        gc = canvas.getGraphicsContext2D();
        text = "";
        if (num == 1) {
            gc.setFill(Color.GREEN);
        } else if (num == 2) {
            gc.setFill(Color.RED);
        } else if (num == 3) {
            gc.setFill(Color.HOTPINK);
        } else if (num == 4) {
            gc.setFill(Color.BLUEVIOLET);
        }
        gc.fillRect(0, 0, wi, he);

        gc.setFill(Color.WHITESMOKE);
        gc.fillText(text, wi / 2, he / 2);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (!lock) {
                    if (num == 4 && !doubleLocked) {
                        doubleLocked = true;
                    }
                    gc.clearRect(e.getX() - wi / 20, e.getY() - wi / 8, wi / 10, wi / 4);
                    for (int i = (int) e.getY() - wi / 8; i <= e.getY() + wi / 8; i++) {
                        for (int j = (int) e.getX() - wi / 20; j <= e.getX() + wi / 20; j++) {
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
                    gc.clearRect(e.getX() - wi / 8, e.getY() - wi / 20, wi / 4, wi / 10);
                    for (int i = (int) e.getY() - wi / 20; i <= e.getY() + wi / 20; i++) {
                        for (int j = (int) e.getX() - wi / 8; j <= e.getX() + wi / 8; j++) {
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
                    gc.clearRect(e.getX() - wi / 10, e.getY() - wi / 10, wi / 5, wi / 5);
                    for (int i = (int) e.getY() - wi / 10; i <= e.getY() + wi / 10; i++) {
                        for (int j = (int) e.getX() - wi / 10; j <= e.getX() + wi / 10; j++) {
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
            gc.setFill(Color.GREEN);
        } else if (colorMode == 2) {
            gc.setFill(Color.RED);
        } else if (colorMode == 3) {
            gc.setFill(Color.HOTPINK);
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

        if (colorMode == 4) {
            if (number == 1) {
                image = ScratchCard.images.get("images/t1.png");
                if (image == null) {
                    image = new Image("images/t1.png", ScratchCard.doubleImageSize, ScratchCard.doubleImageSize, false, false, true);
                    ScratchCard.images.put("images/t1.png", image);
                }
            } else if (number == 2) {
                image = ScratchCard.images.get("images/t2.png");
                if (image == null) {
                    image = new Image("images/t2.png", ScratchCard.doubleImageSize, ScratchCard.doubleImageSize, false, false, true);
                    ScratchCard.images.put("images/t2.png", image);
                }
            } else if (number == 3) {
                image = ScratchCard.images.get("images/t3.png");
                if (image == null) {
                    image = new Image("images/t3.png", ScratchCard.doubleImageSize, ScratchCard.doubleImageSize, false, false, true);
                    ScratchCard.images.put("images/t3.png", image);
                }
            } else if (number == 4) {
                image = ScratchCard.images.get("images/t4.png");
                if (image == null) {
                    image = new Image("images/t4.png", ScratchCard.doubleImageSize, ScratchCard.doubleImageSize, false, false, true);
                    ScratchCard.images.put("images/t4.png", image);
                }
            }
        } else {
            if (number == 1) {
                image = ScratchCard.images.get("images/orange.png");
                if (image == null) {
                    image = new Image("images/orange.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/orange.png", image);
                }
            } else if (number == 2) {
                image = ScratchCard.images.get("images/strawb.png");
                if (image == null) {
                    image = new Image("images/strawb.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/strawb.png", image);
                }
            } else if (number == 3) {
                image = ScratchCard.images.get("images/luuumu.png");
                if (image == null) {
                    image = new Image("images/luumu.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/luumu.png", image);
                }
            } else if (number == 4) {
                image = ScratchCard.images.get("images/cherry.png");
                if (image == null) {
                    image = new Image("images/cherry.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/cherry.png", image);
                }
            } else if (number == 5) {
                image = ScratchCard.images.get("images/melon.png");
                if (image == null) {
                    image = new Image("images/melon.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/melon.png", image);
                }
            } else if (number == 6) {
                image = ScratchCard.images.get("images/banana.png");
                if (image == null) {
                    image = new Image("images/banana.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/banana.png", image);
                }
            } else if (number == 7) {
                image = ScratchCard.images.get("images/ananas.png");
                if (image == null) {
                    image = new Image("images/ananas.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/ananas.png", image);
                }
            } else if (number == 8) {
                image = ScratchCard.images.get("images/pear.png");
                if (image == null) {
                    image = new Image("images/pear.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/pear.png", image);
                }
            } else {
                image = ScratchCard.images.get("images/grapes.png");
                if (image == null) {
                    image = new Image("images/grapes.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
                    ScratchCard.images.put("images/grapes.png", image);
                }
            }
        }
        return image;
    }

    public void initialize(int im) {
        doubleLocked = false;
        lock = false;
        String path = "images/tausta";
        int r = random.nextInt(18) + 1;
        if (r > 1) {
            path += Integer.toString(r);
        }
        path += ".png";
        if (colorMode == 4) {
            path += "l";
        }
        Image img = ScratchCard.images.get(path);
        if (img == null) {
            if (colorMode == 4) {
                path = path.substring(0, path.length() - 1);
            }
            img = new Image(path, width, height, false, false, true);
            if (colorMode == 4) {
                path += "l";
            }
            ScratchCard.images.put(path, img);
        }
        view2.setImage(img);
        view.setImage(getImage(im));
        revealed = false;
        setCanvas(canvas);
    }

    public void open() {
        if (!revealed) {
            gc.clearRect(0, 0, width, height);
            openedCount = 0;
            lock = true;
            initializeOpened();
            revealed = true;
        }
    }

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
}
