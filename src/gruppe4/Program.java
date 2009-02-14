package gruppe4;

import gruppe4.geom.*;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements the program logic. 
 *
 */
public class Program {

    World originalWorld;
    Vector3D cameraView;
    Vector3D cameraPos;

    Matrix3D transMatrix;

    public Program(Reader in, String camViewStr, String camStrPos) throws IOException, Exception
    {
        originalWorld = new World(in);
        cameraView = new Vector3D(camViewStr);
        cameraPos = new Vector3D(camStrPos);
    } 


    /**
     * Describe <code>initTransMatrix</code> method here.
     *
     */
    void initTransMatrix()
    {
        Vector3D oben = new Vector3D(0,-1,0);
        Vector3D camX = cameraView.crossProduct(oben);
        Vector3D camY = cameraView.crossProduct(camX);
        transMatrix = new Matrix3D(camX.normalize(), camY.normalize(), cameraView.normalize());
    }

    Vector3D transformPoint(Vector3D p) {
        return transMatrix.product(p.subtract(cameraPos));
    }

    Triangle3D transformTriangle(Triangle3D t) {
        return new Triangle3D(transformPoint(t.getA()),
                              transformPoint(t.getB()),
                              transformPoint(t.getC()));
    }

    World transformWorld(World w)
    {
        ArrayList<Triangle3D> lt = new ArrayList<Triangle3D>(w.getTriangles().size());
        for(Triangle3D t : w.getTriangles()) {
            lt.add(transformTriangle(t));
        }
        return new World(lt);
    }

    public String run()
    {            
        initTransMatrix();
        World cameraTransformedWorld = transformWorld(originalWorld);

        return "";
    }
}
