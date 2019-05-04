package kh.scratchcard.ui;

import java.util.Arrays;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import kh.scratchcard.domain.ScratchCard;

/**
 * Käyttöliittymän voittotaululuokka, sisältää voittotauluun liittyvät grafiikat
 * ja tiedot.
 */
public class WinTable extends Parent {

    private int x;
    private int y;
    private int width;
    private int height;

    public WinTable(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        Rectangle re = new Rectangle(x, y, width + 3, height);
        re.setFill(Color.MIDNIGHTBLUE);

        int font;
        if (width > 400) {
            font = 22;
        } else if (width > 300) {
            font = 18;
        } else if (width > 200) {
            font = 16;
        } else {
            font = 14;
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

        List<Text> texts = Arrays.asList(t1a, t2a, t3a, t3b, t3c, t4a, t4b, t5a, t5b, t6a, t7a, t8a, t9a);
        for (Text t : texts) {
            t.setFont(Font.font("Verdana", font));
        }

        ImageView view1 = getImageView(0, 0, "grapes", Arrays.asList(t1a), Arrays.asList("3X 50000.00"), Arrays.asList(-10));
        ImageView view2 = getImageView(0, 1, "pear", Arrays.asList(t2a), Arrays.asList("3X 5000.00"), Arrays.asList(0));
        ImageView view3 = getImageView(0, 2, "pineapple", Arrays.asList(t3a, t3b, t3c), Arrays.asList("3X 500.00", "2X 15.00", "1X 3.00"), Arrays.asList(0, 0, 0));
        ImageView view4 = getImageView(1, 0, "banana", Arrays.asList(t4a, t4b), Arrays.asList("3X 100.00", "1X 10.00"), Arrays.asList(0, 0, 0));
        ImageView view5 = getImageView(1, 1, "melon", Arrays.asList(t5a, t5b), Arrays.asList("3X 200.00", "2X 6.00"), Arrays.asList(0, 0, 0));
        ImageView view6 = getImageView(1, 2, "cherry", Arrays.asList(t6a), Arrays.asList("3X 8.00"), Arrays.asList(5));
        ImageView view7 = getImageView(2, 0, "plum", Arrays.asList(t7a), Arrays.asList("3X 5.00"), Arrays.asList(3));
        ImageView view8 = getImageView(2, 1, "strawberry", Arrays.asList(t8a), Arrays.asList("3X 4.00"), Arrays.asList(4));
        ImageView view9 = getImageView(2, 2, "orange", Arrays.asList(t9a), Arrays.asList("3X 2.00"), Arrays.asList(4));

        getChildren().addAll(re, view1, t1a, view2, t2a, view3, t3a, t3b, t3c, view4, t4a, t4b, view5, t5a, t5b, view6, t6a, view7, t7a, view8, t8a, view9, t9a);
    }

    private ImageView getImageView(int row, int column, String symbol, List<Text> texts, List<String> strings, List<Integer> xCordinates) {
        int x1 = x + ((1 + (column * 4)) * width / 12);
        int y1 = y + ((1 + (row * 4)) * height / 12);
        String path = "images/" + symbol + ".png";
        Image image = ScratchCard.images.get(path);
        if (image == null) {
            image = new Image(path, ScratchCard.imageSize, ScratchCard.imageSize, false, false, true);
            ScratchCard.images.put(path, image);
        }
        ImageView view = new ImageView(image);
        view.setX(x1);
        view.setY(y1);

        for (int i = 0; i < texts.size(); i++) {
            Text t = texts.get(i);
            t.setText(strings.get(i));
            t.setX(x + xCordinates.get(i) + ((1 + column * 2) * width / 6) - 5 * t.getLayoutBounds().getWidth() / 12);
            t.setY(y + ((3 + (row * 4)) * height / 12) + ((1 + i * 2) * t.getLayoutBounds().getHeight() / 2));
            t.setFill(Color.CHARTREUSE);
        }
        return view;
    }
}
