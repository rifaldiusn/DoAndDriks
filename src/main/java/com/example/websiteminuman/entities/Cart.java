package com.example.websiteminuman.entities;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String customerEmail;

    @OneToMany(mappedBy = "cart")
    private ArrayList<Minuman> cartItems;

    private int jumlah;


    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public Cart(Long id, String customerEmail) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.cartItems = new ArrayList<>();
    }

    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public ArrayList<Minuman> getCartItems() {
        return cartItems;
    }

    public void setMinuman(Minuman cartItems) {
        this.cartItems.add(cartItems);
    }
}
