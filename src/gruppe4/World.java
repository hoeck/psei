package gruppe4;

import gruppe4.geom.*;
import java.util.List;
import java.util.Vector;
import java.util.Collections;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * The World Class holds the representation of all Triangles.
 *
 */
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


    public World(List<Triangle3D> tri) {
        triangles = tri;
    }    

    /**
     * Return the List of points of this Triangle.
     * @return <b>immutable</b> List of Vectors.
     */
    public List<Triangle3D> getTriangles()
    {
        return Collections.unmodifiableList(triangles);
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

}