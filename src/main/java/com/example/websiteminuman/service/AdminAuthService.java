// package com.example.websiteminuman.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.example.websiteminuman.dto.AdminDto;
// import com.example.websiteminuman.dto.AuthResponseDto;
// import com.example.websiteminuman.dto.DelUpMinuman;
// import com.example.websiteminuman.entities.Admin;
// import com.example.websiteminuman.repositories.AdminRepository;
// import com.example.websiteminuman.repositories.MinumanRepository;
// // import com.example.websiteminuman.security.JwtUtil;

// @Service
// public class AdminAuthService {

//     @Autowired
//     private AdminRepository adminRepo;
//     private MinumanRepository minumanRepo;

//     // @Autowired
//     // private PasswordEncoder passwordEncoder;

//     // @Autowired
//     // private JwtUtil jwtUtil;

//     public Admin login(AdminDto dto) {
//         Admin admin = adminRepo.findByUsername(dto.getUsername())
//                 .orElseThrow(() -> new UsernameNotFoundException("Username Tidak Ditemukan"));

//         // if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
//         //     throw new BadCredentialsException("Invalid credentials");
//         // }

//         // String token = jwtUtil.generateToken(admin.getUsername());
//         return admin;
//     }

//     public DelUpMinuman delUpMinuman(Long id) {
//         var minuman = minumanRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Minuman Tidak Ditemukan"));
//         return new DelUpMinuman(minuman.getId());
//     }
// }

