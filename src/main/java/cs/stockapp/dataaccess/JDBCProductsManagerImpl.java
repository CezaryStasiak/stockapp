package cs.stockapp.dataaccess;

import cs.stockapp.datamapping.ProductMapper;
import cs.stockapp.models.Product;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class JDBCProductsManagerImpl implements JDBCProductsManager {

    private final JDBCConnectionManager connectionManager;
    private final ProductMapper productMapper;

    public JDBCProductsManagerImpl(JDBCConnectionManager connectionManager, ProductMapper productMapper) {
        this.connectionManager = connectionManager;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProductsOnHandByUserId(int id) throws SQLException {

        ResultSet set = null;
        List<Product> products;
        Connection connection = connectionManager.getDatabaseConnection();
        try {
            PreparedStatement query =
                    connection.prepareStatement("{call " + StoredProceduresMapping.GET_PRODUCTS_ON_HAND_BY_USER_ID + "(?)}");
            query.setInt(1, id);
            set = query.executeQuery();
            products = productMapper.getProductsFromResultSet(set);
        } finally {
            connection.close();
        }
        if (set == null) {
            return new ArrayList<>();
        }
        return products;
    }
}
