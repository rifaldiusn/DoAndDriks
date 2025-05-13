package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.entities.Customer;
import com.example.websiteminuman.mapper.AdminMapper;
import com.example.websiteminuman.repositories.AdminRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;

     public LoginController(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
    }

    // @PostMapping
    // public ResponseEntity <Admin> Login(@RequestMapping String username, @RequestMapping String password){
    //     var admin = adminRepository.findByUsername(username);
    //     if (admin == null){
    //         return ResponseEntity.notFound().build();
    //     }
    //     if (admin.getPassword().equals(password)){
    //         return ResponseEntity.ok(admin);
    //     } else {
    //         return ResponseEntity.status(401).build();
    // }
    
}
