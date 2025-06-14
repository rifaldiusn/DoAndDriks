// src/main/java/com/example/websiteminuman/entities/Payment.java
package com.example.websiteminuman.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Long customer_id;
    private String metodePembayaran;
    private int nominal;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Long customerId) {
        this.customer_id = customerId;
    }

    public String getMetode() {
        return metodePembayaran;
    }

    public void setMetode(String metode) {
        this.metodePembayaran = metode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
}
