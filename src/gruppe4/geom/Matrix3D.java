package gruppe4.geom;

/**
 * Implementaion of a simple three-dimensional matrix with basic operations.
 *
 */
public class Matrix3D {

    Vector3D[] a; // the matrix' rows
  
    /**
     * Creates a new <code>Matrix</code> instance.
     *
     * Takes 3 <code>Vector3D</code> objects as rows.
     */
    public Matrix3D(Vector3D u, Vector3D v, Vector3D w)
    {
        a = new Vector3D[3];
        a[0] = u;
        a[1] = v;
        a[2] = w;
    }

    
    /**
     * Creates the product of this matrix and another vector.
     *
     * @param v a vector to multiply with
     * @return the resulting vector
     */
    public Vector3D product(Vector3D v)
    {
        return new Vector3D(a[0].dot(v),
                            a[1].dot(v),
                            a[2].dot(v));
    }

    public String toString()
    {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(a[0]).append(System.getProperty("line.separator"))
            .append(a[1]).append(System.getProperty("line.separator"))
            .append(a[2]).append(System.getProperty("line.separator"));
		return stringBuilder.toString();
    }
}



 