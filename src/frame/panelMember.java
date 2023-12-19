
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader; 
import koneksi.koneksi;



public class panelMember extends javax.swing.JPanel {
    private Connection con;
    private Statement stat;
    private ResultSet res;
    private String t;    
    private static LocalDate lastExecutionDate = null;
       
    public panelMember() {
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
        
        tambahTanggalMember();
        batasWaktuSetmember();
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
        txt_nama.setText("");
        txt_alamat.setText("");
        txt_nohp.setText("");     
        
    }
    
    private void tabel(){
    // Set show grid untuk menampilkan garis pembatas
    Tb_member.setShowGrid(true);

    // Set show horizontal lines dan show vertical lines
    Tb_member.setShowHorizontalLines(true);
    Tb_member.setShowVerticalLines(true);

     DefaultTableModel t = new DefaultTableModel() {
        // ... (kode yang ada sebelumnya)

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Membuat sel tidak dapat diedit langsung di tabel
        }
    };
    t.addColumn("Id Member");
    t.addColumn("Nama Member");
    t.addColumn("Alamat");
    t.addColumn("No HP");
    t.addColumn("Tgl Pembuatan");
    t.addColumn("Tgl Kadulrasa");
    t.addColumn("Status Member");
    Tb_member.setModel(t);
    try {
        res = stat.executeQuery("SELECT * from member");
        int rowCount = 0;
 
        while (res.next()) {
            int statusMember = res.getInt("statusMember");
            if (statusMember == 0) {
                 t.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("tanggalPembuatan"),
                        res.getString("batas_waktu"),
                        "MASIH BERLAKU"
                });
            } else {
                 t.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("tanggalPembuatan"),
                        res.getString("batas_waktu"),
                        "SUDAH KADULRASA"
                });
            }
               
            if (rowCount == 0) {
                Tb_member.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer());
                Tb_member.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer());
                Tb_member.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer());
                Tb_member.getColumnModel().getColumn(3).setCellRenderer(new CustomTableCellRenderer());
                Tb_member.getColumnModel().getColumn(4).setCellRenderer(new CustomTableCellRenderer());
            }
            rowCount++;
        }
        // Menetapkan jumlah data ke JLabel
        jLabel1member.setText("Jumlah Data: " + rowCount);
    } catch (Exception e) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, e);
    }
    // Set rata tengah (centered) untuk semua sel di tabel
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    for (int i = 0; i < Tb_member.getColumnCount(); i++) {
        Tb_member.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    // Set rata tengah (centered) untuk header kolom
    JTableHeader header = Tb_member.getTableHeader();
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
    DefaultTableModel model = (DefaultTableModel) Tb_member.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        // Use prepared statement to avoid SQL injection
        String query = "SELECT * FROM member WHERE id_member LIKE ? OR nama LIKE ? OR alamat LIKE ? OR no_hp LIKE ? OR batas_waktu LIKE ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 1; i <= 5; i++) {
                pstmt.setString(i, "%" + kataKunci + "%");
            }
            res = pstmt.executeQuery();

            while (res.next()) {
                int status = res.getInt("statusMember");
                String statusText;

                if (status == 0) {
                    statusText = "MASIH BERLAKU";
                } else {
                    statusText = "SUDAH KADALUARSA";
                }

                model.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("tanggalPembuatan"),
                        res.getString("batas_waktu"),
                        statusText
                        // Add other columns as needed
                });
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mencari");
        // Handle or log the exception appropriately
    }
}
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng_status = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tb_member = new javax.swing.JTable();
        btn_simpan1 = new javax.swing.JButton();
        btn_edit1 = new javax.swing.JButton();
        btn_hapus1 = new javax.swing.JButton();
        btn_clear1 = new javax.swing.JButton();
        jLabel4member = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel6member = new javax.swing.JLabel();
        jLabel7member = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        txt_nohp = new javax.swing.JTextField();
        btn_lihat1 = new javax.swing.JButton();
        txt_cari1 = new javax.swing.JTextField();
        jLabel1member = new javax.swing.JLabel();
        jPanel1member = new javax.swing.JPanel();
        jLabel2member = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3member = new javax.swing.JLabel();
        jLabel6member1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spinner_tanggal = new javax.swing.JSpinner();
        Kadaluarsa = new javax.swing.JCheckBox();
        Berlaku = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));

        Tb_member.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Member", "Nama Member", "Alamat", "Ho HP", "Batas Waktu"
            }
        ));
        Tb_member.setRowHeight(30);
        Tb_member.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Tb_memberMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Tb_member);

        btn_simpan1.setBackground(new java.awt.Color(51, 0, 153));
        btn_simpan1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btn_simpan1.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan1.setText("Simpan");
        btn_simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_simpan1MousePressed(evt);
            }
        });
        btn_simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan1ActionPerformed(evt);
            }
        });

        btn_edit1.setBackground(new java.awt.Color(153, 102, 0));
        btn_edit1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_edit1.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit1.setText("Edit");
        btn_edit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_edit1MousePressed(evt);
            }
        });
        btn_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit1ActionPerformed(evt);
            }
        });

        btn_hapus1.setBackground(new java.awt.Color(204, 51, 0));
        btn_hapus1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_hapus1.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus1.setText("Hapus");
        btn_hapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hapus1MousePressed(evt);
            }
        });
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });

        btn_clear1.setBackground(new java.awt.Color(0, 102, 102));
        btn_clear1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btn_clear1.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear1.setText("  Clear  ");
        btn_clear1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear1ActionPerformed(evt);
            }
        });

        jLabel4member.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4member.setText("Nama Member");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel10.setText(":");

        txt_nama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6member.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6member.setText("No HP");

        jLabel7member.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7member.setText("Alamat");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel11.setText(":");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel12.setText(":");

        txt_alamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_nohp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_lihat1.setBackground(new java.awt.Color(0, 153, 204));
        btn_lihat1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_lihat1.setForeground(new java.awt.Color(255, 255, 255));
        btn_lihat1.setText("Lihat");
        btn_lihat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihat1ActionPerformed(evt);
            }
        });

        txt_cari1.setToolTipText("");
        txt_cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cari1ActionPerformed(evt);
            }
        });

        jLabel1member.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1member.setText("jlabel1");

        jPanel1member.setBackground(new java.awt.Color(39, 159, 136));

        jLabel2member.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2member.setText("Tambahkan Member");

        javax.swing.GroupLayout jPanel1memberLayout = new javax.swing.GroupLayout(jPanel1member);
        jPanel1member.setLayout(jPanel1memberLayout);
        jPanel1memberLayout.setHorizontalGroup(
            jPanel1memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1memberLayout.createSequentialGroup()
                .addGap(432, 432, 432)
                .addComponent(jLabel2member)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1memberLayout.setVerticalGroup(
            jPanel1memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2member, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel3member.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3member.setText("Cari");

        jLabel6member1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6member1.setText("Batas Waktu");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel13.setText(":");

        spinner_tanggal.setModel(new javax.swing.SpinnerDateModel());

        Kadaluarsa.setBackground(new java.awt.Color(255, 255, 255));
        Kadaluarsa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Kadaluarsa.setText("Kadaluarsa");
        Kadaluarsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KadaluarsaActionPerformed(evt);
            }
        });

        Berlaku.setBackground(new java.awt.Color(255, 255, 255));
        Berlaku.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Berlaku.setText("Berlaku");
        Berlaku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BerlakuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1member, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel4member)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel7member)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel6member)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(btn_simpan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_clear1))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel6member1)
                .addGap(16, 16, 16)
                .addComponent(jLabel13)
                .addGap(12, 12, 12)
                .addComponent(spinner_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(btn_hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_edit1)
                .addGap(10, 10, 10)
                .addComponent(btn_lihat1)
                .addGap(40, 40, 40)
                .addComponent(jLabel3member)
                .addGap(2, 2, 2)
                .addComponent(txt_cari1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1member)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Berlaku)
                .addGap(27, 27, 27)
                .addComponent(Kadaluarsa)
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1member, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4member)
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7member))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6member))
                    .addComponent(jLabel12)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6member1))
                    .addComponent(jLabel13)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(spinner_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan1)
                    .addComponent(btn_clear1))
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_hapus1)
                    .addComponent(btn_edit1)
                    .addComponent(btn_lihat1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3member))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txt_cari1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1member)
                            .addComponent(Berlaku)
                            .addComponent(Kadaluarsa))))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpan1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan1MousePressed

    private void btn_simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan1ActionPerformed
    try {
        // Get values from GUI components
        java.util.Date batas_waktu = (java.util.Date) spinner_tanggal.getValue();
        String nama = txt_nama.getText();
        String alamat = txt_alamat.getText();
        String no_hp = txt_nohp.getText();

        // Validate inputs
        if (nama.isEmpty() || alamat.isEmpty() || no_hp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama, Alamat, dan No HP tidak boleh kosong");
            return;
        }

        // Validate that no_hp contains only numeric values
        if (!no_hp.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Nomor HP harus berupa angka");
            return;
        }

        // Convert date to Timestamp
        Timestamp timestamp = new Timestamp(batas_waktu.getTime());

    if (t == null) {
       // New record insertion
       String insertQuery = "INSERT INTO member(nama, alamat, no_hp, tanggalDaftar, tanggalPembuatan, batas_waktu, statusMember) VALUES (?, ?, ?, ?, ?, ?, ?)";
       try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
           pstmt.setString(1, nama);
           pstmt.setString(2, alamat);
           pstmt.setString(3, no_hp);
           pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Assuming you want to set the current timestamp for "tanggalDaftar"
           pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
           pstmt.setTimestamp(6, timestamp);
           pstmt.setInt(7, 0);

           int affectedRows = pstmt.executeUpdate();
           if (affectedRows > 0) {
               JOptionPane.showMessageDialog(null, "Data baru berhasil disimpan");
               batasWaktuSetmember();
           } else {
               JOptionPane.showMessageDialog(null, "Gagal menyimpan data baru");
           }
       }
   } else {
    // Update existing record
    String updateQuery = "UPDATE member SET nama=?, alamat=?, no_hp=?, batas_waktu=? WHERE id_member=?";
    try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
        pstmt.setString(1, nama);
        pstmt.setString(2, alamat);
        pstmt.setString(3, no_hp);
        pstmt.setTimestamp(4, timestamp);
        pstmt.setInt(5, Integer.parseInt(t));

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
            batasWaktuSetmember();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal memperbarui data");
        }
    }
}

        // Refresh the table or perform any other necessary operations
        tabel();
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception stack trace for debugging
        JOptionPane.showMessageDialog(null, "Gagal Memperbarui Data: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_simpan1ActionPerformed
private void tampilkanDataBerdasarkanID(int idMember) {
  try {
        res = stat.executeQuery("SELECT * FROM member WHERE id_member='" + idMember + "'");
        while (res.next()) {
            // Assuming "tgl_pengeluaran" is a Date column in the database
            java.sql.Date sqlDate = res.getDate("batas_waktu");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            // Assuming that spinner_tanggal is a JSpinner
            spinner_tanggal.setValue(utilDate);
            // Replace these column names with actual column names in your table
            txt_nama.setText(res.getString("nama"));
            txt_alamat.setText(res.getString("alamat"));
            txt_nohp.setText(String.valueOf(res.getInt("no_hp")));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    private void btn_edit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit1MousePressed

    }//GEN-LAST:event_btn_edit1MousePressed

    private void btn_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit1ActionPerformed
// Dapatkan baris yang dipilih
    int row = Tb_member.getSelectedRow(); 

    // Pastikan ada baris yang dipilih
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit");
        return;
    }
    // Dapatkan ID Member dari kolom pertama (indeks 0)
    int idMember = Integer.parseInt(Tb_member.getValueAt(row, 0).toString());
    // Ambil nilai dari database dan tampilkan di JTextField
    tampilkanDataBerdasarkanID(idMember);

    // Simpan ID member yang akan diupdate (digunakan pada aksi btn_simpanActionPerformed)
    t = Integer.toString(idMember);
    
     tabel();
    }//GEN-LAST:event_btn_edit1ActionPerformed

    private void btn_lihat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihat1ActionPerformed
    // Dapatkan baris yang dipilih
    int barisTerpilih = Tb_member.getSelectedRow();

    // Pastikan ada baris yang dipilih
    if (barisTerpilih == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang akan dilihat");
        return;
    }

    // Dapatkan ID Member dari kolom pertama (indeks 0)
    int idMember = Integer.parseInt(Tb_member.getValueAt(barisTerpilih, 0).toString());

    // Tampilkan detail data di sini, misalnya dengan menggunakan JOptionPane
    tampilkanDetailMember(idMember);
}

