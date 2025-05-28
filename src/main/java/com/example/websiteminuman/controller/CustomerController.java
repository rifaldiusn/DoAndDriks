package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.dto.CustomerDto;
import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.entities.Customer;
import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.mapper.CustomerMapper;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.websiteminuman.repositories.CustomerRepository;
import com.example.websiteminuman.repositories.MinumanRepository;
import com.example.websiteminuman.service.CustomerAuthService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth/customer")

public class CustomerController {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final MinumanRepository minumanRepository;
    private final CustomerAuthService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerMapper customerMapper, MinumanRepository minumanRepository, CustomerAuthService customerService) {
        this.minumanRepository = minumanRepository;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    public Iterable<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }
    
    @GetMapping("/sort/{field}")
    public Iterable<CustomerDto> getAllCustomersSorted(@PathVariable String field) {
        return customerRepository.findAll(Sort.by(field))
            .stream()
            .map(customerMapper::toDto)
            .toList();
    }

    @PostMapping("/coba/login")
    public ResponseEntity<Customer> loginCustomer(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        try{
            var customer = customerService.login2(email, password);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/coba/register")
    public ResponseEntity<?> registerCustomer(@RequestParam String username , @RequestParam String email, @RequestParam String password) {
        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setUsername(username);
            customerDto.setEmail(email);
            customerDto.setPassword(password);
            
            if (customerRepository.existsByEmail(email)) {
                return ResponseEntity.status(400).body("Email already exists");
            }
            var customer = customerMapper.toEntity(customerDto);
            var savedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok(customerMapper.toAuthResponse(savedCustomer));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Registration failed" + e.getMessage());
        }
    }
}
