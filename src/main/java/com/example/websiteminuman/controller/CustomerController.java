package com.example.websiteminuman.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.websiteminuman.dto.CustomerDto;
import com.example.websiteminuman.entities.Cart;
import com.example.websiteminuman.entities.Payment;
import com.example.websiteminuman.mapper.CustomerMapper;
import com.example.websiteminuman.repositories.CustomerRepository;
import com.example.websiteminuman.repositories.MinumanRepository;
import com.example.websiteminuman.service.CustomerAuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth/customer")

public class CustomerController {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final MinumanRepository minumanRepository;
    private final CustomerAuthService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerMapper customerMapper, MinumanRepository minumanRepository, CustomerAuthService customerService) {
        this.minumanRepository = minumanRepository;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    public Iterable<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toDto)
            .toList();
    }
    
    @GetMapping("/sort/{field}")
    public Iterable<CustomerDto> getAllCustomersSorted(@PathVariable String field) {
        return customerRepository.findAll(Sort.by(field))
            .stream()
            .map(customerMapper::toDto)
            .toList();
    }
   

    @PostMapping("/coba/login")
    public String loginCustomer(@RequestParam String email, @RequestParam String password, 
                               RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            // Authenticate user
            CustomerDto customer = customerMapper.toDto(customerService.login2(email, password));
            
            // Simpan informasi customer di session
            HttpSession session = request.getSession();
            session.setAttribute("loggedInCustomer", customer);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("customerName", customer.getUsername());
            session.setAttribute("customerEmail", customer.getEmail());
            
            redirectAttributes.addFlashAttribute("message", "Login berhasil! Selamat datang " + customer.getUsername());
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Login gagal: " + e.getMessage());
            return "redirect:/logincust";
        }
    }

    @PostMapping("/coba/register")
    public String registerCustomer(@RequestParam String username , @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setUsername(username);
            customerDto.setEmail(email);
            customerDto.setPassword(password);
            
            if (customerRepository.existsByEmail(email)) {
                redirectAttributes.addFlashAttribute("error", "Email sudah terdaftar");
                return "redirect:/registerCustomer";
            }
            var customer = customerMapper.toEntity(customerDto);
            customerRepository.save(customer);
            redirectAttributes.addFlashAttribute("message", "Registrasi berhasil, silakan login");
            return "redirect:/logincust";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registrasi gagal: " + e.getMessage());
            return "redirect:/registerCustomer";
            
        }
    }
    @PostMapping("/logout")
    public String logoutCustomer(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Hapus semua data session
            session.removeAttribute("loggedInCustomer");
            session.removeAttribute("isLoggedIn");
            session.removeAttribute("customerName");
            session.removeAttribute("customerEmail");
            session.invalidate(); // Hapus session sepenuhnya
        }
        
        redirectAttributes.addFlashAttribute("message", "Anda telah berhasil logout");
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logoutCustomerGet(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return logoutCustomer(request, redirectAttributes);
    }
    @PostMapping("/coba/add")
    public String addTocart (@RequestParam Long minumanId , RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("loggedIncustomer");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Anda harus login terlebih dahulu");
            return "redirect:/logincust";
        }
        var customer = customerRepository.findByEmail(email);
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Customer tidak ditemukan");
            return "redirect:/logincust";
        }

        var minuman = minumanRepository.findById(minumanId).orElse(null);
        if (minuman == null){
            redirectAttributes.addFlashAttribute("error", "Minuman tidak ditemukan");
            return "redirect:/logincust";
        }

        Cart cart = new Cart();
        cart.setCustomerEmail(email);
        minuman = minumanRepository.findById(minumanId).orElse(null);
        cart.setMinuman(minuman);

        return "redirect:/menu";
    }

}
