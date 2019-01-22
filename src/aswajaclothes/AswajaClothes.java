/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aswajaclothes;

import aswajaclothes.laporan.CetakLaporanBarangTerlaris;
import aswajaclothes.laporan.CetakLaporanCostRevenue;
import aswajaclothes.laporan.CetakLaporanPembelian;
import aswajaclothes.laporan.CetakLaporanPenjualan;
import aswajaclothes.master.EntriBarangFrame;
import aswajaclothes.master.EntriEkspedisiFrame;
import aswajaclothes.master.EntriKonsumenFrame;
import aswajaclothes.master.EntriSupplierFrame;
import aswajaclothes.transaction.CetakInvoicePenjualanFrame;
import aswajaclothes.transaction.CetakInvoicePembelianFrame;
import aswajaclothes.transaction.CetakSuratJalanFrame;
import aswajaclothes.transaction.InputBuktiPembayaranFrame;
import aswajaclothes.transaction.InputBuktiPenerimaanFrame;
import aswajaclothes.transaction.InputOrderPembelianFrame;
import aswajaclothes.transaction.InputOrderPenjualanFrame;
import aswajaclothes.transaction.ReturPembelianFrame;
import aswajaclothes.transaction.ReturPenjualanFrame;
import javax.swing.JFrame;

/**
 *
 * @author Satrio
 */
