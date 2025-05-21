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

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

}
