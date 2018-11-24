/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.transaction;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.grid.BarangGridFrame;
import aswajaclothes.grid.CustomerGridFrame;
import aswajaclothes.grid.EkspedisiGridFrame;
import aswajaclothes.grid.GridListener;
import aswajaclothes.grid.PesananGridFrame;
import aswajaclothes.grid.SupplierGridFrame;
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.model.master.EkspedisiModel;
import aswajaclothes.model.master.ItemPesananModel;
import aswajaclothes.model.master.PesananModel;
import aswajaclothes.model.master.SupplierModel;
import aswajaclothes.model.transaction.InputOrderPenjualanDetailModel;
import aswajaclothes.model.transaction.InputOrderPenjualanModel;
import aswajaclothes.util.Config;
import aswajaclothes.util.CurrencyUtil;
import aswajaclothes.util.FilterUtil;
import aswajaclothes.util.ValidatorUtil;
import aswajaclothes.widget.ButtonCell;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Satrio
 */
public class InputOrderPembelianFrame extends javax.swing.JFrame implements GridListener, MouseListener {

    /**
     * Creates new form InputOrderPenjualanFrame
     */
    public InputOrderPembelianFrame() {
        initComponents();
        initDateFormat();
        initKodePembelian();
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
    
    private void initKodePembelian(){
        kodePembelian = new ConnectionManager().getKodePembelian();
        tfKodePembelian.setText(kodePembelian);
    }
    
    private void initFormatFieldNumber(){
        // Todo
    }
    
    private void initTable(){
        tblModel = (DefaultTableModel)tblPesananDetail.getModel();
        tblPesananDetail.addMouseListener(this);
        tblPesananDetail.setModel(tblModel);
        initCellRenderAction();
    }
    
    private void initCellRenderAction(){
        tblPesananDetail.getColumnModel().getColumn(5).setCellRenderer(new ButtonCell());
    }
    
    private void initListOrderPenjualan(){
        listOrderPenjualanDetail = new ArrayList<>();
    }
    
    private void clearSupplier(){
        kodeSupplier = null;
        kodeEkspedisi = null;
        clear(new JTextField[] {
            tfSupplierKodeEkspedisi,
            tfSupplierKodeSupplier,
            tfSupplierNamaEkspedisi,
            tfSupplierNamaSupplier,
            tfSupplierNomorTelepon,
        });
        tfSupplierAlamat.setText("");
    }
    
    private void clearPesanan(){
        kodePesanan = null;
        clear(new JTextField[] {
            tfPesananKodeKustomer,
            tfPesananKodePesanan,
            tfPesananNamaKustomer,
            tfPesananTanggalPesanan
        });
    }
    
    private void clearAll(){
        clearPesanan();
        clearSupplier();
        initKodePembelian();
        bersihPemesananTable();
    }
    
    private void tambah() throws ParseException, Exception{
        // Todo
    }

    private boolean isDuplikasiBarang(InputOrderPenjualanDetailModel model, boolean isUpdate){
        boolean isDuplikasi = false;
        for(InputOrderPenjualanDetailModel obj : listOrderPenjualanDetail) {
            if (obj.getKodeBarang().equals(model.getKodeBarang())){
                if (isUpdate == false){
                   int currQty = obj.getQty();
                   int accumulateQty = currQty + model.getQty();
                   obj.setQty(accumulateQty);
                } else {
                    obj.setQty(model.getQty());
                }
                isDuplikasi = true;
                break;
            }
        }
        return isDuplikasi;
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
        tfKodePembelian = new javax.swing.JTextField();
        btnCariPesanan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        chooserTanggal = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfSupplierKodeSupplier = new javax.swing.JTextField();
        btnCariSupplier = new javax.swing.JButton();
        tfSupplierNamaSupplier = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfSupplierKodeEkspedisi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfSupplierNamaEkspedisi = new javax.swing.JTextField();
        btnCariEkspedisi = new javax.swing.JButton();
        tfSupplierNomorTelepon = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfSupplierAlamat = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesananDetail = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfPesananKodePesanan = new javax.swing.JTextField();
        tfPesananKodeKustomer = new javax.swing.JTextField();
        tfPesananNamaKustomer = new javax.swing.JTextField();
        btnCariPemesanan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tfPesananTanggalPesanan = new javax.swing.JTextField();
        btnBersihPemesanan = new javax.swing.JButton();
        btnTambahPemesanan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pembelian"));

        jLabel1.setText("No Pembelian");

        tfKodePembelian.setEnabled(false);
        tfKodePembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodePembelianActionPerformed(evt);
            }
        });

        btnCariPesanan.setText("Cari");
        btnCariPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPesananActionPerformed(evt);
            }
        });

        jLabel2.setText("Tanggal");

        chooserTanggal.setDateFormatString("dd-MM-yyyy");
        chooserTanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserTanggalPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(tfKodePembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnCariPesanan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(chooserTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooserTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tfKodePembelian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCariPesanan)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Supplier"));

        jLabel5.setText("Kode Supplier");

        tfSupplierKodeSupplier.setEnabled(false);
        tfSupplierKodeSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierKodeSupplierActionPerformed(evt);
            }
        });

        btnCariSupplier.setText("Cari");
        btnCariSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariSupplierActionPerformed(evt);
            }
        });

        tfSupplierNamaSupplier.setEnabled(false);
        tfSupplierNamaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierNamaSupplierActionPerformed(evt);
            }
        });

        jLabel6.setText("Nama Supplier");

        jLabel7.setText("Nomor Telepon");

        jLabel3.setText("Alamat");

        jLabel4.setText("Kode Ekspedisi");

        tfSupplierKodeEkspedisi.setEditable(false);
        tfSupplierKodeEkspedisi.setEnabled(false);

        jLabel8.setText("Nama Ekspedisi");

        tfSupplierNamaEkspedisi.setEditable(false);
        tfSupplierNamaEkspedisi.setEnabled(false);

        btnCariEkspedisi.setText("Cari");
        btnCariEkspedisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariEkspedisiActionPerformed(evt);
            }
        });

        tfSupplierNomorTelepon.setEditable(false);
        tfSupplierNomorTelepon.setEnabled(false);

        tfSupplierAlamat.setEditable(false);
        tfSupplierAlamat.setColumns(20);
        tfSupplierAlamat.setRows(5);
        tfSupplierAlamat.setEnabled(false);
        jScrollPane2.setViewportView(tfSupplierAlamat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfSupplierKodeSupplier)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfSupplierNamaEkspedisi, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfSupplierKodeEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCariEkspedisi)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariSupplier))
                            .addComponent(tfSupplierNamaSupplier)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfSupplierNomorTelepon)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfSupplierKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfSupplierNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfSupplierNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSupplierKodeEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnCariEkspedisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSupplierNamaEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPesananDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "No. Pesanan", "Kode Customer", "Qty", "Total Harga", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tombol"));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel3.add(btnSimpan);

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel3.add(btnBatal);

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel3.add(btnKeluar);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pemesanan"));

        jLabel9.setText("No. Pesanan");

        jLabel10.setText("Kode Customer");

        jLabel11.setText("Nama Customer");

        tfPesananKodePesanan.setEditable(false);
        tfPesananKodePesanan.setEnabled(false);

        tfPesananKodeKustomer.setEditable(false);
        tfPesananKodeKustomer.setEnabled(false);

        tfPesananNamaKustomer.setEditable(false);
        tfPesananNamaKustomer.setEnabled(false);

        btnCariPemesanan.setText("Cari");
        btnCariPemesanan.setToolTipText("");
        btnCariPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPemesananActionPerformed(evt);
            }
        });

        jLabel12.setText("Tanggal");

        tfPesananTanggalPesanan.setEditable(false);
        tfPesananTanggalPesanan.setEnabled(false);

        btnBersihPemesanan.setText("Bersih");
        btnBersihPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihPemesananActionPerformed(evt);
            }
        });

        btnTambahPemesanan.setText("Tambah");
        btnTambahPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPemesananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesananNamaKustomer))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(tfPesananKodePesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariPemesanan))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfPesananKodeKustomer)))))
                .addGap(73, 73, 73)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPesananTanggalPesanan)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTambahPemesanan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBersihPemesanan))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesananKodePesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPemesanan)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(tfPesananTanggalPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesananKodeKustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesananNamaKustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBersihPemesanan)
                    .addComponent(btnTambahPemesanan)))
        );

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
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodePembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodePembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodePembelianActionPerformed

    private void btnCariPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariPesananActionPerformed

    private void tfSupplierKodeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierKodeSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierKodeSupplierActionPerformed

    private void btnCariSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSupplierActionPerformed
        SupplierGridFrame frame = new SupplierGridFrame(FilterUtil.FilterType.NONE, "");
        frame.setGridListener(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCariSupplierActionPerformed

    private void tfSupplierNamaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierNamaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierNamaSupplierActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (simpanInputOrderPembelian()) {
            inputOrderPembelianTersimpan();
        } else {
            inputOrderPembelianGagalTersimpan();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearAll();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void chooserTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserTanggalPropertyChange

    }//GEN-LAST:event_chooserTanggalPropertyChange

    private void btnCariEkspedisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariEkspedisiActionPerformed
        EkspedisiGridFrame frame = new EkspedisiGridFrame(FilterUtil.FilterType.NONE, "");
        frame.setGridListener(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCariEkspedisiActionPerformed

    private void btnCariPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPemesananActionPerformed
        PesananGridFrame frame = new PesananGridFrame("");
        frame.setGridListener(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCariPemesananActionPerformed

    private void btnTambahPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPemesananActionPerformed
        PesananModel pesanan = new ConnectionManager().getDaftarPesananById(kodePesanan);
        if (!kodePesananHasBeenSelected()) {
            List<ItemPesananModel> items = new ConnectionManager().getDaftarPesananItem(pesanan.getKodePesanan());
            tblModel.addRow(new String[] {
                String.valueOf((tblModel.getRowCount() + 1)),
                pesanan.getKodePesanan(),
                pesanan.getNamaKustomer(),
                String.valueOf(items.size()),
                new CurrencyUtil().formatCurrency(pesanan.getTotal()),
                "Hapus"
            });
            selectedKodePesanan.add(kodePesanan);
        } else {
            JOptionPane.showMessageDialog(this, String.format("Pesanan telah %s atas nama %s ditambahkan.", pesanan.getKodePesanan(), pesanan.getNamaKustomer()), "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnTambahPemesananActionPerformed

    private void btnBersihPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihPemesananActionPerformed
        bersihPemesananTable();
    }//GEN-LAST:event_btnBersihPemesananActionPerformed

    // Variable declarations - able to modify
    DefaultTableModel tblModel;
    List<InputOrderPenjualanDetailModel> listOrderPenjualanDetail;
    private String kodePembelian = null;
    private String kodeSupplier = null;
    private String kodeEkspedisi = null;
    private String kodePesanan = null;
    private ArrayList<String> selectedKodePesanan = new ArrayList<>();
    private boolean isUpdate = false;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBersihPemesanan;
    private javax.swing.JButton btnCariEkspedisi;
    private javax.swing.JButton btnCariPemesanan;
    private javax.swing.JButton btnCariPesanan;
    private javax.swing.JButton btnCariSupplier;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahPemesanan;
    private com.toedter.calendar.JDateChooser chooserTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPesananDetail;
    private javax.swing.JTextField tfKodePembelian;
    private javax.swing.JTextField tfPesananKodeKustomer;
    private javax.swing.JTextField tfPesananKodePesanan;
    private javax.swing.JTextField tfPesananNamaKustomer;
    private javax.swing.JTextField tfPesananTanggalPesanan;
    private javax.swing.JTextArea tfSupplierAlamat;
    private javax.swing.JTextField tfSupplierKodeEkspedisi;
    private javax.swing.JTextField tfSupplierKodeSupplier;
    private javax.swing.JTextField tfSupplierNamaEkspedisi;
    private javax.swing.JTextField tfSupplierNamaSupplier;
    private javax.swing.JTextField tfSupplierNomorTelepon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelectedRow(Object model, String fromGrid) {
        if (fromGrid.equals(SupplierGridFrame.class.getSimpleName())) {
            setSupplier((SupplierModel) model);
        }
        if (fromGrid.equals(EkspedisiGridFrame.class.getSimpleName())) {
            setEkspedisi((EkspedisiModel) model);
        }
        if (fromGrid.equals(PesananGridFrame.class.getSimpleName())) {
            setPesanan((PesananModel) model);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (tblPesananDetail.getSelectedColumn() == 5) {
            int position = tblPesananDetail.getSelectedRow();
            String kodePesanan = (String) tblPesananDetail.getValueAt(position, 1);
            String namaKustomer = (String) tblPesananDetail.getValueAt(position, 2);
            int result = JOptionPane.showConfirmDialog(this, String.format("Apakaha Anda yakin hendak menghapus pesanan %s atas nama %s dari daftar pembelian ini?", kodePesanan, namaKustomer), "Pertanyaan", JOptionPane.YES_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                hapusPesananDariPembelian(position);
            }
        }
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

    private void setSupplier(SupplierModel supplier) {
        tfSupplierKodeSupplier.setText(supplier.getKode());
        tfSupplierNamaSupplier.setText(supplier.getName());
        tfSupplierAlamat.setText(supplier.getAlamat());
        tfSupplierNomorTelepon.setText(supplier.getNoTelepon());
        kodeSupplier = supplier.getKode();
    }

    private void setEkspedisi(EkspedisiModel ekspedisi) {
        tfSupplierKodeEkspedisi.setText(ekspedisi.getKode());
        tfSupplierNamaEkspedisi.setText(ekspedisi.getName());
        kodeEkspedisi = ekspedisi.getKode();
    }

    private void setPesanan(PesananModel pesanan) {
        tfPesananKodePesanan.setText(pesanan.getKodePesanan());
        tfPesananNamaKustomer.setText(pesanan.getNamaKustomer());
        tfPesananKodeKustomer.setText(pesanan.getKodeKustomer());
        tfPesananTanggalPesanan.setText(pesanan.getTanggalPemesanan());
        kodePesanan = pesanan.getKodePesanan();
    }

    private boolean kodePesananHasBeenSelected() {
        for (String check: selectedKodePesanan) {
            if (check.equals(kodePesanan)) {
                return true;
            }
        }
        return false;
    }

    private void hapusPesananDariPembelian(int position) {
        selectedKodePesanan.remove(position);
        tblModel.removeRow(position);
    }

    private void clear(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void bersihPemesananTable() {
        selectedKodePesanan.clear();
        clearTable();
    }

    private void clearTable() {
        if (tblModel.getRowCount() == 0) return;
        for(int i = 0; i <= tblModel.getRowCount(); i++) {
            tblModel.removeRow(0);
        }
    }

    private boolean simpanInputOrderPembelian() {
        if (kodePembelian == null) {
            throw new UnsupportedOperationException("kodePembelian should not be null");
        }
        if (kodeSupplier == null) {
            peringatanHarusDiisi("Supplier");
            return false;
        }
        if (kodeEkspedisi == null) {
            peringatanHarusDiisi("Ekspedisi");
            return false;
        }
        if (selectedKodePesanan.isEmpty()) {
            peringatanHarusDiisi("Pesanann");
            return false;
        }
        Date tanggal = chooserTanggal.getDate();
        return new ConnectionManager().simpanInputOrderPembelian(kodePembelian, kodeSupplier, kodeEkspedisi, tanggal, selectedKodePesanan, isUpdate);
    }

    private void peringatanHarusDiisi(String fieldName) {
        JOptionPane.showMessageDialog(this, String.format("%s harus diisi.", fieldName), "Perhatian", JOptionPane.WARNING_MESSAGE);
    }

    private void inputOrderPembelianTersimpan() {
        JOptionPane.showMessageDialog(this, "Input Order Pembelian Tersimpan.", "Tersimpan", JOptionPane.INFORMATION_MESSAGE);
        clearAll();
    }

    private void inputOrderPembelianGagalTersimpan() {
        JOptionPane.showMessageDialog(this, "Input Order Pemeblan Gagal Tersimpan", "Gagal Tersimpan", JOptionPane.ERROR_MESSAGE);
        clearAll();
    }
}
