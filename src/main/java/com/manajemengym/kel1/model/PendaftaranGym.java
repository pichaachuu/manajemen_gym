package com.manajemengym.kel1.model;

import java.sql.Timestamp;

public class PendaftaranGym {
    private int idPendaftaran;
    private int id_member;
    private int id_kelas;
    private Timestamp tanggalDaftar;
    private String catatan;


    public int getIdPendaftaran() { 
        return idPendaftaran; 
    }

    public void setIdPendaftaran(int idPendaftaran) { 
        this.idPendaftaran = idPendaftaran; 
    }

    public int getId_kelas() { 
        return id_kelas; 
    }

    public void setId_kelas(int id_kelas) { 
        this.id_kelas = id_kelas; 
    }

    public int getId_member() { 
        return id_member; 
    }

    public void setId_member(int id_member) { 
        this.id_member = id_member; 
    }
    public Timestamp getTanggalDaftar() { 
        return tanggalDaftar; 
    }

    public void setTanggalDaftar(Timestamp tanggalDaftar) { 
        this.tanggalDaftar = tanggalDaftar; 
    }

    public String getCatatan() { 
        return catatan; 
    }

    public void setCatatan(String catatan) { 
        this.catatan = catatan; 
    }

}
