package kh.scratchcard.ui;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import kh.scratchcard.domain.ScratchCard;

/**
 * Käyttöliittymän voittotaululuokka, sisältää voittotauluun liittyvät grafiikat ja tiedot.
 */
public class WinTable extends Parent {

    private int x;
    private int y;
    private int width;
    private int height;

    public WinTable(int x, int y, int width, int he) {
        this.x = x;
        this.y = y;
        this.height = he;
        this.width = width;
        Rectangle re = new Rectangle(x, y, width + 3, he);
        re.setFill(Color.MIDNIGHTBLUE);

        int font;
        if (width > 400) {
            font = 18;
        } else if (width > 300) {
            font = 14;
        } else if (width > 200) {
            font = 12;
        } else {
            font = 10;
        }

        Text t1a = new Text(); //biggest win
        Text t2a = new Text(); //symbols 1-9 
        Text t3a = new Text(); //a, b, c symbolamount in range 1-3
        Text t3b = new Text(); //e.g. 3a = 3*pineapple, 3b = 2*pineapple, 3c = 1*pineapple
        Text t3c = new Text();
        Text t4a = new Text();
        Text t4b = new Text();
        Text t5a = new Text();
        Text t5b = new Text();
        Text t6a = new Text();
        Text t7a = new Text();
        Text t8a = new Text();
        Text t9a = new Text(); //lowest win

        t1a.setFont(Font.font("Verdana", font));
        t2a.setFont(Font.font("Verdana", font));
        t3b.setFont(Font.font("Verdana", font));
        t3c.setFont(Font.font("Verdana", font));
        t3a.setFont(Font.font("Verdana", font));
        t5b.setFont(Font.font("Verdana", font));
        t4a.setFont(Font.font("Verdana", font));
        t4b.setFont(Font.font("Verdana", font));
        t5a.setFont(Font.font("Verdana", font));
        t6a.setFont(Font.font("Verdana", font));
        t7a.setFont(Font.font("Verdana", font));
        t8a.setFont(Font.font("Verdana", font));
        t9a.setFont(Font.font("Verdana", font));

        int x1 = x + width / 12;
        int y1 = y + he / 12;
        Image image = ScratchCard.images.get("images/grapes.png");
        if (image == null) {
            image = new Image("images/grapes.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/grapes.png", image);
        }
        ImageView view1 = new ImageView(image);
        view1.setX(x1);
        view1.setY(y1);
        t1a.setText("3X 50000.00");
        t1a.setX(x + (width / 6) - (t1a.getLayoutBounds().getWidth() / 2));
        t1a.setY(y + he / 4 + t1a.getLayoutBounds().getHeight() / 2);
        t1a.setFill(Color.CHARTREUSE);

        int x2 = x + 5 * width / 12;
        int y2 = y + he / 12;
        image = ScratchCard.images.get("images/pear.png");
        if (image == null) {
            image = new Image("images/pear.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/pear.png", image);
        }
        ImageView view2 = new ImageView(image);
        view2.setX(x2);
        view2.setY(y2);
        t2a.setText("3X 5000.00");
        t2a.setX(x + width / 2 - 5 * t2a.getLayoutBounds().getWidth() / 12);
        t2a.setY(y + he / 4 + t2a.getLayoutBounds().getHeight() / 2);
        t2a.setFill(Color.CHARTREUSE);

        int x3 = x + (9 * width / 12);
        int y3 = y + he / 12;
        image = ScratchCard.images.get("images/pineapple.png");
        if (image == null) {
            image = new Image("images/pineapple.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/pineapple.png", image);
        }
        ImageView view3 = new ImageView(image);
        view3.setX(x3);
        view3.setY(y3);
        t3a.setText("3X 500.00");
        t3a.setX(x + 5 * width / 6 - 5 * t3a.getLayoutBounds().getWidth() / 12);
        t3a.setY(y + he / 4 + t3a.getLayoutBounds().getHeight() / 2);
        t3a.setFill(Color.CHARTREUSE);
        t3b.setText("2X 15.00");
        t3b.setX(x + 5 * width / 6 - 5 * t3b.getLayoutBounds().getWidth() / 12);
        t3b.setY(y + he / 4 + 3 * t3b.getLayoutBounds().getHeight() / 2);
        t3b.setFill(Color.CHARTREUSE);
        t3c.setText("1X 3.00");
        t3c.setX(x + 5 * width / 6 - 5 * t3c.getLayoutBounds().getWidth() / 12);
        t3c.setY(y + he / 4 + 5 * t3c.getLayoutBounds().getHeight() / 2);
        t3c.setFill(Color.CHARTREUSE);

        int x4 = x + width / 12;
        int y4 = y + 5 * he / 12;
        image = ScratchCard.images.get("images/banana.png");
        if (image == null) {
            image = new Image("images/banana.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/banana.png", image);
        }
        ImageView view4 = new ImageView(image);
        view4.setX(x4);
        view4.setY(y4);
        t4a.setText("3X 100.00");
        t4a.setX(x + width / 6 - 5 * t4a.getLayoutBounds().getWidth() / 12);
        t4a.setY(y + 7 * he / 12 + t4a.getLayoutBounds().getHeight() / 2);
        t4a.setFill(Color.CHARTREUSE);
        t4b.setText("1X 10.00");
        t4b.setX(x + width / 6 - 5 * t4b.getLayoutBounds().getWidth() / 12);
        t4b.setY(y + 7 * he / 12 + 3 * t4b.getLayoutBounds().getHeight() / 2);
        t4b.setFill(Color.CHARTREUSE);

        int x5 = x + 5 * width / 12;
        int y5 = y + 5 * he / 12;
        image = ScratchCard.images.get("images/melon.png");
        if (image == null) {
            image = new Image("images/melon.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/melon.png", image);
        }
        ImageView view5 = new ImageView(image);
        view5.setX(x5);
        view5.setY(y5);
        t5a.setText("3X 20.00");
        t5a.setX(x + width / 2 - 5 * t5a.getLayoutBounds().getWidth() / 12);
        t5a.setY(y + 7 * he / 12 + t5a.getLayoutBounds().getHeight() / 2);
        t5a.setFill(Color.CHARTREUSE);
        t5b.setText("2X 6.00");
        t5b.setX(x + width / 2 - 5 * t5b.getLayoutBounds().getWidth() / 12);
        t5b.setY(y + 7 * he / 12 + 3 * t5b.getLayoutBounds().getHeight() / 2);
        t5b.setFill(Color.CHARTREUSE);

        int x6 = x + 9 * width / 12;
        int y6 = y + 5 * he / 12;
        image = ScratchCard.images.get("images/cherry.png");
        if (image == null) {
            image = new Image("images/cherry.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("resourcess/cherry.png", image);
        }
        ImageView view6 = new ImageView(image);
        view6.setX(x6);
        view6.setY(y6);
        t6a.setText("3X 8.00");
        t6a.setX(x + 5 * width / 6 - 5 * t6a.getLayoutBounds().getWidth() / 12);
        t6a.setY(y + 7 * he / 12 + t6a.getLayoutBounds().getHeight() / 2);
        t6a.setFill(Color.CHARTREUSE);

        int x7 = x + width / 12;
        int y7 = y + 9 * he / 12;
        image = ScratchCard.images.get("images/plum.png");
        if (image == null) {
            image = new Image("images/plum.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/plum.png", image);
        }
        ImageView view7 = new ImageView(image);
        view7.setX(x7);
        view7.setY(y7);
        t7a.setText("3X 5.00");
        t7a.setX(x + width / 6 - 5 * t7a.getLayoutBounds().getWidth() / 12);
        t7a.setY(y + 11 * he / 12 + t7a.getLayoutBounds().getHeight() / 2);
        t7a.setFill(Color.CHARTREUSE);

        int x8 = x + 5 * width / 12;
        int y8 = y + 9 * he / 12;
        image = ScratchCard.images.get("images/strawberry.png");
        if (image == null) {
            image = new Image("images/strawberry.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/strawberry.png", image);
        }
        ImageView view8 = new ImageView(image);
        view8.setX(x8);
        view8.setY(y8);
        t8a.setText("3X 4.00");
        t8a.setX(x + width / 2 - 5 * t8a.getLayoutBounds().getWidth() / 12);
        t8a.setY(y + 11 * he / 12 + t8a.getLayoutBounds().getHeight() / 2);
        t8a.setFill(Color.CHARTREUSE);

        int x9 = x + 9 * width / 12;
        int y9 = y + 9 * he / 12;
        image = ScratchCard.images.get("images/orange.png");
        if (image == null) {
            image = new Image("images/orange.png", ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put("images/orange.png", image);
        }
        ImageView view9 = new ImageView(image);
        view9.setX(x9);
        view9.setY(y9);
        t9a.setText("3X 2.00");
        t9a.setX(x + 5 * width / 6 - 5 * t9a.getLayoutBounds().getWidth() / 12);
        t9a.setY(y + 11 * he / 12 + t9a.getLayoutBounds().getHeight() / 2);
        t9a.setFill(Color.CHARTREUSE);

        getChildren().addAll(re, view1, t1a, view2, t2a, t3b, t3c, view3, t3a, t5b, view4, t4a, t4b, view5, t5a, view6, t6a, view7, t7a, view8, t8a, view9, t9a);

    }
}
