package cs.stockapp.dataaccess;

import cs.stockapp.models.Product;
import cs.stockapp.models.ProductOnHand;

import java.sql.SQLException;
import java.util.List;

public interface JDBCProductsManager {

    List<ProductOnHand> getProductsOnHandByUserId(int id) throws SQLException;

    List<Product> getCurrentlyAvailableProducts() throws SQLException;

    void addOrUpdateProductOnHand(int productId, int userId, float quantity, boolean setQuantity) throws SQLException;
}
