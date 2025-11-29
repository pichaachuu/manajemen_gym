package com.manajemengym.kel1.dao;

import com.manajemengym.kel1.model.Member;
import com.manajemengym.kel1.util.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    // CREATE (Insert)
    public void insert(Member m) {
        String sql = "INSERT INTO member_gym (nama, gender, usia, telepon, alamat) VALUES (?, ?, ?, ?, ?)";;

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNama());
            ps.setString(2, m.getGender());
            ps.setInt(3, m.getUsia());
            ps.setString(4, m.getTelepon());
            ps.setString(5, m.getAlamat());

            ps.executeUpdate();
            System.out.println("Member berhasil ditambahkan.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (Select)
    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member_gym ORDER BY id_member DESC";

        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Member m = new Member();
                m.setIdMember(rs.getInt("id_member"));
                m.setNama(rs.getString("nama"));
                m.setGender(rs.getString("gender"));
                m.setUsia(rs.getInt("usia"));
                m.setTelepon(rs.getString("telepon"));
                m.setAlamat(rs.getString("alamat"));
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public void update(Member m) {
        String sql = "UPDATE member_gym SET nama=?, gender=?, usia=?, telepon=?, alamat=? WHERE id_member=?";

        try (Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNama());
            ps.setString(2, m.getGender());
            ps.setInt(3, m.getUsia());
            ps.setString(4, m.getTelepon());
            ps.setString(5, m.getAlamat());
            ps.setInt(6, m.getIdMember());

            ps.executeUpdate();
            System.out.println("Member berhasil diupdate.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int idMember) {
        String sql = "DELETE FROM member_gym WHERE id_member=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMember);
            ps.executeUpdate();
            System.out.println("Member berhasil dihapus.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
