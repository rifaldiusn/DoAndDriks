package com.example.websiteminuman.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.websiteminuman.entities.Admin;
import com.example.websiteminuman.repositories.AdminRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().permitAll()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        return http.build();
    }

    @Bean
    public UserDetailsService adminDetails(AdminRepository adminRepo) {
        return username -> {
            Admin admin = adminRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin tidak ditemukan"));
            return new org.springframework.security.core.userdetails.User(
                admin.getUsername(), admin.getPassword(), Collections.emptyList()
            );
        };
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
