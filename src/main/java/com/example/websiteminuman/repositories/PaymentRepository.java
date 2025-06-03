package com.example.websiteminuman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.websiteminuman.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}