public class AswajaClothes extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public AswajaClothes() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        logo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnMasterKonsumen = new javax.swing.JMenuItem();
        mnMasterBarang = new javax.swing.JMenuItem();
        mnMasterSupplier = new javax.swing.JMenuItem();
        mnMasterEkspedisi = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnKeluar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnInputOrderPenjualan = new javax.swing.JMenuItem();
        mnInputBuktiPembayaran = new javax.swing.JMenuItem();
        mnCetakInvoicePenjualan = new javax.swing.JMenuItem();
        mnCetakSuratJalan = new javax.swing.JMenuItem();
        mnReturPenjualan = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mnInputOrderPembelian = new javax.swing.JMenuItem();
        mnCetakOrderPembelian = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuInputBuktiPenerimaanBarang = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Utama");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aswajaclothes/logo.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(130, 180, 132, 175);
        getContentPane().add(logo, gridBagConstraints);

        jMenu1.setText("Master");

        mnMasterKonsumen.setText("Master Entri Konsumen");
        mnMasterKonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMasterKonsumenActionPerformed(evt);
            }
        });
        jMenu1.add(mnMasterKonsumen);

        mnMasterBarang.setText("Master Entri Barang");
        mnMasterBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMasterBarangActionPerformed(evt);
            }
        });
        jMenu1.add(mnMasterBarang);

        mnMasterSupplier.setText("Master Entri Supplier");
        mnMasterSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMasterSupplierActionPerformed(evt);
            }
        });
        jMenu1.add(mnMasterSupplier);

        mnMasterEkspedisi.setText("Master Entri Ekspedisi");
        mnMasterEkspedisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMasterEkspedisiActionPerformed(evt);
            }
        });
        jMenu1.add(mnMasterEkspedisi);
        jMenu1.add(jSeparator1);

        mnKeluar.setText("Keluar");
        mnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnKeluarActionPerformed(evt);
            }
        });
        jMenu1.add(mnKeluar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transaksi Penjualan");

        mnInputOrderPenjualan.setText("Input Order Penjualan");
        mnInputOrderPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInputOrderPenjualanActionPerformed(evt);
            }
        });
        jMenu2.add(mnInputOrderPenjualan);

        mnInputBuktiPembayaran.setText("Input Bukti Pembayaran");
        mnInputBuktiPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInputBuktiPembayaranActionPerformed(evt);
            }
        });
        jMenu2.add(mnInputBuktiPembayaran);

        mnCetakInvoicePenjualan.setText("Cetak Invoice Penjualan");
        mnCetakInvoicePenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCetakInvoicePenjualanActionPerformed(evt);
            }
        });
        jMenu2.add(mnCetakInvoicePenjualan);

        mnCetakSuratJalan.setText("Cetak Surat Jalan");
        mnCetakSuratJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCetakSuratJalanActionPerformed(evt);
            }
        });
        jMenu2.add(mnCetakSuratJalan);

        mnReturPenjualan.setText("Retur Penjualan");
        mnReturPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnReturPenjualanActionPerformed(evt);
            }
        });
        jMenu2.add(mnReturPenjualan);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Transaksi Pembelian");

        mnInputOrderPembelian.setText("Input Order Pembelian");
        mnInputOrderPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInputOrderPembelianActionPerformed(evt);
            }
        });
        jMenu4.add(mnInputOrderPembelian);

        mnCetakOrderPembelian.setText("Cetak Order Pembelian");
        mnCetakOrderPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCetakOrderPembelianActionPerformed(evt);
            }
        });
        jMenu4.add(mnCetakOrderPembelian);

        jMenuItem5.setText("Retur Pembelian");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        menuInputBuktiPenerimaanBarang.setText("Input Bukti Penerimaan Barang");
        menuInputBuktiPenerimaanBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInputBuktiPenerimaanBarangActionPerformed(evt);
            }
        });
        jMenu4.add(menuInputBuktiPenerimaanBarang);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Report");

        jMenuItem12.setText("Cetak Laporan Penjualan");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Cetak Laporan Pembelian");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem1.setText("Cetak Laporan Cost Revenue");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem4.setText("Cetak Laporan Barang Terlaris");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnMasterKonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMasterKonsumenActionPerformed
        new EntriKonsumenFrame().setVisible(true);
    }//GEN-LAST:event_mnMasterKonsumenActionPerformed

    private void mnMasterBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMasterBarangActionPerformed
        new EntriBarangFrame().setVisible(true);
    }//GEN-LAST:event_mnMasterBarangActionPerformed

    private void mnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnKeluarActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_mnKeluarActionPerformed

    private void mnMasterSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMasterSupplierActionPerformed
        new EntriSupplierFrame().setVisible(true);
    }//GEN-LAST:event_mnMasterSupplierActionPerformed

    private void mnMasterEkspedisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMasterEkspedisiActionPerformed
        new EntriEkspedisiFrame().setVisible(true);
    }//GEN-LAST:event_mnMasterEkspedisiActionPerformed

    private void mnInputOrderPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInputOrderPenjualanActionPerformed
        new InputOrderPenjualanFrame().setVisible(true);
    }//GEN-LAST:event_mnInputOrderPenjualanActionPerformed

    private void mnCetakInvoicePenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCetakInvoicePenjualanActionPerformed
        new CetakInvoicePenjualanFrame().setVisible(true);
    }//GEN-LAST:event_mnCetakInvoicePenjualanActionPerformed

    private void mnInputBuktiPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInputBuktiPembayaranActionPerformed
        new InputBuktiPembayaranFrame().setVisible(true);
    }//GEN-LAST:event_mnInputBuktiPembayaranActionPerformed

    private void mnInputOrderPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInputOrderPembelianActionPerformed
        new InputOrderPembelianFrame().setVisible(true);
    }//GEN-LAST:event_mnInputOrderPembelianActionPerformed

    private void mnCetakOrderPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCetakOrderPembelianActionPerformed
        new CetakInvoicePembelianFrame().setVisible(true);
    }//GEN-LAST:event_mnCetakOrderPembelianActionPerformed

    private void mnReturPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnReturPenjualanActionPerformed
        new ReturPenjualanFrame().setVisible(true);
    }//GEN-LAST:event_mnReturPenjualanActionPerformed

    private void mnCetakSuratJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCetakSuratJalanActionPerformed
        new CetakSuratJalanFrame().setVisible(true);
    }//GEN-LAST:event_mnCetakSuratJalanActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new CetakLaporanPenjualan().setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        new CetakLaporanPembelian().setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new CetakLaporanCostRevenue().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new CetakLaporanBarangTerlaris().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new ReturPembelianFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void menuInputBuktiPenerimaanBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInputBuktiPenerimaanBarangActionPerformed
        new InputBuktiPenerimaanFrame().setVisible(true);
    }//GEN-LAST:event_menuInputBuktiPenerimaanBarangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AswajaClothes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AswajaClothes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AswajaClothes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AswajaClothes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AswajaClothes().setVisible(true);
            }
        });
    }
  
    //Variable declaration - able to modify
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel logo;
    private javax.swing.JMenuItem menuInputBuktiPenerimaanBarang;
    private javax.swing.JMenuItem mnCetakInvoicePenjualan;
    private javax.swing.JMenuItem mnCetakOrderPembelian;
    private javax.swing.JMenuItem mnCetakSuratJalan;
    private javax.swing.JMenuItem mnInputBuktiPembayaran;
    private javax.swing.JMenuItem mnInputOrderPembelian;
    private javax.swing.JMenuItem mnInputOrderPenjualan;
    private javax.swing.JMenuItem mnKeluar;
    private javax.swing.JMenuItem mnMasterBarang;
    private javax.swing.JMenuItem mnMasterEkspedisi;
    private javax.swing.JMenuItem mnMasterKonsumen;
    private javax.swing.JMenuItem mnMasterSupplier;
    private javax.swing.JMenuItem mnReturPenjualan;
    // End of variables declaration//GEN-END:variables

}
