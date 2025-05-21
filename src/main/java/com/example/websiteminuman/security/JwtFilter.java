// package com.example.websiteminuman.security;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.example.websiteminuman.entities.Admin;
// import com.example.websiteminuman.entities.Customer;
// import com.example.websiteminuman.repositories.AdminRepository;
// import com.example.websiteminuman.repositories.CustomerRepository;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     @Autowired
//     private JwtUtil jwtUtil;

//     @Autowired
//     private AdminRepository adminRepo;

//     @Autowired
//     private CustomerRepository customerRepo;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//                                     throws ServletException, IOException {
//         String header = request.getHeader("Authorization");
//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);
//             String username = jwtUtil.extractUsername(token);

//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

//                 // Coba cari di admin atau customer
//                 Optional<Admin> adminOpt = adminRepo.findByUsername(username);
//                 Optional<Customer> custOpt = customerRepo.findByEmail(username);

//                 if (adminOpt.isPresent() || custOpt.isPresent()) {
//                     UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                         username, "", new ArrayList<>()
//                     );

//                     if (jwtUtil.validateToken(token)) {
//                         UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                             userDetails, null, userDetails.getAuthorities());

//                         auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                         SecurityContextHolder.getContext().setAuthentication(auth);
//                     }
//                 }
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }

