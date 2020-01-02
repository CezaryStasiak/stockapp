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
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_PRODUCTS_ON_HAND_BY_USER_ID + "(?)}");
        query.setInt(1, id);
        set = query.executeQuery();
        products = productOnHandMapper.getProductsFromProductsOnHand(set);

        if (set == null) {
            return new ArrayList<>();
        }
        connection.close();
        return products;
    }

    @Override
    public List<Product> getCurrentlyAvailableProducts() throws SQLException {
        ResultSet set = null;
        List<Product> products;
        Connection connection = connectionManager.getDatabaseConnection();
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_CURRENT_PRODUCTS + "}");
        set = query.executeQuery();
        products = productMapper.getProductsFromCurrentProducts(set);

        if (set == null) {
            return new ArrayList<>();
        }
        connection.close();
        return products;
    }

    @Override
    public void addOrUpdateProductOnHandQuantity(int productId, int userId, float quantity, boolean setQuantity) throws SQLException {

        if (productExists(productId)) {
            if (setQuantity) {
                setQuantityOfProduct(productId, userId, quantity);
            } else {
                updateOrInsertProduct(productId, userId, quantity);
            }
        }
    }

    private void setQuantityOfProduct(int productId, int userId, float quantity) throws SQLException{

        Connection connection = connectionManager.getDatabaseConnection();
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.SET_PRODUCT_QUANTITY + "(?,?,?)}");
        query.setFloat(1, quantity);
        query.setInt(2, userId);
        query.setInt(3, productId);
        query.executeQuery();
    }

    private void updateOrInsertProduct(int productId, int userId, float quantity) throws SQLException{

        Connection connection = connectionManager.getDatabaseConnection();
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_ID_IF_PRODUCT_EXISTS + "(?)}");
        query = connection.prepareStatement("{call " + StoredProceduresMapping.UPDATE_PRODUCT + "(?,?,?)}");
        query.setFloat(1, quantity);
        query.setInt(2, userId);
        query.setInt(3, productId);
        int updateCount = query.executeUpdate();
        if (updateCount <= 0){
            query = connection.prepareStatement("{call " + StoredProceduresMapping.INSERT_PRODUCT + "(?,?,?)}");
            query.setFloat(1, quantity);
            query.setInt(2, userId);
            query.setInt(3, productId);
            query.executeQuery();
        }

        connection.close();
    }

    private boolean productExists(int productId) throws SQLException{
        ResultSet set;
        Connection connection = connectionManager.getDatabaseConnection();
        PreparedStatement query =
                connection.prepareStatement("{call " + StoredProceduresMapping.GET_ID_IF_PRODUCT_EXISTS + "(?)}");
        query.setInt(1, productId);
        set = query.executeQuery();
        if (set.next()){
            connection.close();
            return true;
        }
        return false;
    }
}
