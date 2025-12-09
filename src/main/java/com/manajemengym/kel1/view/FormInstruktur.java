package com.manajemengym.kel1.view;

import com.manajemengym.kel1.dao.InstrukturDAO;
import com.manajemengym.kel1.model.Instruktur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FormInstruktur extends JFrame {

    private JTextField txtNama, txtUsia, txtKeahlian, txtTelpon;
    private JButton btnSave, btnUpdate, btnDelete, btnReset;
    private JTable table;
    private DefaultTableModel model;

    private InstrukturDAO dao = new InstrukturDAO();

    public FormInstruktur() {
        setTitle("Form Instruktur");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =================== LABEL ===================
        JLabel l2 = new JLabel("Nama:");
        l2.setBounds(20, 20, 120, 25);
        add(l2);

        txtNama = new JTextField();
        txtNama.setBounds(130, 20, 200, 25);
        add(txtNama);

        JLabel l3 = new JLabel("Usia:");
        l3.setBounds(20, 60, 120, 25);
        add(l3);

        txtUsia = new JTextField();
        txtUsia.setBounds(130, 60, 200, 25);
        add(txtUsia);

        JLabel l4 = new JLabel("Keahlian:");
        l4.setBounds(20, 100, 120, 25);
        add(l4);

        txtKeahlian = new JTextField();
        txtKeahlian.setBounds(130, 100, 200, 25);
        add(txtKeahlian);

        JLabel l5 = new JLabel("Telepon:");
        l5.setBounds(20, 140, 120, 25);
        add(l5);

        txtTelpon = new JTextField();
        txtTelpon.setBounds(130, 140, 200, 25);
        add(txtTelpon);

        // =================== BUTTON ===================
        btnSave = new JButton("Save");
        btnSave.setBounds(20, 190, 80, 30);
        add(btnSave);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(110, 190, 80, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(200, 190, 80, 30);
        add(btnDelete);

        btnReset = new JButton("Reset");
        btnReset.setBounds(290, 190, 80, 30);
        add(btnReset);

        // =================== TABLE ===================
        model = new DefaultTableModel(new String[]{"ID", "Nama", "Usia", "Keahlian", "Telepon"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 250, 750, 280);
        add(sp);

        loadData();

        // =================== EVENT ===================
        btnSave.addActionListener(e -> saveData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnReset.addActionListener(e -> resetForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fillFormFromTable();
            }
        });

        setVisible(true);
    }

    // =================== METHODS ===================

    private void loadData() {
        model.setRowCount(0);
        List<Instruktur> list = dao.getAll();

        for (Instruktur ins : list) {
            model.addRow(new Object[]{
                    ins.getId_instruktur(),
                    ins.getNama(),
                    ins.getUsia(),
                    ins.getKeahlian(),
                    ins.getTelepon()
            });
        }
    }

    private void saveData() {
        if (txtNama.getText().isEmpty() || txtUsia.getText().isEmpty() ||
            txtKeahlian.getText().isEmpty() || txtTelpon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi!");
            return;
        }

        Instruktur ins = new Instruktur();
        ins.setNama(txtNama.getText());
        ins.setUsia(Integer.parseInt(txtUsia.getText()));
        ins.setKeahlian(txtKeahlian.getText());
        ins.setTelepon(txtTelpon.getText());

        dao.insert(ins);
        loadData();
        resetForm();
    }

    private void updateData() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel dulu!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        Instruktur ins = new Instruktur();
        ins.setId_instruktur(id);
        ins.setNama(txtNama.getText());
        ins.setUsia(Integer.parseInt(txtUsia.getText()));
        ins.setKeahlian(txtKeahlian.getText());
        ins.setTelepon(txtTelpon.getText());

        dao.update(ins);
        loadData();
        resetForm();
    }

    private void deleteData() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
        dao.delete(id);

        loadData();
        resetForm();
    }

    private void resetForm() {
        txtNama.setText("");
        txtUsia.setText("");
        txtKeahlian.setText("");
        txtTelpon.setText("");
    }

    private void fillFormFromTable() {
        int row = table.getSelectedRow();

        txtNama.setText(model.getValueAt(row, 1).toString());
        txtUsia.setText(model.getValueAt(row, 2).toString());
        txtKeahlian.setText(model.getValueAt(row, 3).toString());
        txtTelpon.setText(model.getValueAt(row, 4).toString());
    }
}
