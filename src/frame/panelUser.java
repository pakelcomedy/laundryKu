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
import koneksi.koneksi;

public class panelUser extends javax.swing.JPanel {
    private Connection con;
    private Statement stat;
    private ResultSet res;
    private String t;
       
    public panelUser() {
        koneksi();
        initComponents();
        kosongkan();
        tabel();
        
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
            (screenSize.width - frameSize.width) / 3,
            (screenSize.height -frameSize.height) / 4);
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
     
        txt_username.setText("");
        txt_password.setText("");
        txt_alamat.setText("");
        txt_nohp.setText("");
    }
    
    private void tabel(){
      // Set show grid untuk menampilkan garis pembatas
    tb_user.setShowGrid(true);

    // Set show horizontal lines dan show vertical lines
    tb_user.setShowHorizontalLines(true);
    tb_user.setShowVerticalLines(true);

DefaultTableModel t = new DefaultTableModel();
t.addColumn("Id User");
t.addColumn("Username");
t.addColumn("Password");
t.addColumn("Jabatan");
t.addColumn("Alamat");
t.addColumn("No HP");
tb_user.setModel(t);

try {
    res = stat.executeQuery("SELECT * FROM user");
    int rowCount = 0;

    while (res.next()) {
        t.addRow(new Object[]{
            res.getString("id_pegawai"),
            res.getString("Username"),
            res.getString("password"),
            res.getString("jabatan"),
            res.getString("alamat"),
            res.getString("no_hp"),
        });

        // Set cell renderer for the first row only
        if (rowCount == 0) {
            tb_user.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer());
            tb_user.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer());
            tb_user.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer());
            tb_user.getColumnModel().getColumn(3).setCellRenderer(new CustomTableCellRenderer());
            tb_user.getColumnModel().getColumn(4).setCellRenderer(new CustomTableCellRenderer());
            tb_user.getColumnModel().getColumn(5).setCellRenderer(new CustomTableCellRenderer());
        }

        rowCount++;

        // Update JLabel to display the current row count
        jLabel1.setText("Jumlah Data: " + rowCount);
    }
    } catch (Exception e) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, e);
    }

    // Set rata tengah (centered) untuk semua sel di tabel
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    for (int i = 0; i < tb_user.getColumnCount(); i++) {
        tb_user.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    // Set rata tengah (centered) untuk header kolom
    JTableHeader header = tb_user.getTableHeader();
    header.setDefaultRenderer(centerRenderer);
    header.setPreferredSize(new Dimension(100, 40)); // Sesuaikan dimensi header jika diperlukan
    header.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14)); // Sesuaikan font header jika diperlukan
}

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
    DefaultTableModel model = (DefaultTableModel) tb_user.getModel();
    model.setRowCount(0); // Bersihkan baris yang sudah ada

    try {
        // Gunakan prepared statement untuk menghindari SQL injection
        String query = "SELECT * FROM user WHERE id_pegawai LIKE ? OR Username LIKE ? OR password LIKE ? OR jabatan LIKE ? OR alamat LIKE ? OR no_hp LIKE ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 1; i <= 6; i++) {
                pstmt.setString(i, "%" + kataKunci + "%");
            }
            res = pstmt.executeQuery();
            
            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("id_pegawai"),
                    res.getString("Username"),
                    res.getString("password"),
                    res.getString("jabatan"),
                    res.getString("alamat") != null && !res.getString("alamat").isEmpty() ? res.getString("alamat") : "Kosong",
                    res.getString("no_hp")
                    // Tambahkan kolom lainnya sesuai kebutuhan
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
        tb_user = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        btn_lihat = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_nohp = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        cbx_jabatan = new javax.swing.JComboBox<String>();

        setBackground(new java.awt.Color(255, 255, 255));

        tb_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID User", "Username", "Password", "Jabatan", "Alamat", "No HP"
            }
        ));
        tb_user.setRowHeight(30);
        tb_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_userMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tb_user);

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
        jLabel4.setText("Username");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel10.setText(":");

        txt_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Jabatan");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Password");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel11.setText(":");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel12.setText(":");

        txt_password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jLabel2.setText("Tambahkan User");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(465, 465, 465)
                .addComponent(jLabel2)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Cari");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Alamat");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("No HP");

        txt_nohp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_nohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel13.setText(":");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel14.setText(":");

        txt_alamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbx_jabatan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbx_jabatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Owner", "Pegawai", " " }));
        cbx_jabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_jabatanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(464, 464, 464)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel1))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel12)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4))
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel14)
                        .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addComponent(jLabel11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel13)
                        .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cbx_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_simpan)
                            .addComponent(btn_clear))
                        .addGap(46, 46, 46)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_hapus)
                            .addComponent(btn_edit)
                            .addComponent(btn_lihat)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1)))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
    try {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String nohp = txt_nohp.getText();

        // Check if required fields are not null or empty
        if (username.isEmpty() || password.isEmpty() || nohp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username, Password, and No HP cannot be null or empty");
            return; // Exit the method if validation fails
        }

        // Check if the data with the specified username already exists
        String checkQuery = "SELECT id_Pegawai FROM user WHERE Username=?";
        try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
            checkStmt.setString(1, username);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                // Data with the given username already exists, perform update
                String updateQuery = "UPDATE user SET password=?, jabatan=?, alamat=?, no_hp=? WHERE Username=?";
                try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, password);
                    updateStmt.setString(2, cbx_jabatan.getSelectedItem().toString());
                    updateStmt.setString(3, txt_alamat.getText());
                    updateStmt.setString(4, nohp);
                    updateStmt.setString(5, username);
                    updateStmt.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Berhasil Memperbarui Data");
            } else {
                // Data with the given username does not exist, perform insert
                String insertQuery = "INSERT INTO user(Username, password, jabatan, alamat, no_hp) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, password);
                    insertStmt.setString(3, cbx_jabatan.getSelectedItem().toString());
                    insertStmt.setString(4, txt_alamat.getText());
                    insertStmt.setString(5, nohp);
                    int affectedRows = insertStmt.executeUpdate(); 

                    if (affectedRows > 0) {
                        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int idUser = generatedKeys.getInt(1);
                            // Handle the ID if needed
                            System.out.println("ID User baru: " + idUser);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data");
            }
        }
        tabel();
        kosongkan();
        // Additional logic as needed
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal memperbarui data! " + e.getMessage());
    }
    }//GEN-LAST:event_btn_simpanActionPerformed