// Method untuk menampilkan detail Member
private void tampilkanDetailMember(int idMember) {
    try {
        // Query database untuk mendapatkan detail Member berdasarkan ID
        String query = "SELECT * FROM member WHERE id_member = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idMember);
            res = pstmt.executeQuery();

            // Tampilkan hasil query
            if (res.next()) {
                String detail = "ID Member : " + res.getString("id_member") + "\n"
                        + "Nama: " + res.getString("nama") + "\n"
                        + "Alamat: " + res.getString("alamat") + "\n"
                        + "No. HP: " + res.getString("no_hp") + "\n"
                        + "Batas Waktu: " + res.getString("batas_waktu");

                // Tampilkan detail menggunakan JOptionPane
                JOptionPane.showMessageDialog(this, detail, "Detail Member", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan detail: " );
    }
    }//GEN-LAST:event_btn_lihat1ActionPerformed

    private void Tb_memberMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tb_memberMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Tb_memberMouseReleased

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
int row = Tb_member.getSelectedRow();

// Ensure a row is selected
if (row == -1) {
    JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus");
    return;
}

// Get the ID Member from the first column (index 0)
int idMember = Integer.parseInt(Tb_member.getValueAt(row, 0).toString());

try {
    // Delete data from the database
    String query = "DELETE FROM member WHERE id_member = ?";
    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setInt(1, idMember);
        pstmt.executeUpdate();
    }

    // Remove row from the table
    DefaultTableModel model = (DefaultTableModel) Tb_member.getModel();
    model.removeRow(row);

    // Update the number of data
    int rowCount = model.getRowCount();
    jLabel1member.setText("Jumlah Data: " + rowCount);

    JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, e );
}
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void btn_hapus1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus1MousePressed

    private void txt_cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cari1ActionPerformed
        String kataKunci = txt_cari1.getText();
        cariData(kataKunci);
    }//GEN-LAST:event_txt_cari1ActionPerformed

    private void btn_clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear1ActionPerformed
        kosongkan();
        t = null;
    }//GEN-LAST:event_btn_clear1ActionPerformed

    private void KadaluarsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KadaluarsaActionPerformed
    Berlaku.setSelected(false);
    DefaultTableModel model = (DefaultTableModel) Tb_member.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        res = stat.executeQuery("SELECT * FROM member");
        int rowCount = 0;

        while (res.next()) {
            int statusMember = res.getInt("statusMember");
            if (Kadaluarsa.isSelected() && statusMember == 1) {
                model.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("tanggalPembuatan"),
                        res.getString("batas_waktu"),
                        "SUDAH KADALUARSA"
                });
                rowCount++;
            } else if (!Kadaluarsa.isSelected()) {
                model.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("tanggalPembuatan"),
                        res.getString("batas_waktu"),
                        (statusMember == 0) ? "MASIH BERLAKU" : "SUDAH KADALUARSA"
                });
                rowCount++;
            }
        }

        jLabel1member.setText("Jumlah Data: " + rowCount);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_KadaluarsaActionPerformed

    private void BerlakuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BerlakuActionPerformed
    Kadaluarsa.setSelected(false);
    DefaultTableModel model = (DefaultTableModel) Tb_member.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        res = stat.executeQuery("SELECT * FROM member");
        int rowCount = 0;

        while (res.next()) {
            int statusMember = res.getInt("statusMember");
            if (Berlaku.isSelected() && statusMember == 0) {
                model.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("tanggalPembuatan"),
                        res.getString("batas_waktu"),
                        "MASIH BERLAKU"
                });
                rowCount++;
            } else if (!Berlaku.isSelected()) {
                model.addRow(new Object[]{
                        res.getString("id_member"),
                        res.getString("nama"),
                        res.getString("alamat"),
                        res.getString("no_hp"),
                        res.getString("batas_waktu"),
                        res.getString("tanggalPembuatan"),
                        (statusMember == 0) ? "MASIH BERLAKU" : "SUDAH KADALUARSA"
                });
                rowCount++;
            }
        }

        jLabel1member.setText("Jumlah Data: " + rowCount);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_BerlakuActionPerformed
