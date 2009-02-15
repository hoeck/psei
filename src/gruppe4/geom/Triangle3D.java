package gruppe4.geom;

import gruppe4.Util;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;


/**
 * A Triangle is a shape consisting of 3 {@link gruppe4.geom.Vector3D} objects.
 *
 */
public class Triangle3D {

	Vector3D[] points;
    Vector3D normal;

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
    public String toString() 
    {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(points[0]).append(" ")
            .append(points[1]).append(" ")
            .append(points[2]);
		return stringBuilder.toString();
    }


    /**
     * Translates this triangle vector-points according to {@link Vector3D#transform}.
     *
     * @param rotM a rotation matrix
     * @param transV a translation vector
     * @return the transformed Triangle3D
     */
    public Triangle3D transform(Matrix3D rotM, Vector3D transV) 
    {
        return new Triangle3D(points[0].transform(rotM,transV),
                              points[1].transform(rotM,transV),
                              points[2].transform(rotM,transV));
    }


    /**
     * Calculate and cache the normal of this Triangle.
     *
     * @return the normal
     */
    public Vector3D normal()
    {       
        //         _
        //         A 
        //          |\
        //          | \     
        //         _|__\ _
        //         C     B
        //              _ _   _ _
        //  normal() = (B-A)x(C-A)

        if (normal == null) {
            normal = points[1].subtract(points[0]).crossProduct(points[2].subtract(points[0]));
        }
        return normal;
    }
    
    /**
     * Get the smallest Z value of any point of this triangle.
     *
     * @return a <code>float</code> value
     */
    public float getMinZ()
    {
        return Util.min3f(points[0].getZ(), points[1].getZ(), points[2].getZ());
    }



    /**
     * Projects all vector-points of this triangle according to {@link Vector3D#project}
     * (Note: the normal of this triangle does not get projected)
     *
     * @param d the offset factor
     * @return the projected Triangle
     */
    public Triangle3D project(float d)
    {        
        return new Triangle3D(points[0].project(d),
                              points[1].project(d),
                              points[2].project(d));

    }
}




