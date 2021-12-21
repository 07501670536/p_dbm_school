
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminHome;

import static database.db.con;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bilal
 */
public class CoursAdmin extends javax.swing.JFrame {

//    public void returnNamarCours() {
//
//        try {
//            String query = "SELECT wana FROM teacher ";
//            PreparedStatement ps = con.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String item = rs.getString("wana");
//                jComboBoxCoursCoursName.addItem(item);
//
//            }
//        } catch (Exception e) {
//        }
//
//    }
//    public void returnCours() {
//
//        try {
//            String query = "SELECT fullName FROM teacher where wana=?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, (String) jComboBoxCoursCoursName.getSelectedItem());
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next() == false) {
//
//                JOptionPane.showMessageDialog(null, "course not avible");
//
//            } else {
//
//                jTextFieldAdminAddCoursCoursTeacher.setText(rs.getString("fullName"));
//
//            }
//
//        } catch (Exception e) {
//
//        }
//
//    }
    public int returnIDLogin() {
        int result = 0;
        try {

            String query = "SELECT Lid FROM login where usertype='admin'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String item = rs.getString("Lid");
//                jTextFieldAdminTeacheForeign.setText(item);
                result = Integer.parseInt(item);

            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    public void insertIntoCours() {

        int as = returnIDLogin();

//        String cTeacher = jTextFieldAdminAddCoursCoursTeacher.getText();
        String cName = String.valueOf(jComboBoxCoursCoursName.getSelectedItem());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String cStart = sdf.format(jDateChooserAdminAcoursCoursStart.getDate());
        String cEnd = sdf.format(jDateChooserAdminAcoursCoursEnd.getDate());
        String cOffers = jTextFieldAdminAddCoursCoursOffers.getText();
        String cCost = jTextFieldAdminAddCoursCoursCost.getText();
        String f = "" + as;

        try {

            String qury = "insert into cours VALUES (null,'" + cName + "','" + cStart + "','" + cEnd + "','" + cOffers + "','" + cCost + "','" + f + "')";
            PreparedStatement ps = con.prepareStatement(qury);
//            ps.setString(1, jTextFieldfistenameAddteacher.getText());
//            ps.setString(2, jTextFieldlasteNameAddteacher.getText());
//            ps.setString(3, jTextFieldTelAddTeacher.getText());
//            ps.setString(4,String.valueOf(jComboBoxWanaAddTeacher.getSelectedItem()));

            ps.executeUpdate(qury);

            JOptionPane.showMessageDialog(null, "data insert");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void showCours() {

        try {

            DefaultTableModel dm = (DefaultTableModel) jTableCoursAdmin.getModel();
            while (dm.getRowCount() > 0) {
                dm.removeRow(0);
            }

            String qury = "select * from cours";
            PreparedStatement ps = con.prepareStatement(qury);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String SCid = String.valueOf(rs.getInt("Cid"));
                String cName = rs.getString("cName");

                String cStart = rs.getString("cStart");
                String cEnd = rs.getString("cEnd");
                String cOffers = rs.getString("cOffers");
                String cCost = rs.getString("cCost");

                String tData[] = {SCid, cName, cStart, cEnd, cOffers, cCost};
                DefaultTableModel tbmodel = (DefaultTableModel) jTableCoursAdmin.getModel();

                tbmodel.addRow(tData);

            }
        } catch (Exception e) {
        }

    }

    public void deletrecord() {

        try {
            int row = jTableCoursAdmin.getSelectedRow();
            String cell = jTableCoursAdmin.getModel().getValueAt(row, 0).toString();
            String qury = "delete from cours where Cid=" + cell;
            PreparedStatement ps = con.prepareStatement(qury);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "delet");
        } catch (Exception e) {
        }

    }

    public void update() {
        int as = returnIDLogin();
        String cName = String.valueOf(jComboBoxCoursCoursName.getSelectedItem());
//        String cTeacher = String.valueOf(jComboBoxCoursCoursName.getSelectedItem());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String cStart = sdf.format(jDateChooserAdminAcoursCoursStart.getDate());
        String cEnd = sdf.format(jDateChooserAdminAcoursCoursEnd.getDate());
        String cOffers = jTextFieldAdminAddCoursCoursOffers.getText();
        String cCost = jTextFieldAdminAddCoursCoursCost.getText();
        String f = "" + as;
        try {
            int row = jTableCoursAdmin.getSelectedRow();
            String cell = jTableCoursAdmin.getModel().getValueAt(row, 0).toString();
            String qury = "update cours set cName='" + cName + "',cOffers='" + cOffers + "',cCost='" + cCost + "',cStart='" + cStart + "',cEnd='" + cEnd + "',F_KEYLid='" + f + "' where Cid=" + cell;
            PreparedStatement ps = con.prepareStatement(qury);
            ps.execute();

            JOptionPane.showMessageDialog(null, "data update");

            

        } catch (Exception e) {
        }
    }

    public void selectItem() {

        try {
            String query = "SELECT cName FROM cours";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String item = rs.getString("cName");
                jComboBoxCoursCoursName.addItem(item);

            }
        } catch (Exception e) {
        }

    }

    public CoursAdmin() {
        initComponents();
        Toolkit tool = getToolkit();
        Dimension size = tool.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldAdminAddCoursCoursCost = new javax.swing.JTextField();
        jTextFieldAdminAddCoursCoursOffers = new javax.swing.JTextField();
        jDateChooserAdminAcoursCoursEnd = new com.toedter.calendar.JDateChooser();
        jDateChooserAdminAcoursCoursStart = new com.toedter.calendar.JDateChooser();
        jButtonAdminAddCoursInsert = new javax.swing.JButton();
        jButtonAdminAddCoursBack1 = new javax.swing.JButton();
        jButtonAdminAddCoursBack2 = new javax.swing.JButton();
        jButtonAdminAddCoursBack = new javax.swing.JButton();
        jComboBoxCoursCoursName = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCoursAdmin = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Cours Name :");

        jLabel3.setBackground(new java.awt.Color(0, 0, 102));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Cours Start :");

        jLabel4.setBackground(new java.awt.Color(0, 0, 102));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Cours End :");

        jLabel5.setBackground(new java.awt.Color(0, 0, 102));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Cours Cost :");

        jLabel6.setBackground(new java.awt.Color(0, 0, 102));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Cours Offers :");

        jTextFieldAdminAddCoursCoursCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));

        jTextFieldAdminAddCoursCoursOffers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));

        jButtonAdminAddCoursInsert.setBackground(new java.awt.Color(0, 0, 102));
        jButtonAdminAddCoursInsert.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminAddCoursInsert.setText("Inser");
        jButtonAdminAddCoursInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminAddCoursInsertActionPerformed(evt);
            }
        });

        jButtonAdminAddCoursBack1.setBackground(new java.awt.Color(0, 0, 102));
        jButtonAdminAddCoursBack1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminAddCoursBack1.setText("Update");
        jButtonAdminAddCoursBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminAddCoursBack1ActionPerformed(evt);
            }
        });

        jButtonAdminAddCoursBack2.setBackground(new java.awt.Color(0, 0, 102));
        jButtonAdminAddCoursBack2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminAddCoursBack2.setText("Delete");
        jButtonAdminAddCoursBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminAddCoursBack2ActionPerformed(evt);
            }
        });

        jButtonAdminAddCoursBack.setBackground(new java.awt.Color(0, 0, 102));
        jButtonAdminAddCoursBack.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminAddCoursBack.setText("Backe");
        jButtonAdminAddCoursBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminAddCoursBackActionPerformed(evt);
            }
        });

        jComboBoxCoursCoursName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C++", "Java", "C#", "Python", "Dart" }));
        jComboBoxCoursCoursName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCoursCoursNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextFieldAdminAddCoursCoursCost, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAdminAddCoursCoursOffers, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDateChooserAdminAcoursCoursStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                .addComponent(jDateChooserAdminAcoursCoursEnd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jComboBoxCoursCoursName, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdminAddCoursInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdminAddCoursBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdminAddCoursBack, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdminAddCoursBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCoursCoursName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserAdminAcoursCoursStart, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserAdminAcoursCoursEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminAddCoursCoursOffers, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminAddCoursCoursCost, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdminAddCoursInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdminAddCoursBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdminAddCoursBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdminAddCoursBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTableCoursAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cours Id", "Cours Name", "Cours Start", "Coures End", "Cours Offers", "Cours Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCoursAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCoursAdminMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCoursAdmin);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdminAddCoursInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminAddCoursInsertActionPerformed
        insertIntoCours();
        showCours();
        
      
    }//GEN-LAST:event_jButtonAdminAddCoursInsertActionPerformed

    private void jButtonAdminAddCoursBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminAddCoursBackActionPerformed
        AdminHome adm = new AdminHome();
        adm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAdminAddCoursBackActionPerformed

    private void jButtonAdminAddCoursBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminAddCoursBack1ActionPerformed
        update();
        showCours();

    }//GEN-LAST:event_jButtonAdminAddCoursBack1ActionPerformed

    private void jButtonAdminAddCoursBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminAddCoursBack2ActionPerformed
        deletrecord();
        showCours();
    }//GEN-LAST:event_jButtonAdminAddCoursBack2ActionPerformed

    private void jComboBoxCoursCoursNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCoursCoursNameActionPerformed
