package com.manajemengym.kel1.model;

public class Member {

    private int idMember;
    private String nama;
    private String gender;
    private int usia;
    private String telepon;
    private String alamat;

    public Member() {
    }

    public Member(int idMember, String nama, String gender, int usia, String telepon, String alamat) {
        setIdMember(idMember);
        setNama(nama);
        setGender(gender);
        setUsia(usia);
        setTelepon(telepon);
        setAlamat(alamat);
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        if (idMember < 0) {
            throw new IllegalArgumentException("ID Member tidak boleh negatif.");
        }
        this.idMember = idMember;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender harus dipilih.");
        }
        this.gender = gender;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        if (usia < 12) {
            throw new IllegalArgumentException("Usia minimal untuk member gym adalah 12 tahun.");
        }
        if (usia > 65) {
            throw new IllegalArgumentException("Usia maksimal adalah 65 tahun.");
        }
        this.usia = usia;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat tidak boleh kosong.");
        }
        this.alamat = alamat;
    }

}
