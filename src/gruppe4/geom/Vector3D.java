package gruppe4.geom;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
     * Tries to create a Vector from a String of the format <code>"x y z"</code>
     * where x,y and z are Floats and blank and "," are considered whitespace.
     * @throws IllegalArgumentException when src doesn't contain a valid vector koordinate
     * @throws NumberFormatException when a number cannot be read
     */
    public Vector3D(String src)
	{
        String[] s = src.split("[, ]+");

        if (s.length != 3) {
            throw new IllegalArgumentException("String param does not contain 3 tokens");
        }
        else {
            this.x = Float.parseFloat(s[0]);
            this.y = Float.parseFloat(s[1]);
            this.z = Float.parseFloat(s[2]);            
        }
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

    public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[")
            .append(this.x).append(", ")
            .append(this.y).append(", ")
            .append(this.z).append("]");
		return stringBuilder.toString();
    }
}
