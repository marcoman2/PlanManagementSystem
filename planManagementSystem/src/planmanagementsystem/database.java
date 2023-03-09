/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WINDOWS 10
 */
public class database {
    
    public static Connection connectDB(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/plan", "root", ""); // root is the default username while "" or empty or null is the default password
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
