/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.grid;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.util.CurrencyUtil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Satrio
 */
public class BarangGridFrame extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form CustomerGridFrame
     */
    public BarangGridFrame(String filterByNama) {
        initComponents();
        initBarangs(filterByNama);
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
        tblBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Barang");

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama", "Warna", "Area", "Ukuran", "Harga HPP", "Harga Jual Satuan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBarang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblBarang);

        jLabel1.setText("Nama");

        tfNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaActionPerformed(evt);
            }
        });

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tfNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        if (tfNama.getText().isEmpty()){
            initBarangs("");
        } else {
            initBarangs(tfNama.getText());
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void initBarangs(String byNama) {
        String[] barangColumn = new String [] { "Kode", "Nama", "Warna", "Area",
            "Ukuran", "Harga HPP", "Harga Jual Satuan"};
        ArrayList<String[]> barangsRow = new ArrayList<>();
        if (byNama.isEmpty()) {
           listBarang = new ConnectionManager().getBarangs();
        } else {
            listBarang = new ConnectionManager().getBarangsByNama(byNama);
        }
        for (BarangModel barang : listBarang) {
            String[] rowData = new String[]{
                barang.getKode(),
                barang.getName(),
                barang.getWarna(),
                barang.getArea(),
                barang.getUkuran(),
                new CurrencyUtil().formatCurrency(barang.getHargaHPP()),
                new CurrencyUtil().formatCurrency(barang.getHargaJualSatuan())
            };
            barangsRow.add(rowData);
        }
        TableModel tblModel = new DefaultTableModel(barangsRow.toArray(new String[][]{}), barangColumn){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblBarang.setModel(tblModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tblBarang.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        tblBarang.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
    }
    
    private void initTableListener(){
        tblBarang.addMouseListener(this);
    }
    
    // Variables declaration - able to modify
    private ArrayList<BarangModel> listBarang;
    private GridListener listener;
    private String namaBarang;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField tfNama;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowSelected = tblBarang.getSelectedRow();
        BarangModel barang = listBarang.get(rowSelected);
        listener.onSelectedRow(barang, BarangGridFrame.class.getSimpleName());
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
