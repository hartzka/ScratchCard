package kh.scratchcard.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.WindowEvent;
import kh.scratchcard.dao.Data;
import kh.scratchcard.domain.ScratchCard;
import kh.scratchcard.domain.WinCategory;

/**
 * Sovelluksen käyttöliittymä
 */
public class Ui extends Application {

    private Pane root = new Pane();
    Rectangle2D visualBounds;
    private double width;
    private double height;

    private Text unbelievable2 = new Text("");
    private Text stats = new Text("");

    private WinTable winTable;
    private int fieldWidth;

    private int marginx1;

    private int marginx2;
    private int marginy1;
    private int marginy2;
    Rectangle rect1;
    Rectangle rect2;
    Rectangle rect3;
    Rectangle rect4;
    Rectangle rect5;
    Rectangle rect6;
    Rectangle rect7;
    Rectangle rect8;
    Rectangle rect9;

    Rectangle whiteRect1;
    Rectangle whiteRect2;
    Rectangle whiteRect3;
    Rectangle whiteRect4;

    Rectangle doubleBgRect1;
    Rectangle doubleBgRect2;
    Rectangle doubleBgRect3;
    Rectangle doubleBgRect4;

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

    Rectangle infobg;
    Text upperMargin1 = new Text("");
    Text upperMargin2 = new Text("");
    Text upperMargin3 = new Text("");
    Text upperMargin4 = new Text("");
    Text lowerMargin1 = new Text("");
    Text lowerMargin2 = new Text("");
    Text lowerMargin3 = new Text("");
    Text lowerMargin4 = new Text("");
    Text moneyText = new Text("   Money:");
    Text moneyTotalText = new Text("	   Total:");
    Text moneySessionText = new Text("	   This session:");
    Text playedText = new Text("   Played:");
    Text playedTotalText = new Text("	   Total:");
    Text playedSessionText = new Text("	   This session:");
    Text winsText = new Text("   Wins:");
    Text win50000Text = new Text("	   50000.00:");
    Text win5000Text = new Text("	   5000.00:");
    Text win500Text = new Text("	   500.00:");
    Text win100Text = new Text("	   100.00:");
    Text win20Text = new Text("	   20.00:");
    Text win15Text = new Text("	   15.00:");
    Text win10Text = new Text("	   10.00:");
    Text win8Text = new Text("	   8.00:");
    Text win6Text = new Text("	   6.00:");
    Text win5Text = new Text("	   5.00:");
    Text win4Text = new Text("	   4.00:");
    Text win3Text = new Text("	   3.00:");
    Text win2Text = new Text("	   2.00:");
    Text totalWinsText = new Text("	   Total wins:");
    Text winningCardsText = new Text("	   Winning cards:");
    Text doubleUpText = new Text("   Double Up:");
    Text doubleUpWinsText = new Text("	   Wins:");
    Text doubleUpLossesText = new Text("	   Losses:");
    Text doubleUpMaxWinText = new Text("	   Max win:");
    Text doubleUpBestResultText = new Text("	   Best result:");

    Text moneyInfoText = new Text("");
    Text moneyTotalInfoText = new Text("");
    Text moneySessionInfoText = new Text("");
    Text playedInfoText = new Text("");
    Text playedTotalInfoText = new Text("");
    Text playedSessionInfoText = new Text("");
    Text winsInfoText = new Text("");
    Text win50000InfoText = new Text("");
    Text win5000InfoText = new Text("");
    Text win500InfoText = new Text("");
    Text win100InfoText = new Text("");
    Text win20InfoText = new Text("");
    Text win15InfoText = new Text("");
    Text win10InfoText = new Text("");
    Text win8InfoText = new Text("");
    Text win6InfoText = new Text("");
    Text win5InfoText = new Text("");
    Text win4InfoText = new Text("");
    Text win3InfoText = new Text("");
    Text win2InfoText = new Text("");
    Text totalWinsInfoText = new Text("");
    Text winningCardsInfoText = new Text("");
    Text doubleUpInfoText = new Text("");
    Text doubleUpWinsInfoText = new Text("");
    Text doubleUpLossesInfoText = new Text("");
    Text doubleUpMaxWinInfoText = new Text("");
    Text doubleUpBestResultInfoText = new Text("");

    Text more = new Text("More stats");

    SimpleIntegerProperty moneyTotal;
    SimpleIntegerProperty moneySession;
    SimpleIntegerProperty playedTotal = new SimpleIntegerProperty(0);
    SimpleIntegerProperty playedSession = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win50000 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win5000 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win500 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win100 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win20 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win15 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win10 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win8 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win6 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win5 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win4 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win3 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty win2 = new SimpleIntegerProperty(0);
    SimpleIntegerProperty totalWins = new SimpleIntegerProperty(0);
    SimpleIntegerProperty winningCards = new SimpleIntegerProperty(0);
    SimpleIntegerProperty doubleUpWins = new SimpleIntegerProperty(0);
    SimpleIntegerProperty doubleUpLosses = new SimpleIntegerProperty(0);
    SimpleIntegerProperty doubleUpMaxWin = new SimpleIntegerProperty(0);
    Text doubleUpBestResult = new Text("");
    private ScratchCard sc;

    private ToggleButton tableButton;
    private ToggleButton claimButton;
    private ToggleButton newButton;
    private Button statsButton;
    private ToggleButton doubleButton;
    VBox doubleChoice1 = new VBox(0);
    VBox doubleChoice2 = new VBox(0);
    VBox doubleChoice3 = new VBox(0);
    VBox doubleChoice4 = new VBox(0);
    Text win;
    SimpleStringProperty winText;
    private Text unbelievable;

