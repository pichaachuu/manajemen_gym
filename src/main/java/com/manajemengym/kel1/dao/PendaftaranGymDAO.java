package com.manajemengym.kel1.dao;

import com.manajemengym.kel1.model.PendaftaranGym;
import com.manajemengym.kel1.util.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranGymDAO {

    public void insert(PendaftaranGym p) throws Exception {
        String sql = "INSERT INTO pendaftaran_gym (member, kelas_gym, tanggal_daftar, catatan) VALUES (?, ?, ?, ?)";
        Connection conn = Koneksi.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, p.getMember());
        ps.setString(2, p.getKelasGym());
        ps.setString(3, p.getTanggalDaftar());
        ps.setString(4, p.getCatatan());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<PendaftaranGym> getAll() throws Exception {
        List<PendaftaranGym> list = new ArrayList<>();

        String sql = "SELECT * FROM pendaftaran_gym";
        Connection conn = Koneksi.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            PendaftaranGym p = new PendaftaranGym();
            p.setIdPendaftaran(rs.getInt("id_pendaftaran"));
            p.setMember(rs.getString("member"));
            p.setKelasGym(rs.getString("kelas_gym"));
            p.setTanggalDaftar(rs.getString("tanggal_daftar"));
            p.setCatatan(rs.getString("catatan"));
            list.add(p);
        }

        rs.close();
        st.close();
        conn.close();
        return list;
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM pendaftaran_gym WHERE id_pendaftaran = ?";
        Connection conn = Koneksi.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}