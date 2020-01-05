package cs.stockapp.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "products_on_hand", schema = "stockdb", catalog = "")
public class ProductsOnHandEntity {
    private int id;
    private double amount;
    private int shopId;
    private int productId;

    public ProductsOnHandEntity() {
    }

    public ProductsOnHandEntity(int shopId, int productId, double quantity) {
        this.shopId = shopId;
        this.productId = productId;
        this.amount = quantity;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "shop_id")
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void addQuantity(double quantity){
        this.setAmount(this.getAmount() + quantity);
    }

    public void substractQuantity(double quantity){
        this.setAmount(this.getAmount() - quantity);
    }

    public void setQuantity(double quantity){
        this.setAmount(quantity);
    }
}
