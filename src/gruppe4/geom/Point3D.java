package gruppe4.geom;


/**
 * Class for representing points in R^3
 *
 * @deprecated use {@link Vector3D} instead.
 *
 */
public class Point3D  {

    float x, y, z;
	
	/**
     * create a new Point3D out of three floats.
     *
     */
    public Point3D(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
