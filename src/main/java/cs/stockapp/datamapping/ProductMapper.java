package cs.stockapp.datamapping;

import cs.stockapp.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductMapper {
    List<Product> getProductsFromResultSet(ResultSet set) throws SQLException;
}
