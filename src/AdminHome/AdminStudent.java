/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminHome;

import static database.db.con;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Bilal
 */
public class AdminStudent extends javax.swing.JFrame {

    public void showStudent1() {

        try {
            
             DefaultTableModel dm = (DefaultTableModel) jTableStudent.getModel();
              while (dm.getRowCount() > 0) {
                dm.removeRow(0);
              }

            String qury = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(qury);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Sid = String.valueOf(rs.getInt("Sid"));
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String tel = rs.getString("tel");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String city = rs.getString("city");
                String cName = rs.getString("cName");
                String cTeacher = rs.getString("cTeacher");
                String cStart = rs.getString("cStart");
                String cEnd = rs.getString("cEnd");
                String cOffers = rs.getString("cOffers");
                String cCost = rs.getString("cCost");

                String tData[] = {Sid, fName, lName, tel, gender, age, city, cName, cTeacher, cStart, cEnd, cOffers, cCost};
                DefaultTableModel tbmodel = (DefaultTableModel) jTableStudent.getModel();

                tbmodel.addRow(tData);

            }
        } catch (Exception e) {
        }

    }

    public int returnIDLogin() {
        int result = 0;
        try {

            String query = "SELECT Cid FROM cours where cName=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) jComboBoxCoursName.getSelectedItem());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String item = rs.getString("Cid");
//                jTextFieldAdminTeacheForeign.setText(item);
                result = Integer.parseInt(item);

            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    public void insert() {
        int as = returnIDLogin();
        String fName = jTextFieldAdminStudentFisteName.getText();
        String lName = jTextFieldAdminStudentLasteName.getText();
        String tel = jTextFieldAdminStudentTel.getText();
        String gender = String.valueOf(jComboBoxGender.getSelectedItem());
        String age = String.valueOf(jComboBoxAge.getSelectedItem());
        String city = String.valueOf(jComboBoxCity.getSelectedItem());
        String cName = String.valueOf(jComboBoxCoursName.getSelectedItem());
        String cTeacher = String.valueOf(jComboBoxCoursTeacher.getSelectedItem());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String cStart = sdf.format(jDateChooserStart.getDate());
        String cEnd = sdf.format(jDateChooserEnd.getDate());
        String cOffers = jTextFieldAdminStudentCoursOffers.getText();
        String cCost = jTextFieldAdminStudentCoursCost.getText();
        String fKey = "" + as;   //jTextFieldAdminStudentForeignKey.getText();

        try {

            String qury = "insert into student VALUES (null,'" + fName + "','" + lName + "','" + tel + "','" + age + "','" + gender + "','" + city + "','" + cName + "','" + cTeacher + "','" + cStart + "','" + cEnd + "','" + cOffers + "','" + cCost + "','" + fKey + "')";
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

    public void selectItem() {

        try {
            String query = "SELECT distinct wana FROM teacher";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String item = rs.getString("wana");
                jComboBoxCoursName.addItem(item);

            }
        } catch (Exception e) {
        }

    }

    public void selectIteacherName() {

        try {
            String query = "SELECT fullName FROM teacher";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String item = rs.getString("fullName");
                jComboBoxCoursTeacher.addItem(item);

            }
        } catch (Exception e) {
        }

    }

    public void returnComboxTeacher() {
        jComboBoxCoursTeacher.removeAllItems();
        try {
            String query = "SELECT fullName FROM teacher where wana=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) jComboBoxCoursName.getSelectedItem());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String item = rs.getString("fullName");

                jComboBoxCoursTeacher.addItem(item);

            }

        } catch (Exception e) {

        }

    }

    public void returnCours() {

        try {
            String query = "SELECT cCost,cOffers,cStart,cEnd FROM cours where cName=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) jComboBoxCoursName.getSelectedItem());
            ResultSet rs = ps.executeQuery();

            if (rs.next() == false) {

                JOptionPane.showMessageDialog(null, "course not avible");

            } else {

                jDateChooserStart.setDate(rs.getDate("cStart"));
                jDateChooserEnd.setDate(rs.getDate("cEnd"));
                jTextFieldAdminStudentCoursOffers.setText(rs.getString("cOffers"));
                jTextFieldAdminStudentCoursCost.setText(rs.getString("cCost"));

            }

        } catch (Exception e) {

        }

    }

    public void update() {
        int as = returnIDLogin();
        String sfName = jTextFieldAdminStudentFisteName.getText();
        String slName = jTextFieldAdminStudentLasteName.getText();
        String tel = jTextFieldAdminStudentTel.getText();
        String gender = String.valueOf(jComboBoxGender.getSelectedItem());
        String age = String.valueOf(jComboBoxAge.getSelectedItem());
        String city = String.valueOf(jComboBoxCity.getSelectedItem());
        String cName = String.valueOf(jComboBoxCoursName.getSelectedItem());
        String cTeacher =String.valueOf(jComboBoxCoursTeacher.getSelectedItem()); 
        String cOffers = jTextFieldAdminStudentCoursOffers.getText();
        String cCost = jTextFieldAdminStudentCoursCost.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String cStart = sdf.format(jDateChooserStart.getDate());
        String cEnd = sdf.format(jDateChooserEnd.getDate());
        String fKey = "" + as;//jTextFieldAdminStudentForeignKey.getText();

        try {
            int row = jTableStudent.getSelectedRow();
            String cell = jTableStudent.getModel().getValueAt(row, 0).toString();
            String qury = "update student set fName='" + sfName + "',lName='" + slName + "',tel='" + tel + "',gender='" + gender + "',age='" + age + "',city='" + city + "',cName='" + cName + "',cTeacher='" + cTeacher + "',cOffers='" + cOffers + "',cCost='" + cCost + "',cStart='" + cStart + "',cEnd='" + cEnd + "',F_KEYSCid='" + fKey + "' where Sid=" + cell;
            PreparedStatement ps = con.prepareStatement(qury);
            ps.execute();

            JOptionPane.showMessageDialog(null, "data update");

        } catch (Exception e) {
        }
    }

    public void deletrecord() {

        try {
            int row = jTableStudent.getSelectedRow();
            String cell = jTableStudent.getModel().getValueAt(row, 0).toString();
            String qury = "delete from student where Sid=" + cell;
            PreparedStatement ps = con.prepareStatement(qury);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "delet");
        } catch (Exception e) {
        }

    }

    public AdminStudent() {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldAdminStudentFisteName = new javax.swing.JTextField();
        jTextFieldAdminStudentLasteName = new javax.swing.JTextField();
        jTextFieldAdminStudentTel = new javax.swing.JTextField();
        jTextFieldAdminStudentCoursOffers = new javax.swing.JTextField();
        jTextFieldAdminStudentCoursCost = new javax.swing.JTextField();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jDateChooserEnd = new com.toedter.calendar.JDateChooser();
        jComboBoxAge = new javax.swing.JComboBox<>();
        jComboBoxGender = new javax.swing.JComboBox<>();
        jComboBoxCity = new javax.swing.JComboBox<>();
        jComboBoxCoursName = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBoxCoursTeacher = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStudent = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jTextFieldAdminStudentSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Fiste Name :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Laste Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Gender :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Age :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Tel++ :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("City :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Cours Name :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Cours Teacher :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Cours Start :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("Cours End :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Cours Offers :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Cours Cost");

        jTextFieldAdminStudentTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAdminStudentTelKeyPressed(evt);
            }
        });

        jComboBoxAge.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jComboBoxCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "suly", "halabja" }));

        jComboBoxCoursName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCoursNameActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Insert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 102));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBoxCoursTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCoursTeacherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAdminStudentFisteName, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAdminStudentLasteName, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAdminStudentTel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboBoxAge, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxGender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jComboBoxCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jComboBoxCoursTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxCoursName, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jDateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAdminStudentCoursOffers, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAdminStudentCoursCost, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminStudentFisteName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminStudentLasteName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminStudentTel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAge))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jComboBoxGender))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCoursName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCoursTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminStudentCoursOffers, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminStudentCoursCost, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jTableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Fiste Name", "Laste Name", "Tel ++", "Age", "Gender", "City", "Cours Name", "Cours Teacher", "Cours Start", "Cours End", "Cours Offers", "Cours Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableStudent);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Search By :");

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sid", "fName", "gender", "cName" }));

        jTextFieldAdminStudentSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAdminStudentSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jTextFieldAdminStudentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdminStudentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AdminHome ah = new AdminHome();
        ah.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
        showStudent1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxCoursNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCoursNameActionPerformed
        returnCours();
        returnComboxTeacher();

    }//GEN-LAST:event_jComboBoxCoursNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update();
        showStudent1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        deletrecord();
        showStudent1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxCoursTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCoursTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCoursTeacherActionPerformed

    private void jTextFieldAdminStudentSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAdminStudentSearchKeyReleased
       try {
         String selection = (String) jComboBoxSearch.getSelectedItem();
            String qury = "select  *from student where "+selection+"=?";
//           System.out.println(qury);
            PreparedStatement ps = con.prepareStatement(qury);
            ps.setString(1, jTextFieldAdminStudentSearch.getText());
            ResultSet rs = ps.executeQuery();

            jTableStudent.setModel(DbUtils.resultSetToTableModel(rs));
            
            String valueOfTxtF = jTextFieldAdminStudentSearch.getText();
            if(valueOfTxtF.equals("")){
                showStudent1();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTextFieldAdminStudentSearchKeyReleased

    private void jTextFieldAdminStudentTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAdminStudentTelKeyPressed
        String tel = jTextFieldAdminStudentTel.getText();
        int length = tel.length();
        
        char c = evt.getKeyChar();
        
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            
            if (length < 11) {
                jTextFieldAdminStudentTel.setEditable(true);
            } else {
                jTextFieldAdminStudentTel.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                jTextFieldAdminStudentTel.setEditable(true);
                
            } else {
                jTextFieldAdminStudentTel.setEditable(false);
            }
            
        }
    }//GEN-LAST:event_jTextFieldAdminStudentTelKeyPressed

    private void jTableStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStudentMouseClicked
        try {
            int row = jTableStudent.getSelectedRow();
            String cell = jTableStudent.getModel().getValueAt(row, 0).toString();
            String qury = "SELECT fName ,lName,tel,gender,age,city,cName,cTeacher,cOffers,cCost,cStart,cEnd FROM student where Sid=" + cell;
            PreparedStatement ps = con.prepareStatement(qury);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                jTextFieldAdminStudentFisteName.setText(rs.getString("fName"));
                jTextFieldAdminStudentLasteName.setText(rs.getString("lName"));
                jTextFieldAdminStudentTel.setText(rs.getString("tel"));
                jComboBoxGender.setSelectedItem(rs.getString("gender"));
                jComboBoxAge.setSelectedItem(rs.getString("age"));
                jComboBoxCity.setSelectedItem(rs.getString("city"));
                jComboBoxCoursName.setSelectedItem(rs.getString("cName"));
                jComboBoxCoursTeacher.setSelectedItem(rs.getString("cTeacher"));
                jTextFieldAdminStudentCoursOffers.setText(rs.getString("cOffers"));
                jTextFieldAdminStudentCoursCost.setText(rs.getString("cCost"));
                jDateChooserStart.setDate(rs.getDate("cStart"));
                jDateChooserEnd.setDate(rs.getDate("cEnd"));

            } else {
                JOptionPane.showMessageDialog(null, "nayka");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTableStudentMouseClicked

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
            java.util.logging.Logger.getLogger(AdminStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxAge;
    private javax.swing.JComboBox<String> jComboBoxCity;
    private javax.swing.JComboBox<String> jComboBoxCoursName;
    private javax.swing.JComboBox<String> jComboBoxCoursTeacher;
    private javax.swing.JComboBox<String> jComboBoxGender;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private com.toedter.calendar.JDateChooser jDateChooserEnd;
    private com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStudent;
    private javax.swing.JTextField jTextFieldAdminStudentCoursCost;
    private javax.swing.JTextField jTextFieldAdminStudentCoursOffers;
    private javax.swing.JTextField jTextFieldAdminStudentFisteName;
    private javax.swing.JTextField jTextFieldAdminStudentLasteName;
    private javax.swing.JTextField jTextFieldAdminStudentSearch;
    private javax.swing.JTextField jTextFieldAdminStudentTel;
    // End of variables declaration//GEN-END:variables
}
