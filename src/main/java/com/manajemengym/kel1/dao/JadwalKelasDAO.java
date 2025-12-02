package com.manajemengym.kel1.dao;

import com.manajemengym.kel1.model.JadwalKelas;
import com.manajemengym.kel1.util.Koneksi;
import com.manajemengym.kel1.model.ComboItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JadwalKelasDAO {

    // INSERT
    public void insert(JadwalKelas j) {
        String sql = "INSERT INTO jadwal_kelas (nama_kelas, hari, jam, id_instruktur) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql)) {
            ps.setString(1, j.getNama_kelas());
            ps.setString(2, j.getHari());
            ps.setString(3, j.getJam());
            ps.setInt(4, j.getId_instruktur());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(JadwalKelas j) {
        String sql = "UPDATE jadwal_kelas SET nama_kelas=?, hari=?, jam=?, id_instruktur=? WHERE id_kelas=?";

        try (PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql)) {
            ps.setString(1, j.getNama_kelas());
            ps.setString(2, j.getHari());
            ps.setString(3, j.getJam());
            ps.setInt(4, j.getId_instruktur());
            ps.setInt(5, j.getId_kelas());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM jadwal_kelas WHERE id_kelas=?";

        try (PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET ALL
    public List<JadwalKelas> getAll() {
        List<JadwalKelas> list = new ArrayList<>();

        String sql = "SELECT * FROM jadwal_kelas";

        try (Statement st = Koneksi.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                JadwalKelas j = new JadwalKelas();
                j.setId_kelas(rs.getInt("id_kelas"));
                j.setNama_kelas(rs.getString("nama_kelas"));
                j.setHari(rs.getString("hari"));
                j.setJam(rs.getString("jam"));
                j.setId_instruktur(rs.getInt("id_instruktur"));

                list.add(j);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // LOAD COMBOBOX INSTRUKTUR
    public List<ComboItem> getInstrukturCombo() {
        List<ComboItem> list = new ArrayList<>();

        String sql = "SELECT id_instruktur, nama FROM instruktur";

        try (Statement st = Koneksi.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new ComboItem(
                        rs.getString("nama"),
                        rs.getInt("id_instruktur")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
