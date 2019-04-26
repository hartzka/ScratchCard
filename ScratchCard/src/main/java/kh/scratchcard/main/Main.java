package kh.scratchcard.main;

import java.sql.*;
import kh.scratchcard.domain.ScratchCard;

/**
 * Sovelluksen käynnistävä main-luokka
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        ScratchCard.launch(ScratchCard.class, args);
    }
}