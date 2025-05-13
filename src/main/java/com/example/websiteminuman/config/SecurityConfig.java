package com.example.websiteminuman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.GET).permitAll() // Allow access to authentication endpoints
                .anyRequest().authenticated()
                );

        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails admin = User.builder()
    //         .username("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         .roles("ADMIN")
    //         .build();
    //     UserDetails customer = User.builder()
    //         .username("customer")
    //         .password(passwordEncoder().encode("customer"))
    //         .roles("CUSTOMER")
    //         .build();

    //     return new InMemoryUserDetailsManager(admin, customer);
    // }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
