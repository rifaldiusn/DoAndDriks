package com.example.websiteminuman.mapper;

import org.mapstruct.Mapper;

import com.example.websiteminuman.dto.AuthResponseDto;
import com.example.websiteminuman.dto.CustomerDto;
import com.example.websiteminuman.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto (Customer customer);
    Customer toEntity (CustomerDto customerDto);

   AuthResponseDto toAuthResponse(Customer customer);
}