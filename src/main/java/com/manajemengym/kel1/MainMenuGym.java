package com.manajemengym.kel1;

import javax.swing.*;
import java.awt.*;

import com.manajemengym.kel1.view.FormMember;
import com.manajemengym.kel1.view.FormInstruktur;
import com.manajemengym.kel1.view.FormJadwalKelas;
import com.manajemengym.kel1.view.FormPendaftaranGym;

public class MainMenuGym {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Sistem Manajemen Gym");
            frame.setSize(600, 500);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(new Color(240, 240, 245));

            JLabel lblTitle = new JLabel("SISTEM MANAJEMEN GYM", SwingConstants.CENTER);
            lblTitle.setBounds(0, 30, 600, 40);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
            frame.add(lblTitle);

            int btnWidth = 400;
            int btnHeight = 45;
            int startX = (600 - btnWidth) / 2;
            int startY = 130;
            int spacing = 60;

            JButton btnMember = new JButton("Registrasi Member Gym");
            btnMember.setBounds(startX, startY, btnWidth, btnHeight);
            frame.add(btnMember);

            JButton btnInstruktur = new JButton("Kelola Data Instruktur");
            btnInstruktur.setBounds(startX, startY + spacing, btnWidth, btnHeight);
            frame.add(btnInstruktur);

            JButton btnJadwal = new JButton("Kelola Jadwal Kelas");
            btnJadwal.setBounds(startX, startY + spacing * 2, btnWidth, btnHeight);
            frame.add(btnJadwal);

            JButton btnDaftar = new JButton("Pendaftaran Kelas Member");
            btnDaftar.setBounds(startX, startY + spacing * 3, btnWidth, btnHeight);
            frame.add(btnDaftar);

            JButton btnKeluar = new JButton("Keluar");
            btnKeluar.setBounds(startX, startY + spacing * 4, btnWidth, btnHeight);
            frame.add(btnKeluar);

            btnMember.addActionListener(e ->
                new FormMember().setVisible(true)
            );

            btnInstruktur.addActionListener(e ->
                new FormInstruktur().setVisible(true)
            );

            btnJadwal.addActionListener(e ->
                new FormJadwalKelas().setVisible(true)
            );

            btnDaftar.addActionListener(e ->
                new FormPendaftaranGym().setVisible(true)
            );

            btnKeluar.addActionListener(e -> System.exit(0));

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
