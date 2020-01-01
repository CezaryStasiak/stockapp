package cs.stockapp.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCConnectionManager {
    Connection getDatabaseConnection() throws SQLException;
}
