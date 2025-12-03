package com.manajemengym.kel1.model;

public class PendaftaranGym {
    private int idPendaftaran;
    private String member;
    private String kelasGym;
    private String tanggalDaftar;
    private String catatan;

    public int getIdPendaftaran() { 
        return idPendaftaran; 
    }

    public void setIdPendaftaran(int idPendaftaran) { 
        this.idPendaftaran = idPendaftaran; 
    }

    public String getMember() { 
        return member; 
    }

    public void setMember(String member) { 
        this.member = member; 
    }

    public String getKelasGym() { 
        return kelasGym; 
    }

    public void setKelasGym(String kelasGym) { 
        this.kelasGym = kelasGym; 
    }

    public String getTanggalDaftar() { 
        return tanggalDaftar; 
    }

    public void setTanggalDaftar(String tanggalDaftar) { 
        this.tanggalDaftar = tanggalDaftar; 
    }

    public String getCatatan() { 
        return catatan; 
    }

    public void setCatatan(String catatan) { 
        this.catatan = catatan; 
    }
}
