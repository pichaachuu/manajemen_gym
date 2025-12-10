package com.manajemengym.kel1.view;

<<<<<<< HEAD
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
=======
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Timestamp;

import com.manajemengym.kel1.dao.PendaftaranGymDAO;
import com.manajemengym.kel1.dao.JadwalKelasDAO;
import com.manajemengym.kel1.dao.MemberDAO;
import com.manajemengym.kel1.model.PendaftaranGym;
import com.manajemengym.kel1.model.ComboItem;
import com.manajemengym.kel1.model.JadwalKelas;
import com.manajemengym.kel1.model.Member;

public class FormPendaftaranGym extends JFrame{
    
    private JComboBox<ComboItem> cbMember;
    private JComboBox<ComboItem> cbKelas;
    private JTextField txtTglDaftar;
    private JTextArea txtCatatan;
    private JTable table;
    private DefaultTableModel tableModel;

<<<<<<< HEAD
    JTextField txtMember, txtKelas, txtTanggal, txtCatatan;
    JTable table;
    DefaultTableModel model;
=======
    private PendaftaranGymDAO dao = new PendaftaranGymDAO();
    private JadwalKelasDAO kelasDAO = new JadwalKelasDAO();
    private MemberDAO memberDAO = new MemberDAO();
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984

    private int selectedId = -1;

    public FormPendaftaranGym() {
<<<<<<< HEAD
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
=======
        setTitle("Form Pendaftaran");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Member:"));
        cbMember = new JComboBox<>();
        panelInput.add(cbMember);

        loadMemberCombo();

        panelInput.add(new JLabel("Kelas:"));
        cbKelas = new JComboBox<>();
        panelInput.add(cbKelas);

        loadKelasCombo();

        panelInput.add(new JLabel("Tanggal Daftar:"));
        txtTglDaftar = new JTextField();
        panelInput.add(txtTglDaftar);

        panelInput.add(new JLabel("Catatan:"));
        txtCatatan = new JTextArea(3, 20);
        panelInput.add(txtCatatan);
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984

        JPanel panelBtn = new JPanel(new FlowLayout());
        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
<<<<<<< HEAD
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
=======
        JButton btnReset = new JButton("Reset");

        panelBtn.add(btnSave);
        panelBtn.add(btnUpdate);
        panelBtn.add(btnDelete);
        panelBtn.add(btnReset);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelInput, BorderLayout.NORTH);
        topPanel.add(panelBtn, BorderLayout.CENTER);
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984

        add(topPanel, BorderLayout.NORTH);

<<<<<<< HEAD
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
=======
        // ================= TABLE =================
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Member", "Kelas", "Tanggal Daftar", "Catatan"}, 0
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
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984

        setVisible(true);
    }

<<<<<<< HEAD
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
=======
    private void loadMemberCombo() {
        cbMember.removeAllItems();
        for (Member m : memberDAO.getAll()) {
            cbMember.addItem(new ComboItem(m.getNama(), m.getIdMember()));
        }
    }

    private void loadKelasCombo(){
        cbKelas.removeAllItems();
        for (JadwalKelas k : kelasDAO.getAll()) {
            cbKelas.addItem(new ComboItem(k.getNama_kelas(), k.getId_kelas()));
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<PendaftaranGym> list = dao.getAll();

        for (PendaftaranGym  p: list) {
            tableModel.addRow(new Object[]{
                p.getIdPendaftaran(),
                p.getId_member(),
                p.getId_kelas(),
                p.getTanggalDaftar(),
                p.getCatatan()
            });
        }
    }

    private void saveData() {
        try {
            PendaftaranGym p = new PendaftaranGym();
            ComboItem mem = (ComboItem) cbMember.getSelectedItem();
            p.setId_member(mem.getValue());
            ComboItem kls = (ComboItem) cbKelas.getSelectedItem();
            p.setId_kelas(kls.getValue());
            p.setTanggalDaftar(Timestamp.valueOf(LocalDateTime.now()));
            p.setCatatan(txtCatatan.getText());
            

            dao.insert(p);
            loadData();
            resetForm();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid!");
        }
    }

    private void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        PendaftaranGym p = new PendaftaranGym();
        p.setIdPendaftaran(Integer.parseInt(table.getValueAt(row, 0).toString()));

        ComboItem mem = (ComboItem) cbMember.getSelectedItem();
        p.setId_member(mem.getValue());

        ComboItem kls = (ComboItem) cbKelas.getSelectedItem();
        p.setId_kelas(kls.getValue());

        // TIMESTAMP JUGA
        p.setTanggalDaftar(Timestamp.valueOf(LocalDateTime.now()));

        p.setCatatan(txtCatatan.getText());

        dao.update(p);
        loadData();
        JOptionPane.showMessageDialog(this, "Data diperbarui!");
    }


    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        dao.delete(id);
        loadData();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data dihapus!");
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
    }
    
    private void resetForm() {
<<<<<<< HEAD
        selectedId = -1;
        txtMember.setText("");
        txtKelas.setText("");
        txtTanggal.setText("");
=======
        if (cbMember.getItemCount() > 0) cbMember.setSelectedIndex(0);
        if (cbKelas.getItemCount() > 0) cbKelas.setSelectedIndex(0);
        txtTglDaftar.setText("");
>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
        txtCatatan.setText("");
        table.clearSelection();
    }
<<<<<<< HEAD
}
=======

    private void loadToForm() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        txtTglDaftar.setText(table.getValueAt(row, 3).toString());
        txtCatatan.setText(table.getValueAt(row, 4).toString());
    }    
    
}

>>>>>>> b1c7b6482c7ed2b1a9d6db12347e6b2796bd2984
