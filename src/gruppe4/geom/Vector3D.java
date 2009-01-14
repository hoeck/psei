package gruppe4.geom;


/**
 * Implementation of an immutable three-dimensional vector with basic vector operations.
 */
public class Vector3D {
	float x, y, z;

    /**
     * Get the X value.
     * @return the X value.
     */
    public float getX() {
        return x;
    }

    /**
     * Get the Y value.
     * @return the Y value.
     */
    public float getY() {
        return y;
    }

    /**
     * Get the Z value.
     * @return the Z value.
     */
    public float getZ() {
        return z;
    }


	
	/**
     * Creates a new <code>Vector3D</code> instance.
     */
    public Vector3D(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
     * Creates a new <code>Vector3D</code> instance out of 2 Points.
     */
    public Vector3D(Point3D p1, Point3D p2)
	{
		this.x = p2.x - p1.x;
		this.y = p2.y - p1.y;
		this.z = p2.z - p1.z;
	}
	
	/**
     * Calculates the dotproduct <code>dot</code> between this and another Vector.
     *
     * @return the dotproduct.
     */
    public float dot(Vector3D v)
	{
		return x*v.x + y*v.y + z*v.z;
	}


    /**
     * Compute the cross-product of this and another Vector
     *
     * @return a new <code>Vector3D</code>.
     */
    public Vector3D CrossProduct(Vector3D a)
    {
        Vector3D b = this;
        return new Vector3D(a.y*b.z - a.z*b.y, a.z*b.x - a.x*b.z, a.x*b.y - a.y*b.x);
    }
}
