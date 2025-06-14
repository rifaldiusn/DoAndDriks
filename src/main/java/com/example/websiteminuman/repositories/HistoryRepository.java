package com.example.websiteminuman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.websiteminuman.entities.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    
    // Additional query methods can be defined here if needed
    @Query(value = """
    SELECT l.tanggal, SUM(l.nominal) AS total
    FROM (
        SELECT CAST(h.tanggal AS DATE) AS tanggal, p.id AS payment_id, p.nominal
        FROM history h
        JOIN payment p ON h.paymentId = p.id
        GROUP BY CAST(h.tanggal AS DATE), p.id, p.nominal
    ) l
    GROUP BY l.tanggal
    ORDER BY l.tanggal
    """, nativeQuery = true)
    List<Object[]> getLaporanPenjualanRaw();
    List<History> findByCustomerId(Long customerId);
    List<History> findByMinumanId(Long minumanId);
}
