package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.dto.AdminDto;
import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.dto.MinumanDto;
import com.example.websiteminuman.entities.Admin;
// import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.mapper.AdminMapper;
import com.example.websiteminuman.mapper.MinumanMapper;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.websiteminuman.repositories.AdminRepository;
import com.example.websiteminuman.repositories.MinumanRepository;
import com.example.websiteminuman.service.AdminAuthService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/auth/admin")
public class AdminController {
    private final AdminMapper adminMapper;
    private final MinumanMapper minumanMapper;
    private final AdminRepository adminRepository;
    private final MinumanRepository minumanRepository;
    private final AdminAuthService adminService;

    public AdminController(AdminRepository adminRepository, AdminMapper adminMapper, MinumanRepository minumanRepository, AdminAuthService adminService, MinumanMapper minumanMapper) {
        this.minumanMapper = minumanMapper;
        this.minumanRepository = minumanRepository;
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }

    @GetMapping
    public Iterable<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
            .stream()
            .map(adminMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {
        var admin = adminRepository.findById(id).orElse(null);
        if (admin == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adminMapper.toDto(admin));
    }

    @GetMapping("/sort/{field}")
    public Iterable<AdminDto> getSortedAdmins(@PathVariable String field) {
        return adminRepository.findAll(Sort.by(field))
            .stream()
            .map(adminMapper::toDto)
            .toList();
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody AdminDto adminDto) {
        try {
            var dto = new AdminDto(null, null);
            dto.setUsername(adminDto.getUsername());
            dto.setPassword(adminDto.getPassword());
            System.out.println(dto.getUsername() + " " + dto.getPassword());
            return ResponseEntity.ok(adminService.login(dto));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/minuman/get")
    public Iterable<MinumanDto> getAllMinuman() {
        List<MinumanDto> list = minumanRepository.findAll()
        .stream()
        .map(minumanMapper::toDto)
        .toList();

        System.out.println("DATA MINUMAN: " + list);
        return list;
    }

    @PostMapping("/minuman/create")
    public ResponseEntity<?> createMinuman(@RequestBody MinumanDto dto,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        try {
            // Ambil username/email dari sesi login
            String username = userDetails.getUsername();

            // Temukan admin berdasarkan username
            Admin admin = adminRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("Admin tidak ditemukan"));

            // Mapping dto ke entity
            Minuman minuman = new Minuman();
            minuman.setNama(dto.getNama());
            minuman.setJenis(dto.getJenis());
            minuman.setDeskripsi(dto.getDeskripsi());
            minuman.setHarga(dto.getHarga());
            minuman.setAdmin(admin); // set FK

            return ResponseEntity.ok(minumanRepository.save(minuman));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


    @PutMapping("minuman/update/{id}")
    public ResponseEntity<Minuman> updateMinuman(@PathVariable Long id, @RequestBody Minuman minuman) {
        var existingMinuman = minumanRepository.findById(id);
        if (existingMinuman.isPresent()) {
            var updatedMinuman = existingMinuman.get();
            updatedMinuman.setNama(minuman.getNama());
            updatedMinuman.setJenis(minuman.getJenis());
            // updatedMinuman.setUkuran(minuman.getUkuran());
            updatedMinuman.setHarga(minuman.getHarga());
            minumanRepository.save(updatedMinuman);
            return ResponseEntity.ok(updatedMinuman);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/minuman/delete/{id}")
    public ResponseEntity<Void> deleteMinuman(@PathVariable Long id) {
        var existingMinuman = minumanRepository.findById(id);
        if (existingMinuman.isPresent()) {
            minumanRepository.delete(existingMinuman.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}    
