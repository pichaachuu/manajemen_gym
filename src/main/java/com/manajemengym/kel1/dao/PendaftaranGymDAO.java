package com.manajemengym.kel1.dao;

import com.manajemengym.kel1.model.PendaftaranGym;
import com.manajemengym.kel1.util.Koneksi;
import com.manajemengym.kel1.model.ComboItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranGymDAO {

    public void insert(PendaftaranGym p) {
<<<<<<< HEAD
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
=======
        String sql = "INSERT INTO pendaftaran (id_member, id_kelas, tanggal_daftar, catatan) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId_member());
            ps.setInt(2, p.getId_kelas());
            ps.setTimestamp(3, p.getTanggalDaftar());
            ps.setString(4, p.getCatatan());

            ps.executeUpdate();
            System.out.println("Member berhasil ditambahkan.");

        } catch (SQLException e) {
            e.printStackTrace();
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
        }
    }

    public void update(PendaftaranGym p) {
<<<<<<< HEAD
        String sql = "UPDATE pendaftaran_gym SET member=?, kelas_gym=?, tanggal_daftar=?, catatan=? WHERE id_pendaftaran=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getMember());
            ps.setString(2, p.getKelasGym());
            ps.setDate(3, Date.valueOf(p.getTanggalDaftar()));
=======
        String sql = "UPDATE pendaftaran SET id_member = ?, id_kelas = ?, tanggal_daftar = ?, catatan = ? WHERE id_pendaftaran = ?";

        try (Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId_member());
            ps.setInt(2, p.getId_kelas());
            ps.setTimestamp(3, p.getTanggalDaftar());
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
            ps.setString(4, p.getCatatan());
            ps.setInt(5, p.getIdPendaftaran());

            ps.executeUpdate();
<<<<<<< HEAD

        } catch (Exception e) {
            throw new RuntimeException("Gagal update data: " + e.getMessage());
=======
            System.out.println("Member berhasil ditambahkan.");

        } catch (SQLException e) {
            e.printStackTrace();
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
        }
    }

    public void delete(int id) {
<<<<<<< HEAD
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
=======
        String sql = "DELETE FROM pendaftaran WHERE id_pendaftaran=?";

        try (PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET ALL
    public List<PendaftaranGym> getAll() {
        List<PendaftaranGym> list = new ArrayList<>();

        String sql = "SELECT * FROM pendaftaran";

        try (Statement st = Koneksi.getConnection().createStatement();
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                PendaftaranGym p = new PendaftaranGym();
                p.setIdPendaftaran(rs.getInt("id_pendaftaran"));
<<<<<<< HEAD
                p.setMember(rs.getString("member"));
                p.setKelasGym(rs.getString("kelas_gym"));
                p.setTanggalDaftar(rs.getDate("tanggal_daftar").toString());
                p.setCatatan(rs.getString("catatan"));
                list.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Gagal load data: " + e.getMessage());
=======
                p.setId_member(rs.getInt("id_member"));
                p.setId_kelas(rs.getInt("id_kelas"));
                p.setTanggalDaftar(rs.getTimestamp("tanggal_daftar"));
                p.setCatatan(rs.getString("catatan"));

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
        }

        return list;
    }
<<<<<<< HEAD
}
=======


    public List<ComboItem> getKelasCombo() {
        List<ComboItem> list = new ArrayList<>();

        String sql = "SELECT id_kelas, nama_kelas FROM jadwal_kelas";

        try (Statement st = Koneksi.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new ComboItem(
                        rs.getString("nama_kelas"),
                        rs.getInt("id_kelas")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ComboItem> getMemberCombo() {
        List<ComboItem> list = new ArrayList<>();

        String sql = "SELECT id_member, nama FROM member_gym";

        try (Statement st = Koneksi.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new ComboItem(
                        rs.getString("nama"),
                        rs.getInt("id_member")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
