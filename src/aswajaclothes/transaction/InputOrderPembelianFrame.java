/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aswajaclothes.transaction;

import aswajaclothes.connection.ConnectionManager;
import aswajaclothes.grid.BarangGridFrame;
import aswajaclothes.grid.CityGridFrame;
import aswajaclothes.grid.CustomerGridFrame;
import aswajaclothes.grid.EkspedisiGridFrame;
import aswajaclothes.grid.GridListener;
import aswajaclothes.grid.PesananGridFrame;
import aswajaclothes.grid.SupplierGridFrame;
import aswajaclothes.model.common.KabupatenModel;
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.model.master.EkspedisiModel;
import aswajaclothes.model.master.ItemPesananModel;
import aswajaclothes.model.master.PembelianModel;
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
    
    private String kotaTujuanId = "";

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
            tfJenisLayanan,
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
        clearDetailPengiriman();
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
        jPanel5 = new javax.swing.JPanel();
        btnCariEkspedisi = new javax.swing.JButton();
        tfSupplierKodeEkspedisi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfSupplierNamaEkspedisi = new javax.swing.JTextField();
        tfJenisLayanan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taAlamatPengiriman = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tfKotaTujuan = new javax.swing.JTextField();
        btnCariKotaTujuan = new javax.swing.JButton();
        btnHitung = new javax.swing.JButton();
        tfBerat = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfOngkosKirim = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pembelian"));

        jLabel1.setText("Nomor Invoice");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfSupplierNomorTelepon)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfSupplierNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(tfSupplierKodeSupplier)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariSupplier)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                    .addComponent(tfSupplierNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
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
                        .addComponent(tfPesananKodeKustomer)))
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPesananNamaKustomer)
                    .addComponent(tfPesananTanggalPesanan))
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
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPesananNamaKustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCariPemesanan)
                        .addComponent(tfPesananKodePesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesananKodeKustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tfPesananTanggalPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBersihPemesanan)
                    .addComponent(btnTambahPemesanan)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Ekspedisi"));

        btnCariEkspedisi.setText("Cari");
        btnCariEkspedisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariEkspedisiActionPerformed(evt);
            }
        });

        tfSupplierKodeEkspedisi.setEditable(false);
        tfSupplierKodeEkspedisi.setEnabled(false);

        jLabel4.setText("Kode Ekspedisi");

        jLabel8.setText("Nama Ekspedisi");

        tfSupplierNamaEkspedisi.setEditable(false);
        tfSupplierNamaEkspedisi.setEnabled(false);

        tfJenisLayanan.setEditable(false);
        tfJenisLayanan.setEnabled(false);

        jLabel13.setText("Jenis Layanan");

        jLabel14.setText("Alamat Pengiriman");

        taAlamatPengiriman.setColumns(20);
        taAlamatPengiriman.setRows(5);
        jScrollPane3.setViewportView(taAlamatPengiriman);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tfSupplierKodeEkspedisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariEkspedisi))
                    .addComponent(tfSupplierNamaEkspedisi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfJenisLayanan)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSupplierKodeEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnCariEkspedisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSupplierNamaEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfJenisLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Ongkos Kirim"));

        jLabel15.setText("Kota Tujuan");

        tfKotaTujuan.setEditable(false);
        tfKotaTujuan.setEnabled(false);

        btnCariKotaTujuan.setText("Cari");
        btnCariKotaTujuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKotaTujuanActionPerformed(evt);
            }
        });

        btnHitung.setText("Hitung");

        jLabel16.setText("Berat");

        jLabel17.setText("Kg");

        jLabel18.setText("Ongkos Kirim");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(53, 53, 53)
                        .addComponent(tfKotaTujuan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariKotaTujuan))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(88, 88, 88)
                        .addComponent(tfBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHitung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfOngkosKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btnCariKotaTujuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(btnHitung)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(tfOngkosKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void btnCariEkspedisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariEkspedisiActionPerformed
        EkspedisiGridFrame frame = new EkspedisiGridFrame(FilterUtil.FilterType.NONE, "");
        frame.setGridListener(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCariEkspedisiActionPerformed

    private void tfSupplierNamaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierNamaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierNamaSupplierActionPerformed

    private void btnCariSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSupplierActionPerformed
        SupplierGridFrame frame = new SupplierGridFrame(FilterUtil.FilterType.NONE, "");
        frame.setGridListener(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCariSupplierActionPerformed

    private void tfSupplierKodeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierKodeSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierKodeSupplierActionPerformed

    private void btnCariKotaTujuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKotaTujuanActionPerformed
        CityGridFrame frame = new CityGridFrame(FilterUtil.FilterType.NONE, "");
        frame.setGridListener(this);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCariKotaTujuanActionPerformed

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
    private javax.swing.JButton btnCariKotaTujuan;
    private javax.swing.JButton btnCariPemesanan;
    private javax.swing.JButton btnCariPesanan;
    private javax.swing.JButton btnCariSupplier;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahPemesanan;
    private com.toedter.calendar.JDateChooser chooserTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea taAlamatPengiriman;
    private javax.swing.JTable tblPesananDetail;
    private javax.swing.JTextField tfBerat;
    private javax.swing.JTextField tfJenisLayanan;
    private javax.swing.JTextField tfKodePembelian;
    private javax.swing.JTextField tfKotaTujuan;
    private javax.swing.JFormattedTextField tfOngkosKirim;
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
        if (fromGrid.equals(CityGridFrame.class.getSimpleName())) {
            setCity((KabupatenModel) model);
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
        tfJenisLayanan.setText(ekspedisi.getJenisLayanan());
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
        if (tblModel.getRowCount() > 0) {
            for (int i = tblModel.getRowCount() - 1; i > -1; i--) {
                tblModel.removeRow(i);
            }
        }
    }

    private boolean simpanInputOrderPembelian() {
        try {
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
            
            SupplierModel supplier = new SupplierModel();
            supplier.setKode(kodeSupplier);
            
            EkspedisiModel ekspedisi = new EkspedisiModel();
            ekspedisi.setKode(kodeEkspedisi);
            
            KabupatenModel kabupaten = new KabupatenModel();
            kabupaten.setId(kotaTujuanId);
            kabupaten.setName(tfKotaTujuan.getText());
            
            PembelianModel pembelian = new PembelianModel();
            pembelian.setKode(kodePembelian);
            pembelian.setSupplier(supplier);
            pembelian.setEkspedisi(ekspedisi);
            pembelian.setDateString(new SimpleDateFormat("ddMMyyyy").format(tanggal));
            pembelian.setSelectedKodePesanan(selectedKodePesanan);
            pembelian.setAlamatPengiriman(taAlamatPengiriman.getText());
            pembelian.setKotaTujuan(kabupaten);
            pembelian.setBerat(Integer.valueOf(new ValidatorUtil().isNumber(tfBerat.getText(), "Berat")));
            pembelian.setOngkir(Integer.valueOf(new ValidatorUtil().isNumber(tfOngkosKirim.getText(), "Ongkos Kirim")));
            
            return new ConnectionManager().simpanInputOrderPembelian(pembelian, isUpdate);
        } catch (Exception ex) {
            Logger.getLogger(InputOrderPembelianFrame.class.getName()).log(Level.SEVERE, null, ex);
            inputOrderPembelianGagalTersimpan();
            return false;
        }
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

    private void setCity(KabupatenModel city) {
        kotaTujuanId = city.getId();
        tfKotaTujuan.setText(city.getName() + ", " + city.getProvince().getName());
    }

    private void clearDetailPengiriman() {
        kotaTujuanId = "";
        tfKotaTujuan.setText("");
        taAlamatPengiriman.setText("");
        tfBerat.setText("");
        tfOngkosKirim.setText("");
    }
}
