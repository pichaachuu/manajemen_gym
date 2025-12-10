package com.manajemengym.kel1.view;

import com.manajemengym.kel1.dao.MemberDAO;
import com.manajemengym.kel1.model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FormMember extends JFrame {

    private JTextField txtNama, txtUsia, txtTelepon;
    private JTextArea txtAlamat;
    private JComboBox<String> cbGender;
    private JTable table;
    private DefaultTableModel tableModel;

    private MemberDAO memberDAO = new MemberDAO();

    public FormMember() {
        setTitle("Form Registrasi Member Gym");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ✦ PANEL INPUT ✦
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Gender:"));
        cbGender = new JComboBox<>(new String[]{"L", "P"});
        panelInput.add(cbGender);

        panelInput.add(new JLabel("Usia:"));
        txtUsia = new JTextField();
        panelInput.add(txtUsia);

        panelInput.add(new JLabel("Telepon:"));
        txtTelepon = new JTextField();
        panelInput.add(txtTelepon);

        panelInput.add(new JLabel("Alamat:"));
        txtAlamat = new JTextArea(3, 20);
        panelInput.add(new JScrollPane(txtAlamat));

        add(panelInput, BorderLayout.NORTH);

        // ✦ PANEL TOMBOL ✦
        JPanel panelBtn = new JPanel(new FlowLayout());
        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnReset = new JButton("Reset");

        panelBtn.add(btnSave);
        panelBtn.add(btnUpdate);
        panelBtn.add(btnDelete);
        panelBtn.add(btnReset);

        // Hapus: add(panelInput, BorderLayout.NORTH);

        // ✦ PANEL GABUNGAN UNTUK INPUT DAN TOMBOL ✦
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelInput, BorderLayout.NORTH); // Input di bagian atas panel gabungan
        topPanel.add(panelBtn, BorderLayout.CENTER); // Tombol di bagian bawah panel gabungan

        add(topPanel, BorderLayout.NORTH);

        
        
        
        // ✦ TABEL ✦
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Nama", "Gender", "Usia", "Telepon", "Alamat"}, 0
        );
        table = new JTable(tableModel);
        loadTable();
        
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ✦ AKSI TOMBOL ✦
        btnSave.addActionListener((ActionEvent e) -> saveMember());
        btnUpdate.addActionListener((ActionEvent e) -> updateMember());
        btnDelete.addActionListener((ActionEvent e) -> deleteMember());
        btnReset.addActionListener((ActionEvent e) -> resetForm());

        // klik tabel → isi form
        table.getSelectionModel().addListSelectionListener(event -> loadDataToForm());

        setVisible(true);
    }

    // ✦ FUNGSI SIMPAN ✦
    private void saveMember() {
        try {
            // VALIDASI DASAR DI VIEW (UI)
            if (txtNama.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
                return;
            }

            if (txtUsia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Usia tidak boleh kosong!");
                return;
            }

            if (!txtUsia.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Usia harus angka!");
                return;
            }

            if (txtTelepon.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nomor telepon tidak boleh kosong!");
                return;
            }

            if (!txtTelepon.getText().matches("\\+?\\d{10,13}")) {
                JOptionPane.showMessageDialog(this, "Format nomor telepon tidak valid!");
                return;
            }

            if (txtAlamat.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Alamat tidak boleh kosong!");
                return;
            }

            // Jika lolos UI validation → masukkan ke model
            Member m = new Member();
            m.setNama(txtNama.getText());
            m.setGender(cbGender.getSelectedItem().toString());
            m.setUsia(Integer.parseInt(txtUsia.getText()));  // Model akan validasi ulang
            m.setTelepon(txtTelepon.getText());
            m.setAlamat(txtAlamat.getText());

            memberDAO.insert(m);
            loadTable();
            resetForm();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

        } catch (IllegalArgumentException ex) {
            // Pesan validasi dari Model
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
        }
    }


    // ✦ FUNGSI UPDATE ✦
    private void updateMember() {
        try {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
                return;
            }

            if (txtNama.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
                return;
            }

            if (!txtUsia.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Usia harus angka!");
                return;
            }

            if (!txtTelepon.getText().matches("\\+?\\d{10,13}")) {
                JOptionPane.showMessageDialog(this, "Format nomor telepon tidak valid!");
                return;
            }

            Member m = new Member();
            m.setIdMember(Integer.parseInt(table.getValueAt(row, 0).toString()));
            m.setNama(txtNama.getText());
            m.setGender(cbGender.getSelectedItem().toString());
            m.setUsia(Integer.parseInt(txtUsia.getText()));
            m.setTelepon(txtTelepon.getText());
            m.setAlamat(txtAlamat.getText());

            memberDAO.update(m);
            loadTable();
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }


    // ✦ FUNGSI DELETE ✦
    private void deleteMember() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        memberDAO.delete(id);
        loadTable();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
    }

    // ✦ FUNGSI LOAD TABEL ✦
    private void loadTable() {
        tableModel.setRowCount(0);
        List<Member> list = memberDAO.getAllMembers();

        for (Member m : list) {
            tableModel.addRow(new Object[]{
                    m.getIdMember(), m.getNama(), m.getGender(),
                    m.getUsia(), m.getTelepon(), m.getAlamat()
            });
        }
    }

    // ✦ FUNGSI RESET ✦
    private void resetForm() {
        txtNama.setText("");
        txtUsia.setText("");
        txtTelepon.setText("");
        txtAlamat.setText("");
        cbGender.setSelectedIndex(0);
    }

    // klik tabel → isi form
    private void loadDataToForm() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        txtNama.setText(table.getValueAt(row, 1).toString());
        cbGender.setSelectedItem(table.getValueAt(row, 2).toString());
        txtUsia.setText(table.getValueAt(row, 3).toString());
        txtTelepon.setText(table.getValueAt(row, 4).toString());
        txtAlamat.setText(table.getValueAt(row, 5).toString());
    }

    public static void main(String[] args) {
        new FormMember();
    }
}
