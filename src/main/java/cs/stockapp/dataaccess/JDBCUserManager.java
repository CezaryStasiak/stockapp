package cs.stockapp.dataaccess;

import java.sql.SQLException;

public interface JDBCUserManager {

    int getUserIdIfExists(String userName, String password);

    String getUserFirstNameByUserId(int id);

    String getShopNameByUserId(int id) throws SQLException;

}
