package cs.stockapp.data.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "stockdb", catalog = "")
public class ProductEntity implements Serializable, Comparable<ProductEntity>{
    private int id;
    private String name;
    private String unit;
    private float price;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public int compareTo(ProductEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
