package gruppe4.geom;

/**
 * A Triangle is a shape consisting of 3 {@link gruppe4.geom.Vector3D} objects.
 *
 */
public class Triangle3D {
	public Vector3D[] points;
	
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




