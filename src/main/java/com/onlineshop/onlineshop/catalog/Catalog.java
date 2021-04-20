package com.onlineshop.onlineshop.catalog;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="catalogs")
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="ownerId")
    private long ownerId;

    public Catalog(){}

    public Catalog(String name, long ownerId) {
        this.name = name;
        this.ownerId=ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
