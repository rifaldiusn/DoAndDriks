package com.example.websiteminuman.mapper;

import org.mapstruct.Mapper;

import com.example.websiteminuman.dto.AdminDto;
import com.example.websiteminuman.entities.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDto toDto(Admin admin);
    Admin toEntity (AdminDto adminDto);
}
