package com.example.websiteminuman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.websiteminuman.dto.CustomerDto;
import com.example.websiteminuman.dto.DelUpMinuman;
import com.example.websiteminuman.entities.Customer;
import com.example.websiteminuman.repositories.CustomerRepository;
import com.example.websiteminuman.repositories.MinumanRepository;

@Service
public class CustomerAuthService {
    @Autowired
    private CustomerRepository customerRepo;
    private MinumanRepository minumanRepo;

    public Customer login(CustomerDto dto) {
        var customer = customerRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email Tidak Ditemukan"));

        if (!customer.getPassword().equals(dto.getPassword())) {
           throw new BadCredentialsException("Invalid credentials");
        }

        return customer;
    }

    public Customer login2(String email, String password) {
        var customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email Tidak Ditemukan"));

        if (!customer.getPassword().equals(password)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return customer;
    }
}