private void tampilkanDataBerdasarkanID(int idUser) {
 try {
        res = stat.executeQuery("SELECT * FROM user WHERE id_pegawai='" + idUser+ "'");
        while (res.next()) {
            txt_username.setText(res.getString("Username"));
            txt_password.setText(res.getString("password"));
            cbx_jabatan.setSelectedItem(res.getString("jabatan"));
            txt_alamat.setText(res.getString("alamat"));
            txt_nohp.setText(res.getString("no_hp"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    private void btn_editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMousePressed

    }//GEN-LAST:event_btn_editMousePressed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
/// Dapatkan baris yang dipilih
    int row = tb_user.getSelectedRow(); 

    // Pastikan ada baris yang dipilih
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit");
        return;
    }
    // Dapatkan ID Bahan Baku dari kolom pertama (indeks 0)
    int idPegawai= Integer.parseInt(tb_user.getValueAt(row, 0).toString());

    // Ambil nilai dari database dan tampilkan di JTextField
    tampilkanDataBerdasarkanID(idPegawai);
 
    // Simpan ID Bahan Baku yang akan diupdate (digunakan pada aksi btn_simpanActionPerformed)
    t = Integer.toString(idPegawai);

    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
      kosongkan();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatActionPerformed
      // Dapatkan baris yang dipilih
    int barisTerpilih = tb_user.getSelectedRow();

    // Pastikan ada baris yang dipilih
    if (barisTerpilih == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan dilihat");
        return;
    }

    // Dapatkan ID Bahan Baku dari kolom pertama (indeks 0)
    int idUser = Integer.parseInt(tb_user.getValueAt(barisTerpilih, 0).toString());

    // Tampilkan detail data di sini, misalnya dengan menggunakan JOptionPane
    tampilkanDetailUser(idUser);
}

// Method untuk menampilkan detail Bahan Baku
private void tampilkanDetailUser(int idUser) {
    try {
        // Query database untuk mendapatkan detail Bahan Baku berdasarkan ID
        String query = "SELECT * FROM user WHERE id_pegawai = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idUser);
            res = pstmt.executeQuery();

            // Tampilkan hasil query
            if (res.next()) {
                String detail = "ID User: " + res.getString("id_pegawai") + "\n"
                        + "Username: " + res.getString("Username") + "\n"
                        + "Password: " + res.getString("password") + "\n"
                        + "Jabatan: " + res.getString("jabatan") + "\n"
                        + "Alamat: " + res.getString("alamat") + "\n"
                        + "No HP: " + res.getString("no_hp");

                // Tampilkan detail menggunakan JOptionPane
                JOptionPane.showMessageDialog(this, detail, "Detail User", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan detail: " );
    }
    
    }//GEN-LAST:event_btn_lihatActionPerformed

    private void tb_userMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_userMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_userMouseReleased

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // Dapatkan baris yang dipilih
        int row = tb_user.getSelectedRow();

        // Pastikan ada baris yang dipilih
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus");
            return;
        }

        // Dapatkan ID Bahan Baku dari kolom pertama (indeks 0)
        int idUser = Integer.parseInt(tb_user.getValueAt(row, 0).toString());

              // Hapus data dari database
        try {
            String query = "DELETE FROM user WHERE id_pegawai = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, idUser);
                pstmt.executeUpdate();
            }
            // Refresh tabel setelah penghapusan
            tabel();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: ");
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusMousePressed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        String kataKunci = txt_cari.getText();
        cariData(kataKunci);
    }//GEN-LAST:event_txt_cariActionPerformed

    private void cbx_jabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_jabatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_jabatanActionPerformed

    private void txt_nohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpActionPerformed
    }//GEN-LAST:event_txt_nohpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_lihat;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cbx_jabatan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tb_user;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_nohp;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
