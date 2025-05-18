package com.example.websiteminuman.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Minuman {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nama;
    private String jenis;
    private String ukuran;
    private int harga;
    
    public Minuman(Long id, String nama, String jenis, String ukuran, int harga) {
        this.id = id;
        this.jenis = jenis;
        this.nama = nama;
        this.harga = harga;
        this.ukuran = ukuran;
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

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
}
