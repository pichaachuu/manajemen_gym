package com.manajemengym.kel1.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.manajemengym.kel1.dao.*;
import com.manajemengym.kel1.model.*;

public class FormPendaftaranGym extends JFrame {

    private JComboBox<ComboItem> cbMember;
    private JComboBox<ComboItem> cbKelas;
    private JTextField txtTglDaftar;
    private JTextArea txtCatatan;
    private JTable table;
    private DefaultTableModel tableModel;

    private final PendaftaranGymDAO dao = new PendaftaranGymDAO();
    private final JadwalKelasDAO kelasDAO = new JadwalKelasDAO();
    private final MemberDAO memberDAO = new MemberDAO();

    public FormPendaftaranGym() {
        setTitle("Form Pendaftaran Gym");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Member"));
        cbMember = new JComboBox<>();
        loadMemberCombo();
        panelInput.add(cbMember);

        panelInput.add(new JLabel("Kelas"));
        cbKelas = new JComboBox<>();
        loadKelasCombo();
        panelInput.add(cbKelas);

        panelInput.add(new JLabel("Tanggal Daftar"));
        txtTglDaftar = new JTextField(LocalDateTime.now().toString());
        txtTglDaftar.setEditable(false);
        panelInput.add(txtTglDaftar);

        panelInput.add(new JLabel("Catatan"));
        txtCatatan = new JTextArea(3, 20);
        panelInput.add(new JScrollPane(txtCatatan));

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

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Member", "Kelas", "Tanggal", "Catatan"}, 0);
        table = new JTable(tableModel);
        loadData();
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnSave.addActionListener(this::saveData);
        btnUpdate.addActionListener(this::updateData);
        btnDelete.addActionListener(this::deleteData);
        btnReset.addActionListener(e -> resetForm());

        table.getSelectionModel().addListSelectionListener(e -> loadToForm());

        setVisible(true);
    }

    private void loadMemberCombo() {
        cbMember.removeAllItems();
        for (Member m : memberDAO.getAll()) {
            cbMember.addItem(new ComboItem(m.getNama(), m.getIdMember()));
        }
    }

    private void loadKelasCombo() {
        cbKelas.removeAllItems();
        for (JadwalKelas k : kelasDAO.getAll()) {
            cbKelas.addItem(new ComboItem(k.getNama_kelas(), k.getId_kelas()));
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        for (PendaftaranGym p : dao.getAll()) {
            tableModel.addRow(new Object[]{
                    p.getIdPendaftaran(),
                    p.getId_member(),
                    p.getId_kelas(),
                    p.getTanggalDaftar(),
                    p.getCatatan()
            });
        }
    }

    private boolean validasiInput() {
        if (txtCatatan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Catatan wajib diisi!");
            return false;
        }
        return true;
    }

    private void saveData(ActionEvent e) {
        if (!validasiInput()) return;

        PendaftaranGym p = new PendaftaranGym();
        p.setId_member(((ComboItem) cbMember.getSelectedItem()).getValue());
        p.setId_kelas(((ComboItem) cbKelas.getSelectedItem()).getValue());
        p.setTanggalDaftar(Timestamp.valueOf(LocalDateTime.now()));
        p.setCatatan(txtCatatan.getText());

        dao.insert(p);
        loadData();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
    }

    private void updateData(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel!");
            return;
        }

        PendaftaranGym p = new PendaftaranGym();
        p.setIdPendaftaran((int) table.getValueAt(row, 0));
        p.setId_member(((ComboItem) cbMember.getSelectedItem()).getValue());
        p.setId_kelas(((ComboItem) cbKelas.getSelectedItem()).getValue());
        p.setCatatan(txtCatatan.getText());

        dao.update(p);
        loadData();
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
    }

    private void deleteData(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Yakin hapus data?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) table.getValueAt(row, 0);
            dao.delete(id);
            loadData();
            resetForm();
            JOptionPane.showMessageDialog(this, "Data dihapus");
        }
    }

    private void resetForm() {
        cbMember.setSelectedIndex(0);
        cbKelas.setSelectedIndex(0);
        txtCatatan.setText("");
        txtTglDaftar.setText(LocalDateTime.now().toString());
        table.clearSelection();
    }

    private void loadToForm() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        setComboValue(cbMember, (int) table.getValueAt(row, 1));
        setComboValue(cbKelas, (int) table.getValueAt(row, 2));
        txtCatatan.setText(table.getValueAt(row, 4).toString());
    }

    private void setComboValue(JComboBox<ComboItem> combo, int value) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).getValue() == value) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
}