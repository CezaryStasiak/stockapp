package cs.stockapp.data.models;

import cs.stockapp.data.entities.ProductEntity;


public class ProductsOnHandQuantityModel implements Comparable<ProductsOnHandQuantityModel>{
    private final ProductEntity productEntity;
    private final double quantity;

    public ProductsOnHandQuantityModel(ProductEntity productEntity, double quantity) {
        this.productEntity = productEntity;
        this.quantity = quantity;
    }

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
