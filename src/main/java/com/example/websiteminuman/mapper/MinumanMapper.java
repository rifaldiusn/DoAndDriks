package com.example.websiteminuman.mapper;

import org.mapstruct.Mapper;
import com.example.websiteminuman.dto.MinumanDto;
import com.example.websiteminuman.entities.Minuman;

@Mapper(componentModel = "spring")
public interface MinumanMapper {
    MinumanDto toDto(Minuman minuman);
    Minuman toEntity(MinumanDto minumanDto);
}
