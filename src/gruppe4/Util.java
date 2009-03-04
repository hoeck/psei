package gruppe4;

/**
 * Utility functions.
 *
 */
public class Util {

    /**
     * return the biggest of three floats
     *
     * @param a,b,c floats
     * @return the biggest one
     */
    public static float max3f(float a, float b, float c)
    {
        return Math.max(Math.max(a,b),c);
    }

    /**
     * return the smallest of three floats
     *
     * @param m,n,o floats
     * @return the smallest of these floats
     */
    public static float min3f(float m, float n, float o)
    {
        return Math.min(Math.min(m,n),o);
    }

}
