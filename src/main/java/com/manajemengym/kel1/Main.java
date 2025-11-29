package com.manajemengym.kel1;

import com.manajemengym.kel1.util.Koneksi;
import com.manajemengym.kel1.view.FormMember;


public class Main {
    public static void main(String[] args) {
        // Coba koneksi
        Koneksi.getConnection();

        new FormMember();

        // Nanti di sini kamu bisa buka form pertama
        System.out.println("App is running...");
    }
}
