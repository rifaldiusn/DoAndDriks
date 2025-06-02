package com.example.websiteminuman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.websiteminuman.entities.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    
    // Additional query methods can be defined here if needed

}
