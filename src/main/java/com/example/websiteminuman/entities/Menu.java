package com.example.websiteminuman.entities;

import java.util.ArrayList;
public class Menu {
    protected ArrayList<Minuman> M;

    public Menu() {
        M = new ArrayList<Minuman>();
    }

    public void DisplayMenu(){
        System.out.println("Daftar Menu Minuman:");
        for (int i = 0; i < M.size(); i++) {
            System.out.println((i + 1) + ". " + M.get(i).getNama() + " - " + M.get(i).getJenis() + " - " + M.get(i).getUkuran() + " - " + M.get(i).getHarga());
        }
    }
}
