package com.example.websiteminuman.entities;

import java.util.ArrayList;

public class Customer extends User {
    private String email;
    protected ArrayList<Minuman> M;
    protected ArrayList<History> H;
    protected ArrayList<Payment> P;

    public Customer(String password, String email) {
        super(password);
        this.email = email;
        this.M = new ArrayList<>();
        this.H = new ArrayList<>();
        this.P = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void AddToCart(Minuman minuman) {
        if (minuman != null) {
            M.add(minuman);
            System.out.println("Minuman " + minuman.getNama() + " ditambahkan ke keranjang.");
        } else {
            System.out.println("Minuman tidak boleh null.");
        }
    }

    public void OrderMinuman(String nama, String jenis, String ukuran, int harga) {
        Minuman minumanBaru = new Minuman(nama, jenis, ukuran, harga);
        AddToCart(minumanBaru);
    }

    @Override
    public void login() {
        if (email == null || email.isEmpty()) {
            System.out.println("Email tidak boleh kosongs.");
            return;
        } else if (getPassword() == null || getPassword().isEmpty()) {
            System.out.println("Password tidak boleh kosong.");
            return;
        } else {
            System.out.println("Login berhasil.");
        }
        System.out.println("Customer " + email + " logged in.");
    }
}
