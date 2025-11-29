package com.manajemengym.kel1.model;

public class Member {

    private int idMember;
    private String nama;
    private String gender;
    private int usia;
    private String telepon;
    private String alamat;
    // Constructor kosong
    public Member() {
    }

    // Constructor lengkap
    public Member(int idMember, String nama, String gender, int usia, String telepon, String alamat) {
        this.idMember = idMember;
        this.nama = nama;
        this.gender = gender;
        this.usia = usia;
        this.telepon = telepon;
        this.alamat = alamat;
    }

    // Getter & Setter
    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}
