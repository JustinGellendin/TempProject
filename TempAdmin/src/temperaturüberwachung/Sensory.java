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


public class Sensory {
    

        
        
    public static void showInput(Connection con){   
        try {
         Statement stmt = con.createStatement();
          ResultSet rs;
          rs = stmt.executeQuery("SELECT * FROM sensors");
         displayResults(rs);
        }catch (SQLException e){
          System.out.println(e.getMessage());
        }
        

        
    }
    
    public static void modifyMax(Connection con){
      System.out.println("Bitte geben Sie die Nummer ein, um die Maximaltemperatur zu verändern");
      Scanner sc = new Scanner(System.in);
     int id = Integer.parseInt(sc.next());
        
        try {
         Statement stmt = con.createStatement();
          ResultSet rs;
           rs = stmt.executeQuery("SELECT * FROM sensors WHERE id = "+id+"");
            displayResults(rs);
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }

           System.out.println("Bitte geben Sie die neue Maximaltemperatur für den Sensor ein.");
             int newMax = Integer.parseInt(sc.next());
              System.out.println("Wollen sie wirklich fortfahren? (Y/N)");
               String yesNo = sc.next();
               
        while(yesNo.equals("Y") == false && yesNo.equals("N") == false){
         System.out.println("Das war nicht richtig. Wollen sie wirklich fortfahren? (Y/N)");
          yesNo = sc.next();
        }
        
        if(yesNo.equals("Y") == true){
            try{
             Statement stmt = con.createStatement();
              ResultSet rs;
               String query = "UPDATE sensors SET max_temperature = "+newMax+" WHERE id = "+id+"";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                 preparedStmt.executeUpdate();
                  rs = stmt.executeQuery("SELECT * FROM sensors WHERE id = "+id+"");
                   displayResults(rs);
            }catch(SQLException e){
             System.out.println(e.getMessage());
            }
        }else{
         System.out.println("Vorgang abgebrochen.");   
         // return to menu?
        }
    }
    
    public static void modifyManufac(){
        
    }
    
    public static void modifyAdress(){
        
    }
    
    public static void modifyRack(){
    
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
         System.out.println(e.getMessage());
        }
    }   
} 




