package com.example.websiteminuman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.websiteminuman.entities.Minuman;

@Repository
public interface MinumanRepository extends JpaRepository<Minuman, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByNama(String nama);

}
 