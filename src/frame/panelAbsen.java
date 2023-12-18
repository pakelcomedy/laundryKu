package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class panelAbsen extends javax.swing.JPanel {
    private Timer timer;
    private Timer t;

    public panelAbsen() {
        initComponents();
        initializeTable(Terlambat, "Terlambat");
        initializeTable(TepatWaktu, "TepatWaktu");

        dt();
        times();
        setupTimer();
        JumlahPegawai();
       
    }

    private void initializeTable(JTable table, String status) {
        DefaultTableModel model = loadAttendanceData(status);
        table.setModel(model);
        setTableAlignment(table);
        updateStatusCount(table, status);
    }

    private void setupTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();
    }

    private void updateClock() {
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        String formattedTime = timeFormat.format(currentDate);
        lbl_time.setText(formattedTime);
    }

    private void updateStatusCount(JTable table, String status) {
        int rowCount = table.getRowCount();
        String countLabel = (status.equals("Terlambat")) ? "TerlambatJ" : "HadirJ";
        updateCountLabel(countLabel, rowCount);
    }

    private void updateCountLabel(String labelName, int count) {
        String countAsString = String.valueOf(count); // Convert int to String
        if (labelName.equals("TerlambatJ")) {
            TerlambatJ.setText(countAsString);
        } else if (labelName.equals("HadirJ")) {
            HadirJ.setText(countAsString);
        }
    }

    private void setTableAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private DefaultTableModel loadAttendanceData(String status) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Jam Masuk");
        model.addColumn("Nama Pegawai");
        model.addColumn("Status");

        try {
            int no = 1;
            String sql = "SELECT * FROM absensi";
            try (Connection conn = koneksi.configDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet res = pstmt.executeQuery()) {

                while (res.next()) {
                    String waktuAbsensi = res.getString("waktu_absensi");
                    String username = res.getString("Username");
                    String statusAbsensi = res.getString("status_absensi");

                    boolean isLaterThanSeven = isTimeLaterThan(waktuAbsensi, "07:00");
                    if ((status.equals("Terlambat") && isLaterThanSeven) ||
                            (status.equals("TepatWaktu") && !isLaterThanSeven)) {
                        model.addRow(new Object[]{no++, waktuAbsensi, username, statusAbsensi});
                    }
                }
            }
            updateCountLabel("HadirJ", model.getRowCount());
        } catch (Exception e) {
            handleException("Error loading data", e);
        }

        return model;
    }
    
    private boolean isTimeLaterThan(String time, String targetTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date timeDate = sdf.parse(time);
            Date targetTimeDate = sdf.parse(targetTime);

            return timeDate.compareTo(targetTimeDate) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void handleException(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        // Optionally, display a dialog or log the exception to a file.
    }

    public void dt() {
        Date currentDate = new Date();
        Locale indonesianLocale = new Locale("id", "ID");

        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE dd MMMM", indonesianLocale);
        String formattedDate = sdfDate.format(currentDate);
        lbl_date.setText(formattedDate);

        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        String formattedYear = sdfYear.format(currentDate);
        lbl_year.setText(formattedYear);
    }

    public void times() {
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss a");
                String tt = st.format(dt);
                lbl_time.setText(tt);
            }
        });
        t.start();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_jumlahuser = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        TerlambatJ = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        HadirJ = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbl_date = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        lbl_year = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        search1 = new javax.swing.JTextField();
        Delete1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Terlambat = new javax.swing.JTable();
        SearchB1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        search2 = new javax.swing.JTextField();
        Delete2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TepatWaktu = new javax.swing.JTable();
        SearchB2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1090, 639));

        jPanel2.setBackground(new java.awt.Color(15, 115, 216));
        jPanel2.setPreferredSize(new java.awt.Dimension(237, 149));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/jumlah pegawai.png"))); // NOI18N
        jLabel7.setText("Jumlah Pegawai");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(14, 98, 183));
        jPanel3.setPreferredSize(new java.awt.Dimension(237, 36));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, 40));

        lbl_jumlahuser.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbl_jumlahuser.setForeground(new java.awt.Color(255, 255, 255));
        lbl_jumlahuser.setText("-");
        jPanel2.add(lbl_jumlahuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(237, 149));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/terlambat (meeting Time.png"))); // NOI18N
        jLabel8.setText("Terlambat");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 40));

        jPanel5.setBackground(new java.awt.Color(240, 0, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(237, 36));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Data Hari Ini                                                  ");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 240, 40));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, 40));

        TerlambatJ.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        TerlambatJ.setForeground(new java.awt.Color(255, 255, 255));
        TerlambatJ.setText("-");
        jPanel4.add(TerlambatJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jPanel6.setBackground(new java.awt.Color(6, 176, 82));
        jPanel6.setPreferredSize(new java.awt.Dimension(237, 149));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Checked User Male(hadir).png"))); // NOI18N
        jLabel9.setText("Hadir");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel7.setBackground(new java.awt.Color(0, 152, 68));
        jPanel7.setPreferredSize(new java.awt.Dimension(237, 36));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Data Hari Ini                                                 ");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 237, 40));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, 40));

        HadirJ.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        HadirJ.setForeground(new java.awt.Color(255, 255, 255));
        HadirJ.setText("-");
        jPanel6.add(HadirJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 20, -1));

        jPanel8.setBackground(new java.awt.Color(136, 139, 137));
        jPanel8.setPreferredSize(new java.awt.Dimension(235, 203));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Clock.png"))); // NOI18N
        jLabel12.setText("Hari Ini");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel9.setBackground(new java.awt.Color(105, 112, 107));
        jPanel9.setPreferredSize(new java.awt.Dimension(237, 36));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, 50));

        lbl_date.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_date.setText("-");
        jPanel8.add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        lbl_time.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_time.setForeground(new java.awt.Color(255, 255, 255));
        lbl_time.setText("-");
        jPanel8.add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lbl_year.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_year.setForeground(new java.awt.Color(255, 255, 255));
        lbl_year.setText("-");
        jPanel8.add(lbl_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel10.setPreferredSize(new java.awt.Dimension(493, 284));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Meeting Time(black).png"))); // NOI18N
        jLabel10.setText("Pegawai Hadir Terlambat ");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel12.setBackground(new java.awt.Color(217, 217, 217));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel12.setPreferredSize(new java.awt.Dimension(493, 48));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search1ActionPerformed(evt);
            }
        });
        jPanel10.add(search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 120, -1));

        Delete1.setBackground(new java.awt.Color(153, 0, 0));
        Delete1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Delete1.setForeground(new java.awt.Color(255, 255, 255));
        Delete1.setText("Delete");
        Delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1ActionPerformed(evt);
            }
        });
        jPanel10.add(Delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        Terlambat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Terlambat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Jam Masuk", "Nama Pegawai", "Status"
            }
        ));
        Terlambat.setRowHeight(30);
        jScrollPane5.setViewportView(Terlambat);
        if (Terlambat.getColumnModel().getColumnCount() > 0) {
            Terlambat.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 430, 130));

        SearchB1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        SearchB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/ic_baseline-search.png"))); // NOI18N
        SearchB1.setText("Search");
        SearchB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchB1ActionPerformed(evt);
            }
        });
        jPanel10.add(SearchB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, 20));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel11.setPreferredSize(new java.awt.Dimension(493, 284));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Checked User Male(hadir Black).png"))); // NOI18N
        jLabel11.setText("Pegawai Hadir Tepat Waktu ");
        jPanel11.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel13.setBackground(new java.awt.Color(217, 217, 217));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel13.setPreferredSize(new java.awt.Dimension(493, 48));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel11.add(search2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 120, -1));

        Delete2.setBackground(new java.awt.Color(153, 0, 0));
        Delete2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Delete2.setForeground(new java.awt.Color(255, 255, 255));
        Delete2.setText("Delete");
        Delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2ActionPerformed(evt);
            }
        });
        jPanel11.add(Delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        TepatWaktu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Jam Masuk", "Nama Pegawai", "Status"
            }
        ));
        TepatWaktu.setRowHeight(30);
        jScrollPane4.setViewportView(TepatWaktu);
        if (TepatWaktu.getColumnModel().getColumnCount() > 0) {
            TepatWaktu.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel11.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 430, 130));

        SearchB2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        SearchB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/ic_baseline-search.png"))); // NOI18N
        SearchB2.setText("Search");
        SearchB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchB2ActionPerformed(evt);
            }
        });
        jPanel11.add(SearchB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 36, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 400, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Delete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2ActionPerformed
    int selectedRow = TepatWaktu.getSelectedRow();

    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) TepatWaktu.getModel();
        int modelRow = TepatWaktu.convertRowIndexToModel(selectedRow);

        String usernameToDelete = (String) model.getValueAt(modelRow, 2);

        model.removeRow(modelRow);
        deleteRowFromDatabase(usernameToDelete);
    } else {
        JOptionPane.showMessageDialog(this, "Tolong pilih baris yang akan dihapus!");
    }
    }//GEN-LAST:event_Delete2ActionPerformed
    private void Delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1ActionPerformed
    int selectedRow = Terlambat.getSelectedRow();

    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) Terlambat.getModel();
        int modelRow = Terlambat.convertRowIndexToModel(selectedRow);

        String usernameToDelete = (String) model.getValueAt(modelRow, 2);

        model.removeRow(modelRow);
        deleteRowFromDatabase(usernameToDelete);
    } else {
        JOptionPane.showMessageDialog(this, "Tolong pilih baris yang akan dihapus!");
    }
    }//GEN-LAST:event_Delete1ActionPerformed

    private void SearchB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchB2ActionPerformed
        String searchText = search2.getText();
        clearTableSelection(TepatWaktu);
        for (int row = 0; row < TepatWaktu.getRowCount(); row++) {
            for (int col = 0; col < TepatWaktu.getColumnCount(); col++) {
                Object cellValue = TepatWaktu.getValueAt(row, col);
                if (cellValue != null && cellValue.toString().equals(searchText)) {
                    TepatWaktu.getSelectionModel().addSelectionInterval(row, row);
                    break;
                }
            }
        }
        }

        private void clearTableSelection(JTable table) {
            table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_SearchB2ActionPerformed

    private void SearchB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchB1ActionPerformed
        String searchText = search1.getText();
        clearSelection(Terlambat);
        for (int row = 0; row < Terlambat.getRowCount(); row++) {
            for (int col = 0; col < Terlambat.getColumnCount(); col++) {
                Object cellValue = Terlambat.getValueAt(row, col);
                if (cellValue != null && cellValue.toString().equals(searchText)) {
                    Terlambat.getSelectionModel().addSelectionInterval(row, row);
                    break;
                }
            }
        }
        }

        private void clearSelection(JTable table) {
            table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_SearchB1ActionPerformed

    private void search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search1ActionPerformed
private void deleteRowFromDatabase(String username) {
  try {
            Connection conn = koneksi.configDB();
            String sql = "DELETE FROM absensi WHERE Username = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Row deleted successfully from the database.");
        } catch (Exception e) {
            handleException("Error deleting row from the database", e);
        }
    }

private String JumlahPegawai() { 
    try {
        Connection conn = koneksi.configDB();
        String sql = "SELECT COUNT(*) as jumlahPegawai FROM user WHERE jabatan = 'Pegawai'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int jumlahPegawai = rs.getInt("jumlahPegawai");
                lbl_jumlahuser.setText(String.valueOf(jumlahPegawai));
                System.out.println("jumlahPegawai: " + jumlahPegawai);
            }
        }
    } catch (Exception e) {
        handleException("Error retrieving employee count from the database", e);
    }
    return null;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete1;
    private javax.swing.JButton Delete2;
    private javax.swing.JLabel HadirJ;
    private javax.swing.JButton SearchB1;
    private javax.swing.JButton SearchB2;
    private javax.swing.JTable TepatWaktu;
    private javax.swing.JTable Terlambat;
    private javax.swing.JLabel TerlambatJ;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_jumlahuser;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JLabel lbl_year;
    private javax.swing.JTextField search1;
    private javax.swing.JTextField search2;
    // End of variables declaration//GEN-END:variables
}