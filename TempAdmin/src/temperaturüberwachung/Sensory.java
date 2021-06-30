/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturüberwachung;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author jakob.kaiser
 */
public class Sensory {
    public static void modifyMax(Connection con, String name){
    showInput(con);
    System.out.println("Bitte geben Sie die Nummer ein, um die Maximaltemperatur zu verändern");
    Scanner sc = new Scanner(System.in);
   int id = Integer.parseInt(sc.next());
   String oldMax = "";
      try {
       Statement stmt = con.createStatement();
        ResultSet rs;
         rs = stmt.executeQuery("SELECT * FROM sensor WHERE id = "+id+"");
         System.out.println("before display");
          oldMax = displayResults(rs);


      }catch(SQLException e){
          System.out.println("ooga booga ein fehler: ");
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


                 createLog(con, newMax, oldMax, id, name);
          }catch(SQLException e){
           System.out.println(e.getMessage());
          }
      }else{
       System.out.println("Vorgang abgebrochen.");   
       // return to menu?
      }
    }
    public static void createLog(Connection con, int newMax, String oldMax, int id, String name){
        try{
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = con.createStatement();
                 ResultSet rs;
                  rs = stmt.executeQuery("SELECT * FROM user WHERE username = '"+name+"'");
                  rs.next();
                  String userId = rs.getString("id");
               String query = "INSERT INTO log (user_id,sensor_id,creation_date,old_max_temperature,new_max_temperature) VALUES ("+userId+","+id+",'"+timestamp+"',"+oldMax+","+newMax+")";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                 preparedStmt.executeUpdate();
         }catch(SQLException e){
             System.out.println(e.getMessage());
         }
    }
    public static void modifyManufac(Connection con){
     int id = modifyPrompt(con);
    Scanner sc = new Scanner(System.in);
    try {
         Statement stmt = con.createStatement();
          ResultSet rs;
           rs = stmt.executeQuery("SELECT * FROM manufacture");
           System.out.println("---------------------------------------------");  
           while ( rs.next() ) {
            String manu_id = rs.getString("id");
             String manu_name = rs.getString("name");
               System.out.println("|*| Hersteller Nummer:"+manu_id+" | Name: "+manu_name+" |*|");
            }   
           System.out.println("---------------------------------------------");
            
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }
 System.out.println("Bitte geben Sie die ID des neuen Herstellers an. (1,2,3...)");

         String newManu = sc.nextLine();
     
        String yesNo = modifyAssure(sc);

        if(yesNo.equals("Y") == true){
            try{
             Statement stmt = con.createStatement();
             ResultSet rs;
              String query = "UPDATE sensor SET manufacture_id = '"+newManu+"' WHERE id = "+id+"";
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
             int newRack = Integer.parseInt(sc.nextLine());
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
    public static String displayResults(ResultSet rs){
       String maxTemp = "";
        try{
          System.out.println("------------------------------------------------------------------------------------------------");  
           while ( rs.next() ) {
            String id = rs.getString("id");
             String server = rs.getString("server_rack");
             String adress = rs.getString("adress");
              maxTemp = rs.getString("max_temperature");
              String manu = rs.getString("manufacture_id");
               System.out.println("|*| Sensor Nummer:"+id+" | Schrank: "+server+" | Adresse: "+adress+" | MaxTemp: "+maxTemp+" | Hersteller: "+manu+"|*|");
            }   
          System.out.println("------------------------------------------------------------------------------------------------");
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }
        return maxTemp;
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
} 




