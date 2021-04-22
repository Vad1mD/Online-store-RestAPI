package com.onlineshop.onlineshop.dataModels;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private float price;

    @Column(name="amount")
    private int amount;

    @Column(name="catalog_id")
    private Long catalog_id;

    public Item(){}

    public Item(String name, float price, int amount, Long catalog_id) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.catalog_id=catalog_id;
    }



    public Item(String name, float price, int amount){
        this(name,price,amount,null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(Long catalog_id) {
        this.catalog_id = catalog_id;
    }
}
