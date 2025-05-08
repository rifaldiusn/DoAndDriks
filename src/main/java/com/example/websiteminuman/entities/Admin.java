package com.example.websiteminuman.entities;

import java.util.ArrayList;

public class Admin extends User {
    private String username;
    protected ArrayList<Minuman> M;

    public Admin(String username, String password) {
        super(password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void login() {
        if (username == null || username.isEmpty()) {
            System.out.println("Nama admin tidak boleh kosong.");
            return;
        } else if (getPassword() == null || getPassword().isEmpty()) {
            System.out.println("Password tidak boleh kosong.");
            return;
        } else {
            System.out.println("Login berhasil.");
        }
        System.out.println("Admin " + username + " logged in.");
    }

}
