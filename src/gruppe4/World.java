package gruppe4;

import gruppe4.geom.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.Writer;
import java.io.BufferedWriter;
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
        triangles = new ArrayList<Triangle3D>();
        
        String line;
        while ((line=br.readLine())!=null) {
            triangles.add(new Triangle3D(line));
        }
    }    


    /**
     * Creates a new <code>World</code> instance out of a list of Triangle3D Objects.
     *
     * @param tri a list of triangles
     */
    public World(List<Triangle3D> tri) {
        triangles = tri;
    }

    /**
     * Return a read-only  list of triangles from this world.
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
    public String toString()
    {
		StringBuilder sb = new StringBuilder();

        for(Triangle3D t : triangles) {
            sb.append(t).append(System.getProperty("line.separator"));
        }
		return sb.toString();
    }


    /**
     * Translates this worlds triangles according to the params
     *
     * @param rotM a rotation matrix
     * @param transV a translation vector
     */
    public void transform(Matrix3D rotM, Vector3D transV)
    {
        ArrayList<Triangle3D> lt = new ArrayList<Triangle3D>(triangles.size());
        for(Triangle3D t : triangles) {
            lt.add(t.transform(rotM,transV));
        }
        triangles = lt;
    }


    /**
     * Remove all triangles from this world which are invisible to the camera at [0 0 0] looking to [0 1 0].
     * 
     */
    public void filterVisible()
    {
        ArrayList<Triangle3D> lt = new ArrayList<Triangle3D>();
        for(Triangle3D t : triangles) {            
            if (0<t.normal().getZ()) {
                lt.add(t);
            }
        }
        triangles = lt;
    }    

    /**
     * Remove all triangles behind the x-y plane.
     *
     * @param minDistance The distance considered beeing behind.
     */
    public void filterBackface(float minDistance)
    {
        ArrayList<Triangle3D> lt = new ArrayList<Triangle3D>();
        for(Triangle3D t : triangles) {
            if (Util.min3f(t.getA().getZ(),
                           t.getB().getZ(),
                           t.getC().getZ())
                >=minDistance)
                {
                    lt.add(t);
                }
        }
        triangles = lt;
    }    


    /**
     * Sort the triangles in this World according to {@link Triangle3DDepthComparator}.
     *
     */
    public void sortDepth()
    {
        Collections.sort(triangles, new Triangle3DDepthComparator());
    }



    /**
     * Project the Worlds Triangles according to {@link Triangle3D#project}.
     *
     * @param d the projection offset
     */
    public void project(float d)
    {        
        ArrayList<Triangle3D> tri2D = new ArrayList<Triangle3D>(triangles.size());
        
        for(Triangle3D t : triangles) { 
            tri2D.add(t.project(d));
        }
        triangles = tri2D;
    }

    
    
    /**
    * Write a string representation of this worlds triangles.
    * Write one Triangle (with {@link Triangle3D#toString} followed by 
    * a single space followed by the
    * triangles normal (using {@link Vector3D#toString}) followed by a newline.
    * 
    * @param wr a <code>Writer</code>
    * @return the number of written triangles.
    * @exception IOException if an error occurs in the writer.
    */
    public int write(Writer wr) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(wr);

        for(Triangle3D t : triangles) {
            bw.write(t.toString());
            bw.write(" ");
            bw.write(t.normal().toString());
            bw.newLine();
        }
        
        bw.flush();
        return triangles.size();
    }

}


