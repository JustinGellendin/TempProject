/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturüberwachung;

import java.sql.*;
import java.util.Scanner;


/**
 *
 * @author jakob.kaiser
 */


public class calcInput {
    

        
        
    public static void showInput(){
        
            String url = "jdbc:mysql://85.214.105.212:3306/school?serverTimezone=Europe/Paris";
        String user = "Samuel";
        String pass = "vK?327ec";
        
             try {

     Connection con = DriverManager.getConnection(url, user, pass);
  
      Statement stmt = con.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery("SELECT * FROM sensoren");
     
     
              System.out.println("---------------------------------------------------");  
                 while ( rs.next() ) {
                String id = rs.getString("SensorNr");
                String server = rs.getString("serverschrank");
                String maxTemp = rs.getString("maximalTemperatur");
                        
                System.out.println("|*| Sensor Nummer:"+id+" | Schrank: "+server+" | MaxTemp: "+maxTemp+" |*|");
            }
                 System.out.println("---------------------------------------------------");
                 con.close();
   } 
        catch (SQLException e) {
             System.out.println("Fehler:");
            System.out.println(e.getMessage());
                    }
             System.out.println("Pidde geben Sie die Nummer ein, um die Maximaltemperatur zu verändern");
                     Scanner sc = new Scanner(System.in);
        int id = Integer.parseInt(sc.next());
            
             modifyMax(id);
        }
    
    public static void modifyMax(Integer selector){
    System.out.println("Thats se nubma:"+selector);
    
}
    }

