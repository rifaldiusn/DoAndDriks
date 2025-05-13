package com.example.websiteminuman.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterCustomerDto {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
}
