/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import javax.swing.JOptionPane;


public class db {

    public static Connection con = null;

    public static void conection() {

        String url = "jdbc:mysql://localhost:3306/project1";
        String root = "root";
        String pass = "";

        try {
            con = DriverManager.getConnection(url, root, pass);

            if (con != null) {
                JOptionPane.showMessageDialog(null, "connection sucss");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "eror" + e);
        }

    }

}
