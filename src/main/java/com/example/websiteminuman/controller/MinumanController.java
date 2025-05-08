package com.example.websiteminuman.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.websiteminuman.entities.Minuman;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MinumanController {
    @GetMapping("/")
    public List<Minuman> getAllMinuman() {
        return null;
    }
    
}
