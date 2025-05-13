package com.example.websiteminuman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websiteminuman.entities.Admin;
import java.util.List;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByUsername(String username);
    List<Admin> findByUsername(String username);
}
