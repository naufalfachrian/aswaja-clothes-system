/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.transaction;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.entity.InvoicePesanan;
import aswajaclothes.entity.Pesanan;
import aswajaclothes.entity.PesananDetail;
import aswajaclothes.grid.GridListener;
import aswajaclothes.grid.PesananGridFrame;
import aswajaclothes.pdf.PdfGenerator;
import aswajaclothes.util.CurrencyUtil;
import com.itextpdf.text.DocumentException;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Satrio
 */
public class CetakInvoicePenjualanFrame extends javax.swing.JFrame implements GridListener, MouseListener {

    /**
     * Creates new form InputOrderPenjualanFrame
     */
    public CetakInvoicePenjualanFrame() {
        initComponents();
        initDateFormat();
        initKodeInvoice();
        initFormatFieldNumber();
        initTable();
        initListOrderPenjualan();
    }
    
    private void initDateFormat(){
        chooserTanggal.setDateFormatString("dd-MM-yyyy");
        chooserTanggal.setDate(new Date());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) chooserTanggal.getDateEditor();
        editor.setEditable(false);
    }
    
    private void initKodeInvoice(){
        String kode = ConnectionManager.getKodeInvoicePesanan();
        tfNoInvoice.setText(kode);
    }
    
    private void initFormatFieldNumber(){
        // Todo
    }
    
    private void initTable(){
        tblModel = (DefaultTableModel)tblPesananDetail.getModel();
        tblPesananDetail.addMouseListener(this);
        initCellRenderAction();
    }
    
    private void initCellRenderAction(){
        // Todo
    }
    
    private void initListOrderPenjualan(){
        // Todo
    }
     
    private void calculateTotal() throws ParseException{
        // Todo
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNoInvoice = new javax.swing.JTextField();
        btnCariInvoice = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfNoPesanan = new javax.swing.JTextField();
        btnCariPesanan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfKodeCustomer = new javax.swing.JTextField();
        chooserTanggal = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfNamaCustomer = new javax.swing.JTextField();
        tfNamaEkspedisi = new javax.swing.JTextField();
        ppnCheckBox = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        tfJenisLayanan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesananDetail = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnCetak = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfSubTotal = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());
        tfOngkir = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfPPn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfTotalBayar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Invoice"));

        jLabel1.setText("No. Invoice");

        tfNoInvoice.setEnabled(false);
        tfNoInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoInvoiceActionPerformed(evt);
            }
        });

        btnCariInvoice.setText("Cari");
        btnCariInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariInvoiceActionPerformed(evt);
            }
        });

        jLabel2.setText("Tanggal");

        jLabel3.setText("No. Pesanan");

        tfNoPesanan.setEnabled(false);
        tfNoPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoPesananActionPerformed(evt);
            }
        });

        btnCariPesanan.setText("Cari");
        btnCariPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPesananActionPerformed(evt);
            }
        });

        jLabel4.setText("Kode Customer");

        tfKodeCustomer.setEnabled(false);
        tfKodeCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeCustomerActionPerformed(evt);
            }
        });

        chooserTanggal.setDateFormatString("dd-MM-yyyy");
        chooserTanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserTanggalPropertyChange(evt);
            }
        });

        jLabel8.setText("Nama Customer");

        jLabel15.setText("Nama Ekspedisi");

        tfNamaCustomer.setEnabled(false);

        tfNamaEkspedisi.setEnabled(false);

        ppnCheckBox.setText("PPn");
        ppnCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppnCheckBoxActionPerformed(evt);
            }
        });

        jLabel10.setText("Jenis Layanan");

        tfJenisLayanan.setEditable(false);
        tfJenisLayanan.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNoInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNoPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfKodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNamaEkspedisi)
                                    .addComponent(tfNamaCustomer)
                                    .addComponent(tfJenisLayanan))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCariPesanan)
                                    .addComponent(btnCariInvoice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(chooserTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ppnCheckBox))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooserTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tfNoInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCariInvoice)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfNoPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPesanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfKodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNamaEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(ppnCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfJenisLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tblPesananDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Qty", "Harga Barang"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPesananDetail.setRowHeight(20);
        jScrollPane1.setViewportView(tblPesananDetail);

        jLabel11.setText("SubTotal");

        jLabel12.setText("Ongkir");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tombol"));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel3.add(btnCetak);

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel3.add(btnKeluar);

        jLabel13.setText("Rp");

        jLabel14.setText("Rp");

        tfSubTotal.setEditable(false);
        tfSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfSubTotal.setEnabled(false);
        tfSubTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfSubTotalCaretUpdate(evt);
            }
        });

        tfOngkir.setEditable(false);
        tfOngkir.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfOngkir.setEnabled(false);

        jLabel5.setText("PPn");

        jLabel6.setText("Rp");

        tfPPn.setEditable(false);
        tfPPn.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfPPn.setEnabled(false);

        jLabel7.setText("Total Bayar");

        jLabel9.setText("Rp");

        tfTotalBayar.setEditable(false);
        tfTotalBayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfTotalBayar.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(331, 331, 331)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfSubTotal)
                                    .addComponent(tfOngkir, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(tfPPn)
                                    .addComponent(tfTotalBayar))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(tfOngkir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPPn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNoInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoInvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNoInvoiceActionPerformed

    private void btnCariInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariInvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariInvoiceActionPerformed

    private void tfNoPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNoPesananActionPerformed

    private void btnCariPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPesananActionPerformed
        PesananGridFrame pesananGridFrame = new PesananGridFrame("");
        pesananGridFrame.setGridListener(this);
        pesananGridFrame.setVisible(true);
    }//GEN-LAST:event_btnCariPesananActionPerformed

    private void tfKodeCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeCustomerActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            if (selectedPesanan == null) {
                throw new RuntimeException("Silakan pilih pesanan yang mau dicetak invoicenya.");
            }
            InvoicePesanan invoicePesanan = new InvoicePesanan();
            invoicePesanan.setKodeInvoice(tfNoInvoice.getText());
            invoicePesanan.setLunas(false);
            invoicePesanan.setPpn(ppn);
            invoicePesanan.setTanggal(chooserTanggal.getDate());
            invoicePesanan.setPesanan(selectedPesanan);
            
            ConnectionManager.getDefaultEntityManager().getTransaction().begin();
            ConnectionManager.getDefaultEntityManager().persist(invoicePesanan);
            ConnectionManager.getDefaultEntityManager().getTransaction().commit();
            
            PdfGenerator.cetakInvoicePesanan(invoicePesanan);
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void chooserTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserTanggalPropertyChange

    }//GEN-LAST:event_chooserTanggalPropertyChange

    private void tfSubTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfSubTotalCaretUpdate
        try {
            calculateTotal();
        } catch (ParseException ex) {
            Logger.getLogger(CetakInvoicePenjualanFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfSubTotalCaretUpdate

    private void ppnCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppnCheckBoxActionPerformed
        withPpn = ppnCheckBox.isSelected();
        applyPpn();
    }//GEN-LAST:event_ppnCheckBoxActionPerformed

    // Variable declarations - able to modify
    DefaultTableModel tblModel;
    boolean withPpn = false;
    Integer subTotal = 0;
    Integer ongkir = 0;
    Integer ppn = 0;
    Integer total = 0;
    private Pesanan selectedPesanan = null;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariInvoice;
    private javax.swing.JButton btnCariPesanan;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnKeluar;
    private com.toedter.calendar.JDateChooser chooserTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox ppnCheckBox;
    private javax.swing.JTable tblPesananDetail;
    private javax.swing.JTextField tfJenisLayanan;
    private javax.swing.JTextField tfKodeCustomer;
    private javax.swing.JTextField tfNamaCustomer;
    private javax.swing.JTextField tfNamaEkspedisi;
    private javax.swing.JTextField tfNoInvoice;
    private javax.swing.JTextField tfNoPesanan;
    private javax.swing.JFormattedTextField tfOngkir;
    private javax.swing.JTextField tfPPn;
    private javax.swing.JFormattedTextField tfSubTotal;
    private javax.swing.JTextField tfTotalBayar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelectedRow(Object model, String fromGrid) {
        if (fromGrid.equals(PesananGridFrame.class.getSimpleName())) {
            selectedPesanan = (Pesanan) model;
            tfNoPesanan.setText(selectedPesanan.getKodePesanan());
            tfKodeCustomer.setText(selectedPesanan.getKustomer().getKodeKustomer());
            tfNamaCustomer.setText(selectedPesanan.getKustomer().getNamaKustomer());
            tfNamaEkspedisi.setText(selectedPesanan.getEkspedisi().getNamaEkspedisi());
            tfSubTotal.setText(new CurrencyUtil().formatCurrency(selectedPesanan.getTotal()));
            tfOngkir.setText(new CurrencyUtil().formatCurrency(selectedPesanan.getOngkir()));
            tfJenisLayanan.setText(selectedPesanan.getEkspedisi().getJenisLayanan());
            subTotal = selectedPesanan.getTotal();
            ongkir = selectedPesanan.getOngkir();
            applyPpn();
            applyPesananDetail(selectedPesanan);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Todo
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Todo
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Todo
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Todo
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Todo
    }

    private void applyPpn() {
        Integer prePpn = subTotal + ongkir;
        if (withPpn) {
            ppn = prePpn * 1 / 10; // PPn 10%
        } else {
            ppn = 0;
        }
        total = subTotal + ongkir + ppn;
        tfPPn.setText(new CurrencyUtil().formatCurrency(ppn));
        tfTotalBayar.setText(new CurrencyUtil().formatCurrency(total));
    }

    private void applyPesananDetail(Pesanan pesanan) {
        List<PesananDetail> items = pesanan.getPesananDetailList();
        ArrayList<String[]> rows = new ArrayList<>();
        int count = 1;
        for (PesananDetail item : items) {
            String[] rowData = new String[]{
                String.valueOf(count),
                item.getBarang().getKodeBarang(),
                item.getBarang().getNamaBarang(),
                String.valueOf(item.getQty()),
                new CurrencyUtil().formatCurrency(item.getBarang().getHargaJualSatuan()),
                new CurrencyUtil().formatCurrency(item.getQty()* item.getBarang().getHargaJualSatuan()),
            };
            rows.add(rowData);
            count++;
        }
        tblModel = new DefaultTableModel(rows.toArray(new String[][]{}), new String[] {"No.", "Kode Barang", "Nama Barang", "Qty", "Harga Barang", "Total Per Item"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPesananDetail.setModel(tblModel);
    }
}
