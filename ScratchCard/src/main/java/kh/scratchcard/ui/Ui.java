package kh.scratchcard.ui;

import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kh.scratchcard.domain.ScratchCard;

public class Ui extends Application {

    private Pane root = new Pane();
    Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    private double width = visualBounds.getWidth();
    private double height = visualBounds.getHeight();

    private Text unbelievable2 = new Text("");
    private Text stats = new Text("");
    private Text win;

    private int fieldWidth = (int) (0.19 * height);

    private int marginx1 = (int) (0.6 * (0.35 * width - (1.5 * fieldWidth)));

    private int marginx2 = (int) ((0.4) * (0.35 * width - (1.5 * fieldWidth)));
    private int marginy1 = (int) (0.05 * height);
    private int marginy2 = (int) (0.04 * height);
    Rectangle rect1 = new Rectangle(marginx1 - 4, marginy1 - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect2 = new Rectangle(marginx1 + fieldWidth + marginx2 - 4, marginy1 - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect3 = new Rectangle(marginx1 + 2 * fieldWidth + 2 * marginx2 - 4, marginy1 - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect4 = new Rectangle(marginx1 - 4, marginy1 + marginy2 + fieldWidth - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect5 = new Rectangle(marginx1 + fieldWidth + marginx2 - 4, marginy1 + marginy2 + fieldWidth - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect6 = new Rectangle(marginx1 + 2 * fieldWidth + 2 * marginx2 - 4, marginy1 + marginy2 + fieldWidth - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect7 = new Rectangle(marginx1 - 4, marginy1 + 2 * fieldWidth + 2 * marginy2 - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect8 = new Rectangle(marginx1 + fieldWidth + marginx2 - 4, marginy1 + 2 * fieldWidth + 2 * marginy2 - 4, fieldWidth + 8, fieldWidth + 8);
    Rectangle rect9 = new Rectangle(marginx1 + 2 * fieldWidth + 2 * marginx2 - 4, marginy1 + 2 * fieldWidth + 2 * marginy2 - 4, fieldWidth + 8, fieldWidth + 8);

    Rectangle whiteRect1;
    Rectangle whiteRect2;
    Rectangle whiteRect3;
    Rectangle whiteRect4;

    Rectangle doubleBgRect1;
    Rectangle doubleBgRect2;
    Rectangle doubleBgRect3;
    Rectangle doubleBgRect4;

    ToggleButton doubleButton;

    Field field1;
    Field field2;
    Field field3;
    Field field4;
    Field field5;
    Field field6;
    Field field7;
    Field field8;
    Field field9;

    Rectangle line1;
    Rectangle line2;
    Rectangle line3;
    Rectangle line4;
    Rectangle line5;
    Rectangle line6;
    Rectangle line7;
    Rectangle line8;
    Rectangle line9;
    Rectangle line10;
    Rectangle line11;
    Rectangle line12;

    ImageView doubleImage1;
    ImageView doubleImage2;
    ImageView doubleImage3;
    ImageView doubleImage4;
    Field doubleField;
    VBox doubleChoice1;
    VBox doubleChoice2;
    VBox doubleChoice3;
    VBox doubleChoice4;

    Rectangle infobg;
    Text upper_margin1 = new Text("");
    Text upper_margin2 = new Text("");
    Text upper_margin3 = new Text("");
    Text upper_margin4 = new Text("");
    Text lower_margin1 = new Text("");
    Text lower_margin2 = new Text("");
    Text lower_margin3 = new Text("");
    Text lower_margin4 = new Text("");
    Text money_text = new Text("   Money:");
    Text money_total_text = new Text("	   Total:");
    Text money_session_text = new Text("	   This session:");
    Text played_text = new Text("   Played:");
    Text played_total_text = new Text("	   Total:");
    Text played_session_text = new Text("	   This session:");
    Text wins_text = new Text("   Wins:");
    Text win_50000_text = new Text("	   50000.00:");
    Text win_5000_text = new Text("	   5000.00:");
    Text win_500_text = new Text("	   500.00:");
    Text win_100_text = new Text("	   100.00:");
    Text win_20_text = new Text("	   20.00:");
    Text win_15_text = new Text("	   15.00:");
    Text win_10_text = new Text("	   10.00:");
    Text win_8_text = new Text("	   8.00:");
    Text win_6_text = new Text("	   6.00:");
    Text win_5_text = new Text("	   5.00:");
    Text win_4_text = new Text("	   4.00:");
    Text win_3_text = new Text("	   3.00:");
    Text win_2_text = new Text("	   2.00:");
    Text total_wins_text = new Text("	   Total wins:");
    Text winning_cards_text = new Text("	   Winning cards:");
    Text double_up_text = new Text("   Double Up:");
    Text double_up_wins_text = new Text("	   Wins:");
    Text double_up_losses_text = new Text("	   Losses:");
    Text double_up_max_win_text = new Text("	   Max win:");
    Text double_up_best_result_text = new Text("	   Best result:");

    Text money_infotext = new Text("");
    Text money_total_infotext = new Text("");
    Text money_session_infotext = new Text("");
    Text played_infotext = new Text("");
    Text played_total_infotext = new Text("");
    Text played_session_infotext = new Text("");
    Text wins_infotext = new Text("");
    Text win_50000_infotext = new Text("");
    Text win_5000_infotext = new Text("");
    Text win_500_infotext = new Text("");
    Text win_100_infotext = new Text("");
    Text win_20_infotext = new Text("");
    Text win_15_infotext = new Text("");
    Text win_10_infotext = new Text("");
    Text win_8_infotext = new Text("");
    Text win_6_infotext = new Text("");
    Text win_5_infotext = new Text("");
    Text win_4_infotext = new Text("");
    Text win_3_infotext = new Text("");
    Text win_2_infotext = new Text("");
    Text total_wins_infotext = new Text("");
    Text winning_cards_infotext = new Text("");
    Text double_up_infotext = new Text("");
    Text double_up_wins_infotext = new Text("");
    Text double_up_losses_infotext = new Text("");
    Text double_up_max_win_infotext = new Text("");
    Text double_up_best_result_infotext = new Text("");

    Text more = new Text("More stats");

    SimpleIntegerProperty money_total;
    SimpleIntegerProperty money_session;
    SimpleIntegerProperty played_total = new SimpleIntegerProperty(0);
    SimpleIntegerProperty played_session = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_50000 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_5000 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_500 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_100 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_20 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_15 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_10 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_8 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_6 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_5 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_4 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_3 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win_2 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty total_wins = new SimpleIntegerProperty(0);
    SimpleIntegerProperty winning_cards = new SimpleIntegerProperty(0);
    SimpleIntegerProperty double_up_wins = new SimpleIntegerProperty(0);
    SimpleIntegerProperty double_up_losses = new SimpleIntegerProperty(0);
    SimpleIntegerProperty double_up_max_win = new SimpleIntegerProperty(0);
    Text double_up_best_result = new Text("   0.00 --> 0.00   ");
    private ScratchCard sc;

    public Ui(ScratchCard sc) {
        this.sc = sc;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        win = sc.getWin();
        money_total = sc.getMoneyTotal();
        money_session = sc.getMoneySession();
        money_total_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(money_total.asString().concat(".00")));
        money_session_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(money_session.asString().concat(".00")));
        played_total_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(played_total.asString()));
        played_session_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(played_session.asString()));
        win_50000_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_50000.asString()));
        win_5000_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_5000.asString()));
        win_500_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_500.asString()));
        win_100_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_100.asString()));
        win_20_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_20.asString()));
        win_15_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_15.asString()));
        win_10_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_10.asString()));
        win_8_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_8.asString()));
        win_6_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_6.asString()));
        win_5_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_5.asString()));
        win_4_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_4.asString()));
        win_3_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_3.asString()));
        win_2_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(win_2.asString()));
        total_wins_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(total_wins.asString().concat(".00")));
        winning_cards_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(winning_cards.asString()));
        double_up_wins_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(double_up_wins.asString().concat(".00")));
        double_up_losses_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(double_up_losses.asString().concat(".00")));
        double_up_max_win_infotext.textProperty().bind(new SimpleStringProperty("   ").concat(double_up_max_win.asString().concat(".00")));

        int font;
        if (width > 700) {
            font = 18;
        } else if (width > 600) {
            font = 15;
        } else if (width > 500) {
            font = 13;
        } else {
            font = 11;
        }

        money_text.setFill(Color.ORANGE);
        money_text.setFont(Font.font("Verdana", font));
        money_total_text.setFill(Color.MINTCREAM);
        money_total_text.setFont(Font.font("Verdana", font));
        money_session_text.setFill(Color.MINTCREAM);
        money_session_text.setFont(Font.font("Verdana", font));
        played_text.setFill(Color.ORANGE);
        played_text.setFont(Font.font("Verdana", font));
        played_total_text.setFill(Color.MINTCREAM);
        played_total_text.setFont(Font.font("Verdana", font));
        played_session_text.setFill(Color.MINTCREAM);
        played_session_text.setFont(Font.font("Verdana", font));
        wins_text.setFill(Color.ORANGE);
        wins_text.setFont(Font.font("Verdana", font));
        win_50000_text.setFill(Color.MINTCREAM);
        win_50000_text.setFont(Font.font("Verdana", font));
        win_5000_text.setFill(Color.MINTCREAM);
        win_5000_text.setFont(Font.font("Verdana", font));
        win_500_text.setFill(Color.MINTCREAM);
        win_500_text.setFont(Font.font("Verdana", font));
        win_100_text.setFill(Color.MINTCREAM);
        win_100_text.setFont(Font.font("Verdana", font));
        win_20_text.setFill(Color.MINTCREAM);
        win_20_text.setFont(Font.font("Verdana", font));
        win_15_text.setFill(Color.MINTCREAM);
        win_15_text.setFont(Font.font("Verdana", font));
        win_10_text.setFill(Color.MINTCREAM);
        win_10_text.setFont(Font.font("Verdana", font));
        win_8_text.setFill(Color.MINTCREAM);
        win_8_text.setFont(Font.font("Verdana", font));
        win_6_text.setFill(Color.MINTCREAM);
        win_6_text.setFont(Font.font("Verdana", font));
        win_5_text.setFill(Color.MINTCREAM);
        win_5_text.setFont(Font.font("Verdana", font));
        win_4_text.setFill(Color.MINTCREAM);
        win_4_text.setFont(Font.font("Verdana", font));
        win_3_text.setFill(Color.MINTCREAM);
        win_3_text.setFont(Font.font("Verdana", font));
        win_2_text.setFill(Color.MINTCREAM);
        win_2_text.setFont(Font.font("Verdana", font));
        total_wins_text.setFill(Color.MINTCREAM);
        total_wins_text.setFont(Font.font("Verdana", font));
        winning_cards_text.setFill(Color.MINTCREAM);
        winning_cards_text.setFont(Font.font("Verdana", font));
        double_up_text.setFill(Color.ORANGE);
        double_up_text.setFont(Font.font("Verdana", font));
        double_up_wins_text.setFill(Color.MINTCREAM);
        double_up_wins_text.setFont(Font.font("Verdana", font));
        double_up_losses_text.setFill(Color.MINTCREAM);
        double_up_losses_text.setFont(Font.font("Verdana", font));
        double_up_max_win_text.setFill(Color.MINTCREAM);
        double_up_max_win_text.setFont(Font.font("Verdana", font));
        double_up_best_result_text.setFill(Color.MINTCREAM);
        double_up_best_result_text.setFont(Font.font("Verdana", font));

        money_infotext.setFont(Font.font("Verdana", font));
        money_total_infotext.setFill(Color.MINTCREAM);
        money_total_infotext.setFont(Font.font("Verdana", font));
        money_session_infotext.setFill(Color.MINTCREAM);
        money_session_infotext.setFont(Font.font("Verdana", font));
        played_infotext.setFont(Font.font("Verdana", font));
        played_total_infotext.setFill(Color.MINTCREAM);
        played_total_infotext.setFont(Font.font("Verdana", font));
        played_session_infotext.setFill(Color.MINTCREAM);
        played_session_infotext.setFont(Font.font("Verdana", font));
        wins_infotext.setFont(Font.font("Verdana", font));
        win_50000_infotext.setFill(Color.MINTCREAM);
        win_50000_infotext.setFont(Font.font("Verdana", font));
        win_5000_infotext.setFill(Color.MINTCREAM);
        win_5000_infotext.setFont(Font.font("Verdana", font));
        win_500_infotext.setFill(Color.MINTCREAM);
        win_500_infotext.setFont(Font.font("Verdana", font));
        win_100_infotext.setFill(Color.MINTCREAM);
        win_100_infotext.setFont(Font.font("Verdana", font));
        win_20_infotext.setFill(Color.MINTCREAM);
        win_20_infotext.setFont(Font.font("Verdana", font));
        win_15_infotext.setFill(Color.MINTCREAM);
        win_15_infotext.setFont(Font.font("Verdana", font));
        win_10_infotext.setFill(Color.MINTCREAM);
        win_10_infotext.setFont(Font.font("Verdana", font));
        win_8_infotext.setFill(Color.MINTCREAM);
        win_8_infotext.setFont(Font.font("Verdana", font));
        win_6_infotext.setFill(Color.MINTCREAM);
        win_6_infotext.setFont(Font.font("Verdana", font));
        win_5_infotext.setFill(Color.MINTCREAM);
        win_5_infotext.setFont(Font.font("Verdana", font));
        win_4_infotext.setFill(Color.MINTCREAM);
        win_4_infotext.setFont(Font.font("Verdana", font));
        win_3_infotext.setFill(Color.MINTCREAM);
        win_3_infotext.setFont(Font.font("Verdana", font));
        win_2_infotext.setFill(Color.MINTCREAM);
        win_2_infotext.setFont(Font.font("Verdana", font));
        total_wins_infotext.setFill(Color.MINTCREAM);
        total_wins_infotext.setFont(Font.font("Verdana", font));
        winning_cards_infotext.setFill(Color.MINTCREAM);
        winning_cards_infotext.setFont(Font.font("Verdana", font));
        double_up_infotext.setFont(Font.font("Verdana", font));
        double_up_wins_infotext.setFill(Color.MINTCREAM);
        double_up_wins_infotext.setFont(Font.font("Verdana", font));
        double_up_losses_infotext.setFill(Color.MINTCREAM);
        double_up_losses_infotext.setFont(Font.font("Verdana", font));
        double_up_max_win_infotext.setFill(Color.MINTCREAM);
        double_up_max_win_infotext.setFont(Font.font("Verdana", font));
        double_up_best_result.setFill(Color.MINTCREAM);
        double_up_best_result.setFont(Font.font("Verdana", font));

        unbelievable2.setFont(Font.font("Verdana", font - 2));

        unbelievable2.setVisible(false);

        font++;

        infobg = new Rectangle(0, 0, width, height);
        infobg.setFill(Color.rgb(0, 103, 179));
        ToggleButton closeInfo = new ToggleButton("CLOSE");
        closeInfo.setStyle("-fx-background-color: red");

        HBox h1 = new HBox(0);
        VBox v1 = new VBox();
        v1.getChildren().addAll(upper_margin1, money_text, money_total_text, money_session_text, played_text, played_total_text, played_session_text, double_up_text, double_up_wins_text, double_up_losses_text, double_up_max_win_text, double_up_best_result_text, lower_margin1);
        VBox v2 = new VBox();
        v2.getChildren().addAll(upper_margin2, money_infotext, money_total_infotext, money_session_infotext, played_infotext, played_total_infotext, played_session_infotext, double_up_infotext, double_up_wins_infotext, double_up_losses_infotext, double_up_max_win_infotext, double_up_best_result, lower_margin2);
        HBox h2 = new HBox(5);
        h2.getChildren().addAll(v1, v2);
        VBox v3 = new VBox(7);
        v3.getChildren().addAll(h2, closeInfo);
        v3.setAlignment(Pos.CENTER);
        VBox v4 = new VBox();
        v4.getChildren().addAll(upper_margin3, wins_text, win_2_text, win_3_text, win_4_text, win_5_text, win_6_text, win_8_text, win_10_text, win_15_text, win_20_text, win_100_text, win_500_text, win_5000_text, win_50000_text, total_wins_text, winning_cards_text, lower_margin3);

        VBox v5 = new VBox();
        v5.getChildren().addAll(upper_margin4, wins_infotext, win_50000_infotext, win_5000_infotext, win_500_infotext, win_100_infotext, win_20_infotext, win_15_infotext, win_10_infotext, win_8_infotext, win_6_infotext, win_5_infotext, win_4_infotext, win_3_infotext, win_2_infotext, total_wins_infotext, winning_cards_infotext, lower_margin4);
        closeInfo.setPadding(new Insets(0, width / 30, 0, width / 30));
        
        h1.getChildren().addAll(v4, v5, v3);
        h1.setAlignment(Pos.CENTER);
        VBox v6 = new VBox();
        v6.getChildren().addAll(h1);
        v6.setAlignment(Pos.CENTER);
        final HBox info = new HBox();

        closeInfo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (info.isVisible()) {
                    info.setVisible(false);
                } else {
                    info.setVisible(true);
                }
            }
        });
        info.setVisible(false);
        more.setFill(Color.DARKGOLDENROD);
        more.setFont(Font.font("Verdana", font * 0.9));
        more.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                info.setVisible(true);
            }

        });
        more.setVisible(false);

        stats.textProperty().bind(new SimpleStringProperty("Money: ").concat(money_total.asString()).concat(".00").concat("   "));
        stats.setVisible(false);

        win.setVisible(false);

        win.setFont(Font.font("Verdana", font + 2));
        win.setFill(Color.LIGHTGREEN);
        stats.setFont(Font.font("Verdana", font * 0.9));
        stats.setFill(Color.STEELBLUE);

        Rectangle rect = new Rectangle(width, height * 0.75);
        rect.setFill(Color.ORANGE);

        line1 = new Rectangle(width / 50, marginy1 + fieldWidth / 2 - 1, marginx1 - width / 25, 2);
        line1.setFill(Color.WHITESMOKE);
        line2 = new Rectangle(width / 50, marginy1 + fieldWidth + marginy2 + fieldWidth / 2 - 1, marginx1 - width / 25, 2);
        line2.setFill(Color.WHITESMOKE);
        line3 = new Rectangle(width / 50, marginy1 + 2 * fieldWidth + 2 * marginy2 + fieldWidth / 2 - 1, marginx1 - width / 25, 2);
        line3.setFill(Color.WHITESMOKE);

        line4 = new Rectangle(0.7 * width - marginx1 + width / 50, marginy1 + fieldWidth / 2 - 1, marginx1 - width / 25, 2);
        line4.setFill(Color.WHITESMOKE);
        line5 = new Rectangle(0.7 * width - marginx1 + width / 50, marginy1 + fieldWidth + marginy2 + fieldWidth / 2 - 1, marginx1 - width / 25, 2);
        line5.setFill(Color.WHITESMOKE);
        line6 = new Rectangle(0.7 * width - marginx1 + width / 50, marginy1 + 2 * fieldWidth + 2 * marginy2 + fieldWidth / 2 - 1, marginx1 - width / 25, 2);
        line6.setFill(Color.WHITESMOKE);

        line7 = new Rectangle(marginx1 + 2 * fieldWidth + marginx2 + width / 50, marginy1 + fieldWidth / 2 - 1, marginx2 - width / 25, 2);
        line7.setFill(Color.WHITESMOKE);
        line8 = new Rectangle(marginx1 + 2 * fieldWidth + marginx2 + width / 50, marginy1 + fieldWidth + marginy2 + fieldWidth / 2 - 1, marginx2 - width / 25, 2);
        line8.setFill(Color.WHITESMOKE);
        line9 = new Rectangle(marginx1 + 2 * fieldWidth + marginx2 + width / 50, marginy1 + 2 * fieldWidth + 2 * marginy2 + fieldWidth / 2 - 1, marginx2 - width / 25, 2);
        line9.setFill(Color.WHITESMOKE);

        line10 = new Rectangle(marginx1 + fieldWidth + width / 50, marginy1 + fieldWidth / 2 - 1, marginx2 - width / 25, 2);
        line10.setFill(Color.WHITESMOKE);
        line11 = new Rectangle(marginx1 + fieldWidth + width / 50, marginy1 + fieldWidth + marginy2 + fieldWidth / 2 - 1, marginx2 - width / 25, 2);
        line11.setFill(Color.WHITESMOKE);
        line12 = new Rectangle(marginx1 + fieldWidth + width / 50, marginy1 + 2 * fieldWidth + 2 * marginy2 + fieldWidth / 2 - 1, marginx2 - width / 25, 2);
        line12.setFill(Color.WHITESMOKE);

        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        line5.setVisible(false);
        line6.setVisible(false);
        line7.setVisible(false);
        line8.setVisible(false);
        line9.setVisible(false);
        line10.setVisible(false);
        line11.setVisible(false);
        line12.setVisible(false);

        field1 = new Field(sc, 1, marginx1, marginy1, fieldWidth, fieldWidth);
        field2 = new Field(sc, 1, marginx1 + fieldWidth + marginx2, marginy1, fieldWidth, fieldWidth);
        field3 = new Field(sc, 1, marginx1 + 2 * fieldWidth + 2 * marginx2, marginy1, fieldWidth, fieldWidth);
        field4 = new Field(sc, 2, marginx1, marginy1 + marginy2 + fieldWidth, fieldWidth, fieldWidth);
        field5 = new Field(sc, 2, marginx1 + fieldWidth + marginx2, marginy1 + marginy2 + fieldWidth, fieldWidth, fieldWidth);
        field6 = new Field(sc, 2, marginx1 + 2 * fieldWidth + 2 * marginx2, marginy1 + marginy2 + fieldWidth, fieldWidth, fieldWidth);
        field7 = new Field(sc, 3, marginx1, marginy1 + 2 * fieldWidth + 2 * marginy2, fieldWidth, fieldWidth);
        field8 = new Field(sc, 3, marginx1 + fieldWidth + marginx2, marginy1 + 2 * fieldWidth + 2 * marginy2, fieldWidth, fieldWidth);
        field9 = new Field(sc, 3, marginx1 + 2 * fieldWidth + 2 * marginx2, marginy1 + 2 * fieldWidth + 2 * marginy2, fieldWidth, fieldWidth);

        Image bg1 = new Image("images/t1.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);
        Image bg2 = new Image("images/t2.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);
        Image bg3 = new Image("images/t3.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);
        Image bg4 = new Image("images/t4.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);

        doubleImage1 = new ImageView(bg1);
        doubleImage2 = new ImageView(bg2);
        doubleImage3 = new ImageView(bg3);
        doubleImage4 = new ImageView(bg4);

        Text double_up_2x_text = new Text("2X");
        Text double_up_3x_text = new Text("3X");
        Text double_up_7x_text = new Text("7X");
        Text double_up_30x_text = new Text("30X");
        Text double_up_2x_infotext = new Text();
        Text double_up_3x_infotext = new Text();
        Text double_up_7x_infotext = new Text();
        Text double_up_30x_infotext = new Text();

        double_up_2x_infotext.textProperty().bind((sc.roundWinx2.asString().concat(".00")));
        double_up_3x_infotext.textProperty().bind((sc.roundWinx3.asString().concat(".00")));
        double_up_7x_infotext.textProperty().bind((sc.roundWinx7.asString().concat(".00")));
        double_up_30x_infotext.textProperty().bind((sc.roundWinx30.asString().concat(".00")));

        double_up_2x_text.setFont(Font.font("Verdana", font - 1));
        double_up_2x_text.setFill(Color.BROWN);
        double_up_3x_text.setFont(Font.font("Verdana", font - 1));
        double_up_3x_text.setFill(Color.BROWN);
        double_up_7x_text.setFont(Font.font("Verdana", font - 1));
        double_up_7x_text.setFill(Color.BROWN);
        double_up_30x_text.setFont(Font.font("Verdana", font - 1));
        double_up_30x_text.setFill(Color.BROWN);

        double_up_2x_infotext.setFont(Font.font("Verdana", font));
        double_up_2x_infotext.setFill(Color.BROWN);
        double_up_3x_infotext.setFont(Font.font("Verdana", font));
        double_up_3x_infotext.setFill(Color.BROWN);
        double_up_7x_infotext.setFont(Font.font("Verdana", font));
        double_up_7x_infotext.setFill(Color.BROWN);
        double_up_30x_infotext.setFont(Font.font("Verdana", font));
        double_up_30x_infotext.setFill(Color.BROWN);

        Text test = new Text("1000000.00");
        test.setFont(Font.font("Verdana", font + 6));
        int wi = (int) (test.getLayoutBounds().getWidth());
        int margin = (int) (0.7 * width - 4 * wi);
        margin /= 7;

        int h = (int) (0.75 * fieldWidth + double_up_2x_text.getLayoutBounds().getHeight() + double_up_2x_infotext.getLayoutBounds().getHeight() + 8);

        
        field1.setVisible(false);
        field2.setVisible(false);
        field3.setVisible(false);
        field4.setVisible(false);
        field5.setVisible(false);
        field6.setVisible(false);
        field7.setVisible(false);
        field8.setVisible(false);
        field9.setVisible(false);

        rect1.setVisible(false);
        rect2.setVisible(false);
        rect3.setVisible(false);
        rect4.setVisible(false);
        rect5.setVisible(false);
        rect6.setVisible(false);
        rect7.setVisible(false);
        rect8.setVisible(false);
        rect9.setVisible(false);

        rect1.setFill(Color.YELLOW);
        rect2.setFill(Color.YELLOW);
        rect3.setFill(Color.YELLOW);
        rect4.setFill(Color.YELLOW);
        rect5.setFill(Color.YELLOW);
        rect6.setFill(Color.YELLOW);
        rect7.setFill(Color.YELLOW);
        rect8.setFill(Color.YELLOW);
        rect9.setFill(Color.YELLOW);

        ToggleButton table = sc.getTableButton();
        ToggleButton claim = sc.getClaimButton();
        ToggleButton ne = sc.getNewButton();
        Button st = sc.getStatsButton();
        doubleButton = sc.getDoubleButton();
        table.setFont(Font.font("Verdana", font - 1));
        table.setTextFill(Color.DARKSLATEGREY);
        table.getStyleClass().add("gr");
        table.setMaxWidth(width / 7);

        claim.getStyleClass().add("ye");
        claim.setMaxWidth(width / 7);
        claim.setFont(Font.font("Verdana", font - 1));
        claim.setTextFill(Color.DARKSLATEGREY);

        ne.getStyleClass().add("tu");
        ne.setMaxWidth(width / 7);
        ne.setFont(Font.font("Verdana", font - 1));
        ne.setTextFill(Color.DARKSLATEGREY);

        st.setStyle("-fx-background-color: #696969");
        st.setMaxWidth(width / 7);
        st.setFont(Font.font("Verdana", font - 1));
        st.setTextFill(Color.DARKSLATEGREY);

        doubleButton.getStyleClass().add("or");
        doubleButton.setDisable(true);
        doubleButton.setMaxWidth(width / 7);
        doubleButton.setFont(Font.font("Verdana", font - 1));
        doubleButton.setTextFill(Color.DARKSLATEGREY);

        VBox statsBox = new VBox(0.01 * height);
        statsBox.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(stats, more);

        statsBox.getChildren().addAll(win, st, hbox1);

        HBox buttonsHBox1 = new HBox(width / 20);
        buttonsHBox1.getChildren().addAll(table, doubleButton, statsBox, claim, ne);
        buttonsHBox1.setAlignment(Pos.CENTER);
        VBox buttonsHBox2 = new VBox();
        buttonsHBox2.setLayoutY(0.75 * height);
        Rectangle downBG = new Rectangle(0, 0.75 * height, width, 0.25 * height);
        downBG.setFill(Color.BROWN);
        buttonsHBox2.getChildren().add(new StackPane(downBG, buttonsHBox1));

        root.getChildren().addAll(rect, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, field1, field2, field3, field4, field5, field6, field7, field8, field9, buttonsHBox2);
        root.getChildren().add(info);

        Scene scene = new Scene(root, width, height);
        String css = this.getClass().getResource("/style/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);
        primaryStage.show();

        Rectangle tausta = new Rectangle();

        tausta.setFill(Color.BLACK);
        StackPane sp = new StackPane(tausta, v6);
        sp.setAlignment(Pos.CENTER);
        info.setStyle("-fx-background-color: #0067b3");
        v1.setStyle("-fx-background-color: grey");
        v2.setStyle("-fx-background-color: grey");
        v4.setStyle("-fx-background-color: grey");
        v5.setStyle("-fx-background-color: grey");
        v3.setStyle("-fx-background-color: grey");

        info.getChildren().add(new StackPane(infobg, v6));

    }


    public void setWhiteRectVisible(int i, boolean b) {
        if (i == 1) {
            whiteRect1.setVisible(b);
        } else if (i == 2) {
            whiteRect2.setVisible(b);
        } else if (i == 3) {
            whiteRect3.setVisible(b);
        } else if (i == 4) {
            whiteRect4.setVisible(b);
        }
    }


    public void fillWin(Color color) {
        win.setFill(color);
    }

    public void setWinVisible(boolean b) {
        win.setVisible(b);
    }

    public void handleNewButtonWinTaking() {
        whiteRect1.setVisible(false);
        whiteRect2.setVisible(false);
        whiteRect3.setVisible(false);
        whiteRect4.setVisible(false);
        doubleBgRect1.setVisible(false);
        doubleBgRect2.setVisible(false);
        doubleBgRect3.setVisible(false);
        doubleBgRect4.setVisible(false);
        doubleField.setVisible(false);
    }

    public void handleNewButtonNewCard() {

        win.setFill(Color.LIGHTGREEN);
        played_total.set(played_total.get() + 1);
        played_session.set(played_session.get() + 1);
        money_total.set(money_total.get() - 1);
        money_session.set(money_session.get() - 1);
        rect1.setVisible(false);
        rect2.setVisible(false);
        rect3.setVisible(false);
        rect4.setVisible(false);
        rect5.setVisible(false);
        rect6.setVisible(false);
        rect7.setVisible(false);
        rect8.setVisible(false);
        rect9.setVisible(false);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        field5.setVisible(true);
        field6.setVisible(true);
        field7.setVisible(true);
        field8.setVisible(true);
        field9.setVisible(true);
        line1.setVisible(true);
        line2.setVisible(true);
        line3.setVisible(true);
        line4.setVisible(true);
        line5.setVisible(true);
        line6.setVisible(true);
        line7.setVisible(true);
        line8.setVisible(true);
        line9.setVisible(true);
        line10.setVisible(true);
        line11.setVisible(true);
        line12.setVisible(true);
    }

    public Field getField(int number) {
        if (number == 1) {
            return field1;
        } else if (number == 2) {
            return field2;
        } else if (number == 3) {
            return field3;
        } else if (number == 4) {
            return field4;
        } else if (number == 5) {
            return field5;
        } else if (number == 6) {
            return field6;
        } else if (number == 7) {
            return field7;
        } else if (number == 8) {
            return field8;
        } else if (number == 9) {
            return field9;
        }
        return field1;
    }

    public void setWinBorders(List<Integer> l) {
        if (l.contains(1)) {
            rect1.setVisible(true);
        }
        if (l.contains(2)) {
            rect2.setVisible(true);
        }
        if (l.contains(3)) {
            rect3.setVisible(true);
        }
        if (l.contains(4)) {
            rect4.setVisible(true);
        }
        if (l.contains(5)) {
            rect5.setVisible(true);
        }
        if (l.contains(6)) {
            rect6.setVisible(true);
        }
        if (l.contains(7)) {
            rect7.setVisible(true);
        }
        if (l.contains(8)) {
            rect8.setVisible(true);
        }
        if (l.contains(9)) {
            rect9.setVisible(true);
        }
    }
}
