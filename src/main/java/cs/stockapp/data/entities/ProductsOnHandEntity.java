package cs.stockapp.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "products_on_hand")
public class ProductsOnHandEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "amount")
    private double amount;

    @Basic
    @Column(name = "shop_id")
    private int shopId;

    @Basic
    @Column(name = "product_id")
    private int productId;


    public ProductsOnHandEntity() {
    }

    public ProductsOnHandEntity(int shopId, int productId, double quantity) {
        this.shopId = shopId;
        this.productId = productId;
        this.amount = quantity;
    }

    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public int getShopId() {
        return shopId;
    }
    public int getProductId() {
        return productId;
    }

    public void addQuantity(double quantity){
        this.amount = this.getAmount() + quantity;
    }

    public void substractQuantity(double quantity){
        this.amount = this.getAmount() - quantity;
    }

    public void setQuantity(double quantity){
        this.amount = quantity;
    }
}
