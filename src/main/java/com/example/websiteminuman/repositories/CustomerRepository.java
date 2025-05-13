package com.example.websiteminuman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websiteminuman.entities.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByUsername(String username);
    List<Customer> findByEmail(String email);

}
