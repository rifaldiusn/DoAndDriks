package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.repositories.MinumanRepository;

import java.util.List;


@RestController
public class MinumanController {
    private final MinumanRepository minumanRepository;
    public List<Minuman> getAllMinuman() {
        return null;
    }
    
}
