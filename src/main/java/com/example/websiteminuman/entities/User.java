package com.example.websiteminuman.entities;

public abstract class User {
    private String password;

    public User(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public abstract void login();
}
