package cs.stockapp.data.entities;

import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import lombok.Value;

import java.util.List;

@Value
public class PagesOfProducts {
    List<ProductsOnHandQuantityModel> products;
    int pages;
}
