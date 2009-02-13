package gruppe4.geom;


/**
 * Implementaion of a simple three-dimensional matrix with basic operations.
 *
 */
public class Matrix3D {
    Vector3D[] a;
  
    /**
     * Creates a new <code>Matrix</code> instance.
     *
     * Takes 3 <code>Vector</code> objects as rows.
     */
    public Matrix3D(Vector3D u, Vector3D v, Vector3D w)
    {
        a = new Vector3D[3];
        a[0] = u;
        a[1] = v;
        a[2] = w;
    }
}
