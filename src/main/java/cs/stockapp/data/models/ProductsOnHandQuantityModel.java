package cs.stockapp.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.entities.ProductsOnHandEntity;

public class ProductsOnHandQuantityModel extends ProductsOnHandEntity implements Comparable<ProductsOnHandQuantityModel>{
    private final ProductEntity productEntity;
    private double quantity;

    public ProductsOnHandQuantityModel(ProductEntity productEntity, double quantity) {
        this.productEntity = productEntity;
        this.quantity = quantity;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(ProductsOnHandQuantityModel o) {
        return this.getProductEntity().getName().compareTo(o.getProductEntity().getName());
    }
}
