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
    

        
        
    public static void showInput(Connection con){   
             try {
      Statement stmt = con.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery("SELECT * FROM sensoren");
     displayResults(rs);
                   
   } 
        catch (SQLException e) {
             System.out.println("Fehler:");
            System.out.println(e.getMessage());
                    }
             System.out.println("Pidde geben Sie die Nummer ein, um die Maximaltemperatur zu verändern");
                     Scanner sc = new Scanner(System.in);
        int id = Integer.parseInt(sc.next());
        
            
             modifyMax(id, con);
        }
    
    public static void modifyMax(Integer selector, Connection con){
    System.out.println("Thats se nubma:"+selector);
    try {
    Statement stmt = con.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery("SELECT * FROM sensoren WHERE SensorNr = "+selector+"");
      displayResults(rs);
    }catch(SQLException e) {
        System.out.println("ohney");
            System.out.println(e.getMessage());}
    System.out.println("Bitte geben Sie die neue Maximaltemperatur für den Sensor ein.");
    Scanner sc = new Scanner(System.in);
        int newMax = Integer.parseInt(sc.next());
        System.out.println("Wollen sie wirklich fortfahren? (Y/N)");
        String yesNo ="";
        yesNo = sc.next();
        while(yesNo.equals("Y") == false && yesNo.equals("N") == false){
           System.out.println("Das war nicht richtig. Wollen sie wirklich fortfahren? (Y/N)");
           yesNo = sc.next();
        }
        if(yesNo.equals("Y") == true){
            try{
            Statement stmt = con.createStatement();
      ResultSet rs;
 
        String query = "UPDATE sensoren SET maximalTemperatur = "+newMax+" WHERE sensorNr = "+selector+"";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.executeUpdate();
             rs = stmt.executeQuery("SELECT * FROM sensoren WHERE SensorNr = "+selector+"");
             displayResults(rs);
            }catch(SQLException e){
          System.out.println("ohney PT2");
            System.out.println(e.getMessage());
          
      }
        }
    
    }
    
    
        public static void displayResults(ResultSet rs){
            try{
              System.out.println("---------------------------------------------------");  
                 while ( rs.next() ) {
                String id = rs.getString("SensorNr");
                String server = rs.getString("serverschrank");
                String maxTemp = rs.getString("maximalTemperatur");
                        
                System.out.println("|*| Sensor Nummer:"+id+" | Schrank: "+server+" | MaxTemp: "+maxTemp+" |*|");
            }
                 System.out.println("---------------------------------------------------");
            }catch(SQLException e){
    
                                    }
        }   
} 




