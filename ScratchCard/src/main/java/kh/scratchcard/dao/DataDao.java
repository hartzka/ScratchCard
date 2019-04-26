package kh.scratchcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Datan tallennuksesta ja hakemisesta huolehtiva luokka. Tallettaa dataa
 * tietokantaan ja hakee dataa tietokannasta. Implementoi Dao-rajapintaa.
 */
public class DataDao implements Dao<Data, Integer> {

    private Database database;
    private boolean test;
    private String testText;

    public DataDao(Database database) {
        this.database = database;
        this.test = false;
        this.testText = "";
    }

    public void setTest(boolean b) {
        test = b;
        this.testText = "Test";
    }

    /**
     * Tiedon hakeminen
     *
     * @return Data-olion, joka sisältää pelissä käytettävän datan.
     */
    @Override
    public Data findOne() throws SQLException {
        Data data = new Data();
        Connection conn = database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + testText + "Data");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                data = dataActions(data, result);
                data.setDoubleUpWins(result.getInt("doubleUpWins"));
                data.setDoubleUpLosses(result.getInt("doubleUpLosses"));
                data.setDoubleUpMaxWin(result.getInt("doubleUpMaxWin"));
                data.setDoubleUpBestResult(result.getString("doubleUpBestResult"));
            }
        } catch (Exception e) {
            createTable();
        }
        return data;
    }

    /**
     * Tiedon tallennus, tallettaa datan tietokantaan
     *
     * @param data Data-olio
     */
    @Override
    public void save(Data data) throws SQLException {

        Connection conn = database.getConnection();
        String s = "UPDATE " + testText + "Data SET moneyTotal=?, playedTotal=?, win2=?, win3=?, win4=?, win5=?, win6=?, win8=?, win10=?, win15=?, win20=?, win100=?,"
                + " win500=?, win5000=?, win50000=?, totalWins=?, winningCards=?, doubleUpWins=?, doubleUpLosses=?, doubleUpMaxWin=?, doubleUpBestResult=?"
                + " WHERE id = 1";

        ResultSet rs = null;

        PreparedStatement stmt = null;

        try {
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(s,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, data.getMoneyTotal());
            stmt.setInt(2, data.getPlayedTotal());
            stmt.setInt(3, data.getWin2());
            stmt.setInt(4, data.getWin3());
            stmt.setInt(5, data.getWin4());
            stmt.setInt(6, data.getWin5());
            stmt.setInt(7, data.getWin6());
            stmt.setInt(8, data.getWin8());
            stmt.setInt(9, data.getWin10());
            stmt.setInt(10, data.getWin15());
            stmt.setInt(11, data.getWin20());
            stmt.setInt(12, data.getWin100());
            stmt.setInt(13, data.getWin500());
            stmt.setInt(14, data.getWin5000());
            stmt.setInt(15, data.getWin50000());
            stmt.setInt(16, data.getTotalWins());
            stmt.setInt(17, data.getWinningCards());
            stmt.setInt(18, data.getDoubleUpWins());
            stmt.setInt(19, data.getDoubleUpLosses());
            stmt.setInt(20, data.getDoubleUpMaxWin());
            stmt.setString(21, data.getDoubleUpBestResult());
            int rowAffected = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rowAffected != 1) {
                conn.rollback();
            }

        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }

    }

    /**
     * Luo uuden tietokantataulun Data, mikäli sitä ei ole vielä olemassa.
     * Testitilanteessa luo taulun TestData.
     *
     * @throws java.sql.SQLException jos tietokannan käsittelyssä taphtuu virhe
     *
     */
    public void createTable() throws SQLException {
        Connection conn = database.getConnection();
        conn.prepareStatement("CREATE TABLE " + testText + "Data ("
                + "id integer PRIMARY KEY, moneyTotal integer, playedTotal integer, win2 integer, win3 integer, win4 integer, win5 integer"
                + ", win6 integer, win8 integer, win10 integer, win15 integer, win20 integer, win100 integer,"
                + " win500 integer, win5000 integer, win50000 integer, totalWins integer, winningCards integer, doubleUpWins integer, "
                + "doubleUpLosses integer, doubleUpMaxWin integer, doubleUpBestResult varchar(40))").execute();

        String s = test ? "10" : "0";
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + testText + "Data ("
                + "id, moneyTotal, playedTotal, win2, win3, win4, win5"
                + ", win6, win8, win10, win15, win20, win100,"
                + " win500, win5000, win50000, totalWins, winningCards, doubleUpWins, "
                + "doubleUpLosses, doubleUpMaxWin, doubleUpBestResult)"
                + " VALUES(1, " + s + ", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)");
        stmt.execute();
        stmt.close();
        conn.close();
    }

    /**
     * Poistaa väliaikaisen testeissä käytettävän taulun TestData
     *
     * @throws java.sql.SQLException jos tietokannan käsittelyssä taphtuu virhe
     *
     */
    public void dropTableTestData() throws SQLException {
        Connection conn = database.getConnection();
        conn.prepareStatement("DROP TABLE TestData").execute();
        conn.close();
    }

    /**
     * Datan haussa käytettävä apumetodi, joka tallentaa tietoa data-olioon
     *
     * @return Data-olion
     */
    private Data dataActions(Data data, ResultSet result) throws SQLException {
        data.setMoneyTotal(result.getInt("moneyTotal"));
        data.setPlayedTotal(result.getInt("playedTotal"));
        data.setWin2(result.getInt("win2"));
        data.setWin3(result.getInt("win3"));
        data.setWin4(result.getInt("win4"));
        data.setWin5(result.getInt("win5"));
        data.setWin6(result.getInt("win6"));
        data.setWin8(result.getInt("win8"));
        data.setWin10(result.getInt("win10"));
        data.setWin15(result.getInt("win15"));
        data.setWin20(result.getInt("win20"));
        data.setWin100(result.getInt("win100"));
        data.setWin500(result.getInt("win500"));
        data.setWin5000(result.getInt("win5000"));
        data.setWin50000(result.getInt("win50000"));
        data.setTotalWins(result.getInt("totalWins"));
        data.setWinningCards(result.getInt("winningCards"));
        return data;
    }
}
