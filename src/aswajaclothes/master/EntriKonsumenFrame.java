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
import aswajaclothes.grid.GridListener;
import aswajaclothes.util.FilterUtil;
import aswajaclothes.util.ValidatorUtil;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Satrio
 */
public class EntriKonsumenFrame extends javax.swing.JFrame implements GridListener {

    /**
     * Creates new form EntriKonsumenFrame
     */
    public EntriKonsumenFrame() {
        initComponents();
        initProvinsi();
        initKodeCustomer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initProvinsi() {
        listProvinsi = new ConnectionManager().getProvinsi();
        for (ProvinsiModel provinsi : listProvinsi) {
            cbProvinsi.addItem(provinsi.getName());
        }
    }

    private void initKabupaten(String provinsiId) {
        listKabupaten = new ConnectionManager().getKabupten(provinsiId);
        if (cbKabupaten.getModel().getSize() > 0) {
            ((DefaultComboBoxModel) cbKabupaten.getModel()).removeAllElements();
        }
        for (KabupatenModel kabupaten : listKabupaten) {
            cbKabupaten.addItem(kabupaten.getName());
        }

    }

    private void initKecamatan(String kabupatenId) {
        listKecamatan = new ConnectionManager().getKecamatan(kabupatenId);
        if (cbKecamatan.getModel().getSize() > 0) {
            ((DefaultComboBoxModel) cbKecamatan.getModel()).removeAllElements();
        }
        for (KecamatanModel kecamatan : listKecamatan) {
            cbKecamatan.addItem(kecamatan.getName());
        }
    }

    private void initKelurahan(String kecamatanId) {
        listKelurahan = new ConnectionManager().getKelurahan(kecamatanId);
        if (cbKelurahan.getModel().getSize() > 0) {
            ((DefaultComboBoxModel) cbKelurahan.getModel()).removeAllElements();
        }
        for (KelurahanModel kelurahan : listKelurahan) {
            cbKelurahan.addItem(kelurahan.getName());
        }
    }

    private void initKodeCustomer() {
        String kode = new ConnectionManager().getKodeCustomer();
        tfKodeCustomer.setText(kode);
    }

    private void simpanCustomer(Boolean isUpdate) {
        try {
            String kode = new ValidatorUtil().isEmpty(tfKodeCustomer.getText(), "Kode Kustomer");
            String nama = new ValidatorUtil().isEmpty(tfNama.getText(), "Nama Kustomer");
            String provinsiId = listProvinsi.get(cbProvinsi.getSelectedIndex()).getId();
            String kabupatenId = listKabupaten.get(cbKabupaten.getSelectedIndex()).getId();
            String kecamatanId = listKecamatan.get(cbKecamatan.getSelectedIndex()).getId();
            String kelurahanId = listKelurahan.get(cbKelurahan.getSelectedIndex()).getId();
            String alamat = new ValidatorUtil().isEmpty(taAlamat.getText(), "Alamat");
            String noTelepon = new ValidatorUtil().isNumber(tfNoTelepon.getText(), "No Telepon");
            CustomerModel customer = new CustomerModel();
            customer.setKode(kode);
            customer.setName(nama);
            customer.setProvinsiId(provinsiId);
            customer.setKabupatenId(kabupatenId);
            customer.setKecamatanId(kecamatanId);
            customer.setKelurahanId(kelurahanId);
            customer.setAlamat(alamat);
            customer.setNoTelepon(noTelepon);
            Boolean isResult = new ConnectionManager().saveCustomer(isUpdate, customer);
            if (isResult) {
                JOptionPane.showMessageDialog(this, "Simpan kustomer berhasil", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Simpan kustomer gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void clear(){
        initKodeCustomer();
        tfNama.setText("");
        cbProvinsi.setSelectedIndex(0);
        tfNoTelepon.setText("");
        taAlamat.setText("");
    }
    
    private void setCustomer(CustomerModel customer){
        tfKodeCustomer.setText(customer.getKode());
        tfNama.setText(customer.getName());
        tfNoTelepon.setText(customer.getNoTelepon());
        taAlamat.setText(customer.getAlamat());
        cbProvinsi.setSelectedIndex(findProvinsiById(customer.getProvinsiId()));
        cbKabupaten.setSelectedIndex(findKabupatenById(customer.getKabupatenId()));
        cbKecamatan.setSelectedIndex(findKecamatanById(customer.getKecamatanId()));
        cbKelurahan.setSelectedIndex(findKelurahanById(customer.getKelurahanId()));
    }
    
    private int findProvinsiById(String provinsiId){
        int index = 0;
        for(int i = 0; i < listProvinsi.size(); i++){
            if (listProvinsi.get(i).getId().equals(provinsiId)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    private int findKabupatenById(String kabupatenId){
        int index = 0;
        for(int i = 0; i < listKabupaten.size(); i++){
            if (listKabupaten.get(i).getId().equals(kabupatenId)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    private int findKecamatanById(String kecamatanId){
        int index = 0;
        for(int i = 0; i < listKecamatan.size(); i++){
            if (listKecamatan.get(i).getId().equals(kecamatanId)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    private int findKelurahanById(String kelurahanId){
        int index = 0;
        for(int i = 0; i < listKelurahan.size(); i++){
            if (listKelurahan.get(i).getId().equals(kelurahanId)){
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
        tfKodeCustomer = new javax.swing.JTextField();
        btnCariCustomer = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbProvinsi = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbKabupaten = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbKecamatan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbKelurahan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        tfNoTelepon = new javax.swing.JTextField();
        btnCariNoTelepon = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Customer");
        setPreferredSize(new java.awt.Dimension(550, 500));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Customer"));

        jLabel1.setText("Kode Customer");

        tfKodeCustomer.setEnabled(false);
        tfKodeCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeCustomerActionPerformed(evt);
            }
        });

        btnCariCustomer.setText("Cari");
        btnCariCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariCustomerActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Customer");

        tfNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaActionPerformed(evt);
            }
        });

        jLabel3.setText("Provinsi");

        cbProvinsi.setName("cbProvinsi"); // NOI18N
        cbProvinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProvinsiActionPerformed(evt);
            }
        });

        jLabel4.setText("Kabupaten");

        cbKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKabupatenActionPerformed(evt);
            }
        });

        jLabel5.setText("Kecamatan");

        cbKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKecamatanActionPerformed(evt);
            }
        });

        jLabel6.setText("Kelurahan");

        cbKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKelurahanActionPerformed(evt);
            }
        });

        jLabel7.setText("Alamat");

        taAlamat.setColumns(20);
        taAlamat.setRows(5);
        jScrollPane1.setViewportView(taAlamat);

        jLabel8.setText("No Telepon");

        tfNoTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoTeleponActionPerformed(evt);
            }
        });

        btnCariNoTelepon.setText("Cari");
        btnCariNoTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariNoTeleponActionPerformed(evt);
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
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKabupaten, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbKecamatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbKelurahan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNama)
                            .addComponent(cbProvinsi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfKodeCustomer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariCustomer))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(tfNoTelepon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariNoTelepon)))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfKodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbKabupaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnCariNoTelepon))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tombol"));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpan);

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        jPanel2.add(btnUbah);

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnHapus);

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel2.add(btnBatal);

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel2.add(btnKeluar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodeCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeCustomerActionPerformed

    private void tfNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaActionPerformed

    private void cbProvinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProvinsiActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        ProvinsiModel provinsi = listProvinsi.get(cb.getSelectedIndex());
        initKabupaten(provinsi.getId());
    }//GEN-LAST:event_cbProvinsiActionPerformed

    private void cbKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKabupatenActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        if (cb.getSelectedIndex() >= 0) {
            KabupatenModel kabupaten = listKabupaten.get(cb.getSelectedIndex());
            initKecamatan(kabupaten.getId());
        }
    }//GEN-LAST:event_cbKabupatenActionPerformed

    private void cbKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKecamatanActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        if (cb.getSelectedIndex() >= 0) {
            KecamatanModel kecamatan = listKecamatan.get(cb.getSelectedIndex());
            initKelurahan(kecamatan.id);
        }
    }//GEN-LAST:event_cbKecamatanActionPerformed

    private void cbKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKelurahanActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        if (cb.getSelectedIndex() >= 0) {
            KelurahanModel kelurahan = listKelurahan.get(cb.getSelectedIndex());
        }
    }//GEN-LAST:event_cbKelurahanActionPerformed

    private void tfNoTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoTeleponActionPerformed
        
    }//GEN-LAST:event_tfNoTeleponActionPerformed

    private void btnCariCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariCustomerActionPerformed
        CustomerGridFrame customerGrid = new CustomerGridFrame(FilterUtil.FilterType.NONE, "");
        customerGrid.setGridListener(this);
        customerGrid.setVisible(true);
    }//GEN-LAST:event_btnCariCustomerActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        simpanCustomer(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpanCustomer(false);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCariNoTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariNoTeleponActionPerformed
        CustomerGridFrame customerGrid = new CustomerGridFrame(FilterUtil.FilterType.NO_TELEPON, tfNoTelepon.getText());
        customerGrid.setGridListener(this);
        customerGrid.setVisible(true);
    }//GEN-LAST:event_btnCariNoTeleponActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        konfirmasiHapusCustomer();
    }//GEN-LAST:event_btnHapusActionPerformed

    // Variable declaration - able to modify
    private ArrayList<ProvinsiModel> listProvinsi;
    private ArrayList<KabupatenModel> listKabupaten;
    private ArrayList<KecamatanModel> listKecamatan;
    private ArrayList<KelurahanModel> listKelurahan;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariCustomer;
    private javax.swing.JButton btnCariNoTelepon;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbKabupaten;
    private javax.swing.JComboBox<String> cbKecamatan;
    private javax.swing.JComboBox<String> cbKelurahan;
    private javax.swing.JComboBox<String> cbProvinsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextField tfKodeCustomer;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNoTelepon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelectedRow(Object model, String fromGrid) {
        CustomerModel customer = (CustomerModel) model;
        setCustomer(customer);
    }

    private void konfirmasiHapusCustomer() {
        int option = JOptionPane.showConfirmDialog(this, "Apakah anda yakin hendak menghapus konsumen " + tfNama.getText() + "?", "Hapus Konsumen", JOptionPane.OK_CANCEL_OPTION);
        // OK = 0
        if (option == 0) {
            hapusCustomer();
        }
    }

    private void hapusCustomer() {
        if (new ConnectionManager().deleteCustomer(tfKodeCustomer.getText())) {
            JOptionPane.showMessageDialog(this, "Konsumen terhapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Konsumen gagal terhapus", "Berhasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
