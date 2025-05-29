package com.example.websiteminuman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MinumanDto {
    private Long id;
    private String nama;
    private String jenis;
    private String deskripsi;
    private int harga;

    
    public MinumanDto() {
        // Default constructor
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}