package com.example.websiteminuman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homepageController {

    @GetMapping("/")
    public String showHome() {
        return "homepage";
    }
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
    @GetMapping("/menu")
    public String showMenu() {
        return "menu";
    }
    @GetMapping("/contact")
    public String showContact() {
        return "contact";
    }
    @GetMapping("/collaboration")
    public String showCollaboration() {
        return "collaboration";
    }
    @GetMapping("/download")
    public String showDownload() {
        return "download";
    }
    
    @GetMapping("/dashboardAdmin")
    public String showDashboard() {
        return "dashboardAdmin";
    }

    @GetMapping("/loginAdmin")
    public String showLoginAdmin() {
        return "loginAdmin";
    }
     @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // dashboard.html
    }

    @GetMapping("/tambah")
    public String tambahMinuman() {
        return "tambah-minuman"; // tambah-minuman.html
    }

    @GetMapping("/laporan")
    public String laporanPesanan() {
        return "laporan"; // laporan.html
    }

    @GetMapping("/kelola")
    public String kelolaMenu() {
        return "kelola-menu"; // kelola-menu.html
    }
    @GetMapping("/logincust")
    public String login() {
        return "logincust"; // 
    }
}
