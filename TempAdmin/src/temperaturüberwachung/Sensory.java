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
          rs = stmt.executeQuery("SELECT * FROM sensor");
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
           rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
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
               String query = "UPDATE sensor SET max_temperature = "+newMax+" WHERE id = "+id+"";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                 preparedStmt.executeUpdate();
                  rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
                   displayResults(rs);
            }catch(SQLException e){
             System.out.println(e.getMessage());
            }
        }else{
         System.out.println("Vorgang abgebrochen.");   
         // return to menu?
        }
    }
    
    public static void modifyManufac(Connection con){
     showInput(con);
      System.out.println("Bitte geben Sie die Nummer ein, um den Hersteller zu verändern");
      Scanner sc = new Scanner(System.in);
     int id = Integer.parseInt(sc.next());
        
        try {
         Statement stmt = con.createStatement();
          ResultSet rs;
           rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
            displayResults(rs);
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }
        // Hier muss anders
    }
    

    public static void modifyAdress(Connection con){
        int id = modifyPrompt(con);
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte geben Sie die neue Adresse für den Sensor ein.");
             String newAdress = sc.nextLine();
             System.out.println("newAdress: "+newAdress);
            String yesNo = modifyAssure(sc);
                   
            if(yesNo.equals("Y") == true){
                try{
                 Statement stmt = con.createStatement();
                 ResultSet rs;
                  String query = "UPDATE sensor SET adress = '"+newAdress+"' WHERE id = "+id+"";
                   PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
                    displayResults(rs);
                }catch(SQLException e){
                 System.out.println(e.getMessage());
                }
            }else{
                System.out.println("Vorgang abgebrochen.");   
                // return to menu? (monke, eehehe)
            }
        
    }
    
    public static void modifyRack(Connection con){
     int id = modifyPrompt(con);
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte geben Sie das neue Rack für den Sensor ein.");
             String newRack = sc.nextLine();
             System.out.println("newRack: "+newRack);
            String yesNo = modifyAssure(sc);
                   
            if(yesNo.equals("Y") == true){
                try{
                 Statement stmt = con.createStatement();
                 ResultSet rs;
                  String query = "UPDATE sensor SET server_rack = '"+newRack+"' WHERE id = "+id+"";
                   PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
                    displayResults(rs);
                }catch(SQLException e){
                 System.out.println(e.getMessage());
                }
            }else{
                System.out.println("Vorgang abgebrochen.");   
                // return to menu? (monke, eehehe)
            }
        
    }
    
    public static void displayResults(ResultSet rs){
        try{
          System.out.println("---------------------------------------------------------------");  
           while ( rs.next() ) {
            String id = rs.getString("id");
             String server = rs.getString("server_rack");
             String adress = rs.getString("adress");
              String maxTemp = rs.getString("max_temperature");
               System.out.println("|*| Sensor Nummer:"+id+" | Schrank: "+server+" | Adresse: "+adress+" | MaxTemp: "+maxTemp+" |*|");
            }   
          System.out.println("---------------------------------------------------------------");
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }
    }   
        public static Integer modifyPrompt(Connection con){
        showInput(con);  
      System.out.println("Bitte geben Sie die Nummer ein, um den Wert zu verändern");
      Scanner sc = new Scanner(System.in);
     int id = Integer.parseInt(sc.next());
        
        try {
         Statement stmt = con.createStatement();
          ResultSet rs;
           rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
            displayResults(rs);
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }
        return id;
    }
    
    public static String modifyAssure(Scanner sc){
          System.out.println("Wollen sie wirklich fortfahren? (Y/N)");
               String yesNo = sc.next();
               System.out.println("yesNo: "+yesNo);
               
        while(yesNo.equals("Y") == false && yesNo.equals("N") == false){
         System.out.println("Das war nicht richtig. Wollen sie wirklich fortfahren? (Y/N)");
          yesNo = sc.next();
        }
        return yesNo;
    }
} 




