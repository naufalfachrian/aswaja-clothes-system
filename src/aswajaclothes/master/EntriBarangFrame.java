/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.master;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.grid.CustomerGridFrame;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.model.common.KabupatenModel;
import aswajaclothes.model.common.KecamatanModel;
import aswajaclothes.model.common.KelurahanModel;
import aswajaclothes.model.common.ProvinsiModel;
import aswajaclothes.grid.BarangGridFrame;
import aswajaclothes.grid.GridListener;
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.util.CurrencyUtil;
import aswajaclothes.util.ValidatorUtil;
import com.sun.glass.events.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Satrio
 */
public class EntriBarangFrame extends javax.swing.JFrame implements GridListener {

    /**
     * Creates new form EntriKonsumenFrame
     */
    public EntriBarangFrame() {
        initComponents();
        initKodeBarang();
        initWarna();
        initArea();
        initUkuran();
        initFormatFieldNumber();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initWarna() {
        listWarna = new ArrayList<>();
        listWarna.add("Putih");
        listWarna.add("Hitam");
        listWarna.add("Merah");
        listWarna.add("Biru Nary");
        for (String warna : listWarna) {
            cbWarna.addItem(warna);
        }
    }

    private void initArea() {
        listArea = new ArrayList<>();
        listArea.add("A4");
        listArea.add("A3");
        for (String area : listArea) {
            cbArea.addItem(area);
        }
    }

    private void initUkuran() {
        listUkuran = new ArrayList<>();
        listUkuran.add("S");
        listUkuran.add("M");
        listUkuran.add("L");
        listUkuran.add("XL");
        for (String ukuran : listUkuran) {
            cbUkuran.addItem(ukuran);
        }
    }

    private void initKodeBarang() {
        String kode = new ConnectionManager().getKodeBarang();
        tfKodeBarang.setText(kode);
    }
    
    private void initFormatFieldNumber(){
        tfHargaHpp.setValue(0);
        tfHargaJualSatuan.setValue(0);
    }

    private void simpanBarang(Boolean isUpdate) {
        try {
            String kode = new ValidatorUtil().isEmpty(tfKodeBarang.getText(), "Kode Barang");
            String nama = new ValidatorUtil().isEmpty(tfNama.getText(), "Nama Barang");
            String warna = listWarna.get(cbWarna.getSelectedIndex());
            String area = listArea.get(cbArea.getSelectedIndex());
            String ukuran = listUkuran.get(cbUkuran.getSelectedIndex());
            String hargaHPP = new CurrencyUtil().clearFormat(tfHargaHpp.getText());
            String hargaJualSatuan = new CurrencyUtil().clearFormat(tfHargaJualSatuan.getText());;
            BarangModel barang = new BarangModel();
            barang.setKode(kode);
            barang.setName(nama);
            barang.setWarna(warna);
            barang.setArea(area);
            barang.setUkuran(ukuran);
            barang.setHargaHPP(Integer.parseInt(hargaHPP));
            barang.setHargaJualSatuan(Integer.parseInt(hargaJualSatuan));
           
            Boolean isResult = new ConnectionManager().saveBarang(isUpdate, barang);
            if (isResult) {
                JOptionPane.showMessageDialog(this, "Simpan barang berhasil", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Simpan barang gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            if (ex instanceof ParseException){
                System.out.println("Parsing number failed");
            } else {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void clear() {
        initKodeBarang();
        tfNama.setText("");
        cbWarna.setSelectedIndex(0);
        cbArea.setSelectedIndex(0);
        cbUkuran.setSelectedIndex(0);
        tfHargaJualSatuan.setValue(0);
        tfHargaHpp.setValue(0);

    }

    private void setBarang(BarangModel barang) {
        tfKodeBarang.setText(barang.getKode());
        tfNama.setText(barang.getName());
        cbWarna.setSelectedIndex(findWarna(barang.getWarna()));
        cbArea.setSelectedIndex(findArea(barang.getArea()));
        cbUkuran.setSelectedIndex(findUkuran(barang.getUkuran()));
        tfHargaJualSatuan.setText(String.valueOf(barang.getHargaJualSatuan()));
        tfHargaHpp.setText(String.valueOf(barang.getHargaHPP()));
    }

    private int findWarna(String warna) {
        int index = 0;
        for(int i = 0; i < listWarna.size(); i++){
            if (listWarna.get(i).equals(warna)){
                index = i;
                break;
            }
        }
        return index;
    }

    private int findArea(String area) {
        int index = 0;
        for(int i = 0; i < listArea.size(); i++){
            if (listArea.get(i).equals(area)){
                index = i;
                break;
            }
        }
        return index;
    }

    private int findUkuran(String ukuran) {
        int index = 0;
        for(int i = 0; i < listUkuran.size(); i++){
            if (listUkuran.get(i).equals(ukuran)){
                index = i;
                break;
            }
        }
        return index;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        btnCariBarang = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbWarna = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbArea = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbUkuran = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfHargaHpp = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());
        tfHargaJualSatuan = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());
        jPanel4 = new javax.swing.JPanel();
        btnSimpanBarang = new javax.swing.JButton();
        btnUbahBarang = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Barang");
        setPreferredSize(new java.awt.Dimension(543, 400));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Barang"));

        jLabel1.setText("Kode Barang");

        tfKodeBarang.setEnabled(false);
        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });

        btnCariBarang.setText("Cari");
        btnCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBarangActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Barang");

        tfNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaActionPerformed(evt);
            }
        });

