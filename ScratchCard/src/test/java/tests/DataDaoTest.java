package tests;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kh.scratchcard.dao.Data;
import kh.scratchcard.dao.DataDao;
import kh.scratchcard.dao.Database;
import kh.scratchcard.domain.Hand;
import kh.scratchcard.domain.ScratchCard;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DataDaoTest {

    DataDao dataDao;
    Database database;

    @Before
    public void setUp() throws SQLException {
        try {
            database = new Database("jdbc:sqlite:data.db");
            dataDao = new DataDao(database);
            dataDao.setTest(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScratchCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void dataDaoExists() {
        assertTrue(dataDao != null);
    }

    @Test
    public void createTableWorks() throws SQLException {
        dataDao.createTable();
        Data data = dataDao.findOne();
        dataDao.dropTableTestData();
        assertTrue(data.getMoneyTotal() == 10);
    }

    @Test
    public void dataIsSaved() throws SQLException {
        dataDao.createTable();
        Data data = new Data();
        data.setMoneyTotal(5);
        data.setDoubleUpLosses(10);
        data.setTotalWins(20);
        dataDao.save(data);
        Data data2 = dataDao.findOne();
        dataDao.dropTableTestData();
        assertTrue(data2.getMoneyTotal() == 5);
        assertTrue(data2.getDoubleUpLosses() == 10);
        assertTrue(data2.getTotalWins() == 20);
    }
}
