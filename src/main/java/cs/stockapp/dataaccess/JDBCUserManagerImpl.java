package cs.stockapp.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class JDBCUserManagerImpl implements JDBCUserManager {

    private final JDBCConnectionManager connectionManager;

    @Autowired
    public JDBCUserManagerImpl(JDBCConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public int getUserIdIfExists(String userName, String password) throws SQLException{
        int id = -1;
        Connection connection = connectionManager.getDatabaseConnection();
        try {
            PreparedStatement query =
                    connection.prepareStatement("{call " + StoredProceduresMapping.GET_USER_ID_IF_EXISTS + "(?,?)}");
            query.setString(1, userName);
            query.setString(2, password);
            ResultSet set = query.executeQuery();

            if (set.first()) {
                id = set.getInt("id");
            }
        }
        finally {
            connection.close();
            return id;
        }
    }

    @Override
    public String getUserFirstNameByUserId(int id) throws SQLException{
        String userFirstName = "";
        Connection connection = connectionManager.getDatabaseConnection();

        try{
            PreparedStatement query =
                    connection.prepareStatement("{call " + StoredProceduresMapping.GET_USER_FIRST_NAME_BY_USER_ID + "(?)}");
            query.setInt(1, id);
            ResultSet set = query.executeQuery();

            if (set.first()) {
                userFirstName = set.getString("name");
            }

        } finally {
            connection.close();
            return userFirstName;
        }
    }

    @Override
    public String getShopNameByUserId(int id) throws SQLException {
        return null;
    }
}