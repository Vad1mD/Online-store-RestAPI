package com.onlineshop.onlineshop.dataModels;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @NotNull
    @Column(name="role")
    private String role;

    @Column(name="payment_method")
    private String paymentMethod;

    public Users() {
    }

    public Users(Long id, String userName, String role, String paymentMethod) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