private void batasWaktuSetmember() {
    try {
        String sql = "UPDATE member SET statusMember = 1 WHERE tanggalDaftar >= batas_waktu";
        java.sql.Connection conn = (Connection) koneksi.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.executeUpdate();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal Memperbarui Data");
        System.out.println("e: " + e);
    }
}

  public static void tambahTanggalMember() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every day at any hour
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Check if the task has already run today
                if (lastExecutionDate != null && lastExecutionDate.equals(LocalDate.now())) {
                    System.out.println("Task already executed today at: " + LocalTime.now());
                    System.out.println("Try Tomorrow....");
                    return; // Exit if the task has already run today
                }
 
                // Update all rows in the member table
                String updateSql = "UPDATE member\n" +
                                    "SET tanggalDaftar = DATE_ADD(tanggalDaftar, INTERVAL 1 DAY)\n" +
                                    "WHERE DATE(tanggalDaftar) != CURDATE();";
                java.sql.Connection conn = (Connection) koneksi.configDB();

                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.executeUpdate();
                }

                // Update the last execution date
                lastExecutionDate = LocalDate.now();

                // Print task execution information
                System.out.println("Task executed at: " + LocalTime.now());
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }, 0, 1, TimeUnit.DAYS);
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Berlaku;
    private javax.swing.JCheckBox Kadaluarsa;
    private javax.swing.JTable Tb_member;
    private javax.swing.JButton btn_clear1;
    private javax.swing.JButton btn_edit1;
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_lihat1;
    private javax.swing.JButton btn_simpan1;
    private javax.swing.ButtonGroup btng_status;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel1member;
    private javax.swing.JLabel jLabel2member;
    private javax.swing.JLabel jLabel3member;
    private javax.swing.JLabel jLabel4member;
    private javax.swing.JLabel jLabel6member;
    private javax.swing.JLabel jLabel6member1;
    private javax.swing.JLabel jLabel7member;
    private javax.swing.JPanel jPanel1member;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner spinner_tanggal;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari1;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nohp;
    // End of variables declaration//GEN-END:variables
}
