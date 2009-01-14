
package gruppe4;

public class Main {

    static void prn(Object... args) 
    {
        for(int i=0; i<args.length; i++)
            System.out.println(args[i]);
    }

    public static void main(String[] args)
    {
        prn("hello");

    }
}