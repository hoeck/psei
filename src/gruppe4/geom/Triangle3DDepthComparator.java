package gruppe4.geom;

import java.util.Comparator;

/**
 * Orders Triangle3D objects according to their depth.
 *
 */
public class Triangle3DDepthComparator implements Comparator<Triangle3D> {    


    public int compare(Triangle3D t1, Triangle3D t2)
    {
        if (t1.getMinZ() < t2.getMinZ()) {
            return 1;
        } else {            
            return -1;
        }
    }


    public boolean equals(Object o)
    {        
        return true; // there is only one comparator
    }    
}

