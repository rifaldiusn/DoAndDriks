package com.example.websiteminuman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.websiteminuman.entities.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    
    // Additional query methods can be defined here if needed
     @Query(value = "SELECT h.tanggal AS tanggal, SUM(p.nominal) AS total " +
                   "FROM history h JOIN payment p ON h.paymentId = p.id " +
                   "GROUP BY h.tanggal", nativeQuery = true)
    List<Object[]> getLaporanPenjualanRaw();
}
