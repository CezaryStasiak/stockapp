package cs.stockapp.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class JDBCUserManagerImpl implements JDBCUserManager {

    private final JDBCConnectionManager connectionManager;

    @Autowired
    public JDBCUserManagerImpl(JDBCConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public int getUserIdIfExists(String userName, String password) throws SQLException {
        int id = -1;
        Connection connection = connectionManager.getDatabaseConnection();
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_USER_ID_IF_EXISTS + "(?,?)}");
        query.setString(1, userName);
        query.setString(2, password);
        ResultSet set = query.executeQuery();

        if (set.first()) {
            id = set.getInt("id");
        }
        connection.close();
        return id;
    }

    @Override
    public String getUserFirstNameByUserId(int id) throws SQLException {
        String userFirstName = "";
        Connection connection = connectionManager.getDatabaseConnection();

        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_USER_FIRST_NAME_BY_USER_ID + "(?)}");
        query.setInt(1, id);
        ResultSet set = query.executeQuery();

        if (set.first()) {
            userFirstName = set.getString("name");
        }

        connection.close();
        return userFirstName;
    }

    @Override
    public String getShopNameByUserId(int id) throws SQLException {
        return null;
    }
}