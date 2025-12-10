package com.manajemengym.kel1.view;

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

    private PendaftaranGymDAO dao = new PendaftaranGymDAO();
    private JadwalKelasDAO kelasDAO = new JadwalKelasDAO();
    private MemberDAO memberDAO = new MemberDAO();

    public FormPendaftaranGym() {
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

        setVisible(true);
    }

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
    }
    
    private void resetForm() {
        if (cbMember.getItemCount() > 0) cbMember.setSelectedIndex(0);
        if (cbKelas.getItemCount() > 0) cbKelas.setSelectedIndex(0);
        txtTglDaftar.setText("");
        txtCatatan.setText("");
    }

    private void loadToForm() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        txtTglDaftar.setText(table.getValueAt(row, 3).toString());
        txtCatatan.setText(table.getValueAt(row, 4).toString());
    }    
    
}

