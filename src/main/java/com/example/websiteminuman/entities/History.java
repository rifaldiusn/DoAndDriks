package com.example.websiteminuman.entities;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date tanggal;
    private Long paymentId;
    private Long customerId;
    private Long minumanId;

    public History() {
        this.tanggal = new Date();
        this.paymentId = null;
        this.customerId = null;
        this.minumanId = null;
    }

    public Long getId() {
        return id;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getMinumanId() {
        return minumanId;
    }
    public void setMinumanId(Long minumanId) {
        this.minumanId = minumanId;
    }

    
}
