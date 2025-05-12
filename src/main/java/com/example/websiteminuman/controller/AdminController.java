package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.entities.Admin;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.websiteminuman.repositories.AdminRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public Iterable<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Admin> getAdminById(@PathVariable Long id) {
        var admin = adminRepository.findById(id).orElse(null);
        if (admin == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/sort/{field}")
    public Iterable<Admin> getSortedAdmins(@PathVariable String field) {
        return adminRepository.findAll(Sort.by(field));
    }
}    
