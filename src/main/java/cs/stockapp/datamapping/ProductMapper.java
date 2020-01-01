package cs.stockapp.datamapping;

import cs.stockapp.models.Product;
import cs.stockapp.models.ProductOnHand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductMapper {

    List<Product> getProductsFromCurrentProducts(ResultSet set) throws SQLException;

}
