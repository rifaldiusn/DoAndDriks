package com.example.websiteminuman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.websiteminuman.dto.AdminDto;
import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.repositories.AdminRepository;
// import com.example.websiteminuman.security.JwtUtil;

@Service
public class AdminAuthService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Autowired
    // private JwtUtil jwtUtil;

    public Admin login(AdminDto dto) {
        Admin admin = adminRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username Tidak Ditemukan"));

        String currentPassword = admin.getPassword();

        // Cek jika password sudah di-hash
        if (!currentPassword.startsWith("$2a$") && !currentPassword.startsWith("$2b$") && !currentPassword.startsWith("$2y$")) {
            String hashedPassword = passwordEncoder.encode(currentPassword);
            admin.setPassword(hashedPassword);
            adminRepo.save(admin);
        }

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
             throw new BadCredentialsException("Invalid credentials");
        }

        // String token = jwtUtil.generateToken(admin.getUsername());
        return admin;
    }

    public Admin getAdminById(Long id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Admin Tidak Ditemukan"));
    }

}

