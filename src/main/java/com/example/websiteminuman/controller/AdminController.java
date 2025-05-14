package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.dto.AdminDto;
import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.mapper.AdminMapper;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.websiteminuman.repositories.AdminRepository;
import com.example.websiteminuman.service.AdminAuthService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth/admin")
public class AdminController {
    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;
    private final AdminAuthService adminService;

    public AdminController(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
        this.adminService = new AdminAuthService();
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
    public ResponseEntity<AuthResponseDto> loginAdmin(@RequestBody AdminDto dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }

    // @GetMapping("/minuman/{id}")
    // public Iterable<Minuman> CreateMinuman(@PathVariable Long id) {
    //     var admin = adminRepository.findById(id).orElse(null);
    //     if (admin == null){
    //         return null;
    //     }
    //     return admin.getM();
    // }
}    
