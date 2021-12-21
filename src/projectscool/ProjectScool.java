/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectscool;

import database.db;
import java.sql.*;
import login.login;


/**
 *
 * @author Bilal
 */
public class ProjectScool {

    public static Connection con = null;

    public static void main(String[] args) {

        
        login lo=new login();
        lo.setVisible(true);
        
        db d = new db();
        d.conection();
        

    }
}
