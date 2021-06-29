package temperaturüberwachung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import at.favre.lib.crypto.bcrypt.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 *
 * @author samyo
 * @author Jk
 */
public class TemperaturÜberwachung {

    public static void main(String[] args) throws SQLException {
        
        String url = "jdbc:mysql://85.214.105.212:3306/school?serverTimezone=Europe/Paris";
        String user = "Samuel";
        String pass = "vK?327ec";
        Connection con = null;
        ResultSet rs = null;
        boolean auth = false;
        
        try {
        // Verbindung aufbauen
        con = DriverManager.getConnection(url, user, pass);
        System.out.println("Verbindung erfolgreich hergestellt");
        } 
        catch (SQLException e) {
            System.out.println("Fehler:");
            System.out.println(e.getMessage());
        }
        String name = null;
        //Anmeldung
        System.out.println("Willkommen in der Temperaturüberachung! Bitte Anmelden");
        while(auth == false)
        {
            //Benutzerinteraktion und Anmeldeinfos einlesen
            System.out.println("Nutzername:");
            Scanner sc = new Scanner(System.in);
            name = sc.next();
            System.out.println("Passwort:");
            String passw = sc.next();
            
            //Sql Abfrage 
            Statement stm = con.createStatement();
            String abfrage = "SELECT * FROM user";
            rs = stm.executeQuery(abfrage);
        
            //prüft ob Passwörter und nutzernamen übereinstimmen
            try
            {
                while(rs.next()){
                    String nn = rs.getString(2);
                    String hash = rs.getString(5);
                    BCrypt.Result result = BCrypt.verifyer(BCrypt.Version.VERSION_2Y)
                        .verifyStrict(passw.getBytes(StandardCharsets.UTF_8), hash.getBytes(StandardCharsets.UTF_8));
                
                if(result.verified && Objects.equals(nn, name))
                    {
                        System.out.println("Herzlich Willkommen " + name + "!");
                        auth = true;
                    }
                else
                    {
                        System.out.println("Passwort oder Nutzername falsch, bitte wiederholen!");
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Fehler:");
                System.out.println(e.getMessage());
            }
        }
        MainMenu.menu(con, name);
        //Datenbankverbindung schließen
        try{
        con.close();
        }
        catch(Exception e)
        {
            System.out.println("Fehler:");
            System.out.println(e.getMessage());
        }
    }
}