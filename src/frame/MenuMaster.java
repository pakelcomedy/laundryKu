package frame;

import java.sql.Connection;
import koneksi.koneksi;


public class MenuMaster extends javax.swing.JFrame {

    public MenuMaster() {
        initComponents();
          jLabel3.setText("Master >"); 
          lbl_username.setText(Login.nameUser);
          System.out.println("main: "+Login.nameUser);
          setButtonVisibility();  
    }
    private void setButtonVisibility() {
    String jaba = getJabatanFromDatabase(Login.nameUser);
    System.out.println("jaba: " + jaba);

    if ("Owner".equals(jaba)) {
        // If jabatan is "Owner", display all buttons
        btn_produk.setVisible(true);
        btn_member.setVisible(true);
        btn_bahanbaku.setVisible(true);
        btn_pengeluaran.setVisible(true);
        btn_user.setVisible(true);
    } else {
        // If jabatan is not "Owner", hide the btn_karyawan button
        btn_produk.setVisible(true);
        btn_member.setVisible(true);
        btn_bahanbaku.setVisible(true);
        btn_pengeluaran.setVisible(true);
        btn_user.setVisible(false);
    }
    }
        private String getJabatanFromDatabase(String Jabatan) {
        try {
            if (Jabatan != null) {
                String sql = "SELECT jabatan FROM user WHERE username = ?";
                java.sql.Connection conn = (Connection) koneksi.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, Jabatan);

                java.sql.ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    return rs.getString("jabatan");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception details for debugging
            // Handle the exception appropriately (e.g., log it, show an error message)
        }
        return null;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_pengeluaran = new javax.swing.JButton();
        btn_bahanbaku = new javax.swing.JButton();
        btn_user = new javax.swing.JButton();
        btn_member = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btn_pengeluaran1 = new javax.swing.JButton();
        lbl_username = new javax.swing.JButton();
        SignOut1 = new javax.swing.JButton();
        SignOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 1100, 30));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 1070, 640));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel3.setText("jLalbel2");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(545, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 1070, 80));

        jPanel1.setBackground(new java.awt.Color(39, 159, 136));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Rectangle 264 (2).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 230, 130));

        btn_pengeluaran.setBackground(new java.awt.Color(39, 159, 136));
        btn_pengeluaran.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_pengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        btn_pengeluaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/icon kembali.png"))); // NOI18N
        btn_pengeluaran.setText("Back");
        btn_pengeluaran.setActionCommand("Transaksi");
        btn_pengeluaran.setBorder(null);
        btn_pengeluaran.setBorderPainted(false);
        btn_pengeluaran.setContentAreaFilled(false);
        btn_pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengeluaranActionPerformed(evt);
            }
        });
        jPanel1.add(btn_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        btn_bahanbaku.setBackground(new java.awt.Color(39, 159, 136));
        btn_bahanbaku.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_bahanbaku.setForeground(new java.awt.Color(255, 255, 255));
        btn_bahanbaku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Bahan Baku.png"))); // NOI18N
        btn_bahanbaku.setText("Bahan Baku");
        btn_bahanbaku.setActionCommand("Transaksi");
        btn_bahanbaku.setBorder(new javax.swing.border.MatteBorder(null));
        btn_bahanbaku.setBorderPainted(false);
        btn_bahanbaku.setContentAreaFilled(false);
        btn_bahanbaku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_bahanbakuMousePressed(evt);
            }
        });
        btn_bahanbaku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bahanbakuActionPerformed(evt);
            }
        });
        jPanel1.add(btn_bahanbaku, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        btn_user.setBackground(new java.awt.Color(39, 159, 136));
        btn_user.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_user.setForeground(new java.awt.Color(255, 255, 255));
        btn_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/user.png"))); // NOI18N
        btn_user.setText("User");
        btn_user.setBorder(null);
        btn_user.setBorderPainted(false);
        btn_user.setContentAreaFilled(false);
        btn_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_userMouseClicked(evt);
            }
        });
        btn_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_userActionPerformed(evt);
            }
        });
        jPanel1.add(btn_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        btn_member.setBackground(new java.awt.Color(39, 159, 136));
        btn_member.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_member.setForeground(new java.awt.Color(255, 255, 255));
        btn_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/member_1.png"))); // NOI18N
        btn_member.setText("Member");
        btn_member.setBorder(null);
        btn_member.setBorderPainted(false);
        btn_member.setContentAreaFilled(false);
        btn_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_memberActionPerformed(evt);
            }
        });
        jPanel1.add(btn_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        btn_produk.setBackground(new java.awt.Color(39, 159, 136));
        btn_produk.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_produk.setForeground(new java.awt.Color(255, 255, 255));
        btn_produk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/produk.png"))); // NOI18N
        btn_produk.setText("Produk");
        btn_produk.setBorder(null);
        btn_produk.setBorderPainted(false);
        btn_produk.setContentAreaFilled(false);
        btn_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_produkActionPerformed(evt);
            }
        });
        jPanel1.add(btn_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        btn_pengeluaran1.setBackground(new java.awt.Color(39, 159, 136));
        btn_pengeluaran1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_pengeluaran1.setForeground(new java.awt.Color(255, 255, 255));
        btn_pengeluaran1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/pengeluaran.png"))); // NOI18N
        btn_pengeluaran1.setText("Pengeluaran");
        btn_pengeluaran1.setActionCommand("Transaksi");
        btn_pengeluaran1.setBorder(null);
        btn_pengeluaran1.setBorderPainted(false);
        btn_pengeluaran1.setContentAreaFilled(false);
        btn_pengeluaran1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengeluaran1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_pengeluaran1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        lbl_username.setBackground(new java.awt.Color(39, 159, 136));
        lbl_username.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_username.setForeground(new java.awt.Color(255, 255, 255));
        lbl_username.setText("-");
        lbl_username.setActionCommand("Transaksi");
        lbl_username.setBorder(null);
        lbl_username.setBorderPainted(false);
        lbl_username.setContentAreaFilled(false);
        lbl_username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_usernameMousePressed(evt);
            }
        });
        lbl_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_usernameActionPerformed(evt);
            }
        });
        jPanel1.add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 640, 80, 30));

        SignOut1.setBackground(new java.awt.Color(39, 159, 136));
        SignOut1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        SignOut1.setForeground(new java.awt.Color(255, 255, 255));
        SignOut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/USer_1.png"))); // NOI18N
        SignOut1.setActionCommand("Transaksi");
        SignOut1.setBorder(null);
        SignOut1.setBorderPainted(false);
        SignOut1.setContentAreaFilled(false);
        SignOut1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SignOut1MousePressed(evt);
            }
        });
        SignOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignOut1ActionPerformed(evt);
            }
        });
        jPanel1.add(SignOut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, -1, -1));

        SignOut.setBackground(new java.awt.Color(39, 159, 136));
        SignOut.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        SignOut.setForeground(new java.awt.Color(255, 255, 255));
        SignOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui_componen/Sign Out.png"))); // NOI18N
        SignOut.setText(" Sign Out");
        SignOut.setActionCommand("Transaksi");
        SignOut.setBorder(null);
        SignOut.setBorderPainted(false);
        SignOut.setContentAreaFilled(false);
        SignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SignOutMousePressed(evt);
            }
        });
        SignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignOutActionPerformed(evt);
            }
        });
        jPanel1.add(SignOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 700, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengeluaranActionPerformed
 MenuUtama master = new MenuUtama();
            master.setVisible(true);
            this.dispose(); // Menutup JFrame pertama
    }//GEN-LAST:event_btn_pengeluaranActionPerformed

    private void btn_bahanbakuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bahanbakuMousePressed

    }//GEN-LAST:event_btn_bahanbakuMousePressed

    private void btn_bahanbakuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bahanbakuActionPerformed
          jLabel3.setText("Master > Bahan Baku");
           jTabbedPane2.removeAll();
        panelBahanBaku panel = new panelBahanBaku();
        jTabbedPane2.add(panel);
        panel.setVisible(true);
        jTabbedPane2.revalidate();
        jTabbedPane2.repaint();
    }//GEN-LAST:event_btn_bahanbakuActionPerformed

    private void btn_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_userActionPerformed
        jLabel3.setText("Master > User");
         jTabbedPane2.removeAll();
        panelUser panel = new panelUser();
        jTabbedPane2.add(panel);
        panel.setVisible(true);
        jTabbedPane2.revalidate();
        jTabbedPane2.repaint();
    }//GEN-LAST:event_btn_userActionPerformed

    private void btn_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_memberActionPerformed
         jLabel3.setText("Master > Member");
          jTabbedPane2.removeAll();
        panelMember panel = new panelMember();
        jTabbedPane2.add(panel);
        panel.setVisible(true);
        jTabbedPane2.revalidate();
        jTabbedPane2.repaint();
    }//GEN-LAST:event_btn_memberActionPerformed

    private void btn_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_produkActionPerformed
        jLabel3.setText("Master > Produk");     
        jTabbedPane2.removeAll();
        panelProduk panel = new panelProduk();
        jTabbedPane2.add(panel);
        panel.setVisible(true);
        jTabbedPane2.revalidate();
        jTabbedPane2.repaint();
    }//GEN-LAST:event_btn_produkActionPerformed

    private void btn_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMouseClicked

    }//GEN-LAST:event_btn_userMouseClicked

    private void btn_pengeluaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengeluaran1ActionPerformed
         jLabel3.setText("Master > Pengeluaran");
         jTabbedPane2.removeAll();
        panelPengeluaran panel = new panelPengeluaran();
        jTabbedPane2.add(panel);
        panel.setVisible(true);
        jTabbedPane2.revalidate();
        jTabbedPane2.repaint();
    }//GEN-LAST:event_btn_pengeluaran1ActionPerformed

    private void lbl_usernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_usernameMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_usernameMousePressed

    private void lbl_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_usernameActionPerformed

    private void SignOut1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignOut1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignOut1MousePressed

    private void SignOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOut1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignOut1ActionPerformed

    private void SignOutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignOutMousePressed
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_SignOutMousePressed

    private void SignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutActionPerformed

    }//GEN-LAST:event_SignOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuMaster().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignOut;
    private javax.swing.JButton SignOut1;
    private javax.swing.JButton btn_bahanbaku;
    private javax.swing.JButton btn_member;
    private javax.swing.JButton btn_pengeluaran;
    private javax.swing.JButton btn_pengeluaran1;
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton lbl_username;
    // End of variables declaration//GEN-END:variables
}
