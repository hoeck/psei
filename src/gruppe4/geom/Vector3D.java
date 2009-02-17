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
     * Creates a new <code>Vector3D</code> instance out of 2 other <code>Vector3D</code> instances.
     */
    public Vector3D(Vector3D p1, Vector3D p2)
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
    public Vector3D(String src) throws IllegalArgumentException
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
     * Multiplies each scalar of this Vector with another skalar and returns the result as a new vector.
     *
     * @param s a scalar of type  <code>float</code>
     * @return a new <code>Vector3D</code>.
     */
    public Vector3D product(float s)
    {                
        return new Vector3D(s*this.x, s*this.y, s*this.z);
    }


    /**
     * Calculates the magnitude of this Vector3D 
     *
     * @return a <code>float</code> magnitude.
     */
    public float magnitude() {
                
        return (float)Math.sqrt(x*x + y*y + z*z);
    }


    /**
     * Creates a new normalized instance of this Vector.
     *
     * @return a new normalized <code>Vector3D</code>.
     */
    public Vector3D normalize()
    {
        
        float oneOverMagnitude = 1 / magnitude();
        return this.product(oneOverMagnitude);
    }


    /**
     * Compute the cross-product of this and another Vector
     *
     * @return a new <code>Vector3D</code>.
     */
    public Vector3D crossProduct(Vector3D a)
    {
        Vector3D b = this;
        return new Vector3D(a.y*b.z - a.z*b.y, a.z*b.x - a.x*b.z, a.x*b.y - a.y*b.x);
    }


    /**
     * Subtract Vector3D v from this Vector and return the result as a new Vector3D.
     *
     * @param v the vector to subtract.
     * @return the resulting vector.
     */
    public Vector3D subtract(Vector3D v)
    {
        return new Vector3D(this.x - v.getX(),
                            this.y - v.getY(),
                            this.z - v.getZ());
    }


    /**
     * Translates this vector according to the params and returns a new vector as the result.
     *
     * @param rotM a rotation matrix
     * @param transV a translation vector
     * @return the transformed vector
     */
    public Vector3D transform(Matrix3D rotM, Vector3D transV) {
        return rotM.product(this.subtract(transV));
    }
    

    /**
     * Creates a String representation of the Vector, which is readable with the Vector String constructor.
     *
     * @return a <code>String</code> of 3 floats separated by spaces
     */
    public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(this.x).append(" ")
            .append(this.y).append(" ")
            .append(this.z);
		return stringBuilder.toString();
    }


    /**
     * Projects this vector on the x-y plane use the offset-factor d.
     *
     * @param d the offset factor
     * @return the projected Vector3D where x,y are the projected values and z is keeping its 3D value
     */
    public Vector3D project(float d)
    {
        // x' = x * d/z
        // y' = y * d/z
        float dz = d/z;
        return new Vector3D(x*dz, y*dz, z);
    }
}

