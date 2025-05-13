package com.example.websiteminuman.dto;

// This class is a Data Transfer Object (DTO) for User.
// It is used to transfer data between the application and the client.
// It contains fields for username, id, and hashPassword.
// The class is annotated with @Data, which generates getters, setters, equals, hashCode, and toString methods.
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminDto {
    private Long id;
    private String username;
}
