// src/main/java/com/example/websiteminuman/entities/Payment.java
package com.example.websiteminuman.entities;

import java.util.ArrayList;

public class Payment {
    private String MetodePembayaran;
    protected ArrayList<Minuman> M;
    protected ArrayList<History> H;

    public Payment(String MetodePembayaran) {
        this.MetodePembayaran = MetodePembayaran;
        this.M = new ArrayList<>();
        this.H = new ArrayList<>();
    }

    public String getMetodePembayaran() {
        return MetodePembayaran;
    }

    public void setMetodePembayaran(String MetodePembayaran) {
        this.MetodePembayaran = MetodePembayaran;
    }
}
