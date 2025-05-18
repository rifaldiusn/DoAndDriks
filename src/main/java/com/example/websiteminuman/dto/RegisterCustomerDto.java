package com.example.websiteminuman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterCustomerDto {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
}
