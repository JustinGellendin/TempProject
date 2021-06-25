package temperaturüberwachung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samyo
 */
public class TemperaturÜberwachung {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://85.214.105.212:3306/school?serverTimezone=Europe/Paris";
        String user = "Samuel";
        String pass = "vK?327ec";
        
        try {
        // Verbindung aufbauen
        Connection con = DriverManager.getConnection(url, user, pass);
        System.out.println("Verbindung erfolgreich hergestellt");
        } 
        catch (SQLException e) {
            System.out.println("Fehler:");
            System.out.println(e.getMessage());
        }
        
     
    }
}
