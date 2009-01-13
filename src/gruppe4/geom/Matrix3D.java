package geom;

public class Matrix3D {
  private Vector3D[] a;
  
  public Matrix3D(Vector3D u, Vector3D v, Vector3D w)
  {
	  a = new Vector3D[3];
	  a[0] = u;
	  a[1] = v;
	  a[2] = w;
  }
  
  public Vector3D dot(Vector3D v)
  {
	  return new Vector3D( a[0].dot(v), a[1].dot(v), a[2].dot(v));
  } 
  
  public static void main(String[] args)
  {
	Vector3D e1 = new Vector3D(1,0,0);   
	Vector3D e2 = new Vector3D(0,1,0);   
	Vector3D e3 = new Vector3D(0,0,1);
	
	Matrix3D identity = new Matrix3D(e1,e2,e3);
	
	Vector3D v = new Vector3D(3,4,5);
	Vector3D u = identity.dot(v);
	
	System.out.println(u.x + " " + u.y + " " + u.z);
  }  
}
