package kh.scratchcard.dao;

import java.sql.*;
import java.util.*;

/**
 * Dao-rajapinta tiedontallennukselle
 */
public interface Dao<T, K> {

    T findOne() throws SQLException;

    void save(T object) throws SQLException;
}
