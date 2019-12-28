package cs.stockapp.dataaccess;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JDBCConnectionManagerImpl implements JDBCConnectionManager {

    @Override
    public Connection getDatabaseConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBCConfig.CONNECTION_STRING, JDBCConfig.USER_NAME, JDBCConfig.PASSWORD);
        } catch (SQLException e){

        }
        return connection;
    }
}
