package com.example.websiteminuman.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Minuman {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String nama;
    private String jenis;
    private String deskripsi;
    private int harga;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin adminId;
    
    public Minuman(Long id, String nama, String jenis, String deskripsi, int harga) {
        this.id = id;
        this.jenis = jenis;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
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

    public Long getAdminId() {
        return adminId.getId();
    }

    public void setAdmin(Admin admin) {
        this.adminId = admin;
    }
}
