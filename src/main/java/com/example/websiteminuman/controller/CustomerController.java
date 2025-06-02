package com.example.websiteminuman.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.websiteminuman.dto.CustomerDto;
import com.example.websiteminuman.entities.Cart;
import com.example.websiteminuman.entities.Minuman;
import com.example.websiteminuman.entities.Payment;
import com.example.websiteminuman.mapper.CustomerMapper;
import com.example.websiteminuman.repositories.CartRepository;
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
    private final CartRepository cartRepository;

    public CustomerController(CustomerRepository customerRepository, CustomerMapper customerMapper, MinumanRepository minumanRepository, CustomerAuthService customerService, CartRepository cartRepository) {
        this.cartRepository = cartRepository;
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
            session.setAttribute("customerId", customer.getId());
            
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
    @ResponseBody
    public Map<String, Object> addTocart (@RequestParam Long minumanId , RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Map<String, Object> result = new java.util.HashMap<>();
        String email = (String) request.getSession().getAttribute("customerEmail");
        if (email == null) {
            result.put("success", false);
            result.put("message", "Anda harus login terlebih dahulu");
            result.put("redirect", "/logincust");
            return result;
        }
        var customer = customerRepository.findByEmail(email);
        if (customer == null) {
            result.put("success", false);
            result.put("message", "Customer tidak ditemukan");
            result.put("redirect", "/logincust");
            return result;
        }

        var minuman = minumanRepository.findById(minumanId).orElse(null);
        if (minuman == null){
            result.put("success", false);
            result.put("message", "Minuman tidak ditemukan");
            result.put("redirect", "/menu");
            return result;
        }
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setMinumanId(minumanId);
        cartRepository.save(cart);
        result.put("success", true);
        result.put("message", "Minuman berhasil ditambahkan ke keranjang");
        return result;
    }

    @GetMapping("/cart")
    @ResponseBody
    public List<Map<String, Object>> getCartItems(HttpServletRequest request) {
    Long customerId = (Long) request.getSession().getAttribute("customerId");
    if (customerId == null) {
        throw new RuntimeException("Anda harus login terlebih dahulu.");
    }

    // Ambil semua item cart berdasarkan customerId
    List<Cart> carts = cartRepository.findByCustomerId(customerId);

    // Buat list untuk menyimpan hasil dengan data minuman
    List<Map<String, Object>> result = new ArrayList<>();

    for (Cart cart : carts) {
        // Ambil data minuman berdasarkan minumanId
        Minuman minuman = minumanRepository.findById(cart.getMinumanId()).orElse(null);

        // Jika minuman tidak ditemukan, skip item ini
        if (minuman == null) continue;

        // Gabungkan data cart dan minuman
        Map<String, Object> item = new HashMap<>();
        item.put("id", cart.getId());
        item.put("minuman", minuman); // Masukkan data minuman

        result.add(item);
    }

        return result;
    }

    @DeleteMapping("/cart/{cartId}")
        @ResponseBody
    public Map<String, Object> deleteCartItem(@PathVariable Long cartId, HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            throw new RuntimeException("Anda harus login terlebih dahulu.");
        }

    // Cari item cart berdasarkan ID dan customerId
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Item tidak ditemukan"));

        if (!cart.getCustomerId().equals(customerId)) {
            throw new RuntimeException("Anda tidak memiliki akses untuk menghapus item ini.");
        }

        // Hapus item dari database
        cartRepository.delete(cart);

        // Response sukses
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Item berhasil dihapus dari keranjang");
        return response;
    }

}

