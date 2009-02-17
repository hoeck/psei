
package gruppe4;

import gruppe4.geom.*;
import java.io.IOException;

public class Main {

    final static String defaultCamPosition  = "0,0,-10";
    final static String defaultCamDirection = "0,0,1";
    

    static void printHelp()
    {
        System.out.println("Gruppe 4 Aufgaben 2 - 5");
        System.out.println("  Liesst Dreieckskoordinaten zeilenweise von Stdin ein und transformiert");
        System.out.println("  diese in ein Kamerasystem. Gibt projizierte Schirmkoordinaten und");
        System.out.println("  Normalenvektor der Dreiecke von hinten nach vorn Zeilenweise auf Stdout aus.");
        System.out.println("Aufruf: gruppe4 [--help] [Kameraposition] [Blickrichtung]");
        System.out.println("Optionen:");
        System.out.printf("  Kameraposition:  x,y,z  Position der Kamera in der Welt (Vorgabe: %s)%n",defaultCamPosition);
        System.out.printf("  Blickrichtung:   x,y,z  Blickrichtung der Kamera, (Vorgabe: %s)%n",defaultCamDirection);
    }
    
    public static void main(String[] args) // throws IOException, Exception
    {
        if ((args.length>=1) && args[0].matches(".*help.*")) {
            printHelp();
            return;
        }
        // assume args[1] and args[2] are the optional arguments -> no whitespace in vector-args!
        try {
            switch(args.length) {
                case 0:
                    Program.createAndRunFromStdio(defaultCamDirection, defaultCamPosition);
                    break;
                case 1:
                    Program.createAndRunFromStdio(defaultCamDirection, args[0]);
                    break;
                case 2:
                    Program.createAndRunFromStdio(args[1], args[0]);
                    break;
                default:
                    System.err.println("Fehler - zuviele Parameter im Programmaufruf");
            }
        }
        catch (IllegalArgumentException e) {
            System.err.println("Formatfehler: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Fehler in der Ein-/Ausgabe: " + e.getMessage());
        }        
    }
}

