package temperaturüberwachung;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author samyo
 */
public class MainMenu {
    public static void menu(Connection con, String name){
        int input = 0;
        boolean x = true;
        while (x)
        {
            System.out.println("\nHauptmenü:");
            System.out.println("-----------------------------------------");
            System.out.println("1.Benutzer Anzeigen");
            System.out.println("2.Benutzer Anlegen");
            System.out.println("3.Log Einträge anzeigen");
            System.out.println("4.Maximaltemperatur ändern");
            System.out.println("5.Hersteller ändern");
            System.out.println("6.Adresse ändern");
            System.out.println("7.Rack ändern");
            System.out.println("8.Sensor hinzufügen");
            System.out.println("9.Abbruch");
        
            try
            {
                Scanner sc = new Scanner(System.in);
                input = Integer.parseInt(sc.next());
            }
            catch (Exception e)
            {
                System.out.println("Fehler:");
                System.out.println(e.getMessage());
            }
        
            switch(input)
            {
                case 1:
                    Menupoints.DisplayUser(con);
                break;
                case 2:
                    Menupoints.AddUser(con);
                break;
                case 3:
                    Sensory.showLogs(con);
                break;
                case 4:
                    Sensory.modifyMax(con, name);
                break;
                case 5:
                    Sensory.modifyManufac(con);
                break;
                case 6:
                    Sensory.modifyAdress(con);
                break;
                case 7:
                    Sensory.modifyRack(con);
                break;
                case 8:
                    Sensory.addSensor(con);
                break;
                case 9:
                    x = false;
                break;
                default:
                    System.out.println("Eingabe ungültig, bitte nur die Zahl eingeben!");
                break;
            }
        }
    }
}