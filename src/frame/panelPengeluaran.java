
package frame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;  
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import koneksi.koneksi;

public class panelPengeluaran extends javax.swing.JPanel {
    private Connection con;
    private Statement stat;
    private ResultSet res;
    private String t;
     
    public panelPengeluaran() {
        koneksi();
        initComponents();
        kosongkan();
        tabel();
        spinner_tanggal.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
    

         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
            (screenSize.width - frameSize.width) / 3,
            (screenSize.height -frameSize.height) / 4);
        
//        creteRowByTime();
    }
    
private void koneksi() {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_laundryku", "root", "");
        con = koneksi.configDB(); // Assuming configDB returns a Connection object
        stat = con.createStatement();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal terhubung ke database: " );
        e.printStackTrace(); // Cetak exception untuk debugging
    }
}
     private void kosongkan(){
     
        txt_keterangan.setText("");
        txt_total.setText("");
       
    }
     
    private void tabel(){
        // ...
         // Set show grid untuk menampilkan garis pembatas
    tb_pengeluaran.setShowGrid(true);

    // Set show horizontal lines dan show vertical lines
    tb_pengeluaran.setShowHorizontalLines(true);
    tb_pengeluaran.setShowVerticalLines(true);
    
    DefaultTableModel t = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Membuat sel tidak dapat diedit langsung di tabel
        }
    };
    t.addColumn("NO");
    t.addColumn("Tangal Pengeluaran");
    t.addColumn("Keterangan");
    t.addColumn("Total Pengeluaran");
    tb_pengeluaran.setModel(t);

    try { 
        // Use SUM query to get the total of "total_pengeluaran" column
        res = stat.executeQuery("SELECT COUNT(*) as row_count, SUM(total_pengeluaran) as total_pengeluaran FROM pengeluaran");
        int rowCount = 0;
        double totalPengeluaran = 0.0;

        if (res.next()) {
            rowCount = res.getInt("row_count");
            totalPengeluaran = res.getDouble("total_pengeluaran");
        }

        res = stat.executeQuery("SELECT * from pengeluaran");
        while (res.next()) {
            t.addRow(new Object[]{
                res.getString("id_pengeluaran"),
                res.getString("tgl_pengeluaran"),
                res.getString("keterangan"),
                res.getString("total_pengeluaran"),
            });
        tb_pengeluaran.setModel(t);
        }

        // Modify JLabel to display both row count and total of "total_pengeluaran" column
        jLabel1.setText("Jumlah Data: " + rowCount + " | Total Pengeluaran: " + totalPengeluaran);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    // Set rata tengah (centered) untuk semua sel di tabel
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    for (int i = 0; i < tb_pengeluaran.getColumnCount(); i++) {
        tb_pengeluaran.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    // Set rata tengah (centered) untuk header kolom
    JTableHeader header = tb_pengeluaran.getTableHeader();
    header.setDefaultRenderer(centerRenderer);
    header.setPreferredSize(new Dimension(100, 40)); // Sesuaikan dimensi header jika diperlukan
    header.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14)); // Sesuaikan font header jika diperlukan
}
 