        jLabel3.setText("Warna");

        cbWarna.setName("cbWarna"); // NOI18N
        cbWarna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbWarnaActionPerformed(evt);
            }
        });

        jLabel4.setText("Area");

        cbArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAreaActionPerformed(evt);
            }
        });

        jLabel5.setText("Ukuran");

        cbUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUkuranActionPerformed(evt);
            }
        });

        jLabel6.setText("Harga HPP");

        jLabel8.setText("Harga Jual Satuan");

        tfHargaHpp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfHargaHppKeyPressed(evt);
            }
        });

        tfHargaJualSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfHargaJualSatuanKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbArea, 0, 270, Short.MAX_VALUE)
                    .addComponent(cbUkuran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNama)
                    .addComponent(cbWarna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfKodeBarang)
                    .addComponent(tfHargaHpp)
                    .addComponent(tfHargaJualSatuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCariBarang)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfHargaHpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfHargaJualSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tombol"));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnSimpanBarang.setText("Simpan");
        btnSimpanBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanBarangActionPerformed(evt);
            }
        });
        jPanel4.add(btnSimpanBarang);

        btnUbahBarang.setText("Ubah");
        btnUbahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahBarangActionPerformed(evt);
            }
        });
        jPanel4.add(btnUbahBarang);

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel4.add(btnHapus);

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel4.add(btnBatal);

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel4.add(btnKeluar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeBarangActionPerformed

    private void tfNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaActionPerformed

    private void cbWarnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbWarnaActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
    }//GEN-LAST:event_cbWarnaActionPerformed

    private void cbAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAreaActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
    }//GEN-LAST:event_cbAreaActionPerformed

    private void cbUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUkuranActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
    }//GEN-LAST:event_cbUkuranActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        BarangGridFrame barangGrid = new BarangGridFrame("");
        barangGrid.setGridListener(this);
        barangGrid.setVisible(true);
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void btnUbahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahBarangActionPerformed
        simpanBarang(true);
    }//GEN-LAST:event_btnUbahBarangActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnSimpanBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanBarangActionPerformed
        simpanBarang(false);
    }//GEN-LAST:event_btnSimpanBarangActionPerformed

    private void tfHargaHppKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHargaHppKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_BACKSPACE){
            if (tfHargaHpp.getText().length() == 1){
                tfHargaHpp.setValue(0);
            }
        }
    }//GEN-LAST:event_tfHargaHppKeyPressed

    private void tfHargaJualSatuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHargaJualSatuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_BACKSPACE){
            if (tfHargaJualSatuan.getText().length() == 1){
                tfHargaJualSatuan.setValue(0);
            }
        }
    }//GEN-LAST:event_tfHargaJualSatuanKeyPressed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        konfirmasiHapusBarang();
    }//GEN-LAST:event_btnHapusActionPerformed

    // Variable declaration - able to modify
    private ArrayList<String> listWarna;
    private ArrayList<String> listArea;
    private ArrayList<String> listUkuran;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpanBarang;
    private javax.swing.JButton btnUbahBarang;
    private javax.swing.JComboBox<String> cbArea;
    private javax.swing.JComboBox<String> cbUkuran;
    private javax.swing.JComboBox<String> cbWarna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JFormattedTextField tfHargaHpp;
    private javax.swing.JFormattedTextField tfHargaJualSatuan;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfNama;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelectedRow(Object model, String fromGrid) {
        BarangModel barang = (BarangModel) model;
        setBarang(barang);
    }

    private void konfirmasiHapusBarang() {
        int option = JOptionPane.showConfirmDialog(this, "Apakah anda yakin hendak menghapus barang " + tfNama.getText() + "?", "Hapus Barang", JOptionPane.OK_CANCEL_OPTION);
        // OK = 0
        if (option == 0) {
            hapusBarang();
        }
    }

    private void hapusBarang() {
        if (new ConnectionManager().deleteBarang(tfKodeBarang.getText())) {
            JOptionPane.showMessageDialog(this, "Barang terhapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Barang gagal terhapus", "Berhasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
