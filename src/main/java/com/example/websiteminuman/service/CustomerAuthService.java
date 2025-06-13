package com.example.websiteminuman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.websiteminuman.entities.Customer;
import com.example.websiteminuman.repositories.CustomerRepository;
import com.example.websiteminuman.repositories.MinumanRepository;

@Service
public class CustomerAuthService {
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer login(String email, String password) {
        var customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email Tidak Ditemukan"));
        
        String currentPassword = customer.getPassword();

        if (!currentPassword.startsWith("$2a$") && !currentPassword.startsWith("$2b$") && !currentPassword.startsWith("$2y$")) {
            String hashedPassword = passwordEncoder.encode(currentPassword);
            customer.setPassword(hashedPassword);
            customerRepo.save(customer);
        }

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return customer;
    }
}

