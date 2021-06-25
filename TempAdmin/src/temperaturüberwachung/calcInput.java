/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturüberwachung;

import java.sql.*;


/**
 *
 * @author jakob.kaiser
 */


public class calcInput {
    

        
        
    public static void generate(){
        
            String url = "jdbc:mysql://85.214.105.212:3306/school?serverTimezone=Europe/Paris";
        String user = "Samuel";
        String pass = "vK?327ec";
        
     
     Connection con = DriverManager.getConnection(url, user, pass);
        System.out.println("Verbindung erfolgreich hergestellt");
      Statement stmt = con.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery("SELECT adresse FROM sensoren");
     
     
                
                 while ( rs.next() ) {
                String adresse = rs.getString("adresse");
                System.out.println(adresse);
            }
      System.out.println(rs);
    }
}
