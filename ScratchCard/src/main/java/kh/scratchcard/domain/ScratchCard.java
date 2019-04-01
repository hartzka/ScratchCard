package kh.scratchcard.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kh.scratchcard.ui.Ui;

public class ScratchCard extends Application {

    private Ui ui;
    private Random random = new Random();
    private boolean tableVisible = true;
    SimpleStringProperty winText;
    public static int imageSize;
    public static int doubleImageSize;
    private Hand hand;
    private SimpleBooleanProperty play = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty progress = new SimpleBooleanProperty(false);

    private ToggleButton tableButton;
    private ToggleButton claimButton;
    private ToggleButton newButton;
    private Button statsButton;
    private ToggleButton doubleButton;
    Text win = new Text("");
    SimpleIntegerProperty moneyTotal = new SimpleIntegerProperty(0);
    SimpleIntegerProperty moneySession = new SimpleIntegerProperty(0);
    boolean doubleVisible = false;
    boolean doubleLocked = false;
    Rectangle2D visualBounds;
    private double width;
    private double height;

    public static HashMap<String, Image> images;

    int doubleBestMultiplier = 0;

    private SimpleIntegerProperty roundWinStart = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWin = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx2 = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx3 = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx7 = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty roundWinx30 = new SimpleIntegerProperty(0);
    private boolean statsVisible = false;
    private Text unbelievable = new Text("UNBELIEVABLE! ");
    
    public ScratchCard() {
        
    }

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {
        imageSize = (int) (0.19 * 0.6 * Screen.getPrimary().getVisualBounds().getHeight());
        doubleImageSize = (int) (imageSize + (int) ((0.4) * (0.35 * Screen.getPrimary().getVisualBounds().getWidth() - (1.5 * 0.19 * Screen.getPrimary().getVisualBounds().getHeight()))) / 2);
        tableButton = new ToggleButton(" WIN-\nTABLE");
        claimButton = new ToggleButton("CLAIM");
        newButton = new ToggleButton("  NEW/\nCOLLECT");
        doubleButton = new ToggleButton("DOUBLE\n    UP");
        statsButton = new Button("STATS");
        visualBounds = Screen.getPrimary().getVisualBounds();
        width = visualBounds.getWidth();
        height = visualBounds.getHeight();
        images = new HashMap();
        winText = new SimpleStringProperty("Win: ");
        win.textProperty().bind(winText.concat(roundWin.asString()).concat(".00"));

        unbelievable.setFill(Color.AZURE);
        unbelievable.setFont(Font.font("Verdana", 16));
        unbelievable.setVisible(false);
        ui = new Ui(this);

        
        newButton.disableProperty().bind(progress);
        claimButton.disableProperty().bind(play);

        claimButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                progress.set(false);
                play.set(true);
            }
        });

        newButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (roundWin.get() > 0) {
                    moneyTotal.set(moneyTotal.get() + roundWin.get());
                    moneySession.set(moneySession.get() + roundWin.get());
                    if (roundWin.get() > roundWinStart.get()) {
                        if (roundWin.get() / roundWinStart.get() > doubleBestMultiplier) {
                            doubleBestMultiplier = roundWin.get() / roundWinStart.get();
                        }
                    }

                    roundWin.set(0);
                    roundWinStart.set(0);

                    ui.setWinVisible(false);
                    doubleButton.setDisable(true);
                    ui.handleNewButtonWinTaking();
                    doubleVisible = false;
                } else {
                    hand.randomize_Hand();
                    winText.set("Win: ");
                    doubleVisible = false;
                    ui.handleNewButtonNewCard();
                    progress.set(true);
                    play.set(false);
                }
            }
        });

        ui.start(primaryStage);

        hand = new Hand(this, ui.getField(1), ui.getField(2), ui.getField(3), ui.getField(4), ui.getField(5), ui.getField(6), ui.getField(7), ui.getField(8), ui.getField(9));
    }

    

    public ToggleButton getTableButton() {
        return this.tableButton;
    }

    public ToggleButton getClaimButton() {
        return this.claimButton;
    }

    public ToggleButton getNewButton() {
        return this.newButton;
    }

    public Button getStatsButton() {
        return this.statsButton;
    }

    public ToggleButton getDoubleButton() {
        return this.doubleButton;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Text getWin() {
        return this.win;
    }

    public SimpleStringProperty getWinText() {
        return this.winText;
    }

    public SimpleIntegerProperty getMoneyTotal() {
        return this.moneyTotal;
    }

    public SimpleIntegerProperty getMoneySession() {
        return this.moneySession;
    }
}
