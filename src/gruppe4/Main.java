
package gruppe4;

import gruppe4.geom.*;

public class Main {

    static void printHelp()
    {
        System.out.println("Gruppe 4 Aufgaben 2 - 5");
        System.out.println("  Liesst Dreieckskoordinaten zeilenweise von Stdin ein und transformiert");
        System.out.println("  diese in ein Kamerasystem. Gibt projizierte Schirmkoordinaten und");
        System.out.println("  Normalenvektor der Dreiecke von hinten nach vorn Zeilenweise auf Stdout aus.");
        System.out.println("Aufruf: gruppe4 [--help] [--kamera x,y,z] [--blickrichtung x,y,z]");
        System.out.println("Optionen:");
        System.out.println("  --kamera x,y,z         Position der Kamera in der Welt, Vorgabe: 0,0,-10");
        System.out.println("  --blickrichtung x,y,z  Blickrichtung der Kamera, Vorgabe: 0,0,0");
    }

    public static void main(String[] args)
    {
        
    }
}
