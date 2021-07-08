package temperaturüberwachung;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author samyo
 */
public class Menupoints {
    public static void DisplayUser(Connection con)
    {
        try
        {
            Statement stm = con.createStatement();
            String abfrage = "SELECT * FROM user";
            ResultSet rs = stm.executeQuery(abfrage);
            
            System.out.println("\nBenutzer:\n------------------------------------------------------------------");
            while(rs.next())
            {
                
                System.out.println( "ID: " + rs.getString(1) + 
                                    "\tBenutzername: " + rs.getString(2) + 
                                    "\tRolle: " + rs.getString(3) + 
                                    "\tAktiv: " + rs.getString(6));
                
            }
            System.out.println("------------------------------------------------------------------\n");
        }
        catch(Exception e)
        {
            System.out.println("Fehler:");
            System.out.println(e.getMessage());   
        }
    }
    
    public static void AddUser(Connection con)
    {   
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Neuen Benutzer anlegen\n------------------------------------------------------------------");
        System.out.println("Benutzername: ");
        String username = sc.next();
        System.out.println("Passwort: ");
        String password = sc.next();
        System.out.println("Rolle Admin oder User: (A/U)");
        String role = sc.next();
         
        while(role.equals("A") == false && role.equals("U") == false)
        {
            System.out.println("Bitte Eingabe überprüfen und wiederholen!");
            role = sc.next();
        }
        if(role.equals("A"))
        {
            role = "[\"ROLE_ADMIN\"]";
        }
        if(role.equals("U"))
        {
            role = "[\"ROLE_USER\"]";
        }
        password = BCrypt.withDefaults().hashToString(10, password.toCharArray());
        
        try
        {  
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            String query = "INSERT INTO user (username, roles, password, registration_date) "
                    + "VALUES ('" + username + "','" + role + "','" + password + "','" + timestamp +"');";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.executeUpdate();

            preparedStmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("Fehler:");
            System.out.println(e.getMessage());   
        }
    }
}