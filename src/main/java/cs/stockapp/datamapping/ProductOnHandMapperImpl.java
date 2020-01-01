package cs.stockapp.datamapping;

import cs.stockapp.models.ProductOnHand;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOnHandMapperImpl implements ProductOnHandMapper {

    @Override
    public List<ProductOnHand> getProductsFromProductsOnHand(ResultSet set) throws SQLException {

        List<ProductOnHand> products = new ArrayList<ProductOnHand>();

        while (set.next()) {
            int id = set.getInt("id");
            String name = set.getString("name");
            double price = set.getDouble("price");
            float amount = set.getFloat("amount");
            String unit = set.getString("unit");

            products.add(new ProductOnHand(id, name, price, unit, amount));
        }
        return products;
    }
}
