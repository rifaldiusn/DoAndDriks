// package com.example.websiteminuman.controller;

// import org.springframework.web.bind.annotation.RestController;

// import com.example.websiteminuman.entities.Minuman;
// import com.example.websiteminuman.repositories.MinumanRepository;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;


// @RestController
// @RequestMapping("/minuman")
// public class MinumanController {
//     private final MinumanRepository minumanRepository;

//     public MinumanController(MinumanRepository minumanRepository) {
//         this.minumanRepository = minumanRepository;
//     }
    
//     @GetMapping()
//     public Iterable<Minuman> getAllMinuman() {
//         return minumanRepository.findAll();
//     }
    
// }
