package com.example.websiteminuman.entities;

import java.util.ArrayList;
import java.util.Date;

public class History {
    private Date tanggal;
    private int nominal;
    protected ArrayList<Minuman> M = new ArrayList<>();

    public History(Date tanggal, int nominal) {
        this.tanggal = tanggal;
        this.nominal = nominal;
    }

    public String DisplayHistory() {
        return "Tanggal: " + tanggal + ", Total: Rp" + nominal;
    }

    public String getHistory() {
        return DisplayHistory();
    }

    public String getNominal() {
        return String.valueOf(nominal);
    }

    public void setNominal(String nominal) {
        this.nominal = Integer.parseInt(nominal);
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
