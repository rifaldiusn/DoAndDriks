package com.example.websiteminuman.dto;

import java.time.LocalDate;

public class LaporanPenjualanDto {
    private LocalDate tanggal;
    private int total;

    public LaporanPenjualanDto(LocalDate tanggal, int total){
        this.tanggal = tanggal;
        this.total = total;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }
    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
