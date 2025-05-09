package com.example.websiteminuman.entities;

import java.util.ArrayList;

public class Customer extends User {
    private String email;
    protected ArrayList<Minuman> M; 

    public Customer(String password, String email) {
        super(password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
