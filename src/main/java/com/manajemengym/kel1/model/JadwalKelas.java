package com.manajemengym.kel1.model;

public class JadwalKelas {
    private int id_kelas;
    private String nama_kelas;
    private String hari;
    private String jam;
    private int id_instruktur;

    public int getId_kelas() { 
        return id_kelas; 
    }
    public void setId_kelas(int id_kelas) { 
        this.id_kelas = id_kelas; 
    }

    public String getNama_kelas() { 
        return nama_kelas; 
    }
    public void setNama_kelas(String nama_kelas) { 
        this.nama_kelas = nama_kelas;
    }

    public String getHari() { 
        return hari; 
    }
    public void setHari(String hari) { 
        this.hari = hari; 
    }

    public String getJam() { 
        return jam; }

    public void setJam(String jam) { 
        this.jam = jam; 
    }
    public int getId_instruktur() { 
        return id_instruktur; 
    }
    public void setId_instruktur(int id_instruktur) { 
        this.id_instruktur = id_instruktur; 
    }
}
