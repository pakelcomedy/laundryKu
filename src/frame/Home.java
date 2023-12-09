package frame;

import com.raven.chart.ModelChart;
import java.awt.Color;

import java.awt.Graphics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import koneksi.koneksi;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Home extends javax.swing.JPanel {

    private Connection con;  // Declare con as a class variable

    public Home() {
        initComponents();
        dataMenu();
        dt();

        panelMember.tambahTanggalMember();
        panelKeuangan.tambahBarisBaru();
        panelTransaksi.changeStatus();
        koneksi();  // Initialize the connection
        setChartFromDatabase();
//        getContentPane().setBackground(new Color(250, 250, 250));
//        chart.addLegend("Income", new Color(245, 189, 135));
//        chart.addLegend("Expense", new Color(135, 189, 245));
//        chart.addLegend("Profit", new Color(189, 135, 245));
//        chart.addLegend("Cost", new Color(139, 229, 222));
//        chart.addData(new ModelChart("January", new double[]{1000, 1000, 80,89}));
//        chart.addData(new ModelChart("February", new double[]{600, 750, 90,150}));
//        chart.addData(new ModelChart("March", new double[]{200, 350, 460,900}));
//        chart.addData(new ModelChart("April", new double[]{480, 150, 750,700}));
//        chart.addData(new ModelChart("May", new double[]{350, 540, 300,150}));
//        chart.addData(new ModelChart("June", new double[]{190, 280, 81,200}));
//        this.add(chart);
        
    }

    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = koneksi.configDB(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database: " + e.getMessage());
            e.printStackTrace(); // Cetak exception untuk debugging
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {

    }
    
private void setChartFromDatabase() {
        try {
            String query = "SELECT\n" +
                        "    MONTH(tgl_laporan) AS bulan,\n" +
                        "    COUNT(id_laporan) AS jumlah_laporan,\n" +
                        "    SUM(pemasukan) AS total_pemasukan,\n" +
                        "    SUM(pengeluaran) AS total_pengeluaran,\n" +
                        "    SUM(total) AS total_total\n" +
                        "FROM\n" +
                        "    laporan_keuangan\n" +
                        "WHERE\n" +
                        "    YEAR(tgl_laporan) = YEAR(CURDATE())\n" +
                        "GROUP BY\n" +
                        "    MONTH(tgl_laporan);";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

//            List<ModelChart> chartData = new ArrayList<>();
            
            chart.addLegend("Pemasukan", new Color(135, 189, 245));
            chart.addLegend("Pengeluaran", new Color(189, 135, 245));
            chart.addLegend("Total", new Color(139, 229, 222));
           while (resultSet.next()) {
            int monthNumber = resultSet.getInt("bulan");

            // Assuming you want to create a LocalDate with the first day of the month
            LocalDate tglPengeluaran = LocalDate.of(LocalDate.now().getYear(), monthNumber, 1);

            int total = resultSet.getInt("total_total");
            int pemasukan = resultSet.getInt("total_pemasukan");
            int pengeluaran = resultSet.getInt("total_pengeluaran");

            System.out.println("Date: " + tglPengeluaran + ", Total: " + total + ", Pemasukan: " + pemasukan + ", Pengeluaran: " + pengeluaran);

            String month = tglPengeluaran.getMonth().toString();

            getContentPane().setBackground(new Color(250, 250, 250));
            // Assuming chart is an instance of com.raven.chart.Chart
          
            // Assuming ModelChart expects int values
            chart.addData(new ModelChart(month, new double[]{pemasukan, pengeluaran, total}));
        }
            // Update the chart on the EDT
//            SwingUtilities.invokeLater(() -> {
//                chartData.forEach(chart::addData);
//            });
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database");
        }
    }
public void dt() {
    // Get the current date
    Date currentDate = new Date();
    Locale indonesianLocale = new Locale("id", "ID");
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    String formattedYear = sdfYear.format(currentDate);
    Tahun.setText(formattedYear);
}

    public javax.swing.JPanel getContentPane() {
        return this;
    }

    public com.raven.chart.Chart getChart() {
        return chart;
    }
    
      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblPegawai = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_search2 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        SearchB4 = new javax.swing.JButton();
        show2 = new javax.swing.JButton();
        btnSelesai2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblTransaksi1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblMember = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTransaksi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_search1 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        SearchB3 = new javax.swing.JButton();
        Show1 = new javax.swing.JButton();
        btnSelesai = new javax.swing.JButton();
        chart = new com.raven.chart.Chart();
        jPanel3 = new javax.swing.JPanel();
        Tahun = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1080, 665));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Jumlah Pegawai");

        lblPegawai.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblPegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPegawai.setText("-");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/JUMLAH KARYAWAN.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(lblPegawai)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPegawai)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel15.setPreferredSize(new java.awt.Dimension(493, 284));

        jPanel16.setBackground(new java.awt.Color(217, 217, 217));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel16.setPreferredSize(new java.awt.Dimension(493, 48));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("Jadwal Laundry Pengiriman Hari ini");
        jPanel16.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        txt_search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search2ActionPerformed(evt);
            }
        });

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NO", "Jam Masuk", "Nama Pegawai", "Status"
            }
        ));
        tbl2.setRowHeight(30);
        jScrollPane6.setViewportView(tbl2);

        SearchB4.setBackground(new java.awt.Color(0, 102, 204));
        SearchB4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        SearchB4.setForeground(new java.awt.Color(255, 255, 255));
        SearchB4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/ic_baseline-search.png"))); // NOI18N
        SearchB4.setText("Search");
        SearchB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchB4ActionPerformed(evt);
            }
        });

        show2.setBackground(new java.awt.Color(0, 102, 102));
        show2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        show2.setForeground(new java.awt.Color(255, 255, 255));
        show2.setText("Show");
        show2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show2ActionPerformed(evt);
            }
        });

        btnSelesai2.setBackground(new java.awt.Color(255, 51, 51));
        btnSelesai2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnSelesai2.setForeground(new java.awt.Color(255, 255, 255));
        btnSelesai2.setText("Update Selesai");
        btnSelesai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesai2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnSelesai2)
                        .addGap(20, 20, 20)
                        .addComponent(show2)
                        .addGap(18, 18, 18)
                        .addComponent(SearchB4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchB4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(show2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelesai2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Penghasilan hari ini");

        lblTransaksi1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTransaksi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransaksi1.setText("-");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/PENDAPATAN.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel8))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTransaksi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTransaksi1)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Jumlah Member Aktif");

        lblMember.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMember.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMember.setText("-");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/JUMLAH MEMBERR.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMember)
                .addGap(120, 120, 120))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMember)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Jumlah Transaksi Hari Ini");

        lblTransaksi.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransaksi.setText("-");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/TRANSAKSI HARI INI.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(lblTransaksi))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(lblTransaksi)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel12.setPreferredSize(new java.awt.Dimension(493, 284));

        jPanel14.setBackground(new java.awt.Color(217, 217, 217));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel14.setPreferredSize(new java.awt.Dimension(493, 48));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Jadwal Laundry Ambil Hari Ini");
        jPanel14.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        txt_search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search1ActionPerformed(evt);
            }
        });

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NO", "Jam Masuk", "Nama Pegawai", "Status"
            }
        ));
        tbl1.setRowHeight(30);
        jScrollPane5.setViewportView(tbl1);

        SearchB3.setBackground(new java.awt.Color(0, 102, 204));
        SearchB3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        SearchB3.setForeground(new java.awt.Color(255, 255, 255));
        SearchB3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/ic_baseline-search.png"))); // NOI18N
        SearchB3.setText("Search");
        SearchB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchB3ActionPerformed(evt);
            }
        });

        Show1.setBackground(new java.awt.Color(0, 102, 102));
        Show1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Show1.setForeground(new java.awt.Color(255, 255, 255));
        Show1.setText("Show");
        Show1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Show1ActionPerformed(evt);
            }
        });

        btnSelesai.setBackground(new java.awt.Color(255, 51, 51));
        btnSelesai.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnSelesai.setForeground(new java.awt.Color(255, 255, 255));
        btnSelesai.setText("Update Selesai");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnSelesai)
                        .addGap(19, 19, 19)
                        .addComponent(Show1)
                        .addGap(17, 17, 17)
                        .addComponent(SearchB3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search1))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Show1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SearchB3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        chart.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Tahun.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Tahun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tahun.setText("2023");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Laporan Keuangan Pertahun");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Tahun, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchB3ActionPerformed
        // TODO add your handling code here:
        String kataKunci = txt_search1.getText();
        cariData1(kataKunci);
    }//GEN-LAST:event_SearchB3ActionPerformed

    private void SearchB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchB4ActionPerformed
        // TODO add your handling code here:
        String kataKunci = txt_search2.getText();
        cariData2(kataKunci);
    }//GEN-LAST:event_SearchB4ActionPerformed

    private void btnSelesai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesai2ActionPerformed
        // TODO add your handling code here:
        setSelesai2();
    }//GEN-LAST:event_btnSelesai2ActionPerformed

    private void show2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show2ActionPerformed
        // TODO add your handling code here:
        showTableDetail2();
    }//GEN-LAST:event_show2ActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:
        setSelesai();
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void Show1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Show1ActionPerformed
        // TODO add your handling code here:
        showTableDetail1();
    }//GEN-LAST:event_Show1ActionPerformed

    private void txt_search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search1ActionPerformed
        // TODO add your handling code here:
        String kataKunci = txt_search1.getText();
        cariData1(kataKunci);
    }//GEN-LAST:event_txt_search1ActionPerformed

    private void txt_search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search2ActionPerformed
        // TODO add your handling code here:
        String kataKunci = txt_search2.getText();
        cariData2(kataKunci);
    }//GEN-LAST:event_txt_search2ActionPerformed
