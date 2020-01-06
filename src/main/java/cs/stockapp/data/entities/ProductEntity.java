package cs.stockapp.data.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class ProductEntity implements Serializable, Comparable<ProductEntity>{
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "unit")
    private String unit;
    @Basic
    @Column(name = "price")
    private double price;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(ProductEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
