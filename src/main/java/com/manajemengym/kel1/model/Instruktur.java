package com.manajemengym.kel1.model;

public class Instruktur {
    private int id_instruktur;
    private String nama;
    private int usia;
    private String keahlian;
    private String telepon;

    public Instruktur(){

    }

    public Instruktur(int id_instruktur, String nama, int usia, String keahlian, String telepon){
        setId_instruktur(id_instruktur);
        setNama(nama);
        setUsia(usia);
        setKeahlian(keahlian);
        setTelepon(telepon);
    }

    // Getter & Setter
    public int getId_instruktur() {
        return id_instruktur;
    }
    public void setId_instruktur(int id_instruktur) {
        this.id_instruktur = id_instruktur;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        if (!nama.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama hanya boleh berisi huruf dan spasi.");
        }
        this.nama = nama.trim();
    }

    public int getUsia() {
        return usia;
    }
    public void setUsia(int usia) {
        if (usia < 12) {
            System.out.println("⚠️ Usia instruktur terlalu kecil, otomatis diset menjadi 12.");
            this.usia = 12;
        } else {
            this.usia = usia;
        }
    }

    public String getKeahlian() {
        return keahlian;
    }
    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public String getTelepon() {
        return telepon;
    }
    public void setTelepon(String telepon) {
        if (telepon == null || telepon.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor telepon tidak boleh kosong.");
        }
        if (!telepon.matches("\\+?\\d{10,13}")) {
            throw new IllegalArgumentException("Format nomor telepon tidak valid.");
        }
        this.telepon = telepon;
    }
}
