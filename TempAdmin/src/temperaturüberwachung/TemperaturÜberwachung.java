package temperaturüberwachung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author samyo
 */
public class TemperaturÜberwachung {
     public static String url = "jdbc:mysql://85.214.105.212:3306/school?serverTimezone=Europe/Paris";
     public static String user = "Samuel";
     public static String pass = "vK?327ec";
    
     public static Connection con = DriverManager.getConnection(url, user, pass);

    
    public static void main(String[] args) {
        
     
        // Verbindung aufbauen
      
        System.out.println("Verbindung erfolgreich hergestellt");
   
        
        System.out.println("Willkommen in der Temperaturüberachung! Bitte Anmelden");
        System.out.println("Nutzernamee:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("Passwort:");
        String passw = sc.next();
        
        calcInput.showInput(con);
        
                
    }
}

