package cs.stockapp.datamapping;

import cs.stockapp.models.Product;
import cs.stockapp.models.ProductOnHand;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapperImpl implements ProductMapper {

    @Override
    public List<Product> getProductsFromCurrentProducts(ResultSet set) throws SQLException {
        List<Product> products = new ArrayList<Product>();

        while (set.next()) {
            int id = set.getInt("id");
            String name = set.getString("name");
            String unit = set.getString("unit");
            double price = set.getDouble("price");

            products.add(new Product(id, name, price, unit));
        }
        return products;
    }
}
