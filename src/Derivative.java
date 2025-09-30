public class Derivative{
    public static Vector2D takePartialDerivativeA(Differentiand d, Vector2D in, double da){
        double compA = (d.f(new Vector2D(in.x+da, in.y)).x - d.f(in).x)/ da;
        double compB = (d.f(new Vector2D(in.x+da, in.y)).y - d.f(in).y)/ da;

        return new Vector2D(compA, compB);
    }
    public static Vector2D takePartialDerivativeB(Differentiand d, Vector2D in, double db){
        double compA = (d.f(new Vector2D(in.x, in.y+db)).x - d.f(in).x)/ db;
        double compB = (d.f(new Vector2D(in.x, in.y+db)).y - d.f(in).y)/ db;

        return new Vector2D(compA, compB);
    }
}
