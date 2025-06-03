package com.example.websiteminuman.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Minuman {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String nama;
    private String jenis;
    private String deskripsi;
    private String gambar;
    private int harga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId", nullable = false)
    private Admin adminId;
    
    public Minuman(Long id, String nama, String jenis, String deskripsi, String gambar, int harga, Admin admin) {
        this.adminId = admin;   
        this.id = id;
        this.jenis = jenis;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    public Minuman() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
    
    public Long getAdminId() {
        return adminId.getId();
    }

    public void setAdmin(Admin admin) {
        this.adminId = admin;
    }
}
