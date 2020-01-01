package cs.stockapp.datamapping;

import cs.stockapp.models.ProductOnHand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductOnHandMapper {

    List<ProductOnHand> getProductsFromProductsOnHand(ResultSet set) throws SQLException;
}
