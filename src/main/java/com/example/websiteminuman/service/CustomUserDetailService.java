// package com.example.websiteminuman.service;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.example.websiteminuman.entities.Customer;
// import com.example.websiteminuman.entities.User;
// import com.example.websiteminuman.repositories.AdminRepository;
// import com.example.websiteminuman.repositories.CustomerRepository;

// @Service
// public class CustomUserDetailService implements UserDetailsService {
//     // @Autowired
//     // private AdminRepository adminRepository;
//     // @Autowired
//     // private CustomerRepository customerRepository;

//     // @Override
//     // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     //     // Cek apakah user adalah admin
//     //     Optional<Admin> adminOpt = adminRepository.findByUsername(username);
//     //     if (adminOpt.isPresent()) {
//     //         Admin admin = adminOpt.get();
//     //         return new org.springframework.security.core.userdetails.User(
//     //             admin.getUsername(),
//     //             admin.getPassword(),
//     //             new ArrayList<>() // Kosongkan authority karena tidak pakai role
//     //         );
//     //     }

//     //     // Cek apakah user adalah customer
//     //     Optional<Customer> custOpt = customerReposi.findByUsername(username);
//     //     if (custOpt.isPresent()) {
//     //         Customer customer = custOpt.get();
//     //         return new org.springframework.security.core.userdetails.User(
//     //             customer.getUsername(),
//     //             customer.getPassword(),
//     //             new ArrayList<>()
//     //         );
//     //     }

//         // Kalau tidak ditemukan
//         throw new UsernameNotFoundException("User not found with username: " + username);
//     }

// }
