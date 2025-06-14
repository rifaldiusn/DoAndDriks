package com.example.websiteminuman.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long minumanId;


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setMinumanId(Long minumanId) {
        this.minumanId = minumanId;
    }
    public Long getMinumanId() {
        return minumanId;
    }

    public Long getId() {
        return id;
    }

    public Cart(){
        this.customerId = null;
        this.minumanId = null;
    }
}
