package com.example.websiteminuman.entities;

import java.util.ArrayList;

public class HistoryAll {
    protected ArrayList<Minuman> H = new ArrayList<>();
    protected ArrayList<Payment> P = new ArrayList<>();

    public HistoryAll() {}

    public String DisplayHistoryAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("Riwayat Pemesanan:\n");
        for (Minuman m : H) {
            sb.append("- ").append(m.getNama()).append(", Rp").append(m.getHarga()).append("\n");
        }
        for (Payment p : P) {
            sb.append("Metode Pembayaran: ").append(p.getMetodePembayaran()).append("\n");
        }
        return sb.toString();
    }
}
