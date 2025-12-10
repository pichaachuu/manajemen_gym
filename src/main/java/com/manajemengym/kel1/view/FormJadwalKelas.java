package com.manajemengym.kel1.view;

import com.manajemengym.kel1.dao.InstrukturDAO;
import com.manajemengym.kel1.dao.JadwalKelasDAO;
import com.manajemengym.kel1.model.ComboItem;
import com.manajemengym.kel1.model.JadwalKelas;
import com.manajemengym.kel1.model.Instruktur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FormJadwalKelas extends JFrame {

    private JTextField txtNama, txtJam;
    private JComboBox<String> cbHari;
    private JComboBox<ComboItem> cbInstruktur;
    private JTable table;
    private DefaultTableModel tableModel;

    private JadwalKelasDAO dao = new JadwalKelasDAO();
    private InstrukturDAO instrukturDAO = new InstrukturDAO();

    public FormJadwalKelas() {
        setTitle("Jadwal Kelas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= PANEL INPUT =================
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Nama Kelas:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Hari:"));
        cbHari = new JComboBox<>(new String[]{
                "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
        });
        panelInput.add(cbHari);

        panelInput.add(new JLabel("Jam (HH:MM):"));
        txtJam = new JTextField();
        panelInput.add(txtJam);

        panelInput.add(new JLabel("Instruktur:"));
        cbInstruktur = new JComboBox<>();
        panelInput.add(cbInstruktur);

        loadInstrukturCombo();

        // ================ PANEL BUTTON =================
        JPanel panelBtn = new JPanel(new FlowLayout());
        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnReset = new JButton("Reset");

        panelBtn.add(btnSave);
        panelBtn.add(btnUpdate);
        panelBtn.add(btnDelete);
        panelBtn.add(btnReset);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelInput, BorderLayout.NORTH);
        topPanel.add(panelBtn, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Nama Kelas", "Hari", "Jam", "Instruktur"}, 0
        );
        table = new JTable(tableModel);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ================= AKSI =================
        btnSave.addActionListener((ActionEvent e) -> saveData());
        btnUpdate.addActionListener((ActionEvent e) -> updateData());
        btnDelete.addActionListener((ActionEvent e) -> deleteData());
        btnReset.addActionListener((ActionEvent e) -> resetForm());

        table.getSelectionModel().addListSelectionListener(e -> loadToForm());

        setVisible(true);
    }

    // ===============================================================
    // VALIDASI & KONVERSI JAM
    // ===============================================================

    private boolean isValidJam(String input) {
        input = input.trim().replace(".", ":");

        return input.matches("\\d{1,2}")
                || input.matches("\\d{1,2}:\\d{2}")
                || input.matches("\\d{1,2}:\\d{2}:\\d{2}");
    }

    private String convertJam(String input) {
        input = input.trim().replace(".", ":");

        if (input.matches("\\d{1,2}")) {
            return input + ":00:00";
        }

        if (input.matches("\\d{1,2}:\\d{2}")) {
            return input + ":00";
        }

        return input; // sudah lengkap HH:MM:SS
    }

    private void loadInstrukturCombo() {
        cbInstruktur.removeAllItems();
        for (Instruktur i : instrukturDAO.getAll()) {
            cbInstruktur.addItem(new ComboItem(i.getNama(), i.getId_instruktur()));
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<JadwalKelas> list = dao.getAll();

        for (JadwalKelas j : list) {
            tableModel.addRow(new Object[]{
                    j.getId_kelas(),
                    j.getNama_kelas(),
                    j.getHari(),
                    j.getJam(),
                    j.getId_instruktur()
            });
        }
    }

    private void saveData() {
    try {
        // VALIDASI NAMA KELAS
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama kelas tidak boleh kosong!");
            return;
        }

        if (!txtNama.getText().matches("[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(this,
                    "Nama kelas hanya boleh huruf, angka, dan spasi!");
            return;
        }

        // VALIDASI JAM
        if (!isValidJam(txtJam.getText())) {
            JOptionPane.showMessageDialog(this,
                    "Format jam tidak valid!\nContoh: 12:00 atau 12.00 atau 12:30:00");
            return;
        }

        JadwalKelas j = new JadwalKelas();
        j.setNama_kelas(txtNama.getText());
        j.setHari(cbHari.getSelectedItem().toString());
        j.setJam(convertJam(txtJam.getText())); // FIX JAM

        ComboItem ins = (ComboItem) cbInstruktur.getSelectedItem();
        j.setId_instruktur(ins.getValue());

        dao.insert(j);
        loadData();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Input tidak valid!");
    }
}

    private void updateData() {
    try {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        // VALIDASI NAMA
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama kelas tidak boleh kosong!");
            return;
        }

        if (!txtNama.getText().matches("[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(this,
                    "Nama kelas hanya boleh huruf, angka, dan spasi!");
            return;
        }

        // VALIDASI JAM
        if (!isValidJam(txtJam.getText())) {
            JOptionPane.showMessageDialog(this,
                    "Format jam tidak valid!\nContoh: 12:00 atau 12.00 atau 12:30:00");
            return;
        }

        JadwalKelas j = new JadwalKelas();
        j.setId_kelas(Integer.parseInt(table.getValueAt(row, 0).toString()));
        j.setNama_kelas(txtNama.getText());
        j.setHari(cbHari.getSelectedItem().toString());
        j.setJam(convertJam(txtJam.getText()));

        ComboItem ins = (ComboItem) cbInstruktur.getSelectedItem();
        j.setId_instruktur(ins.getValue());

        dao.update(j);
        loadData();

        JOptionPane.showMessageDialog(this, "Data diperbarui!");

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}


    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        dao.delete(id);
        loadData();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data dihapus!");
    }

    private void resetForm() {
        txtNama.setText("");
        txtJam.setText("");
        cbHari.setSelectedIndex(0);
        if (cbInstruktur.getItemCount() > 0) cbInstruktur.setSelectedIndex(0);
    }

    private void loadToForm() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        txtNama.setText(table.getValueAt(row, 1).toString());
        cbHari.setSelectedItem(table.getValueAt(row, 2).toString());
        txtJam.setText(table.getValueAt(row, 3).toString());
    }
}
