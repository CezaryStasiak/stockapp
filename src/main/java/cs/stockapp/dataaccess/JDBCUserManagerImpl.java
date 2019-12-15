package cs.stockapp.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class JDBCUserManagerImpl implements JDBCUserManager {

    private final JDBCConnectionManager connectionManager;
    @Autowired
    public JDBCUserManagerImpl(JDBCConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @Override
    public int getUserIdIfExists(String userName, String password) throws SQLException {

        Connection connection = connectionManager.getDatabaseConnection();
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_USER_ID_IF_EXISTS + "(?,?)}");
        query.setString(1, userName);
        query.setString(2, password);
        ResultSet set = query.executeQuery();
        if (set.first()){
            return set.getInt("id");
        }
        else
        {
            return -1;
        }

    }
}
