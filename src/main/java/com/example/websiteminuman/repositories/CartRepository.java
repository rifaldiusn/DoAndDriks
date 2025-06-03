package com.example.websiteminuman.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websiteminuman.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByCustomerEmail(String customerEmail);
    List<Cart> findByMinumanId(Long minumanId);
    List<Cart> findByCustomerId(Long customerId);
}
