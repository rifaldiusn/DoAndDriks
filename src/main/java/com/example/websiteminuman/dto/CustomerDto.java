package com.example.websiteminuman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CustomerDto {
    private String email;
    private String password;
}