/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.grid;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.entity.InvoicePesanan;
import aswajaclothes.entity.PesananDetail;
import aswajaclothes.util.CurrencyUtil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Satrio
 */
public class InvoicePesananGridFrame extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form CustomerGridFrame
     * @param valueFilter
     */
    public InvoicePesananGridFrame(String valueFilter) {
        initComponents();
        initDaftarPenjualan(valueFilter);
        initTableListener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesanan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Pembelian");

        tblPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode", "Nama", "Alamat", "No Telepon"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblPesanan.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPesanan);

        jLabel1.setText("Nama");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCari)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        if (tfNama.getText().isEmpty()){
            initDaftarPenjualan("");
        } else {
            initDaftarPenjualan(tfNama.getText());
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void initDaftarPenjualan(String valueFilter) {
        String[] customerColumn = new String [] { "No. Invoice", "Nama Kustomer", "Jumlah Barang", "Total Bayar"};
        ArrayList<String[]> rows = new ArrayList<>();
        items = ConnectionManager.getDefaultEntityManager().createNamedQuery("InvoicePesanan.findAll", InvoicePesanan.class).getResultList();
        
        for (InvoicePesanan item : items) {
            int totalQty = 0;
            int totalBayar = 0;
            for (PesananDetail pesananDetail : item.getPesanan().getPesananDetailList()) {
                totalQty += pesananDetail.getQty();
                totalBayar += (pesananDetail.getQty() * pesananDetail.getBarang().getHargaJualSatuan());
            }
            
            String[] rowData = new String[]{
                item.getKodeInvoice(),
                item.getPesanan().getKustomer().getNamaKustomer(),
                String.valueOf(totalQty),
                CurrencyUtil.getInstance().formatCurrency(totalBayar),
            };
            rows.add(rowData);
        }
        TableModel tblModel = new DefaultTableModel(rows.toArray(new String[][]{}), customerColumn){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPesanan.setModel(tblModel);
    }
    
    private void initTableListener(){
        tblPesanan.addMouseListener(this);
    }
    
    // Variables declaration - able to modify
    private List<InvoicePesanan> items;
    private GridListener listener;
    private String noTelepon;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPesanan;
    private javax.swing.JTextField tfNama;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowSelected = tblPesanan.getSelectedRow();
        InvoicePesanan pembelian  = items.get(rowSelected);
        listener.onSelectedRow(pembelian, InvoicePesananGridFrame.class.getSimpleName());
        dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void setGridListener(GridListener listener){
        this.listener = listener;
    }
    
}
