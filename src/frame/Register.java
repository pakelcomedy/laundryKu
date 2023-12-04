package frame;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import koneksi.koneksi;

public class Register extends javax.swing.JFrame {
    public Register() {
        initComponents();
        boolean ownerExists = checkOwnerExists();
        jComboBox1.setVisible(!ownerExists);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_username = new javax.swing.JTextField();
        Login = new javax.swing.JButton();
        daftar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_hp = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_confirmPassword = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox<>();
        ckbox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setBackground(new java.awt.Color(237, 245, 224));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_username.setBorder(null);
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 310, -1));

        Login.setForeground(new java.awt.Color(0, 102, 204));
        Login.setText("Login Sekarang");
        Login.setBorderPainted(false);
        Login.setContentAreaFilled(false);
        Login.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 660, -1, 20));

        daftar.setBackground(new java.awt.Color(10, 86, 56));
        daftar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        daftar.setForeground(new java.awt.Color(255, 255, 255));
        daftar.setText("DAFTAR");
        daftar.setBorder(null);
        daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarActionPerformed(evt);
            }
        });
        getContentPane().add(daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 620, 120, 30));

        jLabel1.setText("Sudah Punya Akun?");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 660, 130, 20));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setText("No Hp");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("Username");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setText("Password");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, -1, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Confirm Password");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, -1, -1));

        txt_hp.setBackground(new java.awt.Color(237, 245, 224));
        txt_hp.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_hp.setBorder(null);
        txt_hp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hpFocusLost(evt);
            }
        });
        txt_hp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hpActionPerformed(evt);
            }
        });
        getContentPane().add(txt_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 310, 20));

        txt_password.setBackground(new java.awt.Color(237, 245, 224));
        txt_password.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_password.setBorder(null);
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passwordFocusLost(evt);
            }
        });
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 300, -1));

        txt_confirmPassword.setBackground(new java.awt.Color(237, 245, 224));
        txt_confirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_confirmPassword.setBorder(null);
        txt_confirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_confirmPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_confirmPasswordFocusLost(evt);
            }
        });
        getContentPane().add(txt_confirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 300, -1));

        jComboBox1.setBackground(new java.awt.Color(237, 245, 224));
        jComboBox1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Owner", "Pegawai" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 580, 100, 30));

        ckbox.setText("Show Password");
        ckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckboxActionPerformed(evt);
            }
        });
        getContentPane().add(ckbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Register.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        dispose();
        Login frameLogin = new Login();
        frameLogin.setVisible(true);
    }//GEN-LAST:event_LoginActionPerformed

    private void daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarActionPerformed
    if (txt_password.getText().equals(txt_confirmPassword.getText())) {
        try {
            // Check if "Owner" combo box is visible
            String selectedRole = jComboBox1.isVisible() ? jComboBox1.getSelectedItem().toString() : "Pegawai";

            String sql = "INSERT INTO user (username, password, no_hp, jabatan) VALUES ('" 
                    + txt_username.getText() + "','"
                    + txt_password.getText() + "','"
                    + txt_hp.getText() + "','"
                    + selectedRole + "')";

            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data");
            this.setVisible(false);
            new Login().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    } else {
        JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data");
    }
    }//GEN-LAST:event_daftarActionPerformed

    private void ckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckboxActionPerformed
        if(ckbox.isSelected()){
            txt_password.setEchoChar((char)0);
            txt_confirmPassword.setEchoChar((char)0);
        } else if (txt_password.getText().equals("") || txt_confirmPassword.getText().equals("") ) {
            txt_password.setEchoChar((char)0);
            txt_confirmPassword.setEchoChar((char)0);
        } else {
            txt_password.setEchoChar('*');
            txt_confirmPassword.setEchoChar('*');
        }  
    }//GEN-LAST:event_ckboxActionPerformed

    private void txt_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusGained
        if(txt_username.getText().equals("Isi Username")) {
            txt_username.setText("");
            txt_username.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_usernameFocusGained

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        if(txt_username.getText().equals("")) {
            txt_username.setText("Isi Username");
            txt_username.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_usernameFocusLost

    private void txt_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusGained
        if(txt_password.getText().equals("Isi Password")) {
            txt_password.setText("");
            txt_password.setForeground(new Color(0,0,0));
        } 
    }//GEN-LAST:event_txt_passwordFocusGained

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        if(txt_password.getText().equals("")) {
            txt_password.setText("Isi Password");
            txt_password.setEchoChar((char) 0);
            txt_password.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_passwordFocusLost

    private void txt_confirmPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_confirmPasswordFocusGained
        if(txt_confirmPassword.getText().equals("Isi Confirm Password")) {
            txt_confirmPassword.setText("");
            txt_confirmPassword.setForeground(new Color(0,0,0));
        } 
    }//GEN-LAST:event_txt_confirmPasswordFocusGained

    private void txt_confirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_confirmPasswordFocusLost
        // TODO add your handling code here:
         if(txt_confirmPassword.getText().equals("")) {
            txt_confirmPassword.setText("Isi Confirm Password");
            txt_confirmPassword.setEchoChar((char) 0);
            txt_confirmPassword.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_confirmPasswordFocusLost

    private void txt_hpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hpFocusGained
        if(txt_hp.getText().equals("Isi No Hp")) {
            txt_hp.setText("");
            txt_hp.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_hpFocusGained

    private void txt_hpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hpFocusLost
        if(txt_hp.getText().equals("")) {
            txt_hp.setText("Isi No Hp");
            txt_hp.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_hpFocusLost

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed

    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_hpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hpActionPerformed

    }//GEN-LAST:event_txt_hpActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }
    private boolean checkOwnerExists() {
    boolean ownerExists = false;
    String ownerRole = "Owner";

    try {
        // Establish database connection
        Connection conn = (Connection) koneksi.configDB();

        // Check if there is any user with "Owner" role
        String checkOwnerSql = "SELECT COUNT(*) FROM user WHERE jabatan = ?";
        java.sql.PreparedStatement checkOwnerPst = conn.prepareStatement(checkOwnerSql);
        checkOwnerPst.setString(1, ownerRole);
        java.sql.ResultSet rs = checkOwnerPst.executeQuery();

        // If the count is greater than 0, "Owner" already exists
        if (rs.next() && rs.getInt(1) > 0) {
            ownerExists = true;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }

    return ownerExists;
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JCheckBox ckbox;
    private javax.swing.JButton daftar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField txt_confirmPassword;
    private javax.swing.JTextField txt_hp;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