public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> new Home().setVisible(true));
}


    private void dataMenu() {
        jumlahMember();
        jumlahTransaksi();
        jumlahPegawai();
        jumlahPenghasilan();
        tblStatusLaundryAmbil();
        tblStatusLaundryKirim();        
    }

    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

    public int jumlahMember() {
        try {
//            LocalDate currentDate = LocalDate.now();
            String sql = "SELECT COUNT(id_member) AS memberCount FROM member WHERE statusMember = 0";
            
            // Assuming koneksi.configDB() returns a valid connection
            Connection conn = koneksi.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            // Set the parameter with the current date
//            pst.setDate(1, java.sql.Date.valueOf(currentDate));

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Update your UI or return the count as needed
                int jumlah = rs.getInt("memberCount");
                lblMember.setText(String.valueOf(jumlah));
                return jumlah;  // Return the count if needed in your application logic
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            JOptionPane.showMessageDialog(this, "Error in jumlahMember: " + e.getMessage());
        }
        return 0;
    }

    public int jumlahTransaksi() {
            try {
                    LocalDate currentDate = LocalDate.now();

                    String sql = "SELECT COUNT(no_transaksi) AS jum_transaksi FROM `transaksi` where tgl_transaksi =?";
                    java.sql.Connection conn = (Connection) koneksi.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setDate(1, java.sql.Date.valueOf(currentDate));

                    java.sql.ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                       int jumlah = rs.getInt("jum_transaksi");
                       lblTransaksi.setText(String.valueOf(jumlah));
                    }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            return 0;
    }     
    public int jumlahPegawai() {
        try {
            LocalDate currentDate = LocalDate.now();

            // Assuming your table name is "pegawai" and the column name is "jabatan"
            String sql = "SELECT COUNT(id_pegawai) AS jum_pegawai FROM user WHERE jabatan = 'pegawai'";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int jumlah = rs.getInt("jum_pegawai");
                lblPegawai.setText(String.valueOf(jumlah));
                return jumlah;  // Return the count if needed in your application logic
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            JOptionPane.showMessageDialog(this, "Error in jumlahPegawai: " + e.getMessage());
        }
        return 0;
    }
    
    public int jumlahPenghasilan() {
        try {
            LocalDate currentDate = LocalDate.now();

            String sql = "SELECT SUM(totalPembayaran) AS jum_transaksi FROM `transaksi` where tgl_transaksi =?";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setDate(1, java.sql.Date.valueOf(currentDate));

            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int jumlah = rs.getInt("jum_transaksi");
                lblTransaksi1.setText( "Rp. " + String.valueOf(jumlah));
                return jumlah;  // Return the count if needed in your application logic
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            JOptionPane.showMessageDialog(this, "Error in jumlahPegawai: " + e.getMessage());
        }
        return 0;
    }
    
    public void tblStatusLaundryAmbil() {
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.setColumnCount(0);
        tbl.addColumn("No Transaksi");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("Batas Waktu");
        tbl.addColumn("Status");

        tbl1.setModel(tbl);

        try {
            String sql = "SELECT * FROM transaksi where statusPengiriman = 0 AND batas_waktu = ?";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDate(1, currentDate);

            java.sql.ResultSet res = pst.executeQuery();

            while (res.next()) {
                // Retrieve the names for id_pelanggan and id_pegawai
                String namaPelanggan = getNamaPelanggan(res.getString("id_pelanggan"));
                System.out.println("Nama Pelanggan: " + namaPelanggan);

                int statusLaundry = res.getInt("status_laundry");
                System.out.println("Status Laundry: " + statusLaundry);
                int status = res.getInt("status_laundry");
                String statusText;
                
                if (status == 2) {
                        statusText = "DIAMBIL";
                    } else if (status == 3) {
                        statusText = "Selesai";
                     } else if (status == 0 ) {
                        statusText = "Baru";
                     } else if (status == 0 ) {
                        statusText = "Prosses";
                    } else {
                        statusText = "Sudah Lewat";
                    }

                tbl.addRow(new Object[]{
                        res.getString("no_transaksi"),
                        namaPelanggan,
                        res.getString("batas_waktu"),
                        statusText
                });
            }
            
            res.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            JOptionPane.showMessageDialog(this, "Error in tblStatusLaundry: " + e.getMessage());
        }
    }
    
    public void tblStatusLaundryKirim() {
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.setColumnCount(0);
        tbl.addColumn("No Transaksi");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("Alamat");
        tbl.addColumn("Batas Waktu");
        tbl.addColumn("Status");

        tbl2.setModel(tbl);

        try {
            String sql = "SELECT * FROM transaksi where statusPengiriman = 1 AND batas_waktu = ? ";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDate(1, currentDate);

            java.sql.ResultSet res = pst.executeQuery();

            while (res.next()) {
                // Retrieve the names for id_pelanggan and id_pegawai
                String namaPelanggan = getNamaPelanggan(res.getString("id_pelanggan"));
                System.out.println("Nama Pelanggan: " + namaPelanggan);

                int statusLaundry = res.getInt("status_laundry");
                System.out.println("Status Laundry: " + statusLaundry);
                
                 int status = res.getInt("status_laundry");
                 String statusText;
                
                 if (status == 2) {
                        statusText = "DIKIRIM";
                    } else if (status == 3) {
                        statusText = "Selesai";
                     } else if (status == 0 ) {
                        statusText = "Baru";
                      } else if (status == 0 ) {
                        statusText = "Prosses";
                    } else {
                        statusText = "Sudah Lewat";
                    }


                tbl.addRow(new Object[]{
                        res.getString("no_transaksi"),
                        namaPelanggan,
                        res.getString("alamat_pengiriman"),
                        res.getString("batas_waktu"),
                       statusText
                });
            }
            
            res.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            JOptionPane.showMessageDialog(this, "Error in tblStatusLaundry: " + e.getMessage());
        }
    }

     
     // Method to get the name of pelanggan based on id_pelanggan
    private String getNamaPelanggan(String idPelanggan) {
        // Execute a query to fetch the name based on id_pelanggan
        // Replace 'pelanggan' with your actual table name
        String query = "SELECT nama FROM pelanggan WHERE id_pelanggan = ?";
        try {
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, idPelanggan);
            java.sql.ResultSet res = pst.executeQuery();
            if (res.next()) {
                return res.getString("nama");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching pelanggan name: " + e.getMessage());
        }
        return "";
    }
    
    private void setSelesai() {
        try {
            int selectedRow = tbl1.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Mohon pilih barisan yang di table ingin dihapus");
                return;  
            }
            
            String update = "UPDATE transaksi SET status_laundry = 3 where no_transaksi = ?";
            
            int idTransaksi = Integer.parseInt((String) tbl1.getValueAt(selectedRow, tbl1.getColumn("No Transaksi").getModelIndex()));

            java.sql.Connection conn = (java.sql.Connection) koneksi.configDB();

            java.sql.PreparedStatement pstTransaksi = conn.prepareStatement(update);
            pstTransaksi.setInt(1, idTransaksi);
            
            pstTransaksi.executeUpdate();
            
            pstTransaksi.close();
            conn.setAutoCommit(true);  // Restore auto-commit mode
            conn.close();
            
            tblStatusLaundryAmbil();
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void setSelesai2() {
        try {
            int selectedRow = tbl2.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Mohon pilih barisan yang di table ingin dihapus");
                return;  
            }
            
            String update = "UPDATE transaksi SET status_laundry = 3 where no_transaksi = ?";
            
            int idTransaksi = Integer.parseInt((String) tbl2.getValueAt(selectedRow, tbl2.getColumn("No Transaksi").getModelIndex()));

            java.sql.Connection conn = (java.sql.Connection) koneksi.configDB();

            java.sql.PreparedStatement pstTransaksi = conn.prepareStatement(update);
            pstTransaksi.setInt(1, idTransaksi);
            
            pstTransaksi.executeUpdate();
            
            
            pstTransaksi.close();
            conn.setAutoCommit(true);  // Restore auto-commit mode
            conn.close();
            
            tblStatusLaundryKirim();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showTableDetail1() {
        SwingUtilities.invokeLater(() -> {
            int selectedRow = tbl1.getSelectedRow();

            int idShow;
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Mohon Pilih Barisan Yang di Table Ingin Diubah");
                idShow = 0;
            } else {
                idShow = Integer.parseInt((String) tbl1.getValueAt(selectedRow, tbl1.getColumn("No Transaksi").getModelIndex()));
            }
            // System.out.println("idEdit: " + idEdit);
            if (idShow != 0) {
                showTransaksi panel = new showTransaksi(idShow);
                panel.setVisible(true);
            }
            System.out.println("idshow: "+idShow);
        });
    }
    
    private void showTableDetail2() {
        SwingUtilities.invokeLater(() -> {
            int selectedRow = tbl2.getSelectedRow();

            int idShow;
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Mohon Pilih Barisan Yang di Table Ingin Diubah");
                idShow = 0;
            } else {
                idShow = Integer.parseInt((String) tbl2.getValueAt(selectedRow, tbl2.getColumn("No Transaksi").getModelIndex()));
            }
            // System.out.println("idEdit: " + idEdit);
            if (idShow != 0) {
                showTransaksi panel = new showTransaksi(idShow);
                panel.setVisible(true);
            }
            System.out.println("idshow: "+idShow);
        });
    }
    
    private void cariData1(String kataKunci) {
        DefaultTableModel tbl = (DefaultTableModel) tbl1.getModel();
        tbl.setRowCount(0); // Bersihkan baris yang sudah ada

        try {
            // Gunakan prepared statement untuk menghindari SQL injection
            String query = "SELECT \n" +
            "    transaksi.no_transaksi, \n" +
            "    pelanggan.nama AS pelangganNama, \n" +
            "    transaksi.batas_waktu, \n" +
            "    transaksi.status_laundry \n" +
            "FROM \n" +
            "    transaksi \n" +
            "JOIN \n" +
            "    pelanggan ON transaksi.id_pelanggan = pelanggan.id_pelanggan\n" +
            "WHERE \n" +
            "    transaksi.statusPengiriman = 0\n" +
            "    AND \n" +
            "    (transaksi.no_transaksi LIKE ? OR pelanggan.nama LIKE ? OR transaksi.batas_waktu LIKE ? )\n" +
            "GROUP BY \n" +
            "    transaksi.no_transaksi";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, "%" + kataKunci + "%");
                pstmt.setString(2, "%" + kataKunci + "%");
                pstmt.setString(3, "%" + kataKunci + "%");

                ResultSet res = pstmt.executeQuery();

                while (res.next()) {
                    int status = res.getInt("transaksi.status_laundry");
                    String statusText;

                    if (status == 2) {
                        statusText = "DIAMBIL";
                    } else if (status == 3) {
                        statusText = "Selesai";
                    } else if (status == 0) {
                        statusText = "Baru";
                    } else if (status == 1) {
                        statusText = "Proses"; // Assuming 1 corresponds to "Proses"
                    } else {
                        statusText = "Sudah Lewat";
                    }

                    tbl.addRow(new Object[]{
                            res.getString("transaksi.no_transaksi"),
                            res.getString("pelangganNama"),
                            res.getString("transaksi.batas_waktu"),
                            statusText
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
    
    private void cariData2(String kataKunci) {
        DefaultTableModel tbl = (DefaultTableModel) tbl2.getModel();
        tbl.setRowCount(0); // Bersihkan baris yang sudah ada

        try {
            // Gunakan prepared statement untuk menghindari SQL injection
            String query = "SELECT \n" +
            "    transaksi.no_transaksi, \n" +
            "    pelanggan.nama AS pelangganNama, \n" +
            "    transaksi.batas_waktu, \n" +
            "    transaksi.status_laundry, \n" +
            "    transaksi.alamat_pengiriman \n" +
            "FROM \n" +
            "    transaksi \n" +
            "JOIN \n" +
            "    pelanggan ON transaksi.id_pelanggan = pelanggan.id_pelanggan\n" +
            "WHERE \n" +
            "    transaksi.statusPengiriman = 1\n" +
            "    AND \n" +
            "    (transaksi.no_transaksi LIKE ? OR pelanggan.nama LIKE ? OR transaksi.batas_waktu LIKE ? OR transaksi.alamat_pengiriman LIKE ? )\n" +
            "GROUP BY \n" +
            "    transaksi.no_transaksi";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, "%" + kataKunci + "%");
                pstmt.setString(2, "%" + kataKunci + "%");
                pstmt.setString(3, "%" + kataKunci + "%");
                pstmt.setString(4, "%" + kataKunci + "%");

                ResultSet res = pstmt.executeQuery();

                while (res.next()) {
                    int status = res.getInt("transaksi.status_laundry");
                    String statusText;

                    if (status == 2) {
                        statusText = "DIAMBIL";
                    } else if (status == 3) {
                        statusText = "Selesai";
                    } else if (status == 0) {
                        statusText = "Baru";
                    } else if (status == 1) {
                        statusText = "Proses"; // Assuming 1 corresponds to "Proses"
                    } else {
                        statusText = "Sudah Lewat";
                    }

                    tbl.addRow(new Object[]{
                            res.getString("transaksi.no_transaksi"),
                            res.getString("pelangganNama"),
                            res.getString("transaksi.alamat_pengiriman"),
                            res.getString("transaksi.batas_waktu"),
                            statusText
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SearchB3;
    private javax.swing.JButton SearchB4;
    private javax.swing.JButton Show1;
    private javax.swing.JLabel Tahun;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnSelesai2;
    private com.raven.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblMember;
    private javax.swing.JLabel lblPegawai;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JLabel lblTransaksi1;
    private javax.swing.JButton show2;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JTextField txt_search1;
    private javax.swing.JTextField txt_search2;
    // End of variables declaration//GEN-END:variables
}