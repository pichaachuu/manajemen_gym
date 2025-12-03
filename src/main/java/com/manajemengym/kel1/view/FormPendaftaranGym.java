package com.manajemengym.kel1.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.manajemengym.kel1.dao.PendaftaranGymDAO;
import com.manajemengym.kel1.model.PendaftaranGym;

public class FormPendaftaranGym extends JFrame {

    JTextField txtId, txtMember, txtKelas, txtTanggal, txtCatatan;
    JTable table;
    DefaultTableModel model;

    public FormPendaftaranGym() {
        setTitle("Form Pendaftaran Gym");
        setSize(700, 420);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblMember = new JLabel("Member:");
        lblMember.setBounds(20, 20, 120, 25);
        add(lblMember);

        txtMember = new JTextField();
        txtMember.setBounds(150, 20, 200, 25);
        add(txtMember);

        JLabel lblKelas = new JLabel("Kelas Gym:");
        lblKelas.setBounds(20, 60, 120, 25);
        add(lblKelas);

        txtKelas = new JTextField();
        txtKelas.setBounds(150, 60, 200, 25);
        add(txtKelas);

        JLabel lblTanggal = new JLabel("Tanggal Daftar:");
        lblTanggal.setBounds(20, 100, 120, 25);
        add(lblTanggal);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(150, 100, 200, 25);
        add(txtTanggal);

        JLabel lblCatatan = new JLabel("Catatan:");
        lblCatatan.setBounds(20, 140, 120, 25);
        add(lblCatatan);

        txtCatatan = new JTextField();
        txtCatatan.setBounds(150, 140, 200, 25);
        add(txtCatatan);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 190, 90, 30);
        add(btnSimpan);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(120, 190, 90, 30);
        add(btnDelete);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(220, 190, 90, 30);
        add(btnReset);

        // TABEL
        model = new DefaultTableModel();
        model.addColumn("ID");   
        model.addColumn("Member");
        model.addColumn("Kelas Gym");
        model.addColumn("Tanggal Daftar");
        model.addColumn("Catatan");

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 230, 650, 150);
        add(scroll);

        loadTable();

        // --- ACTION SIMPAN ---
        btnSimpan.addActionListener(e -> simpanData());

        // --- ACTION DELETE ---
        btnDelete.addActionListener(e -> deleteData());

        // --- ACTION RESET ---
        btnReset.addActionListener(e -> resetForm());

        // --- TABLE CLICK ---
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.getSelectedRow();
                txtId.setText(model.getValueAt(row, 0).toString());
                txtMember.setText(model.getValueAt(row, 1).toString());
                txtKelas.setText(model.getValueAt(row, 2).toString());
                txtTanggal.setText(model.getValueAt(row, 3).toString());
                txtCatatan.setText(model.getValueAt(row, 4).toString());
            }
        });

        setVisible(true);
    }

    // ================ METHOD CRUD ================

    private void loadTable() {
        try {
            PendaftaranGymDAO dao = new PendaftaranGymDAO();
            List<PendaftaranGym> list = dao.getAll();

            model.setRowCount(0);

            for (PendaftaranGym p : list) {
                model.addRow(new Object[]{
                        p.getIdPendaftaran(),  
                        p.getMember(),
                        p.getKelasGym(),
                        p.getTanggalDaftar(),
                        p.getCatatan()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void simpanData() {
        try {
            PendaftaranGym p = new PendaftaranGym();
            p.setMember(txtMember.getText());
            p.setKelasGym(txtKelas.getText());
            p.setTanggalDaftar(txtTanggal.getText());
            p.setCatatan(txtCatatan.getText());

            new PendaftaranGymDAO().insert(p);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

            loadTable();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void deleteData() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            new PendaftaranGymDAO().delete(id);

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            loadTable();
            resetForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void resetForm() {
        txtMember.setText("");
        txtKelas.setText("");
        txtTanggal.setText("");
        txtCatatan.setText("");
    }
}