package gruppe4;

import gruppe4.geom.*;
import java.util.List;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;

public class World {

    List<Triangle3D> triangles;
    

    /**
     * Creates a new <code>World</code> instance from a string-stream of float numbers.
     *
     * @param input a <code>Reader</code>
     * @exception IOException if an error occurs
     */
    public World(Reader input) throws IOException {
                
        BufferedReader br = new BufferedReader(input);
        triangles = new Vector<Triangle3D>();
        
        String line;
        while ((line=br.readLine())!=null) {
            triangles.add(new Triangle3D(line));
        }
    }

    /**
     * Create a string representation of this world.
     *
     * @return a <code>String</code>
     */
    public String toString() {
		StringBuilder sb = new StringBuilder();

        for(Triangle3D t : triangles) {
            sb.append(t).append(System.getProperty("line.separator"));
        }
		return sb.toString();
    }


    public static void main(String argv[]) throws Exception {

        World w = new World(new java.io.InputStreamReader(System.in));

        System.out.println(w); 
    }
}
