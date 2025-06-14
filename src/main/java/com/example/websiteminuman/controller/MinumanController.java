package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.dto.MinumanDto;
import com.example.websiteminuman.mapper.MinumanMapper;
import com.example.websiteminuman.repositories.MinumanRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/minuman")
public class MinumanController {
    private final MinumanRepository minumanRepository;
    private final MinumanMapper minumanMapper;

    public MinumanController(MinumanRepository minumanRepository, MinumanMapper minumanMapper) {
        this.minumanRepository = minumanRepository;
        this.minumanMapper = minumanMapper;
    }
    
    @GetMapping("/menu")
    public Iterable<MinumanDto> getAllMinuman() {
        List<MinumanDto> list = minumanRepository.findAll()
        .stream()
        .map(minumanMapper::toDto)
        .toList();
        return list;
    }
    
    @GetMapping("/search")
    public @ResponseBody Iterable<MinumanDto> searchMinuman(@RequestParam String keyword) {
        List<MinumanDto> list = minumanRepository.findByNamaContainingIgnoreCase(keyword)
        .stream()
        .map(minumanMapper::toDto)
        .toList();
        return list;
    }
}