//        returnCours();
    }//GEN-LAST:event_jComboBoxCoursCoursNameActionPerformed

    private void jTableCoursAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCoursAdminMouseClicked

        try {
            int row = jTableCoursAdmin.getSelectedRow();
            String cell = jTableCoursAdmin.getModel().getValueAt(row, 0).toString();
            String qury = "SELECT cName,cCost,cOffers,cStart,cEnd FROM cours where Cid=" + cell;
            PreparedStatement ps = con.prepareStatement(qury);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                jComboBoxCoursCoursName.setSelectedItem(rs.getString("cName"));
                
                jTextFieldAdminAddCoursCoursOffers.setText(rs.getString("cOffers"));

                jTextFieldAdminAddCoursCoursCost.setText(rs.getString("cCost"));
                jDateChooserAdminAcoursCoursStart.setDate(rs.getDate("cStart"));
                jDateChooserAdminAcoursCoursEnd.setDate(rs.getDate("cEnd"));

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTableCoursAdminMouseClicked

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
            java.util.logging.Logger.getLogger(CoursAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoursAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoursAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoursAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoursAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdminAddCoursBack;
    private javax.swing.JButton jButtonAdminAddCoursBack1;
    private javax.swing.JButton jButtonAdminAddCoursBack2;
    private javax.swing.JButton jButtonAdminAddCoursInsert;
    private javax.swing.JComboBox<String> jComboBoxCoursCoursName;
    private com.toedter.calendar.JDateChooser jDateChooserAdminAcoursCoursEnd;
    private com.toedter.calendar.JDateChooser jDateChooserAdminAcoursCoursStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCoursAdmin;
    private javax.swing.JTextField jTextFieldAdminAddCoursCoursCost;
    private javax.swing.JTextField jTextFieldAdminAddCoursCoursOffers;
    // End of variables declaration//GEN-END:variables
}
