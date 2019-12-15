package cs.stockapp.dataaccess;

import java.sql.SQLException;

public interface JDBCUserManager {

    int getUserIdIfExists(String userName, String password) throws SQLException;
}
