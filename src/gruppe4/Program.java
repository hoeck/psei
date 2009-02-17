package gruppe4;

import gruppe4.geom.*;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements the program logic. 
 *
 */
public class Program {

    World world;
    Vector3D cameraView;
    Vector3D cameraPos;


    /**
     * Creates the program with the given parameters.
     *
     * @param in a <code>Reader</code> to initialise the world
     * @param camViewStr the camera direction
     * @param camPosStr the camera position
     * @exception IOException if an error occurs
     * @exception Exception if an error occurs
     */
    public Program(Reader in, String camViewStr, String camPosStr) throws IOException, IllegalArgumentException
    {
        world = new World(in);
        cameraView = new Vector3D(camViewStr);
        cameraPos = new Vector3D(camPosStr);
    } 


    /**
     * create the transformation matrix
     *
     */
    Matrix3D createTransMatrix()
    {
        Vector3D oben = new Vector3D(0,-1,0);
        Vector3D camX = cameraView.crossProduct(oben);
        Vector3D camY = cameraView.crossProduct(camX);
        return new Matrix3D(camX.normalize(), camY.normalize(), cameraView.normalize());
    }


    /**
     * Run the program with the given input data and options.
     *
     * @return a <code>String</code> value
     */
    public void run(Writer out) throws IOException
    {            
        Matrix3D transMatrix = createTransMatrix();
        world.transform(transMatrix, cameraPos);
        world.filterVisible();
        world.filterBackface(1);
        world.sortDepth();
        world.project(2f);
        world.write(out);
    }


    /**
     * Create a world from a stream of koordinates from System.in
     * and the program with it writing results to System.out.
     * 
     * @param camViewStr a readable {@link Vector3D} representing the camera view vector
     * @param camPosStr a readable {@link Vector3D} representing the camera position
     * @exception IOException if an error occurs
     * @exception Exception if an error occurs
     */
    static void createAndRunFromStdio(String camViewStr, String camPosStr) throws IOException, IllegalArgumentException
    {
        Program p = new Program(new InputStreamReader(System.in), camViewStr, camPosStr);
        p.run(new OutputStreamWriter(System.out));
    }
}


