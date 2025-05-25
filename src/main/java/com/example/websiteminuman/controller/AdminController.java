package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.dto.AdminDto;
import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.entities.Admin;
// import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.mapper.AdminMapper;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    private final AdminRepository adminRepository;
    private final MinumanRepository minumanRepository;
    private final AdminAuthService adminService;

    public AdminController(AdminRepository adminRepository, AdminMapper adminMapper, MinumanRepository minumanRepository, AdminAuthService adminService) {
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
    public ResponseEntity<Admin> loginAdmin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        try {
            AdminDto dto = new AdminDto(username, password);
            dto.setUsername(username);
            dto.setPassword(password);
            return ResponseEntity.ok(adminService.login(dto));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/minuman/get")
    public Iterable<Minuman> getAllMinuman() {
        return minumanRepository.findAll()
            .stream()
            .map(minuman -> new Minuman(minuman.getId(), minuman.getNama(), minuman.getJenis(), minuman.getUkuran(), minuman.getHarga()))
            .toList();
    }

    @PostMapping("/minuman/create")
    public ResponseEntity<Minuman> createMinuman(@RequestBody Minuman minuman) {
        var newMinuman = new Minuman(minuman.getId(), minuman.getNama(), minuman.getJenis(), minuman.getUkuran(), minuman.getHarga());
        minumanRepository.save(newMinuman);
        return ResponseEntity.ok(newMinuman);
    }

    @PutMapping("minuman/update")
    public ResponseEntity<Minuman> updateMinuman(@PathVariable Long id, @RequestBody Minuman minuman) {
        var existingMinuman = minumanRepository.findById(id);
        if (existingMinuman.isPresent()) {
            var updatedMinuman = existingMinuman.get();
            updatedMinuman.setNama(minuman.getNama());
            updatedMinuman.setJenis(minuman.getJenis());
            updatedMinuman.setUkuran(minuman.getUkuran());
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
