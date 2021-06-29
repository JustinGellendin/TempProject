package temperaturüberwachung;

import java.util.Scanner;

/**
 *
 * @author samyo
 */
public class MainMenu {
    public static void menu(){
        int input = 0;
        boolean x = true;
        while (x)
        {
            System.out.println("Hauptmenü:");
            System.out.println("1.Benutzer Anzeigen");
            System.out.println("2.Benutzer Anlegen");
            System.out.println("3.Log Einträge anzeigen");
            System.out.println("4.");
            System.out.println("5.");
            System.out.println("6.Abbruch");
        
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
                    System.out.println("punkt 1");
                break;
                case 2:
                    System.out.println("punkt 2");
                break;
                case 3:
                    System.out.println("punkt 3");
                break;
                case 4:
                    System.out.println("punkt 4");
                break;
                case 5:
                    System.out.println("punkt 5");
                break;
                case 6:
                    x = false;
                break;
                default:
                    System.out.println("Eingabe ungültig, bitte nur die Zahl eingeben!");
                break;
            }
        }
    }
}