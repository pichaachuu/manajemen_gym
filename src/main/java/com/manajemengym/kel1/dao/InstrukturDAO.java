package com.manajemengym.kel1.dao;

import java.sql.*;
import java.util.*;
import com.manajemengym.kel1.model.Instruktur;
import com.manajemengym.kel1.util.Koneksi;

public class InstrukturDAO {

    // INSERT
    public void insert(Instruktur ins) {
        String sql = "INSERT INTO instruktur(nama, usia, keahlian, telepon) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ins.getNama());
            ps.setInt(2, ins.getUsia());
            ps.setString(3, ins.getKeahlian());
            ps.setString(4, ins.getTelepon());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(Instruktur ins) {
        String sql = "UPDATE instruktur SET nama=?, usia=?, keahlian=?, telepon=? WHERE id_instruktur=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ins.getNama());
            ps.setInt(2, ins.getUsia());
            ps.setString(3, ins.getKeahlian());
            ps.setString(4, ins.getTelepon());
            ps.setInt(5, ins.getId_instruktur());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM instruktur WHERE id_instruktur=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT ALL
    public List<Instruktur> getAll() {
        List<Instruktur> list = new ArrayList<>();
        String sql = "SELECT * FROM instruktur";

        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Instruktur ins = new Instruktur();
                ins.setId_instruktur(rs.getInt("id_instruktur"));
                ins.setNama(rs.getString("nama"));
                ins.setUsia(rs.getInt("usia"));
                ins.setKeahlian(rs.getString("keahlian"));
                ins.setTelepon(rs.getString("telepon"));

                list.add(ins);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
