package geom;

public class Vector3D {
	public float x, y, z;
	
	public Vector3D(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3D(Point3D p1, Point3D p2)
	{
		this.x = p2.x - p1.x;
		this.y = p2.y - p1.y;
		this.z = p2.z - p1.z;
	}
	
	public float dot(Vector3D v)
	{
		return x*v.x + y*v.y + z*v.z;
	}
	// TODO: Kreuzprodukt
	
}
