
package com.manajemengym.kel1.view;

import com.manajemengym.kel1.dao.InstrukturDAO;
import com.manajemengym.kel1.model.Instruktur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FormInstruktur extends JFrame {

    private JTextField txtNama, txtUsia, txtKeahlian, txtTelepon;
    private JTable table;
    private DefaultTableModel tableModel;

    private InstrukturDAO dao = new InstrukturDAO();

    public FormInstruktur() {
        setTitle("Form Instruktur Gym");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===================== PANEL INPUT =====================
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Usia:"));
        txtUsia = new JTextField();
        panelInput.add(txtUsia);

        panelInput.add(new JLabel("Keahlian:"));
        txtKeahlian = new JTextField();
        panelInput.add(txtKeahlian);

        panelInput.add(new JLabel("Telepon:"));
        txtTelepon = new JTextField();
        panelInput.add(txtTelepon);

        // ===================== PANEL TOMBOL =====================
        JPanel panelBtn = new JPanel(new FlowLayout());

        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnReset = new JButton("Reset");

        panelBtn.add(btnSave);
        panelBtn.add(btnUpdate);
        panelBtn.add(btnDelete);
        panelBtn.add(btnReset);

        // Gabungkan input + tombol
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelInput, BorderLayout.CENTER);
        topPanel.add(panelBtn, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // ===================== TABLE =====================
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Nama", "Usia", "Keahlian", "Telepon"}, 0
        );
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTable();

        // ===================== EVENT =====================
        btnSave.addActionListener(e -> saveInstruktur());
        btnUpdate.addActionListener(e -> updateInstruktur());
        btnDelete.addActionListener(e -> deleteInstruktur());
        btnReset.addActionListener(e -> resetForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loadFormFromTable();
            }
        });

        setVisible(true);
    }

    // ===================== SAVE =====================
    private void saveInstruktur() {
    try {
        // VALIDASI NAMA
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
            return;
        }

        if (!txtNama.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Nama hanya boleh huruf dan spasi!");
            return;
        }

        // VALIDASI USIA
        if (txtUsia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usia tidak boleh kosong!");
            return;
        }

        if (!txtUsia.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Usia harus angka!");
            return;
        }

        int usia = Integer.parseInt(txtUsia.getText());

        // Jika <12 → TETAP lanjut tapi kasih pesan, setter akan set ke 12
        if (usia < 12) {
            JOptionPane.showMessageDialog(this, "Usia terlalu kecil, otomatis diset menjadi 12.");
        }

        // VALIDASI KEAHLIAN
        if (txtKeahlian.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keahlian tidak boleh kosong!");
            return;
        }

        // VALIDASI TELEPON
        if (txtTelepon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Telepon tidak boleh kosong!");
            return;
        }

        if (!txtTelepon.getText().matches("\\+?\\d{10,13}")) {
            JOptionPane.showMessageDialog(this, "Format nomor telepon tidak valid!");
            return;
        }

        // MASUKKAN KE MODEL
        Instruktur ins = new Instruktur();
        ins.setNama(txtNama.getText());
        ins.setUsia(usia); // setter akan auto-set 12
        ins.setKeahlian(txtKeahlian.getText());
        ins.setTelepon(txtTelepon.getText());

        dao.insert(ins);
        loadTable();
        resetForm();
        JOptionPane.showMessageDialog(this, "Instruktur berhasil disimpan!");

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}


    // ===================== UPDATE =====================
    private void updateInstruktur() {
    try {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        // VALIDASI NAMA
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
            return;
        }

        if (!txtNama.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Nama hanya boleh huruf dan spasi!");
            return;
        }

        // VALIDASI USIA
        if (txtUsia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usia tidak boleh kosong!");
            return;
        }

        if (!txtUsia.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Usia harus angka!");
            return;
        }

        int usia = Integer.parseInt(txtUsia.getText());

        if (usia < 12) {
            JOptionPane.showMessageDialog(this, "Usia terlalu kecil, otomatis diset menjadi 12.");
        }

        // VALIDASI KEAHLIAN
        if (txtKeahlian.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keahlian tidak boleh kosong!");
            return;
        }

        // VALIDASI TELEPON
        if (txtTelepon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Telepon tidak boleh kosong!");
            return;
        }

        if (!txtTelepon.getText().matches("\\+?\\d{10,13}")) {
            JOptionPane.showMessageDialog(this, "Format nomor telepon tidak valid!");
            return;
        }

        // MASUKKAN KE MODEL
        int id = Integer.parseInt(table.getValueAt(row, 0).toString());

        Instruktur ins = new Instruktur();
        ins.setId_instruktur(id);
        ins.setNama(txtNama.getText());
        ins.setUsia(usia); // setter tetap auto-set
        ins.setKeahlian(txtKeahlian.getText());
        ins.setTelepon(txtTelepon.getText());

        dao.update(ins);

        loadTable();
        JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}



    // ===================== DELETE =====================
    private void deleteInstruktur() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        dao.delete(id);

        loadTable();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
    }

    // ===================== LOAD TABEL =====================
    private void loadTable() {
        tableModel.setRowCount(0);

        List<Instruktur> list = dao.getAll();
        for (Instruktur ins : list) {
            tableModel.addRow(new Object[]{
                    ins.getId_instruktur(),
                    ins.getNama(),
                    ins.getUsia(),
                    ins.getKeahlian(),
                    ins.getTelepon()
            });
        }
    }

    // ===================== RESET =====================
    private void resetForm() {
        txtNama.setText("");
        txtUsia.setText("");
        txtKeahlian.setText("");
        txtTelepon.setText("");
    }

    // ===================== TABLE → FORM =====================
    private void loadFormFromTable() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        txtNama.setText(table.getValueAt(row, 1).toString());
        txtUsia.setText(table.getValueAt(row, 2).toString());
        txtKeahlian.setText(table.getValueAt(row, 3).toString());
        txtTelepon.setText(table.getValueAt(row, 4).toString());
    }

    public static void main(String[] args) {
        new FormInstruktur();
    }
}
