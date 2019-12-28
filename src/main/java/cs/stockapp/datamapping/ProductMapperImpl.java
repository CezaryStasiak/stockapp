package cs.stockapp.datamapping;

import cs.stockapp.models.Product;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapperImpl implements ProductMapper {

    @Override
    public List<Product> getProductsFromResultSet(ResultSet set) throws SQLException {

        List<Product> products = new ArrayList<Product>();

        while (set.next()) {
            int id = set.getInt("id");
            String name = set.getString("name");
            double price = set.getDouble("price");
            int amount = set.getInt("amount");

            products.add(new Product(id, name, price, amount));
        }
        return products;
    }
}
