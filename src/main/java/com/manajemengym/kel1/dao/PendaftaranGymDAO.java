package com.manajemengym.kel1.dao;

import com.manajemengym.kel1.model.PendaftaranGym;
import com.manajemengym.kel1.util.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranGymDAO {

    public void insert(PendaftaranGym p) {
        String sql = "INSERT INTO pendaftaran_gym (member, kelas_gym, tanggal_daftar, catatan) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMember());
            ps.setString(2, p.getKelasGym());
            ps.setDate(3, Date.valueOf(p.getTanggalDaftar()));
            ps.setString(4, p.getCatatan());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Gagal insert data: " + e.getMessage());
        }
    }

    public void update(PendaftaranGym p) {
        String sql = "UPDATE pendaftaran_gym SET member=?, kelas_gym=?, tanggal_daftar=?, catatan=? WHERE id_pendaftaran=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMember());
            ps.setString(2, p.getKelasGym());
            ps.setDate(3, Date.valueOf(p.getTanggalDaftar()));
            ps.setString(4, p.getCatatan());
            ps.setInt(5, p.getIdPendaftaran());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Gagal update data: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM pendaftaran_gym WHERE id_pendaftaran=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Gagal hapus data: " + e.getMessage());
        }
    }

    public List<PendaftaranGym> getAll() {
        List<PendaftaranGym> list = new ArrayList<>();
        String sql = "SELECT * FROM pendaftaran_gym";

        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PendaftaranGym p = new PendaftaranGym();
                p.setIdPendaftaran(rs.getInt("id_pendaftaran"));
                p.setMember(rs.getString("member"));
                p.setKelasGym(rs.getString("kelas_gym"));
                p.setTanggalDaftar(rs.getDate("tanggal_daftar").toString());
                p.setCatatan(rs.getString("catatan"));
                list.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Gagal load data: " + e.getMessage());
        }

        return list;
    }
}
