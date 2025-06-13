package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.dto.AdminDto;
import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.dto.LaporanPenjualanDto;
import com.example.websiteminuman.dto.MinumanDto;
import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.mapper.AdminMapper;
import com.example.websiteminuman.mapper.MinumanMapper;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.websiteminuman.repositories.AdminRepository;
import com.example.websiteminuman.repositories.HistoryRepository;
import com.example.websiteminuman.repositories.MinumanRepository;
import com.example.websiteminuman.service.AdminAuthService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/auth/admin")
public class AdminController {
    private final AdminMapper adminMapper;
    private final MinumanMapper minumanMapper;
    private final AdminRepository adminRepository;
    private final MinumanRepository minumanRepository;
    private final AdminAuthService adminService;
    private final HistoryRepository historyRepository;

    public AdminController(AdminRepository adminRepository, AdminMapper adminMapper, MinumanRepository minumanRepository, AdminAuthService adminService, MinumanMapper minumanMapper, HistoryRepository historyRepository) {
        this.minumanMapper = minumanMapper;
        this.minumanRepository = minumanRepository;
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
        this.adminService = adminService;
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public Iterable<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
            .stream()
            .map(adminMapper::toDto)
            .toList();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminDto adminDto, HttpSession session) {
        try {
            var dto = new AdminDto(null, null);
            dto.setUsername(adminDto.getUsername());
            dto.setPassword(adminDto.getPassword());

            Admin admin = adminService.login(dto);

            session.setAttribute("username", admin.getUsername());

            return ResponseEntity.ok("Login berhasil");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Login Gagal");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Berhasil logout");
    }
    
    @GetMapping("/minuman/get")
    public Iterable<MinumanDto> getAllMinuman() {
        List<MinumanDto> list = minumanRepository.findAll()
        .stream()
        .map(minumanMapper::toDto)
        .toList();
        return list;
    }

    @PostMapping("/minuman/create")
    public ResponseEntity<?> createMinuman(@RequestBody MinumanDto dto,
                                        HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Belum login");
            }

            Admin admin = adminRepository.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("Admin tidak ditemukan"));

            Minuman minuman = minumanMapper.toEntity(dto);
            minuman.setAdmin(admin);

            System.out.println("CREATE MINUMAN: " + minuman.getNama() + " by " + admin.getUsername());
            return ResponseEntity.ok(minumanRepository.save(minuman));
        } catch (Exception e) {
            e.printStackTrace(); // debug error sebenarnya
            return ResponseEntity.badRequest().body("Error creating Minuman: " + e.getMessage());
        }
    }



    @PutMapping("/minuman/update/{id}")
    public ResponseEntity<?> updateMinuman(@PathVariable Long id,
                                        @RequestBody MinumanDto dto,
                                        HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Belum login");
            }

            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Admin tidak ditemukan"));

            Minuman existingMinuman = minumanRepository.findById(id)
                    .orElse(null);
            if (existingMinuman == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Minuman tidak ditemukan");
            }

            existingMinuman.setNama(dto.getNama());
            existingMinuman.setHarga(dto.getHarga());
            existingMinuman.setDeskripsi(dto.getDeskripsi());
            existingMinuman.setJenis(dto.getJenis());
            existingMinuman.setGambar(dto.getGambar());
            existingMinuman.setAdmin(admin);

            minumanRepository.save(existingMinuman);

            return ResponseEntity.ok("Minuman berhasil diupdate");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating Minuman: " + e.getMessage());
        }
    }

    @DeleteMapping("/minuman/delete/{id}")
    public ResponseEntity<Void> deleteMinuman(@PathVariable Long id, HttpSession session) {
        try {
            Minuman minuman = minumanRepository.findById(id).orElse(null);
            if (minuman == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            minumanRepository.delete(minuman);
            String username = (String) session.getAttribute("username");
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            System.out.println("MINUMAN DELETED BY: " + username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/laporan")
    public List<LaporanPenjualanDto> getLaporan() {
        List<Object[]> result = historyRepository.getLaporanPenjualanRaw();
        return result.stream()
                .map(row -> new LaporanPenjualanDto(
                        ((Date) row[0]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        ((Number) row[1]).intValue()
                ))
                .collect(Collectors.toList());
    }
}    
