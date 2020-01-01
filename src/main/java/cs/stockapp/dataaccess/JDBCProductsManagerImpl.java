package cs.stockapp.dataaccess;

import cs.stockapp.datamapping.ProductMapper;
import cs.stockapp.datamapping.ProductOnHandMapper;
import cs.stockapp.models.Product;
import cs.stockapp.models.ProductOnHand;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class JDBCProductsManagerImpl implements JDBCProductsManager {

    private final JDBCConnectionManager connectionManager;
    private final ProductOnHandMapper productOnHandMapper;
    private final ProductMapper productMapper;

    public JDBCProductsManagerImpl(JDBCConnectionManager connectionManager, ProductOnHandMapper productOnHandMapper,
                                    ProductMapper productMapper) {
        this.connectionManager = connectionManager;
        this.productOnHandMapper = productOnHandMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductOnHand> getProductsOnHandByUserId(int id) throws SQLException {

        ResultSet set = null;
        List<ProductOnHand> products;
        Connection connection = connectionManager.getDatabaseConnection();
        try {
            PreparedStatement query =
                    connection.prepareStatement("{call " + StoredProceduresMapping.GET_PRODUCTS_ON_HAND_BY_USER_ID + "(?)}");
            query.setInt(1, id);
            set = query.executeQuery();
            products = productOnHandMapper.getProductsFromProductsOnHand(set);
        } finally {
            connection.close();
        }
        if (set == null) {
            return new ArrayList<>();
        }
        return products;
    }

    @Override
    public List<Product> getCurrentlyAvailableProducts() throws SQLException {
        ResultSet set = null;
        List<Product> products;
        Connection connection = connectionManager.getDatabaseConnection();
        try {
            PreparedStatement query =
                    connection.prepareStatement("{call " + StoredProceduresMapping.GET_CURRENT_PRODUCTS + "}");
            set = query.executeQuery();
            products = productMapper.getProductsFromCurrentProducts(set);
        } finally {
            connection.close();
        }
        if (set == null) {
            return new ArrayList<>();
        }
        return products;
    }
}
