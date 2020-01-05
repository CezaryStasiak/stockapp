package cs.stockapp.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "shop", schema = "stockdb", catalog = "")
public class ShopEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String name;
    private String address;

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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