    public Ui(ScratchCard sc) {
        this.sc = sc;
        field1 = new Field(sc, 0, 0, 0, 0);
        field2 = new Field(sc, 0, 0, 0, 0);
        field3 = new Field(sc, 0, 0, 0, 0);
        field4 = new Field(sc, 0, 0, 0, 0);
        field5 = new Field(sc, 0, 0, 0, 0);
        field6 = new Field(sc, 0, 0, 0, 0);
        field7 = new Field(sc, 0, 0, 0, 0);
        field8 = new Field(sc, 0, 0, 0, 0);
        field9 = new Field(sc, 0, 0, 0, 0);
        doubleField = new Field(sc, 0, 0, 0, 0);
        doubleField.setColorMode(4);
        win = new Text("");
        winText = new SimpleStringProperty("Win: ");
        unbelievable = new Text("Unbelievable!");
        whiteRect1 = new Rectangle(0, 0);
        whiteRect2 = new Rectangle(0, 0);
        whiteRect3 = new Rectangle(0, 0);
        whiteRect4 = new Rectangle(0, 0);
        doubleBgRect1 = new Rectangle(0, 0);
        doubleBgRect2 = new Rectangle(0, 0);
        doubleBgRect3 = new Rectangle(0, 0);
        doubleBgRect4 = new Rectangle(0, 0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        visualBounds = Screen.getPrimary().getVisualBounds();
        width = visualBounds.getWidth();
        height = visualBounds.getHeight();
        fieldWidth = (int) (0.19 * height);

        marginx1 = (int) (0.6 * (0.35 * width - (1.5 * fieldWidth)));
        marginx2 = (int) ((0.4) * (0.35 * width - (1.5 * fieldWidth)));
        marginy1 = (int) (0.05 * height);
        marginy2 = (int) (0.04 * height);

        rect1 = new Rectangle(marginx1 - 4, marginy1 - 4, fieldWidth + 8, fieldWidth + 8);
        rect2 = new Rectangle(marginx1 + fieldWidth + marginx2 - 4, marginy1 - 4, fieldWidth + 8, fieldWidth + 8);
        rect3 = new Rectangle(marginx1 + 2 * fieldWidth + 2 * marginx2 - 4, marginy1 - 4, fieldWidth + 8, fieldWidth + 8);
        rect4 = new Rectangle(marginx1 - 4, marginy1 + marginy2 + fieldWidth - 4, fieldWidth + 8, fieldWidth + 8);
        rect5 = new Rectangle(marginx1 + fieldWidth + marginx2 - 4, marginy1 + marginy2 + fieldWidth - 4, fieldWidth + 8, fieldWidth + 8);
        rect6 = new Rectangle(marginx1 + 2 * fieldWidth + 2 * marginx2 - 4, marginy1 + marginy2 + fieldWidth - 4, fieldWidth + 8, fieldWidth + 8);
        rect7 = new Rectangle(marginx1 - 4, marginy1 + 2 * fieldWidth + 2 * marginy2 - 4, fieldWidth + 8, fieldWidth + 8);
        rect8 = new Rectangle(marginx1 + fieldWidth + marginx2 - 4, marginy1 + 2 * fieldWidth + 2 * marginy2 - 4, fieldWidth + 8, fieldWidth + 8);
        rect9 = new Rectangle(marginx1 + 2 * fieldWidth + 2 * marginx2 - 4, marginy1 + 2 * fieldWidth + 2 * marginy2 - 4, fieldWidth + 8, fieldWidth + 8);

        unbelievable.setFill(Color.AZURE);
        unbelievable.setFont(Font.font("Verdana", 16));
        unbelievable.setVisible(false);

        moneyTotal = sc.getMoneyTotal();
        moneySession = sc.getMoneySession();

        moneyTotalInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(moneyTotal.asString().concat(".00")));
        moneySessionInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(moneySession.asString().concat(".00")));
        playedTotalInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(playedTotal.asString()));
        playedSessionInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(playedSession.asString()));
        win50000InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win50000.asString()));
        win5000InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win5000.asString()));
        win500InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win500.asString()));
        win100InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win100.asString()));
        win20InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win20.asString()));
        win15InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win15.asString()));
        win10InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win10.asString()));
        win8InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win8.asString()));
        win6InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win6.asString()));
        win5InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win5.asString()));
        win4InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win4.asString()));
        win3InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win3.asString()));
        win2InfoText.textProperty().bind(new SimpleStringProperty("   ").concat(win2.asString()));
        totalWinsInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(totalWins.asString().concat(".00")));
        winningCardsInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(winningCards.asString()));
        doubleUpWinsInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(doubleUpWins.asString().concat(".00")));
        doubleUpLossesInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(doubleUpLosses.asString().concat(".00")));
        doubleUpMaxWinInfoText.textProperty().bind(new SimpleStringProperty("   ").concat(doubleUpMaxWin.asString().concat(".00")));

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

        moneyText.setFill(Color.ORANGE);
        moneyText.setFont(Font.font("Verdana", font));
        moneyTotalText.setFill(Color.MINTCREAM);
        moneyTotalText.setFont(Font.font("Verdana", font));
        moneySessionText.setFill(Color.MINTCREAM);
        moneySessionText.setFont(Font.font("Verdana", font));
        playedText.setFill(Color.ORANGE);
        playedText.setFont(Font.font("Verdana", font));
        playedTotalText.setFill(Color.MINTCREAM);
        playedTotalText.setFont(Font.font("Verdana", font));
        playedSessionText.setFill(Color.MINTCREAM);
        playedSessionText.setFont(Font.font("Verdana", font));
        winsText.setFill(Color.ORANGE);
        winsText.setFont(Font.font("Verdana", font));
        win50000Text.setFill(Color.MINTCREAM);
        win50000Text.setFont(Font.font("Verdana", font));
        win5000Text.setFill(Color.MINTCREAM);
        win5000Text.setFont(Font.font("Verdana", font));
        win500Text.setFill(Color.MINTCREAM);
        win500Text.setFont(Font.font("Verdana", font));
        win100Text.setFill(Color.MINTCREAM);
        win100Text.setFont(Font.font("Verdana", font));
        win20Text.setFill(Color.MINTCREAM);
        win20Text.setFont(Font.font("Verdana", font));
        win15Text.setFill(Color.MINTCREAM);
        win15Text.setFont(Font.font("Verdana", font));
        win10Text.setFill(Color.MINTCREAM);
        win10Text.setFont(Font.font("Verdana", font));
        win8Text.setFill(Color.MINTCREAM);
        win8Text.setFont(Font.font("Verdana", font));
        win6Text.setFill(Color.MINTCREAM);
        win6Text.setFont(Font.font("Verdana", font));
        win5Text.setFill(Color.MINTCREAM);
        win5Text.setFont(Font.font("Verdana", font));
        win4Text.setFill(Color.MINTCREAM);
        win4Text.setFont(Font.font("Verdana", font));
        win3Text.setFill(Color.MINTCREAM);
        win3Text.setFont(Font.font("Verdana", font));
        win2Text.setFill(Color.MINTCREAM);
        win2Text.setFont(Font.font("Verdana", font));
        totalWinsText.setFill(Color.MINTCREAM);
        totalWinsText.setFont(Font.font("Verdana", font));
        winningCardsText.setFill(Color.MINTCREAM);
        winningCardsText.setFont(Font.font("Verdana", font));
        doubleUpText.setFill(Color.ORANGE);
        doubleUpText.setFont(Font.font("Verdana", font));
        doubleUpWinsText.setFill(Color.MINTCREAM);
        doubleUpWinsText.setFont(Font.font("Verdana", font));
        doubleUpLossesText.setFill(Color.MINTCREAM);
        doubleUpLossesText.setFont(Font.font("Verdana", font));
        doubleUpMaxWinText.setFill(Color.MINTCREAM);
        doubleUpMaxWinText.setFont(Font.font("Verdana", font));
        doubleUpBestResultText.setFill(Color.MINTCREAM);
        doubleUpBestResultText.setFont(Font.font("Verdana", font));

        moneyInfoText.setFont(Font.font("Verdana", font));
        moneyTotalInfoText.setFill(Color.MINTCREAM);
        moneyTotalInfoText.setFont(Font.font("Verdana", font));
        moneySessionInfoText.setFill(Color.MINTCREAM);
        moneySessionInfoText.setFont(Font.font("Verdana", font));
        playedInfoText.setFont(Font.font("Verdana", font));
        playedTotalInfoText.setFill(Color.MINTCREAM);
        playedTotalInfoText.setFont(Font.font("Verdana", font));
        playedSessionInfoText.setFill(Color.MINTCREAM);
        playedSessionInfoText.setFont(Font.font("Verdana", font));
        winsInfoText.setFont(Font.font("Verdana", font));
        win50000InfoText.setFill(Color.MINTCREAM);
        win50000InfoText.setFont(Font.font("Verdana", font));
        win5000InfoText.setFill(Color.MINTCREAM);
        win5000InfoText.setFont(Font.font("Verdana", font));
        win500InfoText.setFill(Color.MINTCREAM);
        win500InfoText.setFont(Font.font("Verdana", font));
        win100InfoText.setFill(Color.MINTCREAM);
        win100InfoText.setFont(Font.font("Verdana", font));
        win20InfoText.setFill(Color.MINTCREAM);
        win20InfoText.setFont(Font.font("Verdana", font));
        win15InfoText.setFill(Color.MINTCREAM);
        win15InfoText.setFont(Font.font("Verdana", font));
        win10InfoText.setFill(Color.MINTCREAM);
        win10InfoText.setFont(Font.font("Verdana", font));
        win8InfoText.setFill(Color.MINTCREAM);
        win8InfoText.setFont(Font.font("Verdana", font));
        win6InfoText.setFill(Color.MINTCREAM);
        win6InfoText.setFont(Font.font("Verdana", font));
        win5InfoText.setFill(Color.MINTCREAM);
        win5InfoText.setFont(Font.font("Verdana", font));
        win4InfoText.setFill(Color.MINTCREAM);
        win4InfoText.setFont(Font.font("Verdana", font));
        win3InfoText.setFill(Color.MINTCREAM);
        win3InfoText.setFont(Font.font("Verdana", font));
        win2InfoText.setFill(Color.MINTCREAM);
        win2InfoText.setFont(Font.font("Verdana", font));
        totalWinsInfoText.setFill(Color.MINTCREAM);
        totalWinsInfoText.setFont(Font.font("Verdana", font));
        winningCardsInfoText.setFill(Color.MINTCREAM);
        winningCardsInfoText.setFont(Font.font("Verdana", font));
        doubleUpInfoText.setFont(Font.font("Verdana", font));
        doubleUpWinsInfoText.setFill(Color.MINTCREAM);
        doubleUpWinsInfoText.setFont(Font.font("Verdana", font));
        doubleUpLossesInfoText.setFill(Color.MINTCREAM);
        doubleUpLossesInfoText.setFont(Font.font("Verdana", font));
        doubleUpMaxWinInfoText.setFill(Color.MINTCREAM);
        doubleUpMaxWinInfoText.setFont(Font.font("Verdana", font));
        doubleUpBestResult.setFill(Color.MINTCREAM);
        doubleUpBestResult.setFont(Font.font("Verdana", font));

        unbelievable2.setFont(Font.font("Verdana", font - 2));
        unbelievable2.setVisible(false);

        font++;

        infobg = new Rectangle(0, 0, width, height);
        infobg.setFill(Color.rgb(0, 103, 179));

        ToggleButton closeInfo = new ToggleButton("CLOSE");
        closeInfo.setStyle("-fx-background-color: red");
        tableButton = new ToggleButton(" WIN-\nTABLE");
        claimButton = new ToggleButton("CLAIM");
        newButton = new ToggleButton("  NEW/\nCOLLECT");
        doubleButton = new ToggleButton("DOUBLE\n    UP");
        statsButton = new Button("STATS");

        HBox h1 = new HBox(0);
        VBox v1 = new VBox();
        v1.getChildren().addAll(upperMargin1, moneyText, moneyTotalText, moneySessionText, playedText, playedTotalText, playedSessionText, doubleUpText, doubleUpWinsText, doubleUpLossesText, doubleUpMaxWinText, doubleUpBestResultText, lowerMargin1);
        VBox v2 = new VBox();
        v2.getChildren().addAll(upperMargin2, moneyInfoText, moneyTotalInfoText, moneySessionInfoText, playedInfoText, playedTotalInfoText, playedSessionInfoText, doubleUpInfoText, doubleUpWinsInfoText, doubleUpLossesInfoText, doubleUpMaxWinInfoText, doubleUpBestResult, lowerMargin2);
        HBox h2 = new HBox(5);
        h2.getChildren().addAll(v1, v2);
        VBox v3 = new VBox(7);
        v3.getChildren().addAll(h2, closeInfo);
        v3.setAlignment(Pos.CENTER);
        VBox v4 = new VBox();
        v4.getChildren().addAll(upperMargin3, winsText, win2Text, win3Text, win4Text, win5Text, win6Text, win8Text, win10Text, win15Text, win20Text, win100Text, win500Text, win5000Text, win50000Text, totalWinsText, winningCardsText, lowerMargin3);
        VBox v5 = new VBox();
        v5.getChildren().addAll(upperMargin4, winsInfoText, win2InfoText, win3InfoText, win4InfoText, win5InfoText, win6InfoText, win8InfoText, win10InfoText, win15InfoText, win20InfoText, win100InfoText, win500InfoText, win5000InfoText, win50000InfoText, totalWinsInfoText, winningCardsInfoText, lowerMargin4);
        closeInfo.setPadding(new Insets(0, width / 30, 0, width / 30));
        h1.getChildren().addAll(v4, v5, v3);
        h1.setAlignment(Pos.CENTER);
        VBox v6 = new VBox();
        v6.getChildren().addAll(h1);
        v6.setAlignment(Pos.CENTER);

        final HBox info = new HBox();
        info.setVisible(false);

        more.setFill(Color.DARKGOLDENROD);
        more.setFont(Font.font("Verdana", font * 0.9));
        more.setVisible(false);

        win.setVisible(false);
        win.setFont(Font.font("Verdana", font + 2));
        win.setFill(Color.LIGHTGREEN);

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

        field1 = new Field(sc, marginx1, marginy1, fieldWidth, fieldWidth);
        field2 = new Field(sc, marginx1 + fieldWidth + marginx2, marginy1, fieldWidth, fieldWidth);
        field3 = new Field(sc, marginx1 + 2 * fieldWidth + 2 * marginx2, marginy1, fieldWidth, fieldWidth);
        field4 = new Field(sc, marginx1, marginy1 + marginy2 + fieldWidth, fieldWidth, fieldWidth);
        field5 = new Field(sc, marginx1 + fieldWidth + marginx2, marginy1 + marginy2 + fieldWidth, fieldWidth, fieldWidth);
        field6 = new Field(sc, marginx1 + 2 * fieldWidth + 2 * marginx2, marginy1 + marginy2 + fieldWidth, fieldWidth, fieldWidth);
        field7 = new Field(sc, marginx1, marginy1 + 2 * fieldWidth + 2 * marginy2, fieldWidth, fieldWidth);
        field8 = new Field(sc, marginx1 + fieldWidth + marginx2, marginy1 + 2 * fieldWidth + 2 * marginy2, fieldWidth, fieldWidth);
        field9 = new Field(sc, marginx1 + 2 * fieldWidth + 2 * marginx2, marginy1 + 2 * fieldWidth + 2 * marginy2, fieldWidth, fieldWidth);

        field1.initializeBackGround(1);
        field2.initializeBackGround(1);
        field3.initializeBackGround(1);
        field4.initializeBackGround(2);
        field5.initializeBackGround(2);
        field6.initializeBackGround(2);
        field7.initializeBackGround(3);
        field8.initializeBackGround(3);
        field9.initializeBackGround(3);

        Image bg1 = new Image("images/t1.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);
        Image bg2 = new Image("images/t2.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);
        Image bg3 = new Image("images/t3.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);
        Image bg4 = new Image("images/t4.png", 0.75 * fieldWidth, 0.75 * fieldWidth, false, false, true);

        doubleImage1 = new ImageView(bg1);
        doubleImage2 = new ImageView(bg2);
        doubleImage3 = new ImageView(bg3);
        doubleImage4 = new ImageView(bg4);

        Text doubleUp2xText = new Text("2X");
        Text doubleUp3xText = new Text("3X");
        Text doubleUp7xText = new Text("7X");
        Text doubleUp30xText = new Text("30X");
        Text doubleUp2xInfotext = new Text();
        Text doubleUp3xInfotext = new Text();
        Text doubleUp7xInfotext = new Text();
        Text doubleUp30xInfotext = new Text();

        doubleUp2xInfotext.textProperty().bind((sc.roundWinx2.asString().concat(".00")));
        doubleUp3xInfotext.textProperty().bind((sc.roundWinx3.asString().concat(".00")));
        doubleUp7xInfotext.textProperty().bind((sc.roundWinx7.asString().concat(".00")));
        doubleUp30xInfotext.textProperty().bind((sc.roundWinx30.asString().concat(".00")));

        doubleUp2xText.setFont(Font.font("Verdana", font - 1));
        doubleUp2xText.setFill(Color.BROWN);
        doubleUp3xText.setFont(Font.font("Verdana", font - 1));
        doubleUp3xText.setFill(Color.BROWN);
        doubleUp7xText.setFont(Font.font("Verdana", font - 1));
        doubleUp7xText.setFill(Color.BROWN);
        doubleUp30xText.setFont(Font.font("Verdana", font - 1));
        doubleUp30xText.setFill(Color.BROWN);

        doubleUp2xInfotext.setFont(Font.font("Verdana", font));
        doubleUp2xInfotext.setFill(Color.BROWN);
        doubleUp3xInfotext.setFont(Font.font("Verdana", font));
        doubleUp3xInfotext.setFill(Color.BROWN);
        doubleUp7xInfotext.setFont(Font.font("Verdana", font));
        doubleUp7xInfotext.setFill(Color.BROWN);
        doubleUp30xInfotext.setFont(Font.font("Verdana", font));
        doubleUp30xInfotext.setFill(Color.BROWN);

        Text test = new Text("1000000.00");
        test.setFont(Font.font("Verdana", font + 6));

        int wi = (int) (test.getLayoutBounds().getWidth());
        int margin = (int) (0.7 * width - 4 * wi);
        margin /= 7;
        int h = (int) (0.75 * fieldWidth + doubleUp2xText.getLayoutBounds().getHeight() + doubleUp2xInfotext.getLayoutBounds().getHeight() + 8);

        doubleBgRect1 = new Rectangle(2 * margin, 0.75 * height - h - marginy2 / 2, wi, h - 8);
        doubleBgRect2 = new Rectangle(3 * margin + wi, 0.75 * height - h - marginy2 / 2, wi, h - 8);
        doubleBgRect3 = new Rectangle(4 * margin + 2 * wi, 0.75 * height - h - marginy2 / 2, wi, h - 8);
        doubleBgRect4 = new Rectangle(5 * margin + 3 * wi, 0.75 * height - h - marginy2 / 2, wi, h - 8);

        VBox box1 = new VBox();
        box1.getChildren().addAll(doubleImage1, doubleUp2xText, doubleUp2xInfotext);
        box1.setAlignment(Pos.CENTER);
        doubleChoice1.getChildren().add(new StackPane(doubleBgRect1, box1));
        doubleChoice1.setLayoutX(2 * margin);
        doubleChoice1.setLayoutY(0.75 * height - h - marginy2 / 2);

        VBox box2 = new VBox();
        box2.getChildren().addAll(doubleImage2, doubleUp3xText, doubleUp3xInfotext);
        box2.setAlignment(Pos.CENTER);
        doubleChoice2.getChildren().add(new StackPane(doubleBgRect2, box2));
        doubleChoice2.setLayoutX(3 * margin + wi);
        doubleChoice2.setLayoutY(0.75 * height - h - marginy2 / 2);

        VBox box3 = new VBox();
        box3.getChildren().addAll(doubleImage3, doubleUp7xText, doubleUp7xInfotext);
        box3.setAlignment(Pos.CENTER);
        doubleChoice3.getChildren().add(new StackPane(doubleBgRect3, box3));
        doubleChoice3.setLayoutX(4 * margin + 2 * wi);
        doubleChoice3.setLayoutY(0.75 * height - h - marginy2 / 2);

        VBox box4 = new VBox();
        box4.getChildren().addAll(doubleImage4, doubleUp30xText, doubleUp30xInfotext);
        box4.setAlignment(Pos.CENTER);
        doubleChoice4.getChildren().add(new StackPane(doubleBgRect4, box4));
        doubleChoice4.setLayoutX(5 * margin + 3 * wi);
        doubleChoice4.setLayoutY(0.75 * height - h - marginy2 / 2);

        whiteRect1 = new Rectangle(doubleBgRect1.getX() - 4, 0.75 * height - h - 4 - marginy2 / 2, wi + 8, h);
        whiteRect2 = new Rectangle(doubleBgRect2.getX() - 4, 0.75 * height - h - 4 - marginy2 / 2, wi + 8, h);
        whiteRect3 = new Rectangle(doubleBgRect3.getX() - 4, 0.75 * height - h - 4 - marginy2 / 2, wi + 8, h);
        whiteRect4 = new Rectangle(doubleBgRect4.getX() - 4, 0.75 * height - h - 4 - marginy2 / 2, wi + 8, h);

        doubleField = new Field(sc, marginx1 + fieldWidth + marginx2 / 2, (int) (0.75 * height - h - marginy2 / 2 - fieldWidth - (5 * marginx2 / 4)), fieldWidth + marginx2, fieldWidth + marginx2);
        doubleField.initializeBackGround(4);

        field1.setVisible(false);
        field2.setVisible(false);
        field3.setVisible(false);
        field4.setVisible(false);
        field5.setVisible(false);
        field6.setVisible(false);
        field7.setVisible(false);
        field8.setVisible(false);
        field9.setVisible(false);
        doubleField.setVisible(false);

        rect1.setVisible(false);
        rect2.setVisible(false);
        rect3.setVisible(false);
        rect4.setVisible(false);
        rect5.setVisible(false);
        rect6.setVisible(false);
        rect7.setVisible(false);
        rect8.setVisible(false);
        rect9.setVisible(false);

        doubleBgRect1.setFill(Color.YELLOW);
        doubleBgRect2.setFill(Color.YELLOW);
        doubleBgRect3.setFill(Color.YELLOW);
        doubleBgRect4.setFill(Color.YELLOW);

        whiteRect1.setVisible(false);
        whiteRect2.setVisible(false);
        whiteRect3.setVisible(false);
        whiteRect4.setVisible(false);

        doubleBgRect1.setVisible(false);
        doubleBgRect2.setVisible(false);
        doubleBgRect3.setVisible(false);
        doubleBgRect4.setVisible(false);

        doubleChoice1.setVisible(false);
        doubleChoice2.setVisible(false);
        doubleChoice3.setVisible(false);
        doubleChoice4.setVisible(false);

        rect1.setFill(Color.YELLOW);
        rect2.setFill(Color.YELLOW);
        rect3.setFill(Color.YELLOW);
        rect4.setFill(Color.YELLOW);
        rect5.setFill(Color.YELLOW);
        rect6.setFill(Color.YELLOW);
        rect7.setFill(Color.YELLOW);
        rect8.setFill(Color.YELLOW);
        rect9.setFill(Color.YELLOW);

        whiteRect1.setFill(Color.WHITE);
        whiteRect2.setFill(Color.WHITE);
        whiteRect3.setFill(Color.WHITE);
        whiteRect4.setFill(Color.WHITE);

        winTable = new WinTable((int) (0.7 * width), 0, (int) (0.3 * width), (int) (0.76 * height));

        tableButton.setFont(Font.font("Verdana", font - 1));
        tableButton.setTextFill(Color.DARKSLATEGREY);
        tableButton.getStyleClass().add("green");
        tableButton.setMaxWidth(width / 7);

        claimButton.getStyleClass().add("yellow");
        claimButton.setMaxWidth(width / 7);
        claimButton.setFont(Font.font("Verdana", font - 1));
        claimButton.setTextFill(Color.DARKSLATEGREY);

        newButton.getStyleClass().add("turquoise");
        newButton.setMaxWidth(width / 7);
        newButton.setFont(Font.font("Verdana", font - 1));
        newButton.setTextFill(Color.DARKSLATEGREY);

        statsButton.setStyle("-fx-background-color: #696969");
        statsButton.setMaxWidth(width / 7);
        statsButton.setFont(Font.font("Verdana", font - 1));
        statsButton.setTextFill(Color.DARKSLATEGREY);

        doubleButton.getStyleClass().add("orange");
        doubleButton.setDisable(true);
        doubleButton.setMaxWidth(width / 7);
        doubleButton.setFont(Font.font("Verdana", font - 1));
        doubleButton.setTextFill(Color.DARKSLATEGREY);

        stats.textProperty().bind(new SimpleStringProperty("Money: ").concat(moneyTotal.asString()).concat(".00").concat("   "));
        stats.setVisible(false);
        stats.setFont(Font.font("Verdana", font * 0.9));
        stats.setFill(Color.STEELBLUE);

        VBox statsBox = new VBox(0.01 * height);
        statsBox.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(stats, more);
        statsBox.getChildren().addAll(win, statsButton, hbox1);

        HBox buttonsHBox1 = new HBox(width / 20);
        buttonsHBox1.getChildren().addAll(tableButton, doubleButton, statsBox, claimButton, newButton);
        buttonsHBox1.setAlignment(Pos.CENTER);
        VBox buttonsHBox2 = new VBox();
        buttonsHBox2.setLayoutY(0.75 * height);
        Rectangle downBG = new Rectangle(0, 0.75 * height, width, 0.25 * height);
        downBG.setFill(Color.BROWN);
        buttonsHBox2.getChildren().add(new StackPane(downBG, buttonsHBox1));

        root.getChildren().addAll(rect, whiteRect1, whiteRect2, whiteRect3, whiteRect4, doubleChoice1, doubleChoice2, doubleChoice3, doubleChoice4, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, field1, field2, field3, field4, field5, field6, field7, field8, field9, doubleField, winTable, buttonsHBox2);
        root.getChildren().add(info);

        Rectangle bg = new Rectangle();
        bg.setFill(Color.BLACK);

        StackPane sp = new StackPane(bg, v6);
        sp.setAlignment(Pos.CENTER);

        info.setStyle("-fx-background-color: #0067b3");
        v1.setStyle("-fx-background-color: grey");
        v2.setStyle("-fx-background-color: grey");
        v3.setStyle("-fx-background-color: grey");
        v4.setStyle("-fx-background-color: grey");
        v5.setStyle("-fx-background-color: grey");

        info.getChildren().add(new StackPane(infobg, v6));

        //EventHandlers
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

        doubleChoice1.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (!sc.getDoubleLocked()) {
                    if (sc.getDoubleOption1()) {
                        sc.setDoubleOption1(false);
                        sc.setDoubleVisible(false);
                        setWhiteRectsVisible(new int[]{1}, new boolean[]{false});
                        doubleField.setVisible(false);
                    } else {
                        if (!sc.getDoubleVisible()) {
                            sc.setDoubleVisible(true);
                            doubleField.setVisible(true);
                            sc.randomizeDoubleImage();
                        }
                        setWhiteRectsVisible(new int[]{1, 2, 3, 4}, new boolean[]{true, false, false, false});
                        sc.setDoubleOptionsFalse();
                        sc.setDoubleOption1(true);
                    }
                }
            }
        });

        doubleChoice2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!sc.getDoubleLocked()) {
                    if (sc.getDoubleOption2()) {
                        setWhiteRectsVisible(new int[]{2}, new boolean[]{false});
                        sc.setDoubleOption2(false);
                        sc.setDoubleVisible(false);
                        setDoubleFieldVisible(false);
                    } else {
                        if (!sc.getDoubleVisible()) {
                            sc.setDoubleVisible(true);
                            doubleField.setVisible(true);
                            sc.randomizeDoubleImage();
                        }
                        setWhiteRectsVisible(new int[]{1, 2, 3, 4}, new boolean[]{false, true, false, false});
                        sc.setDoubleOptionsFalse();
                        sc.setDoubleOption2(true);
                    }
                }
            }
        });

        doubleChoice3.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!sc.getDoubleLocked()) {
                    if (sc.getDoubleOption3()) {
                        setWhiteRectsVisible(new int[]{3}, new boolean[]{false});
                        sc.setDoubleOption3(false);
                        sc.setDoubleVisible(false);
                        doubleField.setVisible(false);
                    } else {
                        if (!sc.getDoubleVisible()) {
                            sc.setDoubleVisible(true);
                            doubleField.setVisible(true);
                            sc.randomizeDoubleImage();
                        }
                        setWhiteRectsVisible(new int[]{1, 2, 3, 4}, new boolean[]{false, false, true, false});
                        sc.setDoubleOptionsFalse();
                        sc.setDoubleOption3(true);
                    }
                }
            }
        });

        doubleChoice4.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!sc.getDoubleLocked()) {
                    if (sc.getDoubleOption4()) {
                        setWhiteRectsVisible(new int[]{4}, new boolean[]{false});
                        sc.setDoubleOption4(false);
                        sc.setDoubleVisible(false);
                        doubleField.setVisible(false);
                    } else {
                        if (!sc.getDoubleVisible()) {
                            sc.setDoubleVisible(true);
                            doubleField.setVisible(true);
                            sc.randomizeDoubleImage();
                        }
                        setWhiteRectsVisible(new int[]{1, 2, 3, 4}, new boolean[]{false, false, false, true});
                        sc.setDoubleOptionsFalse();
                        sc.setDoubleOption4(true);
                    }
                }
            }
        });

        tableButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (sc.getTableVisible()) {
                    winTable.setVisible(false);
                    moveX(true);
                } else {
                    winTable.setVisible(true);
                    moveX(false);
                }
                sc.toggleTableVisible();
            }
        });

        claimButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List<WinCategory> wins = sc.getHand().reveal();
                sc.setProgress(false);
                sc.setPlay(true);
                if (!wins.isEmpty()) {
                    if (sc.getRoundWin() < 50000) {
                        doubleButton.setDisable(false);
                    } else {
                        winText.set(unbelievable.getText());
                        fillWin(Color.AZURE);
                    }
                    win.setVisible(true);
                }
            }
        });

        doubleButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                doubleButton.setDisable(true);
                doubleChoice1.setVisible(true);
                doubleChoice2.setVisible(true);
                doubleChoice3.setVisible(true);
                doubleChoice4.setVisible(true);
                handleDoubleButtonAction();
                sc.setDoubleLocked(false);
                sc.updateDoubleWins();
            }
        });

        newButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (sc.getRoundWin() > 0) {
                    sc.handleRoundWin();
                    handleNewButtonWinTaking();
                } else {
                    sc.handleNewCard();
                    handleNewButtonNewCard();
                }
            }
        });

        statsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleStatsAction(sc.getStatsVisible());
                sc.toggleStatsVisible();
            }
        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                try {
                    sc.save();
                } catch (SQLException ex) {
                    Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Goodbye!");
            }

        });

        more.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                info.setVisible(true);
            }

        });

        Scene scene = new Scene(root, width, height);
        String css = this.getClass().getResource("/style/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public WinTable getWintable() {
        return this.winTable;
    }

    /**
     * Käsittelee stats-painikkeen toiminnallisuuden
     *
     * @param statsVisible Ilmaisee, onko stats-valikko näkyvillä
     *
     */
    public void handleStatsAction(boolean statsVisible) {
        if (statsVisible) {
            stats.setVisible(false);
            more.setVisible(false);
        } else {
            stats.setVisible(true);
            more.setVisible(true);
        }
    }

    /**
     * Asettaa tuplausvalikon valkoiset suorakulmiot näkyville tai pois
     * näkyvistä
     *
     * @param rects Suorakulmioiden numerot, välillä 1-4
     *
     * @param b Taulukko suorakulmioden totuusarvoista. false = piilotetaan true
     * = laitetaan näkyville
     *
     */
    public void setWhiteRectsVisible(int[] rects, boolean[] b) {
        for (int i = 0; i < rects.length; i++) {
            int rect = rects[i];
            switch (rect) {
                case 1:
                    whiteRect1.setVisible(b[i]);
                    break;
                case 2:
                    whiteRect2.setVisible(b[i]);
                    break;
                case 3:
                    whiteRect3.setVisible(b[i]);
                    break;
                case 4:
                    whiteRect4.setVisible(b[i]);
                    break;
                default:
                    break;
            }
        }
    }

    public void setDoubleFieldVisible(boolean b) {
        doubleField.setVisible(b);
    }

    public void fillWin(Color color) {
        win.setFill(color);
    }

    public void setWinVisible(boolean b) {
        win.setVisible(b);
    }

    /**
     * Käsittelee tuplauspainikkeen toiminnallisuuden. Piilottaa päänäkymän
     * grafiikkaa ja asettaa tuplausnäkymän grafiikkaa näkyville.
     */
    public void handleDoubleButtonAction() {
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
        whiteRect1.setVisible(false);
        whiteRect2.setVisible(false);
        whiteRect3.setVisible(false);
        whiteRect4.setVisible(false);
        doubleBgRect1.setVisible(true);
        doubleBgRect2.setVisible(true);
        doubleBgRect3.setVisible(true);
        doubleBgRect4.setVisible(true);
    }

    /**
     * Käsittelee new-painikkeen voiton talteenottamisen
     */
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

    /**
     * Käsittelee new-painikkeen uuden arvan ostamisen. Päivittää statistiikkaa
     * ja grafiikkaa.
     */
    public void handleNewButtonNewCard() {
        win.setFill(Color.LIGHTGREEN);
        playedTotal.set(playedTotal.get() + 1);
        playedSession.set(playedSession.get() + 1);
        moneyTotal.set(moneyTotal.get() - 1);
        moneySession.set(moneySession.get() - 1);
        doubleField.setVisible(false);
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

    /**
     * Palauttaa raaputuskentän numeron perusteella
     *
     * @param number Ilmaisee raaputukentän numeron
     *
     * @return Numeroa vastaavan kentän
     *
     */
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

    /**
     * Käsittelee tuplauskentän avaamiseen liittyvän toiminnallisuuden
     */
    public void handleDoubleFieldOpened() {
        whiteRect1.setVisible(false);
        whiteRect2.setVisible(false);
        whiteRect3.setVisible(false);
        whiteRect4.setVisible(false);
        doubleBgRect1.setVisible(false);
        doubleBgRect2.setVisible(false);
        doubleBgRect3.setVisible(false);
        doubleBgRect4.setVisible(false);
    }

    /**
     * Muuttaa voittotaulunäkymää
     *
     * @param expand true, jos voittotaulu piilotetaan ja näkymää laajennetaan;
     * false, jos voittotaulu asetetaan näkyville
     *
     */
    public void moveX(boolean expand) {
        if (expand) {

            doubleChoice2.setLayoutX(doubleChoice2.getLayoutX() + 0.1 * width);
            doubleChoice3.setLayoutX(doubleChoice3.getLayoutX() + 0.2 * width);
            doubleChoice4.setLayoutX(doubleChoice4.getLayoutX() + 0.3 * width);
            rect2.setX(rect2.getX() + 0.15 * width);
            rect5.setX(rect5.getX() + 0.15 * width);
            rect8.setX(rect8.getX() + 0.15 * width);
            rect3.setX(rect3.getX() + 0.3 * width);
            rect6.setX(rect6.getX() + 0.3 * width);
            rect9.setX(rect9.getX() + 0.3 * width);

            field2.setX(field2.getX() + 0.15 * width);
            field5.setX(field5.getX() + 0.15 * width);
            field8.setX(field8.getX() + 0.15 * width);
            field3.setX(field3.getX() + 0.3 * width);
            field6.setX(field6.getX() + 0.3 * width);
            field9.setX(field9.getX() + 0.3 * width);

            line10.setWidth(line10.getWidth() + 0.15 * width);
            line11.setWidth(line11.getWidth() + 0.15 * width);
            line12.setWidth(line12.getWidth() + 0.15 * width);

            line7.setWidth(line7.getWidth() + 0.15 * width);
            line8.setWidth(line8.getWidth() + 0.15 * width);
            line9.setWidth(line9.getWidth() + 0.15 * width);

            line7.setX(line7.getX() + 0.15 * width);
            line8.setX(line8.getX() + 0.15 * width);
            line9.setX(line9.getX() + 0.15 * width);

            line4.setX(line4.getX() + 0.3 * width);
            line5.setX(line5.getX() + 0.3 * width);
            line6.setX(line6.getX() + 0.3 * width);

            whiteRect2.setX(whiteRect2.getX() + 0.1 * width);
            whiteRect3.setX(whiteRect3.getX() + 0.2 * width);
            whiteRect4.setX(whiteRect4.getX() + 0.3 * width);

            doubleBgRect2.setX(doubleBgRect2.getX() + 0.1 * width);
            doubleBgRect3.setX(doubleBgRect3.getX() + 0.2 * width);
            doubleBgRect4.setX(doubleBgRect4.getX() + 0.3 * width);
            doubleField.setX(doubleField.getX() + 0.15 * width);
        } else {
            doubleChoice2.setLayoutX(doubleChoice2.getLayoutX() - 0.1 * width);
            doubleChoice3.setLayoutX(doubleChoice3.getLayoutX() - 0.2 * width);
            doubleChoice4.setLayoutX(doubleChoice4.getLayoutX() - 0.3 * width);
            rect2.setX(rect2.getX() - 0.15 * width);
            rect5.setX(rect5.getX() - 0.15 * width);
            rect8.setX(rect8.getX() - 0.15 * width);
            rect3.setX(rect3.getX() - 0.3 * width);
            rect6.setX(rect6.getX() - 0.3 * width);
            rect9.setX(rect9.getX() - 0.3 * width);

            field2.setX(field2.getX() - 0.15 * width);
            field5.setX(field5.getX() - 0.15 * width);
            field8.setX(field8.getX() - 0.15 * width);
            field3.setX(field3.getX() - 0.3 * width);
            field6.setX(field6.getX() - 0.3 * width);
            field9.setX(field9.getX() - 0.3 * width);

            line10.setWidth(line10.getWidth() - 0.15 * width);
            line11.setWidth(line11.getWidth() - 0.15 * width);
            line12.setWidth(line12.getWidth() - 0.15 * width);

            line7.setWidth(line7.getWidth() - 0.15 * width);
            line8.setWidth(line8.getWidth() - 0.15 * width);
            line9.setWidth(line9.getWidth() - 0.15 * width);

            line7.setX(line7.getX() - 0.15 * width);
            line8.setX(line8.getX() - 0.15 * width);
            line9.setX(line9.getX() - 0.15 * width);

            line4.setX(line4.getX() - 0.3 * width);
            line5.setX(line5.getX() - 0.3 * width);
            line6.setX(line6.getX() - 0.3 * width);

            whiteRect2.setX(whiteRect2.getX() - 0.1 * width);
            whiteRect3.setX(whiteRect3.getX() - 0.2 * width);
            whiteRect4.setX(whiteRect4.getX() - 0.3 * width);

            doubleBgRect2.setX(doubleBgRect2.getX() - 0.1 * width);
            doubleBgRect3.setX(doubleBgRect3.getX() - 0.2 * width);
            doubleBgRect4.setX(doubleBgRect4.getX() - 0.3 * width);
            doubleField.setX(doubleField.getX() - 0.15 * width);
        }
    }

    /**
     * Asettaa voittojen korostukset kentille
     *
     * @param l Lista korostettavien kenttien numeroista. 1 = vasen yläkulma, 9
     * = oikea alakulma
     *
     */
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

    public Field getDoubleField() {
        return this.doubleField;
    }

    public Text getDoubleBestWin() {
        return this.doubleUpBestResult;
    }

    public SimpleIntegerProperty getDoubleUpWins() {
        return this.doubleUpWins;
    }

    @Override
    public void stop() {
        System.out.println("closing");
    }

    public Text getWin() {
        return this.win;
    }

    public ToggleButton getNewButton() {
        return this.newButton;
    }

    public ToggleButton getClaimButton() {
        return this.claimButton;
    }

    public ToggleButton getDoubleButton() {
        return this.doubleButton;
    }

    public SimpleStringProperty getWinText() {
        return this.winText;
    }

    /**
     * Piilottaa tuplausvalintapainikkeet
     */
    public void hideDoubleChoices() {
        doubleChoice1.setVisible(false);
        doubleChoice2.setVisible(false);
        doubleChoice3.setVisible(false);
        doubleChoice4.setVisible(false);
    }

    public void setUnbelievableText() {
        this.winText.set(unbelievable.getText());
    }

    public void setDoubleButtonDisable(boolean b) {
        if (doubleButton != null) {
            doubleButton.setDisable(b);
        }
    }

    /**
     * Lisää kierrosvoittotietoon kierrosvoiton
     *
     * @param value Kierrosvoitto
     *
     */
    public void addToTotalWins(int value) {
        totalWins.set(totalWins.get() + value);
    }

    /**
     * Kasvattaa tiettyä voittokategoriaa yhdellä
     *
     * @param c Kasvatettava kategotria
     *
     */
    public void addToWinCategory(WinCategory c) {
        if (c == WinCategory.X3ORANGE) {
            win2.set(win2.get() + 1);
        } else if (c == WinCategory.X1PINEAPPLE) {
            win3.set(win3.get() + 1);
        } else if (c == WinCategory.X3STRAWBERRY) {
            win4.set(win4.get() + 1);
        } else if (c == WinCategory.X3PLUM) {
            win5.set(win5.get() + 1);
        } else if (c == WinCategory.X2MELON) {
            win6.set(win6.get() + 1);
        } else if (c == WinCategory.X3CHERRY) {
            win8.set(win8.get() + 1);
        } else if (c == WinCategory.X1BANANA) {
            win10.set(win10.get() + 1);
        } else if (c == WinCategory.X2PINEAPPLE) {
            win15.set(win15.get() + 1);
        } else if (c == WinCategory.X3MELON) {
            win20.set(win20.get() + 1);
        } else if (c == WinCategory.X3BANANA) {
            win100.set(win100.get() + 1);
        } else if (c == WinCategory.X3PINEAPPLE) {
            win500.set(win500.get() + 1);
        } else if (c == WinCategory.X3PEAR) {
            win5000.set(win5000.get() + 1);
        } else if (c == WinCategory.X3GRAPES) {
            win50000.set(win50000.get() + 1);
        }
    }

    public SimpleIntegerProperty getDoubleUpLosses() {
        return doubleUpLosses;
    }

    /**
     * Päivittää maksimituplausvoiton
     *
     * @param win Jos false, ei tehdä mitään
     *
     * @param roundWin Jos suurempi kuin maksimituplausvoitto, päivitetään
     * maksimituplausvoitto tähän arvoon.
     *
     */
    public void updateMaxDoubleWin(boolean win, SimpleIntegerProperty roundWin) {
        if (win) {
            if (roundWin.get() > doubleUpMaxWin.get()) {
                doubleUpMaxWin.set(roundWin.get());
            }
        }
    }

    public void incrementWinningCards() {
        winningCards.set(winningCards.get() + 1);
    }

    public int getWinningCards() {
        return this.winningCards.get();
    }

    public int getPlayedTotal() {
        return playedTotal.get();
    }

    public int getWin2() {
        return win2.get();
    }

    public int getWin3() {
        return win3.get();
    }

    public int getWin4() {
        return win4.get();
    }

    public int getWin5() {
        return win5.get();
    }

    public int getWin6() {
        return win6.get();
    }

    public int getWin8() {
        return win8.get();
    }

    public int getWin10() {
        return win10.get();
    }

    public int getWin15() {
        return win15.get();
    }

    public int getWin20() {
        return win20.get();
    }

    public int getWin100() {
        return win100.get();
    }

    public int getWin500() {
        return win500.get();
    }

    public int getWin5000() {
        return win5000.get();
    }

    public int getWin50000() {
        return win50000.get();
    }

    public int getTotalWins() {
        return totalWins.get();
    }

    public int getDoubleMaxWin() {
        return doubleUpMaxWin.get();
    }

    public String getDoubleUpBestResult() {
        return doubleUpBestResult.getText();
    }

    /**
     * Asettaa peliin liittyvää tilastotietoa Data-olion avulla
     *
     * @param data Data-olio
     *
     */
    public void setStats(Data data) {
        playedTotal.set(data.getPlayedTotal());
        win2.set(data.getWin2());
        win3.set(data.getWin3());
        win4.set(data.getWin4());
        win5.set(data.getWin5());
        win6.set(data.getWin6());
        win8.set(data.getWin8());
        win10.set(data.getWin10());
        win15.set(data.getWin15());
        win20.set(data.getWin20());
        win100.set(data.getWin100());
        win500.set(data.getWin500());
        win5000.set(data.getWin5000());
        win50000.set(data.getWin50000());
        totalWins.set(data.getTotalWins());
        winningCards.set(data.getWinningCards());
        doubleUpWins.set(data.getDoubleUpWins());
        doubleUpLosses.set(data.getDoubleUpLosses());
        doubleUpMaxWin.set(data.getDoubleUpMaxWin());
        doubleUpBestResult.setText(data.getDoubleUpBestResult());
    }

    /**
     * Tarkistaa, onko tuplauskentät avattu
     *
     * @return true, jos kaikki 9 kenttää on avattu, muuten false
     * 
     */
    public boolean getFieldsOpened() {
        return (field1.getOpened() && field2.getOpened() && field3.getOpened() && field4.getOpened() && field5.getOpened()
                && field6.getOpened() && field7.getOpened() && field8.getOpened() && field9.getOpened());
    }
}
