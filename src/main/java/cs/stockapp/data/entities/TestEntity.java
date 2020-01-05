package cs.stockapp.data.entities;

import javax.persistence.*;

@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Basic
    public String test;
}
