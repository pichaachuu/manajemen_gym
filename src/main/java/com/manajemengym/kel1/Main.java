package com.manajemengym.kel1;

import com.manajemengym.kel1.util.Koneksi;
import com.manajemengym.kel1.view.FormInstruktur;
import com.manajemengym.kel1.view.FormJadwalKelas;
import com.manajemengym.kel1.view.FormMember;

public class Main {
    public static void main(String[] args) {

        // Test koneksi
        Koneksi.getConnection();

        // Jalankan form yang kamu mau
        new FormMember();
        new FormInstruktur();
        new FormJadwalKelas();


        System.out.println("App is running...");
    }
}
