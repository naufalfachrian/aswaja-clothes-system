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
import aswajaclothes.model.master.BarangModel;
import aswajaclothes.model.master.CustomerModel;
import aswajaclothes.model.master.EkspedisiModel;
import aswajaclothes.model.transaction.InputOrderPenjualanDetailModel;
import aswajaclothes.util.CurrencyUtil;
import aswajaclothes.util.FilterUtil;
import aswajaclothes.util.ValidatorUtil;
import aswajaclothes.widget.ButtonCell;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Satrio
 */
public class InputOrderPenjualanFrame extends javax.swing.JFrame implements GridListener, MouseListener {

    /**
     * Creates new form InputOrderPenjualanFrame
     */
    public InputOrderPenjualanFrame() {
        initComponents();
        initDateFormat();
        initKodePesanan();
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
    
    private void initKodePesanan(){
        String kode = new ConnectionManager().getKodePesanan();
        tfKodePesanan.setText(kode);
    }
    
    private void initFormatFieldNumber(){
        tfHargaBarang.setValue(0);
        tfOngkir.setValue(0);
        tfTotal.setValue(0);
    }
    
    private void initTable(){
        tblModel = (DefaultTableModel)tblPesananDetail.getModel();
        tblPesananDetail.addMouseListener(this);
        initCellRenderAction();
    }
    
    private void initCellRenderAction(){
        TableColumnModel columnModel = tblPesananDetail.getColumnModel();
        columnModel.getColumn(5).setCellRenderer(new ButtonCell());
        columnModel.getColumn(6).setCellRenderer(new ButtonCell());
    }
    
    private void initListOrderPenjualan(){
        listOrderPenjualanDetail = new ArrayList<>();
    }
    
    private void clearBarang(){
        tfKodeBarang.setText("");
        tfNamaBarang.setText("");
        tfHargaBarang.setValue(0);
        tfQty.setText("");
        cbLenganPanjang.setSelected(false);
    }
    
    private void clearPesanan(){
        initKodePesanan();
        tfKodeCustomer.setText("");
        tfNamaCustomer.setText("");
        initDateFormat();
        ((DefaultTableModel)tblPesananDetail.getModel()).setRowCount(0);
        listOrderPenjualanDetail.clear();
        tfTotal.setValue(0);
        btnCariCustomer.setEnabled(true);
    }
    
    private void clearEkspedisi(){
        tfKodeEkspedisi.setText("");
        tfNamaEkspedisi.setText("");
        tfOngkir.setValue(0);
        tfTotal.setValue(0);
    }
    
    private void clearAll(){
        clearPesanan();
        clearBarang();
        clearEkspedisi();
    }
    
    private void tambah() throws ParseException, Exception{
        if (tfKodeCustomer.getText().isEmpty()){
            throw new Exception("Kode kustomer harus diisi");
        } else if (tfKodeBarang.getText().isEmpty()){
            throw new Exception("Kode barang harus diisi");
        }
        String kodeBarang = tfKodeBarang.getText();
        String namaBarang = tfNamaBarang.getText();
        String tipeLengan = (cbLenganPanjang.isSelected())? "Panjang" : "Pendek";
        int harga = new CurrencyUtil().clearFormatToInt(tfHargaBarang.getText());
        String sQty = new ValidatorUtil().isNumber(tfQty.getText(), "Kuantitas");
        int qty = Integer.parseInt(sQty);
        
        InputOrderPenjualanDetailModel model = new InputOrderPenjualanDetailModel();
        model.setKodeBarang(kodeBarang);
        model.setNamaBarang(namaBarang);
        model.setQty(qty);
        model.setTipeLengan(tipeLengan);
        model.setHargaBarang(harga);
        if (!isDuplikasiBarang(model)){
           listOrderPenjualanDetail.add(model);
        }
        tblModel.setRowCount(0);
        int index = 1;
        for(InputOrderPenjualanDetailModel modelDetail: listOrderPenjualanDetail){
            String hargaBarang = new CurrencyUtil().formatCurrency(modelDetail.getHargaBarang());
            tblModel.addRow(new Object[]{
                index,
                modelDetail.getKodeBarang(),
                modelDetail.getNamaBarang(),
                modelDetail.getQty(),
                hargaBarang,
                "Edit",
                "Hapus"
            });
            index++;
        }
        initCellRenderAction();
        clearBarang();
        calculateTotal();
    }

    private boolean isDuplikasiBarang(InputOrderPenjualanDetailModel model){
        boolean isDuplikasi = false;
        for(InputOrderPenjualanDetailModel obj : listOrderPenjualanDetail){
            if (obj.getKodeBarang().equals(model.getKodeBarang())){
                int currQty = obj.getQty();
                int accumulateQty = currQty + model.getQty();
                obj.setQty(accumulateQty);
                isDuplikasi = true;
                break;
            }
        }
        return isDuplikasi;
    }
     
    private void calculateTotal() throws ParseException{
        int totalHargaBarang = 0;
        if (listOrderPenjualanDetail != null){
           for(InputOrderPenjualanDetailModel model: listOrderPenjualanDetail){
            totalHargaBarang += model.getHargaBarang() * model.getQty();
        }
        int ongkir = new CurrencyUtil().clearFormatToInt(tfOngkir.getText());
        totalHargaBarang += ongkir;
        tfTotal.setValue(totalHargaBarang);
        }
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
        tfKodePesanan = new javax.swing.JTextField();
        btnCariPesanan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfKodeCustomer = new javax.swing.JTextField();
        btnCariCustomer = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfNamaCustomer = new javax.swing.JTextField();
        chooserTanggal = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        btnCariBarang = new javax.swing.JButton();
        tfNamaBarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        tfQty = new javax.swing.JTextField();
        cbLenganPanjang = new javax.swing.JCheckBox();
        btnTambah = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        tfHargaBarang = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesananDetail = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        tfKodeEkspedisi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfNamaEkspedisi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnCariEkspedisi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfOngkir = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());
        tfTotal = new javax.swing.JFormattedTextField(new CurrencyUtil().formatNumber());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pemesanan/Customer"));

        jLabel1.setText("No Pesanan");

        tfKodePesanan.setEnabled(false);
        tfKodePesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodePesananActionPerformed(evt);
            }
        });

        btnCariPesanan.setText("Cari");
        btnCariPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPesananActionPerformed(evt);
            }
        });

        jLabel2.setText("Tanggal");

        jLabel3.setText("Kode Customer");

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

        jLabel4.setText("Nama Customer");

        tfNamaCustomer.setEnabled(false);
        tfNamaCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaCustomerActionPerformed(evt);
            }
        });

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
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfKodePesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariCustomer)
                            .addComponent(btnCariPesanan))
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
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooserTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tfKodePesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCariPesanan)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfKodeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Barang"));

        jLabel5.setText("Kode Barang");

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

        tfNamaBarang.setEnabled(false);
        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });

        jLabel6.setText("Nama Barang");

        jLabel7.setText("Harga Barang");

        lblQty.setText("Qty");

        cbLenganPanjang.setText("Lengan Panjang (Optional)");
        cbLenganPanjang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLenganPanjangItemStateChanged(evt);
            }
        });
        cbLenganPanjang.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbLenganPanjangStateChanged(evt);
            }
        });

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnBersih.setText("Bersih");
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });

        tfHargaBarang.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfHargaBarang)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariBarang))
                    .addComponent(tfNamaBarang))
                .addGap(18, 18, 18)
                .addComponent(lblQty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLenganPanjang, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnTambah)
                .addGap(18, 18, 18)
                .addComponent(btnBersih)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQty)
                    .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLenganPanjang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnBersih))
                .addContainerGap())
        );

        tblPesananDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Qty", "Harga Barang", "Edit", "Hapus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        tblPesananDetail.setRowHeight(20);
        tblPesananDetail.setShowGrid(false);
        jScrollPane1.setViewportView(tblPesananDetail);

        jLabel9.setText("Kode Ekspedisi");

        tfKodeEkspedisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeEkspedisiActionPerformed(evt);
            }
        });

        jLabel10.setText("Nama Ekspedisi");

        tfNamaEkspedisi.setEnabled(false);
        tfNamaEkspedisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaEkspedisiActionPerformed(evt);
            }
        });

        jLabel11.setText("Ongkir");

        jLabel12.setText("Total");

        btnCariEkspedisi.setText("Cari");
        btnCariEkspedisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariEkspedisiActionPerformed(evt);
            }
        });

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

        jLabel13.setText("Rp");

        jLabel14.setText("Rp");

        tfOngkir.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfOngkirCaretUpdate(evt);
            }
        });

        tfTotal.setEditable(false);

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNamaEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfKodeEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCariEkspedisi)))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel13)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfOngkir)
                                    .addComponent(tfTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfKodeEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnCariEkspedisi)
                    .addComponent(jLabel13)
                    .addComponent(tfOngkir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfNamaEkspedisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodePesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodePesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodePesananActionPerformed

    private void btnCariPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariPesananActionPerformed

    private void tfKodeCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeCustomerActionPerformed

    private void btnCariCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariCustomerActionPerformed
        CustomerGridFrame customerGrid = new CustomerGridFrame(FilterUtil.FilterType.NONE, "");
        customerGrid.setGridListener(this);
        customerGrid.setVisible(true);
    }//GEN-LAST:event_btnCariCustomerActionPerformed

    private void tfNamaCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaCustomerActionPerformed

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeBarangActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        BarangGridFrame barangGrid = new BarangGridFrame("");
        barangGrid.setGridListener(this);
        barangGrid.setVisible(true);
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void tfNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        try {
            tambah();
        } catch (ParseException ex) {
            Logger.getLogger(InputOrderPenjualanFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
        clearBarang();
    }//GEN-LAST:event_btnBersihActionPerformed

    private void tfKodeEkspedisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeEkspedisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeEkspedisiActionPerformed

    private void tfNamaEkspedisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaEkspedisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaEkspedisiActionPerformed

    private void btnCariEkspedisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariEkspedisiActionPerformed
        EkspedisiGridFrame ekspedisiGrid = new EkspedisiGridFrame(FilterUtil.FilterType.NONE, "");
        ekspedisiGrid.setGridListener(this);
        ekspedisiGrid.setVisible(true);
    }//GEN-LAST:event_btnCariEkspedisiActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearAll();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void chooserTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserTanggalPropertyChange

    }//GEN-LAST:event_chooserTanggalPropertyChange

    private void cbLenganPanjangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbLenganPanjangStateChanged
        
    }//GEN-LAST:event_cbLenganPanjangStateChanged

    private void cbLenganPanjangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLenganPanjangItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED){
            cbLenganPanjang.setSelected(true);
            System.out.println("Lengan Panjang");
        } else {
            cbLenganPanjang.setSelected(false);
            System.out.println("Lengan Pendek");
        }
    }//GEN-LAST:event_cbLenganPanjangItemStateChanged

    private void tfOngkirCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfOngkirCaretUpdate
        try {
            calculateTotal();
        } catch (ParseException ex) {
            Logger.getLogger(InputOrderPenjualanFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfOngkirCaretUpdate

    // Variable declarations - able to modify
    DefaultTableModel tblModel;
    List<InputOrderPenjualanDetailModel> listOrderPenjualanDetail;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnCariCustomer;
    private javax.swing.JButton btnCariEkspedisi;
    private javax.swing.JButton btnCariPesanan;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JCheckBox cbLenganPanjang;
    private com.toedter.calendar.JDateChooser chooserTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQty;
    private javax.swing.JTable tblPesananDetail;
    private javax.swing.JFormattedTextField tfHargaBarang;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfKodeCustomer;
    private javax.swing.JTextField tfKodeEkspedisi;
    private javax.swing.JTextField tfKodePesanan;
    private javax.swing.JTextField tfNamaBarang;
    private javax.swing.JTextField tfNamaCustomer;
    private javax.swing.JTextField tfNamaEkspedisi;
    private javax.swing.JFormattedTextField tfOngkir;
    private javax.swing.JTextField tfQty;
    private javax.swing.JFormattedTextField tfTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelectedRow(Object model, String fromGrid) {
        if (fromGrid.equals(CustomerGridFrame.class.getSimpleName())){
            CustomerModel customer = (CustomerModel) model;
            tfKodeCustomer.setText(customer.getKode());
            tfNamaCustomer.setText(customer.getName());
            btnCariCustomer.setEnabled(false);
        } else if (fromGrid.equals(BarangGridFrame.class.getSimpleName())){
            BarangModel barang = (BarangModel) model;
            tfKodeBarang.setText(barang.getKode());
            tfNamaBarang.setText(barang.getName());
            tfHargaBarang.setText(String.valueOf(barang.getHargaHPP()));
        } else if (fromGrid.equals(EkspedisiGridFrame.class.getSimpleName())){
            EkspedisiModel ekspedisi = (EkspedisiModel) model;
            tfKodeEkspedisi.setText(ekspedisi.getKode());
            tfNamaEkspedisi.setText(ekspedisi.getName());
        } else {
            // Do Nothing
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowSelected = tblPesananDetail.getSelectedRow();
        int columnSelected = tblPesananDetail.getSelectedColumn();
        if (columnSelected == 6) {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Yakin ingin hapus? ","Warning", JOptionPane.YES_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION){
                listOrderPenjualanDetail.remove(rowSelected);
                tblModel.removeRow(rowSelected);
                try {
                    calculateTotal();
                } catch (ParseException ex) {
                    Logger.getLogger(InputOrderPenjualanFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (columnSelected == 5){
            InputOrderPenjualanDetailModel model = listOrderPenjualanDetail.get(rowSelected);
            tfKodeBarang.setText(model.getKodeBarang());
            tfNamaBarang.setText(model.getNamaBarang());
            tfQty.setText(String.valueOf(model.getQty()));
            tfHargaBarang.setText(String.valueOf(model.getHargaBarang()));
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
}
