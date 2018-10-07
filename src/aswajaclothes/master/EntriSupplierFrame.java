/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.master;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.grid.CustomerGridFrame;
import aswajaclothes.master.model.CustomerModel;
import aswajaclothes.common.model.KabupatenModel;
import aswajaclothes.common.model.KecamatanModel;
import aswajaclothes.common.model.KelurahanModel;
import aswajaclothes.common.model.ProvinsiModel;
import aswajaclothes.grid.GridListener;
import aswajaclothes.grid.SupplierGridFrame;
import aswajaclothes.master.model.SupplierModel;
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
public class EntriSupplierFrame extends javax.swing.JFrame implements GridListener {

    /**
     * Creates new form EntriKonsumenFrame
     */
    public EntriSupplierFrame() {
        initComponents();
        initProvinsi();
        initKodeSupplier();
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

    private void initKodeSupplier() {
        String kode = new ConnectionManager().getKodeSupplier();
        tfKodeSupplier.setText(kode);
    }

    private void simpanSupplier(Boolean isUpdate) {
        try {
            String kode = new ValidatorUtil().isEmpty(tfKodeSupplier.getText(), "Kode Supplier");
            String nama = new ValidatorUtil().isEmpty(tfNama.getText(), "Nama Supplier");
            String provinsiId = listProvinsi.get(cbProvinsi.getSelectedIndex()).getId();
            String kabupatenId = listKabupaten.get(cbKabupaten.getSelectedIndex()).getId();
            String kecamatanId = listKecamatan.get(cbKecamatan.getSelectedIndex()).getId();
            String kelurahanId = listKelurahan.get(cbKelurahan.getSelectedIndex()).getId();
            String alamat = new ValidatorUtil().isEmpty(taAlamat.getText(), "Alamat");
            String noTelepon = new ValidatorUtil().isNumber(tfNoTelepon.getText(), "No Telepon");
            String noFax = new ValidatorUtil().isNumber(tfNoFax.getText(), "No Fax");
            String email = new ValidatorUtil().isEmpty(tfEmail.getText(), "Email");
            SupplierModel supplier = new SupplierModel();
            supplier.setKode(kode);
            supplier.setName(nama);
            supplier.setProvinsiId(provinsiId);
            supplier.setKabupatenId(kabupatenId);
            supplier.setKecamatanId(kecamatanId);
            supplier.setKelurahanId(kelurahanId);
            supplier.setAlamat(alamat);
            supplier.setNoTelepon(noTelepon);
            supplier.setNoFax(noFax);
            supplier.setEmail(email);
            Boolean isResult = new ConnectionManager().saveSupplier(isUpdate, supplier);
            if (isResult) {
                JOptionPane.showMessageDialog(this, "Simpan supplier berhasil", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Simpan supplier gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void clear(){
        initKodeSupplier();
        tfNama.setText("");
        cbProvinsi.setSelectedIndex(0);
        tfNoTelepon.setText("");
        taAlamat.setText("");
        tfNoFax.setText("");
        tfEmail.setText("");
    }
    
    private void setSupplier(SupplierModel supplier){
        tfKodeSupplier.setText(supplier.getKode());
        tfNama.setText(supplier.getName());
        tfNoTelepon.setText(supplier.getNoTelepon());
        taAlamat.setText(supplier.getAlamat());
        cbProvinsi.setSelectedIndex(findProvinsiById(supplier.getProvinsiId()));
        cbKabupaten.setSelectedIndex(findKabupatenById(supplier.getKabupatenId()));
        cbKecamatan.setSelectedIndex(findKecamatanById(supplier.getKecamatanId()));
        cbKelurahan.setSelectedIndex(findKelurahanById(supplier.getKelurahanId()));
        tfNoFax.setText(supplier.getNoFax());
        tfEmail.setText(supplier.getEmail());
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
        tfKodeSupplier = new javax.swing.JTextField();
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
        jLabel9 = new javax.swing.JLabel();
        tfNoFax = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Supplier");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Supplier"));

        jLabel1.setText("Kode Supplier");

        tfKodeSupplier.setEnabled(false);
        tfKodeSupplier.setSize(new java.awt.Dimension(100, 26));
        tfKodeSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeSupplierActionPerformed(evt);
            }
        });

        btnCariCustomer.setText("Cari");
        btnCariCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariCustomerActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Supplier");

        tfNama.setSize(new java.awt.Dimension(100, 26));
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
        taAlamat.setBounds(new java.awt.Rectangle(0, 0, 243, 80));
        jScrollPane1.setViewportView(taAlamat);

        jLabel8.setText("No Telepon");

        tfNoTelepon.setSize(new java.awt.Dimension(100, 26));
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

        jLabel9.setText("No Fax");

        tfNoFax.setSize(new java.awt.Dimension(100, 26));
        tfNoFax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoFaxActionPerformed(evt);
            }
        });

        jLabel10.setText("Email");

        tfEmail.setSize(new java.awt.Dimension(100, 26));
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
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
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfEmail)
                        .addGap(115, 115, 115))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbKabupaten, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbKecamatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbKelurahan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfNama)
                                    .addComponent(cbProvinsi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfKodeSupplier))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariCustomer))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                    .addComponent(tfNoTelepon))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariNoTelepon)))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfNoFax)
                        .addGap(115, 115, 115))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfNoFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
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

    private void tfKodeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeSupplierActionPerformed

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
        SupplierGridFrame supplierGrid = new SupplierGridFrame("");
        supplierGrid.setGridListener(this);
        supplierGrid.setVisible(true);
    }//GEN-LAST:event_btnCariCustomerActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        simpanSupplier(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpanSupplier(false);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCariNoTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariNoTeleponActionPerformed
        SupplierGridFrame supplierGrid = new SupplierGridFrame(tfNoTelepon.getText());
        supplierGrid.setGridListener(this);
        supplierGrid.setVisible(true);
    }//GEN-LAST:event_btnCariNoTeleponActionPerformed

    private void tfNoFaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoFaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNoFaxActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    // Variable declaration - able to modify
    private ArrayList<ProvinsiModel> listProvinsi;
    private ArrayList<KabupatenModel> listKabupaten;
    private ArrayList<KecamatanModel> listKecamatan;
    private ArrayList<KelurahanModel> listKelurahan;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariCustomer;
    private javax.swing.JButton btnCariNoTelepon;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbKabupaten;
    private javax.swing.JComboBox<String> cbKecamatan;
    private javax.swing.JComboBox<String> cbKelurahan;
    private javax.swing.JComboBox<String> cbProvinsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfKodeSupplier;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNoFax;
    private javax.swing.JTextField tfNoTelepon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelectedRow(Object model) {
        SupplierModel supplier = (SupplierModel) model;
        setSupplier(supplier);
    }
}
