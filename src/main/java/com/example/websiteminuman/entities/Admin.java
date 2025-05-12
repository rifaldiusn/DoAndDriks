package com.example.websiteminuman.entities;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin extends User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    protected ArrayList<Minuman> M;

    public Admin() {
        super("");
    }
    
    public Admin(Long id, String username, String password) {
        super(password);
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