// Kelas renderer khusus untuk baris pertama
class CustomTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Mengubah warna latar belakang untuk baris pertama
        if (row == 0) {
            cellComponent.setBackground(new java.awt.Color(23, 233, 184)); // Ganti warna sesuai kebutuhan
        } else {
            cellComponent.setBackground(table.getBackground());
        }

        return cellComponent;
    }
}
private void cariData(String kataKunci) {
    DefaultTableModel model = (DefaultTableModel) tb_pengeluaran.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        // Use prepared statement to avoid SQL injection
        String query = "SELECT * FROM pengeluaran WHERE tgl_pengeluaran LIKE ? OR keterangan LIKE ? OR total_pengeluaran LIKE ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 1; i <= 3; i++) {
                pstmt.setString(i, "%" + kataKunci + "%");
            }
            res = pstmt.executeQuery();

            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("id_pengeluaran"),
                    res.getString("tgl_pengeluaran"),
                    res.getString("keterangan"),
                    res.getString("total_pengeluaran")
                    // Add other columns as needed
                });
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tb_pengeluaran = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_keterangan = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        btn_lihat = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        spinner_tanggal = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(255, 255, 255));

        tb_pengeluaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NO", "Tanggal Pengeluaran", "Keterangan", "Total Pengeluaran"
            }
        ));
        tb_pengeluaran.setRowHeight(30);
        tb_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_pengeluaranMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tb_pengeluaran);
        if (tb_pengeluaran.getColumnModel().getColumnCount() > 0) {
            tb_pengeluaran.getColumnModel().getColumn(0).setMinWidth(50);
            tb_pengeluaran.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_pengeluaran.getColumnModel().getColumn(0).setMaxWidth(50);
            tb_pengeluaran.getColumnModel().getColumn(1).setMinWidth(150);
            tb_pengeluaran.getColumnModel().getColumn(1).setPreferredWidth(150);
            tb_pengeluaran.getColumnModel().getColumn(1).setMaxWidth(150);
            tb_pengeluaran.getColumnModel().getColumn(3).setMinWidth(200);
            tb_pengeluaran.getColumnModel().getColumn(3).setPreferredWidth(200);
            tb_pengeluaran.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        btn_simpan.setBackground(new java.awt.Color(51, 0, 153));
        btn_simpan.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("Simpan");
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_simpanMousePressed(evt);
            }
        });
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(153, 102, 0));
        btn_edit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setText("Edit");
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_editMousePressed(evt);
            }
        });
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(204, 51, 0));
        btn_hapus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hapusMousePressed(evt);
            }
        });
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(0, 102, 102));
        btn_clear.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btn_clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Tanggal Pengeluaran");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel10.setText(":");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Total Pengeluaran");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Keterangan");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel11.setText(":");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel12.setText(":");

        txt_keterangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_lihat.setBackground(new java.awt.Color(0, 153, 204));
        btn_lihat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_lihat.setForeground(new java.awt.Color(255, 255, 255));
        btn_lihat.setText("Lihat");
        btn_lihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatActionPerformed(evt);
            }
        });

        txt_cari.setToolTipText("");
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("jlabel1");

        jPanel1.setBackground(new java.awt.Color(39, 159, 136));
        jPanel1.setPreferredSize(new java.awt.Dimension(1082, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Tambahkan Pengeluaran");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(432, 432, 432)
                .addComponent(jLabel2)
                .addContainerGap(434, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Cari");

        spinner_tanggal.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1701222634810L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinner_tanggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_lihat, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(10, 10, 10)
                                .addComponent(spinner_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(10, 10, 10)
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel6)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(spinner_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel11))
                    .addComponent(txt_keterangan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_simpan)
                    .addComponent(btn_clear))
                .addGap(46, 46, 46)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_hapus)
                    .addComponent(btn_edit)
                    .addComponent(btn_lihat)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed

    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
    try {
        // Dapatkan nilai dari komponen GUI
        java.util.Date tanggalPengeluaran = (java.util.Date) spinner_tanggal.getValue();
        String keterangan = txt_keterangan.getText();
        String totalPengeluaran = txt_total.getText();

        // Validate inputs
        if (keterangan.isEmpty() || totalPengeluaran.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Keterangan dan Total Pengeluaran tidak boleh kosong");
            return;
        }

        // Konversi nilai tanggal menjadi Timestamp
        Timestamp timestamp = new Timestamp(tanggalPengeluaran.getTime());

        if (t == null) {
            // Jika t == null, itu berarti operasi penyimpanan baru
            // Lakukan penyimpanan data ke database
            String query = "INSERT INTO pengeluaran(tgl_pengeluaran, keterangan, total_pengeluaran) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setTimestamp(1, timestamp);
                pstmt.setString(2, keterangan);
                pstmt.setString(3, totalPengeluaran);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Data baru berhasil disimpan");
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan data baru");
                }

                tabel();
            }
        } else {
            // Jika t != null, itu berarti operasi pembaruan
            // Lakukan pembaruan data ke database berdasarkan ID yang tersimpan di t
            String query = "UPDATE pengeluaran SET tgl_pengeluaran=?, keterangan=?, total_pengeluaran=? WHERE id_pengeluaran=?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setTimestamp(1, timestamp);
                pstmt.setString(2, keterangan);
                pstmt.setString(3, totalPengeluaran);
                pstmt.setInt(4, Integer.parseInt(t));

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data");
                }

                tabel();
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_simpanActionPerformed
private void tampilkanDataBerdasarkanID(int idPengeluaran) {
  try {
        res = stat.executeQuery("SELECT * FROM pengeluaran WHERE id_pengeluaran='" + idPengeluaran + "'");
        while (res.next()) {
            // Assuming "tgl_pengeluaran" is a Date column in the database
            java.sql.Date sqlDate = res.getDate("tgl_pengeluaran");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

            // Assuming that spinner_tanggal is a JSpinner
            spinner_tanggal.setValue(utilDate);
            
            // Replace these column names with actual column names in your table
            txt_keterangan.setText(res.getString("keterangan"));
            txt_total.setText(res.getString("total_pengeluaran"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

    private void btn_editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMousePressed

    }//GEN-LAST:event_btn_editMousePressed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
 // Dapatkan baris yang dipilih
    int row = tb_pengeluaran.getSelectedRow(); 

    // Pastikan ada baris yang dipilih
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit");
        return;
    }

    // Dapatkan ID Pengeluaran dari kolom pertama (indeks 0)
    int idPengeluaran = Integer.parseInt(tb_pengeluaran.getValueAt(row, 0).toString());

    // Ambil nilai dari database dan tampilkan di JTextField
    tampilkanDataBerdasarkanID(idPengeluaran);
 
    // Simpan ID Pengeluaran yang akan diupdate (digunakan pada aksi btn_simpanActionPerformed)
    t = Integer.toString(idPengeluaran);


  
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
      kosongkan();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatActionPerformed
      // Dapatkan baris yang dipilih
    int barisTerpilih = tb_pengeluaran.getSelectedRow();

    // Pastikan ada baris yang dipilih
    if (barisTerpilih == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan dilihat");
        return;
    }

    // Dapatkan ID Bahan Baku dari kolom pertama (indeks 0)
    int idPengeluaran = Integer.parseInt(tb_pengeluaran.getValueAt(barisTerpilih, 0).toString());

    // Tampilkan detail data di sini, misalnya dengan menggunakan JOptionPane
    tampilkanDetailPengeluaran(idPengeluaran);
}

// Method untuk menampilkan detail Bahan Baku
private void tampilkanDetailPengeluaran(int idPengeluaran) {
    try {
        // Query database untuk mendapatkan detail Bahan Baku berdasarkan ID
        String query = "SELECT * FROM pengeluaran WHERE id_pengeluaran = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idPengeluaran);
            res = pstmt.executeQuery();

            // Tampilkan hasil query
            if (res.next()) {
                String detail = "Id Pengeluaran : " + res.getString("id_pengeluaran") + "\n"
                        + "Tanggal Pengeluaran: " + res.getString("tgl_pengeluaran") + "\n"
                        + "Keterangan: " + res.getString("keterangan") + "\n"
                        + "Total Pengeluaran: " + res.getString("total_pengeluaran");

                // Tampilkan detail menggunakan JOptionPane
                JOptionPane.showMessageDialog(this, detail, "Total Pengeluaran", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan detail: " );
    }
 
    }//GEN-LAST:event_btn_lihatActionPerformed

    private void tb_pengeluaranMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pengeluaranMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_pengeluaranMouseReleased

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // Dapatkan baris yang dipilih
        int row = tb_pengeluaran.getSelectedRow();

        // Pastikan ada baris yang dipilih
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus");
            return;
        }

        // Dapatkan ID Bahan Baku dari kolom pertama (indeks 0)
        int idPengeluaran = Integer.parseInt(tb_pengeluaran.getValueAt(row, 0).toString());

        // Hapus data dari database
        try {
            String query = "DELETE FROM pengeluaran WHERE id_pengeluaran = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, idPengeluaran);
                pstmt.executeUpdate();
            }

            // Refresh tabel setelah penghapusan
            tabel();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " );
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusMousePressed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        String kataKunci = txt_cari.getText();
        cariData(kataKunci);
    }//GEN-LAST:event_txt_cariActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_lihat;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner spinner_tanggal;
    private javax.swing.JTable tb_pengeluaran;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_keterangan;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
