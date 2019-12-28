package cs.stockapp.dataaccess;

import cs.stockapp.models.Product;

import java.sql.SQLException;
import java.util.List;

public interface JDBCProductsManager {

    List<Product> getProductsOnHandByUserId(int id) throws SQLException;
}
