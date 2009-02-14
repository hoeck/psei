package gruppe4.geom;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;


/**
 * A Triangle is a shape consisting of 3 {@link gruppe4.geom.Vector3D} objects.
 *
 */
public class Triangle3D {

	final Vector3D[] points;

    public Vector3D getA()
    {
        return points[0];
    }

    public Vector3D getB()
    {
        return points[1];
    }

    public Vector3D getC()
    {
        return points[2];
    }

    /**
     * Get the Points of this Triangle
     * @return the Points in a immutable List, in a clockwise sequence.
     */
    public List<Vector3D> getPoints() 
    {
        return Collections.unmodifiableList(Arrays.asList(points));
    }

    
    /**
     * Creates a new <code>Triangle3D</code> out of 3 Vector3Ds.
     *
     */
    public Triangle3D(Vector3D a, Vector3D b, Vector3D c)
    {
        points = new Vector3D[3];
        points[0] = a;
        points[1] = b;
        points[2] = c;
    }

	
 	/**
     * Creates a new <code>Triangle3D</code> instance out of a String of 9 numbers.
     *
     * @param inp a <code>String</code> of 9 numbers, read with the <code>parseFloat</code> method
     *  and separated by spaces or commas.
     *
     * @throws IllegalArgumentException when src doesn't contain a valid vector koordinate
     * @throws NumberFormatException when a number cannot be read
     */
    public Triangle3D(String inp) 
    {
        String inputArrray[] = inp.split("[, ]+");

        if (inputArrray.length != 9)
            throw new java.lang.IllegalArgumentException("wrong number of tokens");

        points = new Vector3D[3];
        
        for(int v=0;v<3;v++) {
            points[v] = new Vector3D(Float.parseFloat(inputArrray[v*3]),
                                     Float.parseFloat(inputArrray[v*3+1]),
                                     Float.parseFloat(inputArrray[v*3+2]));
        }
 	}

    /**
     * Returns a readable version of the Triangle.
     *
     * @return a <code>String</code> value
     */
    public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(points[0]).append(" ")
            .append(points[1]).append(" ")
            .append(points[2]);
		return stringBuilder.toString();
    }
}




