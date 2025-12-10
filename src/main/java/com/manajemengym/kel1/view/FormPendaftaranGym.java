package com.manajemengym.kel1.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.manajemengym.kel1.dao.PendaftaranGymDAO;
import com.manajemengym.kel1.model.PendaftaranGym;

public class FormPendaftaranGym extends JFrame {

    JTextField txtMember, txtKelas, txtTanggal, txtCatatan;
    JTable table;
    DefaultTableModel model;

    private int selectedId = -1;

    public FormPendaftaranGym() {
        setTitle("Form Pendaftaran Gym");
        setSize(720, 460);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblMember = new JLabel("Member:");
        lblMember.setBounds(20, 30, 120, 25);
        add(lblMember);

        txtMember = new JTextField();
        txtMember.setBounds(150, 30, 200, 25);
        add(txtMember);

        JLabel lblKelas = new JLabel("Kelas Gym:");
        lblKelas.setBounds(20, 65, 120, 25);
        add(lblKelas);

        txtKelas = new JTextField();
        txtKelas.setBounds(150, 65, 200, 25);
        add(txtKelas);

        JLabel lblTanggal = new JLabel("Tanggal:");
        lblTanggal.setBounds(20, 100, 120, 25); 
        add(lblTanggal);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(150, 100, 200, 25); 
        add(txtTanggal);

        JLabel lblCatatan = new JLabel("Catatan:");
        lblCatatan.setBounds(20, 135, 120, 25);
        add(lblCatatan);

        txtCatatan = new JTextField();
        txtCatatan.setBounds(150, 135, 200, 25);
        add(txtCatatan);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(20, 175, 90, 30);
        add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 175, 90, 30);
        add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(220, 175, 90, 30);
        add(btnDelete);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(320, 175, 90, 30);
        add(btnReset);

        model = new DefaultTableModel(
                new Object[]{"ID", "Member", "Kelas", "Tanggal", "Catatan"}, 0
        );

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 220, 660, 180);
        add(scroll);

        loadTable();

        btnSave.addActionListener(e -> saveData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnReset.addActionListener(e -> resetForm());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
                txtMember.setText(model.getValueAt(row, 1).toString());
                txtKelas.setText(model.getValueAt(row, 2).toString());
                txtTanggal.setText(model.getValueAt(row, 3).toString());
                txtCatatan.setText(model.getValueAt(row, 4).toString());
            }
        });

        setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        List<PendaftaranGym> list = new PendaftaranGymDAO().getAll();

        for (PendaftaranGym p : list) {
            model.addRow(new Object[]{
                    p.getIdPendaftaran(),
                    p.getMember(),
                    p.getKelasGym(),
                    p.getTanggalDaftar(),
                    p.getCatatan()
            });
        }
    }

    private void saveData() {
        if (txtMember.getText().isEmpty() || txtKelas.getText().isEmpty() || txtTanggal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Member, Kelas, dan Tanggal wajib diisi!");
            return;
        }

        PendaftaranGym p = new PendaftaranGym();
        p.setMember(txtMember.getText());
        p.setKelasGym(txtKelas.getText());
        p.setTanggalDaftar(txtTanggal.getText());
        p.setCatatan(txtCatatan.getText());

        new PendaftaranGymDAO().insert(p);
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
        loadTable();
        resetForm();
    }

    private void updateData() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel!");
            return;
        }

        PendaftaranGym p = new PendaftaranGym();
        p.setIdPendaftaran(selectedId);
        p.setMember(txtMember.getText());
        p.setKelasGym(txtKelas.getText());
        p.setTanggalDaftar(txtTanggal.getText());
        p.setCatatan(txtCatatan.getText());

        new PendaftaranGymDAO().update(p);
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
        loadTable();
        resetForm();
    }

    private void deleteData() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new PendaftaranGymDAO().delete(selectedId);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
            loadTable();
            resetForm();
        }
    }

    private void resetForm() {
        selectedId = -1;
        txtMember.setText("");
        txtKelas.setText("");
        txtTanggal.setText("");
        txtCatatan.setText("");
        table.clearSelection();
    }
}
