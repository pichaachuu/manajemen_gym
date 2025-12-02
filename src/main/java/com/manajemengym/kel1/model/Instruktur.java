package com.manajemengym.kel1.model;

public class Instruktur {
    private int id_instruktur;
    private String nama;
    private int usia;
    private String keahlian;
    private String telepon;

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
        this.nama = nama;
    }

    public int getUsia() {
        return usia;
    }
    public void setUsia(int usia) {
        this.usia = usia;
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
    public void setTelepon(String telpon) {
        this.telepon = telpon;
    }
}
